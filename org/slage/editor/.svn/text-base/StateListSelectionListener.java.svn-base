package org.slage.editor;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.slage.framework.Attribute;

/**
 * A listener that will react to a selection change in a StateList
 * 
 * @author Jared Cope
 */
public class StateListSelectionListener implements ListSelectionListener {

	/** an object used to display the selected state */
	private StateEditor editor;

	/**
	 * Constructs this listener with the <i>editor</i> that will be used to
	 * display the selected state from the state list.
	 * 
	 * @param anEditor used to display the chosen state in the list
	 */
	public StateListSelectionListener(StateEditor anEditor) {
		this.editor = anEditor;
	}

	/**
	 * Will react to a change in selection for a StateList. The newly selected
	 * state will then be displayed by the <i>editor</i> supplied in the
	 * constructor for this listener.
	 * 
	 * @param e the event that triggered this action
	 */
	public void valueChanged(ListSelectionEvent e) {
		if (!e.getValueIsAdjusting()) {
			StateList source = (StateList) e.getSource();

			int selectedIndex = source.getSelectedIndex();
			Attribute chosenState = source.getStateForIndex(selectedIndex);

			editor.displayState(chosenState);
		}
	}
}
