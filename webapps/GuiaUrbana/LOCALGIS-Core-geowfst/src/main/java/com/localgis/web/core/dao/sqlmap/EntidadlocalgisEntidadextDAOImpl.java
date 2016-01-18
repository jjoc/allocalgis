/**
 * EntidadlocalgisEntidadextDAOImpl.java
 * � MINETUR, Government of Spain
 * This program is part of LocalGIS
 * This program is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 2 of the License, or (at your option) any later version.
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package com.localgis.web.core.dao.sqlmap;

import com.ibatis.dao.client.DaoManager;
import com.ibatis.dao.client.template.SqlMapDaoTemplate;
import com.localgis.web.core.dao.EntidadlocalgisEntidadextDAO;
import com.localgis.web.core.model.EntidadlocalgisEntidadext;

public class EntidadlocalgisEntidadextDAOImpl extends SqlMapDaoTemplate implements EntidadlocalgisEntidadextDAO {

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table geowfst.entidadlocalgis_entidadext
	 * @ibatorgenerated  Thu Mar 31 17:21:03 CEST 2011
	 */
	public EntidadlocalgisEntidadextDAOImpl(DaoManager daoManager) {
		super(daoManager);
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table geowfst.entidadlocalgis_entidadext
	 * @ibatorgenerated  Thu Mar 31 17:21:03 CEST 2011
	 */
	public int deleteByPrimaryKey(String idEntidadext) {
		EntidadlocalgisEntidadext key = new EntidadlocalgisEntidadext();
		key.setIdEntidadext(idEntidadext);
		int rows = delete(
				"geowfst_entidadlocalgis_entidadext.ibatorgenerated_deleteByPrimaryKey",
				key);
		return rows;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table geowfst.entidadlocalgis_entidadext
	 * @ibatorgenerated  Thu Mar 31 17:21:03 CEST 2011
	 */
	public void insert(EntidadlocalgisEntidadext record) {
		insert("geowfst_entidadlocalgis_entidadext.ibatorgenerated_insert",
				record);
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table geowfst.entidadlocalgis_entidadext
	 * @ibatorgenerated  Thu Mar 31 17:21:03 CEST 2011
	 */
	public void insertSelective(EntidadlocalgisEntidadext record) {
		insert("geowfst_entidadlocalgis_entidadext.ibatorgenerated_insertSelective",
				record);
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table geowfst.entidadlocalgis_entidadext
	 * @ibatorgenerated  Thu Mar 31 17:21:03 CEST 2011
	 */
	public EntidadlocalgisEntidadext selectByPrimaryKey(String idEntidadext) {
		EntidadlocalgisEntidadext key = new EntidadlocalgisEntidadext();
		key.setIdEntidadext(idEntidadext);
		EntidadlocalgisEntidadext record = (EntidadlocalgisEntidadext) queryForObject(
				"geowfst_entidadlocalgis_entidadext.ibatorgenerated_selectByPrimaryKey",
				key);
		return record;
	}
	
	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table geowfst.entidadlocalgis_entidadext
	 * @ibatorgenerated  Thu Mar 31 17:21:03 CEST 2011
	 */
	public EntidadlocalgisEntidadext selectBySecondPrimaryKey(Integer idEntidad) {
		EntidadlocalgisEntidadext key = new EntidadlocalgisEntidadext();
		key.setIdEntidad(idEntidad);
		EntidadlocalgisEntidadext record = (EntidadlocalgisEntidadext) queryForObject(
				"geowfst_entidadlocalgis_entidadext.ibatorgenerated_selectBySecondPrimaryKey",
				key);
		return record;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table geowfst.entidadlocalgis_entidadext
	 * @ibatorgenerated  Thu Mar 31 17:21:03 CEST 2011
	 */
	public int updateByPrimaryKeySelective(EntidadlocalgisEntidadext record) {
		int rows = update(
				"geowfst_entidadlocalgis_entidadext.ibatorgenerated_updateByPrimaryKeySelective",
				record);
		return rows;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table geowfst.entidadlocalgis_entidadext
	 * @ibatorgenerated  Thu Mar 31 17:21:03 CEST 2011
	 */
	public int updateByPrimaryKey(EntidadlocalgisEntidadext record) {
		int rows = update(
				"geowfst_entidadlocalgis_entidadext.ibatorgenerated_updateByPrimaryKey",
				record);
		return rows;
	}
    
    
}