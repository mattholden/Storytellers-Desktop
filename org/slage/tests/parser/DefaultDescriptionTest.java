/*
 * $Id: DefaultDescriptionTest.java,v 1.1 2005/11/04 07:51:52 kevlar2 Exp $
 */
package org.slage.tests.parser;

import junit.framework.TestCase;

import org.slage.parser.AdjectiveNounDescription;
import org.slage.parser.BestMatch;
import org.slage.parser.Description;
import org.slage.parser.Synonym;
import org.slage.parser.Tokenizer;
import org.slage.parser.WordRepeatedException;
import org.slage.parser.WordSet;

/**
 * @author <a href="mailto:robert.wenner@gmx.de">Robert Wenner</a>
 */
public class DefaultDescriptionTest
		extends TestCase {
	private Synonym wineDesc;
	private AdjectiveNounDescription wine;

	protected void setUp() {
		wineDesc = new Synonym(Description.NEUTRAL, Description.SINGULAR, new WordSet(new String[] { "a", "the" }), new WordSet(new String[] { "crystal clear", "closed", "empty" }), new WordSet(new String[] { "bottle", "bottle of wine" }));
		wine = new AdjectiveNounDescription("bottle of wine", wineDesc);
	}

	protected int scoreFor(Description what, Tokenizer input) throws WordRepeatedException {
		BestMatch bestFit = new BestMatch();
		input.mark();
		what.addIfScores(bestFit, input);
		input.reset();
		input.goAhead(bestFit.getWordCount());
		return bestFit.getScore();
	}

	public void testDoesNotCreateWithNullShortDescription() {
		try {
			new AdjectiveNounDescription(null, wineDesc);
			fail("Accepted null description");
		} catch (IllegalArgumentException expected) { /* Ok. */
		}
	}

	public void testDoesNotCreateWithBlankShortDescription() {
		try {
			new AdjectiveNounDescription("   ", wineDesc);
			fail("Accepted empty description");
		} catch (IllegalArgumentException expected) { /* Ok. */
		}
	}

	public void testDoesNotCreateStuffWithEmptyShortDescription() {
		try {
			new AdjectiveNounDescription("", wineDesc);
			fail("Accepted empty description");
		} catch (IllegalArgumentException expected) { /* Ok. */
		}
	}

	public void testDoesNotAcceptNullFullDescription() {
		try {
			new AdjectiveNounDescription("blah", (Synonym) null);
			fail("Accepted null description");
		} catch (IllegalArgumentException expected) { /* Ok. */
		}
	}

	public void testStuffDescription() {
		assertEquals("bottle of wine", wine.toString());
	}

	public void testScoreForNoun() throws WordRepeatedException {
		assertEquals(Description.SCORE_COMPLETE + Description.SCORE_NOUN, scoreFor(wine, new Tokenizer("", "bottle")));
	}

	public void testScoreForArticleNoun() throws WordRepeatedException {
		assertEquals(Description.SCORE_COMPLETE + Description.SCORE_ARTICLE + Description.SCORE_NOUN, scoreFor(wine, new Tokenizer("", "the bottle")));
	}

	public void testScoreForAdjectiveNoun() throws WordRepeatedException {
		assertEquals(Description.SCORE_COMPLETE + Description.SCORE_ADJECTIVE + Description.SCORE_NOUN, scoreFor(wine, new Tokenizer("", "closed bottle")));
	}

	public void testScoreForNounGroup() throws WordRepeatedException {
		assertEquals(Description.SCORE_COMPLETE + Description.SCORE_NOUN * 3, scoreFor(wine, new Tokenizer("", "bottle of wine")));
	}

	public void testScoreForAdjectiveGroupNounGroup() throws WordRepeatedException {
		Tokenizer input = new Tokenizer("", "crystal clear bottle of wine");
		assertEquals(Description.SCORE_COMPLETE + Description.SCORE_ADJECTIVE * 2 + Description.SCORE_NOUN * 3, scoreFor(wine, input));
	}

	public void testScoreForAdjectiveGroupAdjectiveNoun() throws WordRepeatedException {
		Tokenizer input = new Tokenizer("", "closed crystal clear bottle of wine");
		assertEquals(Description.SCORE_COMPLETE + Description.SCORE_ADJECTIVE * 3 + Description.SCORE_NOUN * 3, scoreFor(wine, input));
	}

	public void testScoreForAdjectivesInAnyOrder() throws WordRepeatedException {
		assertEquals(scoreFor(wine, new Tokenizer("", "closed")), scoreFor(wine, new Tokenizer("", "empty")));
	}

	public void testScoreForAdjectiveBehindNoun() throws WordRepeatedException {
		Tokenizer input = new Tokenizer("", "bottle closed");
		assertEquals(Description.SCORE_COMPLETE + Description.SCORE_NOUN, scoreFor(wine, input));
		assertEquals("closed", input.next());
	}

	public void testScoreForNounBehindNoun() throws WordRepeatedException {
		Tokenizer input = new Tokenizer("", "bottle of wine bottle");
		assertEquals(Description.SCORE_COMPLETE + Description.SCORE_NOUN * 3, scoreFor(wine, input));
		assertEquals("bottle", input.next());
	}

	public void testDoesNotAcceptArticleAfterFirstPosition() throws WordRepeatedException {
		assertEquals(Description.SCORE_ADJECTIVE, scoreFor(wine, new Tokenizer("", "closed the bottle")));
	}

	public void testStopsAtFirstUnknownWord() throws WordRepeatedException {
		Tokenizer input = new Tokenizer("", "closed tin");
		assertEquals(Description.SCORE_ADJECTIVE, scoreFor(wine, input));
		assertEquals("Wrong stuff left in input", "tin", input.next());
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
