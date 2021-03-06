/**
 * DxfTEXT.java
 * � MINETUR, Government of Spain
 * This program is part of LocalGIS
 * This program is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 2 of the License, or (at your option) any later version.
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
/*
 *
 * Este codigo se distribuye bajo licencia GPL
 * de GNU. Para obtener una c�pia integra de esta
 * licencia acude a www.gnu.org.
 * 
 * Este software se distribuye "como es". AGIL
 * solo  pretende desarrollar herramientas para
 * la promoci�n del GIS Libre.
 * AGIL no se responsabiliza de las perdidas econ�micas o de 
 * informaci�n derivadas del uso de este software.
 */


package org.agil.dxf.reader;

import org.agil.dxf.math.Point3D;


/**
 *  Representing a DXF TEXT.
 *
 *  @version 1.00beta0
 */
public class DxfTEXT extends DxfEntity {
  // mirror flags [#71]
  static public final short MIRROR_X        = 2;
  static public final short MIRROR_Y        = 4;

  // horizontal adjustment flags [#72]
  static public final short H_LEFT          = 0;
  static public final short H_CENTER        = 1;
  static public final short H_RIGHT         = 2;
  static public final short H_ADJUST        = 3;
  static public final short H_MID           = 4;
  static public final short H_FITTED        = 5;

  // vertical adjustment flags [#73]
  static public final short V_BASE          = 0;
  static public final short V_BOTTOM        = 1;
  static public final short V_CENTER        = 2;
  static public final short V_TOP           = 3;


  protected Point3D      position = new Point3D();   // insert position [#10,20,30]
  protected double        height;                     // height [#40]
  protected String       text;                       // the text itself [#1]
  protected double        angle;                      // rotation angle [#50]
  protected double        aspect = 1.0f;              // aspect ratio [#41]
  protected double        slant;                      // slant angle [#51]
  protected String       style;                      // font/style [#7]
  protected short        mirror;                     // mirror flags [#71]
  protected short        hAdjust;                    // horizontal adjustment [#72]
  protected short        vAdjust;                    // vertical adjustment [#73]
  protected Point3D      adjust;                     // adjustment point [#11,21,31]


  /**
   *  Take a double value for a given group code.
   *  Accepted for group codes
   *  10, 20, 30,
   *  11, 21, 31,
   *  40, 41, 50 and 51
   *  and everything DxfEntity accepts.
   *  @param  grpNr  group code
   *  @paran  fval   the double value
   */
  public boolean setGroup(short grpNr, double fval) {
    switch (grpNr) {
    case 10:
    case 20:
    case 30:
      setCoord(position, grpNr/10, fval);
      return true;

    case 11:
    case 21:
    case 31:
      if (adjust == null) {
	adjust = new Point3D();
      }
      setCoord(adjust, grpNr/10, fval);
      return true;

    case 40:
      height = fval;
      return true;

    case 41:
      aspect = fval;
      return true;

    case 50:
      angle = (double)((fval/180.0)*Math.PI);
      return true;

    case 51:
      slant = (double)((fval/180.0)*Math.PI);
      return true;

    default:
      return super.setGroup(grpNr, fval);
    }
  }

  /**
   *  Take an int value for a given group code.
   *  Accepted for group codes
   *  71, 72 & 73
   *  and everything DxfEntity accepts
   *  @param  grpNr  group code
   *  @paran  fval   the integer value
   */
  public boolean setGroup(short grpNr, int ival) {
    switch (grpNr) {
    case 71:
      mirror = (short)ival;
      return true;

    case 72:
      hAdjust = (short)ival;
      return true;

    case 73:
      vAdjust = (short)ival;
      return true;

    default:
      return super.setGroup(grpNr, ival);
    }
  }

  /**
   *  Take a string for a given group code.
   *  Accepted for group codes
   *  1 and 7
   *  and everything DxfEntity accepts
   *  @param  grpNr  group code
   *  @paran  str    the string value
   */
  public boolean setGroup(short grpNr, String str) {
    switch (grpNr) {
    case 1:
      text = str;
      return true;

    case 7:
      style = str;
      return true;

    default:
      return super.setGroup(grpNr, str);
    }
  }


  /**
   *  Convert this TEXT using the given DxfConverter.
   *  @param  converter  the DXF converter
   *  @param  dxf        DXF file for references
   *  @param  collector  collector for results
   */
  public void convert(DxfConverter converter, DxfFile dxf, Object collector) {
    converter.convert(this, dxf, collector);
  }

  /**
   *  Get the text's position. Not cloned for effeciency reasons.
   *  @return text position
   */
  public final Point3D getPosition() {
    return position;
  }

  /**
   *  Get the text's height.
   *  @return text height
   */
  public final double getTextHeight() {
    return height;
  }

  /**
   *  Get the text.
   *  @return text
   */
  public final String getText() {
    return text;
  }

  /**
   *  Get the text's rotation angle.
   *  @return rotation angle
   */
  public final double getRotation() {
    return angle;
  }

  /**
   *  Get the text's aspect ratio.
   *  @return aspect ratio
   */
  public final double getAspectRatio() {
    return aspect;
  }

  /**
   *  Get the text's slant angle.
   *  @return slant angle
   */
  public final double getSlant() {
    return slant;
  }

  /**
   *  Get the text's style name
   */
  public final String getStyleName() {
    return style;
  }

  /**
   *  Get the text's mirror flags.
   *  @return mirror flags
   */
  public final short getMirror() {
    return mirror;
  }

  /**
   *  Get the text's horizontal adjustment.
   *  @return horizontal adjustment
   */
  public final short getHAdjust() {
    return hAdjust;
  }

  /**
   *  Get the text's vertical adjustment.
   *  @return vertical adjustment
   */
  public final short getVAdjust() {
    return vAdjust;
  }

  /**
   *  Get the text's adjustment point. Not cloned for effeciency reasons.
   *  @return adjustment point
   */
  public final Point3D getAdjust() {
    return adjust;
  }

}

