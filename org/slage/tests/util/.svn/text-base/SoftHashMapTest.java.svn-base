/*
 * Created on Nov 12, 2005
 *
 */
package org.slage.tests.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slage.util.SoftHashMap;

import junit.framework.TestCase;

public class SoftHashMapTest extends TestCase {

	private static boolean isEmpty(Map map) {
		return map.get("One") == null && map.get("Two") == null && map.get("Three") == null;
  }
	private static boolean isFull(Map map) {
		return map.get("One") != null && map.get("Two") != null && map.get("Three") != null;
  }

	/**
	 * The Map should unreference objects (set them to null in the map) when they
	 * get garbage collected.
	 *
	 */
	public void testSoftHashMap() {
		Map<String, byte[]> map = new SoftHashMap<String, byte[]>();
		// Put 3 5mb resources into the map.
		map.put("One", new byte[10 * 1024 * 512]);
		map.put("Two", new byte[10 * 1024 * 512]);
		map.put("Three", new byte[10 * 1024 * 512]);
		// Assert that all 3 values are present in our map
		assertTrue(isFull(map));
		List l = new ArrayList();
		// While the map isn't empty...
		while(!isEmpty(map)){
			// malloc some more heap, and make hard references to it.
			l.add(new byte[10 * 1024 * 512]);
		}
		//If we reach here, our map has to have been emptied out by the garbage collector!
		assertTrue(isEmpty(map));
		
	}
	
	/**
	 * The Map should remove unreferenced objects when it's size is checked too.
	 *
	 */
	public void testSoftHashMapSize() {
		Map<String, byte[]> map = new SoftHashMap<String, byte[]>();
		// Put 3 5mb resources into the map.
		map.put("One", new byte[10 * 1024 * 512]);
		map.put("Two", new byte[10 * 1024 * 512]);
		map.put("Three", new byte[10 * 1024 * 512]);
		// Assert that all 3 values are present in our map
		assertTrue(map.size() == 3);
		List l = new ArrayList();
		// While the map isn't empty...
		while(map.size() > 0){
			// malloc some more heap, and make hard references to it.
			l.add(new byte[10 * 1024 * 512]);
		}
		//If we reach here, our map has to have been emptied out by the garbage collector!
		assertTrue(map.size() == 0);
	}
}
