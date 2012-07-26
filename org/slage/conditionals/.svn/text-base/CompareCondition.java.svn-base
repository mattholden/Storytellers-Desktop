/*
 * CompareCondition.java
 *
 * Created on September 23, 2005, 1:24 AM
 */

package org.slage.conditionals;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * Provides a base class to simplify our siz Comparable conditionals.
 * 
 * @author <a href="mailto:Matt@SQ7.org">Matt Holden</a>
 */
public abstract class CompareCondition
		extends Condition {

	/**
	 * Construct a condition in code
	 * 
	 * @param obj1 Compare object
	 * @param obj2 CompareTo object
	 */
	public CompareCondition(Comparable obj1, Comparable obj2) {
		super(obj1, obj2);
	}

	/**
	 * Check that the condition is true
	 * 
	 * @return 'true' if the objects have the required relationship
	 */
	public boolean check() {
		if (getObjCompare() == null && getObjCompareTo() == null)
			return true;
		if (getObjCompare() == null || getObjCompareTo() == null)
			return false;
		if (getObjCompareTo() instanceof Comparable == false)
			return false;
		if (getObjCompare() instanceof Comparable == false)
			return false;

		Comparable a = (Comparable) getObjCompare();
		Comparable b = (Comparable) getObjCompareTo();
		// Use ignore case version for strings so we don't need separate
		// conditionals for strings. Equal and not-equal are pretty self-
		// explanatory. >, >=, < and <= are "lexicographically" compared,
		// which may mean alphabetical; I'm not sure. I'm not entirely
		// confident that I'd want to say "foo" > "bar" in XML anyway.
		if (getObjCompare() instanceof String && getObjCompareTo() instanceof String) {
			String A = (String) a;
			String B = (String) b;
			return compare(A.compareToIgnoreCase(B));
		}

		// Protect against ClassCastExceptions by not comparing different types
		// Promote all floats to Doubles, all integer types to Long, and
		// integer types to Double when at least one float is involved
		if (a instanceof Number && b instanceof Number) {
			Number nA = (Number) a;
			Number nB = (Number) b;

			// first priority - if either are bigs, promote to bigs
			if (isBigType(nA) || isBigType(nB)) {
				BigDecimal bA = promoteNumberToBig(nA);
				BigDecimal bB = promoteNumberToBig(nB);
				return compare(bA.compareTo(bB));
			}

			// if both are ints, promote to long.
			if (isIntegerType(nA) && isIntegerType(nB)) {
				Long lA = promoteNumberToLong(nA);
				Long lB = promoteNumberToLong(nB);
				return compare(lA.compareTo(lB));
			}
			// at least one is float.. promote both to double
			Double dA = promoteNumberToDouble(nA);
			Double dB = promoteNumberToDouble(nB);
			return compare(dA.compareTo(dB));
		}
		return compare(a.compareTo(b));

	}

	/**
	 * Define how the conditional should react
	 * 
	 * @param iCompare the result of a compareTo() call
	 * @return 'true' if the check should pass with this value
	 */
	public abstract boolean compare(int iCompare);

	/**
	 * Determine if a Number is an integer type
	 * 
	 * @param n a Number to check
	 * @return 'true' if it's an integer type
	 */
	public static boolean isIntegerType(Number n) {
		return (n instanceof Byte || n instanceof Short || n instanceof Long || n instanceof Integer
		/* || n instanceof java.math.BigInteger */);
	}

	/**
	 * Determine if a Number is a floating-point type
	 * 
	 * @param n a Number to check
	 * @return 'true' if it's a floating-point type
	 */
	public static boolean isFloatType(Number n) {
		return (n instanceof Float || n instanceof Double
		/* || n instanceof java.math.BigDecimal */);
	}

	/**
	 * Determine if a Number is a "big" type
	 * 
	 * @param n a Number to check
	 * @return 'true' if it's a BigInteger or BigDecimal type
	 */
	public static boolean isBigType(Number n) {
		return (n instanceof BigDecimal || n instanceof BigInteger);
	}

	/**
	 * Promote numeric types to Long if it's integer type
	 * 
	 * @param n a Number
	 * @return the Number as a Long
	 */
	public static Long promoteNumberToLong(Number n) {
		if (isIntegerType(n))
			return new Long(n.longValue());
		return null;
	}

	/**
	 * Promote numeric types to Doubles if any are floating-point
	 * 
	 * @param n a Number
	 * @return the Number as a Double
	 */
	public static Double promoteNumberToDouble(Number n) {
		if (n instanceof Double)
			return (Double) n;
		else if (n instanceof Float)
			return new Double(n.floatValue());
		else if (isIntegerType(n))
			return new Double(n.longValue());

		// bigDecimal - cant handle that yet
		else
			return null;

	}

	/**
	 * Promote a number to a BigDecimal. Since it's gonna be slow anyway, there's
	 * no point in bothering with BigInteger form here.
	 * 
	 * @param N a number to convert
	 * @return the number as a BigDecimal
	 */
	public static BigDecimal promoteNumberToBig(Number N) {
		if (N instanceof BigDecimal)
			return (BigDecimal) N;
		return new BigDecimal(N.toString());

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
