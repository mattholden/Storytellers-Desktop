/*
 * HandlerButton.java
 *
 * Created on October 11, 2005, 2:45 PM
 */

package org.slage.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.slage.framework.FrameworkHandler;

/**
 * Tool bar button that fires a handler when clicked.
 * 
 * @author <a href="mailto:Matt@SQ7.org">Matt Holden</a>
 */
public class HandlerButton extends ToolBarButton {

    /** Handler to fire when clicked. */
    private FrameworkHandler handler;

    /** Label for the button */
    private String strLabel;

    /** Single instance of the input handler for all buttons */
    private static final ActionListener listenerHandler = new ActionListener() {
        public void actionPerformed(ActionEvent evt) {
            ((HandlerButton) evt.getSource()).getHandler().execute();
            ToolBarButton TBB = (ToolBarButton) evt.getSource();
            TBB.getToolbar().getGame().getFrame().requestFocusInWindow();
        }
    };

    /**
     * Creates a new instance of HandlerButton
     * 
     * @param theHandler
     *            Handler to fire
     * @param strButtonLabel
     *            label for the button when rendering text
     * @param strImgButton
     *            image file name (QMarked)
     */
    public HandlerButton(FrameworkHandler theHandler, String strButtonLabel,
            String strImgButton) {
        super(strImgButton);
        this.handler = theHandler;
        this.strLabel = strButtonLabel;

        addActionListener(listenerHandler);
    }

    /** Gets the "label" for the button. Here, a verb */
    public String getButtonLabel() {
        return strLabel;
    }

    /**
     * Getter for handler.
     * 
     * @return Value of property handler.
     */
    public FrameworkHandler getHandler() {
        return handler;
    }

    /**
     * Setter for handler.
     * 
     * @param theHandler
     *            New value of property handler.
     */
    public void setHandler(FrameworkHandler theHandler) {
        this.handler = theHandler;
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
