/*
 * ConditionGroup.java
 *
 * Created on September 17, 2005, 9:39 AM
 */

package org.slage.conditionals;


/**
 * Defines a group of Conditions. The check() for this Condition will return
 * 'true' if the conditions contained within it match the acceptance pattern.
 * There are three acceptance patterns, defined with integer constants in this
 * class:
 * 
 * <li> ALL: All conditions must return 'true'.
 * <li> ANY: Any one condition must return 'true'.
 * <li> NONE: All conditions must return 'false'.
 * 
 * @author <a href="mailto:Matt@SQ7.org">Matt Holden</a>
 */
public class ConditionGroup
		extends Condition {
	/**
	 * Construct an empty ConditionGroup
	 * 
	 * @param iAccept the acceptance criteria.
	 * 
	 * There are three acceptance patterns, defined with integer constants in this
	 * class:
	 * 
	 * <li> ALL: All conditions must return 'true'.
	 * <li> ANY: Any one condition must return 'true'.
	 * <li> NONE: All conditions must return 'false'.
	 * 
	 * @throws IllegalArgumentException if iAccept is not one of these values
	 */
	public ConditionGroup(int iAccept) throws IllegalArgumentException {
		super(null, null);
		setAcceptance(iAccept);
	}

	/** Construct an empty ConditionGroup with acceptance pattern ALL. */
	public ConditionGroup() {
		super(null, null);
	}

	/** A list of Conditions */
	private java.util.ArrayList<Condition> listConditions = new java.util.ArrayList<Condition>();

	/**
	 * Add a Condition
	 * 
	 * @param cond Condition to add
	 */
	public void addCondition(Condition cond) {
		listConditions.add(cond);
	}

	/**
	 * Remove a Condition
	 * 
	 * @param cond Condition to remove
	 */
	public void removeCondition(Condition cond) {
		listConditions.remove(cond);
	}

	/** Clear the Condition list */
	public void clearConditions() {
		listConditions.clear();
	}

	/**
	 * Get the count of conditions
	 * 
	 * @return listConditions.size()
	 */
	public int getConditionCount() {
		return listConditions.size();
	}

	/** Store the acceptance pattern */
	private int iAcceptance = ALL;

	/** Acceptance pattern for "All contained Conditions must return 'true'. */
	public static final int ALL = 0;

	/**
	 * Acceptance pattern for "Any one of the contained Conditions must return
	 * 'true'.
	 */
	public static final int ANY = 1;

	/** Acceptance pattern for "All contained Conditions must return 'false'. */
	public static final int NONE = 2;

	/**
	 * Set the acceptance pattern.
	 * 
	 * There are three acceptance patterns, defined with integer constants in this
	 * class:
	 * 
	 * <li> ALL: All conditions must return 'true'.
	 * <li> ANY: Any one condition must return 'true'.
	 * <li> NONE: All conditions must return 'false'.
	 * 
	 * @param iAccept the constant corresponding to the new acceptance pattern.
	 * @throws IllegalArgumentException if iAccept is not one of the three constants
	 *         above.
	 */
	public void setAcceptance(int iAccept) throws IllegalArgumentException {
		if (iAccept < 0 || iAccept > 2)
			throw new IllegalArgumentException(
                            "ConditionGroup.setAcceptance(): Parameter iAccept must be ALL, ANY, or NONE. See JavaDoc for ConditionGroup.");

		this.iAcceptance = iAccept;
	}

	/**
	 * Get the acceptance pattern.
	 * 
	 * There are three acceptance patterns, defined with integer constants in this
	 * class:
	 * 
	 * <li> ALL: All conditions must return 'true'.
	 * <li> ANY: Any one condition must return 'true'.
	 * <li> NONE: All conditions must return 'false'.
	 * 
	 * @return the constant corresponding to the new acceptance pattern.
	 */
	public int getAcceptance() {
		return iAcceptance;
	}

	/**
	 * Check if the contained Conditions meet the acceptance criteria.
	 * 
	 * @return 'true' if the conditions are met.
	 */
	public boolean check() {
		// If there are no conditions, it's not conditional. Always fire.
		if (listConditions.size() == 0)
			return true;

		// loop through... If we're at "Any" or "All", fail or succeed fast
		for (Condition C : listConditions) {
			if (C.check() == false && iAcceptance == ALL)
				return false;
			if (C.check() == true && iAcceptance == ANY)
				return true;
			if (C.check() == true && iAcceptance == NONE)
				return false;
		}

		// If we get here, we didn't find the fail-fast condition; return
		// the opposite
		return (iAcceptance == ANY) ? false : true;
	}

	/**
	 * Get the text for the Acceptance value
	 * 
	 * @return "any", "all", or "none'
	 */
	public String getAcceptanceText() {
		switch (iAcceptance) {
		case ANY:
			return "any";
		case ALL:
			return "all";
		case NONE:
			return "none";
		default: {
			iAcceptance = ALL;
			return "all";
		}
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
