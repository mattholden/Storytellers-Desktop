package org.slage;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

import org.slage.animation.AnimationController;
import org.slage.framework.Point3D;
import org.slage.framework.SlageFrameworkObject;
import org.slage.framework.Tools;
import org.slage.handlers.Handler;
import org.slage.parser.AdjectiveNounDescription;
import org.slage.parser.DefaultBunchOfItems;
import org.slage.parser.DefaultDescription;
import org.slage.parser.Synonym;
import org.slage.parser.WordSet;
import org.slage.pathfinding.Path;
import org.slage.ui.InventoryDisplay;
import org.slage.ui.InventoryPanel;
import org.slage.ui.ToolBar;
import org.slage.resource.ResourceContainer;
import org.slage.resource.ResourceException;

/**
 * 
 * @author <a href="mailto:Matt@SQ7.org">Matt Holden</a> Provides base
 *         functionality for all Objects in the game.
 */
public class SlageObject
		extends SlageFrameworkObject<Handler>
                implements Drawable, Clickable, Collidable, ResourceContainer
{	
    
        /** The animation controller for this object */
	private AnimationController animator = new AnimationController(this);

	/** Defines how an object is displayed as an inventory */
	private InventoryDisplay inventoryDisplay = null;

	/** The panel for this object if one is open */
	private transient InventoryPanel inventoryPanel = null;

	/** Collision status of the object */
	private CollisionStatus collision = new CollisionStatus(this);

	/** Description of an item (for the parser) */
	private DefaultDescription description;

	/** Synonym for "just the name" - should be added to descriptions */
	private Synonym synonymForName;

	/** The contents of the contained objects, in terms the parser understands */
	private DefaultBunchOfItems bunchOfItems = new DefaultBunchOfItems(this);

	/** If 'true' the object is "solid" and can't be walked through */
	private boolean bSolid = false;

	/**
	 * the image for this object (will be 'null' for background objects) as it
	 * appears in the scene
	 */
	private SlageImage imgScene = null;

	/**
	 * the image for this object (will be 'null' for background objects) as it
	 * appears in the inventory dialog and as cursors
	 */
	private SlageImage imgInv = null;

	/** Image for the cursor. */
	private SlageImage imgCursor = null;

	/** List of all the Templates we've inherited */
	private transient ArrayList<SlageObject> listTemplates = new ArrayList<SlageObject>();

	/** List of the component Objects that built this Object */
	private ArrayList<SlageObject> listCombinedObjects = new ArrayList<SlageObject>();

	/** Position of the object */
	private Point3D ptPosition = new Point3D(0, 0, 0);

	/** Click boundary of the object */
	private Boundary clickBoundary;

	/** Click boundary of the object in the inventory */
	private Boundary clickBoundaryInventory;

	/** The collision boundary for the object */
	private Boundary collideBoundary;

	/**
	 * parent object (in the heirarchy: Contained Object => Object => Room =>
	 * Scene => Act => Game
	 */
	private transient SlageObject objParent = null;

	/** List of objects contained by this object */
	public java.util.LinkedHashMap<String, SlageObject> listObjects = new java.util.LinkedHashMap<String, SlageObject>();

	/**
	 * The current path this object is following.
         * TODO move to handler as appropriate
	 */
	private Path path = null;

	/**
	 * Creates a new instance of SlageObject
	 * 
	 * @param aName object name
	 */
	public SlageObject(String aName) {
		super(aName);

		// have some sort of a description...
		buildLameDescription();

		// build lame ID
		inventoryDisplay = new InventoryDisplay(this);
	}

	/** Build a Synonym with just the name */
	public void buildSynonymForName() {
		WordSet articles = new WordSet(new String[] { "a", "an", "the" });
		WordSet nouns = new WordSet(new String[] { getName() });
		synonymForName = new Synonym(articles, null, nouns);
	}

	/** Build a really crummy description */
	public void buildLameDescription() {
		if (synonymForName == null)
			buildSynonymForName();
		description = new AdjectiveNounDescription(getName(), synonymForName);
		description.setOwner(this);
		description.setLameDescription(true);

	}

	/**
	 * Add an object that was combined to make this one
	 * 
	 * @param obj Object to add
	 */
	public void addCombinedComponent(SlageObject obj) {
		listCombinedObjects.add(obj);
	}

	/**
	 * Remove an object that was combined to make this one
	 * 
	 * @param obj Object to remove
	 */
	public void removeCombinedComponent(SlageObject obj) {
		listCombinedObjects.remove(obj);
	}

	/**
	 * Get count of the combined objects
	 * 
	 * @return count of combined objects in this one
	 */
	public int getCombinedComponentCount() {
		return listCombinedObjects.size();
	}

	/**
	 * Get a combined object
	 * 
	 * @param aName name of the object
	 * @return the object if we have it or null
	 */
	public SlageObject getCombinedComponent(String aName) {
		for (SlageObject o : listCombinedObjects)
			if (o.getName().equalsIgnoreCase(aName))
				return o;

		return null;
	}

	/** Destroy this object and put all its pieces in the parent */
	public void breakIntoContainer() {
		for (SlageObject o : listCombinedObjects)
			getParent().addObject(o);

		getParent().removeObject(this);
	}



	/**
	 * Add an object as a template. The "this" object will absorb many of the
	 * template object's characteristics, including handlers, states and
	 * descriptions.
	 * 
	 * Copied: - States - Handlers - Descriptions - Images (scene, inventory,
	 * cursor) - Animations
	 * 
	 * Not copied (deliberately) - click boundary / collision boundary - name -
	 * contained objects / parent object - position - quantity
	 * 
	 * TODO: add RemoveObjectAsTemplate? Do we need it?
	 * 
	 * @param objTemplate template object
	 */
	public void addObjectAsTemplate(SlageObject objTemplate) {

		if (objTemplate == null)
			return;

		// 0. Add to template list
		listTemplates.add(objTemplate);
		listTemplates.addAll(objTemplate.listTemplates);

		// 1. Handlers
		this.listHandlers.addAll(objTemplate.listHandlers);

		// 2. states
                mapAttributes.putAll(objTemplate.mapAttributes);		
		
		// 3. Images
		if (objTemplate.imgCursor != null)
			this.setCursorImage(objTemplate.imgCursor);
		if (objTemplate.imgInv != null)
			this.setInvImage(objTemplate.imgInv);
		if (objTemplate.imgScene != null)
			this.setSceneImage(objTemplate.imgScene);

		// 4. Description
		if (getDescription() == null)
			this.description = objTemplate.getDescription();
		else
			(getDescription()).merge(objTemplate.getDescription());

		// 5. Animations
		this.animator.merge(objTemplate.getAnimator());

	}

	/**
	 * Retrieve the vocabulary of all (visible) Handlers on this object
	 * 
	 * @param bVerbose If 'true', return ALL recognized visible verbs. Otherwise,
	 *        return only one per action (i.e., not LOOK and EXAMINE both)
	 * @return an ArrayList of String verbs
	 */
	public ArrayList<String> getHandlerVocabulary(boolean bVerbose) {
		ArrayList<String> listVocab = new ArrayList<String>();
		for (Handler H : listHandlers) {
			if (H.isVisibleToVocabulary() == false)
				continue;
			if (H.hasNoExternalVerbs())
				continue;

			// we're gonna add verbs
			ArrayList<String> verbs = H.getVerbs();
			for (String strVerb : verbs) {

				// don't add internal verbs
				if (strVerb.startsWith(org.slage.ui.InputAgent.INTERNAL_VERB_PREFIX))
					continue;

				// don't add duplicate verbs
				if (listVocab.contains(strVerb))
					continue;

				// finally, add it. It will only add if no other handler has
				// registered this verb, which is OK since both will fire anyway.
				// note that some handlers might not get added at all if they
				// duplicate other handlers' verbs.
				listVocab.add(strVerb);

				// only add the first valid verb.. no need for TAKE and EXAMINE
				if (bVerbose == false)
					break;
			}
		}

		// Alphabetize the list
		Tools.Alphabetize(listVocab);
		return listVocab;
	}

	/**
	 * Get the Inventory Display
	 * 
	 * @return inventoryDisplay
	 */
	public InventoryDisplay getInventoryDisplay() {
		return inventoryDisplay;
	}

	/**
	 * Get template count
	 * 
	 * @return listTemplates.size()
	 */
	public int getTemplateCount() {
		return listTemplates.size();
	}

	/**
	 * Get template for name
	 * 
	 * @param name Name to look for
	 * @return the template (if implemented) or null
	 */
	public SlageObject getTemplate(String name) {
		if (name == null)
			return null;
		for (SlageObject obj : listTemplates) {
			if (obj.getName().equalsIgnoreCase(name))
				return obj;
		}
		return null;
	}

	/**
	 * Accessor for animation controller
	 * 
	 * @return animator
	 */
	public AnimationController getAnimator() {
		return this.animator;
	}

	/**
	 * Accessor for click boundary
	 * 
	 * @return Boundary
	 */
	public Boundary getClickBoundary() {
		return clickBoundary;
	}

	/**
	 * Accessor for inventory click boundary
	 * 
	 * @return Boundary
	 */
	public Boundary getInventoryBoundary() {
		return clickBoundaryInventory;
	}

	/**
	 * Build the inventory boundary
	 * 
	 * @param x x position
	 * @param y y position
	 * @param size object size
	 */
	public void buildInventoryBoundary(int x, int y, int size) {
		clickBoundaryInventory = new Boundary();
		clickBoundaryInventory.addPoint(x, y);
		clickBoundaryInventory.addPoint(x, y + size);
		clickBoundaryInventory.addPoint(x + size, y + size);
		clickBoundaryInventory.addPoint(x + size, y);
		clickBoundaryInventory.setZ(200000000); // nice and huge
	}

	/**
	 * Turn this object into a mouse cursor for the "use object" tool
	 * 
	 * @return cursor
	 */
	public SlageImage getCursorImage() {
		return imgCursor;
	}

	/** Build a cursor by scaling the scene image to 32x32 */
	public void buildCursorFromSceneImage() {
		if (imgScene != null) {
			imgCursor = new SlageImage(imgScene.getQMarkPath(), ToolBar.CURSOR_HEIGHT, ToolBar.CURSOR_HEIGHT);
		}
	}

	/** Build an inventory graphic by scaling the scene image to 64x64 */
	public void buildInventoryImageFromSceneImage() {
		if (imgScene != null) {
			imgInv = new SlageImage(imgScene.getQMarkPath(), inventoryDisplay.getObjectSize(), inventoryDisplay.getObjectSize());
		}
	}

	/**
	 * Accessor for description
	 * 
	 * @return description
	 */
	public DefaultDescription getDescription() {
		return description;
	}

	/**
	 * Accessor for image as it should appear in the inventory
	 * 
	 * @return imgInv
	 */
	public SlageImage getInvImage() {
		return imgInv;
	}

	
	/**
	 * Accessor for image as it should appear in the scene
	 * 
	 * @return imgScene
	 */
	public SlageImage getSceneImage() {
		return imgScene;
	}

	
	/**
	 * Accessor for position
	 * 
	 * @return ptPosition
	 */
	public Point3D getPosition() {
		return ptPosition;
	}

	/**
	 * Convenience pass-through for Boundary's isClicked
	 * 
	 * @param X x click point
	 * @param Y y click point
	 * @return 'true' if the click happened inside our Boundary
	 */
	public boolean isClicked(int X, int Y) {
		// no click boundary isn't an error condition- it's an unclickable
		// object.
		// this is valuable for things like a fog layer, that we want to ignore
		// clicks so that we can
		// click on the objects beneath.
		// in the meantime, we can never click on an unclickable thing, so
		// return false/
		if (clickBoundary == null)
			return false;

		return clickBoundary.isClicked(X, Y);
	}

	/**
	 * Convenience pass-through for Boundary's isClicked
	 * 
	 * @param X x click point
	 * @param Y y click point
	 * @return 'true' if the click happened inside our Boundary
	 */
	public boolean isClickedInventory(int X, int Y) {
		if (clickBoundaryInventory == null)
			return false;

		return clickBoundaryInventory.isClicked(X, Y);
	}

	/**
	 * Find the topmost object that was clicked on
	 * 
	 * @param X x position
	 * @param Y y position
	 * @return topmost object (this might be the Room itself)
	 */
	public SlageObject getClickedObject(int X, int Y) {
		SlageObject objToReturn = this;

		Set<Map.Entry<String, SlageObject>> set = listObjects.entrySet();
		for (Map.Entry<String, SlageObject> m1 : set) {
			SlageObject obj = m1.getValue();
			if (obj.isClicked(X, Y)) {
				if (obj.getClickBoundary() != null) {
					if (objToReturn.getClickBoundary() == null)
						objToReturn = obj.getClickedObject(X, Y);

					else if (obj.getClickBoundary().getZ() > objToReturn.getClickBoundary().getZ()) {
						objToReturn = obj.getClickedObject(X, Y);
					}
				}
			}
		}

		return objToReturn;
	}

	/**
	 * Find the topmost object that was clicked on
	 * 
	 * @param X x position
	 * @param Y y position
	 * @return topmost object (this might be the Room itself)
	 */
	public SlageObject getClickedObjectInInventory(int X, int Y) {

		Set<Map.Entry<String, SlageObject>> set = listObjects.entrySet();
		for (Map.Entry<String, SlageObject> m1 : set) {
			SlageObject obj = m1.getValue();
			if (obj.isClickedInventory(X, Y))
				return obj;
		}
		return null;
	}

	/**
	 * Modifier for click boundary
	 * 
	 * @param clickBounds new boundary
	 */
	public void setClickBoundary(Boundary clickBounds) {
		clickBoundary = clickBounds;
	}

	/**
	 * Modifier for description
	 * 
	 * @param desc new description
	 */
	public void setDescription(DefaultDescription desc) {
		this.description = desc;
		description.setOwner(this);
	}

	/**
	 * Modifier for image as it should appear as a cursor
	 * 
	 * @param image new image
	 */
	public void setCursorImage(SlageImage image) {
		this.imgCursor = image;
	}

	/**
	 * Modifier for image as it should appear in inventory
	 * 
	 * @param image new image
	 */
	public void setInvImage(SlageImage image) {
		this.imgInv = image;
	}

	/**
	 * Returns 'true' if this object owns the parameter object
	 * 
	 * @param obj Object to check for
	 * @return 'true' if we (or any of our children) have the object
	 */
	public boolean hasObject(SlageObject obj) {
		return (listObjects.containsValue(obj));
	}

	/**
	 * Modifier for image as it should appear in the scene. Will build a Boundary
	 * for this image if the object does not already have one.
	 * 
	 * @param image A new SlageImage to render in the scene.
	 */
	public void setSceneImage(SlageImage image) {
		this.imgScene = image;
	}

	
	/** Helper - set the object at the origin */
	public void placeAtOrigin() {
		if (clickBoundary != null)
			clickBoundary.translate(-1 * ptPosition.x, -1 * ptPosition.y);
		if (collideBoundary != null)
			collideBoundary.translate(-1 * ptPosition.x, -1 * ptPosition.y);

		ptPosition.translate(-1 * ptPosition.x, -1 * ptPosition.y);
	}

	/**
	 * Modifier for position
	 * 
	 * @param ptNewPos new position
	 */
	public void setPosition(Point3D ptNewPos) {
		// move it to 0,0
		placeAtOrigin();

		// move it to new place
		ptPosition.translate(ptNewPos.x, ptNewPos.y);
		ptPosition.z = ptNewPos.z;

		if (clickBoundary != null) {
			clickBoundary.translate(ptNewPos.x, ptNewPos.y);
			clickBoundary.setZ(ptNewPos.z);
		}

		if (collideBoundary != null) {
			collideBoundary.translate(ptNewPos.x, ptNewPos.y);
			collideBoundary.setZ(ptNewPos.z);
		}

	}

	
	/**
	 * Modifier for z order
	 * 
	 * @param z new Z position
	 */
	public void setZ(int z) {
		ptPosition.z = z;

		if (clickBoundary != null)
			clickBoundary.setZ(z);
		if (collideBoundary != null)
			collideBoundary.setZ(z);
	}

	/**
	 * Move from the current position
	 * 
	 * @param iX x amount to move
	 * @param iY y amount to move
	 */
	public void translate(int iX, int iY) {
		ptPosition.translate(iX, iY);
		if (clickBoundary != null)
			clickBoundary.translate(iX, iY);
		if (collideBoundary != null)
			collideBoundary.translate(iX, iY);
	}

	/**
	 * Accessor for parent
	 * 
	 * @return objParent
	 */
	public final SlageObject getParent() {
		return objParent;
	}

	/**
	 * Mutator for parent
	 * 
	 * @param obj new parent
	 */
	protected void setParent(SlageObject obj) {
		objParent = obj;
	}

	/**
	 * Get the first object ancestor that is an instance of the given class.
	 * 
	 * @param cls the class to find
	 * @return the first ancestor that is assignable from the class, or null if
	 *         none is found
	 */
	public SlageObject getAncestor(Class cls) {
		// shortcut - no non-SlageObject can ever be an ancestor
		if (Tools.IsSubclass(SlageObject.class, cls) == false)
			return null;

		SlageObject parent = getParent();
		while (parent != null) {
			if (Tools.IsSubclass(cls, parent.getClass()))
				break;

			parent = parent.getParent();
		}

		// will be null if the loop broke
		return parent;
	}

	/**
	 * Add an object to this object (making it a container)
	 * 
	 * @param obj Object to add
	 */
	public void addObject(SlageObject obj) {
		obj.setParent(this);
		listObjects.put(obj.getName(), obj);

		if (obj.getDescription() != null)
			bunchOfItems.placeItem(obj.getDescription());
	}

	/**
	 * Returns a list of all the objects that are contained within this one.
	 * 
	 * @return a list of all the objects that are contained within this one
	 */
	public ArrayList<SlageObject> getContainedObjects() {

		ArrayList<SlageObject> objects = new ArrayList<SlageObject>();

		Set<Map.Entry<String, SlageObject>> set = listObjects.entrySet();
		for (Map.Entry<String, SlageObject> entry : set)
			objects.add(entry.getValue());

		return objects;
	}

	/**
	 * Remove the given object
	 * 
	 * @param objRemove object to remove
	 */
	public void removeObject(SlageObject objRemove) {
		if (objRemove == null)
			return;

		listObjects.remove(objRemove.getName());

		if (objRemove.getDescription() != null)
			bunchOfItems.removeItem(objRemove.getDescription());

		objRemove.setParent(null);
	}

	/**
	 * Get the number of owned objects
	 * 
	 * @return object count
	 */
	public int getContainedObjectCount() {
		return listObjects.size();
	}	

	/**
	 * Draw the object to the given rendering context at its current position.
	 * 
	 * @param G2D Graphics context to draw to
	 */
	public void draw(java.awt.Graphics2D G2D) {

		// if we have a current Animation, play it
		if (animator.getCurrentAnimation() != null)
			animator.getCurrentAnimation().draw(G2D, ptPosition);

		// If no current animation, draw the scene image
		else if (imgScene != null)
			G2D.drawImage(imgScene.getImageIcon().getImage(), ptPosition.x, ptPosition.y, null);

		// if in debug mode, draw bounding shape
                if (clickBoundary != null && 
                    getAncestor(SlageGame.class).getAttributeAsBoolean("Debug Mode") == true) {
                        clickBoundary.draw(G2D);
                }
		
	}

	/**
	 * Draw the object's inventory images to the given rendering context at its
	 * current position.
	 * 
	 * @param G2D Graphics context to draw to
	 * @param x X position to draw at
	 * @param y Y position to draw at
	 */
	public void drawInventory(java.awt.Graphics2D G2D, int x, int y) {

		// TODO Allow for animated inventory

		// If no current animation, draw the static image
		if (imgInv != null)
			G2D.drawImage(imgInv.getImageIcon().getImage(), x, y, null);

	}

	/**
	 * Retrieve the collision boundary
	 * 
	 * @return collideBoundary
	 * 
	 */

	public java.awt.Polygon getCollisionBoundary() {
		return collideBoundary;
	}

	/** Set the collision boundary to match the scene image */
	public void useImageBoundaryForCollision() {
		collideBoundary = new Boundary(getSceneImage(), ptPosition.z);
		collideBoundary.translate(ptPosition.x, ptPosition.y);
	}

	/**
	 * Get the image boundary from the Scene image
	 * 
	 * @return the bounds of the image
	 */
	public Boundary getImageBoundary() {
		Boundary IB = new Boundary(getSceneImage(), ptPosition.z);
		IB.translate(ptPosition.x, ptPosition.y);
		return IB;
	}

	/** Set the click boundary to match the scene image */
	public void useImageBoundaryForClicking() {
		clickBoundary = new Boundary(getSceneImage(), ptPosition.z);
		clickBoundary.translate(ptPosition.x, ptPosition.y);
	}

	/** Set the collision boundary to match the click boundary */
	public void useClickBoundaryForCollision() {
		// copy constructor
		collideBoundary = new Boundary(clickBoundary, clickBoundary.getZ());
	}

	/** Set the click boundary to match the collision boundary */
	public void useCollisionBoundaryForClicking() {
		// copy constructor
		clickBoundary = new Boundary(collideBoundary, collideBoundary.getZ());
	}

	/**
	 * Determine if two collidable objects are colliding
	 * 
	 * @param collide collidable object to test against
	 * @return 'true' if 'this' and 'collide' are colliding.
	 */
	public boolean collidesWith(Collidable collide) {
		// if either object is uncollidable, there is certainly no collision
		if (collide == null || collideBoundary == null || collide.getCollisionBoundary() == null)
			return false;
		return collideBoundary.collidesWith(collide);
	}

	/**
	 * Ask if two SlageObjects are in collision. Only checks the results already
	 * generated - use collidesWith() or better yet, testCollision(), to do the
	 * actual check
	 * 
	 * @param strObject object name to check for (exact)
	 * @return 'true' if the CollisionStatus says we hit it
	 */
	public boolean isCollidingWith(String strObject) {
		return collision.isCollidingWith(strObject);
	}

	/**
	 * Ask if two SlageObjects are in collision. Only checks the results already
	 * generated - use collidesWith() or better yet, testCollision(), to do the
	 * actual check
	 * 
	 * @param object object to check for
	 * @return 'true' if the CollisionStatus says we hit it
	 */
	public boolean isCollidingWith(SlageObject object) {
		return collision.isCollidingWith(object);
	}

	/**
	 * Check whether the object can be passed through
	 * 
	 * @return 'true' if the object can NOT be passed through
	 */
	public boolean isSolid() {
		return bSolid;
	}

	/**
	 * Set whether the object can be passed through
	 * 
	 * @param isSolid 'true' if the object can NOT be passed through
	 */
	public void setSolid(boolean isSolid) {
		this.bSolid = isSolid;
	}

	/**
	 * Set the collision boundary of the object
	 * 
	 * @param boundary the new boundary
	 */
	public void setCollisionBoundary(java.awt.Polygon boundary) {
		if (boundary instanceof Boundary)
			collideBoundary = (Boundary) boundary;
		else
			collideBoundary = new Boundary(boundary, ptPosition.z);

	}

	/**
	 * Test all objects contained in this object for collision. Note that an
	 * object can only collide with its sibling - an object will never collide
	 * with its parent, or with its child, or with its sibling's child.
	 */
	public void testCollision() {
		Set<Map.Entry<String, SlageObject>> set = listObjects.entrySet();
		for (Map.Entry<String, SlageObject> m1 : set) {
			SlageObject o1 = m1.getValue();
			CollisionStatus cs = new CollisionStatus(o1);
			for (Map.Entry<String, SlageObject> m2 : set) {
				SlageObject o2 = m2.getValue();
				if (o1 == o2) {
					continue;
				}
				if (o1.collidesWith(o2)) {
					cs.addCollidedWith(o2);
				}
			}

			o1.collision.compareStatus(cs);
			o1.collision.processResults();
			o1.collision = cs;
		}
	}

	/** *** PARSER INTEGRATION **** */

	/**
	 * Getter for the object's contained bunchOfItems.
	 * 
	 * @return Value of property bunchOfItems.
	 */
	public org.slage.parser.DefaultBunchOfItems getBunchOfItems() {
		return bunchOfItems;
	}

	/**
	 * Search for an object anywhere in the heirarchy below me
	 * 
	 * @param strObjName name of the object to search for
	 * @return the object or Null if it's not there
	 */
	public SlageObject searchForObject(String strObjName) {
		// check "this"
		if (getName().equalsIgnoreCase(strObjName))
			return this;

		// check immediate children
		SlageObject obj = listObjects.get(strObjName);
		if (obj != null)
			return obj;

		// do the deeper search separately to make the search breadth-first
		Set<Map.Entry<String, SlageObject>> set = listObjects.entrySet();
		for (Map.Entry<String, SlageObject> me : set) {
			SlageObject objDeep = me.getValue();
			SlageObject result = objDeep.searchForObject(strObjName);
			if (result != null)
				return result;
		}

		return null;
	}

	/**
	 * Set the inventory panel
	 * 
	 * @param IP inventory panel
	 */
	public void setInventoryPanel(InventoryPanel IP) {
		// only be open once
		if (inventoryPanel != null)
			destroyInventoryPanel();
		inventoryPanel = IP;
	}

	/** Destroy the inventory panel (close it, too) */
	public void destroyInventoryPanel() {
		if (inventoryPanel == null)
			return;

		inventoryPanel.setVisible(false);

		// remove from frame
		Slage.getCurrentGame().getFrame().getLayeredPane().remove(inventoryPanel);

		inventoryPanel = null;
	}

	/** TODO move to a handler
	 * @return Returns the path.
	 */
	public Path getPath() {
		return path;
	}

	/** TODO move to a handler
	 * @param aPath The path to set.
	 */
	public void setPath(Path aPath) {
		this.path = aPath;
	}

        /** Load all the resources for this object using the resource manager */
        public void loadResources() throws ResourceException
        {
            // load my images
            // unload my images
            if (getInvImage() != null)
                getInvImage() .loadResources();
            if (getSceneImage() != null)
                getSceneImage().loadResources();
            if (getCursorImage() != null)
                getCursorImage().loadResources();
            
            // make sure my contained objects get it done, too
            Set<Map.Entry<String, SlageObject>> set = listObjects.entrySet();
            for (Map.Entry<String, SlageObject> me : set) 
            {
                    SlageObject object = me.getValue();
                    object.loadResources();
            }
        }       
	    
      /** Unload all the resources for this object using the resource manager */
        public void unloadResources() throws ResourceException
        {
            // unload my images
            if (getInvImage() != null)
                getInvImage() .unloadResources();
            if (getSceneImage() != null)
                getSceneImage().unloadResources();
            if (getCursorImage() != null)
                getCursorImage().unloadResources();
            
            // make sure my contained objects get it done, too
            Set<Map.Entry<String, SlageObject>> set = listObjects.entrySet();
            for (Map.Entry<String, SlageObject> me : set) 
            {
                    SlageObject object = me.getValue();
                    object.unloadResources();
            }
        }       
	     
        
        
	/**
	 * Determines if the object is on screen. Checks the position and all three
	 * boundaries, in the order: image, click, collide. This is determined such
	 * that image is visual, trumping the others. The click boundary relates to
	 * handling of input, which trumps collision. In truth, this is an OR check so
	 * the order doesn't much matter.
	 * 
	 * The "screen" will be determined as the viewport of the current game.
	 * 
	 * @return 'true' if any part of the object is onscreen
	 */
	public boolean isOnScreen() {
		java.awt.Rectangle rectView = Slage.getCurrentGame().getViewport();

		// check position
		if (rectView.contains(getPosition()))
			return true;

		// check image
		Boundary image = getImageBoundary();
		if (image != null && image.intersects(rectView))
			return true;

		// check clicks
		if (getClickBoundary() != null && getClickBoundary().intersects(rectView))
			return true;

		// check collide
		if (getCollisionBoundary() != null && getCollisionBoundary().intersects(rectView))
			return true;

		// not there
		return false;
	}

	
	public void accept(SlageObjectVisitor aVisitor) {
		aVisitor.accept(this);
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
