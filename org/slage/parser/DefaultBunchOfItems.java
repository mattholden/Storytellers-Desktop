// $Id: DefaultBunchOfItems.java,v 1.1 2005/11/04 07:52:19 kevlar2 Exp $
package org.slage.parser;

import java.util.Collection;
import java.util.Iterator;
import java.util.Vector;

import org.slage.framework.SlageFrameworkObject;

/**
 * Implements the default behavior for a BunchOfItems.
 * 
 * @author <a href="mailto:robert.wenner@gmx.de">Robert Wenner</a>
 */
public class DefaultBunchOfItems implements BunchOfItems {
	private Collection contents;

	/**
	 * Creates a new empty DefaultBunchOfItems.
	 */
	public DefaultBunchOfItems() {
		contents = new Vector();
	}

	/**
	 * Creates a new empty DefaultBunchOfItems.
	 * 
	 * @param obj linked object
	 */
	public DefaultBunchOfItems(org.slage.SlageObject obj) {
		contents = new Vector();
		setOwner(obj);
	}

	/**
	 * Places the given Description in this BunchOfItems. That makes it available
	 * to the parser.
	 * 
	 * @param something Description to put in the collection.
	 * @throws IllegalStateException if the given Description is already in this
	 *         BunchOfItems.
	 */
	public void placeItem(Description something) {
		if (contents.contains(something)) {
			throw new IllegalStateException(something + " is already in " + this);
		}
		contents.add(something);
	}

	public BestMatch getBestMatch(Tokenizer input) throws WordRepeatedException {
		BestMatch bestFit = new BestMatch();
		for (Iterator iter = contents.iterator(); iter.hasNext();) {
			input.mark();
			try {
				Description something = (Description) iter.next();
				something.addIfScores(bestFit, input);
			} finally {
				input.reset();
			}
		}
		return bestFit;
	}

	/**
	 * Removes the given Description from this BunchOfItems. Does nothing if the
	 * given Description isn't in this BunchOfItems.
	 * 
	 * @param stuff to remove.
	 */
	public void removeItem(Description stuff) {
		contents.remove(stuff);
	}

	public void removeAllItems() {
		contents.clear();
	}

	public Collection toCollection() {
		Collection copied = new Vector();
		copied.addAll(contents);
		return copied;
	}

	/**
	 * Convenience method to get a count of all the items
	 * 
	 * @return the number of items in the bunch
	 */
	public int getItemCount() {
		return contents.size();
	}

	public SlageFrameworkObject getOwner() {
		return owner;
	}

	public void setOwner(SlageFrameworkObject theSlageObject) {
		this.owner = theSlageObject;
	}

	/**
	 * The object this bunch belongs to, so we can track back to the object after
	 * the parser matches up.
	 */
	private SlageFrameworkObject owner;

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
