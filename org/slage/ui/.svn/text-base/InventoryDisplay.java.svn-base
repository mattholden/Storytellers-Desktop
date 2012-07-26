/*
 * InventoryDisplay.java
 *
 * Created on October 23, 2005, 9:34 PM
 */

package org.slage.ui;

import java.awt.Rectangle;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.slage.SlageObject;
import org.slage.framework.LinkedObject;
import org.slage.SlageImage;

/**
 * Encapsulates all of the inventory displaying functionality a SlageObject will
 * need to communicate with the InventoryPanel and InventoryCanvas.
 * 
 * @author Jaeden
 */
public class InventoryDisplay implements LinkedObject<SlageObject> {

    /** logger instance */
    protected static final Log LOG = LogFactory.getLog(ToolBarButton.class);

    /** If 'true', this is the default ID. Don't save it */
    private boolean bDefault = true;

    /** Owner SlageObject */
    private SlageObject owner;

    /** Inner bounds of the display */
    private Rectangle rectInner = new Rectangle(0, 0, 270, 270);

    /** Outer bounds of the display */
    private Rectangle rectOuter = new Rectangle(0, 0, 270, 270);

    /** Background image of the display */
    private SlageImage imgBackground = null;

    /** The direction in which the panel should scroll */
    private boolean bScrollDirection = VERTICAL;

    /** Define a horizontal scrolling pattern */
    public static final boolean HORIZONTAL = true;

    /** Define a vertical scrolling pattern */
    public static final boolean VERTICAL = false;

    /** Size of the inventory images (Square) */
    private int iObjectSize = 48;

    /** Amount to pad between items */
    private int iPadding = 5;

    /*
     * Set the inner bounds of the panel. Whereas the outer bounds define the
     * size of the panel (for background imaging, etc.), the inner bounds define
     * the area of the panel in which it is legal for us to render inventory
     * items. For best appearance, the overall dimensions of the inner bounds
     * should be multiples of the size of an inventory image.
     * 
     * Also note that the x,y position of the rectangle passed in refers to the
     * -relative- position of the rectangle inside the panel. For example, if
     * the outer panel's location is at (200, 150), then to place the upper left
     * hand corner of the inner bounds at (220, 175), the x, y values of the
     * inner bound rectangle should be (20, 25).
     * 
     * @param rBounds the bounding rectangle for the inner bounds.
     */
    public void setInnerBounds(Rectangle rBounds) {
        rectInner = rBounds;
        bDefault = false;
    }

    /**
     * Accessor for the inner bounds of the panel
     * 
     * @return rectInnerBounds
     */
    public Rectangle getInnerBounds() {
        return rectInner;
    }

    /*
     * Set the outer bounds of the panel. This will represent the bounds of the
     * Swing panel itself.
     * 
     * @param rBounds the bounding rectangle for the outer bounds.
     */
    public void setOuterBounds(Rectangle rBounds) {
        rectOuter = rBounds;
        bDefault = false;
    }

    /**
     * Accessor for the outer bounds of the panel
     * 
     * @return rectOuter
     */
    public Rectangle getOuterBounds() {
        return rectOuter;
    }

    /**
     * Getter for the SlageObject whose contents we are displaying.
     * 
     * @return Value of property owner.
     */
    public org.slage.SlageObject getOwner() {
        return owner;
    }

    /**
     * Setter for the SlageObject whose contents we are displaying
     * 
     * @param ownerObject
     *            New value of property owner.
     */
    public void setOwner(SlageObject ownerObject) {
        this.owner = ownerObject;
    }

    /**
     * Set the direction in which the panel should scroll
     * 
     * @return Direction to scroll - values of constants HORIZONTAL or VERTICAL
     */
    public boolean getScrollDirection() {
        return bScrollDirection;
    }

    /**
     * Set the direction in which the panel should scroll
     * 
     * @param bDirection
     *            Direction to scroll - use constants HORIZONTAL or VERTICAL
     */
    public void setScrollDirection(boolean bDirection) {
        this.bScrollDirection = bDirection;
        bDefault = false;
    }

    /**
     * Getter for background image
     * 
     * @return Value of property imgBackground.
     */
    public SlageImage getBackground() {
        return imgBackground;
    }

    /**
     * Setter for background image
     * 
     * @param imgBackgroundImage
     *            New value of property imgBackground.
     */
    public void setBackground(SlageImage imgBackgroundImage) {
        this.imgBackground = imgBackgroundImage;

        this.imgBackground.scale(rectOuter.width, rectOuter.height);
        bDefault = false;
    }

    /**
     * Getter for the object size (Square) of the objects
     * 
     * @return Value of property iObjectSize.
     */
    public int getObjectSize() {
        return iObjectSize;
    }

    /**
     * Setter for the object size (Square) of the objects
     * 
     * @param iSize
     *            New value of property iObjectSize.
     */
    public void setObjectSize(int iSize) {
        this.iObjectSize = iSize;
        bDefault = false;
    }

    /**
     * Getter for object padding.
     * 
     * @return Value of property iPadding.
     */
    public int getPadding() {
        return iPadding;
    }

    /**
     * Setter for object padding.
     * 
     * @param iNewPadding
     *            New value of property iPadding.
     */
    public void setPadding(int iNewPadding) {
        this.iPadding = iNewPadding;
        bDefault = false;
    }

    /**
     * Check if this is the default
     * 
     * @return 'true' if it is
     */
    public boolean isDefault() {
        return bDefault;
    }

    /**
     * Creates a new instance of InventoryDisplay
     * 
     * @param obj
     *            SlageObject object to link to
     */
    public InventoryDisplay(SlageObject obj) {
        owner = obj;
    }

}