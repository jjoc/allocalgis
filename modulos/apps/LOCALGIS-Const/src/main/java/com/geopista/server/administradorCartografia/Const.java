/**
 * Const.java
 * � MINETUR, Government of Spain
 * This program is part of LocalGIS
 * This program is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 2 of the License, or (at your option) any later version.
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package com.geopista.server.administradorCartografia;

public class Const {
	
	
	public static final int	LOCK_LAYER_ERROR			= -1;

	public static final int	LOCK_LAYER_LOCKED			= 0;

	public static final int	LOCK_LAYER_OWN				= 1;

	public static final int	LOCK_LAYER_OTHER			= 2;

	public static final int	UNLOCK_LAYER_ERROR			= -1;

	public static final int	UNLOCK_LAYER_UNLOCKED		= 0;

	public static final int	UNLOCK_LAYER_NOTLOCKED		= 3;

	public static final int	LOCK_FEATURE_ERROR			= -1;

	public static final int	LOCK_FEATURE_LOCKED			= 0;

	public static final int	LOCK_FEATURE_OWN			= 4;

	public static final int	LOCK_FEATURE_OTHER			= 5;

	public static final int	UNLOCK_FEATURE_ERROR		= -1;

	public static final int	UNLOCK_FEATURE_UNLOCKED		= 0;

	public static final int	UNLOCK_FEATURE_NOTLOCKED	= 6;

	public static final int	MAX_NUM_FEATURE_UPLOAD		= 100;
	
	
    public static final int ACTION_MAPS=0;
    public static final int ACTION_MAP=1;
    public static final int ACTION_LAYER=2;
    public static final int ACTION_FEATURE=3;
    public static final int ACTION_LAYER_LOCK=4;
    public static final int ACTION_LAYER_UNLOCK=5;
    public static final int ACTION_FEATURE_LOCK=6;
    public static final int ACTION_FEATURE_UNLOCK=7;
    public static final int ACTION_FEATURE_UPLOAD=8;
    public static final int ACTION_STYLE_UPLOAD=9;
    public static final int ACTION_MODIFIED_FEATURES=10;
    public static final int ACTION_LAYERFAMILY_IDS=11;
    public static final int ACTION_LAYERFAMILY_IDS_BYMAP=12;
    public static final int ACTION_LAYER_IDS_BYLAYERFAMILY=13;
    public static final int ACTION_LAYER_IDS_BYMAP=14;
    public static final int ACTION_MAP_UPLOAD=15;
    public static final int ACTION_MAP_DELETE=16;
    public static final int ACTION_LAYERFAMILIES=17;
    public static final int ACTION_MAP_UPLOAD_MUNI=18;
    public static final int ACTION_GEOREFADD=19;
    public static final int ACTION_GETUSERS=20;
    public static final int ACTION_GETUSERSPERMLAYERS = 21;
    public static final int ACTION_CREATEEXTRACTPROJECT = 22;
    public static final int ACTION_GETEXTRACTPROJECTS = 23;
	public static final int ACTION_SET_ASSIGNCELLSEXTRACTPROJECT = 24;
	public static final int ACTION_GET_ASSIGNCELLSEXTRACTPROJECT = 25;
	public static final int ACTION_SRID_DEFECTO = 26;
	public static final int ACTION_SRID_INICIAL = 28;
	public static final int ACTION_SEARCH_ATTRIBUTE = 27;
	public static final int ACTION_MUNICIPIO_GEOMETRY = 29;
    public static final int ACTION_DELETEEXTRACTPROJECT = 30;
    public static final int ACTION_EXPORT_SHP_MAPAS = 31;
    public static final int ACTION_EXPORT_SHP_LAYERS_MAPAS = 32;
    public static final int ACTION_LAYER_EXPORT_SHP = 33;
    
    public static final int ACTION_WMS_TEMPLATE_LIST=34;
    public static final int ACTION_WMS_TEMPLATE=35;
    public static final int ACTION_EDIT_WMS_TEMPLATE=36;
    public static final int ACTION_WMS_DELETE_WMS_TEMPLATE=37;
    public static final int ACTION_WMS_TEMPLATE_MAPS=38;
    public static final int ACTION_DELETE_WMS_TEMPLATE=39;
    public static final int ACTION_UPDATE_WMS_TEMPLATE=40;
    public static final int ACTION_ADD_WMS_TEMPLATE=41;
    
    public static final String KEY_GEOMETRY="geometry";
    public static final String KEY_FILTER="filter";
    public static final String KEY_LAYER="layer";
    public static final String KEY_LAYERFAMILY="layerfamily";
    public static final String KEY_LOCALE="locale";
    public static final String KEY_MAP="map";
    public static final String KEY_FEATURE="feature";
    public static final String KEY_UPLOAD="upload";
    public static final String KEY_STYLE="style";
    public static final String KEY_DATE="date";
    public static final String KEY_POSITION="position";
    public static final String KEY_MAP_UPLOAD="map_upload";
    public static final String KEY_LOAD_DATA="load_data";
    public static final String KEY_VALIDATE_DATA="validate_data";
    public static final String KEY_TIPO_VIA="tipo_via";
    public static final String KEY_VIA="via";
    public static final String KEY_NUM_POLI="num_poli";
    public static final String KEY_ID_MUNI="ENV_VAR:geopista.DefaultCityId";
    public static final String KEY_MUNICIPALITIES="listaMunicipios";
    public static final String KEY_MUNICIPIO="idMunicipio";
    public static final String KEY_ID_ENTIDAD="idEntidad";
    public static final String KEY_SRID = "srid";
    public static final String KEY_SRID_MAPA = "srid_mapa";
	public static final String KEY_ARRAY_CAPAS="key_array_capas";
	public static final Object KEY_EXTRACT_PROJECT ="key_extract_project";
	public static final Object KEY_CELLS_USER = "key_cell_user";
	public static final Object KEY_ID_PROJECT = "key_id_project";
	public static final Object KEY_ATTRIBUTE_NAME = "key_attribute_name";
	public static final Object KEY_ATTRIBUTE_VALUE = "key_attribute_value";
	public static final Object KEY_VERSION = "version";
	public static final Object KEY_MUNICIPIO_GEOMETRY = "key_municipio_geometry";
	public static final Object KEY_IMPORTACIONES = "importaciones";
	public static final Object KEY_EXPORT_SHP_IDENTIDAD = "key_shp_identidad";
	public static final Object KEY_EXPORT_SHP_MAP_BEAN = "key_shp_map";
    public static final String PERM_LAYER_READ="Geopista.Layer.Leer";
    public static final String PERM_LAYER_WRITE="Geopista.Layer.Escribir";
    public static final String PERM_LAYER_ADD="Geopista.Layer.A�adir";
    public static final String PERM_MAP_CREATE="Geopista.Map.Create";
    public static final String PERM_MAP_EDIT="Geopista.Map.Edit";
    public static final String PERM_MAP_DELETE="Geopista.Map.Delete";
    public static final String PERM_WRITE_SLD="Geopista.Layer.ModificarSLD";
    
    public static final String KEY_WMS_TEMPLATE="wms_template";
    public static final String KEY_WMS_TEMPLATE_COMPLETE_EDIT="plantillaWmsCompletaEdit";
    public static final String KEY_WMS_TEMPLATE_COMPLETE_ADD="plantillaWmsCompletaAdd";
   
    
    public static final String PERM_MAP_BORRAR_MAPAS_GLOBALES="Geopista.Map.BorrarMapasGlobales";
            
    public static final String DEFAULT_SRID = "default_srid";
    public static final String INITIAL_SRID = "initial_srid";
    public static final String REVISION_ACTUAL ="revision_actual";
    public static final String REVISION_EXPIRADA ="revision_expirada";
    
    public static final int SRID_POR_DEFECTO=4258;

    public static final int HISTORY_ACTION_INSERT=1;
    public static final int HISTORY_ACTION_UPDATE=2;
    public static final int HISTORY_ACTION_DELETE=3;

    public static final int ACL_MAP=50;
    public static final int ACL_GENERAL=12;    
    
	public static final String ESTADO_VALIDACION = "estadovalidacion";
	
    public static final int ESTADO_VALIDO=0;
	public static final int ESTADO_TEMPORAL=1;	
	public static final int ESTADO_PUBLICABLE=2;
	public static final int ESTADO_PUBLICABLE_MOVILIDAD=3;
	public static final int ESTADO_BORRABLE=5;
	public static final int ESTADO_A_BORRAR=6;	
	//Para indicar que se desea publicar un elemento
	public static final int ESTADO_A_PUBLICAR=4;
	
	public static String REVISION_VALIDA="9999999999";
	public static String REVISION_TEMPORAL="9899999999";
	public static String REVISION_PUBLICABLE="9799999999";
	public static String REVISION_PUBLICABLE_PDA="9699999999";
	public static String REVISION_BORRABLE="9599999999";
	

	public static final String PERM_PUBLICADOR_EIEL = "LocalGis.EIEL.Publicador";
	public static final String PERM_VALIDADOR_EIEL = "LocalGis.EIEL.Validador";
	
	public static final String KEY_ESTADO_VALIDACION="estadovalidacion";
	public static final String KEY_LOAD_FEATURE_LAYER = "loadFeatureLayer";
	
	public static final String KEY_USE_SAME_SRID = "useSameSRID";
	
	
	
	
}
