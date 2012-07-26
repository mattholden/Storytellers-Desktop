/*
 * Condition.java
 *
 * Created on September 17, 2005, 9:26 AM
 */

package org.slage.conditionals;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Defines a Condition object that we can use to check values in determining
 * whether or not a handler should be fired.
 * 
 * @author <a href="mailto:Matt@SQ7.org">Matt Holden</a>
 */
public abstract class Condition implements java.io.Serializable {

	/** Logger instance */
	protected static final Log LOG = LogFactory.getLog(Condition.class);

	/** The object to compare to */
	private Object objCompareTo;

	/** The object to compare from (Often, the target of the handler) */
	private Object objCompare;

	/**
	 * The heart and soul of a Condition: determines whether the condition is true
	 * or false.
	 * 
	 * @return the result of the conditional check
	 */
	public abstract boolean check();

	/**
	 * Getter for object to compare
	 * 
	 * @return Value of property objCompare.
	 */
	public java.lang.Object getObjCompare() {
		return objCompare;
	}

	/**
	 * Setter for object to compare
	 * 
	 * @param aCompare New value of property objCompare.
	 */
	public void setObjCompare(Object aCompare) {
		this.objCompare = aCompare;
	}

	/**
	 * Getter for object to compare against
	 * 
	 * @return Value of property objCompareTo.
	 */
	public java.lang.Object getObjCompareTo() {
		return objCompareTo;
	}

	/**
	 * Setter for object to compare against
	 * 
	 * @param aCompareTo New value of property objCompareTo.
	 */
	public void setObjCompareTo(Object aCompareTo) {
		this.objCompareTo = aCompareTo;
	}

	/**
	 * Construct a Condition in code.
	 * 
	 * @param obj Compare object
	 * @param obj2 Compare-against object
	 */
	public Condition(Object obj, Object obj2) {
		setObjCompare(obj);
		setObjCompareTo(obj2);
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
