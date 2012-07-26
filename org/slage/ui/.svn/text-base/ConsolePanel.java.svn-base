/*
 * ConsolePanel.java
 *
 * Created on July 31, 2005, 12:23 AM
 */

package org.slage.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Panel;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.slage.SlageGame;
import org.slage.command.Command;
import org.slage.framework.NotFoundException;
import org.slage.framework.TextFontColor;

/**
 * The panel on which we will render our console window.
 * 
 * @author <a href="mailto:Matt@SQ7.org">Matt Holden</a>
 */
public class ConsolePanel
		extends Panel implements org.slage.command.Commander {
	/** logger instance */
	private static final Log LOG = LogFactory.getLog(ConsolePanel.class);

	/** prefix to ignore when displaying commands */
	private String strInternalPrefix = "__";

	/** Scrollback */
	private JList listScrollback = new JList(new DefaultListModel());

	/** Scroll pane for list */
	private JScrollPane scrollPane;

	/** Input text... */
	private JTextField textCommand = new JTextField();

	/** Height of the console window */
	private static final int CONSOLE_HEIGHT = 85;

	/** Color of console background */
	private Color colorConsole = Color.BLACK;

	/** Color of console foreground */
	private Color colorFont = Color.WHITE;

	/** Font of console */
	private Font font = new Font("Arial", 1, 10);

	/** Game that owns this console */
	private SlageGame game;

	/**
	 * Accessor for owner gane
	 * 
	 * @return game
	 */
	public SlageGame getGame() {
		return game;
	}

	/**
	 * Creates a new instance of ConsolePanel
	 * 
	 * @param owningGame Game that owns this console
	 */
	public ConsolePanel(SlageGame owningGame) {
		this.game = owningGame;

		updateUI();

		textCommand.setBorder(null);

		// Pressing enter in the command sends it and posts it to the scrollback
		textCommand.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (getGame().isPaused())
					return;

				Command command = null;
				try {
					command = getGame().getParser().parseToCommand(textCommand.getText());
				} catch (Exception ex) {
					LOG.debug(ex);
				}

				// give focus to frame for keys
				getGame().getFrame().requestFocusInWindow();

				// blank out box
				textCommand.setText("");

				if (command != null)
					command.fire();
			}
		});

		listScrollback.setCellRenderer(new TextRenderer("Cell Renderer", null, null));

		// double clicking on the scrollback re-fires the command
		listScrollback.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				if (getGame().isPaused())
					return;

				if (evt.getClickCount() == 2 && listScrollback.getSelectedValue() instanceof Command) {
					Command comm = (Command) listScrollback.getSelectedValue();
					comm.fire();
				}
			}
		});

		setLayout(null);

		// set up scrolling for list scrollback
		scrollPane = new JScrollPane(listScrollback);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

		scrollPane.setWheelScrollingEnabled(true);

		add(scrollPane, BorderLayout.NORTH);
		add(textCommand, BorderLayout.SOUTH);

		resize();

	}

	/** Scroll history to the end */
	public void scrollHistoryToEnd() {
		listScrollback.ensureIndexIsVisible(listScrollback.getModel().getSize() - 1);
	}

	/**
	 * Add a Command object to the history
	 * 
	 * @param command Command to add
	 */
	public void addToHistory(org.slage.command.Command command) {
		if (command.getOriginalInput().startsWith(strInternalPrefix))
			return;
		((DefaultListModel) listScrollback.getModel()).addElement(command);
		scrollHistoryToEnd();
	}

	/**
	 * Add a string Command to the scrollback
	 * 
	 * @param strCommand command to add
	 * @param aFont Font to write it in
	 * @param color Color to add it in
	 */
	public void addToHistory(String strCommand, Font aFont, Color color) {
		if (strCommand.startsWith(strInternalPrefix))
			return;
		TextRenderer CT = new TextRenderer(strCommand, aFont, color);
		((DefaultListModel) listScrollback.getModel()).addElement(CT);
		scrollHistoryToEnd();
	}

	/**
	 * Add a string Command to the scrollback
	 * 
	 * @param strCommand command to add
	 */
	public void addToHistory(String strCommand) {
		if (strCommand.startsWith(strInternalPrefix))
			return;
		TextRenderer CT = new TextRenderer(strCommand, listScrollback.getFont(), listScrollback.getForeground());
		((DefaultListModel) listScrollback.getModel()).addElement(CT);
		scrollHistoryToEnd();
	}

	/**
	 * Add a TextFontColor to the scrollback
	 * 
	 * @param text TextFontColor object to add
	 */
	public void addToHistory(TextFontColor text) {
		if (text.getText().startsWith(strInternalPrefix))
			return;
		((DefaultListModel) listScrollback.getModel()).addElement(text);
		listScrollback.ensureIndexIsVisible(listScrollback.getModel().getSize() - 1);
		scrollHistoryToEnd();
	}

	/** Resize the console window */
	public void resize() {
		int iW = 0, iH = 0;
		if (game == null || !game.isFullScreen()) {
			iW = game.getFrame().getBounds().width;
			iH = game.getFrame().getBounds().height;
		} else {
			iW = Toolkit.getDefaultToolkit().getScreenSize().width;
			iH = Toolkit.getDefaultToolkit().getScreenSize().height;
		}

		setBounds(0, iH - CONSOLE_HEIGHT - 30, iW, CONSOLE_HEIGHT);
		textCommand.setBounds(4, CONSOLE_HEIGHT - 27, iW - 6, 25);
		listScrollback.setBounds(0, 0, iW - 10, CONSOLE_HEIGHT - 30);
		scrollPane.setBounds(0, 0, iW - 8, CONSOLE_HEIGHT - 30);
		scrollHistoryToEnd();
	}

	/** Override requestFocus() to pass focus to the console text box. */
	public void requestFocus() {
		textCommand.requestFocusInWindow();
	}

	/** Update UI, reading new values for the SlageGame */
	private void updateUI() {
		// this situation comes up in JUnit testing
		if (game == null)
			return;

                try
                {
                    colorConsole = (Color) game.getAttributeValue("Console Background Color");
                } catch (NotFoundException e) { LOG.warn(e); }
                try
                {
                    colorFont = (Color) game.getAttributeValue("Console Foreground Color");
                } catch (NotFoundException e) { LOG.warn(e); }
                try
                {
                    font = (Font) game.getAttributeValue("Console Font");
                } catch (NotFoundException e) { LOG.warn(e); }
                
                if (colorConsole != null)
                {
                    listScrollback.setBackground(colorConsole);
                    textCommand.setBackground(colorConsole);
                    setBackground(colorConsole);
                }
                if (colorFont != null)
                {
                    listScrollback.setForeground(colorFont);
                    textCommand.setForeground(colorFont);
                    setForeground(colorFont);
                }
                if (font != null)
                {
                    listScrollback.setFont(font);
                    textCommand.setFont(font);               
                    setFont(font);
                }
	}

	/**
	 * Getter for the prefix for hidden "invisible" commands
	 * 
	 * @return Value of property strInternalPrefix.
	 */
	public java.lang.String getInternalPrefix() {
		return strInternalPrefix;
	}

	/**
	 * Setter for the prefix for hidden "invisible" commands
	 * 
	 * @param strPrefix New value of property strInternalPrefix.
	 */
	public void setInternalPrefix(java.lang.String strPrefix) {
		this.strInternalPrefix = strPrefix;
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
