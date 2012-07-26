// $Id: PronounSubstitutionTest.java,v 1.1 2005/11/04 07:51:52 kevlar2 Exp $

package org.slage.tests.parser;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.slage.parser.AdjectiveNounDescription;
import org.slage.parser.AmbiguousPronounException;
import org.slage.parser.DefaultBunchOfItems;
import org.slage.parser.Description;
import org.slage.parser.Parser;
import org.slage.parser.ParserException;
import org.slage.parser.Synonym;
import org.slage.parser.WordSet;

/**
 * @author <a href="mailto:robert.wenner@gmx.de">Robert Wenner</a>
 */
public class PronounSubstitutionTest
		extends ParserTestCase {
	private Parser parser;
	private DefaultBunchOfItems room;
	private Description man;
	private Description woman;
	private Description rock;
	private Description pebbles;

	protected void setUp() throws Exception {
		String xml = "<?xml version='1.0' standalone='no'?>\n" + "<p:vocabulary\n" + "	xmlns:xsi='http://www.w3.org/2001/XMLSchema-instance'\n" + "	xmlns:p='http://org.slage.parser'\n" + "	xsi:schemaLocation='http://org.slage.parser xml/vocabulary.xsd'\n"
				+ "	parser='org.slage.parser.Parser'>\n" + "	<Separator word='and'/>\n" + "	<Pronoun word='it' gender='neutral' number='singular'/>\n" + "	<Pronoun word='him' gender='male' number=\'singular\'/>\n" + "	<Pronoun word='her' gender='female' number=\'singular\'/>\n"
				+ "	<Pronoun word='them' gender='neutral' number='plural'/>\n" + "	<Pronoun word='Them' gender='male' number='plural'/>\n" + "	<Pronoun word='tHem' gender='female' number='plural'/>\n" + "	<Preposition word='at' code='AT' provideNoun='never'/>" + "</p:vocabulary>";
		InputStream in = new ByteArrayInputStream(xml.getBytes());
		parser = Parser.createFromXML(in);
		room = new DefaultBunchOfItems();
		Synonym maleDesc = new Synonym(Description.MALE, Description.SINGULAR, null, null, new WordSet("man"));
		man = new AdjectiveNounDescription("man", maleDesc);
		room.placeItem(man);
		Synonym femaleDesc = new Synonym(Description.FEMALE, Description.SINGULAR, null, null, new WordSet("woman"));
		woman = new AdjectiveNounDescription("woman", femaleDesc);
		room.placeItem(woman);
		Synonym neutralDesc = new Synonym(Description.NEUTRAL, Description.SINGULAR, null, null, new WordSet("rock"));
		rock = new AdjectiveNounDescription("rock", neutralDesc);
		room.placeItem(rock);
		Synonym pluralDesc = new Synonym(Description.NEUTRAL, Description.PLURAL, null, null, new WordSet("pebbles"));
		pebbles = new AdjectiveNounDescription("pebbles", pluralDesc);
		room.placeItem(pebbles);
		parser.setGame(new DummyGame(room, new DefaultBunchOfItems()));
	}

	public void testRejectsItIfNoStuffSeen() throws ParserException {
		try {
			parser.parse("look IT");
			fail("Accepted 'it' without having seen a neutral noun");
		} catch (AmbiguousPronounException expected) { /* Ok. */
		}
	}

	public void testSubstitutesPronounsgenderAware() throws ParserException {
		Object[] expected = new Object[] { "LOOK", rock, woman, man };
		assertParsed(expected, parser.parse("look rock woman man"));
		assertParsed(expected, parser.parse("look IT Her HIM"));
	}

	public void testSubstituteForPlural() throws ParserException {
		assertParsed(new Object[] { "LOOK", pebbles }, parser.parse("look pebbles"));
		assertParsed(new Object[] { "LOOK", pebbles }, parser.parse("look them"));
	}

	public void testSubsitutesThemGenderDependentMen() throws ParserException {
		Synonym maleDesc = new Synonym(Description.MALE, Description.PLURAL, null, null, new WordSet("men"));
		Description men = new AdjectiveNounDescription("men", maleDesc);
		room.placeItem(men);
		parser.parse("look pebbles");
		parser.parse("look men");
		assertParsed(new Object[] { "LOOK", men }, parser.parse("look them"));
	}

	public void testSubsitutesThemGenderDependentWomen() throws ParserException {
		Synonym maleDesc = new Synonym(Description.MALE, Description.PLURAL, null, null, new WordSet("women"));
		Description women = new AdjectiveNounDescription("women", maleDesc);
		room.placeItem(women);
		parser.parse("look pebbles");
		parser.parse("look women");
		assertParsed(new Object[] { "LOOK", women }, parser.parse("look them"));
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
