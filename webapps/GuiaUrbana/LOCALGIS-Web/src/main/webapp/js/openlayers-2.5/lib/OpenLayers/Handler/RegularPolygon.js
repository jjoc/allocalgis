/* Copyright (c) 2006-2007 MetaCarta, Inc., published under the BSD license.
 * See http://svn.openlayers.org/trunk/openlayers/release-license.txt 
 * for the full text of the license. */


/**
 * @requires OpenLayers/Handler/Drag.js
 * 
 * Class: OpenLayers.Handler.RegularPolygon
 * Handler to draw a regular polygon on the map.  Polygon is displayed on mouse
 *     down, moves or is modified on mouse move, and is finished on mouse up.
 *     The handler triggers callbacks for 'done' and 'cancel'.  Create a new
 *     instance with the <OpenLayers.Handler.RegularPolygon> constructor.
 * 
 * Inherits from:
 *  - <OpenLayers.Handler>
 */
OpenLayers.Handler.RegularPolygon = OpenLayers.Class(OpenLayers.Handler.Drag, {
    
    /**
     * APIProperty: sides
     * {Integer} Number of sides for the regular polygon.  Needs to be greater
     *     than 2.  Defaults to 4.
     */
    sides: 4,

    /**
     * APIProperty: radius
     * {Float} Optional radius in map units of the regular polygon.  If this is
     *     set to some non-zero value, a polygon with a fixed radius will be
     *     drawn and dragged with mose movements.  If this property is not
     *     set, dragging changes the radius of the polygon.  Set to null by
     *     default.
     */
    radius: null,
    
    /**
     * APIProperty: snapAngle
     * {Float} If set to a non-zero value, the handler will snap the polygon
     *     rotation to multiples of the snapAngle.  Value is an angle measured
     *     in degrees counterclockwise from the positive x-axis.  
     */
    snapAngle: null,
    
    /**
     * APIProperty: snapToggle
     * {String} If set, snapToggle is checked on mouse events and will set
     *     the snap mode to the opposite of what it currently is.  To disallow
     *     toggling between snap and non-snap mode, set freehandToggle to
     *     null.  Acceptable toggle values are 'shiftKey', 'ctrlKey', and
     *     'altKey'. Snap mode is only possible if this.snapAngle is set to a
     *     non-zero value.
     */
    snapToggle: 'shiftKey',
    
    /**
     * APIProperty: persist
     * {Boolean} Leave the feature rendered until clear is called.  Default
     *     is false.  If set to true, the feature remains rendered until
     *     clear is called, typically by deactivating the handler or starting
     *     another drawing.
     */
    persist: false,

    /**
     * Property: angle
     * {Float} The angle from the origin (mouse down) to the current mouse
     *     position, in radians.  This is measured counterclockwise from the
     *     positive x-axis.
     */
    angle: null,

    /**
     * Property: fixedRadius
     * {Boolean} The polygon has a fixed radius.  True if a radius is set before
     *     drawing begins.  False otherwise.
     */
    fixedRadius: false,

    /**
     * Property: feature
     * {<OpenLayers.Feature.Vector>} The currently drawn polygon feature
     */
    feature: null,

    /**
     * Property: layer
     * {<OpenLayers.Layer.Vector>} The temporary drawing layer
     */
    layer: null,

    /**
     * Property: origin
     * {<OpenLayers.Geometry.Point>} Location of the first mouse down
     */
    origin: null,

    /**
     * Constructor: OpenLayers.Handler.RegularPolygon
     * Create a new regular polygon handler.
     *
     * Parameters:
     * control - {<OpenLayers.Control>} The control that owns this handler
     * callbacks - {Array} An object with a 'done' property whos value is a
     *     function to be called when the polygon drawing is finished.
     *     The callback should expect to recieve a single argument,
     *     the polygon geometry.  If the callbacks object contains a
     *     'cancel' property, this function will be called when the
     *     handler is deactivated while drawing.  The cancel should
     *     expect to receive a geometry.
     * options - {Object} An object with properties to be set on the handler.
     *     If the options.sides property is not specified, the number of sides
     *     will default to 4.
     */
    initialize: function(control, callbacks, options) {
        this.style = OpenLayers.Util.extend(OpenLayers.Feature.Vector.style['default'], {});

        OpenLayers.Handler.prototype.initialize.apply(this,
                                                [control, callbacks, options]);
        this.options = (options) ? options : new Object();
    },
    
    /**
     * APIMethod: setOptions
     * 
     * Parameters:
     * newOptions - {Object} 
     */
    setOptions: function (newOptions) {
        OpenLayers.Util.extend(this.options, newOptions);
        OpenLayers.Util.extend(this, newOptions);
    },
    
    /**
     * APIMethod: activate
     * Turn on the handler.
     *
     * Return:
     * {Boolean} The handler was successfully activated
     */
    activate: function() {
        var activated = false;
        if(OpenLayers.Handler.prototype.activate.apply(this, arguments)) {
            // create temporary vector layer for rendering geometry sketch
            var options = {displayInLayerSwitcher: false};
            this.layer = new OpenLayers.Layer.Vector(this.CLASS_NAME, options);
            this.map.addLayer(this.layer);
            activated = true;
        }
        return activated;
    },

    /**
     * APIMethod: deactivate
     * Turn off the handler.
     *
     * Return:
     * {Boolean} The handler was successfully deactivated
     */
    deactivate: function() {
        var deactivated = false;
        if(OpenLayers.Handler.Drag.prototype.deactivate.apply(this, arguments)) {
            // call the cancel callback if mid-drawing
            if(this.dragging) {
                this.cancel();
            }
            this.map.removeLayer(this.layer, false);
            this.layer.destroy();
            if (this.feature) {
                this.feature.destroy();
            }    
            deactivated = true;
        }
        return deactivated;
    },
    
    /**
     * Method: downFeature
     * Start drawing a new feature
     *
     * Parameters:
     * evt - {Event} The drag start event
     */
    down: function(evt) {
        this.fixedRadius = !!(this.radius);
        var maploc = this.map.getLonLatFromPixel(evt.xy);
        this.origin = new OpenLayers.Geometry.Point(maploc.lon, maploc.lat);
        // create the new polygon
        if(!this.fixedRadius) {
            // smallest radius should not be less one pixel in map units
            // VML doesn't behave well with smaller
            this.radius = this.map.getResolution();
        }
        if(this.persist) {
            this.clear();
        }
        this.feature = new OpenLayers.Feature.Vector();
        this.createGeometry();
        this.layer.addFeatures([this.feature]);
        this.layer.drawFeature(this.feature, this.style);
    },
    
    /**
     * Method: move
     * Respond to drag move events
     *
     * Parameters:
     * evt - {Evt} The move event
     */
    move: function(evt) {
        var maploc = this.map.getLonLatFromPixel(evt.xy);
        var point = new OpenLayers.Geometry.Point(maploc.lon, maploc.lat);
        if(this.fixedRadius) {
            this.origin = point;
        } else {
            this.calculateAngle(point, evt);
            this.radius = Math.max(this.map.getResolution() / 2,
                                   point.distanceTo(this.origin));
        }
        this.modifyGeometry();
        this.layer.drawFeature(this.feature, this.style);
    },

    /**
     * Method: up
     * Finish drawing the feature
     *
     * Parameters:
     * evt - {Event} The mouse up event
     */
    up: function(evt) {
        this.finalize();
    },

    /**
     * Method: out
     * Finish drawing the feature.
     *
     * Parameters:
     * evt - {Event} The mouse out event
     */
    out: function(evt) {
        this.finalize();
    },

    /**
     * Method: createGeometry
     * Create the new polygon geometry.  This is called at the start of the
     *     drag and at any point during the drag if the number of sides
     *     changes.
     */
    createGeometry: function() {
        this.angle = Math.PI * ((1/this.sides) - (1/2));
        if(this.snapAngle) {
            this.angle += this.snapAngle * (Math.PI / 180);
        }
        this.feature.geometry = OpenLayers.Geometry.Polygon.createRegularPolygon(
            this.origin, this.radius, this.sides, this.snapAngle
        );
    },
    
    /**
     * Method: modifyGeometry
     * Modify the polygon geometry in place.
     */
    modifyGeometry: function() {
        var angle, dx, dy, point;
        var ring = this.feature.geometry.components[0];
        // if the number of sides ever changes, create a new geometry
        if(ring.components.length != (this.sides + 1)) {
            this.createGeometry();
        }
        for(var i=0; i<this.sides; ++i) {
            point = ring.components[i];
            angle = this.angle + (i * 2 * Math.PI / this.sides);
            point.x = this.origin.x + (this.radius * Math.cos(angle));
            point.y = this.origin.y + (this.radius * Math.sin(angle));
            point.clearBounds();
        }
    },
    
    /**
     * Method: calculateAngle
     * Calculate the angle based on settings.
     *
     * Parameters:
     * point - {<OpenLayers.Geometry.Point>}
     * evt - {Event}
     */
    calculateAngle: function(point, evt) {
        var alpha = Math.atan2(point.y - this.origin.y,
                               point.x - this.origin.x);
        if(this.snapAngle && (this.snapToggle && !evt[this.snapToggle])) {
            var snapAngleRad = (Math.PI / 180) * this.snapAngle;
            this.angle = Math.round(alpha / snapAngleRad) * snapAngleRad;
        } else {
            this.angle = alpha;
        }
    },

    /**
     * APIMethod: cancel
     * Finish the geometry and call the "cancel" callback.
     */
    cancel: function() {
        // the polygon geometry gets cloned in the callback method
        this.callback("cancel", null);
        this.finalize();
    },

    /**
     * Method: finalize
     * Finish the geometry and call the "done" callback.
     */
    finalize: function() {
        this.origin = null;
        this.radius = this.options.radius;
    },

    /**
     * APIMethod: clear
     * Clear any rendered features on the temporary layer.  This is called
     *     when the handler is deactivated, canceled, or done (unless persist
     *     is true).
     */
    clear: function() {
        this.layer.renderer.clear();
        this.layer.destroyFeatures();
    },
    
    /**
     * Method: callback
     * Trigger the control's named callback with the given arguments
     *
     * Parameters:
     * name - {String} The key for the callback that is one of the properties
     *     of the handler's callbacks object.
     * args - {Array} An array of arguments with which to call the callback
     *     (defined by the control).
     */
    callback: function (name, args) {
        // override the callback method to always send the polygon geometry
        if (this.callbacks[name]) {
            this.callbacks[name].apply(this.control,
                                       [this.feature.geometry.clone()]);
        }
        // since sketch features are added to the temporary layer
        // they must be cleared here if done or cancel
        if(!this.persist && (name == "done" || name == "cancel")) {
            this.clear();
        }
    },

    CLASS_NAME: "OpenLayers.Handler.RegularPolygon"
});
