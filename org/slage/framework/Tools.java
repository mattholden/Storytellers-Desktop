package org.slage.framework;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.Comparator;

/**
 * Tools is an abstract class consisting of several static functions. Most of
 * these methods are simple string formatting algorithms. However, as Tools is
 * something of a home for homeless methods, you will also find such gems as
 * file reading and downloading.
 */
public abstract class Tools {

	/** Logger instance */
	protected static final org.apache.commons.logging.Log LOG = org.apache.commons.logging.LogFactory.getLog(Tools.class);

	/** Declared here for reuse in TrimFloat() */
	private static DecimalFormat nf = new DecimalFormat();

	/** Cache JVM version for speed */
	private static float fJVMVersion = -1.0f;

	/**
	 * Create a Comparator for alphabetizing strings
	 */
	public static final Comparator alphabetizer = new Comparator() {
		public int compare(Object o1, Object o2) {
			return o1.toString().compareTo(o2.toString());
		}
	};

	/**
	 * Alphabetize a list by its toStrings().
	 * 
	 * @param list the list to alphabetize
	 */
	public static void Alphabetize(java.util.List list) {            
                java.util.Collections.sort(list, alphabetizer);		
	}

      /** Capitalize the first letter of a word 
        *  @param word word to capitalize
        *  @return word with first letter capitalized and the rest not */
        public static String capitalize(String word)
        {
            return new String(Character.toUpperCase(word.charAt(0)) + word.toLowerCase().substring(1));            
        }

        /** Compare two Longs for compareTo() methods, such that we protect against
       overflowing the resultant integer with a long value and returning an incorrect
       comparison.
         *
       @param l1 long to compare
       @param l2 long to compare
       @return -1 if l1 is smaller, 0 if l1 == l2, or 1 if l2 is smaller */
        public static int compareLongs(long l1, long l2)
        {
            long l = l1 - l2;
            if (l == 0) return 0;
            return (l < 0 ? -1 : 1);
        }
         
        /** Compare two floats for compareTo() methods, such that we protect against
       rounding errors that return an incorrect comparison.
         *
       @param f1 float to compare
       @param f2 float to compare
       @return -1 if l1 is smaller, 0 if l1 == l2, or 1 if l2 is smaller */
        public static int compareFloats(float f1, float f2)
        {
            float f = f1 - f2;
            if (f == 0) return 0;
            return (f < 0 ? -1 : 1);
        }
         

	/**
	 * Download a file.
	 * 
	 * @param strURL URL of the file as a string
	 * @param strDestinationFile File to save URL to
	 * @return true if the download completed successfully, false otherwise
	 */
	public static boolean DownloadURL(String strURL, String strDestinationFile) {

		// Convert the URL string to a URL object...
		URL urlFile = null;
		try {
			urlFile = new URL(strURL);
		} catch (MalformedURLException e) {
			LOG.error(e);
			return false;
		}

		// Set up the input streams
		String strRead = new String();
		BufferedReader bReader = null;
		InputStream is = null;
		try {
			is = urlFile.openStream();
			bReader = new BufferedReader(new InputStreamReader(is));
		} catch (IOException e) {
			LOG.error(e);
			return false;
		}

		// Open the output streams...
		BufferedWriter bFileOut = null;
		FileWriter fWriter = null;
		try {
			fWriter = new FileWriter(strDestinationFile);
			bFileOut = new BufferedWriter(fWriter);
		} catch (IOException e) {
			LOG.error(e);
			return false;
		}

		// Download the file
		try {
			while ((strRead = bReader.readLine()) != null) {
				try {
					bFileOut.write(strRead + '\n');
				} catch (IOException e) {
					LOG.error(e);
					return false;
				}

			}
		} catch (Exception e) {
			LOG.error(e);
			return false;
		}

		try {
			bFileOut.flush();
			bFileOut.close();
		} catch (IOException e) {
			LOG.error(e);
			return false;
		}

		return true;
	}

	/**
	 * Shorthand method to get the file separator for this platform.
	 * 
	 * @return file separator for this platform
	 */
	public static String GetFileSeparator() {
		return System.getProperty("file.separator");
	}

	/**
	 * Shorthand method to get the name of the OS we're running on.
	 * 
	 * @return name of the OS we're running on
	 */
	public static String GetOS() {
		return System.getProperty("os.name");
	}

	/**
	 * Get the current running version of the JVM as a float.
	 * 
	 * @return JVM version as a float
	 */
	public static float getJavaVersion() {
		if (fJVMVersion < 0) {
			String strVer = System.getProperty("java.version");
			StringBuffer strNew = new StringBuffer();
			int iDecimals = 0;

			// trim out underscores and count periods
			for (int i = 0; i < strVer.length(); i++) {
				if (strVer.charAt(i) == '.') {
					iDecimals++;

					// only allow one decimal
					if (iDecimals > 1) {
						continue;
					}
					strNew.append('.');
				}

				// trim out underscores, etc.
				if (Character.isDigit(strVer.charAt(i))) {
					strNew.append(strVer.charAt(i));
				}
			}
			fJVMVersion = Float.parseFloat(strNew.toString());
		}
		return fJVMVersion;
	}

	/**
	 * Generate a path for the appropriate OS, excluding the file name.
	 * 
	 * @param strPath a path with . (period) delimited folders. For example,
	 *        "Core.Message".
	 * @return the system specific absolute path
	 */
	public static String GetRelativePath(String strPath) {
		String currentDirectory = System.getProperty("user.dir");
		if (strPath == null) {
			return currentDirectory;
		}

		StringBuffer sb = new StringBuffer(currentDirectory);
		sb.append(GetFileSeparator());

		// For some reason, replaceAll crashes...
		for (int i = 0; i < strPath.length(); i++) {
			if (strPath.charAt(i) == '.') {
				sb.append(GetFileSeparator());
			} else {
				sb.append(strPath.charAt(i));
			}
		}
		return sb.toString();
	}

	/**
	 * Returns a cross-platform absolute file path. Place '?' wherever you would
	 * need the system file separator.
	 * 
	 * @param strPath a '?' delimited path name (including file name)
	 * @return the absolute, cross-platform path to the file
	 */
	public static String GetQMarkDelimitedPath(String strPath) {

		if (strPath == null) {
			return null;
		}

		String currentDirectory = System.getProperty("user.dir");
		if (strPath == null) {
			return currentDirectory;
		}

		StringBuffer sb = new StringBuffer(currentDirectory);
		sb.append(GetFileSeparator());

		// For some reason, replaceAll crashes...
		for (int i = 0; i < strPath.length(); i++) {
			if (strPath.charAt(i) == '?') {
				sb.append(GetFileSeparator());
			} else {
				sb.append(strPath.charAt(i));
			}
		}
		return sb.toString();
	}

        
	/**
	 * Returns a cross-platform relative file path to the root classpath. Place '?' wherever you would
	 * need the system file separator.
	 * 
	 * @param strPath a '?' delimited path name (including file name)
	 * @return the relative, cross-platform path to the file
	 */
	public static String GetRelativeQMarkDelimitedPath(String strPath) {

		if (strPath == null) {
			return null;
		}

		StringBuffer sb = new StringBuffer();
		
		// For some reason, replaceAll crashes...
		for (int i = 0; i < strPath.length(); i++) {
			if (strPath.charAt(i) == '?') {
				sb.append(GetFileSeparator());
			} else {
				sb.append(strPath.charAt(i));
			}
		}
		return sb.toString();
	}

	/**
	 * Get a '?' delimited relative file path by starting with a localized
	 * absolute path. This function will trim off the path to the working
	 * directory and then replace file separators with ? characters (which can
	 * then be read back into cross platform paths using GetQMarkDelimitedPath().
	 * 
	 * @param strPath an absolute file path (including file name if appropriate)
	 * @return the '?' delimted relative path
	 */
	public static String BuildQMarkDelimitedPath(String strPath) {

		if (strPath == null) {
			return null;
		}

		String currentDirectory = System.getProperty("user.dir");
		if (strPath.startsWith(currentDirectory)) {
			strPath = strPath.substring(currentDirectory.length());
		}

		if (strPath.startsWith(GetFileSeparator())) {
			strPath = strPath.substring(GetFileSeparator().length());
		}

		StringBuffer sb = new StringBuffer(strPath);

		// For some reason, replaceAll crashes...
		for (int i = 0; i < strPath.length(); i++) {
			if (sb.charAt(i) == GetFileSeparator().charAt(0)) {
				sb.setCharAt(i, '?');
			}
		}
		return sb.toString();
	}

	/**
	 * Generate a path to the file for the appropriate OS
	 * 
	 * @param strPath a path with '.' delimited folders (ex. Core.Message)
	 * @return the system specific absolute path
	 */
	public static String GetFile(String strPath, String strFile) {
		String currentDirectory = System.getProperty("user.dir");
		if (strPath == null) {
			return currentDirectory;
		}

		StringBuffer sb = new StringBuffer(currentDirectory);
		sb.append(GetFileSeparator());

		// For some reason, replaceAll crashes... \
		int i = 0;
		for (i = 0; i < strPath.length(); i++) {
			if (strPath.charAt(i) == '.') {
				sb.append(GetFileSeparator());
			} else {
				sb.append(strPath.charAt(i));
			}
		}
		sb.append(GetFileSeparator());
		sb.append(strFile);

		return sb.toString();
	}

	/**
	 * Creates a file called <i>fileName</i> in the location specified by the
	 * users home directory and <i>parentPath</i>.
	 * 
	 * @param parentPath the parent directory for the file
	 * @param fileName the name of the file to create
	 * @return the newly created file
	 */
	public static File createFile(String parentPath, String fileName) {
		String currentDirectory = System.getProperty("user.dir");

		StringBuffer sb = new StringBuffer(currentDirectory);
		sb.append(GetFileSeparator());

		// For some reason, replaceAll crashes... \
		int i = 0;
		for (i = 0; i < parentPath.length(); i++) {
			if (parentPath.charAt(i) == '.') {
				sb.append(GetFileSeparator());
			} else {
				sb.append(parentPath.charAt(i));
			}
		}
		sb.append(GetFileSeparator());
		sb.append(fileName);

		return new File(sb.toString());
	}

	/**
	 * Returns the parent directory portion of the provided absolute file <i>path</i>
	 * as a String. Note that the file specified by the path must exist, otherwise
	 * an exception will be thrown.
	 * 
	 * @param path the pathway to the file
	 * @return the absolute parent directory path
	 * @throws IllegalArgumentException if the path does not specify an existing
	 *         file
	 */
	public static String getParentDir(String path) throws IllegalArgumentException {
		File file = new File(path);
		if (!file.exists()) {
			throw new IllegalArgumentException("Tools.getParentDir(): The provided path does not specify an existing file");
		}
		return file.getParent();
	}

	/**
	 * Determine if the Number is an integer type
	 * 
	 * @param I a Number that might be an integer type
	 * @return 'true' if it's an integer type
	 */
	public static boolean isIntegerType(Number I) {
		return (I instanceof Byte || I instanceof Short || I instanceof Integer || I instanceof Long);
	}

	/**
	 * Check if an integer is a power of 2. We do this by seeing if exactly one
	 * bit in the integer is turned on.
	 * 
	 * @param i integer to check
	 * @return true if the image is a power of 2, false otherwise
	 */
	public static boolean isPowerOf2(int i) {
		boolean bPower = false;
		for (int x = 0; x < 32 /* size of int */; x++) {
			if (((i >> x) & 1) == 1) {
				// if it's true we've already found a bit on, so there's more
				// than one
				if (bPower == true) {
					return false;
				}

				// if it's false, we've not found a bit on yet, so turn it on
				if (bPower == false) {
					bPower = true;
				}
			}
		}

		return bPower;
	}

	/**
	 * Convenience method because Matt can never remember which order the classes
	 * in java.lang.Class.isAssignableFrom go :)
	 * 
	 * @param classBase class we want to check for superclass-ness
	 * @param classChild class we think might be a subclass of classBase
	 * @return true if classChild is a subclass/subinterface of classBase
	 */
	public static boolean IsSubclass(Class classBase, Class classChild) {
		return classBase.isAssignableFrom(classChild);
	}

	/**
	 * Write to a file (replacing contents)
	 * 
	 * @param strFile file path to write
	 * @param strText contents of file
	 */
	public static void writeFile(String strFile, String strText) {
		BufferedWriter bwOutput = null;
		try {
			File fileWrite = new File(strFile);
			bwOutput = new BufferedWriter(new FileWriter(fileWrite));
			bwOutput.write(strText);

		} catch (Exception e) {
			LOG.error(e);
		} finally {
			try {
				bwOutput.flush();
				bwOutput.close();
			} catch (Exception e) {
				LOG.error(e);
			}
		}
	}

	/**
	 * Read a file into a String and returns it.
	 * 
	 * @param strFile file name to read
	 * @return contents of the file as a String
	 */
	public static String ReadFile(String strFile) {
		BufferedReader brInput = null;
		StringBuffer sbContents = null;
		try {
			File fileRead = new File(strFile);
			sbContents = new StringBuffer();

			brInput = new BufferedReader(new FileReader(fileRead));
			String strLine = null;
			while ((strLine = brInput.readLine()) != null) {
				sbContents.append(strLine);
				sbContents.append(System.getProperty("line.separator"));
			}

		} catch (FileNotFoundException e) {
			LOG.error(e);
			return null;
		} catch (IOException e) {
			LOG.error(e);
			return null;
		} finally {
			try {
				if (brInput != null) {
					brInput.close();
				}
			} catch (IOException e) {
				LOG.error(e);
			}
		}
		return sbContents.toString();
	}

	/**
	 * Replaces spaces with underscores for purposes of XML-izing strings.
	 * 
	 * @param strSource string with spaces
	 * @return string with underscores
	 */
	public static String ReplaceSpaces(String strSource) {
		StringBuffer buff = new StringBuffer();
		for (int i = 0; i < strSource.length(); i++) {
			if (strSource.charAt(i) == ' ') {
				buff.append('_');
			} else {
				buff.append(strSource.charAt(i));
			}
		}
		return buff.toString();
	}

	/**
	 * Replaces underscores with spaces for purposes of de-XML-izing strings.
	 * 
	 * @param strSource string with underscores
	 * @return string with spaces
	 */
	public static String ReplaceUnderscores(String strSource) {
		StringBuffer buff = new StringBuffer();
		for (int i = 0; i < strSource.length(); i++) {
			if (strSource.charAt(i) == '_') {
				buff.append(' ');
			} else {
				buff.append(strSource.charAt(i));
			}
		}
		return buff.toString();
	}

	/**
	 * Trim a floating-point number into a more attractive string for printing.
	 * 
	 * @param fVal Float
	 * @return String representation with the specified parameters
	 */
	public static String TrimFloat(float fVal) {
		return TrimFloat(fVal, 0, 3, false);
	}

	/**
	 * Trim a floating-point number into a more attractive string for printing.
	 * 
	 * @param fVal Float
	 * @param iMin Minimum digits after decimal
	 * @param iMax maximum digits after decimal
	 * @param bShowDecimal if 'true', always show decimal point
	 * @return String representation with the specified parameters
	 */
	public static String TrimFloat(float fVal, int iMin, int iMax, boolean bShowDecimal) {
		nf.setMaximumFractionDigits(iMax);
		nf.setMinimumFractionDigits(iMin);
		nf.setDecimalSeparatorAlwaysShown(bShowDecimal);
		return nf.format(fVal);
	}

	/**
	 * Don't show a constructor for this class
	 */
	private Tools() {
		//
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
