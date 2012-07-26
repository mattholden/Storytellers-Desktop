package org.slage.pathfinding.astar;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.slage.geometry.Geometry;
import org.slage.pathfinding.PathFinder;
import org.slage.pathfinding.RoutesMap;

/**
 * The AStarSearchWithRoutesMap class is a PathFinder that finds a path in a
 * RoutesMap using an A* search algorithm.
 */
public class AStarSearchWithRoutesMap
		extends AStarSearch<AStarRouteNode> implements PathFinder<AStarRouteNode> {

	RoutesMap map;

	public AStarSearchWithRoutesMap(RoutesMap aMap) {
		map = aMap;
	}

	public List<AStarRouteNode> find(AStarRouteNode start, AStarRouteNode goal) {
		if (start.equals(goal) || !Geometry.intersects(start.p, goal.p, map.getObstacles())) {
			return Collections.singletonList(goal);
		}
		List<AStarRouteNode> path = super.findPath(start, goal);
		optimizeList(path);
		return path;
	}

	public List<AStarRouteNode> getNegibors(AStarRouteNode aNode) {
		Collection<Point> c = map.getDestinations(aNode.p);
		List l = new ArrayList();
		if (c == null) {
			return l;
		}
		for (Point po : c) {
			AStarRouteNode asrn = new AStarRouteNode(po);
			asrn.pathParent = aNode;
			l.add(asrn);
		}
		return l;
	}

	private static void optimizeList(List<AStarRouteNode> aList) {
		AStarRouteNode a = null;
		AStarRouteNode b = null;
		AStarRouteNode c = null;
		for (AStarRouteNode node : aList) {
			a = b;
			b = c;
			c = node;
			if (a != null && b != null && c != null) {
				if ((a.p.x == b.p.x && b.p.x == c.p.x) || (a.p.y == b.p.y && b.p.y == c.p.y)) {
					aList.remove(b);
					optimizeList(aList);
					return;
				}
			}
		}
	}
}
