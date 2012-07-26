package org.slage;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.slage.audio.SoundFile;
import org.slage.command.Commander;
import org.slage.framework.Point3D;
import org.slage.framework.Rectangle3D;
import org.slage.framework.Tools;
import org.slage.framework.VerbSynonymList;
import org.slage.framework.scheduler.RecurringEvent;
import org.slage.framework.scheduler.ScheduledEvent;
import org.slage.handlers.Handler;
import org.slage.handlers.RenderLoopHandler;
import org.slage.parser.BunchOfItems;
import org.slage.parser.Parser;
import org.slage.ui.AsyncKeyAction;
import org.slage.ui.ConsolePanel;
import org.slage.ui.GameFrame;
import org.slage.ui.InputAgent;
import org.slage.ui.KeyHandler;
import org.slage.ui.ToolBar;
import org.slage.resource.ResourceException;

/**
 * Stores all the dynamic game-state information. Extend this class to create
 * pure-Java Slage game implementations. Of particular note to override for such
 * projects are the functions initialize() and shutdown(). Place any
 * constructor-type functionality for game objects in initialize(). and
 * shutdown() should contain any cleanup code needed. Both of these functions,
 * if overridden, should call their superclass' counterpart as their first line.
 * update() should not be overridden; to add per-frame actions, instead use
 * addEvent() and specify a RecurringEvent or ScheduledEvent.
 * 
 * To make this a self-executing game within the Slage engine, create a main()
 * in your implementation of SlageGame that calls Slage.runGame(new
 * MyClassName());
 * 
 * 
 */
public class SlageGame
		extends SlageObject implements Commander {

       
	/** Logger instance */
	private transient static final  Log LOG = LogFactory.getLog(SlageGame.class);

	/**
	 * Creates a new instance of SlageGame
	 * 
	 * @param strName name of the whole game, ex. "Space Quest 7"
	 */
	public SlageGame(String strName) {
		super(strName);

	}
                

	/** Sound file management * */

	/** Manager for sound files */
	protected org.slage.audio.SoundManager soundManager = new org.slage.audio.SoundManager();

	/**
	 * Accessor for the sound manager itself
	 * 
	 * @return soundManager
	 */
	public org.slage.audio.SoundManager getSoundManager() {
		return soundManager;
	}

	/**
	 * Play a Sound file. Will load it if necessary.
	 * 
	 * @param strFile file to load
	 * @return a reference to the loaded Sound, in case you want to stop it or
	 *         manipulate it somehow.
	 */
	public SoundFile playSound(String strFile) {
		return soundManager.play(strFile);
	}

	/**
	 * Play a Sound file. Will load it if necessary.
	 * 
	 * @param strFile file to load
	 * @param bLoop 'true' if the sound should loop
	 * @return a reference to the loaded Sound, in case you want to stop it or
	 *         manipulate it somehow.
	 */
	public SoundFile playSound(String strFile, boolean bLoop) {
		return soundManager.play(strFile, bLoop);
	}

	/**
	 * Load a Sound file that isn't loaded
	 * 
	 * @param strFile file to load
	 * @param bLoop 'true' if the sound should loop
	 * @return a reference to the loaded Sound, in case you want to stop it or
	 *         manipulate it somehow. If the Sound was already loaded, returns the
	 *         reference to its original object.
	 */
	public SoundFile loadSound(String strFile, boolean bLoop) {
		return soundManager.load(strFile, bLoop);
	}

	/*****************************************************************************
	 * Engine settings - viewport, etc.
	 ****************************************************************************/

	/** Viewport, including scroll (Used Rect3D for free XML save) */
	private Rectangle rectViewport = new Rectangle3D(0, 0, 0, 1024, 768);

	/**
	 * Accessor for viewport
	 * 
	 * @return rectViewport
	 */
	public final Rectangle getViewport() {
		return rectViewport;
	}

	/**
	 * Sets viewport
	 * 
	 * @param rect New viewport
	 */
	public void setViewport(Rectangle rect) {
		this.rectViewport = rect;
		capViewport();

		getFrame().getCanvas().resizeBackBuffer(rect.width, rect.height);
	}

	/**
	 * Moves viewport
	 * 
	 * @param x new X position
	 * @param y new Y position
	 */
	public void moveViewport(int x, int y) {
		rectViewport.x = x;
		rectViewport.y = y;
		capViewport();
	}

	/**
	 * Scroll viewport
	 * 
	 * @param x amount to scroll in x
	 * @param y amount to scroll in y
	 */
	public void scroll(int x, int y) {
		rectViewport.x += x;
		rectViewport.y += y;
		capViewport();
	}

	/** Object to lock viewport onto */
	private SlageObject objViewportLock = null;

	/**
	 * Lock the viewport on an object
	 * 
	 * @param obj Object to lock on to
	 */
	public void lockViewportOn(SlageObject obj) {
		objViewportLock = obj;
		updateViewportLock();
	}

	/** Update viewport lock on the given object */
	public void updateViewportLock() {
		if (objViewportLock == null)
			return;

		// Get the center of the viewport
		Point3D ptViewCenter = new Point3D(((rectViewport.x + rectViewport.width) >> 1), ((rectViewport.y + rectViewport.height) >> 1), 0);

		/**
		 * get the distance needed to put the center at the object's position
		 */
		int deltaX = objViewportLock.getPosition().x - ptViewCenter.x;
		int deltaY = objViewportLock.getPosition().y - ptViewCenter.y;

		// scroll that amount, and count on capViewport()
		// to protect us from going out of the level
		scroll(deltaX, deltaY);
	}

	/** Don't scroll outside the world */
	private void capViewport() {
		// enforce size
		if (rectViewport.width > getRoom().getDimensions().width)
			rectViewport.width = getRoom().getDimensions().width;
		if (rectViewport.height > getRoom().getDimensions().height)
			rectViewport.height = getRoom().getDimensions().height;
		// enforce edges
		if (rectViewport.x < 0)
			rectViewport.x = 0;
		else if (rectViewport.x + rectViewport.width > getRoom().getDimensions().width)
			rectViewport.x = getRoom().getDimensions().width - rectViewport.width;
		if (rectViewport.y < 0)
			rectViewport.y = 0;
		else if (rectViewport.y + rectViewport.height > getRoom().getDimensions().height)
			rectViewport.y = getRoom().getDimensions().height - rectViewport.height;

	}

	/*****************************************************************************
	 * Current object, tool and room
	 ****************************************************************************/

	/** The current room */
	private Room room;

	/**
	 * Accessor for the room
	 * 
	 * @return room
	 */
	public Room getRoom() {
		return room;
	}

	/**
	 * Mutator for room
	 * 
	 * @param aRoom New room
	 */
	public void setRoom(Room aRoom) {
		this.room = aRoom;
		if (aRoom == null) {
			clearDrawList();
			return;
		}

		aRoom.buildDrawList();

		if (aRoom.getDimensions() != null && getFrame() != null && getFrame().getCanvas() != null) {
			getFrame().getCanvas().resizeBackBuffer(aRoom.getDimensions().width, aRoom.getDimensions().height);
		}

		rectViewport.x = aRoom.getScrollStartX();
		rectViewport.y = aRoom.getScrollStartY();

	}

	/*****************************************************************************
	 * DRAW LIST FUNCTIONALITY
	 ****************************************************************************/

	/** The rendering schedule event - stored so we can pass it among games */
	private RecurringEvent evtRendering;

	/**
	 * Get the shared rendering event
	 * 
	 * @return evtRendering
	 */
	public RecurringEvent getRenderEvent() {
		return evtRendering;
	}

	/**
	 * Throttle FPS to a certain value
	 * 
	 * @param iFPS frames per second
	 */
	public void setFPSCap(int iFPS) {
		evtRendering.setInterval(ScheduledEvent.getNanoInterval(iFPS));
	}

	/** 'true' if the draw list needs sorting */
	private transient boolean bDrawListNeedsSort = false;

	/** Draw list */
	private transient ArrayList<Drawable> listToDraw = new ArrayList<Drawable>();

	/**
	 * Add a list of objects to the draw list... This method is provided as a
	 * convenience and optimization to add all objects in a Room to the list.
	 * 
	 * @param listDraw objects to draw
	 */
	public void addToDrawList(java.util.LinkedHashMap<String, SlageObject> listDraw) {

		Set<Map.Entry<String, SlageObject>> set = listDraw.entrySet();
		for (Map.Entry<String, SlageObject> me : set)
			listToDraw.add(me.getValue());

		bDrawListNeedsSort = true;
	}

	/**
	 * Add an object to the draw list.
	 * 
	 * @param toDraw object to draw
	 */
	public void addToDrawList(Drawable toDraw) {
		listToDraw.add(toDraw);
		bDrawListNeedsSort = true;
	}

	/**
	 * Get count of items in draw list
	 * 
	 * @return listToDraw.size()
	 */
	public int getDrawListSize() {
		return listToDraw.size();
	}

	/**
	 * Remove an object from the draw list.
	 * 
	 * @param toDraw object to remove
	 */
	public void removeFromDrawList(Drawable toDraw) {
		listToDraw.remove(toDraw);
	}

	/** Clear the draw list */
	public void clearDrawList() {
		listToDraw.clear();
	}

	/**
	 * Draw the contents of the draw list to the Graphics2D context.
	 * 
	 * @param G2D Graphics2D object to draw to
	 */
	public void draw(java.awt.Graphics2D G2D) {
		// Sort the draw list if it has been changed.
		if (bDrawListNeedsSort)
			sortDrawList();

		// draw objects in proper Z-order (back to front)
		for (int i = 0; i < listToDraw.size(); i++)
			listToDraw.get(i).draw(G2D);
	}

	/**
	 * Sort the draw list so that objects draw in correct Z-order (and thus,
	 * properly layered on top of one another).
	 */
	private void sortDrawList() {
		bDrawListNeedsSort = false;
		java.util.Collections.sort(listToDraw, comparatorDrawList);
  
	}

	/*****************************************************************************
	 * Passthru functions for Scheduler (so we don't have to expose it)
	 ****************************************************************************/

	/**
	 * Add an ScheduledEvent to the game's internal Scheduler.
	 * 
	 * @param evt Event to add
	 */
	public void addEvent(ScheduledEvent evt) {
		scheduler.addEvent(evt);
	}

	/**
	 * Remove a ScheduledEvent from the game's internal Scheduler.
	 * 
	 * @param evt Event to remove
	 */
	public void removeEvent(ScheduledEvent evt) {
		scheduler.removeEvent(evt);
	}

	/*****************************************************************************
	 * Instanced engine components
	 ****************************************************************************/

	/** InputAgent for key/mouse input */
	private transient InputAgent agent;

	/** Java's random number generator instance for this game */
	private transient java.util.Random random = new java.util.Random();

	/** The Parser we're going to use */
	private transient Parser parser;

	/** Internal Scheduler object for ScheduledEvents and RecurringEvents */
	private SlageScheduler scheduler = new SlageScheduler(this);

	/** Console window */
	private transient ConsolePanel console;

	/** The Toolbar */
	private ToolBar toolbar;

	/**
	 * Accessor for random number generator
	 * 
	 * @return random
	 */
	public java.util.Random getRandom() {
		return random;
	}

	/**
	 * Accessor for parser
	 * 
	 * @return parser
	 */
	public Parser getParser() {
		return parser;
	}

	/**
	 * Accessor for input agent
	 * 
	 * @return agent
	 */
	public InputAgent getInputAgent() {
		return agent;
	}

	/**
	 * Accessor for console panel
	 * 
	 * @return console
	 */
	public ConsolePanel getConsole() {
		return console;
	}

	/**
	 * Accessor for scheduler
	 * 
	 * @return scheduler
	 */
	public SlageScheduler getScheduler() {
		return scheduler;
	}

	/**
	 * Accessor for tool bar
	 * 
	 * @return toolbar
	 */
	public ToolBar getToolbar() {
		return toolbar;
	}

	/** The game's internal frame */
	private transient GameFrame frame;

	/**
	 * Accessor for game frame
	 * 
	 * @return frame
	 */
	public GameFrame getFrame() {
		return frame;
	}

	/** The game's command history */
	private org.slage.command.CommandHistory history;

	/**
	 * Accessor for command history
	 * 
	 * @return history
	 */
	public org.slage.command.CommandHistory getCommandHistory() {
		return history;
	}

	/*****************************************************************************
	 * Startup / Shutdown / Update
	 ****************************************************************************/

	/** Initialize gamestate-related stuff. */
	public void initialize() {

		if(LOG.isDebugEnabled()){
			LOG.debug("Initializing game...");
		}
		// cache viewport when loading from XML
		Rectangle rView = new Rectangle(0, 0, 1024, 768);
		if (rectViewport != null)
			rView = new Rectangle3D(rectViewport, 0);

		// Seed random number generator
		random.setSeed(System.nanoTime() - System.currentTimeMillis());

		// Make our Window
		frame = new GameFrame(this);

		// initialize console panel
		console = new ConsolePanel(this);
		getFrame().getLayeredPane().add(console, new Integer(2));

		// add toolbar
		if (toolbar == null)
			toolbar = new ToolBar(this);
		getFrame().getLayeredPane().add(toolbar, new Integer(2));

		// set up input agent
		agent = new InputAgent(this);
		getFrame().addKeyListener(agent);
		getFrame().getCanvas().addMouseListener(agent);

		/*
		 * set up parser TODO: internationalization? TODO: reference the vocab xml
		 * from the game's xml rather than hard code?
		 */
		try {
			org.jdom.input.SAXBuilder builder = new org.jdom.input.SAXBuilder(true);
			builder.setFeature("http://apache.org/xml/features/validation/schema", true);
			org.jdom.Document doc = builder.build(Tools.GetQMarkDelimitedPath("org?slage?xml?english_vocabulary.xml"));
			parser = Parser.createFromXML(doc.getRootElement());
			parser.setGame(this);
		} catch (Exception ex) {
			LOG.error(ex);
			parser = new org.slage.parser.EnglishParser();
			parser.setGame(this);
		}

		// command history
		if (history == null)
			history = new org.slage.command.CommandHistory(this);

		// Ensure the presence of a rendering event
		java.util.ArrayList evts = scheduler.getEventsWithHandlerType(RenderLoopHandler.class);
		if (evts.size() == 0) {
			RenderLoopHandler RLH = new RenderLoopHandler(this);
			RLH.addVerb("__RENDER");

			evtRendering = new RecurringEvent(RLH, ScheduledEvent.getNanoInterval(2000), Integer.MAX_VALUE);

			addEvent(evtRendering);
		} else
			evtRendering = (RecurringEvent) evts.get(0);

		/* Start the game, already! */

		// make extra sure we get the right cursor to start
		// necessary due to all the changing that happens in the Frame at init
		if (toolbar.getSelectedTool() != null)
			toolbar.getSelectedTool().setAsSelected();
		else
			toolbar.selectFirstVerbButton();

		// refresh viewport and room for XML loads
		setRoom(room);
		rectViewport.x = rView.x;
		rectViewport.y = rView.y;

		frame.setVisible(true);
		getFrame().requestFocusInWindow();
	}

	/** Update game-related functionality */
	public void update() {
		if (bPaused)
			return;

		if (toolbar.getSelectedTool() == null)
			toolbar.selectFirstVerbButton();

		checkAsyncKeys();

		scheduler.update();

		room.testCollision();
	}

	/** Shut down any running stuff */
	public void shutdown() {
		soundManager.shutdown();
	}

	/*****************************************************************************
	 * Pause functionality
	 ****************************************************************************/

	/**
	 * Paused state - defined here vs. in a State due to need for additional work
	 * in pause() and resume()
	 */
	private boolean bPaused = false;

	/**
	 * Check if game is paused
	 * 
	 * @return pause state
	 */
	public boolean isPaused() {
		return bPaused;
	}

	/** Pause the game */
	public void pause() {
		bPaused = true;
	}

	/** Resume the game from paused state */
	public void resume() {
		bPaused = false;
		scheduler.resume();
	}

	/*****************************************************************************
	 * Key Handlers (passthru to hide Agent)
	 ****************************************************************************/

	/** List of keys to fire on and what to fire */
	private java.util.ArrayList<KeyHandler> listKeyHandlers = new java.util.ArrayList<KeyHandler>();

	/**
	 * Add a Key Handler
	 * 
	 * @param handler handler to bind to
	 * @param iKey key to fire on
	 */
	public void addKeyHandler(Handler handler, int iKey) {
		listKeyHandlers.add(new KeyHandler(handler, iKey));
	}

	/**
	 * Add a Key Handler
	 * 
	 * @param handler handler to bind to
	 * @param iKey key to fire on
	 * @param bAlt 'true' if Alt must be on
	 * @param bCtrl 'true' if Ctrl must be on
	 * @param bShift 'true' if shift must be on
	 */
	public void addKeyHandler(Handler handler, int iKey, boolean bAlt, boolean bCtrl, boolean bShift) {
		KeyHandler KH = new KeyHandler(handler, iKey);
		KH.setAlt(bAlt);
		KH.setShift(bShift);
		KH.setCtrl(bCtrl);
		listKeyHandlers.add(KH);
	}

	/**
	 * Getter for the list of key handlers. Only InputAgent should use this.
	 * 
	 * @return Value of property listKeyHandlers.
	 */
	public java.util.ArrayList<KeyHandler> getKeyHandlers() {
		return listKeyHandlers;
	}

	/** Asynchronous keyboard actions */
	private ArrayList<AsyncKeyAction> listAsyncActions = new ArrayList<AsyncKeyAction>();

	/**
	 * Getter for the list of async key actions. Only InputAgent should use this.
	 * 
	 * @return Value of property listAsyncActions.
	 */
	public java.util.ArrayList<AsyncKeyAction> getAsyncActions() {
		return listAsyncActions;
	}

	/**
	 * Add an Async Key Action
	 * 
	 * @param AKA AsyncKeyAction to add
	 */
	public void addAsyncAction(AsyncKeyAction AKA) {
		listAsyncActions.add(AKA);
	}

	/**
	 * Remove an Async Key Action
	 * 
	 * @param AKA AsyncKeyAction to remove
	 */
	public void removeAsyncAction(AsyncKeyAction AKA) {
		listAsyncActions.remove(AKA);
	}

	/** Clear Async Key Actions */
	public void clearAsyncActions() {
		listAsyncActions.clear();
	}

	/**
	 * Get async key action count
	 * 
	 * @return listAsyncActions.size()
	 */
	public int getAsyncActionCount() {
		return listAsyncActions.size();
	}

	/** Check if Async Key Actions are happening */
	public void checkAsyncKeys() 
        {
		// fire async keys
		for (AsyncKeyAction AKA : getAsyncActions())
                {
			if (AKA.isHappening())
				AKA.fire();
		}
	}

	/*****************************************************************************
	 * Fullscreen (passthru to hide Frame)
	 ****************************************************************************/

	/** 'true' if we are in full screen mode */
	private boolean bFullScreen = false;

	/**
	 * Check window state
	 * 
	 * @return bFullScreen
	 */
	public boolean isFullScreen() {
		return bFullScreen;
	}

	/**
	 * Toggle fullscreen mode
	 * 
	 * @param aFullScreen 'true' if we should go to fullscreen, otherwise go to
	 *        windowed
	 */
	public void setFullScreen(boolean aFullScreen) {
		// No change made - we were already in this mode.
		if (this.bFullScreen == aFullScreen)
			return;

		this.bFullScreen = aFullScreen;
		getFrame().maximize(aFullScreen);
	}

	/** ************************ */

	public BunchOfItems getInventoryForParser() {
		return player.getBunchOfItems();
	}

	public BunchOfItems getRoomForParser() {
		return room.getBunchOfItems();
	}

	/**
	 * Accessor for the player character
	 * 
	 * @return Value of property player.
	 */
	public org.slage.SlagePlayer getPlayer() {
		return player;
	}

	/**
	 * Mutator for the player character. This should ONLY be used during setup!
	 * 
	 * @param aPlayer New value of property player.
	 */
	public void setPlayer(org.slage.SlagePlayer aPlayer) {
		this.player = aPlayer;
	}

	/** the player character */
	private SlagePlayer player;

	/** Comparator for sorting Drawables by Z order */
	public static final java.util.Comparator<Drawable> comparatorDrawList = new java.util.Comparator<Drawable>() {
		public int compare(Drawable obj, Drawable obj2) {
			if (obj.getPosition().z < obj2.getPosition().z)
				return -1;
			if (obj.getPosition().z == obj2.getPosition().z)
				return 0;
			return 1;
		}
	};

	/*****************************************************************************
	 * VERB SYNONYMS
	 */

	/** List of synonym objects */
	private ArrayList<VerbSynonymList> listVerbSynonyms = new ArrayList<VerbSynonymList>();

	/**
	 * Apply a synonym set
	 * 
	 * @param list the VerbSynonymList to apply
	 */
	public void applyVerbSynonymList(VerbSynonymList list) {
		// First, see if the list already has a match
		VerbSynonymList VSL = null;
		for (int i = 0; i < listVerbSynonyms.size(); i++) {
			VSL = listVerbSynonyms.get(i);
			if (VSL.getRootVerb().equals(list.getRootVerb()))
				break;
		}

		// If we didnt find a match and we didn't say "omit", default to
		// "create" behavior
		if (VSL == null && list.getActionType() != VerbSynonymList.OMIT) {
			listVerbSynonyms.add(list);
			return;
		}

		// ///////////////////////////////////
		// apply the appropriate behavior on the matched pair
		switch (list.getActionType()) {
		// if "Create" and there already was one, exception
		case VerbSynonymList.CREATE:
			LOG.warn(new Exception("You attempted to \"create\" a duplicate VerbSynonymList for the verb \"" + list.getRootVerb() + "\". This is invalid. If you intend only one verb list" + " (for an unambiguous verb like \"LOOK\"), replace one of your \"create\""
					+ " verbSynonym tags with \"replace\" or \"merge\", depending on your desired behavior.\n\n" + "If you intend different meanings (\"SAVE THE DATA\" vs. \"SAVE THE DROWNING WOMAN\"), consider "
					+ "choosing an alternate verb to be the \"node verb\" of one of the two lists."));
			return;

		case VerbSynonymList.MERGE:
			VSL.merge(list);
			return;

		case VerbSynonymList.OMIT:
			VSL.omit(list);
			return;

		case VerbSynonymList.REPLACE:
			listVerbSynonyms.remove(VSL);
			listVerbSynonyms.add(list);
			return;

		default:
			return;
		}
	}

	/**
	 * Get the VerbSynonymList for the given verb
	 * 
	 * @param strVerb verb to seek
	 * @return the VerbSynonymList with strVerb as the node verb, or null if no
	 *         VSL is registered on that verb
	 */
	public VerbSynonymList getVerbSynonyms(String strVerb) {
		for (VerbSynonymList VSL : listVerbSynonyms)
                {
                        if (VSL.getRootVerb().equalsIgnoreCase(strVerb))
				return VSL;
		}
		return null;
	}

	/**
	 * Get all VerbSynonymLists that contain the given verb (as node or synonym).
	 * 
	 * @param strVerb verb to seek
	 * @return An ArrayList containing all VerbSynonymLists with strVerb in them
	 *         (will be an empty list if no VSL is registered on that verb)
	 */
	public ArrayList<VerbSynonymList> getVerbDefinitions(String strVerb) {
		ArrayList<VerbSynonymList> results = new ArrayList<VerbSynonymList>();
		for (VerbSynonymList VSL : listVerbSynonyms)
                {
                    if (VSL.contains(strVerb))
				results.add(VSL);
		}
		return results;
	}

	/**
	 * The game currently being constructed. This is a "hack" since the game being
	 * built may not be the Slage.getCurrentGame() value, but things like handlers
	 * may need to reference the game during loading.
	 */
	private transient static SlageGame gameUnderConstruction = null;

	/**
	 * Get the current game being built
	 * 
	 * @return gameUnderConstruction
	 */
	public static SlageGame getGameUnderConstruction() {
		return gameUnderConstruction;
	}

	/**
	 * Because the game needs to be the Commander when we load from XML, this must
	 * be implemented. In a monster hack, getGame() to return "this" :)
	 * 
	 * @return this
	 */
	public SlageGame getGame() {
		return this;
	}

        
        /** Load resource files */
        public void loadResources() throws ResourceException
        {
            	super.loadResources();
                soundManager.loadResources();
        }
        
        /** Unload resource files */
        public void unloadResources() throws ResourceException
        {
            	super.unloadResources();
                soundManager.unloadResources();
        }
        
	@Override
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
