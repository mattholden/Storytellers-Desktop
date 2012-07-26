package org.slage.pathfinding;

import java.util.List;

import org.slage.pathfinding.astar.AStarNode;

/**
 * The PathFinder interface is a function that finds a path from one location to
 * another.
 */
public interface PathFinder<T extends AStarNode> {

	/**
	 * Finds a path from T a to T b. The path is an List of T's, not including the
	 * start location (T a) but including the goal location (T b). Returns null if
	 * no path found.
	 */
	public List<T> find(T a, T b);
}
