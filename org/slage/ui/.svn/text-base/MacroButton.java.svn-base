/*
 * MacroButton.java
 *
 * Created on October 11, 2005, 2:45 PM
 */

package org.slage.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.slage.command.Command;

/**
 * Tool bar button that fires a command macro when clicked.
 * 
 * @author <a href="mailto:Matt@SQ7.org">Matt Holden</a>
 */
public class MacroButton
		extends ToolBarButton {
	/** command macro */
	private String strMacro;

	/** Single instance of the input handler for all buttons */
	private static final ActionListener listenerMacro = new ActionListener() {
		public void actionPerformed(ActionEvent evt) {
			MacroButton MB = (MacroButton) evt.getSource();
			String strCommand = MB.getMacro();

			// fake a command
			// Command command =
			// org.slage.tests.FakeCommandHandler.handleCommand(strCommand);

			// TODO: replace above line with this:
			Command command = null;
			try {
				command = MB.getToolbar().getGame().getParser().parseToCommand(strCommand);
			} catch (Exception e) {
				LOG.debug(e);
			}

			// give focus to frame for keys
			MB.getToolbar().getGame().getFrame().requestFocusInWindow();

			if (command != null)
				command.fire();
		}
	};

	/**
	 * Creates a new instance of MacroButton
	 * 
	 * @param strNewMacro Macro to send to the parser
	 * @param strImgButton image file name (QMarked)
	 */
	public MacroButton(String strNewMacro, String strImgButton) {
		super(strImgButton);
		this.strMacro = strNewMacro;

		addActionListener(listenerMacro);
	}

	/** Gets the "label" for the button. Here, a verb */
	public String getButtonLabel() {
		return strMacro;
	}

	/**
	 * Getter for macro.
	 * 
	 * @return Value of property strMacro.
	 */
	public java.lang.String getMacro() {
		return strMacro;
	}

	/**
	 * Setter for macro
	 * 
	 * @param strNewMacro New value of property strMacro.
	 */
	public void setMacro(String strNewMacro) {
		this.strMacro = strNewMacro;
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
