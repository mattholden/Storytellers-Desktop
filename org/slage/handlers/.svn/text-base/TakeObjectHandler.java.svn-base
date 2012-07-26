/*
 * TakeObjectHandler.java
 *
 * Created on September 4, 2005, 9:31 AM
 */

package org.slage.handlers;

import org.slage.Room;
import org.slage.Slage;
import org.slage.SlageCharacter;
import org.slage.SlageGame;
import org.slage.SlageObject;
import org.slage.SlagePlayer;

/**
 * Pick up an object and put it in your inventory.
 * 
 * The Target should be set to the room losing the object. (If target isn't a
 * Room, use the current room)
 * 
 * The Indirect Object should be set to the player taking the object. (If
 * indirectObj isn't a Character, use the current player)
 * 
 * @author <a href="mailto:Matt@SQ7.org">Matt Holden</a>
 */
public class TakeObjectHandler
		extends Handler<SlageObject, SlageObject, SlageObject> {

	/**
	 * Creates a new instance of TakeObjectHandler
	 * 
	 * @param obj Parent object (being taken)
	 */
	public TakeObjectHandler(SlageObject obj) {
		super(obj);
	}

	/**
	 * Take the object. If 'target' isn't a Character, set the target to the
	 * current player.
	 */
	protected void fire() {
		SlageGame game = Slage.getCurrentGame();

		if (getTarget() instanceof Room == false)
			setTarget(game.getRoom());

		// make sure it's in the room to remove it
		if (getOwner().getParent() != getTarget())
			return;

		// remove object from the room
		((Room) getTarget()).removeObject(getOwner());

		// add object to the player
		if (getIndirectObj() instanceof SlageCharacter == false)
			setIndirectObj(game.getPlayer());

		((SlagePlayer) getIndirectObj()).addObject(getOwner());

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
