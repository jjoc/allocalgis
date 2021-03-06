/*
 * The Unified Mapping Platform (JUMP) is an extensible, interactive GUI
 * for visualizing and manipulating spatial features with geometry and attributes.
 *
 * Copyright (C) 2003 Vivid Solutions
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 *
 * For more information, contact:
 *
 * Vivid Solutions
 * Suite #1A
 * 2328 Government Street
 * Victoria BC  V8T 5G5
 * Canada
 *
 * (250)385-6040
 * www.vividsolutions.com
 */
package com.vividsolutions.jump.workbench.ui.cursortool;

import java.awt.Cursor;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.HashMap;

import com.vividsolutions.jump.workbench.ui.ILayerViewPanel;
import com.vividsolutions.jump.workbench.ui.LayerViewPanel;
import com.vividsolutions.jump.workbench.ui.WorkbenchFrame;
import com.vividsolutions.jump.workbench.ui.zoom.PanTool;
import com.vividsolutions.jump.workbench.ui.zoom.ZoomTool;


/**
 * Delegates to different CursorTools depending on whether various modifier
 * keys are pressed (Ctrl, Shift, Alt). The term "quasimode" refers to a mode that
 * is only in existence as long as a key is held down -- the mode vanishes as soon
 * as the key is released. For more information, see the book "Humane Interfaces"
 * by Jef Raskin.
 */
public class QuasimodeTool extends DelegatingTool {

    //Sometimes when I try to use the Alt quasimode, the cursor becomes the default
    //cursor (arrow) and stays that way. This seems to have been fixed in JDK 1.4,
    //in which the default cursor stays only for a split second. [Jon Aquino]

    public QuasimodeTool(CursorTool defaultTool) {
        super(defaultTool);
        add(new ModifierKeySpec(false, false, false), defaultTool);
        cursor = defaultTool.getCursor();
    }

    private CursorTool getDefaultTool() {
        return (CursorTool) keySpecToToolMap.get(new ModifierKeySpec(false, false, false));
    }

    public Cursor getCursor() {
        return cursor;
    }

    private Cursor cursor;

    private CursorTool getTool(KeyEvent e) {
    if (e.getKeyCode()==0)
    	return getDefaultTool(); //TODO: QUITAR, PARA PRUEBAS SOLO
        CursorTool tool =
            (CursorTool) keySpecToToolMap.get(
                new ModifierKeySpec(
                    e.isControlDown(),
                    e.isShiftDown(),
                    e.isAltDown() || e.isMetaDown()));
        return tool != null ? tool : getDefaultTool();
    }

    private KeyListener keyListener = new KeyListener() {
        public void keyTyped(KeyEvent e) {}

        public void keyPressed(KeyEvent e) {
            keyStateChanged(e);
        }

        public void keyReleased(KeyEvent e) {
            keyStateChanged(e);
        }

        private void keyStateChanged(KeyEvent e) {
            setTool(e);
        }
    };

    private void setTool(KeyEvent e) {
        cursor = getTool(e).getCursor();
        panel.setCursor(cursor);
        currentKeyEvent = e;
        if (getDelegate().isGestureInProgress()
            && getDelegate() != getTool(e)
            && getDelegate() != getDefaultTool()) {
            setDelegate(getDefaultTool());
        }
    }

    private KeyEvent currentKeyEvent = null;

    private LayerViewPanel panel;
    private WorkbenchFrame frame;

    public void activate(final ILayerViewPanel panel) {
        this.panel = (LayerViewPanel)panel;
        super.activate(this.panel);
        //Cache WorkbenchFrame because in JDK 1.3 when I minimize an internal
        //frame, SwingUtilities#windowForComponent returns null for that frame.
        //A Swing bug. [Jon Aquino]
        frame =  AbstractCursorTool.workbenchFrame(this.panel);
        if (frame != null) {
            frame.addEasyKeyListener(keyListener);
            //Workaround for the following:
            // * Use WorkbenchFrame#addKeyboardShortcut for a plug-in that
            //   pops up a dialog (or could pop up an error dialog). Assign it to Ctrl-A,
            //   for example.
            // * Press Ctrl-A. The Ctrl quasimode happens. But also the dialog pops up.
            // * Release Ctrl. Close the dialog. Note that the cursor shows we're still 
            //    in the Ctrl quasimode! This is because the dialog consumed the
            //    key-up event.
            //So we're working around this by clearing the quasimode when the
            //WorkbenchFrame is activated (e.g. when a dialog is closed). [Jon Aquino]
            frame.addWindowListener(new WindowAdapter() {
                public void windowActivated(WindowEvent e) {
					super.windowActivated(e);
					setTool(new KeyEvent((LayerViewPanel)panel, KeyEvent.KEY_PRESSED, 
                        0, 0, KeyEvent.VK_UNDEFINED, KeyEvent.CHAR_UNDEFINED));
					// REstaura el cursor que tuviera la herramienta activa.
					// Este provoca que los iconos se queden incorrectos
					//cursor=panel.getCurrentCursorTool().getCursor();
					//panel.setCursor(cursor);

                }
            });
        }
    }

    public void mousePressed(MouseEvent e) {
        setDelegate(currentKeyEvent != null ? getTool(currentKeyEvent) : getDefaultTool());
        super.mousePressed(e);
    }

    public void deactivate() {
        super.deactivate();
        if (frame != null) {
            frame.removeEasyKeyListener(keyListener);
        }
    }

    private HashMap keySpecToToolMap = new HashMap();

    public QuasimodeTool add(ModifierKeySpec keySpec, CursorTool tool) {
        if (keySpecToToolMap.containsKey(keySpec)) {
            return this;
        }
        keySpecToToolMap.put(
            keySpec,
            tool != null
                ? (tool.isRightMouseButtonUsed() ? tool : new LeftClickFilter(tool))
                : null);
        return this;
    }

    public static class ModifierKeySpec {
        public ModifierKeySpec(boolean needsControl, boolean needsShift, boolean needsAltOrMeta) {
            this.needsControl = needsControl;
            this.needsShift = needsShift;
            this.needsAltOrMeta = needsAltOrMeta;
        }
        private boolean needsShift, needsAltOrMeta, needsControl;
        public int hashCode() {
            //Map will be small anyway. [Jon Aquino]
        // return 0;
        // TODO: Hay un comportamiento extra�o con los hash map en lso que solo se almacena la clave 0.
            return (needsShift?1:0) | (needsControl?2:0) | (needsAltOrMeta?4:0);
        }
        public boolean equals(Object obj) {
            if (!(obj instanceof ModifierKeySpec)) {
                return false;
            }
            ModifierKeySpec other = (ModifierKeySpec) obj;
            return needsControl == other.needsControl
                && needsShift == other.needsShift
                && needsAltOrMeta == other.needsAltOrMeta;
        }
    }

    public static QuasimodeTool addStandardQuasimodes(CursorTool tool) {
        QuasimodeTool quasimodeTool =
            tool instanceof QuasimodeTool ? (QuasimodeTool) tool : new QuasimodeTool(tool);
        quasimodeTool.add(new ModifierKeySpec(false, false, true), new ZoomTool());
        quasimodeTool.add(new ModifierKeySpec(false, true, true), new PanTool());
        SelectFeaturesTool selectFeaturesTool = new SelectFeaturesTool() {
            protected boolean selectedLayersOnly() {
                return false;
            }
        };
        quasimodeTool.add(new ModifierKeySpec(true, false, false), selectFeaturesTool);
        quasimodeTool.add(new ModifierKeySpec(true, true, false), selectFeaturesTool);
        quasimodeTool.add(new ModifierKeySpec(true, false, true), new FeatureInfoTool());
        return quasimodeTool;
    }
   public void clean(){
	  // System.out.println("Borrando quasimode tool");
	   if (keySpecToToolMap!=null)
		   keySpecToToolMap.clear();
	   keyListener=null;
    }

}
