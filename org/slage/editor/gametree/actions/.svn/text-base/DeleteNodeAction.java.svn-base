package org.slage.editor.gametree.actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

import org.slage.SlageObject;
import org.slage.editor.Editor;
import org.slage.editor.gametree.AbstractTreeNode;

/**
 * An action that will delete the selected UI tree node from the Game tree, and
 * also remove the game object that it represents from the overall game.
 * 
 * @author Jared Cope
 */
public class DeleteNodeAction
		extends AbstractAction {

	/** the tree that will provide the selection information */
	private JTree tree;
	/** the editor that will be used for reacting to editor wide actions */
	private Editor editor;

	/**
	 * Constructs this action with the tree that will be the source of the user
	 * selection for deletion.
	 * 
	 * @param aTree the tree that is used to find the user selection
	 */
	public DeleteNodeAction(JTree aTree, Editor anEditor) {
		this.tree = aTree;
		this.editor = anEditor;
	}

	/**
	 * Will delete the selected UI tree node from the tree supplied in the
	 * constructor of this action. It will also remove the game object that the
	 * tree node represents in the overall game.
	 * <p>
	 * For example consider the following situation: the current game tree shows a
	 * <i>room</i> node with several <i>object</i> nodes beneath that and the
	 * selected node is a child node. This action will find the SLAGE object
	 * wrapped inside the <i>object</i> node, remove that from the SLAGE object
	 * wrapped in the <i>room</i> node and then finally remove the selected
	 * <i>object</i> node from the tree.
	 * 
	 * @param e the event that triggered this action
	 */
	public void actionPerformed(ActionEvent e) {

		TreePath selectedPath = tree.getSelectionPath();
		DefaultTreeModel tm = (DefaultTreeModel) tree.getModel();

		AbstractTreeNode doomedNode = (AbstractTreeNode) selectedPath.getLastPathComponent();
		if (doomedNode.getParent() == null) {
			editor.clearCurrentSession();
		} else {
			AbstractTreeNode parentNode = (AbstractTreeNode) doomedNode.getParent();
			SlageObject parentObject = parentNode.getWrappedGameObject();
			SlageObject doomedObject = doomedNode.getWrappedGameObject();

			parentObject.removeObject(doomedObject);
			tm.removeNodeFromParent(doomedNode);
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
