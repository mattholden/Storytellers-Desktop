/*
 * CollisionStatus.java
 *
 * Created on October 10, 2005, 2:18 PM
 */

package org.slage;

import java.util.ArrayList;

import org.jdom.Element;
import org.slage.command.Command;
import org.slage.command.Commander;

/**
 * Small class to track the changes in objects with which we are colliding.
 * Please DO NOT genericize this class
 * 
 * @author <a href="mailto:Matt@SQ7.org">Matt Holden</a>
 */
public class CollisionStatus implements Commander {

	/** Our parent object - the object we'return watching collision for */
	private SlageObject parent;

	/** Objects with which we collided in this instant in time */
	private ArrayList listCollisions = new ArrayList();

	/** Store the list of objects we entered collision with */
	private ArrayList entered = new ArrayList();

	/** Store the list of objects we exited collision with */
	private ArrayList exited = new ArrayList();

	/**
	 * Creates a new instance of CollisionStatus
	 * 
	 * @param obj Parent object
	 */
	public CollisionStatus(SlageObject obj) {
		parent = obj;
	}

	/**
	 * Add an object that we are colliding with
	 * 
	 * @param obj Object we are in collision with
	 */
	public void addCollidedWith(SlageObject obj) {
		listCollisions.add(obj);
	}

	/**
	 * Add an object that we are colliding with
	 * 
	 * @param str Name of the object we are in collision with
	 */
	protected void addCollidedWith(String str) {
		listCollisions.add(str);
	}

	/**
	 * Get the number of colliding objects
	 * 
	 * @return collision object count
	 */
	public int getCollisionCount() {
		return listCollisions.size();
	}

	/**
	 * Check if the list contains an object
	 * 
	 * @param obj Object to check for
	 * @return 'true' if this status reported collision on this object
	 */
	public boolean isCollidingWith(SlageObject obj) {
		return listCollisions.contains(obj);
	}

	/**
	 * Check if the list contains an object
	 * 
	 * @param strName Object name to check for
	 * @return 'true' if this status reported collision on this object
	 */
	public boolean isCollidingWith(String strName) {
		for (int i = 0; i < listCollisions.size(); i++) {
			SlageObject ob = (SlageObject) listCollisions.get(i);
			if (ob.getName().equalsIgnoreCase(strName))
				return true;
		}
		return false;
	}

	/**
	 * Get the list of objects we entered collisions with
	 * 
	 * @return entered
	 */
	public ArrayList getEnteredObjects() {
		return entered;
	}

	/**
	 * Get the list of objects we exited collisions with
	 * 
	 * @return exited
	 */
	public ArrayList getExitedObjects() {
		return exited;
	}

	/**
	 * Compare two lists, storing the results
	 * 
	 * @param CS1 first list to compare
	 * @param CS2 second list to compare
	 * @param listResult list to put anything that was in list1 but not list2
	 */
	protected static void compareLists(CollisionStatus CS1, CollisionStatus CS2, ArrayList listResult) {
		for (int i = 0; i < CS1.listCollisions.size(); i++) {
			if (CS1.listCollisions.get(i) instanceof String) {
				String str = (String) CS1.listCollisions.get(i);
				if (CS2.isCollidingWith(str) == false)
					listResult.add(str);
			} else if (CS1.listCollisions.get(i) instanceof SlageObject) {
				SlageObject obj = (SlageObject) CS1.listCollisions.get(i);
				if (CS2.isCollidingWith(obj) == false)
					listResult.add(obj);
			}
		}
	}

	/**
	 * Compare against another CollisionStatus - this should be done against the
	 * CollisionStatus for the NEXT update, and it will note the differences.
	 * 
	 * @param next The next frame's CollisionStatus.
	 */
	public void compareStatus(CollisionStatus next) {
		entered.clear();
		exited.clear();

		// if in you and not in me, we entered
		compareLists(next, this, entered);

		// if not in you and in me, we exited
		compareLists(this, next, exited);

	}

	/**
	 * Getter for parent object.
	 * 
	 * @return Value of property parent.
	 */
	public org.slage.SlageObject getParent() {
		return parent;
	}

	/**
	 * Setter for parent object.
	 * 
	 * @param aParent New value of property parent.
	 */
	public void setParent(SlageObject aParent) {
		this.parent = aParent;
	}

	/**
	 * Construct a CollisionStatus from XML
	 * 
	 * @param elem XML element
	 */
	public CollisionStatus(Element elem) {
		java.util.List collides = elem.getChildren("colliding", elem.getNamespace());
		for (int i = 0; i < collides.size(); i++) {
			Element e = (Element) collides.get(i);
			this.addCollidedWith(e.getAttributeValue("with"));
		}
	}

	/**
	 * Return an XML save
	 * 
	 * @return XML Element
	 */
	public Element getXMLElement() {
		return getXMLElement("collisionStatus");
	}

	/**
	 * Return an XML save
	 * 
	 * @param strRootElementTag element tag name
	 * @return XML Element
	 */
	public Element getXMLElement(String strRootElementTag) {
		Element elem = new Element(strRootElementTag);

		for (int i = 0; i < listCollisions.size(); i++) {
			Element collision = new Element("colliding");
			if (listCollisions.get(i) instanceof String)
				collision.setAttribute("with", (String) listCollisions.get(i));
			else
				collision.setAttribute("with", ((SlageObject) listCollisions.get(i)).getName());

			elem.addContent(collision);
		}
		return elem;
	}

	/**
	 * Process a list for collision
	 * 
	 * @param list List to process
	 * @param strVerb verb to use
	 */
	protected void processList(ArrayList list, String strVerb) {
		for (int i = 0; i < list.size(); i++) {
			SlageObject obj = null;
			if (list.get(i) instanceof String)
				obj = parent.getParent().searchForObject(list.get(i).toString());
			else
				obj = (SlageObject) list.get(i);

			Command c = new Command(strVerb, parent, obj, this);
			c.fire();
		}
	}

	/** Launch all relevant commands based on changes made to the collision status */
	public void processResults() {
		processList(entered, "__COLLISION_ENTERED");
		processList(exited, "__COLLISION_EXITED");
	}

	/**
	 * Returns the game the parent belongs to
	 * 
	 * @return game
	 */
	public org.slage.SlageGame getGame() {
		if (parent != null)
			return (SlageGame) parent.getAncestor(SlageGame.class);
		else if (SlageGame.getGameUnderConstruction() != null)
			return SlageGame.getGameUnderConstruction();

		return null;
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
