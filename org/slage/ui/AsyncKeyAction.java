/*
 * AsyncKeyAction.java
 *
 * Created on October 8, 2005, 2:24 AM
 */

package org.slage.ui;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.slage.framework.FrameworkHandler;
import org.slage.framework.NamedObject;

/**
 * Defines an Action that can be performed using asynchronous key input.
 * 
 * @author <a href="mailto:Matt@SQ7.org">Matt Holden</a>
 */
public class AsyncKeyAction implements KeyListener, NamedObject {

    /** Logger instance */
    protected static transient final Log LOG = LogFactory.getLog(AsyncKeyAction.class);

    /** Name of the action (Ex: "WalkUp", "Shoot") */
    private String strActionName;

    /** List of our KeyStates */
    private ArrayList<AsyncKeyState> listKeyStates = new ArrayList<AsyncKeyState>();

    /** FrameworkHandler to fire (TODO: Template?) */
    private FrameworkHandler handler;

    /** Acceptance pattern for "All keys must be down' */
    public static transient final boolean ALL = true;

    /** Acceptance pattern for "Any one of the keys must be down. */
    public static transient final boolean ANY = false;

    /** Condition for acceptance - either ANY or ALL */
    private boolean bRequireAll = ALL;

    /**
     * Creates a new instance of AsyncKeyAction
     * 
     * @param strName
     *            action name
     * @param _handler
     *            a Handler
     */
    public AsyncKeyAction(String strName, FrameworkHandler _handler) {
        setName(strName);
        setHandler(_handler);
    }

    /**
     * Getter for property strActionName.
     * 
     * @return Value of property strActionName.
     */
    public java.lang.String getName() {
        return strActionName;
    }

    /**
     * Setter for property strActionName.
     * 
     * @param theActionName
     *            New value of property strActionName.
     */
    public void setName(String theActionName) {
        this.strActionName = theActionName;
    }

    /**
     * Add a Key State
     * 
     * @param iKey
     *            iKey Key to be pressed
     */
    public void addKey(int iKey) {
        listKeyStates.add(new AsyncKeyState(iKey));
    }

    /**
     * Remove a Key State
     * 
     * @param int
     *            iKey Key to be removed
     */
    public void removeKey(int iKey) 
    {
        // not using optimized for() because we're removing        
        for (int i = 0; i < listKeyStates.size(); i++)
        {
            AsyncKeyState AKS = (AsyncKeyState)listKeyStates.get(i);
            if (AKS.getKey() == iKey)
                listKeyStates.remove(AKS);
        }
    }

    /**
     * Get count of number of keys
     * 
     * @return listKeyStates.size()
     */
    public int getKeyCount() {
        return listKeyStates.size();
    }

    /** Clear the key state list */
    public void clear() {
        listKeyStates.clear();
    }

    /**
     * Check if a key is in the list
     * 
     * @param iKey
     *            key to check
     * @return 'true' if it is in the list
     */
    public boolean contains(int iKey) {
        for (AsyncKeyState AKS : listKeyStates)
        {
            if (AKS.getKey() == iKey)
                return true;
        }
        return false;
    }

    /**
     * Getter for fired handler.
     * 
     * @return Value of property fireabhandlerle.
     */
    public FrameworkHandler getHandler() {
        return handler;
    }

    /**
     * Setter for fired handler.
     * 
     * @param aHandler
     *            New handler to bind to this key.
     */
    public void setHandler(FrameworkHandler aHandler) {
        this.handler = aHandler;
    }

    /**
     * Checks whether all keys must be down, or just one
     * 
     * @return Acceptance criteria. Either ANY or ALL.
     */
    public boolean getAcceptance() {
        return bRequireAll;
    }

    /**
     * Sets whether all keys must be down, or just one
     * 
     * @param bAll Acceptance criteria. Either ANY or ALL.
     */
    public void setAcceptance(boolean bAll) {
        this.bRequireAll = bAll;
    }

    /**
     * Check whether the action is currently being performed (if the keys are
     * down)
     * 
     * @return 'true' if the action is currently happening
     */
    public boolean isHappening() {
        for (AsyncKeyState AKS : listKeyStates) {

            // Any succeeds fast, All fails fast
            if (AKS.isDown() && getAcceptance() == ANY)
                return true;
            if (AKS.isDown() == false && getAcceptance() == ALL)
                return false;
        }

        // if we get here and we're ANY, none were down
        // if we get here and we're ALL, all were down
        // conveniently, this matches our bool
        return getAcceptance();
    }

    /** Fire the action (read: its handler) */
    public void fire() {
        handler.execute();
    }

    /**
     * Fired when the key is pressed. Registers the keys "down".
     * 
     * @param keyEvent
     *            KeyEvent fired
     */
    public void keyPressed(KeyEvent keyEvent) {
        // disseminate to keys we own
        for (AsyncKeyState AKS : listKeyStates) {            
            AKS.keyPressed(keyEvent);
        }
    }

    /**
     * Fired when the key is released. Registers the keys "up".
     * 
     * @param keyEvent
     *            KeyEvent fired
     */
    public void keyReleased(KeyEvent keyEvent) {
        // disseminate to keys we own
        for (AsyncKeyState AKS : listKeyStates) {
            AKS.keyReleased(keyEvent);
        }
    }

    /**
     * Fired when the key is quickly pressed and released. Do nothing.
     * 
     * @param keyEvent
     *            KeyEvent fired
     */
    public void keyTyped(KeyEvent keyEvent) {
        // TODO not used?
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
