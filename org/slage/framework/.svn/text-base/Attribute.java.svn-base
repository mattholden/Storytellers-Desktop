/*
 * Attribute.java
 *
 * Created on November 3, 2005, 12:15 AM
 */

package org.slage.framework;

/**
 * Defines an Attribute container to permit us to make generic data structures
 * to store lists of Attributes with different types
 * 
 * @author Jaeden
 */
public class Attribute<Type> implements NamedObject, java.io.Serializable
{
	/** The name of the attribute */
	private String name;

	/** The value of the attribute */
	private Type value;

	/**
	 * Creates a new instance of Attribute
	 * 
	 * @param aName name of the state (will be the key in the attribute map)
	 * @param val Value of the attribute
	 */
	public Attribute(String aName, Type val) {
		name = aName;
		value = val;
	}

	/**
	 * Accessor for the attribute's name
	 * 
	 * @return attribute's name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Accessor for the attribute's value
	 * 
	 * @return attribute's value
	 */
	public Type getValue() {
		return value;
	}

	/**
	 * Mutator for the attribute's name
	 * 
	 * @param aName new name
	 */
	public void setName(String aName) {
		name = aName;
	}

	/**
	 * Mutator for the attribute's value
	 * 
	 * @param val new value
	 */
	public void setValue(Type val) {
		value = val;
	}

	/**
	 * toString - returns the format "(name): (value.toString)"
	 * 
	 * @return a string representation in the format above
	 */
	public String toString() {
		return name + ": " + value.toString();
	}

	/**
	 * Check that two Attributes are equal BY VALUE. Name can be different. Note
	 * that this behavior may not be predictable if the Type used for the value
	 * does not override equals(). Use equalTo() vs. overriding equals() to
	 * maintain type security throughout.
	 * 
	 * @param other Attribute to check against
	 * @return 'true' if the other object's value equals 'this' value
	 */
	public boolean equalTo(Attribute<Type> other) {
		return value.equals(other.getValue());
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
