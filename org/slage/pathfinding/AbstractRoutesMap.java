/*
 * Created on Oct 23, 2005
 *
 */
package org.slage.pathfinding;

import java.awt.Point;
import java.awt.Polygon;
import java.util.Collection;

import org.slage.geometry.Geometry;
import org.slage.geometry.PolygonPathVisitor;

public abstract class AbstractRoutesMap implements RoutesMap {

	public static class WeightedPolygon {
		int weight;
		Polygon p;

		/**
		 * @param aP
		 * @param aWeight
		 */
		public WeightedPolygon(Polygon aP, int aWeight) {
			super();
			// TODO Auto-generated constructor stub
			this.p = aP;
			this.weight = aWeight;
		}

	}

	public void addRoutesFromPointToPolygon(Point aPoint, Polygon aPolygon, Collection<Polygon> someObstacles) {
		PolygonPathVisitor visitor = new PolygonPathVisitor(aPolygon);
		int[] seg = visitor.getCurrentSegment();
		while (visitor.hasNext()) {
			if (seg[0] == aPoint.x && seg[1] == aPoint.y) {
				seg = visitor.nextSegment();
				continue;
			}
			Point expand = Geometry.contractPoint(PointBank.getPoint(seg[0], seg[1]), aPoint);
			Point expand2 = Geometry.contractPoint(aPoint, PointBank.getPoint(seg[0], seg[1]));
			if (!Geometry.intersects(expand, expand2, someObstacles)) {
				addRoute(aPoint, PointBank.getPoint(seg[0], seg[1]));
			}
			seg = visitor.nextSegment();
		}
		if (seg[0] == aPoint.x && seg[1] == aPoint.y) {
			return;
		}
		Point expand = Geometry.contractPoint(PointBank.getPoint(seg[0], seg[1]), aPoint);
		Point expand2 = Geometry.contractPoint(aPoint, PointBank.getPoint(seg[0], seg[1]));
		if (!Geometry.intersects(expand, expand2, someObstacles)) {
			addRoute(aPoint, PointBank.getPoint(seg[0], seg[1]));
		}
	}

	public void addRoutesFromPolygonToPoint(Point aPoint, Polygon aPolygon, Collection<Polygon> someObstacles) {
		PolygonPathVisitor visitor = new PolygonPathVisitor(aPolygon);
		int[] seg = visitor.getCurrentSegment();
		while (visitor.hasNext()) {
			if (seg[0] == aPoint.x && seg[1] == aPoint.y) {
				seg = visitor.nextSegment();
				continue;
			}
			Point expand = Geometry.contractPoint(PointBank.getPoint(seg[0], seg[1]), aPoint);
			Point expand2 = Geometry.contractPoint(aPoint, PointBank.getPoint(seg[0], seg[1]));
			if (!Geometry.intersects(expand, expand2, someObstacles)) {
				addRoute(PointBank.getPoint(seg[0], seg[1]), aPoint);
			}
			seg = visitor.nextSegment();
		}
		if (seg[0] == aPoint.x && seg[1] == aPoint.y) {
			return;
		}
		Point expand = Geometry.contractPoint(PointBank.getPoint(seg[0], seg[1]), aPoint);
		Point expand2 = Geometry.contractPoint(aPoint, PointBank.getPoint(seg[0], seg[1]));
		if (!Geometry.intersects(expand, expand2, someObstacles)) {
			addRoute(PointBank.getPoint(seg[0], seg[1]), aPoint);
		}
	}

	public void addAllRoutes(Polygon aPolygon, Collection<Polygon> someObstacles) {
		PolygonPathVisitor visitor = new PolygonPathVisitor(aPolygon);
		int[] seg = visitor.getCurrentSegment();
		while (visitor.hasNext()) {
			addRoute(PointBank.getPoint(seg[0], seg[1]), PointBank.getPoint(seg[2], seg[3]));
			addRoute(PointBank.getPoint(seg[2], seg[3]), PointBank.getPoint(seg[0], seg[1]));
			addRoutesFromPointToPolygon(PointBank.getPoint(seg[0], seg[1]), aPolygon, someObstacles);
			seg = visitor.nextSegment();
		}
		addRoute(PointBank.getPoint(seg[0], seg[1]), PointBank.getPoint(seg[2], seg[3]));
		addRoute(PointBank.getPoint(seg[2], seg[3]), PointBank.getPoint(seg[0], seg[1]));
		addRoutesFromPointToPolygon(PointBank.getPoint(seg[0], seg[1]), aPolygon, someObstacles);
	}
}
