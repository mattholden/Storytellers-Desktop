/*
 * Scheduler.java
 *
 * Created on July 31, 2005, 10:03 PM
 */

package org.slage.framework.scheduler;

import java.util.ArrayList;
import java.util.Collections;

import org.slage.framework.Tools;

/**
 * This class will fire events on cue.
 * 
 * @author <a href="mailto:Matt@SQ7.org">Matt Holden</a>
 */
public class Scheduler {

	/** Logger instance */
	protected static final org.apache.commons.logging.Log LOG = org.apache.commons.logging.LogFactory.getLog(Scheduler.class);

	/** Last time the scheduler was updated */
	private long lLastUpdated = 0L;

	/** Total run time for the game (including saved time) */
	private long lGameRunTime = 0L;

	/** List of events to maintain */
	private ArrayList<ScheduledEvent> listEvents = new java.util.ArrayList<ScheduledEvent>();

	/** If 'true', the list has been added to and needs a sort() */
	private boolean bNeedsSort = false;

	/**
	 * Creates a new instance of Scheduler
	 */
	public Scheduler() {
		lLastUpdated = System.nanoTime();
	}

	/**
	 * Add an ScheduledEvent to the scheduler
	 * 
	 * @param evt Event to add
	 */
	public void addEvent(ScheduledEvent evt) {
		if (evt == null)
			return;
		listEvents.add(evt);
		bNeedsSort = true;
	}

	/**
	 * Remove a ScheduledEvent
	 * 
	 * @param evt Event to remove
	 */
	public void removeEvent(ScheduledEvent evt) {
		listEvents.remove(evt);
	}

	/** Comparator. Storing the instance saves memory allocations */
	private static final ScheduledEvent.EventComparator comparator = new ScheduledEvent.EventComparator();

	/** Sort the events by priority */
	public void sort() {
		Collections.sort(listEvents, comparator);
		bNeedsSort = false;

	}

	/**
	 * Check the Events, firing those that should be fired. Call this function
	 * every game loop iteration!
	 */
	public void update() {
		// sort if we need it
		if (bNeedsSort)
			sort();

		// update elapsed game time
		this.lGameRunTime += getElapsedTime(this.lLastUpdated);
		lLastUpdated = System.nanoTime();

		// run all our events if it's time
		for (ScheduledEvent e : listEvents) {
			e.update(); // tick off time

			if (e.destroyOnRun() && e.hasRun()) {
				listEvents.remove(e);
				continue;
			}

			if (e.isReadyToRun())
				e.run();
		}
	}

	/** Resume the timer after a pause */
	public void resume() {
		for (ScheduledEvent SE : listEvents) {
			SE.resumeAfterPause();
		}
	}

	/**
	 * Get count of events
	 * 
	 * @return event count
	 */
	public int getEventCount() {
		return listEvents.size();
	}

	/** Clear list of events */
	public void clearEvents() {
		listEvents.clear();
	}

	/**
	 * Return all events whose Handlers are of the given type
	 * 
	 * @param clsHandler class file of the handler to seek
	 * @return list of handlers (which may be empty)
	 */
	public ArrayList getEventsWithHandlerType(Class clsHandler) {
		ArrayList listResults = new ArrayList();
		for (int i = 0; i < listEvents.size(); i++) {
			ScheduledEvent SE = listEvents.get(i);
			if (SE.getHandler() == null)
				continue;

			if (Tools.IsSubclass(clsHandler, SE.getHandler().getClass()))
				listResults.add(SE);
		}
		return listResults;
	}

	/**
	 * Get the elapsed time since the given time
	 * 
	 * @param lTime time to start the elapse
	 * @return number of nanoseconds elapsed since lTime
	 */
	public static long getElapsedTime(long lTime) {
		// Time now
		long lTimeNow = System.nanoTime();

		// if now is less than then, the long overflowed. Get the
		// correct value back
		if (lTimeNow < lTime)
			return (Long.MAX_VALUE - lTime) + lTimeNow;
		return lTimeNow - lTime;
	}

	/**
	 * Get the current running time (total) of the game
	 * 
	 * @return total time the game has run (not including pause time)
	 */
	public long getGameRunTime() {
		return this.lGameRunTime;
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
