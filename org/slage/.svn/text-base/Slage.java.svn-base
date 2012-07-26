package org.slage;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.slage.framework.Tools;
import org.slage.xml.XMLProcessor;
import org.slage.resource.ResourceManager;
import org.slage.resource.SoftCachingResourceManager;

/**
 * Holds all the settings for the engine itself; things loaded in from script
 * that don't have to be saved with the game state.
 * 
 * @author <a href="mailto:Matt@SQ7.org">Matt Holden</a>
 */
public abstract class Slage {

	/*****************************************************************************
	 * Game state array
	 ****************************************************************************/

	/** The Game State */
	private static ArrayList<SlageGame> listGames = new ArrayList<SlageGame>();

	/** Current game state */
	private static int iCurrentGame = -1;

	/**
	 * Accessor for game state
	 * 
	 * @return state
	 */
	public static SlageGame getCurrentGame() {
		return (iCurrentGame == -1) ? null : listGames.get(iCurrentGame);
	}

       /*****************************************************************************
	 * Slage version information
	 ****************************************************************************/

	/**
	 * Accessor for Slage version
	 * 
	 * @return SLAGE_VERSION
	 */
	public static float getVersion() {
		return SLAGE_VERSION;
	}

	/** The version number of this build of Slage */
	private static final float SLAGE_VERSION = 1.00f;

	/**
	 * Accessor for version as a title
	 * 
	 * @return version and title
	 */
	public static String getVersionText() {
		return ("Slage Adventure Game Engine v" + Tools.TrimFloat(Slage.getVersion(), 2, 4, true));
	}

	/*****************************************************************************
	 * Log4j
	 ****************************************************************************/

	/** The logger used for Slage */
	private static Log LOG = LogFactory.getLog(Slage.class);

	/**
	 * Returns the logger for slage. This enables other SLAGE code to log messages
	 * with this logger.
	 * 
	 * @return the logger for slage
	 */
	public static Log getLogger() {
		return LOG;
	}

	/*****************************************************************************
	 * Utility functionality
	 ****************************************************************************/

	/** Ensure we have the appropriate Java version */
	public static void checkJavaVersion() {
		// Make sure we have Java 1.5+
		if (Tools.getJavaVersion() < 1.5f) {
			JOptionPane.showMessageDialog(null, "The game you are trying to play requires at least version 1.5 of " + "the Java Runtime Environment. " + "Please update your JRE.", "Java Update Required", JOptionPane.ERROR_MESSAGE);
			LOG.fatal("JRE version is less than 1.5. Quitting.");
			System.exit(1);
		}
		LOG.info("JRE version (" + Tools.getJavaVersion() + ") is adequate to run Slage.");

	}

	/**
	 * Workaround for Windows machines - Java2D doesn't like to be drawn with
	 * DirectDraw. Replacement for -Dsun.java2d.noddraw=true on the command line.
	 */
	public static void disableDirectDraw() {
		if (System.getProperty("os.name").startsWith("Windows")) {
			System.setProperty("sun.java2d.noddraw", "true");
		}
	}

	/*****************************************************************************
	 * Main Game Loop
	 ****************************************************************************/

	/**
	 * The game's initialization routines. This function should initialize any
	 * managers and other technologies needed to make Slage operate.
	 */
	public static void initialize() {

		checkJavaVersion();
		disableDirectDraw();

	}

	/**
	 * The contents of the game loop. This is the function that will be the heart
	 * and soul of Slage's in-game operations.
	 */
	public static void update() {
		// Update the game state's stuff
		getCurrentGame().update();
	}

	/**
	 * The game's shutdown routines. This function should shut down any managers
	 * or techologies as needed.
	 */
	public static void shutdown() {
		for (SlageGame game : listGames) {
			game.shutdown();
		}
	}

	/*****************************************************************************
	 * Game launcher support
	 ****************************************************************************/

	/**
	 * Run a game from a SlageGame object
	 * 
	 * @param game SlageGame to run
	 */
	public static void runGame(SlageGame game) {
		listGames.add(game);
		iCurrentGame = listGames.size() - 1;

		game.initialize();

		while (true) {
			update(); // calls getCurrentGame().update too
		}
	}

	/**
	 * The entry point for our entire application
	 * 
	 * @param args command line arguments.
	 */
	public static void main(String[] args) {
		initialize();

		String strXML = args[0].toLowerCase();
		if (strXML.endsWith(".xml")) {
			try {
				String strXMLFile = Tools.GetQMarkDelimitedPath(strXML);
				SlageGame game = (SlageGame) XMLProcessor.dispatch(strXMLFile);

				if (game == null)
					throw new IllegalArgumentException("Slage.main(): XML file not found or not valid.");

				runGame(game);
				return;
			} catch (Exception e) {
				LOG.error(e);
			}
		}
		LOG.error(new IllegalArgumentException("Slage.main(): First parameter must be a valid XML file."));
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
