/*
 * $Id: Tokenizer.java,v 1.1 2005/11/04 07:52:19 kevlar2 Exp $
 */
package org.slage.parser;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;
import java.util.Vector;

/**
 * Tokenizer for the low-level parsing stuff like grouping characters into
 * words. Contrary to the <code>java.util.StringTokenizer</code> this
 * Tokenizer allows stepping forward and back in its tokens, storing a position,
 * and converting any (not yet read) tokens into a string or collection.
 * 
 * @author <a href="mailto:robert.wenner@gmx.de">Robert Wenner</a>
 */
public class Tokenizer {
	private List words;
	private int index;
	private String theSeparator;
	private Stack stored;
	private WordSimplifier theWordSimplifier;

	/**
	 * An object that does simplification of input on a word-basis. When reading
	 * tokens the Tokenizer will pass each word from its input to the
	 * <code>simplifiedWord</code> method. This method should do any desired
	 * simplifications on the word. For example, a German parser may choose to
	 * translate umlauts, e.g. convert &quot;&auml;&quot; to &quot;ae&quot;.
	 * 
	 * @author <a href="mailto:robert.wenner@gmx.de">Robert Wenner</a>
	 */
	public interface WordSimplifier {
		/**
		 * Simplifies the current word context-insensitive on a per-word basis. Note
		 * that this method may be called multiple times for the same word. It
		 * <em>must not</em> keep a state, but rather work only on a word-by-word
		 * basis.
		 * 
		 * @param currentWord word to be simplified.
		 * @return simplified word.
		 */
		public String simplifiedWord(String currentWord);
	}

	/**
	 * Convenience constructor if you do not need a WordSimplifier.
	 * 
	 * @param separator a word that the Tokenizer should use to replace sentence
	 *        separator characters like &quot;!.;&quot;. This allows the Tokenizer
	 *        to be language-indpendent.
	 * @param input as typed by the player.
	 */
	public Tokenizer(String separator, String input) {
		this(separator, input, null);
	}

	/**
	 * Creates a new Tokenizer.
	 * 
	 * @param separator a word that the Tokenizer should use to replace sentence
	 *        separator characters like &quot;!.;&quot;. This allows the Tokenizer
	 *        to be language-indpendent.
	 * @param input as typed by the player.
	 * @param ws WordSimplifier for context-insensitive, word-wise
	 *        simplifications.
	 */
	public Tokenizer(String separator, String input, WordSimplifier ws) {
		words = new LinkedList();
		index = 0;
		stored = new Stack();
		theSeparator = separator;
		theWordSimplifier = ws;
		insertText(input);
	}

	/**
	 * Insert further input at the current position.
	 * 
	 * @param input text to insert.
	 */
	public void insertText(String input) {
		StringTokenizer tok = new StringTokenizer(input);
		int i = 0;
		while (tok.hasMoreTokens()) {
			words.add(index + i, tok.nextToken());
			i++;
		}
	}

	/**
	 * Returns whether the Tokenizer has a next word to read.
	 * 
	 * @return whether calling <code>next</code> is safe.
	 */
	public boolean hasNext() {
		return index < words.size();
	}

	/**
	 * Get the next word from the input tokenizer, detecting duplicated words.
	 * 
	 * @return next word from input with leading and trailing whitespace trimmed.
	 * @throws WordRepeatedException if the current word is the same as the last
	 *         one. The offending word is removed from the input.
	 * @throws IndexOutOfBoundsException if there is no more input to read.
	 */
	public String next() throws WordRepeatedException {
		String currentWord;
		do {
			currentWord = substituteSeparators((String) words.get(index));
		} while (currentWord.trim().equals(""));

		if (index > 0 && currentWord.equalsIgnoreCase((String) words.get(index - 1))) {
			words.remove(index);
			throw new WordRepeatedException(currentWord);
		}

		index++;
		if (theWordSimplifier != null) {
			return theWordSimplifier.simplifiedWord(currentWord);
		}
		return currentWord;
	}

	/**
	 * Transforms separator characters into a separator word.
	 */
	private String substituteSeparators(String currentWord) {
		for (int currentPos = 0; currentPos < currentWord.length(); currentPos++) {
			if (isSentenceSeparatorChar(currentWord.charAt(currentPos))) {
				String beforeSep = currentWord.substring(0, currentPos);
				while (currentPos < currentWord.length() && isSentenceSeparatorChar(currentWord.charAt(currentPos))) {
					currentPos++;
				}
				String afterSep = currentWord.substring(currentPos);
				words.remove(index);
				insertText(beforeSep + " " + theSeparator + " " + afterSep);
				return beforeSep;
			}
		}
		return currentWord;
	}

	/**
	 * Checks whether the given character is a sentence separator.
	 */
	private boolean isSentenceSeparatorChar(char c) {
		return ".;!&+".indexOf(c) > -1;
	}

	/**
	 * Gets the unprocessed input as a string.
	 * 
	 * @return remaining input as a space separated string.
	 */
	public String toString() {
		return toString(words.size() - index);
	}

	/**
	 * Returns a number of the remaining words as a space separated string.
	 * 
	 * @param howMuch exact number of word to return.
	 * @return words left to process in this Tokenizer.
	 */
	public String toString(int howMuch) {
		String tokens = "";
		for (int i = 0; i < howMuch; i++) {
			tokens += words.get(index + i);
			if (i + 1 < howMuch) {
				tokens += " ";
			}
		}
		return tokens;
	}

	/**
	 * Returns the remaining input as a collection.
	 * 
	 * @return copy of the remaining input.
	 */
	public Collection toCollection() {
		return toCollection(words.size() - index);
	}

	/**
	 * Returns the first <code>howMuch</code> words of the remaining input as a
	 * collection.
	 * 
	 * @param howMuch number of words to return, must not exceed number of words
	 *        available.
	 * @return copy of the first remaining input words.
	 * @throws IndexOutOfBoundsException if <code>howMuch</code> is beyond the
	 *         end of the input.
	 */
	public Collection toCollection(int howMuch) {
		return words.subList(index, index + howMuch);
	}

	/**
	 * Steps words back.
	 * 
	 * @param howMuch number of words to go back.
	 * @throws IllegalStateException if asked to go before the beginning of the
	 *         input.
	 */
	public void goBack(int howMuch) {
		if (howMuch > index) {
			throw new IllegalStateException("Can not push back " + howMuch + " from " + index);
		}
		index -= howMuch;
	}

	/**
	 * Steps words ahead.
	 * 
	 * @param howMuch number of words to go.
	 * @throws IllegalStateException if asked to go beyond the end of the input.
	 */
	public void goAhead(int howMuch) {
		if (index + howMuch > words.size()) {
			throw new IllegalArgumentException("Can only go " + (words.size() - index) + " elements, not " + howMuch + ".");
		}
		index += howMuch;
	}

	/**
	 * Marks the current position. The Tokenizer keeps a stack of position
	 * markers, so multiple mark operations are possible.
	 */
	public void mark() {
		stored.push(new Integer(index));
	}

	/**
	 * Jump back to the most recently marked position and pop it off the marker
	 * stack. Discards the position jumped back to from the positions stack.
	 */
	public void reset() {
		index = ((Integer) stored.pop()).intValue();
	}

	/**
	 * Appends given input to this tokenizer.
	 * 
	 * @param moreWords words to append at the end of the existing input.
	 */
	public void append(Vector moreWords) {
		words.addAll(moreWords);
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
