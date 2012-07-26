/*
 * Boundary.java
 *
 * Created on July 25, 2005, 9:58 PM
 */

package org.slage;

import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;

import javax.swing.ImageIcon;

import org.slage.framework.Point3D;
import org.slage.geometry.Geometry;

/**
 * 
 * Boundary is a lightweight wrapper around java.awt.Polygon that provides some
 * additional collision and construction support.
 * 
 * 
 * @author <a href="mailto:Matt@SQ7.org">Matt Holden</a>
 */
public class Boundary
		extends Polygon implements Drawable, Clickable, Collidable {

	// TODO: WTF?
	private static final long serialVersionUID = 5676342997729156388L;

	/** Store the Z value of this boundary */
	private int zOrder = 0;

	/** Do nothing constructor */
	public Boundary() { /* do nothing */
	}

	/**
	 * Create a Boundary from a plain Polygon
	 * 
	 * @param poly Polygon to use points from
	 * @param z Z order
	 */
	public Boundary(Polygon poly, int z) {
		setZ(z);
		setBoundary(poly);
	}

	/**
	 * @param someXPoints
	 * @param someYPoints
	 * @param someNPoints
	 */
	public Boundary(int[] someXPoints, int[] someYPoints, int someNPoints) {
		super(someXPoints, someYPoints, someNPoints);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Create a rectangular Boundary from an image
	 * 
	 * @param img the image
	 * @param iZOrder z location
	 */
	public Boundary(ImageIcon img, int iZOrder) {
		setZ(iZOrder);
		addPoint(0, 0);
		addPoint(0, img.getIconHeight());
		addPoint(img.getIconWidth(), img.getIconHeight());
		addPoint(img.getIconWidth(), 0);
	}
        
        
	/**
	 * Create a rectangular Boundary from an image
	 * 
	 * @param img the image
	 * @param iZOrder z location
	 */
	public Boundary(SlageImage img, int iZOrder) {
		setZ(iZOrder);
		addPoint(0, 0);
		addPoint(0, img.getImageIcon().getIconHeight());
		addPoint(img.getImageIcon().getIconWidth(), img.getImageIcon().getIconHeight());
		addPoint(img.getImageIcon().getIconWidth(), 0);
	}

	/**
	 * Creates a new instance of Boundary
	 * 
	 * @param points array of Point (or Point3D) objects
	 * @param aZOrder z-order of the polygon as a whole
	 */
	public Boundary(Point[] points, int aZOrder) {

		setBoundary(points, aZOrder);
	}

	/**
	 * Accessor for zOrder
	 * 
	 * @return zOrder
	 */
	public int getZ() {
		return zOrder;
	}

	/**
	 * Set zOrder
	 * 
	 * @param z new zOrder
	 */
	public void setZ(int z) {
		zOrder = z;
	}

	/**
	 * Set the boundary's shape from another polygon
	 * 
	 * @param poly Polygon to use
	 */
	public void setBoundary(Polygon poly) {
		for (int i = 0; i < poly.npoints; i++)
			addPoint(poly.xpoints[i], poly.ypoints[i]);

		if (poly instanceof Boundary)
			setZ(((Boundary) poly).getZ());
	}

	/**
	 * Check for collision against a MouseEvent
	 * 
	 * @param evt a MouseEvent
	 * @return 'true' if the point of collision is inside the polygon.
	 */
	public boolean isClicked(java.awt.event.MouseEvent evt) {
		return contains(evt.getX(), evt.getY());
	}

	/**
	 * Check for collision against a coordinate
	 * 
	 * @param X x coordinate
	 * @param Y y coordinate
	 * @return 'true' if the point of collision is inside the polygon.
	 */
	public boolean isClicked(int X, int Y) {
		return contains(X, Y);
	}

	/** store color to draw bounding shapes */
	protected static java.awt.Color colorBoundary = new java.awt.Color(255, 0, 255);

	/** store stroke to draw bounding shapes */
	protected static java.awt.Stroke strokeBoundary = new BasicStroke(10.0f);

	/**
	 * Draw as a filled in polygon
	 * 
	 * @param G2D graphics
	 */
	public void draw(Graphics2D G2D) {
		java.awt.Color colorNow = G2D.getColor();
		java.awt.Stroke strokeNow = G2D.getStroke();

		G2D.setColor(colorBoundary);
		G2D.setStroke(strokeBoundary);

		G2D.drawPolygon(this);

		G2D.setColor(colorNow);
		G2D.setStroke(strokeNow);
	}

	/**
	 * Accessor for first point
	 * 
	 * @return first point
	 */
	public Point3D getPosition() {
		return new Point3D(this.xpoints[0], this.ypoints[0], this.zOrder);
	}

	/**
	 * Move to the given position
	 * 
	 * @param ptPos where to move to
	 */
	public void setPosition(Point3D ptPos) {
		translate(-1 * this.xpoints[0], -1 * this.ypoints[0]);
		translate(ptPos.x, ptPos.y);
		setZ(ptPos.z);
	}

	/**
	 * Accessor for boundary (for clickable interface)
	 * 
	 * @return this
	 */
	public Boundary getClickBoundary() {
		return this;
	}

	/**
	 * Set the values of the Boundary
	 * 
	 * @param points array of points
	 * @param iZ z order
	 */
	public void setBoundary(Point[] points, int iZ) {
		for (int i = 0; i < points.length; i++)
			addPoint(points[i].x, points[i].y);

		this.zOrder = iZ;
	}

	/**
	 * The collision boundary of a boundary is just 'this'
	 * 
	 * @return this
	 */
	public java.awt.Polygon getCollisionBoundary() {
		return this;
	}

	/**
	 * Determine if we are colliding with another Collidable
	 * 
	 * @param collide Collidable to test against
	 * @return 'true' if the objects are in collision
	 */
	public boolean collidesWith(Collidable collide) {
		Polygon other = collide.getCollisionBoundary();

		// first, quick test: are the outer boundaries colliding?

		// if either is contained, they're colliding for sure
		if (this.contains(other.getBounds2D()) || other.contains(getBounds2D()))
			return true;

		// if neither intersects, they're not colliding for sure
		if (intersects(other.getBounds2D()) == false || other.intersects(getBounds2D()) == false)
			return false;

		// if they intersect, we need to do a more thorough test.
		// see if any point from either poly is in the other poly

		for (int i = 0; i < other.npoints; i++)
			if (contains(other.xpoints[i], other.ypoints[i]))
				return true;
		for (int i = 0; i < npoints; i++)
			if (other.contains(xpoints[i], ypoints[i]))
				return true;

		// nothing collided - it was a near miss
		return false;
	}

	/**
	 * Setting a boundary means re-evaluating each point. Z-order is not touched.
	 * 
	 * @param boundary new boundary
	 */
	public void setCollisionBoundary(java.awt.Polygon boundary) {
		reset();
		setBoundary(boundary);
	}

        /** TODO javadoc */
	public double[] findLineIntersections(Point aStart, Point aEnd) {
		return Geometry.findLinePolygonIntersections(Geometry.toDoubleArray(this.xpoints), Geometry.toDoubleArray(this.ypoints), aStart.x, aStart.y, aEnd.x, aEnd.y);
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
