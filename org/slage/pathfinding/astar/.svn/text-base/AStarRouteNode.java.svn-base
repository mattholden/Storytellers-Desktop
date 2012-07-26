package org.slage.pathfinding.astar;

import java.awt.Point;

import org.slage.geometry.Geometry;

/**
 * The AStarRouteNode class is an AStarNode that repesents a location in
 * RoutesMap. Used for the start and goal nodes of a search.
 */
public class AStarRouteNode
		extends AStarNode<AStarRouteNode> {
	public Point p;

	public AStarRouteNode(Point aPoint) {
		p = aPoint;
	}

	public double getCost(AStarRouteNode node) {
		return Geometry.length(p.x, p.y, node.p.x, node.p.y);
	}

	public double getEstimatedCost(AStarRouteNode node) {
		return getCost(node);
	}

	public boolean equals(Object obj) {
		return p.equals(((AStarRouteNode) obj).p);
	}
}