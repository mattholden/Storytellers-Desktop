/*
 * ConditionalList.java
 *
 * Created on September 18, 2005, 10:06 AM
 */

package org.slage.conditionals;


/**
 * Defines interface functionality for containing and checking Conditions.
 * 
 * Known implementors:
 * 
 * @see ConditionGroup
 * @see org.slage.handlers.Handler
 * 
 * @author <a href="mailto:Matt@SQ7.org">Matt Holden</a>
 */
public interface ConditionalList {

	/**
	 * Add a Condition
	 * 
	 * @param cond Condition to add
	 */
	public abstract void addCondition(Condition cond);

	/**
	 * Remove a Condition
	 * 
	 * @param cond Condition to remove
	 */
	public abstract void removeCondition(Condition cond);

	/** Clear the Condition list */
	public abstract void clearConditions();

	/**
	 * Get the count of conditions
	 * 
	 * @return listConditions.size()
	 */
	public abstract int getConditionCount();

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
	public abstract void setAcceptance(int iAccept) throws IllegalArgumentException;

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
	public abstract int getAcceptance();

	/**
	 * Check if the contained Conditions meet the acceptance criteria.
	 * 
	 * @return 'true' if the conditions are met.
	 */
	public abstract boolean check();

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
