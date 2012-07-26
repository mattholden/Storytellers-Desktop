// $Id: BunchOfItems.java,v 1.1 2005/11/04 07:52:19 kevlar2 Exp $

package org.slage.parser;

import java.io.Serializable;
import java.util.Collection;

import org.slage.framework.LinkedObject;
import org.slage.framework.SlageFrameworkObject;
/**
 * For the parser a room or the player's inventory is just a a collection of
 * items (Description objects).
 * 
 * <p>
 * The parser does not care how items enter a BunchOfItems or how items are
 * removed, therefor this isn't specified in this interface.
 * </p>
 * 
 * <p>
 * You <em>do want</em> to use the
 * 
 * DefaultBunchOfItems. Implement your own BunchOfItems only if you must
 *                             and make sure you understand the Parser's
 *                             internal protocol.
 *                             </p>
 * 
 * <p>
 * Serializable to allow saving / restoring.
 * </p>
 * 
 * @author <a href="mailto:robert.wenner@gmx.de?subject=BunchOfItems">Robert
 *         Wenner</a>
 */
public interface BunchOfItems extends Serializable, LinkedObject<SlageFrameworkObject> {
	/**
	 * Gets the best match for the given input. The position in <code>input</code>
	 * must not be modified.
	 * 
	 * @param input from which to read words.
	 * @return best matches for the given input.
	 * @throws WordRepeatedException on duplicated words in the input.
	 */
	public abstract BestMatch getBestMatch(Tokenizer input) throws WordRepeatedException;

	/**
	 * Returns this BunchOfItems as Collection of Description items. Mainly for
	 * debugging purposes and to provide sensible error messages.
	 * 
	 * @return Collection of Description items.
	 */
	public abstract Collection toCollection();
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
