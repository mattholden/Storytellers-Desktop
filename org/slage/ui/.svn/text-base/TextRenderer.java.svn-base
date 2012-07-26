/*
 * TextRenderer.java
 *
 * Created on August 26, 2005, 2:21 PM
 */

package org.slage.ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import org.slage.framework.TextFontColor;

/**
 * 
 * A class for storing text with a font and color. Used in the Console Panel and
 * provides a base class for TextDrawable.
 * 
 * @author <a href="mailto:Matt@SQ7.org">Matt Holden</a>
 */
public class TextRenderer implements ListCellRenderer, TextFontColor {

	/** Font to draw in */
	private java.awt.Font font;

	/** Color to draw in */
	private java.awt.Color color;

	/** Text to write */
	private String strText;

	/**
	 * Accessor for font
	 * 
	 * @return font
	 */
	public Font getFont() {
		return font;
	}

	/**
	 * Accessor for color
	 * 
	 * @return color
	 */
	public Color getColor() {
		return color;
	}

	/**
	 * Accessor for text
	 * 
	 * @return text
	 */
	public String getText() {
		return strText;
	}

	/**
	 * Mutator for font
	 * 
	 * @param f new font
	 */
	public void setFont(Font f) {
		font = f;
	}

	/**
	 * Mutator for color
	 * 
	 * @param c new color
	 */
	public void setColor(Color c) {
		color = c;
	}

	/**
	 * Mutator for text
	 * 
	 * @param strWords new text
	 */
	public void setText(String strWords) {
		strText = strWords;
	}

	/**
	 * Creates a new instance of TextRenderer
	 * 
	 * @param strTxt Text to draw
	 * @param aFont Font to draw in
	 * @param aColor color to draw in
	 */
	public TextRenderer(String strTxt, Font aFont, Color aColor) {
		strText = strTxt;
		this.font = aFont;
		this.color = aColor;
	}

	/**
	 * Get a simple string representation
	 * 
	 * @return strText
	 */
	public String toString() {
		return strText;
	}

	/** JLabel to use for cell rendering */
	private static JLabel label = new JLabel();

	/**
	 * Provide the component to render the cell
	 * 
	 * @param list List to work with
	 * @param value value to display (a TextFontColor)
	 * @param index Integer index in the list
	 * @param isSelected 'true' if the line is selected
	 * @param cellHasFocus 'true' if the cell is focused
	 * @return the component to render
	 */
	public Component getListCellRendererComponent(JList list, Object value, // value
			// to
			// display
			int index, // cell index
			boolean isSelected, // is the cell selected
			boolean cellHasFocus) // the list and the cell have the focus
	{
		label.setOpaque(true);
		if (value instanceof TextFontColor == false) {
			value = new TextRenderer(value.toString(), list.getFont(), list.getForeground());
		}
		TextFontColor cText = (TextFontColor) value;
		if (cText.getColor() == null)
			cText.setColor(list.getForeground());
		if (cText.getFont() == null)
			cText.setFont(list.getFont());

		label.setFont(cText.getFont());
		// pad the rendering so it doesn't appear right on the edge
		label.setText(" " + cText.getText());

		if (isSelected) {
			list.setSelectionForeground(list.getBackground());
			list.setSelectionBackground(cText.getColor());
			label.setBackground(cText.getColor());
			label.setForeground(list.getBackground());
		} else {
			label.setBackground(list.getBackground());
			label.setForeground(cText.getColor());
		}
		return label;

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
