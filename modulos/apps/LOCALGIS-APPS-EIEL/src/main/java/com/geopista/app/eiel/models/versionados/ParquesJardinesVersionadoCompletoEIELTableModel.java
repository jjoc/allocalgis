/**
 * ParquesJardinesVersionadoCompletoEIELTableModel.java
 * � MINETUR, Government of Spain
 * This program is part of LocalGIS
 * This program is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 2 of the License, or (at your option) any later version.
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package com.geopista.app.eiel.models.versionados;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

import com.geopista.app.eiel.beans.ParquesJardinesEIEL;
import com.geopista.app.eiel.beans.VersionEiel;
import com.geopista.app.eiel.utils.LocalGISEIELUtils;
import com.geopista.app.utilidades.estructuras.Estructuras;
import com.vividsolutions.jump.I18N;

/**
 * Clase que implementa el modelo de datos de la tabla del panel BienesJPanel
 */

public class ParquesJardinesVersionadoCompletoEIELTableModel  extends DefaultTableModel {
	
	private static String[] columnNames = {
			I18N.get("LocalGISEIEL","localgiseiel.version.CampoVersion"),
			I18N.get("LocalGISEIEL","localgiseiel.version.CampoAccion"),
			I18N.get("LocalGISEIEL","localgiseiel.version.CampoUsuario"),
			I18N.get("LocalGISEIEL","localgiseiel.version.CampoFecha"),
            I18N.get("LocalGISEIEL","localgiseiel.tabla.pj.columna.clave"),
            I18N.get("LocalGISEIEL","localgiseiel.tabla.pj.columna.codprov"),
            I18N.get("LocalGISEIEL","localgiseiel.tabla.pj.columna.codmunic"),
            I18N.get("LocalGISEIEL","localgiseiel.tabla.pj.columna.codorden"),
            I18N.get("LocalGISEIEL","localgiseiel.tabla.pj.columna.codentidad"),
            I18N.get("LocalGISEIEL","localgiseiel.tabla.pj.columna.codnucleo"),
            I18N.get("LocalGISEIEL","localgiseiel.tabla.pj.columna.nombre_pj"),
            I18N.get("LocalGISEIEL","localgiseiel.tabla.pj.columna.tipo_pj"),
            I18N.get("LocalGISEIEL","localgiseiel.tabla.pj.columna.titular_pj"),
            I18N.get("LocalGISEIEL","localgiseiel.tabla.pj.columna.gestor_pj"),
            I18N.get("LocalGISEIEL","localgiseiel.tabla.pj.columna.s_cubi"),
            I18N.get("LocalGISEIEL","localgiseiel.tabla.pj.columna.s_aire"),
            I18N.get("LocalGISEIEL","localgiseiel.tabla.pj.columna.s_solar"),
            I18N.get("LocalGISEIEL","localgiseiel.tabla.pj.columna.agua"),
            I18N.get("LocalGISEIEL","localgiseiel.tabla.pj.columna.saneamiento"),
            I18N.get("LocalGISEIEL","localgiseiel.tabla.pj.columna.electricidad"),
            I18N.get("LocalGISEIEL","localgiseiel.tabla.pj.columna.comedor"),
            I18N.get("LocalGISEIEL","localgiseiel.tabla.pj.columna.juegos_inf"),
            I18N.get("LocalGISEIEL","localgiseiel.tabla.pj.columna.otras_pj"),
            I18N.get("LocalGISEIEL","localgiseiel.tabla.pj.columna.estado_pj"),
            I18N.get("LocalGISEIEL","localgiseiel.tabla.pj.columna.fecha_revision"),
            I18N.get("LocalGISEIEL","localgiseiel.tabla.pj.columna.estado_revision"),
            I18N.get("LocalGISEIEL","localgiseiel.tabla.pj.columna.observ"),
		    I18N.get("LocalGISEIEL","localgiseiel.tabla.pj.columna.bloqueado"),
		    I18N.get("LocalGISEIEL","localgiseiel.tabla.pj.columna.acceso_s_ruedas"),
		    I18N.get("LocalGISEIEL","localgiseiel.tabla.pj.columna.obra_ejec")};

    public ParquesJardinesVersionadoCompletoEIELTableModel() {        

    }

    private ArrayList lstElementos = new ArrayList();

    /**
     * @return n�mero de columnas de la tabla
     */
    public int getColumnCount() {
    	if (columnNames != null)
    		return columnNames.length;
    	else
    		return 0;
    }
    
    /**
     * @return n�mero de filas de la tabla
     */
    public int getRowCount() {
    	if (lstElementos != null)
    		return lstElementos.size();
    	else
    		return 0;
    }
    
    
    /**
     * Devuelve el nombre de la columna solicitada
     * @param col �ndice de la columna
     * @return nombre de la columna
     */
    public String getColumnName(int col) {
        return columnNames[col];
    }
    
    /**
     * Devuelve el objeto que contiene la celda en la posici�n indicada
     * @param row �ndice de la fila
     * @param col �ndice de la columna
     * 
     * @return Objeto contenido en la posici�n seleccionada
     */
    public Object getValueAt(int row, int col) {
        
        if (lstElementos.get(row)==null)
            return null;
        
        Object[] fila = (Object[]) lstElementos.get(row);
		switch (col) {
			case 0:
			case 1:
			case 2:
			case 4:
			case 5:
			case 6:
			case 7:				
			case 8:
			case 9:
			case 10:
			case 26:
				return	 fila[col];
			case 11:
	            Estructuras.cargarEstructura("eiel_Tipo de parque");
	            return LocalGISEIELUtils.getNameFromEstructura((String) fila[col]);
	        
			case 12:
	            Estructuras.cargarEstructura("eiel_Titularidad_General_equip");
	            return LocalGISEIELUtils.getNameFromEstructura((String) fila[col]);
	       
			case 13:
	            Estructuras.cargarEstructura("eiel_Gestor_General_equip");
	            return LocalGISEIELUtils.getNameFromEstructura((String) fila[col]);
	       
			case 17:
	            Estructuras.cargarEstructura("eiel_Existencia de agua");
	            return LocalGISEIELUtils.getNameFromEstructura((String) fila[col]);
	       
			case 18:
	            Estructuras.cargarEstructura("eiel_saneamiento");
	            return LocalGISEIELUtils.getNameFromEstructura((String) fila[col]);
	        
			case 19:
	            Estructuras.cargarEstructura("eiel_electricidad");
	            return LocalGISEIELUtils.getNameFromEstructura((String) fila[col]);
	        
			case 20:
	            Estructuras.cargarEstructura("eiel_comedor");
	            return LocalGISEIELUtils.getNameFromEstructura((String) fila[col]);
	        
			case 21:
	            Estructuras.cargarEstructura("eiel_juegos_inf");
	            return LocalGISEIELUtils.getNameFromEstructura((String) fila[col]);
	        
			case 22:
	            Estructuras.cargarEstructura("eiel_Otras");
	            return LocalGISEIELUtils.getNameFromEstructura((String) fila[col]);
	       
			case 23:
	            Estructuras.cargarEstructura("eiel_Estado de conservaci�n");
	            return LocalGISEIELUtils.getNameFromEstructura((String) fila[col]);
			case 25:
				Estructuras.cargarEstructura("eiel_Estado de revisi�n");
            	return fila[col]!=null? LocalGISEIELUtils.getNameFromEstructura(Integer.toString((Integer)fila[col])):"";
			case 28:
		          Estructuras.cargarEstructura("eiel_Acceso con silla de ruedas");
		            return LocalGISEIELUtils.getNameFromEstructura((String) fila[col]);
		       
			case 29:
	            Estructuras.cargarEstructura("eiel_Obra ejecutada");
	            return LocalGISEIELUtils.getNameFromEstructura((String) fila[col]);
	          
			case 14:
			case 15:	
			case 16:
			case 24:
			case 27:
				return fila[col] != null ? fila[col].toString() : "";
			case 3:	
        		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ");
        		return fila[col] != null ? formatter.format(fila[col]):"";				
			default:
				return null;
		}  
    }
    
    /**
     * Devuelve la Depuradora1EIEL completa de la fila seleccionada
     * @param row �ndice de la fila cuyo objeto FincaCatastro se solicita
     * @return FincaCatastro completa
     */
    public ParquesJardinesEIEL getValueAt(int row) {
        
    	Object[] fila = (Object[]) lstElementos.get(row);
    	ParquesJardinesEIEL obj = new ParquesJardinesEIEL();
    	 

		VersionEiel version = new VersionEiel();
		version.setIdVersion((Integer) fila[0]);
		version.setAccion((String) fila[1]);
		version.setUsuario((String) fila[2]);
		version.setFecha((Date) fila[3]); 
		
		obj.setVersion(version);
		obj.setClave((String) fila[4]); 
		obj.setCodINEProvincia((String) fila[5]);
		obj.setCodINEMunicipio((String) fila[6]); 
		obj.setCodOrden((String) fila[7]);
		obj.setCodINEEntidad((String) fila[8]); 
		obj.setCodINEPoblamiento((String) fila[9]);
		obj.setNombre((String) fila[10]); 
		obj.setTipo((String) fila[11]); 
		obj.setTitularidad((String) fila[12]); 
		obj.setGestion((String) fila[13]);
		obj.setSupCubierta((Integer) fila[14]); 
		obj.setSupLibre((Integer) fila[15]); 
		obj.setSupSolar((Integer) fila[16]); 
		obj.setAgua((String) fila[17]); 
		obj.setSaneamiento((String) fila[18]);
		obj.setElectricidad((String) fila[19]);
		obj.setComedor((String) fila[20]);
		obj.setJuegosInf((String) fila[21]); 
		obj.setOtros((String) fila[22]); 
		obj.setEstado((String) fila[23]);
		obj.setFechaRevision((Date) fila[24]); 
		obj.setEstadoRevision((Integer) fila[25]); 
		obj.setObservaciones((String) fila[26]); 	
		obj.setBloqueado((String) fila[27]); 
		obj.setAccesoSilla((String) fila[28]);
		obj.setObra_ejec((String) fila[29]);
		
        return obj;
        
    }
    /**
     * JTable uses this method to determine the default renderer/
     * editor for each cell.  
     */
    public Class getColumnClass(int c) {
        
        if (getValueAt(0, c)!=null)        
            return getValueAt(0, c).getClass();
        else
            return String.class;
    }
    
    public boolean isCellEditable(int row, int col) {
        return false;
    }
    
    
    /**
     * Establece los datos mostrados en el modelo
     * @param datos Datos a mostrar en el modelo
     */
    public void setData (ArrayList datos)
    {
        this.lstElementos = datos;
    }
    
    /**
     * Recupera los datos del modelo
     * @return Datos del modelo
     */
    public ArrayList getData (){
        return lstElementos;
    }    

}
