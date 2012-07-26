/*
 * TextObject.java
 *
 * Created on August 11, 2005, 2:32 PM
 */

package org.slage;

import java.awt.Color;
import java.awt.Font;

import org.slage.framework.Point3D;
import org.slage.framework.TextFontColor;

/**
 * Defines a SlageObject subclass for rendered text. This merges the
 * functionality of the more basic TextDrawable with the ability for text to
 * have a click area, collision boundary, handlers, and positioning
 * functionality.
 * 
 * This class will also be used in the construction of DialogueObjects.
 * 
 * @author <a href="mailto:Matt@SQ7.org">Matt Holden</a>
 */
public class TextObject
		extends SlageObject implements TextFontColor {

	/** Logger instance */
	protected static final org.apache.commons.logging.Log LOG = org.apache.commons.logging.LogFactory.getLog(TextObject.class);

	/** Font to draw in */
	private java.awt.Font font = new Font("Arial", 1, 10);

	/** Color to draw in */
	private java.awt.Color color = Color.WHITE;

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
	 * Accessor for text (the same as the name here; method provided for
	 * readability and symmetry with ColoredText )
	 * 
	 * @return text
	 */
	public String getText() {
		return getName();
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
	 * Mutator for text (the same as the name - this function is here for
	 * readability and symmetry with ColoredText
	 * 
	 * @param strWords new text
	 */
	public void setText(String strWords) {
		setName(strWords);
	}

	/**
	 * Draw text at the object's position
	 * 
	 * @param G2D graphics context to render the text to
	 */
	public void draw(java.awt.Graphics2D G2D) {

		Font oldFont = G2D.getFont();
		Color oldColor = G2D.getColor();

		G2D.setFont(font);
		G2D.setColor(color);

		G2D.drawString(getName(), getPosition().x, getPosition().y);

		G2D.setFont(oldFont);
		G2D.setColor(oldColor);
	}

	/**
	 * Creates a new TextObject
	 * 
	 * @param strTxt Text to draw
	 * @param aFont Font to draw in
	 * @param aColor color to draw in
	 */
	public TextObject(String strTxt, Font aFont, Color aColor) {
		super(strTxt);
		this.font = aFont;
		this.color = aColor;
	}

	/**
	 * Creates a new TextObject
	 * 
	 * @param strTxt Text to draw
	 * @param aFont Font to draw in
	 * @param aColor color to draw in
	 * @param ptPos position to draw
	 */
	public TextObject(String strTxt, Font aFont, Color aColor, Point3D ptPos) {
		super(strTxt);
		this.font = aFont;
		this.color = aColor;
		setPosition(ptPos);
	}
        
             
        /** Accept a visitor for double dispatch 
        @param aVisitor a visitor to check the types */        
	public void accept(SlageObjectVisitor aVisitor) {
		aVisitor.accept(this);
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
