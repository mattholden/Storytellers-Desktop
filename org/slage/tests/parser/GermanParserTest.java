// $Id: GermanParserTest.java,v 1.1 2005/11/04 07:51:52 kevlar2 Exp $

package org.slage.tests.parser;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.slage.parser.AdjectiveNounDescription;
import org.slage.parser.DefaultBunchOfItems;
import org.slage.parser.Description;
import org.slage.parser.GermanParser;
import org.slage.parser.Parser;
import org.slage.parser.ParserException;
import org.slage.parser.Synonym;
import org.slage.parser.WordSet;

/**
 * @author <a href="mailto:robert.wenner@gmx.de?subject=GermanParserTest">Robert
 *         Wenner</a>
 */
public class GermanParserTest
		extends ParserTestCase {
	private Parser parser;
	private Description box;
	private DefaultBunchOfItems here;

	protected void setUp() throws Exception {
		Synonym femaleBox = new Synonym(Description.FEMALE, Description.SINGULAR, new WordSet(new String[] { "die", "eine", "ne" }), new WordSet(new String[] { "hoelzerne", "haessliche" }), new WordSet(new String[] { "Kiste", "Schachtel" }));
		Synonym maleBox = new Synonym(Description.MALE, Description.SINGULAR, new WordSet(new String[] { "der", "ein", "nen" }), new WordSet(new String[] { "hoelzerner", "haesslicher" }), new WordSet(new String[] { "Kasten" }));
		box = new AdjectiveNounDescription("Kiste", new Synonym[] { femaleBox, maleBox });
		here = new DefaultBunchOfItems();
		here.placeItem(box);
		String vocabulary = "<?xml version='1.0' standalone='no'?>\n" + "<p:vocabulary\n" + "	xmlns:xsi='http://www.w3.org/2001/XMLSchema-instance'\n" + "	xmlns:p='http://org.slage.parser'\n" + "	xsi:schemaLocation='http://org.slage.parser xml/vocabulary.xsd'\n"
				+ "	parser='org.slage.parser.GermanParser'>\n" + "	<Separator word='und'/>\n" + "	<Preposition word='in' code='IN' provideNoun='never'/>\n" + "	<Preposition word='hinein' code='HINEIN' provideNoun='always'/>\n"
				+ "	<Preposition word='rein' code='HINEIN' provideNoun='always'/>\n" + "	<Preposition word='mit' code='WITH' provideNoun='never'/>\n" + "	<Preposition word='auf' code='ON' provideNoun='never'/>\n" + "	<Preposition word='an' code='ON' provideNoun='never'/>\n"
				+ "	<Preposition word='ein' code='ON' provideNoun='never'/>\n" + "	<Pronoun word='ihm' gender='male' number='singular'/>\n" + "	<Pronoun word='ihn' gender='male' number='singular'/>\n" + "	<Pronoun word='Ihm' gender='neutral' number='singular'/>\n"
				+ "	<Pronoun word='sie' gender='female' number='singular'/>\n" + "</p:vocabulary>";
		InputStream in = new ByteArrayInputStream(vocabulary.getBytes());
		parser = Parser.createFromXML(in);
		parser.setGame(new DummyGame(here, new DefaultBunchOfItems()));
	}

	public void testParseSimpleInput() throws ParserException {
		assertTrue("Wrong parser type", parser instanceof GermanParser);
		assertParsed(new Object[] { "SCHAU" }, parser.parse("schau"));
	}

	public void testSubstitutesPronoun() throws ParserException {
		assertParsed(new Object[] { "SCHAU", box }, parser.parse("schau kiste und nimm sie"));
		assertParsed(new Object[] { "NIMM", box }, parser.parse());
	}

	public void testParserRefersToLastNounForAmbiguousPronoun() throws ParserException {
		// The pronoun "ihm" is ambiguous, it can be male or neutral.
		// The Parser should pick the noun that was used last.
		Synonym bookDesc = new Synonym(Description.NEUTRAL, Description.SINGULAR, null, null, new WordSet("Buch"));
		Description book = new AdjectiveNounDescription("Buch", bookDesc);
		here.placeItem(book);
		Synonym manDesc = new Synonym(Description.MALE, Description.SINGULAR, null, null, new WordSet("Mann"));
		Description man = new AdjectiveNounDescription("Mann", manDesc);
		here.placeItem(man);

		parser.parse("schau buch");
		parser.parse("schau mann");
		assertParsed(new Object[] { "REDE", "WITH", man }, parser.parse("rede mit ihm"));

		parser.parse("schau buch");
		assertParsed(new Object[] { "REDE", "WITH", book }, parser.parse("rede mit ihm"));

		assertParsed(new Object[] { "SCHAU", man }, parser.parse("schau ihn"));
	}

	public void testConvertsUmlauts() throws ParserException {
		String input = "dr\u00FCcke h\u00E4\u00DFliche h\u00F6lzerne kiste";
		assertParsed(new Object[] { "DRUECKE", box }, parser.parse(input));
	}

	public void testDoesAlwaysProvideNounForCertainPrepositions() throws ParserException {
		assertParsed(new Object[] { "SCHAU", box }, parser.parse("schau kiste und schau hinein in und"));
		assertParsed(new Object[] { "SCHAU", "IN", box, "IN" }, parser.parse());
	}

	public void testGuckInKisteHinein() throws ParserException {
		// Combinations like "in ... hinein" is duplication, but may be valid.
		// The Parser should just discard the duplicate pronoun (here "hinein").
		assertParsed(new Object[] { "SCHAU", "IN", box }, parser.parse("schau in die kiste hinein"));
	}

	public void testUsesCorrectGender() throws ParserException {
		// Articles differ based on their noun's gender, and adjectives have
		// different endings based on the noun's gender. An item may have
		// synonyms with different genders.
		// Assume a DefaultDescription kiste (box, in German female, thus
		// article "die") with adjectives "hoelzerne" (wooden, female version of
		// "hoelzern"), and noun "kiste". Now we want the synonym "kasten" to be
		// used, too. "kasten" is male, and would have article "der" and adjective
		// "hoelzerner". While one could live with grammatically bad "nimm die
		// hoelzerne kasten", the input "kasten" would be wrongly stored as last
		// female noun, rather than as last male noun, making pronoun handling
		// very confusing.
		assertParsed(new Object[] { "SCHAU", box }, parser.parse("schau kasten und nimm ihn"));
		assertParsed(new Object[] { "NIMM", box }, parser.parse());
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
