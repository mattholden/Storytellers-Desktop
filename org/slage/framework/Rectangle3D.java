/*
 * Rectangle3D.java
 *
 * Created on September 13, 2005, 11:49 PM
 */

package org.slage.framework;

import java.awt.Graphics2D;

/**
 * Defines a small extension to Rectangle to account for z-order
 * 
 * @author <a href="mailto:Matt@SQ7.org">Matt Holden</a>
 */
public class Rectangle3D
		extends java.awt.Rectangle implements java.io.Serializable {

	/** Z position of the rectangle */
	public int z = 0;

	/**
	 * Creates a new instance of Rectangle3D
	 * 
	 * @param anX x position
	 * @param aY y position
	 * @param aZ z position
	 * @param aWidth width
	 * @param aHeight height
	 */
	public Rectangle3D(int anX, int aY, int aZ, int aWidth, int aHeight) {
		super(anX, aY, aWidth, aHeight);
		this.z = aZ;
	}

	/**
	 * Creates a new instance of Rectangle3D
	 * 
	 * @param r Rectangle to build from
	 * @param aZ z position for the rectangle
	 */
	public Rectangle3D(java.awt.Rectangle r, int aZ) {
		super(r.x, r.y, r.width, r.height);
		this.z = aZ;
	}

	/**
	 * Creates a new instance of Rectangle3D at Z=0
	 * 
	 * @param r Rectangle to build from
	 */
	public Rectangle3D(java.awt.Rectangle r) {
		super(r.x, r.y, r.width, r.height);
		this.z = 0;
	}


	/**
	 * Draw the rectangle on the given Graphics2D
	 * 
	 * @param G2D graphics context to draw to
	 */
	public void draw(Graphics2D G2D) {
		G2D.drawRect(x, y, width, height);
	}

	/**
	 * Get the position of the rectangle
	 * 
	 * @return the position as a Point3D
	 */
	public Point3D getPosition() {
		return new Point3D(x, y, z);
	}

	/**
	 * Set the position of the rectangle
	 * 
	 * @param ptPos the position as a Point3D
	 */
	public void setPosition(Point3D ptPos) {
		x = ptPos.x;
		y = ptPos.y;
		z = ptPos.z;
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
