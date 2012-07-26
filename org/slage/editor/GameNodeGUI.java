package org.slage.editor;

import javax.swing.JTabbedPane;

import org.slage.SlageGame;
import org.slage.editor.gametree.GameNode;

/**
 * The GUI that is used to display the game object represented by a <i>game node</i>
 * in the game tree hierachy.
 * <p>
 * The GUI is a tabbed pane to allow editing of:
 * <ul>
 * <li>the game description</li>
 * <li>any states the game has</li>
 * <li>the toolbar that the game uses</li>
 * <li>the sounds in the game</li>
 * <li>any handlers registered on the act</li>
 * <li>key actions for the game</li>
 * <li>asynchronous key actions for the game</li>
 * <li>verbs and synonyms used in the game</li>
 * <li>any scheduled events in the game</li>
 * </ul>
 * 
 * @author Jared Cope
 * @see org.slage.SlageGame
 * @see org.slage.editor.gametree.GameNode
 */
public class GameNodeGUI
		extends JTabbedPane {

	/** the SLAGE game that this gui will allow editing of */
	private SlageGame game;

	/**
	 * Constructs this GUI with the tree node that contains the game object to
	 * display on the GUI.
	 * 
	 * @param gameNode the game object in this node will be editable on the GUI
	 */
	public GameNodeGUI(GameNode gameNode) {
		game = gameNode.getGameObject();

		addTab("Description", new DescriptionPanel(game));
		addTab("States", new StatesPanel(game));
		addTab("Toolbar", new ToolbarPanel());
		addTab("Sounds", new SoundsPanel());
		addTab("Handlers", new HandlersPanel());
		addTab("Key actions", new KeyActionsPanel());
		addTab("Asynchronous key actions", new AsyncKeyActionsPanel());
		addTab("Verb synonyms", new VerbSynonymsPanel());
		addTab("Scheduled events", new ScheduledEventsPanel());
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
