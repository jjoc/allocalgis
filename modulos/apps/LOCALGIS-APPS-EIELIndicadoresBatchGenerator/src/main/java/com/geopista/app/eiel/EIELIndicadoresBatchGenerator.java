/**
 * EIELIndicadoresBatchGenerator.java
 * � MINETUR, Government of Spain
 * This program is part of LocalGIS
 * This program is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 2 of the License, or (at your option) any later version.
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package com.geopista.app.eiel;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.InvalidPropertiesFormatException;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

import com.geopista.server.database.CPoolDatabase;

public class EIELIndicadoresBatchGenerator {

	private static Logger logger = Logger
			.getLogger(EIELIndicadoresBatchGenerator.class);

	private static Connection conn = null;

	private static int typeOfConnection = 0;

	private static Properties properties = new Properties();

	/**
	 * Borra los datos previos de indicadores (nuevo modelo)
	 */
	public static void initIndicadoresCleanData(String idMunicipio,
			Connection conn) throws Exception {

		logger.info("EIEL_Indicadores. Eliminando datos previos eiel_indicadores_d_*");

		String[] sqlKey = { "eiel_indicadores_i_cleandata" };
		for (String s : sqlKey) {
			logger.debug("key=" + s);
			initIndicadoresExecuteQuery(s, idMunicipio, conn);
		}

	}

	public static void initIndicadoresLoadPoblacionViviendaPlaneamiento(
			String idMunicipio, Connection conn) throws Exception {

		String[] sqlKey = { "eiel_indicadores_i_poblacion_mun",
				"eiel_indicadores_i_poblacion_nuc",
				"eiel_indicadores_i_vivienda_mun",
				"eiel_indicadores_i_vivienda_nuc",
				"eiel_indicadores_i_planeamiento_mun",
				"eiel_indicadores_i_planeamiento_nuc" };
		for (String s : sqlKey) {
			logger.debug("key=" + s);
			initIndicadoresExecuteQuery(s, idMunicipio, conn);
		}
	}

	public static void initIndicadoresLoadCicloAgua(String idMunicipio,
			Connection conn) throws Exception {

		String[] sqlKey = { "eiel_indicadores_ii_captaciones_pre",
				"eiel_indicadores_ii_captaciones_mun",
				"eiel_indicadores_ii_captaciones_nuc",

				"eiel_indicadores_ii_depositos_mun",
				"eiel_indicadores_ii_depositos_nuc",

				"eiel_indicadores_ii_potabilizadoras_mun",
				"eiel_indicadores_ii_potabilizadoras_nuc",

				"eiel_indicadores_ii_rdistribucion_pre",
				"eiel_indicadores_ii_rdistribucion_mun",
				"eiel_indicadores_ii_rdistribucion_nuc",

				"eiel_indicadores_ii_rsaneamiento_pre",
				"eiel_indicadores_ii_rsaneamiento_mun",
				"eiel_indicadores_ii_rsaneamiento_nuc",

				"eiel_indicadores_ii_vertidos_mun",
				"eiel_indicadores_ii_vertidos_nuc"

		};
		for (String s : sqlKey) {
			logger.debug("key=" + s);
			initIndicadoresExecuteQuery(s, idMunicipio, conn);
		}
	}

	public static void initIndicadoresLoadInfraestructuras(String idMunicipio,
			Connection conn) throws Exception {

		String[] sqlKey = {
				"eiel_indicadores_iii_accesibilidad_mun",
				"eiel_indicadores_iii_accesibilidad_nuc",
				"eiel_indicadores_iii_pavimentacion_mun",
				"eiel_indicadores_iii_pavimentacion_nuc",
				// "eiel_indicadores_iii_alumbrado_mun",
				"eiel_indicadores_iii_alumbrado_nuc",

				"eiel_indicadores_iii_comunicaciones_pre",
				"eiel_indicadores_iii_comunicaciones_mun",
				"eiel_indicadores_iii_comunicaciones_nuc",
				"eiel_indicadores_iii_suministros_mun",
				"eiel_indicadores_iii_suministros_nuc" };
		for (String s : sqlKey) {
			logger.debug("key=" + s);
			initIndicadoresExecuteQuery(s, idMunicipio, conn);
		}
	}

	public static void initIndicadoresLoadResiduosUrbanos(String idMunicipio,
			Connection conn) throws Exception {

		String[] sqlKey = { "eiel_indicadores_iv_rblimpieza_pre",
				"eiel_indicadores_iv_rblimpieza_mun",
				"eiel_indicadores_iv_rblimpieza_nuc",

				"eiel_indicadores_iv_tratamresiduos_mun",
				"eiel_indicadores_iv_tratamresiduos_nuc" };
		for (String s : sqlKey) {
			logger.debug("key=" + s);
			initIndicadoresExecuteQuery(s, idMunicipio, conn);
		}
	}

	public static void initIndicadoresLoadEducacionCultura(String idMunicipio,
			Connection conn) throws Exception {

		String[] sqlKey = { "eiel_indicadores_v_centrosen_mun",
				"eiel_indicadores_v_centrosen_nuc",
				"eiel_indicadores_v_ideporte_mun",
				"eiel_indicadores_v_ideporte_nuc",
				"eiel_indicadores_v_ccultura_mun",
				"eiel_indicadores_v_ccultura_nuc",
				"eiel_indicadores_v_zverde_mun",
				"eiel_indicadores_v_zverde_nuc" };
		for (String s : sqlKey) {
			logger.debug("key=" + s);
			initIndicadoresExecuteQuery(s, idMunicipio, conn);
		}
	}

	public static void initIndicadoresLoadSanitarioAsistencial(
			String idMunicipio, Connection conn) throws Exception {

		String[] sqlKey = { "eiel_indicadores_vi_csanitario_mun",
				"eiel_indicadores_vi_csanitario_nuc",
				"eiel_indicadores_vi_casistencial_mun",
				"eiel_indicadores_vi_casistencial_nuc" };
		for (String s : sqlKey) {
			logger.debug("key=" + s);
			initIndicadoresExecuteQuery(s, idMunicipio, conn);
		}
	}

	public static void initIndicadoresLoadOtros(String idMunicipio,
			Connection conn) throws Exception {

		String[] sqlKey = { "eiel_indicadores_vii_otroserv_mun",
				"eiel_indicadores_vii_otroserv_nuc" };
		for (String s : sqlKey) {
			logger.debug("key=" + s);
			initIndicadoresExecuteQuery(s, idMunicipio, conn);
		}
	}

	/**
	 * Retorna todos las id de Municipio en geopista
	 * 
	 * @param conn
	 * @return Lista con los id de las entidades en geopista
	 */
	public static List<String> getGeopistaMunicipios(Connection conn) {
		List<String> entidades = new ArrayList<String>();
		try {
			// Primero obtenemos la lista de entidades supramunique tienen alg�n
			// mapa publicado
			Statement stmt = conn.createStatement(ResultSet.TYPE_FORWARD_ONLY,
					ResultSet.CONCUR_READ_ONLY);
			//ResultSet rs = stmt.executeQuery("SELECT id FROM municipios WHERE id_provincia=33 ORDER BY 1");
			ResultSet rs = stmt.executeQuery("select distinct(id_municipio) from entidades_municipios ORDER BY 1;");
			while (rs.next()) {
				String id = String.valueOf(rs.getInt("id_municipio"));
				entidades.add(id);
				// log.debug(id);
			}
		} catch (SQLException sqle) {
			logger.error("Error recuperando municipios. " + sqle.getMessage());
		}
		return entidades;
	}

	public static boolean safeClose(ResultSet rs, Statement statement,
			Connection connection) {

		// try {
		// connection.commit();
		// } catch (Exception ex2) {
		// }
		try {
			if (rs != null)
				rs.close();
		} catch (Exception ex2) {
		}
		try {
			if (statement != null)
				statement.close();
		} catch (Exception ex2) {
		}
		try {
			connection.close();

		} catch (Exception ex2) {
		}

		return true;
	}

	/**
	 * Ejecuta una consulta del fichero de propiedades XML eiel_indicdadores_sql
	 * para eliminaci�n de registros o carga de datos en las tablas de
	 * indicadores
	 * 
	 * @param key
	 *            Clave de la consulta
	 * @param idMunicipio
	 *            C�digo de municipio (5d�g PPMMM) para parametrizar la consulta
	 */
	private static void initIndicadoresExecuteQuery(String key,
			String idMunicipio, Connection conn) {
		// java.io.FileInputStream propFile=new java.io.FileInputStream
		// ("eiel_indicadores_sql.xml");+
		// Properties properties = new Properties();

		PreparedStatement ps = null;

		try {

			String sSQLUpdate = "";

			// properties.loadFromXML(propFile);

			sSQLUpdate = properties.getProperty(key);

			if (sSQLUpdate != null) {

				logger.info("EIEL_Indicadores. Cargando datos " + key);

				// conn = AppContext.getJDBCConnection();

				/*
				 * try{ conn = CPoolDatabase.getSimpleConnection(); } catch
				 * (Exception e){} if (conn==null){ conn =
				 * AppContext.getJDBCConnection(); typeOfConnection=1; }
				 */
				conn.setAutoCommit(false);

				// Reemplazo manual del par�metro en lugar de setParameter para
				// permitir
				// cambiar el id_municipio en m�ltiples lugares
				sSQLUpdate = sSQLUpdate.replaceAll("\\?M", idMunicipio);
				sSQLUpdate = sSQLUpdate.replaceAll("\\?p",idMunicipio.substring(0, 2));
				sSQLUpdate = sSQLUpdate.replaceAll("\\?m",idMunicipio.substring(2, 5));

				
				ps = conn.prepareStatement(sSQLUpdate);
				// ps.execute();
				logger.info("EIEL_Indicadores. Query a ejecutar " + ((org.postgresql.PGStatement)ps).toString());

				int nrecords = ps.executeUpdate();
				logger.info(nrecords + " registros");

				// conn.commit();
				// ps.close();
				// conn.close();
				conn.commit();
			} else {
				logger.error("No se encuentra la clave " + key
						+ " en eiel_indicadores_sql.xml");
			}

		} catch (SQLException e) {
			logger.error("Error cargando datos indicadores. Excepci�n "
					+ e.getMessage());
			e.printStackTrace();

		} finally {

			try {
				safeClose(null, ps, null);
				// if (typeOfConnection==1)

				/*
				 * else{ conn.close(); if (typeOfConnection==0)
				 * CPoolDatabase.releaseConexion(); }
				 */
			} catch (Exception ex2) {
			}
		}

	}

	/**
	 * Constructor de la clase
	 * 
	 * @param args
	 */
	public EIELIndicadoresBatchGenerator(String[] args) {
		try {
			AppContext.init();

			InputStream in = EIELIndicadoresBatchGenerator.class
					.getClassLoader().getResourceAsStream(
							"eiel_indicadores_sql.xml");

			properties.loadFromXML(in);
			logger.info("Le�do fichero de propiedades eiel_indicadores_sql.xml");

			// Recuperamos la conexion del Pool del servidor, en caso contrario
			// de fichero de configuracion.
			try {
				conn = CPoolDatabase.getSimpleConnection();
			} catch (Exception e) {
			}
			if (conn == null) {
				conn = AppContext.getJDBCConnection();
				typeOfConnection = 1;
			}

			conn.setAutoCommit(false);

			List<String> municipios = getGeopistaMunicipios(conn);
			// safeClose(null, null, conn);
			long tiempoInicioTotal = System.currentTimeMillis();
			
			for (String m : municipios) {

				long tiempoInicioMunicipio = System.currentTimeMillis();
				logger.info("Procesando indicadores Municipio " + m);

				initIndicadoresCleanData(m, conn);

				logger.info("Procesando indicadores Poblacion. Municipio:" + m);
				initIndicadoresLoadPoblacionViviendaPlaneamiento(m, conn);

				logger.info("Procesando indicadores Ciclo de aguas Municipio:"+ m);
				initIndicadoresLoadCicloAgua(m, conn);

				logger.info("Procesando indicadores Infraestructuras Municipio:"+ m);
				initIndicadoresLoadInfraestructuras(m, conn);
				
				logger.info("Procesando indicadores Residuos Urbanos Municipio:"+ m);
				initIndicadoresLoadResiduosUrbanos(m, conn);

				logger.info("Procesando indicadores Educacion Municipio:"+ m);
				initIndicadoresLoadEducacionCultura(m, conn);

				logger.info("Procesando indicadores Sanitarios Municipio:"+ m);
				initIndicadoresLoadSanitarioAsistencial(m, conn);

				logger.info("Procesando otros indicadores. Municipio:"+ m);
				initIndicadoresLoadOtros(m, conn);

				long totalTiempoMunicipio = System.currentTimeMillis() - tiempoInicioMunicipio;
				logger.info("Tiempo Proceso Indicadores para municipio:"+ m+" ->"+totalTiempoMunicipio+" ms");
			}

			long totalTiempoTotal= System.currentTimeMillis() - tiempoInicioTotal;
			logger.info("Tiempo Proceso Indicadores para todos los municipios ->"+totalTiempoTotal+" ms");

			conn.commit();
			// conn.close();
			logger.info("FINALIZADO proceso actualizaci�n indicadores");
		} catch (FileNotFoundException ex) {
			logger.error("No se encontr� fichero de propiedades "
					+ ex.getMessage());
			ex.printStackTrace();

		} catch (InvalidPropertiesFormatException e) {
			logger.error("No se encontraron propiedades " + e.getMessage());
			e.printStackTrace();

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				conn.close();
				if (typeOfConnection == 0)
					CPoolDatabase.releaseConexion();
			} catch (Exception ex2) {
			}
		}
	}

	public static void main(String[] args) {

		new EIELIndicadoresBatchGenerator(args);

	}

}
