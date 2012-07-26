package org.slage.editor.actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JFileChooser;

import org.slage.editor.Editor;
import org.slage.editor.EditorFileFilter;

/**
 * An action that will save the game that is currently in the editor.
 * 
 * @author Jared Cope
 */
public class SaveAction
		extends AbstractAction {

	/** the editor that will save the game */
	private Editor editor;
	/** the file chooser for selecting a file to save to */
	private JFileChooser fc = new JFileChooser(System.getProperty("user.dir"));

	/**
	 * Constructs this action with the <i>editor</i> that will save the game. A
	 * JFileChooser is also created for use when this action fires.
	 * 
	 * @param anEditor the editor that will save the game
	 */
	public SaveAction(Editor anEditor) {
		this.editor = anEditor;
		fc.setDialogType(JFileChooser.SAVE_DIALOG);
		fc.setDialogTitle("Save game");
		fc.setAcceptAllFileFilterUsed(false);
		fc.setFileFilter(new EditorFileFilter());
	}

	/**
	 * Will present a JFileChooser to the user so that a game can be saved to a
	 * file.
	 * 
	 * @param e the event that triggered this action
	 */
	public void actionPerformed(ActionEvent e) {
		if (fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			editor.saveGame(fc.getSelectedFile().getAbsolutePath());
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
