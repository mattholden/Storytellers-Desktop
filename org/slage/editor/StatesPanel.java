package org.slage.editor;

import java.awt.BorderLayout;
import java.util.Collection;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import org.slage.SlageObject;
import org.slage.framework.Attribute;

import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

/**
 * TODO
 * 
 * @author Jared Cope
 */
public class StatesPanel
		extends JPanel implements StateEditor {

	private SlageObject object;

	private StateList statesList = new StateList();
	private JScrollPane stateListScroller;
	private JButton removeStateButton = new JButton("Remove");
	private JButton addStateButton = new JButton("Add");

	private JLabel stateNameLabel = new JLabel("State name");
	private JTextField stateNameField = new JTextField(50);

	private JLabel stateTypeLabel = new JLabel("State type");
	private Object[] stateTypes = new Object[] { "boolean", "integer", "long", "char", "byte", "short", "float", "double", "bigDecimal", "bigInteger", "null", "string", "colour", "font", "point", "rectangle", "boundary" };
	private JComboBox stateTypeCombo = new JComboBox(stateTypes);

	private JLabel stateValueLabel = new JLabel("State value");
	private JTextField stateValueField = new JTextField(50);

	public StatesPanel(SlageObject obj) {
		object = obj;
		setLayout(new BorderLayout());

		FormLayout layout = new FormLayout("pref, 10dlu, right:pref, 3dlu, pref", "p, 3dlu, p:grow, 3dlu, p, 3dlu, p, 3dlu, p");

		PanelBuilder builder = new PanelBuilder(layout);
		CellConstraints cc = new CellConstraints();

		builder.addSeparator("States", cc.xyw(1, 1, 1));
		stateListScroller = new JScrollPane(statesList);
		statesList.addListSelectionListener(new StateListSelectionListener(this));
		builder.add(stateListScroller, cc.xywh(1, 3, 1, 5));
		builder.add(removeStateButton, cc.xy(1, 9));

		builder.addSeparator("Add/Edit states", cc.xyw(3, 1, 3));
		builder.add(stateNameLabel, cc.xy(3, 3));
		builder.add(stateNameField, cc.xy(5, 3));

		builder.add(stateTypeLabel, cc.xy(3, 5));
		builder.add(stateTypeCombo, cc.xy(5, 5));

		builder.add(stateValueLabel, cc.xy(3, 7));
		builder.add(stateValueField, cc.xy(5, 7));

		builder.add(addStateButton, cc.xy(3, 9));

		add(builder.getPanel(), BorderLayout.CENTER);

		populatePanel();
	}

	private void populatePanel() {

		Collection<Attribute<?>> states = object.getAttributeList();
                for(Attribute s : states){
                    statesList.addStateToList(s);
		}
	}

	public void displayState(Attribute state) {
		stateNameField.setText(state.getName());
		stateValueField.setText(state.getValue().toString());
		// stateTypeCombo.addItem(state.getClass().toString());
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
