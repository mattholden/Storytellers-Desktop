package org.slage.editor.gametree.actions;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPopupMenu;
import javax.swing.SwingUtilities;

import org.slage.editor.Editor;
import org.slage.editor.gametree.AbstractTreeNode;
import org.slage.editor.gametree.GameTree;

/**
 * A listener that should be registered on the GameTree to respond to mouse
 * clicks.
 * 
 * @author Jared Cope
 */
public class GameTreeMouseListener
		extends MouseAdapter {

	/** used to take action, depending on the mouse clicks */
	private Editor editor;

	/**
	 * Constructs the listener with the <i>editor</i> that will be used to take
	 * action on the mouse clicks
	 * 
	 * @param anEditor used to take action on the mouse clicks
	 */
	public GameTreeMouseListener(Editor anEditor) {
		this.editor = anEditor;
	}

	/**
	 * Will respond to mouse clicks on the GameTree.
	 * <p>
	 * If the mouse event is a <i>right click</i>, then the popup menu for the
	 * currently selected tree node is displayed.
	 * <p>
	 * If the mouse event is a <i>left click</i>, then the editor GUI is changed
	 * appropriately to start editing the object wrapped in that node.
	 * 
	 * @param e the event that triggered this action
	 */
	public void mouseClicked(MouseEvent e) {

		if (SwingUtilities.isRightMouseButton(e)) {
			GameTree tree = (GameTree) e.getSource();
			AbstractTreeNode node = (AbstractTreeNode) tree.getLastSelectedPathComponent();
			if (node != null) {
				JPopupMenu menu = node.getPopMenu();
				menu.show(e.getComponent(), e.getX(), e.getY());
			}
		} else if (SwingUtilities.isLeftMouseButton(e)) {
			GameTree tree = (GameTree) e.getSource();
			AbstractTreeNode node = (AbstractTreeNode) tree.getLastSelectedPathComponent();
			if (node != null) {
				editor.changeGUIForNode(node);
			}
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
