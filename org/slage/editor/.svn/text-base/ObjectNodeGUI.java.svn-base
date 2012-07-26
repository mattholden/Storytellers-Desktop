package org.slage.editor;

import javax.swing.JTabbedPane;

import org.slage.SlageObject;
import org.slage.editor.gametree.ObjectNode;

/**
 * The GUI that is used to display the SLAGE object represented by an <i>object
 * node</i> in the game tree hierachy.
 * <p>
 * The GUI is a tabbed pane to allow editing of:
 * <ul>
 * <li>the object description</li>
 * <li>any states the object has</li>
 * <li>the bounds and images associated with this object</li>
 * <li>any handlers registered on the object</li>
 * </ul>
 * If the object contained in the ObjectNode has no scene image associated with
 * it, then the object is assumed to be a special <i>click boundary</i> object
 * that is defined directly on the background image of the containing room. In
 * this case, the bounds and images are not able to be edited on this GUI.
 * 
 * @author Jared Cope
 * @see org.slage.SlageObject
 * @see org.slage.editor.gametree.ObjectNode
 */
public class ObjectNodeGUI
		extends JTabbedPane {

	/** the SLAGE object that this gui will allow editing of */
	private SlageObject object;

	/**
	 * Constructs the GUI with the tree node that contains the SLAGE object to
	 * display on the GUI. The <i>editor</i> is also supplied to react to certain
	 * user events that will have an effect beyond this GUI.
	 * 
	 * @param node the SLAGE object in this node will be editable on the GUI
	 * @param editor used to react to certain user events on this GUI
	 */
	public ObjectNodeGUI(ObjectNode node, Editor editor) {
		object = node.getWrappedGameObject();

		addTab("Description", new DescriptionPanel(object));
		addTab("States", new StatesPanel(object));
		if (object.getSceneImage() == null) {
			// it is a click boundary on the room image
			addTab("Bounds/Images", new ClickBoundaryPanel());
		} else {
			addTab("Bounds/Images", new ObjectBoundsAndImagesPanel(object, editor));
		}
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
