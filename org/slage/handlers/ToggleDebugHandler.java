/*
 * ToggleDebugHandler.java
 *
 * Created on August 26, 2005, 4:59 PM
 */

package org.slage.handlers;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.slage.Slage;
import org.slage.SlageGame;
import org.slage.framework.NotFoundException;

/**
 * Toggles debug mode. To manually set it true or false, use SetStateHandler.
 * 
 * @author <a href="mailto:Matt@SQ7.org">Matt Holden</a>
 */
public class ToggleDebugHandler extends Handler {

	private static final Log LOG = LogFactory.getLog(ToggleDebugHandler.class);

	/**
	 * Creates a new instance of ToggleDebugHandler
	 * 
	 * @param game Game to run it on
	 */
	public ToggleDebugHandler(org.slage.SlageGame game) {
		super(game);
	}

	/** Creates a new instance of the handler for the current game */
	public ToggleDebugHandler() {
		super(Slage.getCurrentGame());
	}

	/** Set Debug Mode to whatever it isn't. */
	protected void fire() {
		if (getTarget() instanceof SlageGame == false)
			setTarget(Slage.getCurrentGame());

		try {
			if (getTarget().getAttributeAsBoolean("Debug Mode") == true)
				getTarget().setAttribute("Debug Mode", new Boolean(false));
			else
				getTarget().setAttribute("Debug Mode", new Boolean(true));
		} catch (NotFoundException e) {
			LOG.warn(e.getMessage(), e);
		}
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