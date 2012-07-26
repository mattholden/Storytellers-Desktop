package org.slage.editor.gametree;

import javax.swing.JPopupMenu;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

import org.slage.SlageObject;
import org.slage.editor.Editor;

/**
 * An abstract class that represents the base functionality and state for nodes
 * that will appear in the JTree that represents a game in the editor.
 * <p>
 * Sub-classes need to provide an implementation for creating their own popup
 * menus, and returning the SLAGE object that they are representing.
 * 
 * @author Jared Cope
 */
public abstract class AbstractTreeNode
		extends DefaultMutableTreeNode {

	/** the name of this node */
	protected String nodeTitle;
	/** the menu that will be displayed when the node is right-clicked */
	protected JPopupMenu menu;
	/** the tree that this node is a part of */
	protected JTree tree;

	/**
	 * Constructs this node with the provided <i>nodeTitle</i>.
	 * 
	 * @param aTitle the title for this node
	 */
	public AbstractTreeNode(String aTitle) {
		this.nodeTitle = aTitle;
	}

	/**
	 * Over-ridden to return the name of this node. This method is utilized by the
	 * JTree to obtain a label for the node on its display.
	 * 
	 * @return the string to be used for the label of this node
	 */
	public String toString() {
		return nodeTitle;
	}

	/**
	 * Returns the popup menu that has been created for this node.
	 * 
	 * @return the popup menu that has been created for this node
	 */
	public JPopupMenu getPopMenu() {
		return menu;
	}

	/**
	 * Will create the popup menu that is displayed for this node when it is right
	 * clicked. The provided <i>title</i> can be used to provide a more
	 * meaningful name in the menu.
	 * 
	 * @param title can be used to customize the title of the menu
	 * @param editor used to react to actions generated on this menu
	 */
	public abstract void createPopupMenu(String title, Editor editor);

	/**
	 * Returns the SLAGE object that this node represents. Clients of this method
	 * may need to cast the returned object to something more specific if that
	 * level of precision is desired.
	 * 
	 * @return the SLAGE object that this node represents
	 */
	public abstract SlageObject getWrappedGameObject();

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
