/*
 * Room_Test.java
 *
 * Created on September 8, 2005, 7:47 PM
 */

package org.slage.tests;

import org.slage.Boundary;
import org.slage.Room;
import org.slage.SlageGame;
import org.slage.SlageObject;
import org.slage.framework.Point3D;
import org.slage.SlageImage;

/**
 * Junit test for Rooms.
 * 
 * Since Rooms have very little real functionality, this test inherits from
 * SlageObject_Test.
 * 
 * 
 * TODO: write a test for testCollision() when we have a real command handler.
 * The best way I see to do this is to make 2 objects collide, and then ensure
 * that the last command in the history matches a __COLLIDESWITH with the two
 * objects given.
 * 
 * @author Jaeden
 */
public class Room_Test
		extends SlageObject_Test {

	/**
	 * Creates a new instance of Room_Test
	 * 
	 * @param strFunc function
	 */
	public Room_Test(String strFunc) {
		super(strFunc);
	}

	/** The game to own the room */
	private SlageGame game;

	/** the room to test */
	private Room room;

	/** Set up the tests */
	public void setUp() throws Exception {
		super.setUp();

		game = new SlageGame("test game");
		room = new Room("Test Room");
		game.addObject(room);

	}

	/** Tear down the tests */
	public void tearDown() throws Exception {
		super.tearDown();
		room = null;
		game = null;
	}

	/**
	 * Draw list functionality test
	 * 
	 * Tests: - addObject() - removeObject() - buildDrawList() [via
	 * SlageGame.setRoom())
	 */
	public void testDrawList() throws Exception {
		// these 3 should be null
		room.addObject(new SlageObject("thingy"));
		SlageObject foo = new SlageObject("foo");
		room.addObject(foo);
		room.addObject(new SlageObject("twidget"));

		// haven't added anything yet
		assertEquals(room.getContainedObjectCount(), 3);
		assertEquals(game.getDrawListSize(), 0);

		// setting the room should yield 4 objects (3 contained
		// + the room itself
		game.setRoom(room);
		assertEquals(game.getDrawListSize(), 4);

		// adding an object when it's the selected room
		// should add to draw list
		SlageObject cat = new SlageObject("catalyst");
		room.addObject(cat);
		assertEquals(room.getContainedObjectCount(), 4);
		assertEquals(game.getDrawListSize(), 5);

		// removing an object when it's the selected room
		// should remove from draw list
		room.removeObject(cat);
		assertEquals(room.getContainedObjectCount(), 3);
		assertEquals(game.getDrawListSize(), 4);

		// set a new room with no objects
		// now draw list should be 1, contained obj still 3
		Room blerg = new Room("blerg");
		game.addObject(blerg);
		game.setRoom(blerg);

		assertEquals(room.getContainedObjectCount(), 3);
		assertEquals(game.getDrawListSize(), 1); // just the new room

		// removing an item now shouldn't change the draw list
		room.removeObject(foo);
		assertEquals(room.getContainedObjectCount(), 2);
		assertEquals(game.getDrawListSize(), 1); // just the new room

	}

	/**
	 * Test the room's dimensions
	 * 
	 * Tests: - getDimensions() - setDimensions() [ 2 versions ] -
	 * getScrollStartX() - getScrollStartY() - setViewportStart() -
	 * setSceneImage()
	 */
	public void testDimensions() throws Exception {
		game.setRoom(room);

		java.awt.Rectangle R = room.getDimensions();

		// prove it was constructed right
		assertNotNull(R);
		assertEquals(0, R.x);
		assertEquals(0, R.y);
		assertEquals(1024, R.width);
		assertEquals(768, R.height);

		// set the scroll start (R.x and R.y)
		room.setViewportStart(25, 50);

		R = room.getDimensions();
		assertNotNull(R);
		assertEquals(25, R.x);
		assertEquals(25, room.getScrollStartX());
		assertEquals(50, room.getScrollStartY());
		assertEquals(50, R.y);
		assertEquals(1024, R.width);
		assertEquals(768, R.height);

		room.setDimensions(800, 600);

		R = room.getDimensions();
		assertNotNull(R);
		assertEquals(25, R.x);
		assertEquals(25, room.getScrollStartX());
		assertEquals(50, room.getScrollStartY());
		assertEquals(50, R.y);
		assertEquals(800, R.width);
		assertEquals(600, R.height);

		room.setDimensions(new java.awt.Rectangle(0, 0, 1024, 768));
		R = room.getDimensions();
		assertNotNull(R);
		assertEquals(0, R.x);
		assertEquals(0, R.y);
		assertEquals(1024, R.width);
		assertEquals(768, R.height);

		SlageImage burger = new SlageImage("org?slage_tests?content?monolith_scrollhall.jpg");

		room.setSceneImage(burger);

		R = room.getDimensions();
		assertNotNull(R);
		assertEquals(0, R.x);
		assertEquals(0, R.y);
		assertEquals(burger.getImageIcon().getIconWidth(), R.width);
		assertEquals(burger.getImageIcon().getIconHeight(), R.height);

	}

	/**
	 * Test boundary functions on the room level
	 * 
	 * Tests: - getClickedObject()
	 */
	public void testClickBoundaries() throws Exception {
		game.setRoom(room);

		SlageObject obj1 = new SlageObject("1");
		SlageObject obj2 = new SlageObject("2");
		SlageObject obj3 = new SlageObject("3");

		Point3D[] click1 = { new Point3D(0, 0, 1), new Point3D(10, 0, 1), new Point3D(10, 10, 1), new Point3D(0, 10, 1), };
		obj1.setClickBoundary(new Boundary(click1, 1));

		Point3D[] click2 = { new Point3D(5, 5, 1), new Point3D(10, 5, 1), new Point3D(10, 10, 1), new Point3D(5, 10, 1), };
		obj2.setClickBoundary(new Boundary(click2, 1));

		Point3D[] click3 = { new Point3D(0, 0, 2), new Point3D(5, 0, 2), new Point3D(5, 5, 2), new Point3D(0, 5, 2), };
		obj3.setClickBoundary(new Boundary(click3, 2));

		SlageObject clicked;

		clicked = room.getClickedObject(1, 1);

		// nothing in the room so you clicked the room
		assertSame(room, clicked);

		// add one object... now we clicked that object
		room.addObject(obj1);
		clicked = room.getClickedObject(1, 1);
		assertSame(clicked, obj1);

		// add another object on half the area, same z
		// should find obj1 since it was added first
		room.addObject(obj2);
		clicked = room.getClickedObject(1, 1);
		SlageObject clicked2 = room.getClickedObject(6, 6);
		assertSame(clicked, obj1);
		assertSame(clicked2, obj1);

		// add a 3rd object.. clicking it should get it, since it has a higher
		// z value
		room.addObject(obj3);
		clicked = room.getClickedObject(1, 1);
		clicked2 = room.getClickedObject(6, 6);
		assertSame(clicked, obj3);
		assertSame(clicked2, obj1);

	}

}
/*******************************************************************************
 * BEGIN LICENSE BLOCK **** Version: MPL 1.1/GPL 2.0/LGPL 2.1
 * 
 * The contents of this file are subject to the Mozilla Public License Version
 * 1.1 (the "License"); you may not use this file except in compliance with the
 * License. You may obtain a copy of the License at http://www.mozilla.org/MPL/
 * 
 * Software distributed under the License is distributed on an "AS IS" basis,
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License for
 * the specific language governing rights and limitations under the License.
 * 
 * The Original Code is Slage.
 * 
 * The Initial Developer of the Original Code is The SQ7.org project. Portions
 * created by the Initial Developer are Copyright (C) 2005 the Initial
 * Developer. All Rights Reserved.
 * 
 * Contributor(s): Matt Holden (Matt@sq7.org) Travis Savo (Travis@sq7.org)
 * Robert Wenner (Robert@sq7.org) Jared Cope (Jared@sq7.org) Colin Davis
 * (Colin@sq7.org)
 * 
 * Alternatively, the contents of this file may be used under the terms of
 * either the GNU General Public License Version 2 or later (the "GPL"), or the
 * GNU Lesser General Public License Version 2.1 or later (the "LGPL"), in which
 * case the provisions of the GPL or the LGPL are applicable instead of those
 * above. If you wish to allow use of your version of this file only under the
 * terms of either the GPL or the LGPL, and not to allow others to use your
 * version of this file under the terms of the MPL, indicate your decision by
 * deleting the provisions above and replace them with the notice and other
 * provisions required by the GPL or the LGPL. If you do not delete the
 * provisions above, a recipient may use your version of this file under the
 * terms of any one of the MPL, the GPL or the LGPL.
 * 
 ******************************************************************************/
