/**
 * V_alumbrado_bean.java
 * � MINETUR, Government of Spain
 * This program is part of LocalGIS
 * This program is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 2 of the License, or (at your option) any later version.
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package com.geopista.server.database.validacion.beans;


public class V_alumbrado_bean {

		    String provincia="-";
		    String municipio="-";
		    String entidad="-";
		    String nucleo="-";
		    String ah_ener_rl="-";
		    String ah_ener_ri="-";
		    String calidad="-";
		   double pot_instal;
		    int puntos_luz;
		    
			public String getProvincia() {
				return provincia;
			}
			public void setProvincia(String provincia) {
				this.provincia = provincia;
			}
			public String getMunicipio() {
				return municipio;
			}
			public void setMunicipio(String municipio) {
				this.municipio = municipio;
			}
			public String getEntidad() {
				return entidad;
			}
			public void setEntidad(String entidad) {
				this.entidad = entidad;
			}
			public String getNucleo() {
				return nucleo;
			}
			public void setNucleo(String nucleo) {
				this.nucleo = nucleo;
			}
			public String getAh_ener_rl() {
				return ah_ener_rl;
			}
			public void setAh_ener_rl(String ah_ener_rl) {
				this.ah_ener_rl = ah_ener_rl;
			}
			public String getAh_ener_ri() {
				return ah_ener_ri;
			}
			public void setAh_ener_ri(String ah_ener_ri) {
				this.ah_ener_ri = ah_ener_ri;
			}
			public String getCalidad() {
				return calidad;
			}
			public void setCalidad(String calidad) {
				this.calidad = calidad;
			}
			public double getPot_instal() {
				return pot_instal;
			}
			public void setPot_instal(double pot_instal) {
				this.pot_instal = pot_instal;
			}
			public int getPuntos_luz() {
				return puntos_luz;
			}
			public void setPuntos_luz(int puntos_luz) {
				this.puntos_luz = puntos_luz;
			}

		
}
