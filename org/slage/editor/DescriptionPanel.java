package org.slage.editor;

import java.awt.BorderLayout;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import org.slage.SlageObject;
import org.slage.editor.actions.AddWordToListAction;
import org.slage.editor.actions.RemoveWordFromListAction;

import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

/**
 * TODO
 * 
 * @author Jared Cope
 */
public class DescriptionPanel
		extends JPanel {

	private SlageObject object;

	private JLabel objectNameLabel = new JLabel("Object name");
	private JTextField objectNameField = new JTextField();

	private JList synonymList = new JList(new DefaultListModel());
	private JScrollPane synonymScroller = new JScrollPane(synonymList);
	private JButton addSynonymButton = new JButton("Add");
	private JButton removeSynonymButton = new JButton("Remove");
	private JTextField synonymField = new JTextField(15);

	private JList adjectiveList = new JList(new DefaultListModel());
	private JScrollPane adjectiveScroller = new JScrollPane(adjectiveList);
	private JButton addAdjectiveButton = new JButton("Add");
	private JButton removeAdjectiveButton = new JButton("Remove");
	private JTextField adjectiveField = new JTextField(15);

	private JList articleList = new JList(new DefaultListModel());
	private JScrollPane articleScroller = new JScrollPane(articleList);
	private JButton addArticleButton = new JButton("Add");
	private JButton removeArticleButton = new JButton("Remove");
	private JTextField articleField = new JTextField(15);

	public DescriptionPanel(SlageObject obj) {

		setLayout(new BorderLayout());
		object = obj;

		FormLayout layout = new FormLayout("pref, 3dlu, pref, 3dlu," + "pref, 3dlu, pref, 3dlu, pref, 3dlu, pref", "p, 3dlu, p, 3dlu, p, 3dlu, p, 3dlu, p");

		PanelBuilder builder = new PanelBuilder(layout);
		CellConstraints cc = new CellConstraints();

		builder.add(objectNameLabel, cc.xy(1, 1));
		builder.add(objectNameField, cc.xyw(3, 1, 3));

		builder.addSeparator("Articles", cc.xyw(1, 3, 3));
		builder.add(articleScroller, cc.xyw(1, 5, 3));
		builder.add(removeArticleButton, cc.xyw(1, 7, 3));
		builder.add(addArticleButton, cc.xy(1, 9));
		builder.add(articleField, cc.xy(3, 9));
		addArticleButton.addActionListener(new AddWordToListAction(articleList, articleField));
		removeArticleButton.addActionListener(new RemoveWordFromListAction(articleList));
		articleField.addActionListener(new AddWordToListAction(articleList, articleField));

		builder.addSeparator("Adjectives", cc.xyw(5, 3, 3));
		builder.add(adjectiveScroller, cc.xyw(5, 5, 3));
		builder.add(removeAdjectiveButton, cc.xyw(5, 7, 3));
		builder.add(addAdjectiveButton, cc.xy(5, 9));
		builder.add(adjectiveField, cc.xy(7, 9));
		addAdjectiveButton.addActionListener(new AddWordToListAction(adjectiveList, adjectiveField));
		removeAdjectiveButton.addActionListener(new RemoveWordFromListAction(adjectiveList));
		adjectiveField.addActionListener(new AddWordToListAction(adjectiveList, adjectiveField));

		builder.addSeparator("Synonyms", cc.xyw(9, 3, 3));
		builder.add(synonymScroller, cc.xyw(9, 5, 3));
		builder.add(removeSynonymButton, cc.xyw(9, 7, 3));
		builder.add(addSynonymButton, cc.xy(9, 9));
		builder.add(synonymField, cc.xy(11, 9));
		addSynonymButton.addActionListener(new AddWordToListAction(synonymList, synonymField));
		removeSynonymButton.addActionListener(new RemoveWordFromListAction(synonymList));
		synonymField.addActionListener(new AddWordToListAction(synonymList, synonymField));

		add(builder.getPanel(), BorderLayout.CENTER);

		populatePanel();
	}

	private void populatePanel() {
		objectNameField.setText(object.getName());

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
