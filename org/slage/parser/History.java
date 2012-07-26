/*
 * $Id: History.java,v 1.1 2005/11/04 07:52:19 kevlar2 Exp $
 */
package org.slage.parser;

import java.util.Collection;
import java.util.Iterator;

/**
 * Represents the last used nouns and verb history. Noun history is kept for
 * each gender and for singular and plural.
 * 
 * @author <a href="mailto:robert.wenner@gmx.de">Robert Wenner</a>
 */
class History {
	/*
	 * The last noun is stored as a String rather as a Description. This way the
	 * game will react correctly if a command moved an item from the room. The
	 * next command will yield a NoSuchStuffException rather than accepting an
	 * item that isn't on the scene anymore.
	 */
	private String[][] lastNoun;
	private int[][] usedBefore;
	private String lastVerb;

	public History() {
		lastNoun = new String[Description.NUM_GENI][Description.NUM_NUMBERS];
		for (int g = 0; g < Description.NUM_GENI; g++) {
			for (int c = 0; c < Description.NUM_NUMBERS; c++) {
				lastNoun[g][c] = null;
			}
		}
		usedBefore = new int[Description.NUM_GENI][Description.NUM_NUMBERS];
		for (int g = 0; g < Description.NUM_GENI; g++) {
			for (int c = 0; c < Description.NUM_NUMBERS; c++) {
				// 100 is small enough to prevent an integer overflow on aging
				// and large enough to prevent the initial null noun to be
				// considered new enough to be provided for a preposition.
				usedBefore[g][c] = 100;
			}
		}
		lastVerb = null;
	}

	/**
	 * Provides the corresponding noun for the given pronoun.
	 * 
	 * @param pronouns Collection of pronouns where to look for the pronoun in
	 *        question.
	 * @param pronoun pronoun we look for.
	 * @return last used noun matching the given pronoun.
	 * @throws AmbiguousPronounException if there is no matching noun in the
	 *         history.
	 */
	public String getNounForPronoun(Collection pronouns, String pronoun) throws AmbiguousPronounException {
		int gender = Description.NO_GENDER;
		int number = Description.UNCOUNTED;
		for (Iterator iter = pronouns.iterator(); iter.hasNext();) {
			Pronoun p = (Pronoun) iter.next();
			if (isMostRecentlyUsed(pronoun, p, gender, number)) {
				gender = p.getGender();
				number = p.getNumber();
			}
		}
		if (lastNoun[gender][number] != null) {
			return lastNoun[gender][number];
		}
		throw new AmbiguousPronounException(pronoun);
	}

	private boolean isMostRecentlyUsed(String pronoun, Pronoun p, int gender, int count) {
		return p.equals(pronoun) && (gender == Description.NO_GENDER || usedBefore[p.getGender()][p.getNumber()] < usedBefore[gender][count]);
	}

	/**
	 * Remembers the last noun to be able to substitute it for a pronoun in
	 * following input.
	 * 
	 * @param noun noun to remember.
	 * @param gender the given noun's gender, use Description gender constants.
	 * @param count singular or plural, use Description count constants.
	 */
	public void rememberLastNoun(String noun, int gender, int count) {
		lastNoun[gender][count] = noun;
		usedBefore[gender][count] = 0;
	}

	/**
	 * Recalls the last noun remembered, independent of its gender. Must have been
	 * used in the current or the last command.
	 * 
	 * @return last used noun or <code>null</code> if no noun was used lately.
	 */
	public String recallLastNoun() {
		for (int g = 0; g < lastNoun.length; g++) {
			for (int n = 0; n < lastNoun[g].length; n++) {
				if (usedBefore[g][n] <= 1) {
					return lastNoun[g][n];
				}
			}
		}
		return null;
	}

	/**
	 * Ages all nouns.
	 */
	public void ageLastNouns() {
		useNouns(1);
	}

	/**
	 * Undoes aging.
	 */
	public void unAgeLastNouns() {
		useNouns(-1);
	}

	private void useNouns(int direction) {
		for (int g = 0; g < usedBefore.length; g++) {
			for (int n = 0; n < usedBefore[g].length; n++) {
				usedBefore[g][n] += direction;
			}
		}
	}

	/**
	 * Stores the last used verb.
	 * 
	 * @param verb verb to remember.
	 */
	public void rememberVerb(String verb) {
		lastVerb = verb;
	}

	/**
	 * Obtain the last used verb. Opposite to the last used noun the use of the
	 * verb must not have been in the last command.
	 * 
	 * @return last used verb.
	 */
	public String recallVerb() {
		return lastVerb;
	}

	/**
	 * Forgets the last used verb. This is needed to disallow command sequences
	 * like &quot;look gem&quot, &quot;box&quot;, &quot;card&quot;.
	 */
	public void forgetVerb() {
		lastVerb = null;
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
