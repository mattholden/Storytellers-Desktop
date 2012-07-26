package org.slage.pathfinding;

import java.awt.Point;
import java.awt.Polygon;
import java.util.Collection;

/**
 * This interface defines the object storing the graph of all routes in the
 * system.
 * 
 */

public interface RoutesMap {

	/**
	 * Enter a new segment in the graph.
	 */
	public void addRoute(Point start, Point end);

	/**
	 * Get the list of cities that can be reached from the given polyPoint.
	 */
	public Collection<Point> getDestinations(Point aPolyPoint);

	public Collection<Polygon> getObstacles();

}
