/*
 * SlageImage.java
 *
 * Created on September 16, 2005, 9:14 AM
 */

package org.slage;
import org.slage.resource.Resource;
import java.awt.Image;
import javax.swing.ImageIcon;
import org.slage.framework.Tools;
import org.slage.resource.ResourceManager;
import org.slage.resource.SoftCachingResourceManager;

/**
 * Extend javax.swing.ImageIcon for useful features like being able to retrieve
 * the file name of the image, scale the image, et. al.
 * 
 * @author <a href="mailto:Matt@SQ7.org">Matt Holden</a>
 */
public class SlageImage
		 implements Resource
{
    
    /** Resource manager for SlageImages */
    private static ResourceManager resources = new SoftCachingResourceManager();
              

        /** Logger instance */
	protected static final org.apache.commons.logging.Log LOG =     
            org.apache.commons.logging.LogFactory.getLog(SlageImage.class);

	/** Static value for how to scale images */
	private static int iScaleHint = Image.SCALE_FAST;

	/**
	 * Accessor for scaling hint
	 * 
	 * @return iScaleHint
	 */
	public static int getScaleHint() {
		return iScaleHint;
	}

	/**
	 * Mutator for scaling hint
	 * 
	 * @param iScale new scaling hint (use constants in java.awt.Image)
	 */
	public static void setScaleHint(int iScale) {
		iScaleHint = iScale;
	}

	/** File name of the image (as a ?-delimited string) */
	private String strFile;

        /** The actual image */
        private transient javax.swing.ImageIcon image;
        
	/** The X scale of the image */
	private int x = 0;
	/** The Y scale of the image */
	private int y = 0;

	/** The original size of the image */
	private int xOrig = 0;
	/** The original size of the image */
	private int yOrig = 0;

	
	/**
	 * Construct a new SlageImage
	 * 
	 * @param strImg image file to load
	 */
	public SlageImage(String strImg) {
		
                strFile = Tools.GetRelativeQMarkDelimitedPath(strImg);
                try
                {
                    loadResources();
                } catch (Exception e) { LOG.error(e); e.printStackTrace(); }
                
		// save original dimensions
		xOrig = image.getIconWidth();
		yOrig = image.getIconHeight();
		
		strFile = strImg;
	}

	/**
	 * Construct a new SlageImage
	 * 
	 * @param strImg image file to load
	 * @param anX x scale
	 * @param aY y scale
	 */
	public SlageImage(String strImg, int anX, int aY) {
		strFile = Tools.GetRelativeQMarkDelimitedPath(strImg);
        
                try
                {
                    loadResources();
                } catch (Exception e) { LOG.error(e); }
                
		// save original dimensions
		xOrig = image.getIconWidth();
		yOrig = image.getIconHeight();

		this.x = anX;
		this.y = aY;

		image.setDescription(strImg);
		
		if (anX != image.getIconWidth() || aY != image.getIconHeight())
			image.setImage(image.getImage().getScaledInstance(anX, aY, iScaleHint));

	}

	/**
	 * Get the "boundaries" of the image: 0, 0, width, height
	 * 
	 * @return a rectangle containing the image "boundaries"
	 */
	public java.awt.Rectangle getBounds() {
		return new java.awt.Rectangle(0, 0, image.getIconWidth(), image.getIconHeight());
	}

	/**
	 * Get the file path (?-delimited)
	 * 
	 * @return QMark-delimited file path
	 */
	public String getQMarkPath() {
		return strFile;
	}

	/**
	 * Get the file path (real, non-cross-platform path)
	 * 
	 * @return absolute, actual file path
	 */
	public String getAbsolutePath() {
		return Tools.GetQMarkDelimitedPath(strFile);
	}

	/**
	 * Get the current scale of the image.
	 * 
	 * @return a Point containing the current x, y of the image
	 */
	public java.awt.Point getScale() {
		if (x == 0 || y == 0) {
			return new java.awt.Point(image.getIconWidth(), image.getIconHeight());
		}
		return new java.awt.Point(x, y);
	}

	/**
	 * Get the original size of the image.
	 * 
	 * @return a Point containing the original x, y of the image
	 */
	public java.awt.Point getOriginalSize() {
		if (xOrig == 0 || yOrig == 0) {
			return new java.awt.Point(image.getIconWidth(), image.getIconHeight());
		}
		return new java.awt.Point(xOrig, yOrig);
	}

	/**
	 * Scale the image
	 * 
	 * @param anX new X size
	 * @param aY new Y size
	 */
	public void scale(int anX, int aY) {
		this.x = anX;
		this.y = aY;

		// Scale back to its original size
		Image i;
		if (anX != 0 || aY != 0)
			i = image.getImage().getScaledInstance(xOrig, yOrig, iScaleHint);
		else
			i = image.getImage();

		// scale that to new size
		image.setImage(i.getScaledInstance(anX, aY, iScaleHint));
	}

	// Scale the image back to its original size */
	public void unscale() {
		image.setImage(image.getImage().getScaledInstance(xOrig, yOrig, iScaleHint));
	}

        /** Actually load the image */
        public void loadResources() throws org.slage.resource.ResourceException 
        {            
            image = new ImageIcon(resources.getResource(strFile), strFile);
        }
        
        /** Release the image */
        public void unloadResources() throws org.slage.resource.ResourceException 
        {
            image.setImage(null);
            image = null;
        }
        
        /**
         * Getter for property image.
         * @return Value of property image.
         */
        public ImageIcon getImageIcon() {
            return image;
        }
        
        /**
         * Setter for property image.
         * @param newImage New value of property image.
         */
        public void setImageIcon(ImageIcon newImage) {
            image = newImage;
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
