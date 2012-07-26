/*
 * InventoryCanvas.java
 *
 * Created on August 17, 2005, 1:19 AM
 */

package org.slage.ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import org.slage.Slage;
import org.slage.SlageObject;

/**
 * Create a custom Canvas with paint methods, etc. This is the component on
 * which our inventory dialog will be painted.
 * 
 * @author <a href="mailto:Matt@SQ7.org">Matt Holden</a>
 */
public class InventoryCanvas
		extends java.awt.Canvas {

	/** The InventoryPanel that owns this canvas */
	private InventoryPanel panel;

	/**
	 * Accessor for the panel
	 * 
	 * @return panel
	 */
	public InventoryPanel getPanel() {
		return panel;
	}

	/**
	 * Viewport to scroll around the window
	 */
	private Rectangle rectViewport;

	/**
	 * Creates a new instance of InventoryCanvas
	 * 
	 * @param ownerPanel the InventoryPanel that owns this Canvas
	 */
	public InventoryCanvas(InventoryPanel ownerPanel) {
		this.panel = ownerPanel;
		setFocusTraversalKeysEnabled(false); // disable focus change when tab is
		// pressed
		setFocusable(false); // Don't let the canvas get focus

		// viewport starts as inner bounds but can be moved independently of it
		Rectangle inner = ownerPanel.getDisplay().getInnerBounds();
		rectViewport = new Rectangle(0, 0, inner.width, inner.height);

		addMouseListener(listener);

	}

	/*****************************************************************************
	 * Scrolling
	 ****************************************************************************/

	/** Don't scroll outside the world */
	private void capViewport() {

		int backX = imageBackBuffer.getWidth(null);
		int backY = imageBackBuffer.getHeight(null);

		// enforce size
		if (rectViewport.width > backX)
			rectViewport.width = backX;
		if (rectViewport.height > backY)
			rectViewport.height = backY;
		// enforce edges
		if (rectViewport.x < 0)
			rectViewport.x = 0;
		else if (rectViewport.x + rectViewport.width > backX)
			rectViewport.x = backX - rectViewport.width;
		if (rectViewport.y < 0)
			rectViewport.y = 0;
		else if (rectViewport.y + rectViewport.height > backY)
			rectViewport.y = backY - rectViewport.height;
	}

	/**
	 * Accessor for viewport
	 * 
	 * @return rectViewport
	 */
	public final Rectangle getViewport() {
		return rectViewport;
	}

	/**
	 * Sets viewport
	 * 
	 * @param rect New viewport
	 */
	public void setViewport(Rectangle rect) {
		this.rectViewport = rect;
		capViewport();
	}

	/**
	 * Moves viewport
	 * 
	 * @param x new X position
	 * @param y new Y position
	 */
	public void moveViewport(int x, int y) {
		rectViewport.x = x;
		rectViewport.y = y;
		capViewport();
	}

	/**
	 * Scroll viewport
	 * 
	 * @param x amount to scroll in x
	 * @param y amount to scroll in y
	 */
	public void scroll(int x, int y) {
		rectViewport.x += x;
		rectViewport.y += y;
		capViewport();
	}

	/*****************************************************************************
	 * Back Buffer Functionality
	 ****************************************************************************/

	/** The buffer strategy used to internally represent our double-buffering */
	private BufferStrategy strategy;

	/** our back buffer */
	private Image imageBackBuffer;

	/** Build the back buffer, including drawing all the objects to it */
	public void buildInventoryBuffer() {
		// size of the back buffer
		int X = 0, Y = 0;

		// how many will fit in the non scrolling side
		int iNonScrollSide = 0;

		// whichever side isn't scrolling should just be the inner bound size
		InventoryDisplay ID = panel.getDisplay();
		if (ID.getScrollDirection() == InventoryDisplay.HORIZONTAL) {
			Y = ID.getInnerBounds().height;
			iNonScrollSide = Y / ID.getObjectSize();
		} else {
			X = ID.getInnerBounds().width;
			iNonScrollSide = X / (ID.getObjectSize() + ID.getPadding());
		}

		// now that we know how many rows/cols we're limited to on one side
		// we can figure out how many of the other dimension we need
		int iNumItems = ID.getOwner().getContainedObjectCount();

		int iScrollSide = iNumItems / iNonScrollSide;
		if (iNumItems % iNonScrollSide != 0)
			iScrollSide++;

		// scroll side in px
		int iScrollPx = (iScrollSide * (ID.getObjectSize() + ID.getPadding())) + ID.getPadding();

		// assign to appropriate side and ensure minimum size
		if (X == 0)
			X = Math.max(iScrollPx, ID.getInnerBounds().width);
		else
			Y = Math.max(iScrollPx, ID.getInnerBounds().height);

		// Now we have both dimensions - make the actual buffer
		imageBackBuffer = new BufferedImage(X, Y, BufferedImage.TYPE_INT_ARGB);

		// prep current position, step, objects and graphics context
		int x = ID.getPadding();
		int y = ID.getPadding();
		int iStep = ID.getPadding() + ID.getObjectSize();
		java.util.ArrayList<SlageObject> objects = ID.getOwner().getContainedObjects();
		java.awt.Graphics2D G2D = (Graphics2D) imageBackBuffer.getGraphics();

		// prefer to fill the window before we scroll
		if (ID.getScrollDirection() == InventoryDisplay.HORIZONTAL) {
			for (SlageObject obj : objects) {
				obj.drawInventory(G2D, x, y);
				obj.buildInventoryBoundary(x, y, ID.getObjectSize());

				y += iStep;
				if (y >= ID.getInnerBounds().height) {
					y = ID.getPadding();
					x += iStep;
				}
			}
		} else {
			for (SlageObject obj : objects) {
				obj.drawInventory(G2D, x, y);
				obj.buildInventoryBoundary(x, y, ID.getObjectSize());

				x += iStep;
				if (x >= ID.getInnerBounds().width) {
					x = ID.getPadding();
					y += iStep;
				}
			}
		}

	}

	/** Build back buffer strategy. */
	public void buildBufferStrategy() {
		// Set up buffer strategy for quick double-buffering
		createBufferStrategy(2);
		strategy = getBufferStrategy();
	}

	/*****************************************************************************
	 * Rendering functionality
	 ****************************************************************************/

	/**
	 * Draw the contents of the Draw List
	 * 
	 * @param g Graphics object
	 */
	public void paint(Graphics g) {

		if (strategy == null)
			buildBufferStrategy();
		if (imageBackBuffer == null)
			buildInventoryBuffer();

		// get the G2D for the Canvas
		Graphics2D G2D = (Graphics2D) strategy.getDrawGraphics();

		// clear to transparent
		Color C = G2D.getColor();
		G2D.setColor(new Color(0, 0, 0, 255));
		G2D.clearRect(0, 0, panel.getBounds().width, panel.getBounds().height);
		G2D.setColor(C);

		// draw background image
		if (panel.getDisplay().getBackground() != null) {
			G2D.drawImage(panel.getDisplay().getBackground().getImageIcon().getImage(),

			// destination
					0, 0, panel.getBounds().width, panel.getBounds().height,

					// source
					0, 0, panel.getDisplay().getBackground().getImageIcon().getIconWidth(), 
                                        panel.getDisplay().getBackground().getImageIcon().getIconHeight(),

					// observer
					null);
		}

		// Draw the back buffer, scaled, to the canvas
		Rectangle inner = panel.getDisplay().getInnerBounds();
		G2D.drawImage(imageBackBuffer,

		// render to inner bounds
				inner.x, inner.y, inner.width, inner.height,

				// source
				rectViewport.x, rectViewport.y, rectViewport.width + rectViewport.x, rectViewport.height + rectViewport.y,

				// observer
				null);

		strategy.show();
		G2D.dispose();

	}

	/** Listener for mouse clicks on the canvas */
	private static final java.awt.event.MouseListener listener = new java.awt.event.MouseListener() {
		public void mouseClicked(java.awt.event.MouseEvent evt) {
			InventoryCanvas iCanvas = (InventoryCanvas) evt.getSource();
			SlageObject owner = iCanvas.getPanel().getDisplay().getOwner();

			int x = evt.getX() + iCanvas.getViewport().x;
			int y = evt.getY() + iCanvas.getViewport().y;

			SlageObject obj = owner.getClickedObjectInInventory(x, y);
			if (obj == null || obj.equals(owner))
				return;

			// set to the first Object Button
			ToolBar TB = Slage.getCurrentGame().getToolbar();
			for (int i = 0; i < TB.getButtonCount(); i++) {
				if (TB.getButton(i) instanceof ObjectButton) {
					((ObjectButton) TB.getButton(i)).setObject(obj);
					return;
				}
			}

		}

		public void mouseEntered(java.awt.event.MouseEvent evt) {
		}

		public void mousePressed(java.awt.event.MouseEvent evt) {
		}

		public void mouseReleased(java.awt.event.MouseEvent evt) {
		}

		public void mouseExited(java.awt.event.MouseEvent evt) {
		}
	};
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
