/*
 * $Id: ManyWordsTerm.java,v 1.1 2005/11/04 07:52:19 kevlar2 Exp $
 */
package org.slage.parser;

/**
 * Implements a complex description (consisting of a fixed group of words). To
 * match this ManyWordsTerm, the input must:
 * <ul>
 * <li>have at least the same number of words as this ManyWordsTerm</li>
 * <li>have all the words in this CompounMatchable in the given order</li>
 * </ul>
 * The amount of whitespace and case is ignored.
 * 
 * @author <a href="mailto:robert.wenner@gmx.de">Robert Wenner</a>
 */
public class ManyWordsTerm implements Term {
	private String[] theWords;

	/**
	 * Creates this ManyWordsTerm from given phrase.
	 * 
	 * @param phrase Words to describe this ManyWordsTerm.
	 */
	public ManyWordsTerm(String[] phrase) {
		theWords = phrase;
	}

	public int countMatchingWords(Tokenizer input) throws WordRepeatedException {
		int compared = 0;
		while (compared < theWords.length && input.hasNext()) {
			if (!theWords[compared++].equalsIgnoreCase(input.next())) {
				input.goBack(compared);
				return 0;
			}
		}
		input.goBack(compared);
		return compared;
	}

	public int countTotalWords() {
		return theWords.length;
	}

	public String toString() {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < theWords.length; i++) {
			sb.append(theWords[i]);
			if (i + 1 != theWords.length)
				sb.append(' ');
		}
		return sb.toString();
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
