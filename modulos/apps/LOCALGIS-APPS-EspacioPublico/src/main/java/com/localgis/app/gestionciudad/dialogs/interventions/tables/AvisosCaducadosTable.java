/**
 * AvisosCaducadosTable.java
 * � MINETUR, Government of Spain
 * This program is part of LocalGIS
 * This program is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 2 of the License, or (at your option) any later version.
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package com.localgis.app.gestionciudad.dialogs.interventions.tables;

import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableModel;

import com.localgis.app.gestionciudad.beans.LocalGISIntervention;
import com.localgis.app.gestionciudad.dialogs.interventions.tables.models.AvisoCaducadoTableModel;
import com.localgis.app.gestionciudad.dialogs.interventions.utils.UtilidadesAvisosPanels;
import com.localgis.app.gestionciudad.utils.TableSorted;

/**
 * @author javieraragon
 *
 */
public class AvisosCaducadosTable extends JTable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1568556948413083007L;
	
	private AvisoCaducadoTableModel avisoCaducadoTableModel = null;
	
	public AvisosCaducadosTable(ArrayList<LocalGISIntervention> listaAvisos){
		super();

		avisoCaducadoTableModel  = new AvisoCaducadoTableModel();

		TableSorted tblSorted= new TableSorted((TableModel)avisoCaducadoTableModel);
		tblSorted.setTableHeader(this.getTableHeader());
		this.setModel(tblSorted);
		this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.setCellSelectionEnabled(false);
		this.setColumnSelectionAllowed(false);
		this.setRowSelectionAllowed(true);
		this.getTableHeader().setReorderingAllowed(false);
		
		((AvisoCaducadoTableModel)((TableSorted)this.getModel()).getTableModel()).setData(listaAvisos);
		
		this.pakcsAvisosCaducadosColumns();
	}
	
	private void pakcsAvisosCaducadosColumns(){
		UtilidadesAvisosPanels.packColumns(this, 2);
		this.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		
		DefaultTableCellRenderer tcrCenter = new DefaultTableCellRenderer();
		tcrCenter.setHorizontalAlignment(SwingConstants.CENTER);
		
		DefaultTableCellRenderer tcrRight = new DefaultTableCellRenderer();
		tcrRight.setHorizontalAlignment(SwingConstants.LEFT);

		this.getColumnModel().getColumn(0).setCellRenderer(tcrCenter);
		this.getColumnModel().getColumn(1).setCellRenderer(tcrRight);
		this.getColumnModel().getColumn(2).setCellRenderer(tcrCenter);
		this.getColumnModel().getColumn(3).setCellRenderer(tcrCenter);
	}

}
