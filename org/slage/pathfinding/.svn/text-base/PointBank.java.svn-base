/*
 * Created on Oct 16, 2005
 *
 */
package org.slage.pathfinding;

import java.awt.Point;
import java.util.HashMap;
import java.util.Map;

public class PointBank {
	private static Map<String, Point> verticies = new HashMap<String, Point>();

	public static Point getPoint(int anX, int aY) {
		Point v = verticies.get(anX + "," + aY);
		if (v == null) {
			v = new Point(anX, aY);
			verticies.put(anX + "," + aY, v);
		}
		return v;
	}
}
