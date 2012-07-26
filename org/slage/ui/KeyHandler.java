/*
 * KeyHandler.java
 *
 * Created on September 28, 2005, 12:49 AM
 */

package org.slage.ui;

import java.awt.event.KeyEvent;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.slage.framework.FrameworkHandler;

/** Mini-class to pair handlers to keys */
public class KeyHandler implements java.io.Serializable 
{

    /** Logger instance */
    protected static transient final Log LOG = LogFactory.getLog(KeyHandler.class);

    /**
     * Check if the handler should be fired, firing if needed
     * 
     * @param keyEvt
     *            key event
     * @return true if we fired
     */
    public boolean fire(KeyEvent keyEvt) {
        if ((iKey == keyEvt.getKeyCode() || iKey == keyEvt.getKeyChar())
                && bAlt == keyEvt.isAltDown()
                && bCtrl == keyEvt.isControlDown()
                && bShift == keyEvt.isShiftDown()) {
            handler.execute();
            return true;
        }
        return false;
    }

    /**
     * Getter for contained handler.
     * 
     * @return Value of contained handler.
     */
    public FrameworkHandler getHandler() {
        return handler;
    }

    /**
     * Setter for contained handler.
     * 
     * @param _handler
     *            New value of contained handler.
     */
    public void setHandler(FrameworkHandler _handler) {
        this.handler = _handler;
    }

    /**
     * Accessor for key code constant
     * 
     * @return Value of property iKey.
     */
    public int getKey() {
        return iKey;
    }

    /**
     * Setter for key value
     * 
     * @param iKeyValue
     *            New value of property iKey.
     */
    public void setKey(int iKeyValue) {
        this.iKey = iKeyValue;
    }

    /**
     * Returns true if alt must be pressed.
     * 
     * @return Value of property bAlt.
     */
    public boolean needsAlt() {
        return bAlt;
    }

    /**
     * Set to true if alt must be pressed.
     * 
     * @param bAltPressed
     *            New value of property bAlt.
     */
    public void setAlt(boolean bAltPressed) {
        this.bAlt = bAltPressed;
    }

    /**
     * Returns true if ctrl must be pressed.
     * 
     * @return Value of property bCtrl.
     */
    public boolean needsCtrl() {
        return bCtrl;
    }

    /**
     * Set to true if control must be pressed.
     * 
     * @param bCtrlPressed
     *            New value of property bCtrl.
     */
    public void setCtrl(boolean bCtrlPressed) {
        this.bCtrl = bCtrlPressed;
    }

    /**
     * Returns true if shift must be pressed.
     * 
     * @return Value of property bShift.
     */
    public boolean needsShift() {
        return bShift;
    }

    /**
     * Set to true if control must be pressed.
     * 
     * @param bShiftPressed
     *            New value of property bShift.
     */
    public void setShift(boolean bShiftPressed) {
        this.bShift = bShiftPressed;
    }

    /** the handler to pair with */
    private FrameworkHandler handler;

    /** key to fire on */
    private int iKey;

    /** true if 'alt' should be on */
    private boolean bAlt = false;

    /** true if 'ctrl' should be on */
    private boolean bCtrl = false;

    /** true if 'shift' should be on */
    private boolean bShift = false;

    /**
     * construct a key handler
     * 
     * @param _handler
     *            handler to pair with
     * @param iKeyValue
     *            key to fire on
     */
    public KeyHandler(FrameworkHandler _handler, int iKeyValue) {
        this.handler = _handler;
        this.iKey = iKeyValue;
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
