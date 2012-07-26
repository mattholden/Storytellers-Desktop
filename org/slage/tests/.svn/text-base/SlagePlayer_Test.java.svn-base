/*
 * SlagePlayer_Test.java
 *
 * 
 * Created on September 10, 2005, 10:45 AM
 */

package org.slage.tests;

import org.slage.SlagePlayer;

/**
 * Test SlagePlayer functionality
 * 
 * TODO: Test that score is incremented from firing a handler, when the real
 * parser is in
 * 
 * @author Jaeden
 */
public class SlagePlayer_Test
		extends SlageObject_Test {

	/**
	 * Creates a new instance of SlagePlayer_Test
	 * 
	 * @param strFunc function
	 */
	public SlagePlayer_Test(String strFunc) {
		super(strFunc);
	}

	/**
	 * Tests scoring system
	 * 
	 * Tests: - getScore() - setScore() - incScore()
	 */
	public void testScoring() throws Exception {
		SlagePlayer player = new SlagePlayer("Character", "Player");
		assertEquals(0, player.getScore());

		player.setScore(100);
		assertEquals(100, player.getScore());

		player.incScore(-50);
		assertEquals(player.getScore(), 50);

		player.incScore(100);
		assertEquals(player.getScore(), 150);
	}

	/**
	 * Tests player name
	 * 
	 * Tests: - constructor - setPlayerName() - getPlayerName()
	 */
	public void testPlayerName() throws Exception {
		SlagePlayer player = new SlagePlayer("Character", "Player");
		assertEquals(player.getName(), "Character");
		assertEquals(player.getPlayerName(), "Player");

		player.setPlayerName("Slacker Man");
		assertEquals(player.getPlayerName(), "Slacker Man");
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
