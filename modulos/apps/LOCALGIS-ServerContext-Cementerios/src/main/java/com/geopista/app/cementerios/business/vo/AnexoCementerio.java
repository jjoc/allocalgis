/**
 * AnexoCementerio.java
 * � MINETUR, Government of Spain
 * This program is part of LocalGIS
 * This program is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 2 of the License, or (at your option) any later version.
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package com.geopista.app.cementerios.business.vo;

public class AnexoCementerio extends AnexoCementerioKey {
    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column cementerio.anexo_cementerio.tipo
     *
     * @ibatorgenerated Mon Jul 11 14:45:21 CEST 2011
     */
    private String tipo;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column cementerio.anexo_cementerio.subtipo
     *
     * @ibatorgenerated Mon Jul 11 14:45:21 CEST 2011
     */
    private String subtipo;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column cementerio.anexo_cementerio.tipo
     *
     * @return the value of cementerio.anexo_cementerio.tipo
     *
     * @ibatorgenerated Mon Jul 11 14:45:21 CEST 2011
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column cementerio.anexo_cementerio.tipo
     *
     * @param tipo the value for cementerio.anexo_cementerio.tipo
     *
     * @ibatorgenerated Mon Jul 11 14:45:21 CEST 2011
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column cementerio.anexo_cementerio.subtipo
     *
     * @return the value of cementerio.anexo_cementerio.subtipo
     *
     * @ibatorgenerated Mon Jul 11 14:45:21 CEST 2011
     */
    public String getSubtipo() {
        return subtipo;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column cementerio.anexo_cementerio.subtipo
     *
     * @param subtipo the value for cementerio.anexo_cementerio.subtipo
     *
     * @ibatorgenerated Mon Jul 11 14:45:21 CEST 2011
     */
    public void setSubtipo(String subtipo) {
        this.subtipo = subtipo;
    }
}