/*
 * $Id: Description.java,v 1.1 2005/11/04 07:52:19 kevlar2 Exp $
 */
package org.slage.parser;

import java.io.Serializable;

import org.slage.framework.LinkedObject;
import org.slage.framework.SlageFrameworkObject;

/**
 * Something in the game that the player can interact with. May be items in a
 * room as well as items in the inventory or persons.
 * 
 * <p>
 * As a game author, you probably want to use the {@link DefaultDescription}
 * rather than implement this interface yourself.
 * </p>
 * 
 * @author <a href="mailto:robert.wenner@gmx.de">Robert Wenner</a>
 */
public interface Description extends Serializable, LinkedObject<SlageFrameworkObject> {
	/**
	 * Score if the Description does not fit the given input.
	 */
	public static final int SCORE_NO_FIT = 0;

	/**
	 * Score if the Description fits only the given article. This is trivial in
	 * English, but is likely to be interesting in languages that have different
	 * articles depending on the noun's gender.
	 */
	public static final int SCORE_ARTICLE = 1;

	/**
	 * Score if the Description fits an adjective.
	 */
	public static final int SCORE_ADJECTIVE = 5;

	/**
	 * Score if the Description fits a noun.
	 */
	public static final int SCORE_NOUN = 1000;

	/**
	 * Score if the Description is a complete noun. This is used to evaluate
	 * "rock" for a piece of stone higher than the rock part in "rock music".
	 */
	public static final int SCORE_COMPLETE = 50000;

	/**
	 * Maximum possible number of geni (genders).
	 */
	public static final int NUM_GENI = 3;

	/**
	 * Indicates some Description has no qualified gender yet. Should not be used
	 * in real life, but only for initialization while the real gender is unknown.
	 */
	public static int NO_GENDER = -1;

	/**
	 * Marks some Description as having neutral gender. Used for assigning
	 * pronouns (i.e. it).
	 */
	public static final int NEUTRAL = 0;

	/**
	 * Marks some Description as having male gender. Used for assigning pronouns
	 * (i.e. him).
	 */
	public static final int MALE = 1;

	/**
	 * Marks some Description as having female gender. Used for assigning pronouns
	 * (i.e. her).
	 */
	public static final int FEMALE = 2;

	/**
	 * Maximum possible number of numeri (probably only singular / plural).
	 */
	public static final int NUM_NUMBERS = 2;

	/**
	 * Placeholder when the real count is not known yet.
	 */
	public static final int UNCOUNTED = -1;

	/**
	 * Indicates singular (one item).
	 */
	public static final int SINGULAR = 0;

	/**
	 * Indicates plural (many items).
	 */
	public static final int PLURAL = 1;

	/**
	 * Tells this Description to put itself on the given BestMatch for the given
	 * input.
	 * 
	 * @param bestFit current BestMatch for the input.
	 * @param input word-wise split input to examine for a match with this
	 *        Description.
	 * @throws WordRepeatedException if a duplicate word was detected during
	 *         reading from input.
	 */
	public abstract void addIfScores(BestMatch bestFit, Tokenizer input) throws WordRepeatedException;

	/**
	 * Tells this Description to put itself on the given BestMatch for the given
	 * input.
	 * 
	 * @param bestFit current BestMatch for the input.
	 * @param input word-wise split input to examine for a match with this
	 *        Description.
	 * @param synonym which synonym to try.
	 * @throws WordRepeatedException if a duplicate word was detected during
	 *         reading from input.
	 */
	public abstract void addIfScores(BestMatch bestFit, Tokenizer input, Synonym synonym) throws WordRepeatedException;
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
