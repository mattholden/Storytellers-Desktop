package org.slage.editor;

import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JList;

import org.slage.framework.Attribute;

/**
 * A custom JList that knows how to store and manage State objects. When a State
 * is added to the list, its <i>name</i> is used as the display string in the
 * list.
 * <p>
 * States should be added to the list with the <i>addStateToList()</i> method.
 * 
 * @author Jared Cope
 */
public class StateList
		extends JList {

	/** a list of all the states displayed */
	private List states = new ArrayList();

	/**
	 * Constructs an empty list.
	 */
	public StateList() {
		setModel(new DefaultListModel());
	}

	/**
	 * Will add a State object to the end of the list.
	 * 
	 * @param state the state to add to the list
	 */
	public void addStateToList(Attribute state) {
		states.add(state);
		((DefaultListModel) getModel()).addElement(state.getName());
	}

	/**
	 * Returns the state object found at index <i>i</i> within the list.
	 * 
	 * @param i the index to find the state object at
	 * @return the state object found at index <i>i</i>
	 */
	public Attribute getStateForIndex(int i) {
		return (Attribute) states.get(i);
	}
}

/*******************************************************************************
 * BEGIN LICENSE BLOCK **** Version: MPL 1.1/GPL 2.0/LGPL 2.1
 * 
 * The contents of this file are subject to the Mozilla Public License Version
 * 1.1 (the "License"); you may not use this file except in compliance with the
 * License. You may obtain a copy of the License at http://www.mozilla.org/MPL/
 * 
 * Software distributed under the License is distributed on an "AS IS" basis,
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License for
 * the specific language governing rights and limitations under the License.
 * 
 * The Original Code is Slage.
 * 
 * The Initial Developer of the Original Code is The SQ7.org project. Portions
 * created by the Initial Developer are Copyright (C) 2005 the Initial
 * Developer. All Rights Reserved.
 * 
 * Contributor(s): Matt Holden (Matt@sq7.org) Travis Savo (Travis@sq7.org)
 * Robert Wenner (Robert@sq7.org) Jared Cope (Jared@sq7.org) Colin Davis
 * (Colin@sq7.org)
 * 
 * Alternatively, the contents of this file may be used under the terms of
 * either the GNU General Public License Version 2 or later (the "GPL"), or the
 * GNU Lesser General Public License Version 2.1 or later (the "LGPL"), in which
 * case the provisions of the GPL or the LGPL are applicable instead of those
 * above. If you wish to allow use of your version of this file only under the
 * terms of either the GPL or the LGPL, and not to allow others to use your
 * version of this file under the terms of the MPL, indicate your decision by
 * deleting the provisions above and replace them with the notice and other
 * provisions required by the GPL or the LGPL. If you do not delete the
 * provisions above, a recipient may use your version of this file under the
 * terms of any one of the MPL, the GPL or the LGPL.
 * 
 ******************************************************************************/
