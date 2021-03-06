package org.slage.editor;

import javax.swing.JTabbedPane;

import org.slage.Act;
import org.slage.editor.gametree.ActNode;

/**
 * The GUI that is used to display the act object represented by an <i>Act node</i>
 * in the game tree hierachy.
 * <p>
 * The GUI is a tabbed pane to allow editing of:
 * <ul>
 * <li>the act description</li>
 * <li>any states the act has</li>
 * <li>any handlers registered on the act</li>
 * </ul>
 * 
 * @author Jared Cope
 * @see org.slage.Act
 * @see org.slage.editor.gametree.ActNode
 */
public class ActNodeGUI
		extends JTabbedPane {

	/** the SLAGE act that this gui will allow editing of */
	private Act act;

	/**
	 * Constructs this GUI with the tree node that contains the act object to
	 * display on the GUI.
	 * 
	 * @param actNode the act object in this node will be editable on the GUI
	 */
	public ActNodeGUI(ActNode actNode) {
		act = actNode.getActObject();

		addTab("Description", new DescriptionPanel(act));
		addTab("States", new StatesPanel(act));
		addTab("Handlers", new HandlersPanel());
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
