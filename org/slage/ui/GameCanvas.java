/*
 * GameCanvas.java
 *
 * Created on August 17, 2005, 1:19 AM
 */

package org.slage.ui;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

/**
 * Create a custom Canvas with paint methods, etc. This is the component on
 * which our actual game will be painted.
 * 
 * @author <a href="mailto:Matt@SQ7.org">Matt Holden</a>
 */
public class GameCanvas extends Canvas {

    /** The GameFrame that owns this canvas */
    private GameFrame frame;

    /**
     * Creates a new instance of GameCanvas
     * 
     * @param owningFrame
     *            the GameFrame that owns this Canvas
     */
    public GameCanvas(GameFrame owningFrame) {
        this.frame = owningFrame;
        setFocusTraversalKeysEnabled(false); // disable focus change when tab
                                                // is
        // pressed

        // set background color of the frame for clears
        setBackground(owningFrame.getBackground());
        setBackground(Color.BLACK);

        // back buffer will be the total size of our WORLD room
        resizeBackBuffer(1024, 768);

        // Synchronize the boundaries
        syncBounds();

        // Don't let the canvas get focus
        setFocusable(false);
        setFocusTraversalKeysEnabled(false);

        // don't repaint; we will do it
        setIgnoreRepaint(true);

    }

    /***************************************************************************
     * Back Buffer Functionality
     **************************************************************************/

    /** The buffer strategy used to internally represent our double-buffering */
    private BufferStrategy strategy;

    /** our back buffer */
    private Image imageBackBuffer;

    /**
     * Resize the back buffer
     * 
     * @param iW
     *            width
     * @param iH
     *            height
     */
    public void resizeBackBuffer(int iW, int iH) {
        // set up back buffer
        imageBackBuffer = new BufferedImage(iW, iH, BufferedImage.TYPE_INT_ARGB);
    }

    /** Build back buffer strategy. */
    public void buildBufferStrategy() {
        // Set up buffer strategy for quick double-buffering
        createBufferStrategy(2);
        strategy = getBufferStrategy();
    }

    /** Synchronize the position of the canvas to the frame */
    public void syncBounds() {
        // bounds will match the outer frame
        setBounds(0, 0, frame.getBounds().width, frame.getBounds().height);
    }

    /***************************************************************************
     * Rendering functionality
     **************************************************************************/

    /**
     * Draw the contents of the Draw List
     * 
     * @param g
     *            Graphics object
     */
    public void paint(Graphics g) {

        if (strategy == null)
            buildBufferStrategy();
        if (imageBackBuffer == null)
            resizeBackBuffer(1024, 768);

        // get the G2D for the Canvas
        Graphics2D G2D = (Graphics2D) strategy.getDrawGraphics();

        // clear to black
        G2D.clearRect(0, 0, imageBackBuffer.getWidth(null), imageBackBuffer
                .getHeight(null));

        // draw drawlist items to the back buffer
        Graphics2D g2dBack = (Graphics2D) imageBackBuffer.getGraphics();
        frame.getGame().draw(g2dBack);

        Rectangle rectViewport = frame.getGame().getViewport();

        // Draw the back buffer, scaled, to the canvas
        G2D.drawImage(imageBackBuffer,

        // destination
                0, 0,

                // if full screen, render to size of screen. Else, render to
                // size of
                // window
                ((frame.getGame().isFullScreen()) ? Toolkit.getDefaultToolkit()
                        .getScreenSize().width : getBounds().width), ((frame
                        .getGame().isFullScreen()) ? Toolkit
                        .getDefaultToolkit().getScreenSize().height
                        : getBounds().height),

                // source
                rectViewport.x, rectViewport.y, rectViewport.width
                        + rectViewport.x, rectViewport.height + rectViewport.y,

                // observer
                null);

        strategy.show();
        g2dBack.dispose();
        G2D.dispose();

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
