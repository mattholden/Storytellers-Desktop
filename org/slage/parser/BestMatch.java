/*
 * $Id: BestMatch.java,v 1.1 2005/11/04 07:52:19 kevlar2 Exp $
 */
package org.slage.parser;

import java.util.Collection;
import java.util.Vector;

/**
 * Bundles the collection of best matching Description with information on it.
 * The additional information includes the gender of the matching Description,
 * the score it achieved, and the textual description that yielded the score.
 * 
 * @author <a href="mailto:robert.wenner@gmx.de">Robert Wenner</a>
 */
public class BestMatch {
	// This class seems to be two things in one: the best matches list and
	// a single best match. Maybe a singleBestMatch wants to be extracted
	// from this class?
	private Collection matchingStuff;
	private int matchingScore;
	private int consumedWords;
	private String matchingText;
	private int matchingGender;
	private int matchingNumber;

	/**
	 * Creates a new BestMatch.
	 */
	public BestMatch() {
		matchingStuff = new Vector();
		matchingScore = Description.SCORE_NO_FIT;
		consumedWords = 0;
		matchingText = null;
		matchingGender = Description.NO_GENDER;
		matchingNumber = Description.UNCOUNTED;
	}

	/**
	 * Obtains the size of this BestMatch.
	 * 
	 * @return number of matching items in this BestMatch.
	 */
	public int size() {
		return matchingStuff.size();
	}

	/**
	 * Adds the given Description to this BestMatch if it is good enough.
	 * (&quot;Good enough&quot; means that it scores higher than the current
	 * score.) Discards previous scoring Description if the new Description scores
	 * better.
	 * 
	 * @param score Likeliness for a match.
	 * @param words Number of words used for matching.
	 * @param something The Description that yielded the score.
	 * @param gender Gender of the <code>something</code>.
	 * @param number Count of the <code>something</code>.
	 * @param text Descriptive text of <code>something</code>.
	 * @throws IllegalArgumentException if score is greater than zero but caller
	 *         claims to have used no words.
	 */
	public void addIfGoodEnough(int score, int words, Description something, int gender, int number, String text) {
		if (words < 1 && score > 0) {
			throw new IllegalArgumentException("Cannot score " + score + " for " + words + " words");
		}
		if (score > matchingScore) {
			matchingStuff.clear();
			matchingScore = score;
			matchingGender = gender;
			matchingNumber = number;
			matchingText = text;
		}
		if (score == matchingScore && score != Description.SCORE_NO_FIT) {
			matchingStuff.add(something);
			consumedWords = words;
		}
	}

	/**
	 * Returns the score for all the DefaultDescription in this BestMatch.
	 * 
	 * @return Score of this BestMatch.
	 */
	public int getScore() {
		return matchingScore;
	}

	/**
	 * Merges the given BestMatch into this one, if its score is better than this
	 * BestMatch's score.
	 * 
	 * @param other BestMatch from which to copy the result.
	 */
	public void addAll(BestMatch other) {
		if (other.matchingScore > matchingScore) {
			matchingStuff.clear();
			matchingScore = other.matchingScore;
			consumedWords = other.consumedWords;
			matchingText = other.matchingText;
			matchingGender = other.matchingGender;
			matchingNumber = other.matchingNumber;
		}
		if (other.matchingScore == matchingScore) {
			matchingStuff.addAll(other.matchingStuff);
		}
	}

	/**
	 * Returns the only best matching Description.
	 * 
	 * @return the Description that matched best.
	 * @throws IllegalStateException if the match is not unambiguous, i.e. if
	 *         there is no match or more than one.
	 */
	public Description getStuff() {
		if (matchingStuff.size() != 1) {
			throw new IllegalStateException("Has " + matchingStuff.size() + " entries");
		}
		return (Description) (matchingStuff.toArray()[0]);
	}

	/**
	 * Returns the gender of the best matching synonym.
	 * 
	 * @return gender as {@link Description} constant.
	 * @throws IllegalStateException if the match is not unambiguous, i.e. if
	 *         there is no match or more than one.
	 */
	public int getGender() {
		if (matchingStuff.size() != 1) {
			throw new IllegalStateException("Has " + matchingStuff.size() + " entries");
		}
		return matchingGender;
	}

	/**
	 * Gets the count (singular or plural) of the best matching synonym.
	 * 
	 * @return count as {@link Description} constant.
	 * @throws IllegalStateException if the match is not unambiguous, i.e. if
	 *         there is no match or more than one.
	 */
	public int getNumber() {
		if (matchingStuff.size() != 1) {
			throw new IllegalStateException("Has " + matchingStuff.size() + " entries");
		}
		return matchingNumber;
	}

	/**
	 * Returns this BestMatch as a Collection. For use in the
	 * AmbiguousInputException only.
	 * 
	 * @return Collection of the matching Description items.
	 */
	public Collection asCollection() {
		return matchingStuff;
	}

	/**
	 * Gets the number of words used by this match. If this BestMatch does not
	 * contain matching items, the word count may still be zero to indicate how
	 * many words could have matched, but were not used for another reason (e.g.
	 * only adjectives, but no noun).
	 * 
	 * @return number of words.
	 */
	public int getWordCount() {
		return consumedWords;
	}

	/**
	 * Gets the description of the best matching Description.
	 * 
	 * @return short description.
	 */
	public String getText() {
		return matchingText;
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
