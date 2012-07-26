package org.slage.editor;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Polygon;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

import org.slage.Slage;
import org.slage.SlageGame;
import org.slage.SlageObject;
import org.slage.editor.actions.EditorWindowListener;
import org.slage.editor.gametree.AbstractTreeNode;
import org.slage.editor.gametree.ActNode;
import org.slage.editor.gametree.GameNode;
import org.slage.editor.gametree.GameTreePanel;
import org.slage.editor.gametree.ObjectNode;
import org.slage.editor.gametree.RoomNode;
import org.slage.editor.gametree.SceneNode;
import org.slage.editor.menu.EditorMenuBar;
import org.slage.editor.room.RoomNodeGUI;
import org.slage.handlers.SaveHandler;
import org.slage.xml.XMLProcessor;

/**
 * An editor that will allow a game author to create a SLAGE game. The work
 * done in the editor can be saved as an XML file, which can be loaded straight
 * into the SLAGE game engine and played.
 * 
 * @author Jared Cope
 */
public class SlageGameEditor extends JFrame implements Editor {

	/** the panel with the tree that holds the hierarchy of game elements */
	private GameTreePanel treePanel;
	/** a menu for the editor */
	private EditorMenuBar menuBar;
	/** a split pane to hold the main GUI for the editor */
	private JSplitPane splitPane;

	/**
	 * Creates and starts the game editor with the provided <i>game</i>
     * instance object.
	 * 
	 * @param game the game to load into the editor
	 */
	public SlageGameEditor(SlageGame game) {
		this();
		loadObject(game);
	}

	/**
	 * Creates and starts the game editor with a blank slate, so that the user
     * can start a new game from scratch.
	 */
	public SlageGameEditor() {

		Slage.initialize();

		getContentPane().setLayout(new BorderLayout());

		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new EditorWindowListener());
		setTitle("SLAGE Game Editor");
		int width = Toolkit.getDefaultToolkit().getScreenSize().width;
		int height = Toolkit.getDefaultToolkit().getScreenSize().height - 300;
		setSize(new Dimension(width, height - 30));

		menuBar = new EditorMenuBar(this);
		treePanel = new GameTreePanel(this);

		setJMenuBar(menuBar);
		splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, treePanel,
                                   new JPanel());

		getContentPane().add(splitPane, BorderLayout.CENTER);

		setVisible(true);
		splitPane.setDividerLocation(0.15D);
	}

	/**
	 * Will change the right hand side GUI of the editor to match the given
	 * <i>node</i>. Different types of nodes represent different game
	 * information, so the GUI needs to allow the author to interact in
     * different ways.
	 */
	public void changeGUIForNode(AbstractTreeNode node) {
		if (node instanceof GameNode) {
			splitPane.setRightComponent(new GameNodeGUI((GameNode) node));
			splitPane.setDividerLocation(0.15D);
		} else if (node instanceof ActNode) {
			splitPane.setRightComponent(new ActNodeGUI((ActNode) node));
			splitPane.setDividerLocation(0.15D);
		} else if (node instanceof SceneNode) {
			splitPane.setRightComponent(new SceneNodeGUI((SceneNode) node));
			splitPane.setDividerLocation(0.15D);
		} else if (node instanceof RoomNode) {
			splitPane.setRightComponent(new RoomNodeGUI((RoomNode) node, this));
			splitPane.setDividerLocation(0.15D);
		} else if (node instanceof ObjectNode) {
			splitPane.setRightComponent(new ObjectNodeGUI((ObjectNode) node, this));
			splitPane.setDividerLocation(0.15D);
		} else {
			JOptionPane.showMessageDialog(null, "GUI not yet implemented to"
                    + " show a " + node.getClass() + " node.\n\n"
                    + "Its on the TODO list");
			Slage.getLogger().error("Must build GUI for node: " + node);
		}
	}

	/**
	 * Will load a previously saved game into the editor so that it can be
     * worked on further.
	 * 
	 * @param fileLocation the location of the game file to load
	 */
	public void loadSavedGame(String fileLocation) {
		try {
			SlageObject object = (SlageObject) XMLProcessor.dispatch(fileLocation);
			loadObject(object);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Could not open the game:\n\n"
                                          + e.getMessage());
			Slage.getLogger().error("", e);
		}
	}

	/**
	 * Will load the provided <i>object</i> instance into the editor so that it
	 * can be edited. This will clear any previous work from the editor.
	 * 
	 * @param object the object to load into the editor
	 */
	public void loadObject(SlageObject object) {
		splitPane.setRightComponent(new JPanel());
		splitPane.setDividerLocation(0.15D);
		treePanel.setGame(object);
	}

	/**
	 * Will save the current game in the editor to the file <i>filePath</i>.
	 * 
	 * @param filePath the location for the save file
	 */
	public void saveGame(String filePath) {
		SlageObject slageObjectToSave = treePanel.getRootNode();
		new SaveHandler(slageObjectToSave, filePath).execute();
	}

	/**
	 * Will clear the current game in the editor and create a blank game to
     * start editing.
	 */
	public void createNewGame() {
		treePanel.createNewGame();
		splitPane.setRightComponent(new JPanel());
		splitPane.setDividerLocation(0.15D);
	}

	/**
	 * Will clear the current game in the editor and create a blank act to start
	 * editing.
	 */
	public void createNewAct() {
		treePanel.createNewAct();
		splitPane.setRightComponent(new JPanel());
		splitPane.setDividerLocation(0.15D);
	}

	/**
	 * Will clear the current game in the editor and create a blank scene to
     * start editing.
	 */
	public void createNewScene() {
		treePanel.createNewScene();
		splitPane.setRightComponent(new JPanel());
		splitPane.setDividerLocation(0.15D);
	}

	/**
	 * Will clear the current game in the editor and create a blank room to
     * start editing.
	 */
	public void createNewRoom() {
		treePanel.createNewRoom();
		splitPane.setRightComponent(new JPanel());
		splitPane.setDividerLocation(0.15D);
	}

	/**
	 * Will clear the current game in the editor and create a blank object to
	 * start editing.
	 */
	public void createNewObject() {
		treePanel.createNewObject();
		splitPane.setRightComponent(new JPanel());
		splitPane.setDividerLocation(0.15D);
	}

	/**
	 * Will clear the editor and remove all the work that has been done so far.
	 * This will remove the game tree and the GUI used to edit game components.
	 * <p>
	 * Before carrying out these tasks, the editor will prompt for confirmation
     * as this will wipe a lot of work for the author if they are not careful.
	 */
	public void clearCurrentSession() {
		int response = JOptionPane.showConfirmDialog(this,
                "You have selected to delete your whole editing session.\n\n"
                + "All your work will be lost. Do you wish to continue?",
                "Delete all work", JOptionPane.YES_NO_OPTION);
		if (response == JOptionPane.YES_OPTION) {
			treePanel.clearSession();
			splitPane.setRightComponent(new JPanel());
			splitPane.setDividerLocation(0.15D);
		}
	}

	/**
	 * Will find and select the node within the game tree that contains the
	 * provided <i>object</i>.
	 * <p>
	 * If the object exists, the GUI will change to display that object.
	 * <p>
	 * If the object does not exist, then there will be no reaction.
	 * 
	 * @param object the object to display in the editor
	 */
	public void selectNodeWithObject(SlageObject object) {
		treePanel.selectObjectInTree(object);
	}

	/**
	 * Will create a new <b>SLAGEObject</b> using the provided <i>polygon</i> as
	 * a clickboundary, and attach it to the currently selected tree node.
	 * 
	 * @param polygon the polygon to construct the click boundary with
	 */
	public void addObjectToCurrentNode(Polygon polygon) {
		treePanel.addObjectForCurrentNode(polygon);
	}

	/**
	 * Starts the editor application. There are no command line arguments
     * needed.
	 * 
	 * @param args the program command line arguments
	 */
	public static void main(String[] args) {
		new SlageGameEditor();
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
