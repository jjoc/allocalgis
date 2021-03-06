/*
 * El presente software ha sido desarrollado por Balidea Consulting & Programming, con control de calidad y previo dise�o por Enxenio S.L., para la Diputaci�n de A Coru�a en el seno del proyecto LOURED (http://loured.es), inclu�do en el Plan AVANZA Local 2009-2011 del Ministerio de Industria, Energ�a y Turismo del Gobierno de Espa�a.  
 * Su distribuci�n se realiza bajo las condiciones establecidas por la licencia European Public License (EUPL), versi�n 1.1 o posteriores (http://http://joinup.ec.europa.eu/software/page/eupl/licence-eupl)
 */
package es.dc.a21l.fuente.cu.impl;

import org.dozer.Mapper;

import es.dc.a21l.base.cu.impl.TransformadorEntidadBase2DtoBase;
import es.dc.a21l.elementoJerarquia.cu.IndicadorExpresionDto;
import es.dc.a21l.elementoJerarquia.cu.impl.IndicadorExpresion2IndicadorExpresionDtoTransformer;
import es.dc.a21l.elementoJerarquia.modelo.IndicadorExpresion;
import es.dc.a21l.fuente.cu.AtributoDto;
import es.dc.a21l.fuente.modelo.Atributo;

public class Atributo2AtributoDtoTransformer extends TransformadorEntidadBase2DtoBase<Atributo, AtributoDto>{
	public Atributo2AtributoDtoTransformer(Mapper mapper) {
		super(mapper);
	}
	
	@Override
	protected void aplicaPropiedadesEstendidas(Atributo origen,AtributoDto destino) {
		super.aplicaPropiedadesEstendidas(origen, destino);
		if ( origen.getIndicadorExpresion()!=null) {
			IndicadorExpresion indi = origen.getIndicadorExpresion();
			IndicadorExpresionDto indiDto = new IndicadorExpresionDto();
			indiDto.setExpresionLiteral(indi.getExpresionLiteral());
			indiDto.setExpresionTransformada(indi.getExpresionTransformada());
			indiDto.setId(indi.getId());
			if ( indi.getExpresion()!=null )
				indiDto.setIdExpresion(indi.getExpresion().getId());
			else
				indiDto.setIdExpresion(null);
			indiDto.setIdIndicador(indi.getIndicador().getId());
			destino.setIndicadorExpresion(indiDto);
		}
	}
}

