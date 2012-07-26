package org.slage.editor.actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 * An action that will add a <code>String</code> from a text field into a
 * JList.
 * 
 * @author Jared Cope
 */
public class AddWordToListAction
		extends AbstractAction {

	/** the list to add the string into */
	private JList list;
	/** the text field to source the string from */
	private JTextField field;

	/**
	 * Constructs this action with the <i>list</i> that will receive the string
	 * and the <i>field</i> that is the source of the string.
	 * 
	 * @param aList the list to receive the string
	 * @param aField the field to supply the string
	 */
	public AddWordToListAction(JList aList, JTextField aField) {
		this.list = aList;
		this.field = aField;
	}

	/**
	 * Adds a string from the textfield into the list.
	 * 
	 * @param e the event that triggered this action
	 */
	public void actionPerformed(ActionEvent e) {
		String word = field.getText().trim();
		if (word == null || word.equals("")) {
			JOptionPane.showMessageDialog(null, "There is no word entered. Please enter a word and try " + "again.", "No word entered", JOptionPane.OK_OPTION);
			return;
		}

		DefaultListModel model = (DefaultListModel) list.getModel();
		if (!model.contains(word)) {
			model.addElement(word);
		}

		field.setText("");
		field.requestFocus();
	}
}
