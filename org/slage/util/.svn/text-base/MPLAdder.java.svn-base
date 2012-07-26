/*
 * MPLAdder.java
 *
 * Created on October 3, 2005, 11:19 PM
 */

package org.slage.util;

import java.io.File;
import java.io.FileFilter;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import org.slage.framework.Tools;

/**
 * 
 * @author Jaeden
 */
public class MPLAdder {

	/** Files to search */
	private static ArrayList listFiles = new ArrayList();

	/**
	 * Run the class file filter recursively to find all files in all
	 * subdirectories that are .java files.
	 * 
	 * @param fDirectory directory to search
	 * @param strExtension file extension
	 */
	private static void runFilter(File fDirectory, final String strExtension) {
		File[] fDirs = fDirectory.listFiles(new FileFilter() {
			public boolean accept(File f) {
				return f.isDirectory();
			}
		});
		File[] fFiles = fDirectory.listFiles(new FileFilter() {
			public boolean accept(File f) {
				return !f.isDirectory() && f.getName().toUpperCase().endsWith(strExtension);
			}
		});

		if (fFiles != null)
			for (int i = 0; i < fFiles.length; i++)
				listFiles.add(fFiles[i]);

		if (fDirs != null)
			for (int i = 0; i < fDirs.length; i++)
				runFilter(fDirs[i], strExtension);
	}

	/**
	 * Find all the .java files in this path and sub-paths
	 * 
	 * @param strPath path to look in
	 * @return an array of URLs representing all the files we found
	 */
	private static URL[] getJavaFiles(String strPath) {
		listFiles.clear();
		// Get a list of files from the directory specified
		File Fdir = new File(strPath);
		runFilter(Fdir, ".JAVA");
		URL[] urls = new URL[listFiles.size()];

		// make a URL array of .class files
		for (int i = 0; i < listFiles.size(); i++) {
			// System.out.println((File)listFiles.get(i)); // debug
			try {
				urls[i] = ((File) listFiles.get(i)).toURL();

				// Don't load bad files.
			} catch (MalformedURLException e) {
				continue;
			}
		}
		return urls;
	}

	/**
	 * Find all the .xml files in this path and sub-paths
	 * 
	 * @param strPath path to look in
	 * @return an array of URLs representing all the files we found
	 */
	private static URL[] getXMLFiles(String strPath) {
		listFiles.clear();
		// Get a list of files from the directory specified
		File Fdir = new File(strPath);
		runFilter(Fdir, ".XML");
		URL[] urls = new URL[listFiles.size()];

		// make a URL array of .class files
		for (int i = 0; i < listFiles.size(); i++) {
			// System.out.println((File)listFiles.get(i)); // debug
			try {
				urls[i] = ((File) listFiles.get(i)).toURL();

				// Don't load bad files.
			} catch (MalformedURLException e) {
				continue;
			}
		}
		return urls;
	}

	/**
	 * Find all the .xsd files in this path and sub-paths
	 * 
	 * @param strPath path to look in
	 * @return an array of URLs representing all the files we found
	 */
	private static URL[] getXSDFiles(String strPath) {
		listFiles.clear();
		// Get a list of files from the directory specified
		File Fdir = new File(strPath);
		runFilter(Fdir, ".XSD");
		URL[] urls = new URL[listFiles.size()];

		// make a URL array of .class files
		for (int i = 0; i < listFiles.size(); i++) {
			// System.out.println((File)listFiles.get(i)); // debug
			try {
				urls[i] = ((File) listFiles.get(i)).toURL();

				// Don't load bad files.
			} catch (MalformedURLException e) {
				continue;
			}
		}
		return urls;
	}

	/**
	 * Add the MPL text to all the files in this directory
	 * 
	 * @param args command line args
	 */
	public static void main(String[] args) {
		URL[] urlJavas = getJavaFiles(Tools.GetRelativePath(null));
		URL[] urlXML = getXMLFiles(Tools.GetRelativePath(null));
		URL[] urlXSD = getXSDFiles(Tools.GetRelativePath(null));

		String strMPL = Tools.ReadFile(Tools.GetQMarkDelimitedPath("org?slage_util?MPL_paste.txt")).trim();

		for (int i = 0; i < urlJavas.length; i++) {
			String strContents = Tools.ReadFile(urlJavas[i].getFile());
			if (strContents.trim().endsWith(strMPL))
				continue;

			System.out.println("Adding license block to " + urlJavas[i].getFile());
			strContents = strContents + "\n\n" + strMPL;
			Tools.writeFile(urlJavas[i].getFile(), strContents);
		}

		String strXMLMPL = "<!--" + strMPL.trim() + "-->";

		for (int i = 0; i < urlXML.length; i++) {
			String strContents = Tools.ReadFile(urlXML[i].getFile());
			if (strContents.trim().endsWith(strXMLMPL))
				continue;

			System.out.println("Adding license block to " + urlXML[i].getFile());

			strContents = strContents + "\n\n" + strXMLMPL;
			Tools.writeFile(urlXML[i].getFile(), strContents);
		}
		for (int i = 0; i < urlXSD.length; i++) {
			String strContents = Tools.ReadFile(urlXSD[i].getFile());
			if (strContents.trim().endsWith(strXMLMPL))
				continue;

			System.out.println("Adding license block to " + urlXSD[i].getFile());

			strContents = strContents + "\n\n" + strXMLMPL;
			Tools.writeFile(urlXSD[i].getFile(), strContents);
		}
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
