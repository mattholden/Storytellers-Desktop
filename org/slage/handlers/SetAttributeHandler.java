/*
 * SetAttributeHandler.java
 *
 * Created on August 25, 2005, 2:01 AM
 */

package org.slage.handlers;

import org.slage.SlageObject;

/**
 * Sets an attribute and its value on an object.
 * @author <a href="mailto:Matt@SQ7.org">Matt Holden</a>
 */
public class SetAttributeHandler extends Handler {

    /** the name of the attribute to set on the object */
    private String strAttribute;

    /** Value to set the attribute to */
    private Object value;

    /**
     * Creates a new instance of this handler with the object that will
     * receive the new attribute and value.
     * @param object the object that will get the attribute
     * @param strAttName the name of the attribute
     * @param attValue the value for the attribute
     */
    public SetAttributeHandler(SlageObject object, String strAttName,
            Object attValue) {
        super(object);
        setAttribute(strAttName);
        setValue(attValue);
    }

    /**
     * Returns the name of the attribute that will be set on the object.
     * @return the name of the attribute that will be set on the object
     */
    public java.lang.String getAttribute() {
        return strAttribute;
    }

    /**
     * Sets the name of the attribute that will be set for the object.
     * @param strAttName the name of the attribute
     */
    public void setAttribute(String strAttName) {
        this.strAttribute = strAttName;
    }

    /**
     * Returns the value of the attribute that will be set on the object.
     * @return the value of the attribute that will be set on the object
     */
    public Object getValue() {
        return value;
    }

    /**
     * Sets the value for the attribute on the object.
     * @param newValue the value to be used for the attribute
     */
    public void setValue(Object newValue) {
        this.value = newValue;
    }

    /**
     * Will set the attribute with its value on the object.
     */
    protected void fire() {
        getTarget().setAttribute(strAttribute, value);
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
