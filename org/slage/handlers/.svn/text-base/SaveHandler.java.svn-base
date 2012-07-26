/*
 * SaveHandler.java
 *
 * Created on September 27, 2005, 10:46 PM
 */

package org.slage.handlers;

import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.slage.SlageObject;
import org.slage.xml.XMLProcessor;

/**
 * A handler that is used to save a game object to a specified file.
 * @author <a href="mailto:Matt@SQ7.org">Matt Holden</a>
 */
public class SaveHandler extends Handler {

    /** the logger for this class */
    private static final Log LOG = LogFactory.getLog(SaveHandler.class);

    /** Filename to save to */
    private String strFile;

    /** Optionally can be used for naming the XML element for the object */
    private String strTag;

    /**
     * Creates a new instance of a SaveHandler with the object to save, the
     * name of the file to save it in and a name for the outermost XML element
     * that will describe the provided object.
     * @param obj the object to save
     * @param strFilename the name of the file to save to
     * @param strXMLTag the name of the outer-most XML element for the object
     */
    public SaveHandler(SlageObject obj, String strFilename, String strXMLTag) {
        super(obj);
        this.strFile = strFilename;
        this.strTag = strXMLTag;
    }

    /**
     * Creates a new instance of a SaveHandler with the object to save and the
     * name of the file to save it in.
     * @param obj the object to save
     * @param strFilename the name of the file to save to
     */
    public SaveHandler(SlageObject obj, String strFilename) {
        this(obj, strFilename, null);
    }

    /**
     * Will save the object provided in the constructor of this class.
     */
    protected void fire() {
        if (strFile != null && getTarget() != null) {
            try {
                XMLProcessor.save(strFile, getTarget());
            } catch (IOException e) {
                LOG.error(e.getMessage(), e);
            }
        }
    }

    /**
     * Returns the name of the file that the object will be saved in.
     * @return the name of the file that the object will be saved in
     */
    public String getFileName() {
        return strFile;
    }

    /**
     * Returns the name of the XML element that will represent the object to
     * be saved.
     * @return the name of the XML element
     */
    public String getTag() {
        return strTag;
    }

    /**
     * Sets the name of the XML element that will represent the object when it
     * is saved.
     * @param xmlTag the name of the XML element
     */
    public void setTag(String xmlTag) {
        this.strTag = xmlTag;
    }

    /**
     * Sets the filename for where the object will get saved to.
     * @param filename the filename to save to
     */
    public void setFileName(String filename) {
        this.strFile = filename;
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
