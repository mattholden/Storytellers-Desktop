/*
 * CloseInventoryHandler.java
 *
 * Created on October 24, 2005, 10:16 PM
 */

package org.slage.handlers;

import org.slage.SlageObject;

/**
 * Close the inventory of the target as an inventory dialog
 * 
 * @author Jaeden
 */
public class CloseInventoryHandler
		extends Handler<SlageObject, SlageObject, SlageObject> {

	/**
	 * Creates a new instance of CloseInventoryHandler
	 * 
	 * @param targ Target object
	 */
	public CloseInventoryHandler(SlageObject targ) {
		super(targ);
	}

	/** Fire the handler (Close the dialog) */
	protected void fire() {
		getTarget().destroyInventoryPanel();
	}

}