/*
 * GameFrame.java
 *
 * Created on July 24, 2005, 9:53 PM
 */

package org.slage.ui;

import java.awt.Color;
import java.awt.Frame;
import java.awt.event.ComponentListener;
import java.awt.event.WindowListener;
import java.awt.event.WindowStateListener;

import javax.swing.JFrame;
import javax.swing.JPopupMenu;
import javax.swing.ToolTipManager;

import org.slage.Slage;
import org.slage.SlageGame;
import org.slage.handlers.WindowChangeHandler;

/**
 * 
 * Defines the frame in which Slage will run.
 * 
 * @author <a href="mailto:Matt@SQ7.org">Matt Holden</a>
 */
public class GameFrame extends JFrame implements WindowListener,
        WindowStateListener, ComponentListener {

    /** The game that owns this frame */
    private SlageGame game;

    /** The panel on which we will draw our game */
    private GameCanvas canvas;

    /**
     * Creates a new instance of GameFrame
     * 
     * @param owningGame
     *            Owner game
     */
    public GameFrame(SlageGame owningGame) {
        this.game = owningGame;

        // make window listener
        windowChanged = new WindowChangeHandler(owningGame);

        // workaround for gray window displaying - display black instead
        setBackground(java.awt.Color.BLACK);
        getContentPane().setBackground(Color.BLACK);
        getLayeredPane().setBackground(Color.BLACK);

        // workaround for flickering swing components
        ToolTipManager.sharedInstance().setLightWeightPopupEnabled(false); // for
        // tooltips
        JPopupMenu.setDefaultLightWeightPopupEnabled(false); // for menus and
        // pop-ups
        setFocusTraversalKeysEnabled(false); // disable focus change when tab
        // is
        // pressed

        // center on screen
        setLocationRelativeTo(null);

        // exit the game on close.
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Set this class up as a listener to the parent frame for all our
        // interfaces
        this.addComponentListener(this);
        this.addWindowListener(this);
        this.addWindowStateListener(this);

        // make canvas
        canvas = new GameCanvas(this);
        getLayeredPane().setLayout(null);
        getLayeredPane().add(canvas, new Integer(0));

        // load game settings (or default ones)
        loadGameSettings();

    }

    /***************************************************************************
     * Accessors / Mutators
     **************************************************************************/

    /**
     * Accessor for canvas
     * 
     * @return canvas
     */
    public GameCanvas getCanvas() {
        return canvas;
    }

    /**
     * maximize/restore window
     * 
     * @param bMax
     *            'true' if we are going to maximized
     */
    public void maximize(boolean bMax) {
        setExtendedState(bMax ? Frame.MAXIMIZED_BOTH : Frame.NORMAL);
    }

    /***************************************************************************
     * WindowListener / WindowStateListener functionality
     **************************************************************************/

    /**
     * Called when the window is activated
     * 
     * @param windowEvent
     *            a WindowEvent
     */
    public void windowActivated(java.awt.event.WindowEvent windowEvent) {
        windowChanged.execute();
    }

    /**
     * Called when the window is closed
     * 
     * @param windowEvent
     *            a WindowEvent
     */
    public void windowClosed(java.awt.event.WindowEvent windowEvent) {
        // TODO implement me, or use an adapter
    }

    /**
     * Called when the window is about to close
     * 
     * @param windowEvent
     *            a WindowEvent
     */
    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
        new org.slage.handlers.QuitHandler(game).execute();
    }

    /**
     * Called when the window is deactivated
     * 
     * @param windowEvent
     *            a WindowEvent
     */
    public void windowDeactivated(java.awt.event.WindowEvent windowEvent) {
        windowChanged.execute();
    }

    /**
     * Called when the window is restored (de-iconified)
     * 
     * @param windowEvent
     *            a WindowEvent
     */
    public void windowDeiconified(java.awt.event.WindowEvent windowEvent) {
        windowChanged.execute();
    }

    /**
     * Called when the window is iconified
     * 
     * @param windowEvent
     *            a WindowEvent
     */
    public void windowIconified(java.awt.event.WindowEvent windowEvent) {
        windowChanged.execute();
    }

    /**
     * Called when the window is opened
     * 
     * @param windowEvent
     *            a WindowEvent
     */
    public void windowOpened(java.awt.event.WindowEvent windowEvent) {
        windowChanged.execute();
    }

    /**
     * Called when the window's state changes (gets maximized or minimized or
     * restored...)
     * 
     * @param windowEvent
     *            a WindowEvent
     */
    public void windowStateChanged(java.awt.event.WindowEvent windowEvent) {
        windowChanged.execute();
    }

    /***************************************************************************
     * ComponentListener functionality
     **************************************************************************/

    public void componentHidden(java.awt.event.ComponentEvent componentEvent) {
        windowChanged.execute();
    }

    public void componentMoved(java.awt.event.ComponentEvent componentEvent) {
        windowChanged.execute();
    }

    public void componentResized(java.awt.event.ComponentEvent componentEvent) {
        windowChanged.execute();
    }

    public void componentShown(java.awt.event.ComponentEvent componentEvent) {
        windowChanged.execute();
    }

    /** A WindowChangeHandler for all our window commands */
    private WindowChangeHandler windowChanged;

    /** Load default values for window/game states */
    public void loadDefaults() {
        setTitle(Slage.getVersionText());
        setBounds(0, 0, 640, 480);
        maximize(true);
    }

    /** Load game's values for window/game states */
    public void loadGameSettings() {
        loadDefaults();
        if (game == null)
            return;

        try {
            int iWidth = game.getAttributeAsInt("Window Width");
            int iHeight = game.getAttributeAsInt("Window Height");
            setBounds(0, 0, iWidth, iHeight);
        } catch (Exception e) { /* use defaults */
        }

        maximize(game.isFullScreen());
        setTitle(game.getName());
    }

    /**
     * Getter for property game.
     * 
     * @return Value of property game.
     */
    public org.slage.SlageGame getGame() {
        return game;
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
