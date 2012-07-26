package org.slage.pathfinding.astar;

/**
 * The AStarNode class, along with the AStarSearch class, implements a generic
 * A* search algorthim. The AStarNode class should be subclassed to provide
 * searching capability, and templated upon the subclassed type so it can hold
 * it's parent without loosing it's type.
 */
public abstract class AStarNode<T extends AStarNode> implements Comparable<T> {

	T pathParent;
	double costFromStart;
	double estimatedCostToGoal;

	public double getCost() {
		return costFromStart + estimatedCostToGoal;
	}

	public int compareTo(T other) {
		double otherValue = other.getCost();
		double thisValue = this.getCost();

		return (int) (thisValue - otherValue);
	}

	/**
	 * Gets the cost between this node and the specified adjacent (aka "neighbor"
	 * or "child") node.
	 */
	public abstract double getCost(T node);

	/**
	 * Gets the estimated cost between this node and the specified node. The
	 * estimated cost should never exceed the true cost. The better the estimate,
	 * the more effecient the search.
	 */
	public abstract double getEstimatedCost(T node);

}
