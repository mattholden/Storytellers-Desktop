// $Id: MultipleInputTest.java,v 1.1 2005/11/04 07:51:52 kevlar2 Exp $

package org.slage.tests.parser;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.slage.parser.AdjectiveNounDescription;
import org.slage.parser.AmbiguousInputException;
import org.slage.parser.DefaultBunchOfItems;
import org.slage.parser.Description;
import org.slage.parser.MissingWordException;
import org.slage.parser.Parser;
import org.slage.parser.ParserException;
import org.slage.parser.Synonym;
import org.slage.parser.WordRepeatedException;
import org.slage.parser.WordSet;

/**
 * @author <a href="mailto:robert.wenner@gmx.de">Robert Wenner</a>
 */
public class MultipleInputTest
		extends ParserTestCase {
	private Parser parser;
	private DefaultBunchOfItems room;
	private Description rock;
	private Description geyser;

	protected void setUp() throws Exception {
		String xml = "<?xml version='1.0' standalone='no'?>\n" + "<parser:vocabulary\n" + "	xmlns:xsi='http://www.w3.org/2001/XMLSchema-instance'\n" + "	xmlns:parser='http://org.slage.parser'\n" + "	xsi:schemaLocation='http://org.slage.parser xml/vocabulary.xsd'\n"
				+ "	parser='org.slage.parser.Parser'>\n" + "	<Separator word='and'/>\n" + "	<Separator word='then'/>\n" + "	<Preposition word='at' code='AT' provideNoun='never'/>" + "	<Preposition word='inside' code='IN' provideNoun='unlessGiven'/>"
				+ "	<Preposition word='in' code='IN' provideNoun='never'/>" + "</parser:vocabulary>";
		InputStream in = new ByteArrayInputStream(xml.getBytes());
		parser = Parser.createFromXML(in);
		room = new DefaultBunchOfItems();
		Synonym rockDescription = new Synonym(Description.NEUTRAL, Description.SINGULAR, new WordSet("the"), new WordSet("big"), new WordSet(new String[] { "rock", "piece of stone" }));
		rock = new AdjectiveNounDescription("rock", rockDescription);
		Synonym geyserDesc = new Synonym(Description.NEUTRAL, Description.SINGULAR, new WordSet("the"), new WordSet(new String[] { "hot", "big" }), new WordSet("geyser"));
		geyser = new AdjectiveNounDescription("geyser", geyserDesc);
		room.placeItem(rock);
		room.placeItem(geyser);
		parser.setGame(new DummyGame(room, new DefaultBunchOfItems()));
	}

	public void testSeparatedBySingleCharacter() throws ParserException {
		assertParsed(new Object[] { "PUT", rock }, parser.parse("put rock.put geyser"));
		assertTrue("Lost second command", parser.hasInput());
		assertParsed(new Object[] { "PUT", geyser }, parser.parse());
	}

	public void testSeparatedByWord() throws ParserException {
		assertParsed(new Object[] { "PUT", rock }, parser.parse("put rock then put geyser"));
		assertParsed(new Object[] { "PUT", geyser }, parser.parse());
	}

	public void testAllowsTwoCommandSeparatingWords() throws ParserException {
		assertParsed(new Object[] { "PUT", rock }, parser.parse("put rock and then put geyser"));
		assertParsed(new Object[] {}, parser.parse());
		assertParsed(new Object[] { "PUT", geyser }, parser.parse());
		assertFalse("Shouldn't have input anymore", parser.hasInput());
	}

	public void testIgnoresEmptyLeadingSentenceCharacter() throws ParserException {
		assertParsed(new Object[] {}, parser.parse(".put rock"));
		assertParsed(new Object[] { "PUT", rock }, parser.parse());
	}

	public void testIgnoresEmptyLeadingSentenceWord() throws ParserException {
		assertParsed(new Object[] {}, parser.parse("and put rock"));
		assertParsed(new Object[] { "PUT", rock }, parser.parse());
	}

	public void testIgnoresEmptyTrailingSentence() throws ParserException {
		assertParsed(new Object[] { "PUT", rock }, parser.parse("put rock."));
		assertFalse("Got data for second command from somewhere", parser.hasInput());
	}

	public void testIgnoresEmptySentencesInBetween() throws ParserException {
		assertParsed(new Object[] { "PUT", rock }, parser.parse("put rock... put geyser"));
		assertParsed(new Object[] { "PUT", geyser }, parser.parse());
	}

	public void testParseContinueAfterWordRepeatedAndOtherCommand() throws ParserException {
		parser.parse("look and put rock geyser geyser");
		try {
			parser.parse();
			fail("Accepted duplicated words");
		} catch (WordRepeatedException expected) { /* Ok. */
		}
		assertParsed(new Object[] { "PUT", rock, geyser }, parser.parse());
	}

	public void testThreeInOne() throws ParserException {
		parser.parse("Look; look; look");
		parser.parse();
		assertParsed(new Object[] { "LOOK" }, parser.parse());
	}

	public void testAndInDescription() throws ParserException {
		Synonym dnbDesc = new Synonym(Description.NEUTRAL, Description.SINGULAR, null, null, new WordSet(new String[] { "cd", "drum and base cd" }));
		Description dnb = new AdjectiveNounDescription("drum and base cd", dnbDesc);
		room.placeItem(dnb);
		assertParsed(new Object[] { "LOOK", dnb }, parser.parse("look drum and base cd"));
	}

	public void testProvideLastVerbIfNoneInSecondSentence() throws ParserException {
		assertParsed(new Object[] { "LOOK", geyser }, parser.parse("look geyser and rock"));
		assertParsed(new Object[] { "LOOK", rock }, parser.parse());
	}

	public void testProvidesLastNounForPrepositionIfMissing() throws ParserException {
		assertParsed(new Object[] { "LOOK", geyser }, parser.parse("look geyser AND look inside"));
		assertParsed(new Object[] { "LOOK", "IN", geyser }, parser.parse());
	}

	public void testProvideLastNounNoLastNoun() throws ParserException {
		try {
			parser.parse("look inside");
			fail("Accepted inside without noun");
		} catch (MissingWordException expected) { /* Ok. */
		}
	}

	public void testProvidesLastNounOnlyInNextCommand() throws ParserException {
		parser.parse("look geyser and look and look inside");
		parser.parse();
		try {
			parser.parse().toArray();
			fail("Has provided noun");
		} catch (MissingWordException expected) { /* Ok. */
		}
	}

	public void testProvidesVerbAndNoun() throws ParserException {
		assertParsed(new Object[] { "LOOK", "AT", geyser }, parser.parse("look at geyser and inside"));
		assertParsed(new Object[] { "LOOK", "IN", geyser }, parser.parse());
	}

	public void testProvidesNounAtSeparator() throws ParserException {
		assertParsed(new Object[] { "LOOK", geyser }, parser.parse("look geyser and look inside and do whatever"));
		assertParsed(new Object[] { "LOOK", "IN", geyser }, parser.parse());
	}

	public void testProvidesNounWithWordRepeated() throws ParserException {
		parser.parse("look geyser and look look inside");
		try {
			parser.parse();
		} catch (WordRepeatedException expected) { /* Ok. */
		}
		assertParsed(new Object[] { "LOOK", "IN", geyser }, parser.parse());
	}

	public void testProvidesVerbAfterAmbiguousCommand() throws ParserException {
		// You type "look rock", but there are two rocks, so the game asks "which
		// one?"
		// and you just say "that one".
		Synonym otherRockDescription = new Synonym(Description.NEUTRAL, Description.SINGULAR, new WordSet("the"), new WordSet("other"), new WordSet(new String[] { "rock", "piece of stone" }));
		Description otherRock = new AdjectiveNounDescription("other rock", otherRockDescription);
		room.placeItem(otherRock);
		try {
			parser.parse("look rock");
			fail("Should reject ambiguous input");
		} catch (AmbiguousInputException expected) {
			// Ok.
		}
		assertParsed(new Object[] { "LOOK", otherRock }, parser.parse("other rock"));
	}

	public void testProvidesLastInputAfterAmbiguousInput() throws ParserException {
		// You type "put rock geyser", but there are two rocks, so the game asks
		// "which one?" and you just say "that one" to get "put that rock geyser".
		// Neat, eh?
		Synonym otherRockDescription = new Synonym(Description.NEUTRAL, Description.SINGULAR, new WordSet("the"), new WordSet("other"), new WordSet(new String[] { "rock", "piece of stone" }));
		Description otherRock = new AdjectiveNounDescription("other rock", otherRockDescription);
		room.placeItem(otherRock);
		try {
			parser.parse("put rock geyser");
			fail("Should reject ambiguous input");
		} catch (AmbiguousInputException expected) {
			// Ok.
		}
		assertParsed(new Object[] { "PUT", otherRock, geyser }, parser.parse("other rock"));
	}

	public void testIgnoresSeparatorOnly() throws ParserException {
		assertTrue("Wrong text parsed", parser.parse(";").isEmpty());
		assertFalse("Should have no more input", parser.hasInput());
	}
}
/*******************************************************************************
 * BEGIN LICENSE BLOCK **** Version: MPL 1.1/GPL 2.0/LGPL 2.1
 * 
 * The contents of this file are subject to the Mozilla Public License Version
 * 1.1 (the "License"); you may not use this file except in compliance with the
 * License. You may obtain a copy of the License at
 * httparser://www.mozilla.org/MPL/
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
