/*
 * ScrollHandler.java
 *
 * Created on August 27, 2005, 12:38 AM
 */

package org.slage.handlers;

import org.slage.Slage;
import org.slage.SlageGame;

/**
 * Scrolls the viewport of a game by the specified X and Y values.
 * 
 * @author <a href="mailto:Matt@SQ7.org">Matt Holden</a>
 */
public class ScrollHandler extends Handler {

    /** Amount to scroll in the x */
    private int x;

    /** amount to scroll in the y */
    private int y;

    /**
     * Creates a new instance of ScrollHandler
     * 
     * @param game the game that will have the viewport scrolled
     * @param xPos amount in the x axis to scroll
     * @param yPos amount in the y axis to scroll
     */
    public ScrollHandler(SlageGame game, int xPos, int yPos) {
        super(game);
        this.x = xPos;
        this.y = yPos;
    }

    /**
     * Scrolls the viewport of the game using the X and Y values set for this
     * instance.
     */
    protected void fire() {

        if (getTarget() instanceof SlageGame == false) {
            setTarget(Slage.getCurrentGame());
        }

        ((SlageGame) getTarget()).scroll(x, y);
    }

    /**
     * Returns the X value that will be used to adjust the viewport for the
     * game.
     * @return the X value that will be used to adjust the viewport
     */
    public int getX() {
        return x;
    }

    /**
     * Sets the X value that will be used to adjust the viewport for the game.
     * @param newX the X value to be used when adjusting the viewport
     */
    public void setX(int newX) {
        this.x = newX;
    }

    /**
     * Returns the Y value that will be used to adjust the viewport for the
     * game.
     * @return the Y value that will be used to adjust the viewport
     */
    public int getY() {
        return y;
    }

    /**
     * Sets the Y value that will be used to adjust the viewport for the game.
     * @param newY the Y value to be used when adjusting the viewport
     */
    public void setY(int newY) {
        this.y = newY;
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
