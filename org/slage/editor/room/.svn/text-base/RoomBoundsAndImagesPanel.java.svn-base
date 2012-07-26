package org.slage.editor.room;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import org.slage.SlageObject;
import org.slage.editor.Editor;
import org.slage.editor.room.actions.drawModeAction;
import org.slage.framework.Point3D;
import org.slage.SlageImage;
import org.slage.framework.Tools;

import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

/**
 * TODO
 * 
 * @author Jared Cope
 */
public class RoomBoundsAndImagesPanel
		extends JPanel {

	/** the room object that this panel is used to edit */
	private SlageObject room;
	/** a scroll pane for the picture editing area */
	private JScrollPane pictureScrollPane;
	/** the editor for the room image, used to make click boundaries */
	private RoomImageEditor roomImageEditor;
	/** a button to put the room image editor in draw mode */
	private JButton defineClickBoundaryButtion;
	/** a button to allow a walkable area to be drawn */
	private JButton defineWalkableArea;
	/** the label for the scroll x value */
	private JLabel scrollXLabel = new JLabel("X");
	/** the label for the scroll y value */
	private JLabel positionYLabel = new JLabel("Y");
	/** the field for the scroll x value */
	private JTextField scrollXField = new JTextField(4);
	/** the field for the scroll y value */
	private JTextField scrollYField = new JTextField(4);
	/** the label for the scene image field */
	private JLabel sceneImageLabel = new JLabel("Scene image");
	/** the field for the path to the scene image */
	private JTextField sceneImageField = new JTextField(20);
	/** a button for choosing a room image */
	private JButton chooseSceneImageButton = new JButton("Browse ...");
	/** a file chooser for loading the room image */
	private JFileChooser fc = new JFileChooser(System.getProperty("user.dir"));

	/**
	 * Constructs the panel with the room object that will be edited with the GUI
	 * and the editor that is used for editor wide action responses.
	 * 
	 * @param aRoom the room to be edited on the GUI
	 * @param editor the editor that will handle actions
	 */
	public RoomBoundsAndImagesPanel(SlageObject aRoom, Editor editor) {

		this.room = aRoom;
		setLayout(new BorderLayout());
		roomImageEditor = new RoomImageEditor(editor, this);

		chooseSceneImageButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fc.setDialogType(JFileChooser.OPEN_DIALOG);
				fc.setDialogTitle("Open picture for room image");
				fc.setAcceptAllFileFilterUsed(false);

				int action = fc.showOpenDialog(null);
				if (action == JFileChooser.APPROVE_OPTION) {
					setImageForRoom(fc.getSelectedFile().getAbsolutePath());
					sceneImageField.setText(fc.getSelectedFile().getAbsolutePath());
				}
			}
		});

		add(buildRoomImagePanel(), BorderLayout.NORTH);
		add(buildControlsPanel(), BorderLayout.CENTER);

		populatePanel();
	}

	/**
	 * Will reapply all the click boundary outlines to the room image so that it
	 * is clear to see the defined boundaries on the room image.
	 */
	public void refreshClickBoundaries() {
		addClickBoundariesToPicture();
	}

	/**
	 * Fills in the fields on this panel with the information contained in the
	 * room object provided in the constructor.
	 */
	private void populatePanel() {
		SlageImage img = room.getSceneImage();
		if (img != null) {
			sceneImageField.setText(room.getSceneImage().getAbsolutePath());
			setImageForRoom(room.getSceneImage().getAbsolutePath());
		}

		Point3D position = room.getPosition();
		if (position != null) {
			scrollXField.setText("" + room.getPosition().x);
			scrollYField.setText("" + room.getPosition().y);
		}

		addClickBoundariesToPicture();
	}

	/**
	 * Will draw the click boundaries defined on this room onto the room image
	 * editor.
	 * <p>
	 * TODO I am assuming that an object is a click boundary if its scene image is
	 * null. Perhaps this isn't the best way to determine.
	 * 
	 */
	private void addClickBoundariesToPicture() {
		List objects = room.getContainedObjects();
		for (int i = 0; i < objects.size(); i++) {
			Object o = objects.get(i);
			if (o.getClass() == SlageObject.class) {
				SlageObject obj = (SlageObject) o;
				if (obj.getSceneImage() == null) {
					// it uses the room image for its view
					roomImageEditor.addObject(obj);
				}
			}
		}
	}

	/**
	 * Will set the image for this room to the file specified by <i>imageFilePath</i>.
	 * <p>
	 * The room image editor will be updated, as well as the room object for this
	 * panel.
	 * 
	 * @param imageFilePath the path to the room image
	 */
	private void setImageForRoom(String imageFilePath) {
		SlageImage roomImage = new SlageImage(Tools.BuildQMarkDelimitedPath(imageFilePath));
		roomImageEditor.setImage(new ImageIcon(roomImage.getImageIcon().getImage()));
		room.setSceneImage(roomImage);
	}

	/**
	 * Returns a panel containing the GUI elements.
	 * 
	 * @return a panel containing the GUI elements
	 */
	private JPanel buildRoomImagePanel() {

		pictureScrollPane = new JScrollPane(roomImageEditor);
		pictureScrollPane.setPreferredSize(new Dimension(400, 500));
		pictureScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		pictureScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		pictureScrollPane.setViewportBorder(BorderFactory.createLineBorder(Color.black));

		FormLayout layout = new FormLayout("pref, pref:grow", "p:grow, p:grow, p:grow," + "p:grow, p:grow, p:grow");
		PanelBuilder builder = new PanelBuilder(layout);
		CellConstraints cc = new CellConstraints();

		defineClickBoundaryButtion = new JButton("Define click boundary");
		defineClickBoundaryButtion.setToolTipText("<html>" + "Puts the image editor into the mode that allows" + "<p><b>click boundaries</b> to be defined on the image.</html>");
		defineClickBoundaryButtion.addActionListener(new drawModeAction(roomImageEditor));
		builder.add(defineClickBoundaryButtion, cc.xy(1, 1));

		defineWalkableArea = new JButton("Define walkable area");
		defineWalkableArea.setToolTipText("<html>" + "Puts the image editor into the mode that allows" + "<p><b>walkable areas</b> to be defined on the image.</html>");

		builder.add(defineWalkableArea, cc.xy(1, 2));
		builder.add(new JCheckBox("Show click boundaries"), cc.xy(1, 3));
		builder.add(new JCheckBox("Show walkable areas"), cc.xy(1, 4));
		builder.add(new JCheckBox("Show independant objects"), cc.xy(1, 5));
		builder.add(new JCheckBox("Show all"), cc.xy(1, 6));
		builder.add(pictureScrollPane, cc.xywh(2, 1, 1, 6));

		return builder.getPanel();
	}

	/**
	 * Returns a panel with the scroll start fields and image selection fields.
	 * 
	 * @return a panel with the scroll start fields and image selection fields
	 */
	private JPanel buildControlsPanel() {
		FormLayout layout = new FormLayout("right:pref, 3dlu, right:pref, 3dlu, right:pref, 3dlu," + "pref, 3dlu, pref, 3dlu, pref:grow, 3dlu, pref," + " 6dlu, pref, 3dlu, pref", "p, 3dlu, p");

		PanelBuilder builder = new PanelBuilder(layout);
		CellConstraints cc = new CellConstraints();

		builder.addSeparator("Scroll start", cc.xyw(1, 1, 7));
		builder.add(scrollXLabel, cc.xy(1, 3));
		builder.add(scrollXField, cc.xy(3, 3));

		builder.add(positionYLabel, cc.xy(5, 3));
		builder.add(scrollYField, cc.xy(7, 3));

		builder.addSeparator("Images", cc.xyw(9, 1, 9));
		builder.add(sceneImageLabel, cc.xy(9, 3));
		sceneImageField.setEditable(false);
		builder.add(sceneImageField, cc.xyw(11, 3, 5));
		builder.add(chooseSceneImageButton, cc.xy(17, 3));

		return builder.getPanel();
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
