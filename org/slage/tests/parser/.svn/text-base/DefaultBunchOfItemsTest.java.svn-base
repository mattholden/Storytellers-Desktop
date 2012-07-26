// $Id: DefaultBunchOfItemsTest.java,v 1.1 2005/11/04 07:51:52 kevlar2 Exp $

package org.slage.tests.parser;

import junit.framework.TestCase;

import org.slage.parser.AdjectiveNounDescription;
import org.slage.parser.BestMatch;
import org.slage.parser.BunchOfItems;
import org.slage.parser.DefaultBunchOfItems;
import org.slage.parser.Description;
import org.slage.parser.Synonym;
import org.slage.parser.Tokenizer;
import org.slage.parser.WordRepeatedException;
import org.slage.parser.WordSet;

/**
 * @author <a href="mailto:robert.wenner@gmx.de">Robert Wenner</a>
 */
public class DefaultBunchOfItemsTest
		extends TestCase {
	private class MockStuff
			extends AdjectiveNounDescription {
		public int askedFit;

		public MockStuff(String description, Synonym full) {
			super(description, full);
			askedFit = 0;
		}

		public int scoreFor(Tokenizer input) throws WordRepeatedException {
			BestMatch bestFit = new BestMatch();
			addIfScores(bestFit, input);
			return bestFit.getScore();
		}

		public void addIfScores(BestMatch bestFit, Tokenizer input, Synonym synonym) throws WordRepeatedException {
			askedFit++;
			super.addIfScores(bestFit, input, synonym);
		}
	}

	private DefaultBunchOfItems roomWithCarAndBox;
	private MockStuff box;
	private MockStuff car;

	protected void setUp() {
		box = new MockStuff("BOX", new Synonym(Description.NEUTRAL, Description.SINGULAR, null, new WordSet("green"), new WordSet("box")));
		car = new MockStuff("CAR", new Synonym(Description.NEUTRAL, Description.SINGULAR, null, new WordSet("green"), new WordSet("car")));
		roomWithCarAndBox = new DefaultBunchOfItems();
		roomWithCarAndBox.placeItem(box);
		roomWithCarAndBox.placeItem(car);
	}

	public void testNoSuchObjectInEmptyBundle() throws WordRepeatedException {
		BunchOfItems emptyRoom = new DefaultBunchOfItems();
		BestMatch bestFit = emptyRoom.getBestMatch(new Tokenizer("", "whatever"));
		assertTrue("Should not find matching items in empty room", bestFit.size() == 0);
	}

	public void testAsksContentsForBestFit() throws WordRepeatedException {
		roomWithCarAndBox.getBestMatch(new Tokenizer("", "box"));
		assertEquals("Asked box wrong times", 1, box.askedFit);
		assertEquals("Asked car wrong times", 1, car.askedFit);
	}

	public void testFindsBestFittingStuff() throws WordRepeatedException {
		BestMatch bestFitting = roomWithCarAndBox.getBestMatch(new Tokenizer("", "box"));
		assertSame("Wrong best fitting DefaultDescription picked", bestFitting.asCollection().toArray()[0], box);
	}

	public void testReturnsSetOfStuffsIfAmbiguous() throws WordRepeatedException {
		BestMatch matching = roomWithCarAndBox.getBestMatch(new Tokenizer("", "green"));
		assertEquals("Should have two items", 2, matching.size());
	}

	public void testFindsSimilarItemsInBundle() throws WordRepeatedException {
		Description wine = new AdjectiveNounDescription("wine", new Synonym(Description.NEUTRAL, Description.SINGULAR, null, null, new WordSet(new String[] { "bottle", "bottle of wine" })));
		Description whiskey = new AdjectiveNounDescription("whiskey", new Synonym(Description.NEUTRAL, Description.SINGULAR, null, null, new WordSet(new String[] { "bottle", "bottle of whiskey" })));
		DefaultBunchOfItems room = new DefaultBunchOfItems();
		room.placeItem(wine);
		room.placeItem(whiskey);
		assertFalse("Wine not found", room.getBestMatch(new Tokenizer("", "bottle of wine")).size() == 0);
		assertFalse("Whiskey not found", room.getBestMatch(new Tokenizer("", "bottle of whiskey")).size() == 0);
	}

	public void testDoesNotAllowSameStuffTwice() {
		Description otherCar = new MockStuff("CAR", new Synonym(Description.NEUTRAL, Description.SINGULAR, null, new WordSet("green"), new WordSet("car")));
		try {
			roomWithCarAndBox.placeItem(otherCar);
			fail("Accepted same stuff twice");
		} catch (IllegalStateException expected) { /* Ok. */
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
