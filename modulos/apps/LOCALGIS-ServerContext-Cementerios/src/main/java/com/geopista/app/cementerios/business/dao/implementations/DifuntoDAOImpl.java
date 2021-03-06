/**
 * DifuntoDAOImpl.java
 * � MINETUR, Government of Spain
 * This program is part of LocalGIS
 * This program is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 2 of the License, or (at your option) any later version.
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package com.geopista.app.cementerios.business.dao.implementations;

import java.sql.SQLException;
import java.util.List;

import com.geopista.app.cementerios.business.dao.DAO;
import com.geopista.app.cementerios.business.dao.interfaces.DifuntoDAO;
import com.geopista.app.cementerios.business.vo.Difunto;
import com.geopista.app.cementerios.business.vo.DifuntoExample;

public class DifuntoDAOImpl extends DAO implements DifuntoDAO {

    /**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table cementerio.difunto
	 * @ibatorgenerated  Tue Jul 26 14:01:38 CEST 2011
	 */
	public DifuntoDAOImpl() {
		super();
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table cementerio.difunto
	 * @throws SQLException 
	 * @ibatorgenerated  Tue Jul 26 14:01:38 CEST 2011
	 */
	public int deleteByPrimaryKey(Integer id) throws SQLException {
		Difunto key = new Difunto();
		key.setId(id);
		int rows = getSqlMapClientTemplate().delete(
				"cementerio_difunto.ibatorgenerated_deleteByPrimaryKey", key);
		return rows;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table cementerio.difunto
	 * @ibatorgenerated  Tue Jul 26 14:01:38 CEST 2011
	 */
	public Integer insert(Difunto record) throws SQLException {
		Object newKey = getSqlMapClientTemplate().insert(
				"cementerio_difunto.ibatorgenerated_insert", record);
		return (Integer) newKey;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table cementerio.difunto
	 * @ibatorgenerated  Tue Jul 26 14:01:38 CEST 2011
	 */
	public Integer insertSelective(Difunto record) throws SQLException {
		Object newKey = getSqlMapClientTemplate().insert(
				"cementerio_difunto.ibatorgenerated_insertSelective", record);
		return (Integer) newKey;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table cementerio.difunto
	 * @ibatorgenerated  Tue Jul 26 14:01:38 CEST 2011
	 */
	public List selectByExample(DifuntoExample example) throws SQLException {
		List list = getSqlMapClientTemplate().queryForList(
				"cementerio_difunto.ibatorgenerated_selectByExample", example);
		return list;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table cementerio.difunto
	 * @ibatorgenerated  Tue Jul 26 14:01:38 CEST 2011
	 */
	public Difunto selectByPrimaryKey(Integer id) throws SQLException {
		Difunto key = new Difunto();
		key.setId(id);
		Difunto record = (Difunto) getSqlMapClientTemplate().queryForObject(
				"cementerio_difunto.ibatorgenerated_selectByPrimaryKey", key);
		return record;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table cementerio.difunto
	 * @ibatorgenerated  Tue Jul 26 14:01:38 CEST 2011
	 */
	public int updateByPrimaryKeySelective(Difunto record) throws SQLException {
		int rows = getSqlMapClientTemplate()
				.update("cementerio_difunto.ibatorgenerated_updateByPrimaryKeySelective",
						record);
		return rows;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table cementerio.difunto
	 * @ibatorgenerated  Tue Jul 26 14:01:38 CEST 2011
	 */
	public int updateByPrimaryKey(Difunto record) throws SQLException {
		int rows = getSqlMapClientTemplate()
				.update("cementerio_difunto.ibatorgenerated_updateByPrimaryKey",
						record);
		return rows;
	}

	public int selectByLastSeqKey() throws SQLException {
		Integer lastKey = (Integer) getSqlMapClientTemplate()
				.queryForObject(
						"cementerio_difunto.selectByLastSeqKey",
						null);
		return lastKey.intValue();
	}
	
	public List selectAll () throws SQLException{
		List list = getSqlMapClientTemplate()
		.queryForList(
				"cementerio_difunto.selectAll",
				null);
		return list;
	}
    
}