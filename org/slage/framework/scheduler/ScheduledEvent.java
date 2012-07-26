/*
 * ScheduledEvent.java
 *
 * Created on July 31, 2005, 10:07 PM
 */

package org.slage.framework.scheduler;
import org.slage.framework.FrameworkHandler;

/**
 * Base class for schedulable Events.
 * 
 * @author <a href="mailto:Matt@SQ7.org">Matt Holden</a>
 */
public class ScheduledEvent implements Runnable {

	/** Logger instance */
	protected static final org.apache.commons.logging.Log LOG = org.apache.commons.logging.LogFactory.getLog(ScheduledEvent.class);

	/** If 'true', we should destroy the event once it is run */
	private boolean bDestroyOnRun = true;

	/** The time remaining before we fire */
	private long lTimeLeft;

	/** Time of the last update */
	private long lLastUpdate;

	/** The FrameworkHandler to track */
	private FrameworkHandler handler;

	/** Priority (this is how events are sorted for calling order in the scheduler */
	private int iPriority;

	/**
	 * Check if we should destroy this object when it is run
	 * 
	 * @return bDestroyOnRun
	 */
	public boolean destroyOnRun() {
		return bDestroyOnRun;
	}

	/**
	 * Set whether we should destroy this object when it is run
	 * 
	 * @param bDestroy 'true' if it should be destroyed
	 */
	public void setDestroyOnRun(boolean bDestroy) {
		this.bDestroyOnRun = bDestroy;
	}

	/**
	 * Accessor for priority
	 * 
	 * @return iPriority
	 */
	public int getPriority() {
		return iPriority;
	}

	/**
	 * Mutator for priority
	 * 
	 * @param aPriority priority
	 */
	public void setPriority(int aPriority) {
		this.iPriority = aPriority;
	}

	/**
	 * Accessor for the FrameworkHandler object
	 * 
	 * @return handler
	 */
	public FrameworkHandler getHandler() {
		return handler;
	}

	/**
	 * Accessor for time to fire
	 * 
	 * @return lTimeToFire
	 */
	public long getTimeLeft() {
		return lTimeLeft;
	}

	/** Has this event been run? */
	private boolean bHasRun = false;

	/**
	 * Has the event been run?
	 * 
	 * @return true if it has
	 */
	public boolean hasRun() {
		return bHasRun;
	}

	/**
	 * Checks if it is time to fire the event
	 * 
	 * @return 'true' if it's action time!
	 */
	public boolean isReadyToRun() {
		return (lTimeLeft <= 0);
	}

	/** Update the "last fired" to reflect "now" when we resume from pause */
	public void resumeAfterPause() {
		this.lLastUpdate = System.nanoTime();
	}

	/** An every-frame update */
	public void update() {
		lTimeLeft -= Scheduler.getElapsedTime(lLastUpdate);
		lLastUpdate = System.nanoTime();
	}

	/**
	 * Static method to retrieve the number of nanoseconds for an interval given
	 * the number of desired frames per second
	 * 
	 * @param iFPS desired FPS
	 * @return nanosecond interval between frames
	 */
	public static long getNanoInterval(int iFPS) {
		return (long) (NANOSEC_IN_SEC * (1.0f / iFPS));
	}

	/** Number of nanosec in a sec */
	public static final long NANOSEC_IN_SEC = 1000000000L;

	/** Run the timed object if it's time */
	public void run() {
		bHasRun = true;
		handler.execute();
	}

	/**
	 * Set the FrameworkHandler
	 * 
	 * @param h the new FrameworkHandler
	 */
	public void setHandler(FrameworkHandler h) {
		this.handler = h;
	}

	/**
	 * Set the time remaining before the object fires
	 * 
	 * @param lTime time until fire (as nanoseconds FROM NOW)
	 */
	public void setTimeLeft(long lTime) {
		this.lTimeLeft = lTime;
	}

	/**
	 * Creates a new instance of ScheduledEvent
	 * 
	 * @param h FrameworkHandler to schedule
	 * @param lWhen The time, in NANOseconds, FROM NOW, at which to fire the event
	 * @param aPriority priority in the call order
	 */
	public ScheduledEvent(FrameworkHandler h, long lWhen, int aPriority) {
		this.handler = h;
		lTimeLeft = lWhen;
		this.iPriority = aPriority;
		lLastUpdate = System.nanoTime();
	}

	/** Class for comparing events */
	public static class EventComparator implements java.util.Comparator {
		/**
		 * See which has the higher priority
		 * 
		 * @param obj an event
		 * @param obj1 another event
		 * @return -1 if obj is lower, 1 if obj1 is lower, 0 if they are equal
		 */
		public int compare(Object obj, Object obj1) {
			int i0 = ((ScheduledEvent) obj).getPriority();
			int i1 = ((ScheduledEvent) obj1).getPriority();

			if (i0 > i1)
				return -1;
			if (i1 > i0)
				return 1;
			return 0;
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
