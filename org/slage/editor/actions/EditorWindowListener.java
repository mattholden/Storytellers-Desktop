package org.slage.editor.actions;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JOptionPane;

/**
 * A listener that should be registered on the main editor frame. This class
 * provides an implementation for the <i>windowClosing()</i> method to prompt
 * the user for confirmation when exiting the editor.
 * 
 * @author Jared Cope
 */
public class EditorWindowListener
		extends WindowAdapter {

	/**
	 * Will prompt the user for confirmation when an attempt is made to exit the
	 * editor using the <i>X</i> window control near the minimize and maximize
	 * window controls.
	 * 
	 * @param e the event that triggered this action
	 */
	public void windowClosing(WindowEvent e) {
		int choice = JOptionPane.showConfirmDialog(null, "Exit the SLAGE Game editor", "Do you wish to exit the editor?", JOptionPane.YES_NO_OPTION);

		if (choice == JOptionPane.YES_OPTION) {
			System.exit(1);
		} else {
			return;
		}
	}
}
