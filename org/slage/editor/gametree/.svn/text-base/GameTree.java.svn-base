package org.slage.editor.gametree;

import java.awt.Polygon;

import javax.swing.JOptionPane;
import javax.swing.JTree;
import javax.swing.tree.TreeSelectionModel;

import org.slage.Boundary;
import org.slage.Room;
import org.slage.SlageObject;
import org.slage.editor.Editor;
import org.slage.editor.gametree.actions.GameTreeMouseListener;
import org.slage.editor.gametree.actions.GameTreeSelectionListener;

/**
 * A custom JTree that can display the various components that make up the game
 * in the editor. The tree displays the game as a hierarchy.
 * <p>
 * For example, a game can have a GameNode as the node node of the tree. As
 * children to that node, there may be several ActNodes. Each ActNode may
 * contain many SceneNodes.
 * 
 * @author Jared Cope
 */
public class GameTree
		extends JTree {

	/** used to react to actions generated from this tree */
	private Editor editor;
	/** used to help make up automatic names for new objects created */
	private int objectCounter = 1;

	/**
	 * Creates the game tree with the <i>editor</i> that will handle actions
	 * generated from this tree.
	 * 
	 * @param anEditor used to handle actions generated on the tree
	 */
	public GameTree(Editor anEditor) {
		super();
		this.editor = anEditor;
		getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
		addTreeSelectionListener(new GameTreeSelectionListener());
		addMouseListener(new GameTreeMouseListener(anEditor));
	}

	/**
	 * Using the currently selected node on the tree, this method will search
	 * through its children nodes looking for a node that wraps the provided
	 * <i>object</i>.
	 * <p>
	 * If the object is found, then the tree node that wraps it is selected, and
	 * the editor GUI is changed appropriately to allow editing of that object.
	 * 
	 * @param object the object to search for
	 */
	public void selectNodeWithObject(SlageObject object) {
		AbstractTreeNode obj = (AbstractTreeNode) getSelectionPath().getLastPathComponent();
		int leadRow = getLeadSelectionRow();
		for (int i = 0; i < obj.getChildCount(); i++) {
			AbstractTreeNode node = (AbstractTreeNode) obj.getChildAt(i);
			if (node instanceof ObjectNode) {
				ObjectNode on = (ObjectNode) node;
				SlageObject slageObject = on.getWrappedGameObject();
				if (slageObject == object) {
					setSelectionRow(leadRow + i + 1);
					editor.changeGUIForNode(on);
					break;
				}
			}
		}
	}

	/**
	 * Will create a new <b>SLAGEObject</b> using the provided <i>polygon</i> as
	 * a clickboundary, and attach it to the currently selected game node.
	 * <p>
	 * If the currently selected node does not represent a <i>Room</i>, then an
	 * error message is displayed for the author before returning.
	 * 
	 * @param polygon the polygon to construct the click boundary with
	 */
	public void addObjectForCurrentNode(Polygon polygon) {
		AbstractTreeNode node = (AbstractTreeNode) getSelectionPath().getLastPathComponent();
		if (node instanceof RoomNode) {
			RoomNode roomNode = (RoomNode) node;
			Room room = roomNode.getRoomObject();
			SlageObject newObject = new SlageObject("CB object " + objectCounter);
			objectCounter++;
			Boundary cb = new Boundary(polygon, 0);
			newObject.setClickBoundary(cb);
			room.addObject(newObject);
			roomNode.add(new ObjectNode(newObject, this, editor));
			this.updateUI();
		} else {
			JOptionPane.showMessageDialog(null, "Click boundaries can only be added to room nodes.\n\n");
		}
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
