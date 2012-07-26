/*
 * Created on Oct 15, 2005
 *
 */
package org.slage.tests;

import java.awt.Point;

import junit.framework.TestCase;

import org.slage.Boundary;

public class GeometryTest
		extends TestCase {

	@SuppressWarnings("boxing")
	public void testLineIntersectsPolygon() {
		Boundary b = new Boundary();
		b.addPoint(10, 10);
		b.addPoint(20, 10);
		b.addPoint(20, 20);
		b.addPoint(10, 20);
		double[] d = b.findLineIntersections(new Point(15, 5), new Point(15, 25));
		for (int i = 0; i < d.length; i += 2) {
			assertEquals(15.0, d[0]);
			assertEquals(10.0, d[1]);
			assertEquals(15.0, d[2]);
			assertEquals(20.0, d[3]);
		}
	}
}
