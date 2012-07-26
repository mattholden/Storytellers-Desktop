/*
 * InventoryPanel.java
 *
 * Created on October 21, 2005, 8:06 AM
 */

package org.slage.ui;

/**
 * Defines the panel in which the player views and selects inventory items.
 * 
 * @author <a href="mailto:Matt@SQ7.org">Matt Holden</a>
 */
public class InventoryPanel
		extends javax.swing.JPanel {

	/** The InventoryDisplay object from which this panel was built */
	private InventoryDisplay display;

	/** Our inventory canvas */
	private InventoryCanvas canvas;

	/**
	 * Creates a new instance of InventoryPanel
	 * 
	 * @param ID the InventoryDisplay object to create from
	 */
	public InventoryPanel(InventoryDisplay ID) {
		display = ID;
		setBounds(display.getOuterBounds());
		setBackground(java.awt.Color.BLACK);
		setLayout(null); // TODO: replace with nicer layout

		canvas = new InventoryCanvas(this);
		canvas.setBounds(display.getOuterBounds());
		add(canvas);

		// add to frame
		org.slage.Slage.getCurrentGame().getFrame().getLayeredPane().add(this, new Integer(3));

		setVisible(true);
	}

	/**
	 * Getter for canvas.
	 * 
	 * @return Value of property canvas.
	 */
	public org.slage.ui.InventoryCanvas getCanvas() {
		return canvas;
	}

	/**
	 * Getter for inventory display settings.
	 * 
	 * @return Value of property display.
	 */
	public org.slage.ui.InventoryDisplay getDisplay() {
		return display;
	}

}
