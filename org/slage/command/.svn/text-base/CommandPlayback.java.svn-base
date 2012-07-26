/*
 * CommandPlayback.java
 *
 * Created on October 10, 2005, 12:55 AM
 */

package org.slage.command;

import org.slage.framework.scheduler.RecurringEvent;
import org.slage.framework.scheduler.Scheduler;

/**
 * Plays back a Command History like a movie, properly paced.
 * 
 * @author <a href="mailto:Matt@SQ7.org">Matt Holden</a>
 */
public class CommandPlayback
		extends CommandHistory {
	/*****************************************************************************
	 * Playback stuff
	 ****************************************************************************/

	/** current command of the playback */
	private int iCurrentPlaybackCommandIndex = -1;

	/** RecurringEvent to play the playback */
	private RecurringEvent eventPlayback = null;

	/** If true, the playback is paused (but not stopped) */
	private boolean bPlaybackPaused = false;

	/** The current "time" in our playback game world */
	private long lPlaybackTime = -1;

	/** The time when we last updated */
	private long lPlaybackLastUpdated = -1;

	/**
	 * Creates a new instance of CommandPlayback
	 * 
	 * @param game Game we are playing back history for
	 */
	public CommandPlayback(org.slage.SlageGame game) {
		super(game);
	}

	

	/**
	 * Check if playback is paused, but not stopped
	 * 
	 * @return true if the playback is paused
	 */
	public boolean isPlaybackPaused() {
		return (isPlayingBack() && bPlaybackPaused);
	}

	/**
	 * Check if playback is playing (or paused)
	 * 
	 * @return true if the playback is not stopped
	 */
	public boolean isPlayingBack() {
		return (this.iCurrentPlaybackCommandIndex != -1);
	}

	/** Build the playback event */
	protected void buildPlaybackEvent() {
		org.slage.handlers.Handler H = new AdvancePlaybackHandler(this);

		// as fast as we can - advancePlayback() will control the timing
		eventPlayback = new RecurringEvent(H, 0, 1);
	}

	/** Start playback */
	public void startPlayback() {
		buildPlaybackEvent();
		getGame().addEvent(eventPlayback);

		iCurrentPlaybackCommandIndex = 0;
		lPlaybackTime = 0;
		lPlaybackLastUpdated = System.nanoTime();
	}

	/** Pause playback */
	public void pausePlayback() {
		if (bPlaybackPaused == true)
			return;

		// don't fire the event any more
		getGame().removeEvent(eventPlayback);
		bPlaybackPaused = true;
	}

	/** Resume playback if it's paused */
	public void resumePlayback() {
		if (bPlaybackPaused == false)
			return;

		lPlaybackLastUpdated = System.nanoTime();

		// start firing the event again
		getGame().addEvent(eventPlayback);
	}

	/** Stop playback altogether */
	public void stopPlayback() {
		getGame().removeEvent(eventPlayback);
		eventPlayback = null;
		bPlaybackPaused = false;
		iCurrentPlaybackCommandIndex = -1;
		lPlaybackTime = -1;
		lPlaybackLastUpdated = -1;
	}

	/** Advance a playback */
	public void advancePlayback() {
		// update time
		lPlaybackTime += Scheduler.getElapsedTime(lPlaybackLastUpdated);

		// we might have more than one command to fire this round
		while (true) {
			Command next = listCommands.get(iCurrentPlaybackCommandIndex);
			if (lPlaybackTime >= next.getTimestamp()) {
				next.fire();
				iCurrentPlaybackCommandIndex++;
			} else
				break;
		}
	}

	/**
	 * Getter for Current Playback Command index.
	 * 
	 * @return Value of property iCurrentPlaybackCommandIndex.
	 */
	public int getCurrentPlaybackCommandIndex() {
		return iCurrentPlaybackCommandIndex;
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
