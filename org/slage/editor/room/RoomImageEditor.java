package org.slage.editor.room;

import java.awt.BasicStroke;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import org.slage.Boundary;
import org.slage.SlageObject;
import org.slage.editor.Editor;

/**
 * TODO
 * 
 * @author Jared Cope
 */
public class RoomImageEditor
		extends JLabel implements MouseMotionListener, MouseListener {

	/** the list of objects that are click objects on the room image */
	private List clickObjects = new ArrayList();
	/** used to control certain editor-wide events that arise from here */
	private Editor editor;

	private boolean drawMode;
	private Polygon polygon;
	private List points = new ArrayList();

	private Point currentMouse = new Point();
	private RoomBoundsAndImagesPanel parent;

	private Cursor currentDefaultCursor = new Cursor(Cursor.DEFAULT_CURSOR);

	public RoomImageEditor(Editor anEditor) {
		this(anEditor, null);
	}

	public RoomImageEditor(Editor anEditor, RoomBoundsAndImagesPanel aParent) {
		this.editor = anEditor;
		this.parent = aParent;
		setHorizontalAlignment(SwingConstants.CENTER);
		setVerticalAlignment(SwingConstants.CENTER);
		setAutoscrolls(true);
		polygon = new Polygon();
		polygon.addPoint(-1, -1);
		addMouseMotionListener(this);
		addMouseListener(this);
	}

	public void setDrawMode(boolean theDrawMode) {
		this.drawMode = theDrawMode;
		if (drawMode == true) {
			currentDefaultCursor = new Cursor(Cursor.CROSSHAIR_CURSOR);
			polygon = new Polygon();
			points.clear();
		} else {
			currentDefaultCursor = new Cursor(Cursor.DEFAULT_CURSOR);
		}
		setCursor(currentDefaultCursor);
	}

	public void addObject(SlageObject obj) {
		clickObjects.add(obj);
	}

	public void clearObjects() {
		clickObjects.clear();
	}

	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		for (int i = 0; i < clickObjects.size(); i++) {
			SlageObject obj = (SlageObject) clickObjects.get(i);
			obj.getClickBoundary().draw(g2d);
		}

		g2d.setStroke(new BasicStroke(4.0f));

		if (drawMode == true) {
			if (points.size() == 0) {
				return;
			}
			if (points.size() == 1) {
				Point p = (Point) points.get(0);
				g2d.drawLine(p.x, p.y, currentMouse.x, currentMouse.y);
			} else {
				Point firstPoint = (Point) points.get(0);
				for (int i = 1; i < points.size(); i++) {
					Point nextPoint = (Point) points.get(i);
					g2d.drawLine(firstPoint.x, firstPoint.y, nextPoint.x, nextPoint.y);
					firstPoint = nextPoint;
				}
				g2d.drawLine(firstPoint.x, firstPoint.y, currentMouse.x, currentMouse.y);
			}
		}
	}

	public void mouseMoved(MouseEvent e) {
		for (int i = 0; i < clickObjects.size(); i++) {
			Point p = new Point(e.getX(), e.getY());
			SlageObject obj = (SlageObject) clickObjects.get(i);
			Boundary b = obj.getClickBoundary();
			if (b.contains(p)) {
				setCursor(new Cursor(Cursor.HAND_CURSOR));
				setToolTipText("<html>This object is a clickboundary" + "<hr>" + "<p>" + "Name: <i>" + obj.getName() + "</i>" + "<hr><b>Double click</b> to load this object.</html>");
				break;
			}
			setToolTipText("");
			setCursor(currentDefaultCursor);
		}
		currentMouse.x = e.getX();
		currentMouse.y = e.getY();
		if (drawMode == true) {
			setToolTipText("(" + currentMouse.x + "," + currentMouse.y + ")");
		}
		repaint();
	}

	public void setImage(ImageIcon image) {
		setIcon(image);
	}

	public void mouseDragged(MouseEvent e) {
		// The user is dragging us, so scroll!
		Rectangle r = new Rectangle(e.getX(), e.getY(), 1, 1);
		scrollRectToVisible(r);
	}

	public void mouseClicked(MouseEvent e) {

		if (e.getClickCount() == 1 && drawMode == true) {
			if (SwingUtilities.isLeftMouseButton(e)) {
				points.add(new Point(e.getX(), e.getY()));
				polygon.addPoint(e.getX(), e.getY());
			} else if (SwingUtilities.isRightMouseButton(e)) {
				System.out.println("Ending drawing");
				setDrawMode(false);
				if (polygon.npoints == 0) {
					return;
				}
				editor.addObjectToCurrentNode(polygon);
				parent.refreshClickBoundaries();
				repaint();
			}
		} else if (e.getClickCount() == 2) {
			boolean found = false;
			SlageObject obj = null;
			for (int i = 0; i < clickObjects.size(); i++) {
				Point p = new Point(e.getX(), e.getY());
				obj = (SlageObject) clickObjects.get(i);
				Boundary b = obj.getClickBoundary();
				if (b.contains(p)) {
					System.out.println("Will load up the " + obj.getName());
					found = true;
					break;
				}
			}
			if (found == true) {
				editor.selectNodeWithObject(obj);
			}
		}
	}

	public void mousePressed(MouseEvent e) {
	    // TODO, use an adapter instead
	}

	public void mouseReleased(MouseEvent e) {
	    //    TODO, use an adapter instead
	}

	public void mouseEntered(MouseEvent e) {
	    //    TODO, use an adapter instead
	}

	public void mouseExited(MouseEvent e) {
	    //    TODO, use an adapter instead
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

