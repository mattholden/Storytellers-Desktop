/*
 * Created on Oct 13, 2005
 *
 */
package org.slage.pathfinding;

import java.awt.Point;
import java.awt.Polygon;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slage.geometry.PolygonPathVisitor;

/**
 * An implementation of a RoutesMap which uses a sparse Map to store the routes
 * and distances.
 * 
 * @author Travis Savo <Kevlar@Sindome.org>
 */
public class SparseRoutesMap
		extends AbstractRoutesMap implements RoutesMap {

	public Map<Point, List<Point>> destinationMap = new HashMap<Point, List<Point>>();

	Collection<Polygon> obstacles;

	/**
	 * Given a set of polygons and a line, make a map of all routes from every
	 * point to every other point that doesn't intersect a polygon. Also include
	 * all lines in the polygon as valid paths.
	 * 
	 * @param someObstacles
	 * @param aStart
	 * @param anEnd
	 */
	public SparseRoutesMap(Collection<Polygon> someObstacles, Point aStart, Point anEnd) {
		int[] seg;
		obstacles = someObstacles;
		for (Polygon p : someObstacles) {
			addRoutesFromPointToPolygon(aStart, p, someObstacles);
			addRoutesFromPolygonToPoint(aStart, p, someObstacles);
			addRoutesFromPolygonToPoint(anEnd, p, someObstacles);
			addRoutesFromPointToPolygon(anEnd, p, someObstacles);
			addAllRoutes(p, someObstacles);
			PolygonPathVisitor visitor = new PolygonPathVisitor(p);
			seg = visitor.getCurrentSegment();
			while (visitor.hasNext()) {
				for (Polygon p2 : someObstacles) {
					addRoutesFromPointToPolygon(PointBank.getPoint(seg[0], seg[1]), p2, someObstacles);
				}
				seg = visitor.nextSegment();
			}
			for (Polygon p2 : someObstacles) {
				addRoutesFromPointToPolygon(PointBank.getPoint(seg[0], seg[1]), p2, someObstacles);
			}
		}
	}

	public Collection<Polygon> getObstacles() {
		return obstacles;
	}

	public void addRoute(Point start, Point end) {
		if (destinationMap.get(start) != null) {
			destinationMap.get(start).add(end);
		} else {
			ArrayList<Point> l = new ArrayList<Point>();
			l.add(end);
			destinationMap.put(start, l);
		}
	}

	public List<Point> getDestinations(Point aPolyPoint) {
		if (!destinationMap.containsKey(aPolyPoint)) {
			return new ArrayList<Point>();
		}
		return destinationMap.get(aPolyPoint);
	}

}
