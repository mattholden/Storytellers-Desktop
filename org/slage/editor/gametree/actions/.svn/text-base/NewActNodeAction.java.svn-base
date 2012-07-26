package org.slage.editor.gametree.actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

import org.slage.editor.Editor;
import org.slage.editor.gametree.ActNode;

/**
 * An action that will add a new ActNode to a JTree that represents the game in
 * the editor. The ActNode will be added as a child to the node that is
 * currently selected on the tree that is provided to the constructor of the
 * action.
 * 
 * @author Jared Cope
 * @see org.slage.editor.gametree.ActNode
 */
public class NewActNodeAction
		extends AbstractAction {

	/** the tree that will be modified by this action */
	private JTree tree;
	/** the editor that is used to react to editor wide actions */
	private Editor editor;

	/**
	 * Constructs this action with the <i>tree</i> that will have an ActNode
	 * added to it.
	 * 
	 * @param aTree the tree that will have an ActNode added to it
	 * @param anEditor the editor that will react to user actions
	 */
	public NewActNodeAction(JTree aTree, Editor anEditor) {
		this.tree = aTree;
		this.editor = anEditor;
	}

	/**
	 * Will add a new ActNode to the tree that represents the game in the editor.
	 * 
	 * @param e the event that triggered this action
	 */
	public void actionPerformed(ActionEvent e) {

		TreePath parentPath = tree.getSelectionPath();

		DefaultMutableTreeNode parentNode = (DefaultMutableTreeNode) parentPath.getLastPathComponent();

		DefaultTreeModel tm = (DefaultTreeModel) tree.getModel();
		tm.insertNodeInto(new ActNode("Act " + parentNode.getChildCount(), parentNode.getChildCount(), tree, editor), parentNode, parentNode.getChildCount());
		tree.expandPath(parentPath);
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
