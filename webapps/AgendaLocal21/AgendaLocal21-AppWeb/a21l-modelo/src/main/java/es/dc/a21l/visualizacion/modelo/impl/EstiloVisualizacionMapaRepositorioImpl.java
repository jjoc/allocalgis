/*
 * El presente software ha sido desarrollado por Balidea Consulting & Programming, con control de calidad y previo dise�o por Enxenio S.L., para la Diputaci�n de A Coru�a en el seno del proyecto LOURED (http://loured.es), inclu�do en el Plan AVANZA Local 2009-2011 del Ministerio de Industria, Energ�a y Turismo del Gobierno de Espa�a.  
 * Su distribuci�n se realiza bajo las condiciones establecidas por la licencia European Public License (EUPL), versi�n 1.1 o posteriores (http://http://joinup.ec.europa.eu/software/page/eupl/licence-eupl)
 */
package es.dc.a21l.visualizacion.modelo.impl;

import es.dc.a21l.base.modelo.impl.RepositorioBaseImpl;
import es.dc.a21l.visualizacion.modelo.EstiloVisualizacionMapa;
import es.dc.a21l.visualizacion.modelo.EstiloVisualizacionMapaRepositorio;

@SuppressWarnings("unchecked")
public class EstiloVisualizacionMapaRepositorioImpl extends RepositorioBaseImpl<EstiloVisualizacionMapa> implements EstiloVisualizacionMapaRepositorio {
	
	private static final String SELECT_POR_ID_USUARIO_E_ID_INDICADOR="select e from EstiloVisualizacionMapa e where e.usuario.id = :idUsuario and e.indicador.id = :idIndicador";
	
	
	public EstiloVisualizacionMapa cargaPorIdUsuarioEIdIndicador(Long idUsuario, Long idIndicador){
		try {
			return (EstiloVisualizacionMapa)getEntityManager().createQuery(SELECT_POR_ID_USUARIO_E_ID_INDICADOR).setParameter("idUsuario", idUsuario).setParameter("idIndicador", idIndicador).getSingleResult();
		} catch (Exception ex) {
			return new EstiloVisualizacionMapa();
		}
	}
}