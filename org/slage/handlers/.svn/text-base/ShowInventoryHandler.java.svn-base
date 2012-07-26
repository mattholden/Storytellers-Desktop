/*
 * ShowInventoryHandler.java
 *
 * Created on October 24, 2005, 10:16 PM
 */

package org.slage.handlers;

import org.slage.SlageObject;
import org.slage.ui.InventoryPanel;

/**
 * Show the inventory of the target as an inventory dialog
 * 
 * @author Jaeden
 */
public class ShowInventoryHandler
		extends Handler<SlageObject, SlageObject, SlageObject> {

	/**
	 * Creates a new instance of ShowInventoryHandler
	 * 
	 * @param targ Target object
	 */
	public ShowInventoryHandler(SlageObject targ) {
		super(targ);
	}

	/** Fire the handler (show the dialog) */
	protected void fire() {
		getTarget().setInventoryPanel(new InventoryPanel(getTarget().getInventoryDisplay()));
	}

}
