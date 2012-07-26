/*
 * $Id: AdjectiveNounDescription.java,v 1.1 2005/11/04 07:52:19 kevlar2 Exp $
 */
package org.slage.parser;

/**
 * Description that is described by adjectives in front of the noun. Used for
 * languages like English or German.
 * 
 * @author <a href="mailto:robert.wenner@gmx.de">Robert Wenner</a>
 */
public class AdjectiveNounDescription
		extends DefaultDescription {
	public AdjectiveNounDescription(String shortDesc, Synonym synonym) {
		super(shortDesc, synonym);
	}

	/**
	 * A more convenient constructor - Builds 1 Synonym and adds it TODO remove
	 * me; see NounAdjectiveDescription
	 * 
	 * @param strName name of the object
	 * @param iGender Gender constant (MALE, FEMALE, NEITHER)
	 * @param iSingular Quantity constant (SINGULAR, PLURAL)
	 * @param listArticles Array of Strings containing the articles
	 * @param listAdjectives Array of Strings containing the adjectives
	 * @param listNouns Array of Strings containing the synonyms
	 */
	public AdjectiveNounDescription(String strName, int iGender, int iSingular, String[] listArticles, String[] listAdjectives, String[] listNouns) {
		super(strName, new Synonym(iGender, iSingular, new WordSet(listArticles), new WordSet(listAdjectives), new WordSet(listNouns)));
	}

	public AdjectiveNounDescription(String shortDesc, Synonym[] synonyms) {
		super(shortDesc, synonyms);
	}

	public void addIfScores(BestMatch bestFit, Tokenizer input, Synonym synonym) throws WordRepeatedException {
		int score = 0;
		int totalWords = 0;
		try {
			input.mark();
			while (input.hasNext()) {
				int matchingWords = 0;
				if (totalWords == 0 && (matchingWords = synonym.countMatchingArticles(input)) > 0) {
					score = SCORE_ARTICLE * matchingWords;
					totalWords += matchingWords;
				} else if ((matchingWords = synonym.countMatchingAdjectives(input)) > 0) {
					score += SCORE_ADJECTIVE * matchingWords;
					totalWords += matchingWords;
				} else if ((matchingWords = synonym.countMatchingNouns(input)) > 0) {
					score += SCORE_NOUN * matchingWords;
					if (synonym.wasComplete()) {
						score += SCORE_COMPLETE;
					}
					totalWords += matchingWords;
					break;
				} else {
					// Mismatch, no score.
					break;
				}
				input.goAhead(matchingWords);
			}
			input.reset();
			bestFit.addIfGoodEnough(score, totalWords, me, synonym.getGender(), synonym.getNumber(), input.toString(totalWords));
		} catch (WordRepeatedException wre) {
			input.reset();
			throw wre;
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
