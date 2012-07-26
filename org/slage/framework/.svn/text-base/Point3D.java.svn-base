/*
 * Point3D.java
 *
 * Created on July 24, 2005, 8:39 PM
 */

package org.slage.framework;

/**
 * store x,y,z Cartesian coordinates. Hungarian notation and private access
 * omitted on x, y, z fields for consistency with base class Point.
 * 
 * The 'z' coordinate is not a true 3D z coordinate; rather, it is used as a
 * z-order component.
 * 
 * @author <a href="mailto:Matt@SQ7.org">Matt Holden</a>
 */
public class Point3D
		extends java.awt.Point implements java.io.Serializable
{
	/** The z coordinate. If no z coordinate is set it will default to 0. */
	public int z = 0;

	/** Creates a new instance of Point3D. Its coordinates will be 0,0,0. */
	public Point3D() { /** deliberately blank */ }
		
	/**
	 * Creates a new instance of Point
	 * 
	 * @param iX x coordinate
	 * @param iY y coordinate
	 * @param iZ z coordinate
	 */
	public Point3D(int iX, int iY, int iZ) {
		x = iX;
		y = iY;
		z = iZ;
	}

	/**
	 * Set the values for the Point
	 * 
	 * @param iX x coordinate
	 * @param iY y coordinate
	 * @param iZ z coordinate
	 */
	public void set(int iX, int iY, int iZ) {
		x = iX;
		y = iY;
		z = iZ;
	}

	/**
	 * Translate a point by the given amount
	 * 
	 * @param iX amount to translate in the X
	 * @param iY amount to translate in the Y
	 * @param iZ amount to translate in the Z
	 */
	public void translate(int iX, int iY, int iZ) {
		super.translate(iX, iY);
		z += iZ;
	}

	/**
	 * Get a string representation of the point
	 * 
	 * @return string representation in the form (x, y, z)
	 */
	public String toString() {
		return "[x: " + Integer.toString(x) + " y: " + Integer.toString(y) + " z: " + Integer.toString(z) + "]";
	}

	/**
	 * Draw the point as a dot
	 * 
	 * @param G2D graphics interface
	 */
	public void draw(java.awt.Graphics2D G2D) {
		G2D.drawLine(x, y, x, y);
	}

	/**
	 * Get the position (this)
	 * 
	 * @return this
	 */
	public Point3D getPosition() {
		return this;
	}

	/**
	 * Set the position's values
	 * 
	 * @param ptPos new position
	 */
	public void setPosition(Point3D ptPos) {
		this.x = ptPos.x;
		this.y = ptPos.y;
		this.z = ptPos.z;
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
