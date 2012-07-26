// $Id: GermanParser.java,v 1.1 2005/11/04 07:52:19 kevlar2 Exp $

package org.slage.parser;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;

/**
 * German adventure game parser.
 * 
 * @author <a href="mailto:robert.wenner@gmx.de?subject=GermanParser">Robert
 *         Wenner</a>
 */
public class GermanParser
		extends Parser {
	private HashMap duplicatePrepositions;

	/**
	 * Creates a new German language parser.
	 */
	public GermanParser() {
		super(new GermanSimplifier());
		duplicatePrepositions = new HashMap();
		duplicatePrepositions.put("HINEIN", "IN"); // in ... rein / hinein
		duplicatePrepositions.put("HINAUS", "OUT"); // aus ... hinaus / heraus /
		// raus
		duplicatePrepositions.put("RAUS", "OUT"); // aus ... raus
		duplicatePrepositions.put("DRAUF", "ON"); // auf ... drauf
	}

	public Vector parse(String input) throws ParserException {
		Vector parsed = super.parse(input);
		fixDuplicatePrepositions(parsed);
		return parsed;
	}

	public Vector parse() throws ParserException {
		Vector parsed = super.parse();
		fixDuplicatePrepositions(parsed);
		return parsed;
	}

	/**
	 * Fixes (simplifies) duplicated prepositions that may occur in German; for
	 * example in &quot;schau in kiste hinein&quot;.
	 */
	private void fixDuplicatePrepositions(Vector parsed) {
		// "schau in kiste rein" is parsed to "LOOK IN BOX HINEIN BOX" where the
		// last noun box is provided by the Parser ("rein" should be a word where
		// the Parser should always provide a noun). Here the last two words
		// need to be discarded.
		// On the other hand, in "oeffne kiste und schau rein" the result is
		// "[OPEN BOX] LOOK HINEIN BOX". In the first case the last two words
		// are discarded, in the second case the HINEIN is substituted for a
		// 'standard' code of IN. This code is narrowed down to the positions
		// 2 and 4 for the nouns and 1 and 3 for the prepositions on purpose,
		// to avoid changing other parsed text by accident.
		if (parsed.size() == 5 && parsed.get(2) == parsed.get(4)) {
			for (Iterator iter = duplicatePrepositions.keySet().iterator(); iter.hasNext();) {
				String firstPreposition = (String) iter.next();
				String secondPreposition = (String) duplicatePrepositions.get(firstPreposition);
				if (parsed.get(3).equals(firstPreposition) && parsed.get(1).equals(secondPreposition)) {
					parsed.remove(4);
					parsed.remove(3);
					break;
				}
			}
		}
		for (int i = 0; i < parsed.size(); i++) {
			Object germanCode = parsed.get(i);
			String standardCode = (String) duplicatePrepositions.get(germanCode);
			if (standardCode != null) {
				parsed.remove(i);
				parsed.insertElementAt(standardCode, i);
			}
		}
	}
}

/**
 * Substitutes umlauts for their two-letter form in the given word.
 * 
 * <p>
 * Does not know any words, will as well convert &quot;Salzstre&uuml;r&quot; to
 * &quot;Salzstreuer&quot;; but I guess this won't hurt.
 * </p>
 */
class GermanSimplifier implements Tokenizer.WordSimplifier {
	// See Unicode code page U0080, http://www.unicode.org/charts/PDF/U0080.pdf
	private static char[] convertFrom = { '\u00E4', '\u00F6', '\u00FC', '\u00C4', '\u00D6', '\u00DC', '\u00DF' };
	private static String[] convertTo = { "ae", "oe", "ue", "Ae", "Oe", "Ue", "ss" };

	public String simplifiedWord(String word) {
		StringBuffer converted = new StringBuffer();
		for (int i = 0; i < word.length(); i++) {
			boolean changed = false;
			for (int j = 0; j < convertFrom.length; j++) {
				if (word.charAt(i) == convertFrom[j]) {
					converted.append(convertTo[j]);
					changed = true;
					break;
				}
			}
			if (!changed) {
				converted.append(word.charAt(i));
			}
		}
		return converted.toString();
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
