package org.slage.xml;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.thoughtworks.xstream.XStream;

/**
 * Singleton that dispatches XML tags to the appropriate objects for processing
 * when they are read. This class is abstract so that an instance can never be
 * made, thus enforcing the singleton pattern.
 * 
 * 
 */
public abstract class XMLProcessor<T> {

	/**
	 * Dispatch an XML element found in the file at <i>fileLocation</i> for
	 * processing, and return a java object from the XML information.
	 * <p>
	 * The file is firstly scanned for occurrences of the <i>include</i> element
	 * which are expanded as necessary before processing the final XML content.
	 * 
	 * @param fileLocation the location of the XML file to process
	 * @return a java object created from the XML in the file
	 * @throws NoRegisteredDispatcherException if no match found for the element
	 */
	public static <T> T dispatch(String fileLocation) throws FileNotFoundException {
		XStream stream = new XStream();
		return (T) stream.fromXML(new FileReader(new File(fileLocation)));

		// String startingDirectory = "";
		// try {
		// startingDirectory = Tools.getParentDir(fileLocation);
		// } catch (InvalidParameter e) {
		// org.slage.SlageException.log(e);
		// return null;
		// }
		//
		// String xml = Tools.ReadFile(fileLocation);
		//
		// xml = xml.replaceAll("< *", "<"); // close up messy opening tags
		// while (includeTagIsFound(xml)) {
		// String includeTag = getFirstInclude(xml);
		// String xmlFile = getXMLFileReference(includeTag);
		// // TODO I am assuming that any <include> xml reference file is
		// // located in the same directory as the initial XML file. Need
		// // to make more robust so that files can be referenced from
		// // anywhere on the hard drive.
		// String newXML = Tools.ReadFile(startingDirectory +
		// Tools.GetFileSeparator() + xmlFile);
		// xml = xml.replaceAll(includeTag, newXML);
		// xml = xml.replaceAll("< *", "<"); // again, clean up any messy
		// // opening tags that may have
		// // now been brought in.
		// }
		//
		// Document doc = null;
		// try {
		// File file = new File(startingDirectory, "temp.xml");
		// FileWriter fw = new FileWriter(file);
		// fw.write(xml);
		// fw.flush();
		// fw.close();
		//
		// SAXBuilder builder = new SAXBuilder(true);
		// builder.setFeature("http://apache.org/xml/features/validation/schema",
		// true);
		// doc = builder.build(file.getAbsolutePath());
		// file.delete();
		// } catch (JDOMException e) {
		// org.slage.SlageException.log(e);
		// return null;
		// } catch (IOException e) {
		// org.slage.SlageException.log(e);
		// return null;
		// }
		//
		// Element rootElement = doc.getRootElement();
		// return XMLProcessor.<T> dispatch(rootElement);
	}

	/**
	 * Save the game to a file
	 * 
	 * @param strFile name of the save file
	 * @param root Root object to save
	 */
	public static void save(String strFile, Object root) throws IOException {

		XStream stream = new XStream();
		stream.toXML(root, new FileWriter(new File(strFile)));
	}

	/** File filter for .XML files */
	public final static javax.swing.filechooser.FileFilter XMLFilter = new javax.swing.filechooser.FileFilter() {

		/**
		 * Checks acceptance of a file
		 * 
		 * @param f File to check
		 * @return true if f is a directory or ends in .XML
		 */
		public boolean accept(java.io.File f) {
			if (f.isDirectory())
				return true;
			return (f.getName().toUpperCase().endsWith(".XML"));
		}

		/**
		 * Get the description
		 * 
		 * @return "XML Files"
		 */
		public String getDescription() {
			return "XML Files";
		}
	};

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
