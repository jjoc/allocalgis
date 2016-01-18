package com.geopista.app.cementerios.business.dao.interfaces;

import java.sql.SQLException;
import java.util.List;

import com.geopista.app.cementerios.business.vo.HistoricoDifuntos;
import com.geopista.app.cementerios.business.vo.HistoricoDifuntosExample;

public interface HistoricoDifuntosDAO {
    /**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table cementerio.historico_difuntos
     * @throws SQLException 
	 * @ibatorgenerated  Wed Jun 29 17:04:16 CEST 2011
	 */
	int deleteByExample(HistoricoDifuntosExample example) throws SQLException;

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table cementerio.historico_difuntos
	 * @ibatorgenerated  Wed Jun 29 17:04:16 CEST 2011
	 */
	int deleteByPrimaryKey(Integer id) throws SQLException;

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table cementerio.historico_difuntos
	 * @ibatorgenerated  Wed Jun 29 17:04:16 CEST 2011
	 */
	Integer insert(HistoricoDifuntos record) throws SQLException;

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table cementerio.historico_difuntos
	 * @ibatorgenerated  Wed Jun 29 17:04:16 CEST 2011
	 */
	Integer insertSelective(HistoricoDifuntos record) throws SQLException;

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table cementerio.historico_difuntos
	 * @ibatorgenerated  Wed Jun 29 17:04:16 CEST 2011
	 */
	List selectByExample(HistoricoDifuntosExample example) throws SQLException;

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table cementerio.historico_difuntos
	 * @ibatorgenerated  Wed Jun 29 17:04:16 CEST 2011
	 */
	HistoricoDifuntos selectByPrimaryKey(Integer id) throws SQLException;

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table cementerio.historico_difuntos
	 * @ibatorgenerated  Wed Jun 29 17:04:16 CEST 2011
	 */
	int updateByExampleSelective(HistoricoDifuntos record,
			HistoricoDifuntosExample example) throws SQLException;

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table cementerio.historico_difuntos
	 * @ibatorgenerated  Wed Jun 29 17:04:16 CEST 2011
	 */
	int updateByExample(HistoricoDifuntos record,
			HistoricoDifuntosExample example) throws SQLException;

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table cementerio.historico_difuntos
	 * @ibatorgenerated  Wed Jun 29 17:04:16 CEST 2011
	 */
	int updateByPrimaryKeySelective(HistoricoDifuntos record) throws SQLException;

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table cementerio.historico_difuntos
	 * @ibatorgenerated  Wed Jun 29 17:04:16 CEST 2011
	 */
	int updateByPrimaryKey(HistoricoDifuntos record) throws SQLException;

	public List  selectByDifunto (Integer idDifunto) throws SQLException;
    
	int selectByLastSeqKey() throws SQLException;
}