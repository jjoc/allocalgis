package com.geopista.app.eiel.forms;


import com.geopista.app.AppContext;
import com.geopista.app.eiel.ConstantesLocalGISEIEL;
import com.geopista.app.eiel.beans.Depuradora1EIEL;
import com.geopista.app.eiel.panels.Depuradoras1Panel;
import com.geopista.app.eiel.panels.EditingInfoPanel;
import com.geopista.app.eiel.panels.GeopistaEditorPanel;
import com.geopista.app.eiel.utils.EdicionOperations;
import com.geopista.feature.AbstractValidator;
import com.geopista.feature.GeopistaFeature;
import com.geopista.feature.GeopistaSchema;
import com.geopista.model.GeopistaLayer;
import com.geopista.util.ApplicationContext;
import com.geopista.util.FeatureDialogHome;
import com.geopista.util.FeatureExtendedForm;
import com.vividsolutions.jump.util.Blackboard;



public class Depuradora1PanelExtendedForm implements FeatureExtendedForm
{
    
    public Depuradora1PanelExtendedForm()
    {  
        
    }
    
    public void setApplicationContext(ApplicationContext context)
    {
        
    }
    
    public void flush()
    {
    }
    
    public boolean checkPanels()
    {
        return true;
    }
    
    
    public AbstractValidator getValidator()
    {
        return null;
    }

    public void disableAll() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void initialize(FeatureDialogHome fd)
    {      
        GeopistaSchema esquema = (GeopistaSchema)fd.getFeature().getSchema();        
        
        AppContext.getApplicationContext().getBlackboard().put("featureDialog", fd);
        
        Object obj = fd.getFeature().getAttribute(esquema.getAttributeByColumn("id"));
        if(obj!=null && !obj.equals("") && 
                ((esquema.getGeopistalayer()!=null && !esquema.getGeopistalayer().isExtracted() && !esquema.getGeopistalayer().isLocal()) 
                        || (esquema.getGeopistalayer()==null) && fd.getFeature() instanceof GeopistaFeature && !((GeopistaFeature)fd.getFeature()).getLayer().isExtracted()))
        {              
        	
        	String clave = null;
        	if (fd.getFeature().getAttribute(esquema.getAttributeByColumn("clave"))!=null){
        		clave=(fd.getFeature().getAttribute(esquema.getAttributeByColumn("clave"))).toString();
        	}
        	
        	String codprov = null;
        	if (fd.getFeature().getAttribute(esquema.getAttributeByColumn("codprov"))!=null){
        		codprov=(fd.getFeature().getAttribute(esquema.getAttributeByColumn("codprov"))).toString();
        	}
        	
        	String codmunic = null;
        	if (fd.getFeature().getAttribute(esquema.getAttributeByColumn("codmunic"))!=null){
        		codmunic=(fd.getFeature().getAttribute(esquema.getAttributeByColumn("codmunic"))).toString();
        	}
        	
        	String orden_ed = null;
        	if (fd.getFeature().getAttribute(esquema.getAttributeByColumn("orden_ed"))!=null){
        		orden_ed=(fd.getFeature().getAttribute(esquema.getAttributeByColumn("orden_ed"))).toString();
        	}
        	
            AppContext app =(AppContext) AppContext.getApplicationContext();
            Blackboard Identificadores = app.getBlackboard();
            EdicionOperations operations = new EdicionOperations();
            Identificadores.put("depuradora1_panel", operations.getPanelDepuradora1EIEL(clave,codprov,codmunic,orden_ed));
            Depuradoras1Panel depuradoras= new Depuradoras1Panel();  
            depuradoras.loadDataIdentificacion(clave,codprov,codmunic,orden_ed);
            depuradoras.loadData();
            this.depuradorasPanel = depuradoras;           
            fd.addPanel(depuradoras);     
        }  
        
}
    
    private Depuradoras1Panel depuradorasPanel = null;

    public void execute() throws Exception 
    {
    	if (depuradorasPanel != null) {
    		if (depuradorasPanel.datosMinimosYCorrectos()){
    			GeopistaLayer geopistaLayer= (GeopistaLayer) GeopistaEditorPanel.getEditor().getLayerManager().getLayer(ConstantesLocalGISEIEL.DEPURADORAS_LAYER);
    			String idLayer = geopistaLayer.getSystemId();
    			depuradorasPanel.okPressed();
    			Depuradora1EIEL depuradora1 = (Depuradora1EIEL) AppContext.getApplicationContext().getBlackboard().get("depuradora1_panel");
    			ConstantesLocalGISEIEL.clienteLocalGISEIEL.insertarElemento(depuradorasPanel.getDepuradora1(depuradora1), idLayer, ConstantesLocalGISEIEL.DEPURADORAS1);
    			
    			if (EditingInfoPanel.getInstance() != null && EditingInfoPanel.getInstance().getJPanelTree()!=null && EditingInfoPanel.getInstance().getJPanelTree().getPatronSelected()!= null){
    				if (EditingInfoPanel.getInstance().getJPanelTree().getPatronSelected().equals(ConstantesLocalGISEIEL.DEPURADORAS1)){
    					EditingInfoPanel.getInstance().listarDatosTabla();
    				}
    			}
    		} 
    	}
	}

}