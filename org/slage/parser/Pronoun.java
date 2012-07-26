/*
 * $Id: Pronoun.java,v 1.1 2005/11/04 07:52:19 kevlar2 Exp $
 */
package org.slage.parser;

import org.jdom.Element;

/**
 * Encapsulates a personal pronoun and its gender.
 * 
 * @author <a href="mailto:robert.wenner@gmx.de">Robert Wenner</a>
 */
public class Pronoun {
	private String thePronoun;
	private int theGender;
	private int theNumber;

	/**
	 * Creates this Pronoun from the given XML node.
	 * 
	 * @param node JDOM style XML node.
	 */
	public Pronoun(Element node) {
		thePronoun = node.getAttributeValue("word");
		theGender = genderFromText(node.getAttributeValue("gender"));
		theNumber = numberFromText(node.getAttributeValue("number"));
	}

	private static int numberFromText(String attributeValue) {
		if ("singular".equals(attributeValue)) {
			return Description.SINGULAR;
		} else if ("plural".equals(attributeValue)) {
			return Description.PLURAL;
		}
		throw new IllegalStateException("Invalid count " + attributeValue);
	}

	private static int genderFromText(String genderText) {
		if ("male".equals(genderText)) {
			return Description.MALE;
		} else if ("female".equals(genderText)) {
			return Description.FEMALE;
		} else if ("neutral".equals(genderText)) {
			return Description.NEUTRAL;
		}
		throw new IllegalArgumentException("Invalid gender " + genderText);
	}

	public boolean equals(Object other) {
		if (other instanceof Pronoun) {
			return thePronoun.equalsIgnoreCase(((Pronoun) other).thePronoun);
		}
		if (other instanceof String) {
			return thePronoun.equalsIgnoreCase((String) other);
		}
		return false;
	}

	public int hashCode() {
		return thePronoun.toLowerCase().hashCode();
	}

	/**
	 * Gets this Pronoun's gender.
	 * 
	 * @return gender as defined in Description constants.
	 * @see Description#FEMALE
	 * @see Description#MALE
	 * @see Description#NEUTRAL
	 */
	public int getGender() {
		return theGender;
	}

	/**
	 * Gets this Pronoun's number.
	 * 
	 * @return either {@link Description#SINGULAR} or {@link Description#PLURAL}.
	 */
	public int getNumber() {
		return theNumber;
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
