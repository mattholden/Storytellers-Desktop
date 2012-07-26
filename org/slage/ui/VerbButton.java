/*
 * VerbButton.java
 *
 * Created on October 11, 2005, 2:45 PM
 */

package org.slage.ui;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.slage.SlageGame;
import org.slage.SlageImage;

/**
 * Tool bar button that sets itself as the current tool, and wraps a Verb. This
 * Verb, when paired with the object that is clicked on with the tool, generates
 * a command. Thus, if the LOOK tool is selected, and the TREE object is
 * clicked, the command LOOK TREE is sent to the command handling module.
 * 
 * @author <a href="mailto:Matt@SQ7.org">Matt Holden</a>
 */
public class VerbButton extends ToolBarButton {
    /** Image for the cursor. */
    private SlageImage imgCursor = null;

    /** The actual Cursor to use */
    private Cursor cursor;

    /** Verb to become the current tool */
    private String strVerb;

    /**
     * Accessor for cursor object
     * 
     * @return cursor
     */
    public java.awt.Cursor getCursor() {
        if (cursor == null)
            buildCursor();
        return cursor;
    }

    /** Build the Cursor object */
    private void buildCursor() {
        if (getCursorImage() != null && getVerb() != null) {
            cursor = java.awt.Toolkit.getDefaultToolkit().createCustomCursor(
                    getCursorImage().getImageIcon().getImage(), new java.awt.Point(0, 0),
                    getVerb());
        }
    }

    /** Set this button to be the "active" button */
    public void setAsSelected() {
        if (getToolbar() != null)
            getToolbar().setSelectedTool(this);

        SlageGame game = getToolbar().getGame();
        if (game != null && game.getFrame() != null) {
            game.getFrame().setCursor(getCursor());
        }

        paintImmediately(getBounds());

        if (game != null)
            getToolbar().getGame().getFrame().requestFocusInWindow();
    }

    /**
     * Accessor for the verb that this tool fires
     * 
     * @return strVerb
     */
    public String getVerb() {
        return strVerb;
    }

    /**
     * Set the primary verb to fire when this tool is used
     * 
     * @param strPrimaryVerb
     *            primary verb
     */
    public void setVerb(String strPrimaryVerb) {
        this.strVerb = strPrimaryVerb.toUpperCase();
        buildCursor();

        if (getToolbar() != null)
            setRenderMode(getToolbar().getRenderMode());
    }

    /** Single instance of the input handler for all buttons */
    private static final ActionListener listenerVerbs = new ActionListener() {
        public void actionPerformed(ActionEvent evt) {
            ((VerbButton) evt.getSource()).setAsSelected();
        }
    };

    /**
     * Modifier for image as it should appear as a cursor
     * 
     * @param image
     *            new image
     */
    public void setCursorImage(SlageImage image) {
        this.imgCursor = image;
        imgCursor.scale(ToolBar.CURSOR_HEIGHT, ToolBar.CURSOR_HEIGHT);

        buildCursor();

        if (getToolbar() != null)
            setRenderMode(getToolbar().getRenderMode());
    }

    /**
     * Turn this object into a mouse cursor for the "use object" tool
     * 
     * @return cursor
     */
    public SlageImage getCursorImage() {
        return imgCursor;
    }

    /**
     * Creates a new instance of VerbButton
     * 
     * @param strVerbForFiring
     *            verb to fire
     * @param strImgButton
     *            image file name (QMarked)
     * @param strImgCursor
     *            cursor image file name (QMarked)
     */
    public VerbButton(String strVerbForFiring, String strImgButton, String strImgCursor) {
        super(strImgButton);
        setVerb(strVerbForFiring);

        addActionListener(listenerVerbs);

        if (strImgCursor != null)
            setCursorImage(new SlageImage(strImgCursor));
        else if (strImgButton != null)
            setCursorImage(new SlageImage(getImage().getQMarkPath()));

        buildCursor();

    }

    /** Gets the "label" for the button. Here, a verb */
    public String getButtonLabel() {
        return strVerb;
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
