/*
 * $Id: LoggingTest.java,v 1.1 2005/11/04 07:51:52 kevlar2 Exp $
 */
package org.slage.tests.parser;

import java.util.Vector;

import junit.framework.TestCase;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.SimpleLayout;
import org.apache.log4j.WriterAppender;
import org.slage.parser.ParserException;

/**
 * Logging test of ParserExceptions.
 * 
 * @author <a href="mailto:robert.wenner@gmx.de">Robert Wenner</a>
 */
public class LoggingTest
		extends TestCase {
	private java.io.StringWriter logged;

	protected void setUp() {
		logged = new java.io.StringWriter();
		Logger log = Logger.getLogger("org.slage.parser");
		log.setLevel(Level.ALL);
		log.setAdditivity(false);

		log.addAppender(new WriterAppender(new SimpleLayout(), logged));
	}

	protected Vector makeVector(String text) {
		Vector v = new Vector();
		v.add(text);
		return v;
	}

	protected Vector makeVector(String[] words) {
		Vector v = new Vector();
		for (int i = 0; i < words.length; i++) {
			v.add(words[i]);
		}
		return v;
	}

	/*
	 * Removed - tests fail when using a logger project-wide because the logging
	 * is going there and not to the "logged" buffer
	 * 
	 * public void testLogsAmbiguousStuff() { new
	 * AmbiguousInputException(makeVector("box"), makeVector(new String[]{"green
	 * box", "red box"})); assertTrue("Can not find green box in log",
	 * logged.toString().indexOf("green box") > 0); assertTrue("Can not find red
	 * box in log", logged.toString().indexOf("red box") > 0); }
	 * 
	 * 
	 * public void testLogsUnknownStuff() { new
	 * NoSuchStuffException(makeVector("box"), new Vector()); assertTrue("Can not
	 * find box in log", logged.toString().indexOf("box") > 0); }
	 */

	public void testParserExceptionAsStringInsertsAnd() {
		Vector v = new Vector();
		v.add("one");
		v.add("two");
		v.add("three");
		assertEquals("Wrong detail message", "one, two, and three", ParserException.asString(v, ", ", true));
	}

	public void testParserExceptionAsStringInsertsAndUnlessOnlyOneElement() {
		Vector v = new Vector();
		v.add("one");
		assertEquals("Wrong detail message", "one", ParserException.asString(v, ", ", true));
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
