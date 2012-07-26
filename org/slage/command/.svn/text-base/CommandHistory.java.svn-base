/*
 * CommandHistory.java
 *
 * Created on October 9, 2005, 5:26 PM
 */

package org.slage.command;

import java.util.ArrayList;

import org.slage.SlageGame;

/**
 * Stores the command history for the game.
 * 
 * @author <a href="mailto:Matt@SQ7.org">Matt Holden</a>
 */
public class CommandHistory implements Commander {

	/** Logger instance */
	protected static final org.apache.commons.logging.Log LOG = org.apache.commons.logging.LogFactory.getLog(CommandHistory.class);

	/**
	 * Creates a new instance of CommandHistory
	 * 
	 * @param aGame Game we are recording history for
	 */
	public CommandHistory(SlageGame aGame) {
		this.game = aGame;
	}

	/** The SlageGame we are recording history for */
	private SlageGame game;

	/** A list of Commands */
	protected ArrayList<Command> listCommands = new ArrayList<Command>();

	/**
	 * Accessor for the paired game for this history
	 * 
	 * @return game
	 */
	public SlageGame getGame() {
		return game;
	}

	/**
	 * The maximum number of Commands to maintain. The oldest command will be
	 * dropped if this limit is reached. Defaults to unlimited.
	 */
	private int iMaxCacheSize = Integer.MAX_VALUE;

	
	/**
	 * Getter for Max Cache Size.
	 * 
	 * @return Value of property iMaxCacheSize.
	 */
	public int getMaxCacheSize() {
		return iMaxCacheSize;
	}

	/**
	 * Setter for Max Cache Size.
	 * 
	 * @param aMaxCache New value of property iMaxCacheSize.
	 */
	public void setMaxCacheSize(int aMaxCache) {
		this.iMaxCacheSize = aMaxCache;
	}

	/**
	 * Add a Command to the history.
	 * 
	 * @param command Command to add
	 */
	public void add(Command command) {
		// Drop the oldest if we are at the cap
		if (iMaxCacheSize != Integer.MAX_VALUE) {
			while (iMaxCacheSize <= listCommands.size())
				listCommands.remove(0);
		}
		listCommands.add(command);
		game.getConsole().addToHistory(command);
	}

	/**
	 * Remove a command from the history
	 * 
	 * @param command Command to remove
	 */
	public void remove(Command command) {
		listCommands.remove(command);
	}

	/** Clear the command history */
	public void clear() {
		listCommands.clear();
	}

	/**
	 * Get the number of stored commands
	 * 
	 * @return number of stored commands
	 */
	public int getCommandCount() {
		return listCommands.size();
	}

	/**
	 * Get the command at the given index
	 * 
	 * @param index Index to get from
	 * @return the Command at that index
	 * @throws IllegalArgumentException if index < 0 or > the max index
	 */
	public Command getCommand(int index) throws IllegalArgumentException {
		if (index < 0 || index >= getCommandCount())
			throw new IllegalArgumentException("CommandHandler.getCommand(): Index out of bounds");

		return listCommands.get(index);
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
