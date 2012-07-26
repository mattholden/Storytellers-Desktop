/*
 * ImageTest.java
 *
 * Created on July 30, 2005, 5:44 PM
 */

package org.slage.tests;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.slage.Boundary;
import org.slage.Room;
import org.slage.Slage;
import org.slage.SlageGame;
import org.slage.SlageObject;
import org.slage.SlagePlayer;
import org.slage.TextObject;
import org.slage.animation.SingleSpriteAnimation;
import org.slage.animation.SingleSpriteFrame;
import org.slage.framework.Point3D;
import org.slage.SlageImage;
import org.slage.framework.Tools;
import org.slage.framework.VerbSynonymList;
import org.slage.framework.scheduler.RecurringEvent;
import org.slage.framework.scheduler.ScheduledEvent;
import org.slage.handlers.CloseInventoryHandler;
import org.slage.handlers.FocusConsoleHandler;
import org.slage.handlers.Handler;
import org.slage.handlers.LaunchEditorHandler;
import org.slage.handlers.LockViewportHandler;
import org.slage.handlers.PauseHandler;
import org.slage.handlers.PlaySoundHandler;
import org.slage.handlers.QuitHandler;
import org.slage.handlers.ResponseHandler;
import org.slage.handlers.SaveWithChooserDialog;
import org.slage.handlers.ScrollHandler;
import org.slage.handlers.ShowInventoryHandler;
import org.slage.handlers.TakeObjectHandler;
import org.slage.handlers.ToggleDebugHandler;
import org.slage.handlers.TranslateHandler;
import org.slage.parser.Synonym;
import org.slage.parser.WordSet;
import org.slage.ui.AsyncKeyAction;
import org.slage.ui.HandlerButton;
import org.slage.ui.MacroButton;
import org.slage.ui.VerbButton;

/**
 * 
 * @author Jaeden
 */
public class ImageTest
		extends SlageGame {

	/** logger instance */
	private static final Log LOG = LogFactory.getLog(ImageTest.class);

	/** Construct */
	public ImageTest() {
		super("Slage Image Test");
                
                /* Re-add when xstream bugs fixed 
		try {
			addObjectAsTemplate(XMLProcessor.<SlageObject> dispatch(Tools.GetQMarkDelimitedPath("..?xml?gameUI.xml")));
		} catch (Exception e) {
			LOG.error(e);
		}
                */
	}

	/** Duke */
	private SlageObject duke;

	/** Room */
	private Room room;

	/** Build Duke */
	private void makeDuke() {
		duke = new SlageObject("Duke");
		duke.getDescription().addSynonym(new Synonym(new WordSet(new String[] { "a", "the" }), new WordSet(new String[] { "walking", "badly animated", "sucky", "moving" }), new WordSet(new String[] { "man", "sprite", "stick figure" })));

		// load static image
		SlageImage img = new SlageImage("org?slage?tests?content?duke_wave.gif");

		duke.setSceneImage(img);
		duke.buildCursorFromSceneImage();
		duke.buildInventoryImageFromSceneImage();

		// put Duke in the room
		room.addObject(duke);

		// build click boundary manually
		Point3D pts[] = { new Point3D(0, 0, 3), new Point3D(0, img.getImageIcon().getIconHeight(), 3), new Point3D(img.getImageIcon().getIconWidth(), img.getImageIcon().getIconHeight(), 3),
		/** test polygonals */
		new Point3D((int) (img.getImageIcon().getIconWidth() * 1.5f), (img.getImageIcon().getIconHeight() / 2), 3), new Point3D(img.getImageIcon().getIconWidth(), 0, 3) };
		Boundary click = new Boundary(pts, 3);
		duke.setClickBoundary(click);
		duke.setZ(3);
		duke.useClickBoundaryForCollision();

		// set up duke animation
		SingleSpriteAnimation animDuke = new SingleSpriteAnimation("Flip", ScheduledEvent.NANOSEC_IN_SEC);
		animDuke.addFrame(new SingleSpriteFrame(new SlageImage("org?slage?tests?content?duke_wave.gif"), animDuke));
		animDuke.addFrame(new SingleSpriteFrame(new SlageImage("org?slage?tests?content?duke_wave2.gif"), animDuke));
		animDuke.addFrame(new SingleSpriteFrame(new SlageImage("org?slage?tests?content?duke_wave3.gif"), animDuke));
		animDuke.addFrame(new SingleSpriteFrame(new SlageImage("org?slage?tests?content?duke_wave4.gif"), animDuke));
		animDuke.setLooping(true);
		duke.getAnimator().addAnimation(animDuke);

		ResponseHandler mlook = new ResponseHandler(duke, "He looks like he could use some better sprites.", null);
		ResponseHandler mlick = new ResponseHandler(duke, "We've got a rule around these parts. No licking Stickfigures!", null);
		mlook.addVerb("look");
		mlook.addVerb("examine");
		mlick.addVerb("lick");
		mlick.addVerb("taste");

		duke.addHandler(mlick);
		duke.addHandler(mlook);

		try {
			duke.getAnimator().setCurrentAnimation("Flip");
		} catch (Exception e) {
			LOG.error(e);
		}
		animDuke.start();

	}

	/** Build the Pope */
	private void makePope() {
		// add the pope and load his image
		SlageObject pope = new SlageObject("John Paul II");
		pope.setSceneImage(new SlageImage("org?slage?tests?content?pope.jpg"));
		pope.setPosition(new Point3D(200, 100, 2));

		ResponseHandler mlook = new ResponseHandler(pope, "He looks like a nice enough guy.", null);
		ResponseHandler mlick = new ResponseHandler(pope, "Somehow, I doubt he'd like that very much.", null);
		ResponseHandler mKiss = new ResponseHandler(pope, "He's not allowed to do that, you know.", null);
		mKiss.addVerb("kiss");
		mlook.addVerb("look");
		mlook.addVerb("examine");
		mlick.addVerb("lick");
		mlick.addVerb("taste");

		pope.useImageBoundaryForClicking();
		pope.useClickBoundaryForCollision();
		pope.getDescription().addSynonym(new Synonym(new WordSet(new String[] { "a", "the" }), new WordSet(new String[] { "old", "nice", "dead", "religious", "catholic" }), new WordSet(new String[] { "pope", "man", "bishop", "shepherd", "priest", "picture", "hanging" })));

		ResponseHandler enter = new ResponseHandler(pope, "The pope says, Hey Roger! Get off of me!", null);
		enter.addVerb("__COLLISION_ENTERED");
		pope.addHandler(enter);
		ResponseHandler exit = new ResponseHandler(pope, "The pope says, Thanks for letting me up, Roger!", null);
		exit.addVerb("__COLLISION_EXITED");
		pope.addHandler(exit);
		enter.setIndirectObj(getPlayer());
		exit.setIndirectObj(getPlayer());

		pope.addHandler(mlick);
		pope.addHandler(mKiss);
		pope.addHandler(mlook);
		// put him in the room
		room.addObject(pope);
	}

	/** Build fake toolbar buttons */
	public void makeToolButtons() {

		VerbButton look = new VerbButton("LOOK", "org?slage?tests?content?eye.png", "org?slage?tests?content?eye.png");
		getToolbar().add(look);

		getToolbar().add(new VerbButton("LICK", "org?slage?tests?content?tongue.png", "org?slage?tests?content?tongue.png"));

		getToolbar().add(new VerbButton("TAKE", "org?slage?tests?content?hand.png", "org?slage?tests?content?hand.png"));

		getToolbar().add(new HandlerButton(new QuitHandler(this), "QUIT", "org?slage?tests?content?door.png"));

		getToolbar().add(new MacroButton("KISS Pope", "org?slage?tests?content?lips.png"));

		objectButton = new org.slage.ui.ObjectButton(duke);
		getToolbar().add(objectButton);
	}

	/** object button */
	public org.slage.ui.ObjectButton objectButton;

	/** text object to work with */
	private TextObject tryTheSpecial;

	/** Make monolith burger bathroom doors as background objects */
	private void makeBathrooms() {
		SlageObject mens = new SlageObject("Mens Room");
		Point3D[] ptMens = { new Point3D(732, 551, 1), new Point3D(708, 183, 1), new Point3D(797, 127, 1), new Point3D(902, 181, 1), new Point3D(888, 561, 1) };
		mens.setPosition(new Point3D(732, 551, 1));
		mens.setClickBoundary(new Boundary(ptMens, 1));
		ResponseHandler mlook = new ResponseHandler(mens, "It's the men's room. You've cleaned your share of them.", null);
		ResponseHandler mlick = new ResponseHandler(mens, "Licking the restroom door is clinically proven to carry severe risk of disease.", null);
		mlook.addVerb("look");
		mlook.addVerb("examine");
		mlick.addVerb("lick");
		mlick.addVerb("taste");

		mens.addHandler(mlick);
		mens.addHandler(mlook);
		room.addObject(mens);

		SlageObject womens = new SlageObject("Ladies Room");
		Point3D[] ptWomens = { new Point3D(458, 506, 1), new Point3D(454, 179, 1), new Point3D(529, 121, 1), new Point3D(622, 173, 1), new Point3D(620, 541, 1) };
		womens.setPosition(new Point3D(458, 506, 1));
		womens.setClickBoundary(new Boundary(ptWomens, 1));
		ResponseHandler look = new ResponseHandler(womens, "The bright pink door reminds you of the Ukiniae on Monostadt 7. Getting too close to either is hazardous to your health.", null);
		ResponseHandler lick = new ResponseHandler(womens, "Licking the restroom door is clinically proven to carry severe risk of disease.", null);
		look.addVerb("look");
		look.addVerb("examine");
		lick.addVerb("lick");
		lick.addVerb("taste");

		womens.addHandler(lick);
		womens.addHandler(look);
		room.addObject(womens);

	}

	/** Add the arcade machine */
	private void makeArcade() {
		SlageObject arcade = new SlageObject("Arcade Machine");
		Point3D[] ptarcade = { new Point3D(230, 195, 1), new Point3D(275, 192, 1), new Point3D(341, 241, 1), new Point3D(348, 486, 1), new Point3D(290, 503, 1), new Point3D(223, 476, 1), new Point3D(220, 327, 1), new Point3D(242, 270, 1) };
		arcade.setClickBoundary(new Boundary(ptarcade, 1));
		ResponseHandler look = new ResponseHandler(arcade, "It's a Half-Life 2 machine!", null);
		ResponseHandler lick = new ResponseHandler(arcade, "It tastes as if many a grubby alien thumb has graced these buttons.", null);
		look.addVerb("look");
		look.addVerb("examine");
		lick.addVerb("lick");
		lick.addVerb("taste");

		ResponseHandler bucks = new ResponseHandler(buck, "You don't have time to play games!", null);
		bucks.addVerb("use");
		bucks.setIndirectObj(arcade);
		buck.addHandler(bucks);

		arcade.addHandler(lick);
		arcade.addHandler(look);
		room.addObject(arcade);
	}

	/** Add the scheduled events */
	private void makeEvents() {
		// add movement to the scheduler
		addEvent(new RecurringEvent(new ImageTestMotion(duke, tryTheSpecial), ScheduledEvent.getNanoInterval(60), 5));

	}
	/** buckazoid */
	public SlageObject buck;

	/** Add a buckazoid to remove */
	public void makeBuckazoid() {
		buck = new SlageObject("Buckazoids");
		SlageImage icon = new SlageImage("org?slage?tests?content?buckazoid_sq4.gif");

		// Watch us take advantage of all our freebies...
		buck.setSceneImage(icon);
		buck.useImageBoundaryForClicking();
		buck.buildCursorFromSceneImage(); // use scene image for cursor...
		buck.buildInventoryImageFromSceneImage(); // ... and inventory icon
		// buck.useClickBoundaryForCollision(); // use click bound for collision

		buck.setPosition(new Point3D(350, 500, 2)); // put everything in place

		ResponseHandler look = new ResponseHandler(buck, "Free buckazoids! Moolah! Sponduli! Cash!", null);
		ResponseHandler lick = new ResponseHandler(buck, "With as often as money changes hands, licking it probably isn't the best idea.", null);
		ResponseHandler take = new ResponseHandler(buck, "You snatch up the buckazoid and drop it in your pocket. Cha-ching!", null);

		look.addVerb("look");
		look.addVerb("examine");
		lick.addVerb("lick");
		lick.addVerb("taste");
		take.addVerb("grab");
		take.addVerb("take");
		take.addVerb("pick up");
		take.addVerb("snatch");

		buck.addHandler(lick);
		buck.addHandler(look);
		buck.addHandler(take);

		// more freebies.. will assume current room, current player
		TakeObjectHandler takeObj = new TakeObjectHandler(buck);
		takeObj.addVerb("grab");
		takeObj.addVerb("take");
		takeObj.addVerb("pick up");
		takeObj.addVerb("snatch");
		takeObj.setScore(5);
		buck.addHandler(takeObj);

		ChangeObjectButton cob = new ChangeObjectButton(this, buck);
		cob.addVerb("grab");
		cob.addVerb("take");
		cob.addVerb("pick up");
		cob.addVerb("snatch");
		buck.addHandler(cob);

		// prob'ly outta be in the room
		room.addObject(buck);

	}

	/** Add a candy to remove */
	public void makeCandy() {
		SlageObject candy = new SlageObject("Candies");
		SlageImage icon = new SlageImage("org?slage?tests?content?candy.png");

		// Watch us take advantage of all our freebies...
		candy.setSceneImage(icon);
		candy.useImageBoundaryForClicking();
		candy.buildCursorFromSceneImage(); // use scene image for cursor...
		candy.buildInventoryImageFromSceneImage(); // ... and inventory icon
		// buck.useClickBoundaryForCollision(); // use click bound for collision

		candy.setPosition(new Point3D(550, 500, 2)); // put everything in place

		ResponseHandler look = new ResponseHandler(candy, "It looks like a perfect Halloween treat.", null);
		ResponseHandler lick = new ResponseHandler(candy, "It melts in your mouth, not in your hand. But it's on the floor, so pick it up first.", null);
		ResponseHandler take = new ResponseHandler(candy, "You deftly swipe the candy from the floor, hoping it's not been stepped on too many times.", null);

		look.addVerb("look");
		look.addVerb("examine");
		lick.addVerb("lick");
		lick.addVerb("taste");

		take.addVerb("grab"); // Specify a huge list of "take" verbs"
		take.addVerb("take");
		take.addVerb("pick up");
		take.addVerb("snatch");

		candy.addHandler(lick);
		candy.addHandler(look);
		candy.addHandler(take);

		// more freebies.. will assume current room, current player
		TakeObjectHandler takeObj = new TakeObjectHandler(candy);

		takeObj.addVerbWithSynonyms("take", this); // replace the huge list with
		// this.
		takeObj.setScore(5);
		candy.addHandler(takeObj);

		ChangeObjectButton cob = new ChangeObjectButton(this, candy);
		cob.addVerb("grab");
		cob.addVerb("take");
		cob.addVerb("pick up");
		cob.addVerb("snatch");
		candy.addHandler(cob);

		// prob'ly outta be in the room
		room.addObject(candy);

	}

	/** Hard-code a pause key click */
	public void makePauseHandler() {
		addKeyHandler(new PauseHandler(this), KeyEvent.VK_F10);
		addKeyHandler(new QuitHandler(), KeyEvent.VK_Q, false, true, false);

		// toggle debug mode (bounding shapes, etc)
		addKeyHandler(new ToggleDebugHandler(this), KeyEvent.VK_TAB);

		// cycle toolbar rendering
		addKeyHandler(new CycleRenderHandler(this), KeyEvent.VK_EQUALS);

		addKeyHandler(new FocusConsoleHandler(this), KeyEvent.VK_ENTER);

		// manual scrolling
		addKeyHandler(new ScrollHandler(this, 5, 0), KeyEvent.VK_PAGE_UP);
		addKeyHandler(new ScrollHandler(this, -5, 0), KeyEvent.VK_PAGE_DOWN);

		// toggle scroll lock
		addKeyHandler(new LockViewportHandler(this, tryTheSpecial), java.awt.event.KeyEvent.VK_HOME);
		addKeyHandler(new LockViewportHandler(this), java.awt.event.KeyEvent.VK_END);

		addKeyHandler(new PrintHistory(this), java.awt.event.KeyEvent.VK_F1);
	
		// open imagetest in the editor
		addKeyHandler(new LaunchEditorHandler(this), java.awt.event.KeyEvent.VK_SCROLL_LOCK);

		// play a sound when space is pressed
		addKeyHandler(new PlaySoundHandler(this, Tools.GetFile("org.slage.tests.content", "congratulations-escaped.ogg")), java.awt.event.KeyEvent.VK_SPACE);

		// save the game
		addKeyHandler(new SaveWithChooserDialog(this), java.awt.event.KeyEvent.VK_F5);

		// inventory dialog, we hope
		addKeyHandler(new ShowInventoryHandler(getPlayer()), java.awt.event.KeyEvent.VK_F4);
		addKeyHandler(new CloseInventoryHandler(getPlayer()), java.awt.event.KeyEvent.VK_ESCAPE);

	}

	 
	/** Initialize the stuff */
	public void initialize() {
		super.initialize();

		// Make a VSL, and add it to the game
		VerbSynonymList takelist = new VerbSynonymList("take");
		takelist.add("grab");
		takelist.add("pick up");
		takelist.add("snatch");
		this.applyVerbSynonymList(takelist);

		// every game needs a player
		SlagePlayer player = new SlagePlayer("Roger Wilco", "SQ7 Testing Team");
		player.setSceneImage(new SlageImage("org?slage?tests?content?roger_eva.jpg"));
		player.setClickBoundary(new Boundary(player.getSceneImage(), 10));
		player.setZ(10);
		player.useClickBoundaryForCollision();

		AsyncKeyAction akaN = new AsyncKeyAction("North", new TranslateHandler(player, 0, -4));
		akaN.addKey(KeyEvent.VK_UP);

		AsyncKeyAction akaS = new AsyncKeyAction("South", new TranslateHandler(player, 0, 4));
		akaS.addKey(KeyEvent.VK_DOWN);

		AsyncKeyAction akaW = new AsyncKeyAction("West", new TranslateHandler(player, -4, 0));
		akaW.addKey(KeyEvent.VK_LEFT);

		AsyncKeyAction akaE = new AsyncKeyAction("East", new TranslateHandler(player, 4, 0));
		akaE.addKey(KeyEvent.VK_RIGHT);

		addAsyncAction(akaN);
		addAsyncAction(akaW);
		addAsyncAction(akaE);
		addAsyncAction(akaS);

		setPlayer(player);

		setAttribute("Debug Mode", new Boolean(false));

		ResponseHandler rlook = new ResponseHandler(player, "It's you!", null);
		rlook.addVerb("look");
		player.addHandler(rlook);
		ResponseHandler rlick = new ResponseHandler(player, "You lick yourself.", null);
		rlick.addVerb("Lick");
		rlick.addVerb("Kiss");
		player.addHandler(rlick);
		ResponseHandler rtake = new ResponseHandler(player, "This is a family game!", null);
		rtake.addVerb("Take");
		rtake.addVerb("Touch");
		player.addHandler(rtake);

		// set the default cursor
		getToolbar().setDefaultCursor(new SlageImage("org?slage?tests?content?sqcursor.PNG"));

		// make a fake Room
		room = new Room("Monolith Burger");
		room.setSceneImage(new SlageImage("org?slage?tests?content?monolith_scrollhall.jpg"));
		room.addObject(player);

		ResponseHandler look = new ResponseHandler(room, "A Monolith Burger! You haven't seen one of these in two or three sequels!", strMonolith);
		ResponseHandler lick = new ResponseHandler(room, "Having worked in a Monolith Burger once, you should know they are not sanitary enough to lick.", null);
		look.addVerb("look");
		look.addVerb("examine");
		lick.addVerb("lick");
		lick.addVerb("taste");
		room.addHandler(look);
		room.addHandler(lick);

		tryTheSpecial = new TextObject("Try the special!", new Font("Arial", 1, 14), Color.BLACK, new Point3D(0, room.getDimensions().height - 300, 0));
		room.addObject(tryTheSpecial);

		// build objects
		makeDuke();
		makePope();
		makeToolButtons();
		makeBathrooms();
		makeBuckazoid();
		makeArcade();
		makeCandy();

		makePauseHandler();

		// set up scheduling
		makeEvents();

		// set current room and add objects in it to draw list
		addObject(room);
		setRoom(room);

		loadSound(strMonolith, false);
	}

	/** Monolith burger ogg */
	private String strMonolith = Tools.GetQMarkDelimitedPath("org?slage?tests?content?SP-Buford-SearchMonolithBurger.ogg");

	/**
	 * Fire the game directly
	 * 
	 * @param args command line arguments
	 */
	public static void main(String[] args) {
		try 
                {
                    Slage.initialize();			
		}
                catch (Exception e) {
                    LOG.warn(e.getMessage(), e);
		}
                Slage.runGame(new ImageTest());
	}

	public static class CycleRenderHandler
			extends Handler {
		public void fire() {
			SlageGame game = (SlageGame) getTarget();

			int i = game.getToolbar().getRenderMode();
			if (i == 2)
				game.getToolbar().setRenderMode(0);
			else
				game.getToolbar().setRenderMode(++i);
		}

		public CycleRenderHandler(SlageGame game) {
			super(game);
		}

	}


	public static class ImageTestMotion
			extends Handler<SlageObject, SlageObject, SlageObject> {

		public ImageTestMotion(SlageObject duke, TextObject ttS) {
			setTarget(duke);
			setIndirectObj(ttS);
		}

		/** moves Duke across the screen */
		protected void fire() {
			Room room = org.slage.Slage.getCurrentGame().getRoom();
			SlageObject duke = getTarget();
			TextObject tryTheSpecial = (TextObject) getIndirectObj();

			duke.translate(3, 3);
			if (duke.getPosition().x > 500)
				duke.placeAtOrigin();

			// moving of text object
			tryTheSpecial.translate(5, 0);
			if (tryTheSpecial.getPosition().x > room.getDimensions().width)
				tryTheSpecial.setPosition(new Point3D(0, tryTheSpecial.getPosition().y, tryTheSpecial.getPosition().z));
		}
	}

	public static class PrintHistory
			extends Handler {

		public PrintHistory(SlageGame game) {
			super(game);
		}

		protected void fire() {
			SlageGame game = (SlageGame) getTarget();
			if (game.getCommandHistory().getCommandCount() == 0)
				return;

			for (int i = 0; i < game.getCommandHistory().getCommandCount(); i++) {
				try {
					LOG.info(game.getCommandHistory().getCommand(i));
				} catch (Exception e) {
					LOG.error(e.getMessage(), e);
					throw new RuntimeException(e);
				}
			}
		}
	}

	
	public static class ChangeObjectButton
			extends Handler<SlageObject, SlageObject, SlageObject> {

		public ChangeObjectButton(SlageGame game, SlageObject object) {
			super(game);
			this.setIndirectObj(object);
		}

		/** moves Duke across the screen */
		protected void fire() {
			SlageGame game = (SlageGame) getTarget();
			for (int i = 0; i < game.getToolbar().getButtonCount(); i++) {
				org.slage.ui.ToolBarButton tbb = game.getToolbar().getButton(i);
				if (tbb instanceof org.slage.ui.ObjectButton)
					((org.slage.ui.ObjectButton) tbb).setObject(getIndirectObj());
			}
		}
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
