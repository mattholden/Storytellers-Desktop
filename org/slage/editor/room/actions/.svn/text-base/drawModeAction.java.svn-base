package org.slage.editor.room.actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import org.slage.editor.room.RoomImageEditor;

/**
 * An action that will put the provided RoomImageEditor into <i>draw</i> mode.
 * 
 * @author Jared Cope
 */
public class drawModeAction
		extends AbstractAction {

	/** the editor to put into draw mode */
	private RoomImageEditor editor;

	/**
	 * Constructs this action with the editor that will be put into <i>draw</i>
	 * mode.
	 * 
	 * @param anEditor the editor to put into draw mode
	 */
	public drawModeAction(RoomImageEditor anEditor) {
		this.editor = anEditor;
	}

	/**
	 * Will put the editor provided in the constructor of this action into draw
	 * mode.
	 * 
	 * @param e the event that triggered this action
	 */
	public void actionPerformed(ActionEvent e) {
		editor.setDrawMode(true);
	}
}
