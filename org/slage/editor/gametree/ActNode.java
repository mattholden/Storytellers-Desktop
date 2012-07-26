package org.slage.editor.gametree;

import javax.swing.JLabel;
import javax.swing.JPopupMenu;
import javax.swing.JSeparator;
import javax.swing.JTree;

import org.slage.Act;
import org.slage.SlageObject;
import org.slage.editor.Editor;
import org.slage.editor.gametree.menu.AddCharacterNodeMenuItem;
import org.slage.editor.gametree.menu.AddObjectNodeMenuItem;
import org.slage.editor.gametree.menu.AddRoomNodeMenuItem;
import org.slage.editor.gametree.menu.AddSceneMenuItem;
import org.slage.editor.gametree.menu.DeleteNodeMenuItem;

/**
 * A class that can be a node in the game tree of the editor. This class
 * represents an <b>Act</b> in the game.
 * 
 * @author Jared Cope
 */
public class ActNode
		extends AbstractTreeNode {

	/** the act object that this node represents */
	private Act act;

	/**
	 * Constructs this node with the <i>act</i> that it represents, and the
	 * <i>tree</i> that this node will be part of. The <i>editor</i> is used to
	 * handle any actions for this node.
	 * 
	 * @param anAct the act that this node represents
	 * @param aTree the tree that this node is part of
	 * @param editor used to react to actions generated on this node
	 */
	public ActNode(Act anAct, JTree aTree, Editor editor) {
		super(anAct.getName());
		this.act = anAct;
		this.tree = aTree;

		createPopupMenu(anAct.getName(), editor);
	}

	/**
	 * Constructs this node with a new Act object that it will represent.
	 * 
	 * @param name a name given to this node and the new Act object
	 * @param actNumber the act number for the new Act object
	 * @param aTree the tree that this node is part of
	 * @param editor used to react to actions generated on this node
	 */
	public ActNode(String name, int actNumber, JTree aTree, Editor editor) {
		super(name);
		this.tree = aTree;
		this.act = new Act(name, actNumber);

		createPopupMenu(name, editor);
	}

	/**
	 * Returns the Act object that this node represents.
	 * 
	 * @return the Act object that this node represents
	 * @see #getWrappedGameObject()
	 */
	public Act getActObject() {
		return act;
	}

	/**
	 * Returns the SLAGEObject that this node represents. Clients of this method
	 * may safely cast the returned object to an instance of <b>org.slage.Act</b>
	 * if that level of precision is desired.
	 * 
	 * @return the SLAGEObject that this node represents
	 * @see #getActObject()
	 */
	public SlageObject getWrappedGameObject() {
		return act;
	}

	/**
	 * Creates the popup menu for this node that will appear when the user
	 * right-clicks on this node.
	 * <p>
	 * This menu will contain options such as:
	 * <ul>
	 * <li>Adding a scene to the act</li>
	 * <li>Adding a room to the act</li>
	 * <li>Adding a character to the act</li>
	 * <li>Adding an object to the act</li>
	 * <li>Deleting this node from the tree</li>
	 * </ul>
	 * 
	 * @param actName a name that can be used to customize the menu
	 * @param editor used to react to actions generated on this menu
	 */
	public void createPopupMenu(String actName, Editor editor) {
		menu = new JPopupMenu();
		menu.add(new JLabel(" Menu for - " + actName));

		menu.add(new JSeparator());

		menu.add(new AddSceneMenuItem(tree, editor));
		menu.add(new AddRoomNodeMenuItem(tree, editor));
		menu.add(new AddCharacterNodeMenuItem(tree, editor));
		menu.add(new AddObjectNodeMenuItem(tree, editor));

		menu.add(new JSeparator());

		menu.add(new DeleteNodeMenuItem(tree, editor));
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
