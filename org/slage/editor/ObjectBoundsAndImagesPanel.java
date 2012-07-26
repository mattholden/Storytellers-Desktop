package org.slage.editor;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import org.slage.SlageObject;
import org.slage.editor.room.RoomImageEditor;
import org.slage.framework.Point3D;
import org.slage.SlageImage;

import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

/**
 * TODO
 * 
 * @author Jared Cope
 */
public class ObjectBoundsAndImagesPanel
		extends JPanel {

	private SlageObject object;

	private JScrollPane pictureScrollPane;

	private JLabel positionXLabel = new JLabel("X");

	private JLabel positionYLabel = new JLabel("Y");

	private JLabel positionZLabel = new JLabel("Z");

	private JTextField positionXField = new JTextField(4);

	private JTextField positionYField = new JTextField(4);

	private JTextField positionZField = new JTextField(4);

	private JLabel sceneImageLabel = new JLabel("Scene image");

	private JTextField sceneImageField = new JTextField(20);

	private JButton chooseSceneImageButton = new JButton("Browse");

	private JLabel inventoryImageLabel = new JLabel("Inventory image");

	private JTextField inventoryImageField = new JTextField(20);

	private JButton chooseInventoryImageButton = new JButton("Browse");

	private JLabel cursorImageLabel = new JLabel("Cursor image");

	private JTextField cursorImageField = new JTextField(20);

	private JButton chooseCursorImageButton = new JButton("Browse");

	private JButton defineClickBoundaryButton = new JButton("Define ...");

	private JButton cbUseImageBoundary = new JButton("Use image boundary");

	private JButton cbUseCollisionBoundary = new JButton("Use collision boundary");

	private JButton defineCollisionBoundaryButton = new JButton("Define ...");

	private JButton colUseImageBoundary = new JButton("Use image boundary");

	private JButton colUseClickBoundary = new JButton("Use click boundary");

	private JFileChooser fc = new JFileChooser(System.getProperty("user.dir"));

	private RoomImageEditor picture;

	public ObjectBoundsAndImagesPanel(SlageObject anObject, Editor editor) {

		this.object = anObject;
		picture = new RoomImageEditor(editor);
		setLayout(new BorderLayout());

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

	private void populatePanel() {
		SlageImage img = object.getSceneImage();
		if (img != null) {
			sceneImageField.setText(object.getSceneImage().getAbsolutePath());
			setImageForRoom(object.getSceneImage().getAbsolutePath());
		}

		img = object.getInvImage();
		if (img != null) {
			inventoryImageField.setText(object.getInvImage().getAbsolutePath());
		}

		img = object.getCursorImage();
		if (img != null) {
			cursorImageField.setText(object.getCursorImage().getAbsolutePath());
		}

		Point3D position = object.getPosition();
		if (position != null) {
			positionXField.setText("" + object.getPosition().x);
			positionYField.setText("" + object.getPosition().y);
			positionZField.setText("" + object.getPosition().z);
		}

	}

	private void setImageForRoom(String imageFilePath) {
		ImageIcon image = new ImageIcon(imageFilePath);
		picture.setImage(image);
	}

	private JPanel buildRoomImagePanel() {

		pictureScrollPane = new JScrollPane(picture);
		pictureScrollPane.setPreferredSize(new Dimension(400, 400));
		pictureScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		pictureScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		pictureScrollPane.setViewportBorder(BorderFactory.createLineBorder(Color.black));

		FormLayout layout = new FormLayout("pref, pref:grow", "p:grow, p:grow, p:grow");
		PanelBuilder builder = new PanelBuilder(layout);
		CellConstraints cc = new CellConstraints();

		builder.add(new JButton("Define ..."), cc.xy(1, 1));
		builder.add(new JButton("Remove ..."), cc.xy(1, 2));
		builder.add(new JButton("Show all ..."), cc.xy(1, 3));
		builder.add(pictureScrollPane, cc.xywh(2, 1, 1, 3));

		return builder.getPanel();
	}

	private JPanel buildControlsPanel() {
		FormLayout layout = new FormLayout("right:pref, 3dlu, right:pref, 3dlu, right:pref, 3dlu," + "pref, 3dlu, pref, 3dlu, pref, 3dlu, pref, 6dlu, pref, 3dlu, pref", "p, 3dlu, p, 3dlu, p, 3dlu, p, 3dlu, p, 3dlu, p");

		PanelBuilder builder = new PanelBuilder(layout);
		CellConstraints cc = new CellConstraints();

		builder.addSeparator("Position", cc.xyw(1, 1, 11));
		builder.add(positionXLabel, cc.xy(1, 3));
		builder.add(positionXField, cc.xy(3, 3));

		builder.add(positionYLabel, cc.xy(5, 3));
		builder.add(positionYField, cc.xy(7, 3));

		builder.add(positionZLabel, cc.xy(9, 3));
		builder.add(positionZField, cc.xy(11, 3));

		builder.addSeparator("Images", cc.xyw(1, 5, 13));
		builder.add(sceneImageLabel, cc.xy(1, 7));
		builder.add(sceneImageField, cc.xyw(3, 7, 9));
		builder.add(chooseSceneImageButton, cc.xy(13, 7));

		builder.add(inventoryImageLabel, cc.xy(1, 9));
		builder.add(inventoryImageField, cc.xyw(3, 9, 9));
		builder.add(chooseInventoryImageButton, cc.xy(13, 9));

		builder.add(cursorImageLabel, cc.xy(1, 11));
		builder.add(cursorImageField, cc.xyw(3, 11, 9));
		builder.add(chooseCursorImageButton, cc.xy(13, 11));

		builder.addSeparator("Click boundary", cc.xyw(15, 5, 1));
		builder.add(defineClickBoundaryButton, cc.xy(15, 7));
		builder.add(cbUseImageBoundary, cc.xy(15, 9));
		builder.add(cbUseCollisionBoundary, cc.xy(15, 11));

		builder.addSeparator("Collision boundary", cc.xyw(17, 5, 1));
		builder.add(defineCollisionBoundaryButton, cc.xy(17, 7));
		builder.add(colUseImageBoundary, cc.xy(17, 9));
		builder.add(colUseClickBoundary, cc.xy(17, 11));

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
