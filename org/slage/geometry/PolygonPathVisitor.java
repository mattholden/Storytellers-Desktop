/*
 * Created on Oct 15, 2005
 *
 */
package org.slage.geometry;

import java.awt.Polygon;

/** TODO javadoc */
public class PolygonPathVisitor {

	private final Polygon polygon;
	private boolean loop;
	private int index = 0;

	public PolygonPathVisitor(Polygon aPolygon) {
		polygon = aPolygon;
		loop = false;
	}

	public int[] getCurrentSegment() {
		int[] curSeg = new int[4];
		currentSegment(curSeg);
		return curSeg;
	}

	public boolean hasNext() {
		return index < (polygon.npoints - 1);
	}

	public Polygon getPolygon() {
		return polygon;
	}

	public void reset() {
		index = 0;
	}

	public int[] nextSegment() {
		if (!hasNext()) {
			if (loop) {
				index = -1;
			} else {
				throw new IndexOutOfBoundsException();
			}
		}
		index++;

		return getCurrentSegment();
	}

	public void currentSegment(int[] curSeg) {
		curSeg[0] = polygon.xpoints[index];
		curSeg[1] = polygon.ypoints[index];
		curSeg[2] = polygon.xpoints[index == (polygon.npoints - 1) ? 0 : index + 1];
		curSeg[3] = polygon.ypoints[index == (polygon.npoints - 1) ? 0 : index + 1];
	}

	/**
	 * @return Returns the loop.
	 */
	public boolean isLoop() {
		return loop;
	}

	/**
	 * @param toLoop The loop to set.
	 */
	public void setLoop(boolean toLoop) {
		this.loop = toLoop;
	}
}
