/*
 * $Id: WordSet.java,v 1.1 2005/11/04 07:52:19 kevlar2 Exp $
 */
package org.slage.parser;

import java.io.Serializable;
import java.util.Iterator;
import java.util.StringTokenizer;
import java.util.Vector;

/**
 * A part of some Description description. Used for sets of words of the same
 * kind, e.g. for articles, adjectives, or nouns.
 * 
 * @author <a href="mailto:robert.wenner@gmx.de">Robert Wenner</a>
 */
public class WordSet implements Serializable {
	private Vector<Term> theTerms;
	private boolean lastNounCheckComplete;

	/**
	 * Creates an empty WordSet. Matches nothing.
	 */
	WordSet() {
		theTerms = new Vector<Term>();
		lastNounCheckComplete = false;
	}

	/**
	 * Creates a WordSet matching the given term.
	 * 
	 * @param term descriptive text.
	 * @throws IllegalArgumentException if <code>term</code> is empty.
	 */
	public WordSet(String term) {
		this();
		theTerms.add(termsFromString(term));
	}

	/**
	 * Creates a WordSet matching the given terms.
	 * 
	 * @param terms descriptive texts.
	 * @throws IllegalArgumentException if <code>terms</code> is empty.
	 */
	public WordSet(String[] terms) {
		this();
		for (int i = 0; i < terms.length; i++) {
			Term term = termsFromString(terms[i]);
			if (theTerms.contains(term)) {
				throw new IllegalArgumentException("Duplicated terms in WordSet");
			}
			theTerms.add(term);
		}
	}

	private Term termsFromString(String term) {
		Vector words = new Vector();
		StringTokenizer tokenizer = new StringTokenizer(term);
		while (tokenizer.hasMoreTokens()) {
			words.add(tokenizer.nextToken());
		}
		if (words.size() == 0) {
			throw new IllegalArgumentException("Can not have empty description term");
		} else if (words.size() == 1) {
			return new OneWordTerm((String) words.firstElement());
		}
		String[] buffer = new String[words.size()];
		words.toArray(buffer);
		return new ManyWordsTerm(buffer);
	}

	/**
	 * Checks whether this WordSet contains the given input. Reads as much from
	 * <code>input</code> until it can decide on the match. <code>input</code>'s
	 * position is not changed.
	 * 
	 * @param input from which to read words.
	 * @return best matching score (maximum words that matched)
	 * @throws WordRepeatedException on repeated words in the input.
	 */
	public int scoreFor(Tokenizer input) throws WordRepeatedException {
		int bestScore = 0;
		lastNounCheckComplete = false;
		for (Iterator iter = theTerms.iterator(); iter.hasNext();) {
			Term thisMatch = (Term) iter.next();
			int currentScore = thisMatch.countMatchingWords(input);
			if (currentScore > bestScore) {
				bestScore = currentScore;
				lastNounCheckComplete = currentScore == thisMatch.countTotalWords();
			}
		}
		return bestScore;
	}

	/**
	 * Checks whether this WordSet is valid. Mainly for detecting errors in
	 * vocabulary early.
	 * 
	 * @throws IllegalArgumentException if this WordSet has no nouns.
	 */
	public void checkAsNoun() {
		if (theTerms.isEmpty()) {
			throw new IllegalArgumentException("Noun terms must not be empty");
		}
	}

	/**
	 * Checks whether the last check was complete.
	 * 
	 * @return whether the last check matched a full set of words.
	 */
	public boolean wasComplete() {
		return lastNounCheckComplete;
	}

	/**
	 * Get an array of Elements representing the words
	 * 
	 * @param strTag node tag for each word
	 * @return array of elements
	 */
	public org.jdom.Element[] getXMLElements(String strTag) {

		org.jdom.Element elems[] = new org.jdom.Element[theTerms.size()];

		for (int i = 0; i < theTerms.size(); i++) {
			Term T = theTerms.get(i);
			elems[i] = new org.jdom.Element(strTag);
			elems[i].setAttribute("value", T.toString());
		}
		return elems;
	}

	/**
	 * Append a term to the set.
	 * 
	 * @param strTerm term to add
	 */
	public void append(String strTerm) {
		Term term = termsFromString(strTerm);
		if (theTerms.contains(term)) {
			throw new IllegalArgumentException("Duplicated terms in WordSet");
		}
		theTerms.add(term);
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
