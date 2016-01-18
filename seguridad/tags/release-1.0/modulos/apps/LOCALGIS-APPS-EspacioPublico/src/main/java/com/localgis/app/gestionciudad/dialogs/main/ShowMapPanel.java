package com.localgis.app.gestionciudad.dialogs.main;


import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.Collection;
import javax.swing.JPanel;
import com.geopista.app.AppContext;
import com.geopista.editor.GeopistaEditor;
import com.geopista.security.GeopistaPermission;
import com.vividsolutions.jump.workbench.ui.InputChangedListener;

/**
 * @author javieraragon
 *
 */
public class ShowMapPanel extends JPanel
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1500581003450074379L;
	private boolean acceso;
	private GeopistaMapPanel geopistaMapPanel = null;

	AppContext aplicacion = (AppContext) AppContext.getApplicationContext();
	
	private String fileProperties = null;
	private int idMap = 0;

	public ShowMapPanel()
	{
		try
		{
			jbInit();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public ShowMapPanel(String fileProperties, int idMap)
	{
		this.fileProperties = fileProperties;
		this.idMap = idMap;
		
		try
		{
			jbInit();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	public JPanel getGeopistaMapPanel(String fileProperties, int idMap){

		geopistaMapPanel = new GeopistaMapPanel(fileProperties, idMap);
		
		return geopistaMapPanel;
	}



	private void jbInit() throws Exception
	{

		GeopistaPermission geopistaPerm = new GeopistaPermission("LocalGIS.edicion.EIEL");
		acceso = aplicacion.checkPermission(geopistaPerm,"EIEL");

		this.setLayout(new GridBagLayout());

		this.add(getGeopistaMapPanel(fileProperties, idMap), 
				new GridBagConstraints(0, 0, 1, 1, 1, 1,
						GridBagConstraints.CENTER, GridBagConstraints.BOTH,
						new Insets(0, 0, 0, 0), 0, 0));

		AppContext.getApplicationContext().getBlackboard().put("GeopistaEditor",geopistaMapPanel);

	}


	/**
	 * Tip: Delegate to an InputChangedFirer.
	 * @param listener a party to notify when the input changes (usually the
	 * WizardDialog, which needs to know when to update the enabled state of
	 * the buttons.
	 */
	public void add(InputChangedListener listener)
	{

	}

	public void remove(InputChangedListener listener)
	{

	}


	public String getTitle()
	{
		return "";
	}

	public String getID()
	{
		return "1";
	}

	public String getInstructions()
	{
		return "";
	}

	@SuppressWarnings("unchecked")
	public boolean isInputValid()
	{
		Collection<GeopistaEditor> lista = null;
		lista = geopistaMapPanel.getEditor().getLayerViewPanel().getSelectionManager().getFeaturesWithSelectedItems(
				geopistaMapPanel.getEditor().getLayerManager().getLayer("parcelas"));
		if (lista.size()==1)
			if (acceso) {
				return true;
			}
			else{
				return false;
			}

		else

			return false;
	}
}
