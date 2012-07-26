/*
 * SpriteSheetFrame.java
 *
 * Created on August 10, 2005, 10:58 PM
 */

package org.slage.animation;

import org.slage.framework.Point3D;
import org.slage.SlageImage;

/**
 * A frame of animation that uses single sprites (one per frame)
 * 
 * @author <a href="mailto:Matt@SQ7.org">Matt Holden</a>
 */
public class SingleSpriteFrame
		extends AnimationFrame {

	/**
	 * Construct a frame
	 * 
	 * @param img Sprite image to render all or part of
	 * @param rectSprite area of the image to render
	 * @param anim Parent animation
	 */
	public SingleSpriteFrame(SlageImage img, java.awt.Rectangle rectSprite, SingleSpriteAnimation anim) {
		super(rectSprite, anim);
		imgSprite = img;
	}

	/**
	 * Construct a frame
	 * 
	 * @param img Sprite image to render all or part of
	 * @param anim Parent animation
	 */
	public SingleSpriteFrame(SlageImage img, SingleSpriteAnimation anim) {
		super(null, anim);
		if (img != null)
			setSpriteArea(img.getBounds());
		imgSprite = img;
	}
	/** Graphic from which our sprite is pulled */
	protected SlageImage imgSprite;

	/**
	 * Draw a frame
	 * 
	 * @param G2D Graphics2D object to draw to
	 * @param P3D position to draw at
	 */
	public void draw(java.awt.Graphics2D G2D, Point3D P3D) {
		if (getSpriteArea() == null)
			setSpriteArea(imgSprite.getBounds());
		G2D.drawImage(imgSprite.getImageIcon().getImage(), // image
				P3D.x, P3D.y, // dest rectangle upper left
				P3D.x + imgSprite.getImageIcon().getIconWidth(), // dest lower right x
				P3D.y + imgSprite.getImageIcon().getIconHeight(), // dest lower right y
				getSpriteArea().x, getSpriteArea().y, // source rectangle upper left
				getSpriteArea().x + getSpriteArea().width, // dest lower right x
				getSpriteArea().y + getSpriteArea().height, // dest lower right y
				null); // image observer
	}


	/**
	 * Getter for property imgSprite.
	 * 
	 * @return Value of property imgSprite.
	 */
	public SlageImage getSprite() {
		return imgSprite;
	}

	/**
	 * Setter for property imgSprite.
	 * 
	 * @param aSprite New value of property imgSprite.
	 */
	public void setSprite(SlageImage aSprite) {
		this.imgSprite = aSprite;
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
