/*
 * VerbSynonymList.java
 *
 * Created on October 7, 2005, 11:45 AM
 */

package org.slage.framework;

import java.util.ArrayList;

/**
 * Stores a list of synonyms for a verb. These synonyms will then be transferred
 * into a fireable whenever the useSynonyms attribute on a verb is set to
 * 'true'.
 * 
 * @author <a href="mailto:Matt@SQ7.org">Matt Holden</a>
 */
public class VerbSynonymList implements java.io.Serializable
{

	/** The list of synonyms */
	private ArrayList<String> listSynonyms = new ArrayList<String>();

	/** The node verb */
	private String strRootVerb;

	/** The "Action" - only used when we read these from XML */
	private int iAction = -1;

	/** Action flag for the XML "action" attribute value "create" */
	public static final int CREATE = 0;
	/** Action flag for the XML "action" attribute value "merge" */
	public static final int MERGE = 1;
	/** Action flag for the XML "action" attribute value "replace" */
	public static final int REPLACE = 2;
	/** Action flag for the XML "action" attribute value "omit" */
	public static final int OMIT = 3;

	/**
	 * Get count of synonyms
	 * 
	 * @return listSynonyms.size()
	 */
	public int getSynonymCount() {
		return listSynonyms.size();
	}

	/** Clear the synonym list */
	public void clear() {
		listSynonyms.clear();
	}

	/**
	 * Check if a given verb is in the list
	 * 
	 * @param strVerb verb to look for
	 * @return 'true' if it is in the list or is the node verb
	 */
	public boolean contains(String strVerb) {
		String strV = strVerb.toUpperCase();
		return (strV.equals(getRootVerb()) || listSynonyms.contains(strV));
	}

	/**
	 * Add a synonym. Does not allow duplicates.
	 * 
	 * @param strSyn synonym to add
	 */
	public void add(String strSyn) {
		if (contains(strSyn) == false)
			listSynonyms.add(strSyn.toUpperCase());
	}

	/**
	 * Remove a synonym
	 * 
	 * @param strSyn synonym to remove
	 */
	public void remove(String strSyn) {
		listSynonyms.remove(strSyn.toUpperCase());
	}

	/**
	 * Creates a new instance of VerbSynonymList
	 * 
	 * @param strRoot Root verb
	 */
	public VerbSynonymList(String strRoot) {
		setRootVerb(strRoot);
	}

	/**
	 * Getter for property strRootVerb.
	 * 
	 * @return Value of property strRootVerb.
	 */
	public java.lang.String getRootVerb() {
		return strRootVerb;
	}

	/**
	 * Setter for property strRootVerb.
	 * 
	 * @param aRootVerb New value of property strRootVerb.
	 */
	public void setRootVerb(java.lang.String aRootVerb) {
		this.strRootVerb = aRootVerb.toUpperCase();
	}

	/**
	 * Get a -copy- of the synonym list.
	 * 
	 * @return a copy of listSynonyms.
	 */
	public ArrayList getSynonyms() {
		ArrayList<String> listReturn = new ArrayList<String>();
		listReturn.addAll(listSynonyms);
		return listReturn;
	}

	/**
	 * Getter for property iAction.
	 * 
	 * @return Value of property iAction.
	 */
	public int getActionType() {
		return iAction;
	}

	/**
	 * Omit one VerbSynonymList from another. In this syntax, "this" will lose all
	 * of the verbs of "other".
	 * 
	 * @param other a VerbSynonymList to omit.
	 */
	public void omit(VerbSynonymList other) {
		for (String strSyn : other.listSynonyms) {
			remove(strSyn);
		}
	}

	/**
	 * Merge one VerbSynonymList into another. In this syntax, "this" will take on
	 * the verbs of "other".
	 * 
	 * @param other a VerbSynonymList to absorb.
	 */
	public void merge(VerbSynonymList other) {
		for (String strSyn : other.listSynonyms) {
			add(strSyn);
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
