/*
 * Created on Jul 22, 2003
 */
package org.slage.pathfinding.handler;

import org.apache.log4j.Logger;

/**
 * @author Kevlar
 * 
 * A class to move an object from Point A to Point B in a specified time.
 */
public class TimedVelocity implements Velocity {
	private static final Logger logger = Logger.getLogger(TimedVelocity.class);
	int startX;
	int startY;
	int startZ;
	int endX;
	int endY;
	int endZ;
	long overMilliseconds;
	int count;
	double unitsPerTickX = 0.0;
	double unitsPerTickY = 0.0;
	double unitsPerTickZ = 0.0;
	boolean cachedUnitsPerTickX = false;
	boolean cachedUnitsPerTickY = false;
	boolean cachedUnitsPerTickZ = false;
	protected boolean done = false;
	public static final long MILLISECONDS_PER_TICK = 30;

	public TimedVelocity(int aStartX, int aStartY, int aStartZ, int aEndX, int aEndY, int aEndZ, long someMilliseconds) {
		startX = aStartX;
		startY = aStartY;
		startZ = aStartZ;
		endX = aEndX;
		endY = aEndY;
		endZ = aEndZ;
		overMilliseconds = someMilliseconds;
		logger.debug("Making a new TimedVelocity from " + startX + "," + startY + " to " + endX + "," + endY + " over " + someMilliseconds + " milliseconds.");
	}

	public void backtrack() {
		if (count > 0) {
			count--;
		}
	}

	public void advance() {
		if (count == 0 || count < ((endX - startX) / unitsPerTickX) || count < ((endY - startY) / unitsPerTickY) || count < ((endZ - startZ) / unitsPerTickZ)) {
			count++;
		} else {
			logger.debug("Done!");
			done = true;
		}
	}

	public int getCurrentX() {
		if (endX == startX) {
			return endX;
		}
		int nextX = new Float(getUnitsPerTickX() * count).intValue() + startX;
		if (endX > startX) {
			return Math.min(endX, nextX);
		}
		return Math.max(endX, nextX);
	}

	public int getCurrentY() {
		if (endY == startY) {
			return endY;
		}
		int nextY = new Float(getUnitsPerTickY() * count).intValue() + startY;
		if (endY > startY) {
			return Math.min(endY, nextY);
		}
		return Math.max(endY, nextY);
	}

	public int getCurrentZ() {
		if (endZ == startZ) {
			return endZ;
		}
		int nextZ = new Float(getUnitsPerTickZ() * count).intValue() + startZ;
		if (endZ > startZ) {
			return Math.min(endY, nextZ);
		}
		return Math.max(endY, nextZ);
	}

	public double getUnitsPerTickX() {
		if (cachedUnitsPerTickX) {
			return unitsPerTickX;
		}
		cachedUnitsPerTickX = true;
		if (overMilliseconds == 0) {
			return unitsPerTickX = endX - startY;
		}
		return unitsPerTickX = (new Double(endX).doubleValue() - new Double(startX).doubleValue()) / (new Double(overMilliseconds).doubleValue() / new Double(MILLISECONDS_PER_TICK).doubleValue());
	}

	public double getUnitsPerTickY() {
		if (cachedUnitsPerTickY) {
			return unitsPerTickY;
		}
		cachedUnitsPerTickY = true;
		if (overMilliseconds == 0) {
			return unitsPerTickY = endY - startY;
		}
		return unitsPerTickY = (new Double(endY).doubleValue() - new Double(startY).doubleValue()) / (new Double(overMilliseconds).doubleValue() / new Double(MILLISECONDS_PER_TICK).doubleValue());
	}

	public double getUnitsPerTickZ() {
		if (cachedUnitsPerTickZ) {
			return unitsPerTickZ;
		}
		cachedUnitsPerTickZ = true;
		if (overMilliseconds == 0) {
			return unitsPerTickZ = endZ - startZ;
		}
		return unitsPerTickZ = (new Double(endX).doubleValue() - new Double(startX).doubleValue()) / (new Double(overMilliseconds).doubleValue() / new Double(MILLISECONDS_PER_TICK).doubleValue());
	}

	public long getOverMilliseconds() {
		return overMilliseconds;
	}

	public int getStartX() {
		return startX;
	}

	public int getStartY() {
		return startY;
	}

	public int getStartZ() {
		return startZ;
	}

	public void setCount(int i) {
		count = i;
	}

	public void setEndX(int i) {
		endX = i;
	}

	public void setEndY(int i) {
		endY = i;
	}

	public void setEndZ(int i) {
		endZ = i;
	}

	public void setOverMilliseconds(long l) {
		overMilliseconds = l;
	}

	public void setStartX(int i) {
		startX = i;
	}

	public void setStartY(int i) {
		startY = i;
	}

	public void setStartZ(int i) {
		startZ = i;
	}

	public boolean isDone() {
		return done;
	}
}
