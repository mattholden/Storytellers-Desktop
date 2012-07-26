// $Id: VocabularyFilesTest.java,v 1.1 2005/11/04 07:51:52 kevlar2 Exp $

package org.slage.tests.parser;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import junit.framework.TestCase;

import org.jdom.input.JDOMParseException;
import org.slage.parser.Parser;
import org.xml.sax.SAXParseException;

/**
 * Most tests in this class make sure the XML Parser has the right features set
 * and the XML schema is right.
 * 
 * @author <a
 *         href="mailto:robert.wenner@gmx.de?subject=VocabularyFilesTest">Robert
 *         Wenner</a>
 */
public class VocabularyFilesTest
		extends TestCase {
	private static final String xmlHeader = "<?xml version='1.0' standalone='no'?>\n" + "<vocabulary\n" + "	xmlns:xsi='http://www.w3.org/2001/XMLSchema-instance'\n" + "	xmlns:parser='http://org.slage.parser'\n" + "	xsi:schemaLocation='http://org.slage.parser "
			+ "xml/vocabulary.xsd'\n" + "	parser='org.slage.parser.Parser'>\n";

	private static Parser createParserFrom(String xml) throws Exception {
		InputStream in = new ByteArrayInputStream(xml.getBytes());
		return Parser.createFromXML(in);
	}

	/**
	 * @see #assertRejects(String, String, String)
	 */
	private static void assertRejects(String badXML, String expectedMsg) throws Exception {
		assertRejects(badXML, expectedMsg, "Created parser from flawed XML");
	}

	/**
	 * Asserts that the given XML string is rejected as vocabulary.
	 * 
	 * @param badXML the bad XML string.
	 * @param expected regular expression that the XML Parser's error message.
	 *        must match. Unfortunately, XML parsers throw either a SAX or a JDOM
	 *        exception without further classification on the kind of error
	 *        encountered (eg, illegal attribute value or duplicate attribute).
	 *        This code is a tradeoff between hoping the right thing went wrong
	 *        and parsing the exception message. Hence you'd rather not include
	 *        too much detail here to avoid breaking your test when the XML parser
	 *        people change error message texts. (Think of internationalization.)
	 *        It's probably a good idea to look for the names of the offending
	 *        attribute or element names or values in the error message. Surround
	 *        them by \\b (\b matches a word boundary, but you need to escape the
	 *        backslash from Java's string escaping) and pick unique names, to
	 *        avoid matching part of a different error message by accident. Due to
	 *        a bug in Sun's regular expression matching, you must suround the
	 *        regular expression with .* to match leading and trailing characters.
	 *        Other than in Perl, you need this in Java even if you don't use
	 *        anchor characters. Put (?i) in front of your pattern for
	 *        case-independent matching.
	 * @throws Exception if anything other than JDOMException or SAXException
	 *         happens when attempting to build the parser.
	 * @see String#matches(java.lang.String)
	 */
	private static void assertRejects(String badXML, String expected, String failureMsg) throws Exception {
		String msg = null;
		try {
			createParserFrom(badXML);
			fail(failureMsg);
		}
		// Tests check for bad XML accept SAXParseExceptions as well as
		// JDOMParseExceptions. What exception is actually thrown depends on the
		// XMLParser used (JDOM throws JDOMParseExceptions (even if wrapping
		// Xerces), while Xerces alone throws SAXParseExceptions).
		catch (SAXParseException sax) {
			msg = sax.getMessage();
		} catch (JDOMParseException jdom) {
			msg = jdom.getMessage();
		}
		assertTrue("Wrong failure message: '" + msg + "' does not match '" + expected + "'", msg.matches(expected));
	}

	public void testMustHaveAtLeastOneSeparator() throws Exception {
		String xml = xmlHeader + "	<Verb word='take' code='TAKE'/>\n" + "</vocabulary>";
		String failMsg = "Accepted vocabulary without separator";
		String expectedMsg = ".*Separator.*";
		assertRejects(xml, expectedMsg, failMsg);
	}

	public void testRejectsEmptyVocabulary() throws Exception {
		assertRejects(xmlHeader + "</vocabulary>", ".*vocabulary.*");
	}

	public void testRejectsBadEntity() throws Exception {
		String xml = xmlHeader + "<Separator word='and'/>\n" + "	<Foo code='FOO' word='foo'/>\n" + "</vocabulary>\n";
		String failureMsg = "Accepted bad entity";
		String expectedMsg = ".*\\bInvalid content\\b.*";
		assertRejects(xml, expectedMsg, failureMsg);
	}

	public void testRejectsMisplacedEntity() throws Exception {
		String xml = xmlHeader + "<Separator word='and'/>\n" + "<Preposition code='FOO' word='foo' provideNoun='never'>\n" + "	<defaultPreposition word='foo'/>\n" + "</Preposition>\n" + "</vocabulary>\n";
		String expectedMsg = ".*content type.*";
		assertRejects(xml, expectedMsg);
	}

	public void testRejectsBadAttribute() throws Exception {
		String xml = xmlHeader + "<Separator word='and'/>\n" + "<Pronoun thisIsABadAttribute='foo' code='FOO' word='foo'/>\n" + "</vocabulary>\n";
		String expectedMsg = ".*\\bthisIsABadAttribute\\b.*";
		assertRejects(xml, expectedMsg);
	}

	public void testRejectsIllegalAttributeValue() throws Exception {
		String xml = xmlHeader + "<Separator word='and'/>\n" + "<Pronoun gender='thisIsNoValidGender' word='theword'/>\n" + "</vocabulary>\n";
		String expectedMsg = ".*\\bthisIsNoValidGender\\b.*";
		assertRejects(xml, expectedMsg);
	}

	public void testRejectsMissingAttribute() throws Exception {
		String xml = xmlHeader + "<Separator word='and'/>\n" + "	<Pronoun word='foo'/>\n" + // gender='foo'
				// missing
				"</vocabulary>\n";
		assertRejects(xml, ".*\\bgender\\b.*");
	}

	public void testRejectsDuplicatedAttribute() throws Exception {
		String xml = xmlHeader + "<Separator word='and'/>\n" + "	<Pronoun word='foo' word='bar' gneder='male' number='singular'/>\n" + "</vocabulary>\n";
		assertRejects(xml, ".*\\bword\\b.+\\balready specified\\b.*");
	}

	public void testEnsuresUniqueWords() throws Exception {
		String xml = xmlHeader + "<Separator word='youDoKnowThis'/>\n" + "	<Pronoun word='youDoKnowThis' gender='male' number='singular'/>\n" + "</vocabulary>\n";
		assertRejects(xml, ".*\\byouDoKnowThis\\b.*");
	}

	public void testSchemaAllowsVocabularyWithoutVerb() throws Exception {
		createParserFrom(xmlHeader + "	<Separator word='and'/>" + "	<Preposition word='to' code='TO' provideNoun='never'/>\n" + "	<Preposition word='with' code='WITH' provideNoun='never'/>\n" + "</vocabulary>");
		// If you we get here, a Parser was created without a Verb.
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
