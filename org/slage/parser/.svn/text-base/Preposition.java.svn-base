/*
 * $Id: Preposition.java,v 1.1 2005/11/04 07:52:19 kevlar2 Exp $
 */
package org.slage.parser;

import org.jdom.Element;

/**
 * Encapsulates a preposition and its settings.
 * 
 * @author <a href="mailto:robert.wenner@gmx.de">Robert Wenner</a>
 */
public class Preposition {
	/**
	 * Indicates the Parser should always insert the last used noun after the
	 * preposition was encountered. Use for prepositions like &quot;inside&quot;.
	 */
	public static final int ALWAYS_PROVIDE_NOUN = 0;
	/**
	 * Indicates the Parser should never provide the last used noun after the
	 * preposition. Use for prepositions like &quot;under&quot; (under what?).
	 */
	public static final int NEVER_PROVIDE_NOUN = 1;
	/**
	 * Indicates the Parser should provide the last used noun after the
	 * preposition, unless the user used a noun. Use for prepositions that can
	 * have a noun, but where the noun may be omitted as well.
	 */
	public static final int PROVIDE_NOUN_UNLESS_GIVEN = 2;

	private String theWord;
	private String theCode;
	private int provideNoun;

	/**
	 * Creates this Command from the given XML node.
	 * 
	 * @param node JDOM style XML node.
	 */
	public Preposition(Element node) {
		theWord = node.getAttributeValue("word");
		theCode = node.getAttributeValue("code");
		provideNoun = nounHandlingFromText(node.getAttributeValue("provideNoun"));
	}

	private static int nounHandlingFromText(String nounHandling) {
		if (nounHandling.equals("never")) {
			return NEVER_PROVIDE_NOUN;
		} else if (nounHandling.equals("always")) {
			return ALWAYS_PROVIDE_NOUN;
		} else if (nounHandling.equals("unlessGiven")) {
			return PROVIDE_NOUN_UNLESS_GIVEN;
		}
		throw new IllegalArgumentException("Invalid noun handling: " + nounHandling);
	}

	public boolean equals(Object other) {
		if (other instanceof Preposition) {
			return theWord.equalsIgnoreCase(((Preposition) other).theWord);
		}
		if (other instanceof String) {
			return theWord.equalsIgnoreCase((String) other);
		}
		return false;
	}

	public int hashCode() {
		return theWord.toLowerCase().hashCode();
	}

	/**
	 * Gets this Preposition's code.
	 * 
	 * @return internal code used in command handling.
	 */
	public String getCode() {
		return theCode;
	}

	/**
	 * Checks whether this preposition should always provide a noun.
	 * 
	 * @return whether to always provide a noun.
	 */
	public boolean alwaysProvideNoun() {
		return provideNoun == ALWAYS_PROVIDE_NOUN;
	}

	/**
	 * Checks whether this preposition can provide a noun.
	 * 
	 * @return whether to provide a noun unless given.
	 */
	public boolean canProvideNoun() {
		return provideNoun == PROVIDE_NOUN_UNLESS_GIVEN;
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
