/*
 * ToolBar.java
 *
 * Created on August 24, 2005, 10:42 PM
 */

package org.slage.ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.Toolkit;
import java.util.ArrayList;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.slage.SlageGame;
import org.slage.framework.NotFoundException;
import org.slage.SlageImage;

/**
 * Defines the dockable panel in which our tool buttons reside.
 * 
 * TODO: use Cursor Size and Toolbar Height states
 * TODO: Add functionality to dock on any side of the screen
 * 
 * @author <a href="mailto:Matt@SQ7.org">Matt Holden</a>
 */
public class ToolBar extends Panel implements org.slage.command.Commander {
    /** logger instance */
    private static final Log LOG = LogFactory.getLog(ToolBar.class);

    /** internal list of components for easier updating */
    private ArrayList<ToolBarButton> listComponents = new ArrayList<ToolBarButton>();

    /** Game that owns the toolbar */
    private SlageGame game;

    /**
     * /** Creates a new instance of ToolBar
     * 
     * @param ownerGame
     *            Game that owns the tool bar
     */
    public ToolBar(SlageGame ownerGame) {
        this.game = ownerGame;
        if (ownerGame == null) {
            setBackground(Color.DARK_GRAY);
            setForeground(Color.BLACK);
            setFont(new Font("Arial", 1, 10));
        } else {
            try {
                setBackground((Color) ownerGame
                        .getAttributeValue("Toolbar Background Color"));
            } catch (NotFoundException e) {
                LOG.warn(e);
            }
            try {
                setForeground((Color) ownerGame
                        .getAttributeValue("Toolbar Foreground Color"));
            } catch (NotFoundException e) {
                LOG.warn(e);
            }
            try {
                setFont((Font) ownerGame.getAttributeValue("Toolbar Font"));
            } catch (NotFoundException e) {
                LOG.warn(e);
            }
        }

        setLayout(new GridLayout(1, 1));
        resize();
        setFocusTraversalKeysEnabled(false); // disable focus change when tab
                                                // is
        // pressed
    }

    /**
     * Add a component
     * 
     * @param comp
     *            component to add
     */
    public Component add(ToolBarButton comp) {
        listComponents.add(comp);
        comp.setToolbar(this);

        return super.add(comp);
    }

    /**
     * Remove a component
     * 
     * @param comp
     *            component to remove
     */
    public void remove(Component comp) {
        listComponents.remove(comp);
        super.remove(comp);
    }

    /** Height of the toolbar */
    public static final int TOOLBAR_HEIGHT = 32;

    /** Height of the cursor */
    public static final int CURSOR_HEIGHT = 32;

    /** Resize the console window */
    public void resize() {
        if (game.getFrame() == null)
            return;

        int iW = 0, iH = 0;
        if (game == null || !game.isFullScreen()) {
            iW = game.getFrame().getBounds().width;
            iH = game.getFrame().getBounds().height;
        } else {
            iW = Toolkit.getDefaultToolkit().getScreenSize().width;
            iH = Toolkit.getDefaultToolkit().getScreenSize().height;
        }

        setBounds(0, 0, iW, TOOLBAR_HEIGHT);

    }

    /** Render state: Render only icons */
    public static final int RENDER_ICONS = 0;

    /** Render state: Render only text */
    public static final int RENDER_TEXT = 1;

    /** Render state: Render icons and text */
    public static final int RENDER_BOTH = 2;

    /** The current render state */
    private int iRenderState = RENDER_ICONS;

    /**
     * Set the current render state
     * 
     * @param iState
     *            A Rendering constant. One of the following:
     *            <li>RENDER_ICONS
     *            <Li>RENDER_TEXT
     *            <LI>RENDER_BOTH
     */
    public void setRenderMode(int iState) {
        if (iState < 0 || iState > 2)
            return;
        iRenderState = iState;

        for (ToolBarButton btn : listComponents) {
            btn.setRenderMode(iRenderState);
        }

    }

    /**
     * Get the current render mode.
     * 
     * @return iRenderState
     */
    public final int getRenderMode() {
        return iRenderState;
    }

    /**
     * Get count of buttons
     * 
     * @return listComponents.size()
     */
    public int getButtonCount() {
        return listComponents.size();
    }

    /**
     * Getter for property game.
     * 
     * @return Value of property game.
     */
    public org.slage.SlageGame getGame() {
        return game;
    }

    /**
     * Setter for property game.
     * 
     * @param newGame
     *            New value of property game.
     */
    public void setGame(SlageGame newGame) {
        this.game = newGame;
    }

    /***************************************************************************
     * CURSOR functionality
     **************************************************************************/

    /** Default cursor image */
    private SlageImage imgDefaultCursor;

    /**
     * Get the default cursor image
     * 
     * @return imgDefaultCursor;
     */
    public final SlageImage getDefaultCursor() {
        return imgDefaultCursor;
    }

    /**
     * Set the default cursor image
     * 
     * @param img
     *            new image
     */
    public void setDefaultCursor(SlageImage img) {
        imgDefaultCursor = img;
    }

    /** Currently selected VerbButton */
    private VerbButton toolSelected;

    /**
     * Accessor for current tool
     * 
     * @return toolSelected
     */
    public VerbButton getSelectedTool() {
        return toolSelected;
    }

    /**
     * mutator for current tool
     * 
     * @param strVerb
     *            verb to select
     */
    public void setSelectedTool(String strVerb) {
        toolSelected = getButtonForVerb(strVerb);
    }

    /**
     * mutator for current tool
     * 
     * @param verb
     *            VerbButton to select
     */
    public void setSelectedTool(VerbButton verb) {
        toolSelected = verb;
    }

    /**
     * Get a button for a verb
     * 
     * @param strVerb
     *            verb to look for
     * @return button with that verb or null if there isnt' one
     */
    public VerbButton getButtonForVerb(String strVerb) {
        for (int i = 0; i < listComponents.size(); i++) {
            if (listComponents.get(i) instanceof VerbButton) {
                VerbButton tbb = (VerbButton) listComponents.get(i);
                if (tbb.getVerb().equalsIgnoreCase(strVerb))
                    return tbb;
            }
        }
        return null;
    }

    /** Select first VerbButton */
    public void selectFirstVerbButton() {
        for (int i = 0; i < listComponents.size(); i++)
            if (listComponents.get(i) instanceof VerbButton) {
                ((VerbButton) listComponents.get(i)).setAsSelected();
            }
    }

    /**
     * Retrieve the given button
     * 
     * @param index
     *            Button index to get
     * @return the button reference
     */
    public ToolBarButton getButton(int index) {
        return listComponents.get(index);
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
