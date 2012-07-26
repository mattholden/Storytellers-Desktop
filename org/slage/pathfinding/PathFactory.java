/*
 * Created on Oct 25, 2005
 *
 */
package org.slage.pathfinding;

import java.awt.Point;
import java.awt.Polygon;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.jdom.Element;
import org.slage.pathfinding.astar.AStarRouteNode;
import org.slage.pathfinding.astar.AStarSearchWithRoutesMap;

public class PathFactory {

	private static final class PathImpl implements Path {
		private final List<Point> points = new ArrayList<Point>();

		public PathImpl(List<Point> aRoute) {
			points.addAll(aRoute);
		}

		public Point current() {
			return points.get(0);
		}

		public Point next() {
			points.remove(0);
			return current();
		}

		public boolean hasNext() {
			return points.size() > 1;
		}

		public int pointsLeft() {
			return points.size();
		}

		public Element getXMLElement() {
			return getXMLElement("path");
		}

		public Element getXMLElement(String aTagName) {
			Element el = new Element(aTagName);
			for (Point p : points) {
				Element point = new Element("point");
				point.setAttribute("x", p.x + "");
				point.setAttribute("y", p.y + "");
				el.addContent(point);
			}
			return el;
		}

		public PathImpl(Element anElement) {
			List<Element> point = Collections.checkedList(anElement.getChildren(), Element.class);
			for (Element el : point) {
				Point p = new Point();
				p.x = Integer.parseInt(el.getAttributeValue("x"));
				p.y = Integer.parseInt(el.getAttributeValue("y"));
				points.add(p);
			}
		}
	}

	public static Path getPath(Point aStart, Point anEnd, Collection<Polygon> someObstacles) {
		AStarSearchWithRoutesMap search = new AStarSearchWithRoutesMap(new SparseRoutesMap(someObstacles, aStart, anEnd));
		List<AStarRouteNode> list = search.find(new AStarRouteNode(aStart), new AStarRouteNode(anEnd));
		List<Point> points = new ArrayList<Point>();
		for (AStarRouteNode route : list) {
			points.add(route.p);
		}
		return new PathImpl(points);
	}

	public static Path getPath(Element aPath) {
		return new PathImpl(aPath);
	}
}
