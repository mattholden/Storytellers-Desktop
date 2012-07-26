/*
 * ResponseHandler.java
 *
 * Created on August 26, 2005, 4:18 PM
 */

package org.slage.handlers;

import java.awt.Color;
import org.slage.Slage;
import org.slage.SlageObject;

/**
 * Base response handler. Plays the selected sound and writes the text to the
 * console window.
 * 
 * @author Owner
 */
public class ResponseHandler extends PlaySoundHandler {

    /** The text to display when responding */
    private String displayText = null;

    /** Default color for response handlers */
    private Color colorDefault = new Color(0xAC, 0x99, 0x57);

    /**
     * Creates a new instance of ResponseHandler
     * 
     * @param obj
     *            the game object to set this handler for
     * @param strText
     *            the response text to write to console
     * @param strSound
     *            full path to sound file to play
     */
    public ResponseHandler(SlageObject obj, String strText, String strSound) {
        super(obj, strSound);
        this.displayText = strText;
    }

    /**
     * Will play a sound if there was one registered for this handler, and
     * display the response text on the game console.
     */
    protected void fire() {

        // play the sound
        // also ensures getTarget() is-a SlageGame and linkObjects is called
        super.fire();

        // we're guaranteed this is OK by super.fire();

        Slage.getCurrentGame().getConsole().addToHistory(displayText);

    }

    /**
     * Returns the text that is set to be displayed on the game console when
     * this handler fires.
     * 
     * @return the text that will be displayed when this handler fires
     */
    public String getText() {
        return displayText;
    }

    /**
     * Sets the text that will be displayed on the game console when this
     * handler is fired.
     * 
     * @param text the text to be displayed on the game console
     */
    public void setText(String text) {
        this.displayText = text;
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
