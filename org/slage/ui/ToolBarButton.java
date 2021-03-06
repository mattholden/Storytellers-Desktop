/*
 * ToolBarButton.java
 *
 * Created on August 24, 2005, 11:06 PM
 */

package org.slage.ui;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.slage.SlageImage;

/**
 * Defines a graphical button to dock on the ToolBar.
 * TODO: refactor to have-a JButton vs. be-a JButton
 * 
 * @author <a href="mailto:Matt@SQ7.org">Matt Holden</a>
 */
public abstract class ToolBarButton
		extends JButton  {
	/** logger instance */
	protected transient static final Log LOG = LogFactory.getLog(ToolBarButton.class);

	/** Image for the button. Stored separately to support render states. */
	private SlageImage imgButton = null;

	/**
	 * Creates a new instance of ToolBarButton
	 * 
	 * @param strImgButton image for the button
	 */
	public ToolBarButton(String strImgButton) {
		setBounds(0, 0, ToolBar.TOOLBAR_HEIGHT, ToolBar.TOOLBAR_HEIGHT);

		setDefaultLookAndFeel();

		setFocusable(false);
		this.setRequestFocusEnabled(false);

		setFocusTraversalKeysEnabled(false); // disable focus change when tab is
		// pressed

		if (strImgButton != null)
			setImage(new SlageImage(strImgButton));

		/*
		 * addActionListener(new ActionListener() { public void
		 * actionPerformed(ActionEvent evt) { // give focus to frame for keys
		 * ToolBarButton TBB = (ToolBarButton)evt.getSource();
		 * TBB.getToolbar().getGame().getFrame().requestFocusInWindow(); } });
		 */
	}

	/**
	 * Accessor for image as it should appear in the button
	 * 
	 * @return imgButton
	 */
	public SlageImage getImage() {
		return imgButton;
	}

	/**
	 * Modifier for image as it should appear as a button
	 * 
	 * @param image new image
	 */
	public void setImage(SlageImage image) {
		this.imgButton = image;
		imgButton.scale(ToolBar.TOOLBAR_HEIGHT, ToolBar.TOOLBAR_HEIGHT);

		if (toolbar != null)
			setRenderMode(toolbar.getRenderMode());
	}

	/** Define a label for the button when rendering in text mode */
	public abstract String getButtonLabel();

	/**
	 * Set the current render state
	 * 
	 * @param iState A Rendering constant. One of the following:
	 *        <li>RENDER_ICONS
	 *        <Li>RENDER_TEXT
	 *        <LI>RENDER_BOTH
	 */
	public void setRenderMode(int iState) {
		if (iState < 0 || iState > 2)
			return;

		switch (iState) {
		case ToolBar.RENDER_TEXT:
			setText(getButtonLabel());
			setIcon(null);
			break;

		case ToolBar.RENDER_ICONS:

			// if there is no image, use text anyway
			if (imgButton == null)
				setText(getButtonLabel());
			else {
				setText(null);
				setIcon(imgButton.getImageIcon());
			}
			break;

		case ToolBar.RENDER_BOTH:
			setText(getButtonLabel());
			setIcon(imgButton.getImageIcon());
			break;

		}

	}

	/** Set default colors and font */
	public void setDefaultLookAndFeel() {
		setForeground(Color.BLACK);
		setBackground(Color.DARK_GRAY);
		setFont(new Font("Arial", 1, 10));
		setRenderMode(ToolBar.RENDER_ICONS);
	}

	/**
	 * Getter for property toolbar.
	 * 
	 * @return Value of property toolbar.
	 */
	public org.slage.ui.ToolBar getToolbar() {
		return toolbar;
	}

	/**
	 * Setter for property toolbar.
	 * 
	 * @param newToolbar New value of property toolbar.
	 */
	public void setToolbar(ToolBar newToolbar) {
		this.toolbar = newToolbar;
		if (newToolbar != null) {
			setRenderMode(newToolbar.getRenderMode());
        }
	}

	/** The toolbar that owns this button */
	private ToolBar toolbar;

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
