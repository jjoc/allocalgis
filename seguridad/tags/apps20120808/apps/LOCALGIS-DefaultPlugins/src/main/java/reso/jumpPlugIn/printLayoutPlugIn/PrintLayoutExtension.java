/*
 * Package reso.JumpPlugIn.PrintLayoutPlugIn pour JUMP
 *
 * Copyright (C) 2004
 * Olivier Bedel, ing�nieur informaticien laboratoire Reso UMR ESO 6590, Bassin versant du Jaudy-Guindy-Bizien.
 * C�line Foureau, stagiaire MIAGE, laboratoire Reso UMR ESO 6590.
 * Erwan Bocher, doctorant en g�ographie, laboratoire Reso UMR ESO 6590, Bassin versant du Jaudy-Guindy-Bizien
 *
 * Date de cr�ation : 31 ao�t 2004
 *
 * D�velopp� dans le cadre du Projet APARAD 
 *  (Laboratoire Reso UMR ESO 6590 CNRS / Bassin Versant du Jaudy-Guindy-Bizien)
 *    Responsable : Erwan BOCHER
 *    D�veloppeurs : C�line FOUREAU, Olivier BEDEL
 *
 * olivier.bedel@uhb.fr ou olivier.bedel@yahoo.fr
 * erwan.bocher@uhb.fr ou erwan.bocher@free.fr
 * celine.foureau@uhb.fr ou celine.foureau@wanadoo.fr
 * 
 * Ce package h�rite de la licence GPL de JUMP. Il est r�gi par la licence CeCILL soumise au droit fran�ais et
 * respectant les principes de diffusion des logiciels libres. (http://www.cecill.info)
 * 
 */

package reso.jumpPlugIn.printLayoutPlugIn;

import com.vividsolutions.jump.workbench.plugin.Extension;
import com.vividsolutions.jump.workbench.plugin.PlugInContext;

/**
 * @author FOUREAU_C
 */
public class PrintLayoutExtension extends Extension {
	public void configure(PlugInContext context) throws Exception {
		new PrintLayoutPlugIn().initialize(context);
	}

}