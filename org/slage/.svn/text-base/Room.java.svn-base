/*
 * Room.java
 *
 * Created on July 24, 2005, 5:37 PM
 */

package org.slage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.slage.framework.Point3D;

/**
 * The Room will handle our background image and all the drawn Objects in the
 * room, including player characters. TODO replace dimensions with
 * getImageBoundary
 * 
 * @author <a href="mailto:Matt@SQ7.org">Matt Holden</a>
 */
public class Room
		extends SlageObject {

	/** Logger instance */
	private static final Log LOG = LogFactory.getLog(Room.class);

	/**
	 * Creates a new instance of Room
	 * 
	 * @param strName name of the Room
	 */
	public Room(String strName) {
		super(strName);
		if(LOG.isDebugEnabled()){
			LOG.debug("Constructing new Room '" + strName + "'.");
		}
		setPosition(new Point3D(0, 0, -1));
		rectDimensions = new java.awt.Rectangle(0, 0, 1024, 768);
	}

	/**
	 * Dimensions of the room (width/height) and starting point for viewport (x,
	 * y)
	 */
	private java.awt.Rectangle rectDimensions = new java.awt.Rectangle(0, 0, 1024, 768);

	/**
	 * Set the dimensions of our room
	 * 
	 * @param rect dimensions
	 */
	public void setDimensions(java.awt.Rectangle rect) {
		rectDimensions = rect;
	}

	/**
	 * Set the dimensions of our room
	 * 
	 * @param x width of the room
	 * @param y height of the room
	 */
	public void setDimensions(int x, int y) {
		if (rectDimensions == null)
			rectDimensions = new java.awt.Rectangle(0, 0, x, y);
		else {
			rectDimensions.width = x;
			rectDimensions.height = y;
		}
	}

	/**
	 * Set the viewport's starting position
	 * 
	 * @param x starting x position
	 * @param y starting y position
	 */
	public void setViewportStart(int x, int y) {
		rectDimensions.x = x;
		rectDimensions.y = y;
		ptScrollStart.x = x;
		ptScrollStart.y = y;

	}

	/**
	 * Accessor for dimensions of the room
	 * 
	 * @return dimensions
	 */
	public final java.awt.Rectangle getDimensions() {
		return rectDimensions;
	}

	/** Build a draw list out of all the objects in the room */
	public void buildDrawList() {
		SlageGame game = (SlageGame) getAncestor(SlageGame.class);
		if (game == null)
			return;

		game.moveViewport(rectDimensions.x, rectDimensions.y);
		game.clearDrawList();
		game.addToDrawList(this);
		game.addToDrawList(this.listObjects);
	}

	/**
	 * Set the scene image
	 * 
	 * @param img new image
	 */
	public void setSceneImage(SlageImage img) {
		if (img != null) {
			if (rectDimensions == null)
				rectDimensions = new java.awt.Rectangle(0, 0, img.getImageIcon().getIconWidth(), img.getImageIcon().getIconHeight());
			else {
				rectDimensions.width = img.getImageIcon().getIconWidth();
				rectDimensions.height = img.getImageIcon().getIconHeight();
			}
		}
		super.setSceneImage(img);
	}

	/** Save the original scroll starting position */
	private Point3D ptScrollStart = new Point3D(0, 0, 0);

	/**
	 * Returns the starting X position for scrolling the room
	 * 
	 * @return the starting X position for scrolling the room
	 */
	public int getScrollStartX() {
		return ptScrollStart.x;
	}

	/**
	 * Returns the starting Y position for scrolling the room
	 * 
	 * @return the starting Y position for scrolling the room
	 */
	public int getScrollStartY() {
		return ptScrollStart.y;
	}

	/**
	 * Adding an object to the room adds it to the draw list also when the room is
	 * the current room
	 * 
	 * @param obj object to add
	 */
	public void addObject(SlageObject obj) {
		super.addObject(obj);

		SlageGame game = (SlageGame) getAncestor(SlageGame.class);
		if (game == null)
			return;

		if (game.getRoom() == this)
			game.addToDrawList(obj);
	}

	/**
	 * Remove the given object (should remove it from the drawlist if needed)
	 * 
	 * @param objRemove object to remove
	 */
	public void removeObject(SlageObject objRemove) {
		super.removeObject(objRemove);

		SlageGame game = (SlageGame) getAncestor(SlageGame.class);
		if (game == null)
			return;

		if (game.getRoom() == this)
			game.removeFromDrawList(objRemove);
	}
	
	@Override
	public void accept(SlageObjectVisitor aVisitor) {
		// TODO Auto-generated method stub
		aVisitor.accept(this);
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
