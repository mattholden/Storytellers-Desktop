/*
 * SlageObject_Test.java
 *
 * Created on August 18, 2005, 1:28 PM
 */

package org.slage.tests;

import junit.framework.TestCase;

import org.slage.Act;
import org.slage.Boundary;
import org.slage.framework.NotFoundException;
import org.slage.framework.Point3D;
import org.slage.Room;
import org.slage.Scene;
import org.slage.SlageGame;
import org.slage.SlageImage;
import org.slage.SlageObject;
import org.slage.handlers.Handler;
import org.slage.handlers.ResponseHandler;
import org.slage.framework.Attribute;

/**
 * Test for SlageObject and the following support classes: - ClickBoundary (also
 * collide boundaries) - Point3D - Attribute - AttributeList (superclass)
 * 
 * @author Jaeden
 */
public class SlageObject_Test
		extends TestCase {

	/**
	 * Creates a new instance of SlageObject_Test
	 * 
	 * @param strFunc function
	 */
	public SlageObject_Test(String strFunc) {
		super(strFunc);
	}

	/** Object to test with */
	public SlageObject object;

	/** Image to test with */
	public SlageImage image = new SlageImage("org?slage_tests?content?eye.png");

	/**
	 * Tests: - Name set in constructor - getName() - setName() -
	 * equals()
	 */
	public void testName() throws Exception {

		object = new SlageObject("Test Name");
		assertEquals("Test Name", object.getName());

		object.setName("Another Test Name");
		assertEquals("Another Test Name", object.getName());
		
		SlageObject obj2 = new SlageObject("Another Test Name");
		assertTrue(obj2.equals(object));
		assertTrue(object.equals(obj2));
		assertFalse(object.equals(new SlageObject("FOO!")));

	}

	/**
	 * Tests: - original position - setPosition() - getPosition() -
	 * placeAtOrigin() - translate() - setZ()
	 */
	public void testPosition() throws Exception {
		object = new SlageObject("test"); // sets position to 0, 0, 0
		assertEquals(0, object.getPosition().x);
		assertEquals(0, object.getPosition().y);
		assertEquals(0, object.getPosition().z);

		object.setPosition(new Point3D(100, 200, 300));
		assertEquals(100, object.getPosition().x);
		assertEquals(200, object.getPosition().y);
		assertEquals(300, object.getPosition().z);

		object.setZ(500);
		assertEquals(100, object.getPosition().x);
		assertEquals(200, object.getPosition().y);
		assertEquals(500, object.getPosition().z);

		object.placeAtOrigin();
		assertEquals(0, object.getPosition().x);
		assertEquals(0, object.getPosition().y);
		assertEquals(500, object.getPosition().z);

		object.translate(50, 75);
		assertEquals(50, object.getPosition().x);
		assertEquals(75, object.getPosition().y);
		assertEquals(500, object.getPosition().z);

		object.translate(50, -25);
		assertEquals(100, object.getPosition().x);
		assertEquals(50, object.getPosition().y);
		assertEquals(500, object.getPosition().z);
	}

	/**
	 * Tests: - setSceneImage() - getSceneImage() - setCursorImage() -
	 * getCursorImage() - setInvImage() - getInvImage()
	 */
	public void testImages() throws Exception {
		object = new SlageObject("Test");

		object.setSceneImage(image);

		assertNotNull(object.getSceneImage());
		assertEquals(object.getSceneImage().getImageIcon().getIconWidth(), 75);
		assertEquals(object.getSceneImage().getImageIcon().getIconHeight(), 75);

		// set the scale of the scene image
		object.getSceneImage().scale(50, 50);

		assertNotNull(object.getSceneImage());
		assertEquals(object.getSceneImage().getImageIcon().getIconWidth(), 50);
		assertEquals(object.getSceneImage().getImageIcon().getIconHeight(), 50);

		SlageImage icon = object.getCursorImage();
		assertNull(icon);

		// manually set the icon to the original image
		object.setCursorImage(image);

		icon = object.getCursorImage();
		assertEquals(object.getCursorImage().getImageIcon().getIconWidth(), 50);
		assertEquals(object.getCursorImage().getImageIcon().getIconHeight(), 50);

		// for safety - thoroughly prove they work independently
		object.setSceneImage(null);
		object.setCursorImage(null);

		object.setInvImage(image);

		assertNotNull(object.getInvImage());
		assertEquals(object.getInvImage().getImageIcon().getIconHeight(), 50);
		assertEquals(object.getInvImage().getImageIcon().getIconWidth(), 50);

	}

	/**
	 * Tests: - getClickBoundary() - setClickBoundary() - isClicked() -
	 * getCollisionBoundary() - setCollisionBoundary() -
	 * useClickBoundaryForCollision() - useCollisionBoundaryForClicking() -
	 * isCollidingWith - changing position moves the boundaries too
	 */
	public void testBoundaries() throws Exception {

		object = new SlageObject("Test");

		Point3D[] pts = { new Point3D(0, 0, 5), new Point3D(10, 0, 5), new Point3D(10, 10, 5), };

		object.setClickBoundary(new Boundary(pts, 5));
		object.setCollisionBoundary(new Boundary(pts, 3));

		assertEquals(3, object.getClickBoundary().npoints);
		assertEquals(5, object.getClickBoundary().getZ());
		assertEquals(3, object.getCollisionBoundary().npoints);
		assertEquals(3, ((Boundary) object.getCollisionBoundary()).getZ());

		object.useClickBoundaryForCollision();
		assertEquals(5, ((Boundary) object.getCollisionBoundary()).getZ());

		object.getClickBoundary().setZ(6);
		assertEquals(6, object.getClickBoundary().getZ());
		((Boundary) object.getCollisionBoundary()).setZ(2);
		assertEquals(2, ((Boundary) object.getCollisionBoundary()).getZ());

		object.useCollisionBoundaryForClicking();
		assertEquals(2, object.getClickBoundary().getZ());

		assertTrue(object.getClickBoundary().contains(new java.awt.Point(2, 2)));
		assertFalse(object.getClickBoundary().contains(new java.awt.Point(20, 20)));
		assertTrue(object.isClicked(2, 2));
		assertFalse(object.isClicked(20, 20));

		// the triangle should now be from 15, 15 to 25, 15 to 25, 25
		object.setPosition(new Point3D(15, 15, 6));
		assertTrue(object.isClicked(20, 20));
		assertFalse(object.isClicked(2, 2));

		object.placeAtOrigin();
		assertTrue(object.isClicked(2, 2));
		assertFalse(object.isClicked(20, 20));

		// the triangle should now be from 15, 15 to 25, 15 to 25, 25
		object.translate(15, 15);
		assertTrue(object.isClicked(20, 20));
		assertFalse(object.isClicked(2, 2));

	}

	/**
	 * Tests - getAttributeAs_____() setAttribute() setAttribute() clearAttributes()
	 * getAttributeCount() removeAttribute()
	 */
	
	@SuppressWarnings("boxing")
	public void testAttributes() throws Exception {
		object = new SlageObject("Attribute Test");
		object.setAttribute(new Attribute<Boolean>("Open", new Boolean(true)));
		object.setAttribute(new Attribute<String>("Password", "FLARN"));
		object.setAttribute(new Attribute<Integer>("Count", new Integer(50)));
		object.setAttribute(new Attribute<Long>("Time", new Long(50000L)));
		object.setAttribute(new Attribute<Object>("Null", null));
		object.setAttribute(new Attribute<Float>("Percent", new Float(25.75f)));
		object.setAttribute(new Attribute<SlageObject>("SlageObj", new SlageObject("Thingy")));
		object.setAttribute(new Attribute<java.awt.Color>("Object", new java.awt.Color(255, 0, 0)));
		object.setAttribute(new Attribute<String>("List1", "list"));
		object.setAttribute(new Attribute<Integer>("List2", new Integer(222)));
		
		assertEquals(10, object.getAttributeCount());

		try {
			assertEquals(true, object.getAttributeAsBoolean("Open"));
			assertEquals("FLARN", object.getAttributeAsString("Password"));
			assertEquals(50, object.getAttributeAsInt("Count"));
			assertEquals(50000L, object.getAttributeAsLong("Time"));
			assertNull(object.getAttribute("Null"));
			assertEquals(25.75f, object.getAttributeAsFloat("Percent"), 0.001f);
			assertEquals("Thingy", ((SlageObject) object.getAttributeValue("SlageObj")).toString());
			assertEquals(255, ((java.awt.Color) object.getAttributeValue("Object")).getRed());
			assertEquals("list", object.getAttributeAsString("List1"));
			assertEquals(222, object.getAttributeAsInt("List2"));
		} catch (NotFoundException e) {
			fail(e.getMessage());
		}

		object.removeAttribute("List1");
		assertEquals(object.getAttributeCount(), 9);

		object.setAttribute(new Attribute<Boolean>("Open", new Boolean(false)));
		object.setAttribute("Password", "BLARGH");
		object.setAttribute("Null", new java.util.Date());
		object.setAttribute("Count", 150);
		object.setAttribute("Time", 10000L);
		object.setAttribute("Percent", 75.25f);
		object.setAttribute("Object", new java.awt.Color(0, 0, 255));

		try {
			assertEquals(false, object.getAttributeAsBoolean("Open"));
			assertEquals("BLARGH", object.getAttributeAsString("Password"));
			assertEquals(150, object.getAttributeAsInt("Count"));
			assertEquals(10000L, object.getAttributeAsLong("Time"));
			assertNotNull(object.getAttribute("Null"));
			assertEquals(75.25f, object.getAttributeAsFloat("Percent"), 0.001f);
			assertEquals(0, ((java.awt.Color) object.getAttributeValue("Object")).getRed());
			assertEquals(255, ((java.awt.Color) object.getAttributeValue("Object")).getBlue());
		} catch (NotFoundException e) {
			fail(e.getMessage());
		}

		// make sure NFE gets thrown when we ask for something that's not there
		try {
			object.getAttributeValue("NOT THERE!");
			fail("SlageObject.getAttribute() did not throw NotFoundException when something wasn't found.");
		} catch (NotFoundException e) {
			//Correct behavior
		}

		// make sure setAttribute() adds a Attribute when we need it to
                object.setAttribute(new Attribute<String>("NEW Attribute", "new"));
		
		assertEquals(object.getAttributeCount(), 10);
		try {
			assertEquals(object.getAttributeAsString("NEW Attribute"), "new");
		} catch (NotFoundException e) {
			fail("SlageObject.setAttribute() fails to add new object if not found.");
		}

		object.clearAttributes();

		assertEquals(object.getAttributeCount(), 0);

		// adding another works after clear
		object.setAttribute(new Attribute<Integer>("newbie", new Integer(4)));
		assertEquals(object.getAttributeCount(), 1);
	}

	/**
	 * Parentage tests
	 * 
	 * Tests: - getParent() - setParent() - getAncestor() - addObject() -
	 * getContainedObjectCount() - getObjectForNoun() - removeObject() -
	 * getBunchOfItems()
	 */
	public void testContainerRelationships() throws Exception {
		SlageObject act = new Act("Act", 1);
		SlageObject scene = new Scene("Scene", 2);
		SlageObject room = new Room("Room");
		SlageObject container = new SlageObject("Container");
		object = new SlageObject("Object");

		SlageObject object2 = new SlageObject("broom");
		SlageObject object3 = new SlageObject("dustpan");
		SlageObject object4 = new SlageObject("rubber chicken");

		// these calls call setParent() internally
		act.addObject(scene);
		scene.addObject(room);
		room.addObject(container);
		room.addObject(object3);
		room.addObject(object4);
		container.addObject(object);
		container.addObject(object2);

		// check sizes...
		assertEquals(act.getContainedObjectCount(), 1);
		assertEquals(scene.getContainedObjectCount(), 1);
		assertEquals(room.getContainedObjectCount(), 3);
		assertEquals(container.getContainedObjectCount(), 2);
		assertEquals(object.getContainedObjectCount(), 0);

		// make sure item bunch is getting tracked properly
		assertEquals(act.getContainedObjectCount(), act.getBunchOfItems().getItemCount());
		assertEquals(scene.getContainedObjectCount(), scene.getBunchOfItems().getItemCount());
		assertEquals(room.getContainedObjectCount(), room.getBunchOfItems().getItemCount());
		assertEquals(container.getContainedObjectCount(), container.getBunchOfItems().getItemCount());
		assertEquals(object.getContainedObjectCount(), object.getBunchOfItems().getItemCount());

		assertSame(object.getBunchOfItems().getOwner(), object);
		assertSame(object.getDescription().getOwner(), object);

		// check parents
		assertSame(act, scene.getParent());
		assertSame(scene, room.getParent());
		assertSame(room, container.getParent());
		assertSame(room, object3.getParent());
		assertSame(container, object.getParent());

		// Find items
		assertSame(scene, act.searchForObject("Scene"));
		assertSame(object2, container.searchForObject("broom"));
		assertSame(object3, room.searchForObject("dustpan"));
		assertSame(room, scene.searchForObject("Room"));
		assertSame(container, room.searchForObject("Container"));
		assertSame(room, room.searchForObject("Room")); // special case - looked at
		// the room

		// test ancestors
		assertSame(container.getAncestor(Act.class), act);
		assertSame(container.getAncestor(Scene.class), scene);
		assertSame(container.getAncestor(Room.class), room);
		assertSame(room.getAncestor(Scene.class), scene);
		assertSame(room.getAncestor(Act.class), act);
		assertNull(room.getAncestor(SlageGame.class));

		// remove items
		container.removeObject(object);
		container.removeObject(object2);

		assertNull(object.getParent());
		assertNull(object2.getParent());
		assertEquals(container.getContainedObjectCount(), 0);
	}

	

	/**
	 * Tests: - addHandler() - getHandlers() - getHandlerCount() - removeHandler() -
	 * clearHandlers()
	 */
	public void testHandlers() throws Exception {
		object = new SlageObject("Handler test");

		Handler lick = new ResponseHandler(object, "It tastes bad.", null);
		lick.addVerb("lick");
		Handler look = new ResponseHandler(object, "It looks bad.", null);
		lick.addVerb("look");

		object.addHandler(lick);
		object.addHandler(look);

		Handler h = new org.slage.handlers.Handler(object) {
			public void fire() {
				System.out.println("Bang.");
			}
		};

		h.addVerb("shoot");
		h.addVerb("fire");
		object.addHandler(h);

		assertEquals(object.getHandlerCount(), 3);
		assertEquals(object.getHandlers("shoot").size(), 1);
		assertEquals(object.getHandlers("oepopojepoj").size(), 0);

		assertEquals(object.getHandlers("lick").size(), 1);

		assertSame(lick.getOwner(), object);

		object.removeHandler(lick);
		assertEquals(object.getHandlers("lick").size(), 0);
		assertEquals(object.getHandlerCount(), 2);

		object.clearHandlers();
		assertEquals(object.getHandlerCount(), 0);

		object.addHandler(lick);
		assertEquals(object.getHandlerCount(), 1);

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
