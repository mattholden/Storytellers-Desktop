package org.slage.editor.gametree;

import java.awt.BorderLayout;
import java.awt.Polygon;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.tree.DefaultTreeModel;

import org.slage.Act;
import org.slage.Room;
import org.slage.Scene;
import org.slage.SlageGame;
import org.slage.SlageObject;
import org.slage.SlageObjectVisitor;
import org.slage.editor.Editor;

/**
 * A panel that contains a tree representation of the game that is being edited
 * in the editor.
 * 
 * @author Jared Cope
 */
public class GameTreePanel
		extends JPanel {

	/** the tree representation of the game */
	private GameTree tree;
	/** the editor that is used for editor wide actions */
	private Editor editor;

	/**
	 * Creates this panel with the editor that will be used for reacting to user
	 * actions. Initially no game tree is loaded and displayed on the panel.
	 * <p>
	 * A game can be created by calling <i>createNewGame()</i>, or by setting a
	 * previously created game with <i>setGame()</i>.
	 * 
	 * @param anEditor the object that will deal with user actions
	 */
	public GameTreePanel(Editor anEditor) {
		this.editor = anEditor;
		setLayout(new BorderLayout());

		tree = new GameTree(anEditor);
		tree.setModel(null);

		JScrollPane treeView = new JScrollPane(tree);
		add(treeView, BorderLayout.CENTER);
	}

	/**
	 * Will clear the current game tree displayed on this panel, and create a new
	 * blank game to use in the editor.
	 */
	public void createNewGame() {
		GameNode root = new GameNode("New game", tree, editor);
		tree.setModel(new DefaultTreeModel(root));
	}

	/**
	 * Will clear the current game tree displayed on this panel, and create a new
	 * blank act to use in the editor.
	 */
	public void createNewAct() {
		ActNode root = new ActNode("New act", 1, tree, editor);
		tree.setModel(new DefaultTreeModel(root));
	}

	/**
	 * Will clear the current game tree displayed on this panel, and create a new
	 * blank scene to use in the editor.
	 */
	public void createNewScene() {
		SceneNode root = new SceneNode("New scene", 1, tree, editor);
		tree.setModel(new DefaultTreeModel(root));
	}

	/**
	 * Will clear the current game tree displayed on this panel, and create a new
	 * blank room to use in the editor.
	 */
	public void createNewRoom() {
		RoomNode root = new RoomNode("New room", tree, editor);
		tree.setModel(new DefaultTreeModel(root));
	}

	/**
	 * Will clear the current game tree displayed on this panel, and create a new
	 * blank object to use in the editor.
	 */
	public void createNewObject() {
		ObjectNode root = new ObjectNode("New object", tree, editor);
		tree.setModel(new DefaultTreeModel(root));
	}

	/**
	 * Will clear the data in the tree used to represent the game.
	 */
	public void clearSession() {
		tree.setModel(null);
	}

    /**
     * TODO comment
     * @author Jared Cope
     */
	private class TreeNodeFactory implements SlageObjectVisitor {
		private AbstractTreeNode node = null;

		public TreeNodeFactory(SlageObject anObject) {
			anObject.accept(this);
		}

		public void accept(Act anAct) {
			node = new ActNode(anAct, tree, editor);
		}

		public void accept(Room aRoom) {
			node = new RoomNode(aRoom, tree, editor);
		}

		public void accept(Scene aScene) {
			node = new SceneNode(aScene, tree, editor);
		}

		public void accept(SlageGame aGame) {
			node = new GameNode(aGame, tree, editor);
		}

		public void accept(SlageObject anObject) {
			node = new ObjectNode(anObject, tree, editor);
		}

                
                public void accept(org.slage.framework.SlageFrameworkObject anObject) {
                    /** todo? Do we need this one to do anything? */
                }
                
                public void accept(org.slage.SlageCharacter aGame) {
                    /** todo: character nodes */
                }
                
                public void accept(org.slage.TextObject aTextObject) {
                    /** todo: text object nodes */
                }
                
                public void accept(org.slage.SlagePlayer aGame) {
                    /** todo: player nodes */
                }
                
		/**
		 * @return Returns the node.
		 */
		public AbstractTreeNode getNode() {
			return node;
		}

                
	}

	/**
	 * Will clear the current game tree displayed on this panel, and display the
	 * provided <i>object</i> as a tree instead.
	 * 
	 * @param object the object to display as a tree on this panel.
	 */
	public void setGame(SlageObject object) {
		TreeNodeFactory factory = new TreeNodeFactory(object);
		AbstractTreeNode root = factory.getNode();

		List objects = object.getContainedObjects();
		for (int i = 0; i < objects.size(); i++) {
			SlageObject obj = (SlageObject) objects.get(i);
			root.add(makeTreeNode(obj));
		}

		tree.setModel(new DefaultTreeModel(root));
	}

	/**
	 * Returns the game instance that is being edited, as depicted in the tree of
	 * this panel.
	 * 
	 * @return the game that is depicted in the tree of this panel
	 */
	public SlageObject getRootNode() {
		AbstractTreeNode rootNode = (AbstractTreeNode) tree.getModel().getRoot();
		return rootNode.getWrappedGameObject();
	}

	/**
	 * Will search through all the nodes in the displayed game tree, and if that
	 * node represents the provided <i>object</i>, then that node will be
	 * selected in the tree.
	 * 
	 * @param object the object to search for in the tree
	 */
	public void selectObjectInTree(SlageObject object) {
		tree.selectNodeWithObject(object);
	}

	/**
	 * Will create a new <b>SLAGEObject</b> using the provided <i>polygon</i> as
	 * a clickboundary, and attach it to the currently selected game node.
	 * 
	 * @param polygon the polygon to construct the click boundary with
	 */
	public void addObjectForCurrentNode(Polygon polygon) {
		tree.addObjectForCurrentNode(polygon);
	}

	/**
	 * Will create a node from the provided <i>object</i> that can be added to
	 * the tree. The node will have any children objects added to it as needed.
	 * 
	 * @param object the object to make a node from
	 * @return a node that can be added to the tree
	 */
	public AbstractTreeNode makeTreeNode(SlageObject object) {

		AbstractTreeNode newNode = null;

		if (object instanceof Act) {
			Act act = (Act) object;
			newNode = new ActNode(act, tree, editor);

			List objects = act.getContainedObjects();
			for (int i = 0; i < objects.size(); i++) {
				newNode.add(makeTreeNode((SlageObject) objects.get(i)));
			}
		} else if (object instanceof Scene) {
			Scene scene = (Scene) object;
			newNode = new SceneNode(scene, tree, editor);

			List objects = scene.getContainedObjects();
			for (int i = 0; i < objects.size(); i++) {
				newNode.add(makeTreeNode((SlageObject) objects.get(i)));
			}
		} else if (object instanceof Room) {
			Room room = (Room) object;
			newNode = new RoomNode(room, tree, editor);
			List objects = room.getContainedObjects();
			for (int i = 0; i < objects.size(); i++) {
				newNode.add(makeTreeNode((SlageObject) objects.get(i)));
			}
		} else {
			newNode = new ObjectNode(object, tree, editor);
			List objects = object.getContainedObjects();
			for (int i = 0; i < objects.size(); i++) {
				newNode.add(makeTreeNode((SlageObject) objects.get(i)));
			}
		}
		return newNode;
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
