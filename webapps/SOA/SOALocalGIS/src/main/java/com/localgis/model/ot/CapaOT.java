/**
 * CapaOT.java
 * � MINETUR, Government of Spain
 * This program is part of LocalGIS
 * This program is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 2 of the License, or (at your option) any later version.
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package com.localgis.model.ot;

import java.util.ArrayList;

public class CapaOT {
	
	private String patron = null;
	private String nombreCapa = null;
	private ArrayList subtipos = new ArrayList();
    
	public String getPatron() {
		return patron;
	}
	public void setPatron(String patron) {
		this.patron = patron;
	}
	public String getNombreCapa() {
		return nombreCapa;
	}
	public void setNombreCapa(String nombreCapa) {
		this.nombreCapa = nombreCapa;
	}
	/**
	 * @return the subtipos
	 */
	public ArrayList getSubtipos() {
		return subtipos;
	}
	/**
	 * @param subtipos the subtipos to set
	 */
	public void setSubtipos(ArrayList subtipos) {
		this.subtipos = subtipos;
	}
}
