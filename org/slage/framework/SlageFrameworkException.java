/*
 * SlageFrameworkException.java
 *
 * Created on July 25, 2005, 4:47 PM
 */

package org.slage.framework;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Priority;

/**
 * Simple jumping-off point for exceptions fired by Slage. Barring any
 * additional functionality added, it's also handy just to have a common base
 * class to separate Slage exceptions from standard Java exceptions.
 * 
 * @author <a href="mailto:Matt@SQ7.org">Matt Holden</a>
 */

public abstract class SlageFrameworkException extends RuntimeException {

	private static final Log LOG = LogFactory.getLog(SlageFrameworkException.class);

	/** Construct a SlageException */
	protected SlageFrameworkException() { /** deliberately blank */ }		

	/**
	 * Construct a SlageException
	 * 
	 * @param strMsg the exception message
	 */
	public SlageFrameworkException(String strMsg) {
		super(strMsg);
	}

	/**
	 * Accessor for the log4j priority.
	 * 
	 * @return Value of property iPriority.
	 */
	public int getPriority() {
		return iPriority;
	}

	/**
	 * Setter for log4j priority.
	 * 
	 * @param aPriority New value of property iPriority.
	 */
	public void setPriority(int aPriority) {
		this.iPriority = aPriority;
	}

	/** The priority for Log4j */
	private int iPriority = Priority.ERROR_INT;

	/**
	 * Log an exception which might not be a SlageException
	 * 
	 * @param e Exception
	 */
	public static void log(Exception e) {
		if (e instanceof SlageFrameworkException)
			((SlageFrameworkException) e).log();
		else
			LOG.fatal("Logging Java exception", e);

	}

	/** Log this exception */
	public void log() {
		switch (iPriority) {
		case Priority.DEBUG_INT:
			LOG.debug("Logging Exception", this);
			break;
		case Priority.INFO_INT:
			LOG.info("Logging Exception", this);
			break;
		case Priority.WARN_INT:
			LOG.warn("Logging Exception", this);
			break;
		case Priority.ERROR_INT:
			LOG.error("Logging Exception", this);
			break;
		case Priority.FATAL_INT:
			LOG.fatal("Logging Exception", this);
			break;
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
