/**
 * FalseFilter.java
 * � MINETUR, Government of Spain
 * This program is part of LocalGIS
 * This program is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 2 of the License, or (at your option) any later version.
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
//$HeadURL: https://svn.wald.intevation.org/svn/deegree/base/trunk/src/org/deegree/model/filterencoding/FalseFilter.java $
/*----------------    FILE HEADER  ------------------------------------------

This file is part of deegree.
Copyright (C) 2001-2006 by:
EXSE, Department of Geography, University of Bonn
http://www.giub.uni-bonn.de/deegree/
lat/lon GmbH
http://www.lat-lon.de

This library is free software; you can redistribute it and/or
modify it under the terms of the GNU Lesser General Public
License as published by the Free Software Foundation; either
version 2.1 of the License, or (at your option) any later version.

This library is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
Lesser General Public License for more details.

You should have received a copy of the GNU Lesser General Public
License along with this library; if not, write to the Free Software
Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA

Contact:

Andreas Poth  
lat/lon GmbH
Aennchenstr. 19
53115 Bonn
Germany
E-Mail: poth@lat-lon.de

Prof. Dr. Klaus Greve
Department of Geography
University of Bonn
Meckenheimer Allee 166
53115 Bonn
Germany
E-Mail: greve@giub.uni-bonn.de

                 
 ---------------------------------------------------------------------------*/
package org.deegree.model.filterencoding;

import org.deegree.model.feature.Feature;

/**
 * Abstract superclass representing <Filter> elements (as defined in the Filter DTD). 
 * A <Filter> element either consists of (one or more) FeatureId-elements or
 * one operation-element. This is reflected in the two implementations FeatureFilter
 * and ComplexFilter.
 * <p>
 * @author Markus Schneider
 * @version $Revision: 1.1 $
 */
public class FalseFilter implements Filter {

	/**
	 * Calculates the <tt>Filter</tt>'s logical value (false).
	 * <p>
	 * @param feature (in this special case irrelevant)
	 * @return false (always)
	 */
	public boolean evaluate (Feature feature) {
		return false;    
	}

    /** Produces an indented XML representation of this object. */
    public StringBuffer toXML () {
		StringBuffer sb = new StringBuffer ();
		sb.append ("<ogc:Filter xmlns:ogc='http://www.opengis.net/ogc'>");
		sb.append ("<False/>");
		sb.append ("</ogc:Filter>\n");
		return sb;    	
    }
}
/* ********************************************************************
Changes to this class. What the people have been up to:
$Log: FalseFilter.java,v $
Revision 1.1  2011/09/19 13:47:32  satec
MODELO EIEL

Revision 1.3  2010/05/03 08:41:19  satec
*** empty log message ***

Revision 1.1  2009/03/31 15:54:49  roger
Creaci�n de m�dulo FIlter SLD que implementa los filtros OGC sobre Features SVG

Revision 1.5  2006/07/12 14:46:14  poth
comment footer added

********************************************************************** */
