package org.slage.editor;

import java.awt.Polygon;

import org.slage.SlageObject;
import org.slage.editor.gametree.AbstractTreeNode;

/**
 * Defines operations that the editor needs to perform at a high level.
 * 
 * @author Jared Cope
 */
public interface Editor {

	/**
	 * Will create a blank game to start editing.
	 */
	public abstract void createNewGame();

	/**
	 * Will create a blank act to start editing
	 */
	public abstract void createNewAct();

	/**
	 * Will create a blank scene to start editing
	 */
	public abstract void createNewScene();

	/**
	 * Will create a blank room to start editing
	 */
	public abstract void createNewRoom();

	/**
	 * Will create a blank object to start editing
	 */
	public abstract void createNewObject();

	/**
	 * Will clear the editor and remove all the work that has been done so far.
	 * This will remove the game tree and the GUI used to edit game components.
	 */
	public abstract void clearCurrentSession();

	/**
	 * Will load a saved game into the editor that is found at the provided file
	 * system location.
	 * 
	 * @param fileLocation the location of the game file to load
	 */
	public abstract void loadSavedGame(String fileLocation);

	/**
	 * Will load the provided <i>object</i> into the editor. The object could be
	 * a simple object, a room or even a whole game.
	 * 
	 * @param object the object to load into the editor
	 */
	public abstract void loadObject(SlageObject object);

	/**
	 * Will save the current game in the editor to a file specified by <i>filePath</i>.
	 * 
	 * @param filePath the location to save the game to
	 */
	public abstract void saveGame(String filePath);

	/**
	 * Will change the editor GUI to one that is appropriate for editing the
	 * provided <i>node</i>.
	 * 
	 * @param node the node to change the GUI display for
	 */
	public abstract void changeGUIForNode(AbstractTreeNode node);

	/**
	 * Will find and select the node within the game tree that contains the
	 * provided <i>object</i>.
	 * 
	 * @param object the object to find within the tree nodes
	 */
	public abstract void selectNodeWithObject(SlageObject object);

	/**
	 * Will create a new <b>SLAGEObject</b> using the provided <i>polygon</i> as
	 * a clickboundary, and attach it to the currently selected tree node.
	 * 
	 * @param polygon to be used as the click boundary for the new object
	 */
	public abstract void addObjectToCurrentNode(Polygon polygon);
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
