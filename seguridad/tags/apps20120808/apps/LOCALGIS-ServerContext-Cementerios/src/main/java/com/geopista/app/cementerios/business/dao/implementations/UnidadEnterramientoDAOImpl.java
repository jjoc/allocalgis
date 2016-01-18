package com.geopista.app.cementerios.business.dao.implementations;

import java.sql.SQLException;
import java.util.List;

import com.geopista.app.cementerios.business.dao.DAO;
import com.geopista.app.cementerios.business.dao.interfaces.UnidadEnterramientoDAO;
import com.geopista.app.cementerios.business.vo.UnidadEnterramiento;
import com.geopista.app.cementerios.business.vo.UnidadEnterramientoExample;

public class UnidadEnterramientoDAOImpl extends DAO  implements UnidadEnterramientoDAO {

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table cementerio.unidadenterramiento
	 * @ibatorgenerated  Thu Jun 02 17:40:09 CEST 2011
	 */
	public UnidadEnterramientoDAOImpl() {
		super();
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table cementerio.unidadenterramiento
	 * @ibatorgenerated  Thu Jun 02 17:40:09 CEST 2011
	 */
	public int deleteByPrimaryKey(Integer id) throws SQLException {
		UnidadEnterramiento key = new UnidadEnterramiento();
		key.setId(id);
		int rows = getSqlMapClientTemplate()
				.delete("cementerio_unidadenterramiento.ibatorgenerated_deleteByPrimaryKey",
						key);
		return rows;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table cementerio.unidadenterramiento
	 * @ibatorgenerated  Thu Jun 02 17:40:09 CEST 2011
	 */
	public Integer insert(UnidadEnterramiento record) throws SQLException {
		Object newKey = getSqlMapClientTemplate()
				.insert("cementerio_unidadenterramiento.ibatorgenerated_insert",
						record);
		return (Integer) newKey;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table cementerio.unidadenterramiento
	 * @ibatorgenerated  Thu Jun 02 17:40:09 CEST 2011
	 */
	public Integer insertSelective(UnidadEnterramiento record) throws SQLException {
		Object newKey = getSqlMapClientTemplate()
				.insert("cementerio_unidadenterramiento.ibatorgenerated_insertSelective",
						record);
		return (Integer) newKey;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table cementerio.unidadenterramiento
	 * @ibatorgenerated  Thu Jun 02 17:40:09 CEST 2011
	 */
	public List selectByExample(UnidadEnterramientoExample example) throws SQLException {
		List list = getSqlMapClientTemplate()
				.queryForList(
						"cementerio_unidadenterramiento.ibatorgenerated_selectByExample",
						example);
		return list;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table cementerio.unidadenterramiento
	 * @ibatorgenerated  Thu Jun 02 17:40:09 CEST 2011
	 */
	public UnidadEnterramiento selectByPrimaryKey(Integer id) throws SQLException{
		UnidadEnterramiento key = new UnidadEnterramiento();
		key.setId(id);
		UnidadEnterramiento record = (UnidadEnterramiento) getSqlMapClientTemplate()
				.queryForObject(
						"cementerio_unidadenterramiento.ibatorgenerated_selectByPrimaryKey",
						key);
		return record;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table cementerio.unidadenterramiento
	 * @ibatorgenerated  Thu Jun 02 17:40:09 CEST 2011
	 */
	public int updateByPrimaryKeySelective(UnidadEnterramiento record)throws SQLException {
		int rows = getSqlMapClientTemplate()
				.update("cementerio_unidadenterramiento.ibatorgenerated_updateByPrimaryKeySelective",
						record);
		return rows;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table cementerio.unidadenterramiento
	 * @ibatorgenerated  Thu Jun 02 17:40:09 CEST 2011
	 */
	public int updateByPrimaryKey(UnidadEnterramiento record)throws SQLException {
		int rows = getSqlMapClientTemplate()
				.update("cementerio_unidadenterramiento.ibatorgenerated_updateByPrimaryKey",
						record);
		return rows;
	}

	public int selectByLastSeqKey() throws SQLException {
		Integer lastKey = (Integer) getSqlMapClientTemplate()
				.queryForObject(
						"cementerio_unidadenterramiento.selectByLastSeqKey",
						null);
		return lastKey.intValue();
	}
	
	public List selectAll () throws SQLException{
		List list = getSqlMapClientTemplate()
		.queryForList(
				"cementerio_unidadenterramiento.selectAll",
				null);
		return list;
	}

	public List selectAllByCemetery (Integer idCementerio) throws SQLException{
		List list = getSqlMapClientTemplate()
		.queryForList(
				"cementerio_unidadenterramiento.selectAllByCemetery",
				idCementerio);
		return list;
	}

	
}