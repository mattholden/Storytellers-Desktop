package org.slage.editor;

import java.io.File;

import javax.swing.filechooser.FileFilter;

/**
 * A filter to use with a JFileChooser that will restrict the types of files
 * that can be loaded into the editor.
 * <p>
 * The filter will only allow directories and files that end with <i>xml</i> to
 * accepted.
 * 
 * @author Jared Cope
 */
public class EditorFileFilter extends FileFilter {

    /**
     * Will determine if the provided <i>file</i> should be included in the
     * list of files that will appear in the JFileChooser.
     * <p>
     * Only directories and files that end with <i>xml</i> will be accepted.
     * 
     * @param file the file to test for inclusion
     */
    public boolean accept(File file) {

        if (file.isDirectory()) {
            return true;
        }

        String fileName = file.getName();
        int index = fileName.indexOf('.');
        if (index < 0) {
            return false;
        }

        String fileType = fileName.substring(index + 1);
        if (fileType == null || fileType.equals("")) {
            return false;
        }

        if (fileType.equals("xml")) {
            return true;
        }
        return false;

    }

    /**
     * Returns a description of the files that are allowed by this filter.
     * <p>
     * Will return the <code>String</code> "XML files".
     * 
     * @return a description of the files that are allowed by this filter
     */
    public String getDescription() {
        return "XML files";
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

