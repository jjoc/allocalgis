/**
 * IntegracionEIELTableModel.java
 * © MINETUR, Government of Spain
 * This program is part of LocalGIS
 * This program is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 2 of the License, or (at your option) any later version.
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package com.geopista.app.inventario;

/**
 * Created by IntelliJ IDEA.
 * User: charo
 * Date: 07-jul-2006
 * Time: 10:11:55
 * To change this template use File | Settings | File Templates.
 */

import java.util.Enumeration;
import java.util.Hashtable;

import javax.swing.table.DefaultTableModel;

import com.geopista.app.utilidades.estructuras.Estructuras;
import com.geopista.protocol.inventario.InventarioEIELBean;
import com.geopista.protocol.inventario.ListaEIEL;

/**
 * Clase que implementa el modelo de datos de la tabla del panel BienesJPanel
 */

public class IntegracionEIELTableModel  extends DefaultTableModel {
    public static final int idIndex=4;
    public static final int idNombre=1;
    private String locale;
    private static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(IntegracionEIELTableModel.class);
    private static String[] eielModelNames = new String[] { "Tipo", "Nombre", "Gestión", "Estado", "Clave"};

   /* public ListaRoles getModelListaRoles() {
        return modelListaRoles;
    }*/


	public IntegracionEIELTableModel(String locale) {
		this.locale = locale;
	}
	
    public void setModelData(ListaEIEL listaEIEL) {
        try
        {
        	if (listaEIEL!=null){
	          //   modelListaRoles=listaRoles;
	            Hashtable auxListaEIEL= listaEIEL.gethEIEL();
	            for (Enumeration e=auxListaEIEL.elements();e.hasMoreElements();) {
				    InventarioEIELBean auxEIEL =(InventarioEIELBean) e.nextElement();
				    String gestion = "";
				    if (auxEIEL.getGestionEIEL()!=null){
				    	Estructuras.cargarEstructura("eiel_Gestión");
			          	if (Estructuras.getListaTipos().getDomainNode(auxEIEL.getGestionEIEL())!=null)
			          		gestion=Estructuras.getListaTipos().getDomainNode(auxEIEL.getGestionEIEL()).getTerm(locale);
			        }
				    String estado = "";
				    if (auxEIEL.getEstadoEIEL()!=null){
				    	Estructuras.cargarEstructura("eiel_Estado de conservación");
			          	if (Estructuras.getListaTipos().getDomainNode(auxEIEL.getEstadoEIEL())!=null)
			          		estado=Estructuras.getListaTipos().getDomainNode(auxEIEL.getEstadoEIEL()).getTerm(locale);
			        }
				    
				    
				    Object row[] = new Object[] { auxEIEL.getTipoEIEL(),auxEIEL.getNombreEIEL(), gestion, estado, auxEIEL.getUnionClaveEIEL()};
				    this.addRow(row);
			    }
	
		    	fireTableDataChanged();
        	}
        	else{
        		logger.info("No hay elementos de la EIEL para mostrar");
        	}


        }catch(Exception e)
        {
            logger.error("Error al poner la lista de elementos EIEL: ",e);
        }
	}

	public int getColumnCount() {
		return eielModelNames.length;
	}

	public String getColumnName(int c) {
		return eielModelNames[c];
	}
    public String setColumnName(int c, String sName) {
        return eielModelNames[c]=sName;
    }

	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}





}
