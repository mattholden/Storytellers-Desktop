// $Id: SimpleParsingTest.java,v 1.1 2005/11/04 07:51:52 kevlar2 Exp $

package org.slage.tests.parser;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.slage.parser.AdjectiveNounDescription;
import org.slage.parser.DefaultBunchOfItems;
import org.slage.parser.Description;
import org.slage.parser.NoSuchStuffException;
import org.slage.parser.Parser;
import org.slage.parser.ParserException;
import org.slage.parser.Synonym;
import org.slage.parser.WordRepeatedException;
import org.slage.parser.WordSet;

/**
 * @author <a href="mailto:robert.wenner@gmx.de">Robert Wenner</a>
 */
public class SimpleParsingTest
		extends ParserTestCase {
	private Parser parser;
	private DefaultBunchOfItems room;
	private Description rock;
	private Description geyser;

	protected void setUp() throws Exception {
		String xml = "<?xml version='1.0'?>\n" + "<p:vocabulary\n" + "	xmlns:xsi='http://www.w3.org/2001/XMLSchema-instance'\n" + "	xmlns:p='http://org.slage.parser'\n" + "	xsi:schemaLocation='http://org.slage.parser xml/vocabulary.xsd'\n" + "	parser='org.slage.parser.Parser'>\n"
				+ "	<Separator word='and'/>\n" + "	<Preposition word='at' code='AT' provideNoun='never'/>\n" + "	<Preposition word='with' code='WITH' provideNoun='never'/>\n" + "	<Preposition word='on' code='ON' provideNoun='never'/>\n"
				+ "	<Preposition word='to' code='TO' provideNoun='never'/>\n" + " 	<Preposition word='up' code='UP' provideNoun='unlessGiven'/>\n" + "</p:vocabulary>";
		InputStream in = new ByteArrayInputStream(xml.getBytes());
		parser = Parser.createFromXML(in);
		room = new DefaultBunchOfItems();
		Synonym rockDesc = new Synonym(Description.NEUTRAL, Description.SINGULAR, new WordSet("the"), new WordSet("big"), new WordSet(new String[] { "rock", "piece of stone" }));
		rock = new AdjectiveNounDescription("rock", rockDesc);
		Synonym geyserDesc = new Synonym(Description.NEUTRAL, Description.SINGULAR, new WordSet("the"), new WordSet(new String[] { "hot", "big" }), new WordSet("geyser"));
		geyser = new AdjectiveNounDescription("geyser", geyserDesc);
		room.placeItem(rock);
		room.placeItem(geyser);
		parser.setGame(new DummyGame(room, new DefaultBunchOfItems()));
	}

	public void testTranslatesWordCaseInsensitive() throws ParserException {
		// This is the first test to feed XML vocabulary to the Parser. If you see
		// errors
		// in all tests after this one (a JDOMException because Crimson does not
		// recognize the warn-on-duplicate-attdef feature for SAX), you should make
		// sure you have a usable XML Parser like Apache Xerxes on the project.
		// Add it in Eclipse's project properties dialog: Java Build Path ->
		// Libraries tab,
		// then Add External JARs, choose your Eclipse installation directory, then
		// pick both jar files from the plugins/org.apache.xerxes_version_number/
		// (one
		// after the other).
		assertParsed("Wrong meaning of 'look'", new Object[] { "LOOK" }, parser.parse("Look"));
	}

	public void testFailsOnUnknownNoun() throws ParserException {
		try {
			parser.parse("look car");
			fail("Accepted unknown noun");
		} catch (NoSuchStuffException expected) {
			// Ok
		}
	}

	public void testInputWithWhiteSpace() throws ParserException {
		assertEquals("Did not ignore white space", parser.parse("    look "), parser.parse("look"));
	}

	public void testParsesEmptyText() throws ParserException {
		assertTrue("Did not ignore empty input.", parser.parse("").isEmpty());
	}

	public void testParsesBlanksOnlyText() throws ParserException {
		assertTrue("Did not ignore blank input.", parser.parse("     ").isEmpty());
	}

	public void testParseVerbNounNoun() throws ParserException {
		assertParsed(new Object[] { "PUT", rock, geyser }, parser.parse("put rock geyser"));
	}

	public void testParseVerbArticleAdjectiveNoun() throws ParserException {
		assertParsed(new Object[] { "LOOK", rock }, parser.parse("look the BIG rock"));
	}

	public void testParseVerbNounPrepositionNoun() throws ParserException {
		assertParsed(new Object[] { "PUT", rock, "ON", geyser }, parser.parse("put rock ON geyser"));
	}

	public void testParseCompoundNoun() throws ParserException {
		assertParsed(new Object[] { "LOOK", rock }, parser.parse("look Piece Of Stone"));
	}

	public void testParseVerbTwoTimesArticleAdjectiveNoun() throws ParserException {
		assertParsed(new Object[] { "PUT", rock, "ON", geyser }, parser.parse("put the big rock on the big hot geyser"));
	}

	public void testParseUnambiguousAdjectiveNoNounFails() throws ParserException {
		try {
			parser.parse("put hot on rock");
			fail("Accepted adjective without noun.");
		} catch (NoSuchStuffException expected) {
			assertTrue("Did not find input in message", expected.getMessage().indexOf("hot") >= 0);
		}
	}

	public void testDetectsDuplicates() throws ParserException {
		try {
			parser.parse("put the the rock");
			fail("Accepted duplicate words");
		} catch (WordRepeatedException expected) {
			assertTrue("Did not find duplicated word in message", expected.getMessage().indexOf("'the the'") >= 0);
		}
	}

	public void testContinueAfterWordRepeated() throws ParserException {
		try {
			parser.parse("put rock on geyser geyser");
			fail("Word repeated");
		} catch (WordRepeatedException expected) {
			// Ok.
		}
		assertParsed(new Object[] { "PUT", rock, "ON", geyser }, parser.parse());
	}

	public void testDiscardingInput() throws ParserException {
		parser.parse("look and look");
		parser.discardInput();
		assertFalse("Should have no more input", parser.hasInput());
	}

	public void testParserExceptionDoesntMessWithRoom() throws ParserException {
		try {
			parser.parse("look foo");
			fail("Accepted unknown word 'foo'");
		} catch (NoSuchStuffException expected) {
			// Ok.
		}
		parser.discardInput();
		parser.parse("look rock");
		// This test throws if it fails. It may look too simple and not really
		// testing
		// anything, but it showed a bug in BunchOfItems not copying its contents
		// but rather handing a reference, and Parser modifying it to produce a nice
		// error message.
	}

	public void testNoSuchStuffMessageHasOnlyNounParts() throws ParserException {
		try {
			parser.parse("look hot green big geyser and put rock on hot geyser");
			fail("Accepted bad noun phrase");
		} catch (NoSuchStuffException expected) {
			String message = expected.getMessage();
			assertTrue("Should not include input after mismatching word; " + message, message.indexOf("green") == -1);
			assertTrue("Should provide words that were known; " + message, message.indexOf("hot") != -1);
		}
	}

	public void testValuesCompleteNounOverHalfPhrase() throws ParserException {
		Synonym cdDesc = new Synonym(Description.NEUTRAL, Description.SINGULAR, null, null, new WordSet(new String[] { "rock cd", "cd" }));
		Description cd = new AdjectiveNounDescription("cd", cdDesc);
		room.placeItem(cd);
		// This may throw if the parser considers the input ambiguous (though it
		// isn't)
		assertParsed(new Object[] { "LOOK", rock }, parser.parse("look rock"));
	}

	public void testPicksPhraseInsteadOfNounIfPossible() throws ParserException {
		Synonym cdDesc = new Synonym(Description.NEUTRAL, Description.SINGULAR, null, null, new WordSet(new String[] { "rock cd", "cd" }));
		Description cd = new AdjectiveNounDescription("cd", cdDesc);
		room.placeItem(cd);
		assertParsed(new Object[] { "LOOK", cd }, parser.parse("look rock cd"));
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
