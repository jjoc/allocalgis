/**
 * ChangeOperatorTypeAction.java
 * � MINETUR, Government of Spain
 * This program is part of LocalGIS
 * This program is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 2 of the License, or (at your option) any later version.
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
/*
 * Created on 22-sep-2004
 */
package com.geopista.style.filtereditor.ui.impl.actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

/**
 * @author Enxenio, SL
 */
public class ChangeOperatorTypeAction extends AbstractAction {


	public ChangeOperatorTypeAction(String name, int targetOperatorType) {
		super(name);
		_targetOperatorType = targetOperatorType;
	}

	public void actionPerformed(ActionEvent actionEvent) {
	}
	
	public int getTargetOperatorType() {
		return _targetOperatorType;
	}
	
	private int _targetOperatorType;

}
