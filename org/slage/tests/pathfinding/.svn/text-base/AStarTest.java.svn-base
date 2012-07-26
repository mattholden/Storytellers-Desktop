/*
 * Created on Oct 22, 2005
 *
 */
package org.slage.tests.pathfinding;

import java.awt.Point;
import java.awt.Polygon;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import junit.framework.TestCase;

import org.slage.pathfinding.PathFinder;
import org.slage.pathfinding.PointBank;
import org.slage.pathfinding.RoutesMap;
import org.slage.pathfinding.SparseRoutesMap;
import org.slage.pathfinding.astar.AStarRouteNode;
import org.slage.pathfinding.astar.AStarSearchWithRoutesMap;

public class AStarTest
		extends TestCase {

	public void testAStar() {

		Polygon bo = new Polygon();
		bo.addPoint(10, 10);
		bo.addPoint(20, 10);
		bo.addPoint(20, 20);
		bo.addPoint(10, 20);

		Polygon bo2 = new Polygon();
		bo2.addPoint(10, 30);
		bo2.addPoint(20, 30);
		bo2.addPoint(20, 40);
		bo2.addPoint(10, 40);

		Polygon fun = new Polygon();
		fun.addPoint(6, 45);
		fun.addPoint(20, 45);
		fun.addPoint(12, 47);
		fun.addPoint(20, 50);
		fun.addPoint(10, 50);

		// List<Polygon> someObstacles = new ArrayList<Polygon>();
		// BisectingPolygonRouteBuilder builder = new
		// BisectingPolygonRouteBuilder();
		// RoutesMap map = new SparseRoutesMap();
		// TreeSet<BisectingResultSet> set = new TreeSet<BisectingResultSet>(new
		// Comparator<BisectingResultSet>() {
		// public int compare(BisectingResultSet o1, BisectingResultSet o2) {
		// return o1.distance - o2.distance;
		// }
		// });
		// someObstacles.add(bo);
		// someObstacles.add(bo2);
		//		
		// for (Polygon p : someObstacles) {
		// int[] result = builder.bisect(p, new double[] { aStart.x, aStart.y }, new
		// double[] { anEnd.x, anEnd.y }, map);
		// set.add(new BisectingResultSet(new int[] { result[0], result[1] }, new
		// int[] { result[2], result[3] }, (int) Geometry.length(aStart.x, aStart.y,
		// result[0], result[1])));
		// }
		// int[] lastSeg = new int[] { aStart.x, aStart.y };
		// for (BisectingResultSet b : set) {
		// map.addDirectRoute(PointBank.getPoint(lastSeg[0], lastSeg[1]),
		// PointBank.getPoint(b.start[0], b.start[1]), (int)
		// Geometry.length(lastSeg[0], lastSeg[1], b.start[0], b.start[1]));
		// lastSeg[0] = b.end[0];
		// lastSeg[1] = b.end[1];
		// }
		// map.addDirectRoute(PointBank.getPoint(lastSeg[0], lastSeg[1]),
		// PointBank.getPoint(anEnd.x, anEnd.y), (int) Geometry.length(lastSeg[0],
		// lastSeg[1], anEnd.x, anEnd.y));
		Point start = PointBank.getPoint(15, 5);
		Point end = PointBank.getPoint(15, 60);
		Collection<Polygon> list = new ArrayList<Polygon>();
		list.add(bo);
		list.add(bo2);
		list.add(fun);
		RoutesMap map = new SparseRoutesMap(list, start, end);
		PathFinder<AStarRouteNode> finder = new AStarSearchWithRoutesMap(map);
		List<AStarRouteNode> l = null;
		l = finder.find(new AStarRouteNode(start), new AStarRouteNode(end));
		for (AStarRouteNode node : l) {
			System.out.println(node.p.x + "," + node.p.y);
		}
	}

}
