package org.slage.pathfinding.astar;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeSet;

/**
 * The AStarSearch class, along with the AStarNode class, implements a generic
 * A* search algorthim. The AStarNode class should be subclassed to provide
 * searching capability.
 */
public abstract class AStarSearch<T extends AStarNode<T>> {

	/**
	 * Construct the path, not including the start node.
	 */
	protected List constructPath(T node) {
		LinkedList<T> path = new LinkedList<T>();
		while (node.pathParent != null) {
			path.addFirst(node);
			node = node.pathParent;
		}
		return path;
	}

	/**
	 * Find the path from the start node to the end node. A list of AStarNodes is
	 * returned, or null if the path is not found.
	 */
	public List findPath(T startNode, T goalNode) {

		TreeSet<T> openList = new TreeSet(new Comparator<T>() {
			public int compare(T o1, T o2) {
				return o1.compareTo(o2);
			}
		});
		LinkedList<T> closedList = new LinkedList<T>();

		startNode.costFromStart = 0;
		startNode.estimatedCostToGoal = startNode.getEstimatedCost(goalNode);
		startNode.pathParent = null;
		openList.add(startNode);

		while (!openList.isEmpty()) {
			T node = openList.first();
			openList.remove(node);
			if (node.equals(goalNode)) {
				// construct the path from start to goal
				return constructPath(node);
			}

			List<T> neighbors = getNegibors(node);
			for (T neighborNode : neighbors) {
				boolean isOpen = openList.contains(neighborNode);
				boolean isClosed = closedList.contains(neighborNode);
				double costFromStart = node.costFromStart + node.getCost(neighborNode);

				// check if the neighbor node has not been
				// traversed or if a shorter path to this
				// neighbor node is found.
				if ((!isOpen && !isClosed) || costFromStart < neighborNode.costFromStart) {
					neighborNode.pathParent = node;
					neighborNode.costFromStart = costFromStart;
					neighborNode.estimatedCostToGoal = neighborNode.getEstimatedCost(goalNode);
					if (isClosed) {
						closedList.remove(neighborNode);
					}
					if (!isOpen) {
						openList.add(neighborNode);
					}
				}
			}
			closedList.add(node);
		}

		// no path found
		return null;
	}

	public abstract List<T> getNegibors(T aNode);

}
