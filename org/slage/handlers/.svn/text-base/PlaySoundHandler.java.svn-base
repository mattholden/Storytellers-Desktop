/*
 * PlayOggHandler.java
 *
 * Created on September 4, 2005, 8:58 AM
 */

package org.slage.handlers;

import org.slage.Slage;
import org.slage.SlageGame;
import org.slage.SlageObject;
import org.slage.audio.OggFile;

/**
 * A handler that plays a sound file for a particular game object.
 * 
 * @author <a href="mailto:Matt@SQ7.org">Matt Holden</a>
 */
public class PlaySoundHandler
		extends Handler {

	/** The location of the sound file to play when fired */
	private String soundFilePath;

	/**
	 * Creates a new instance of this handler that will play the sound file
	 * located at <i>soundFilePath</i> for the provided <i>gameObject</i>.
	 * 
	 * @param gameObject the the game object that this handler is for
	 * @param aSoundFilePath full path to the sound file that will play
	 */
	public PlaySoundHandler(SlageObject gameObject, String aSoundFilePath) {
		super(gameObject);
		this.soundFilePath = aSoundFilePath;
		this.bIndirectMustMatch = true;

	}

	/**
	 * Creates a new instance of this handler that will play the provided
	 * <b>OggFile</b> format file <i>oggFile</i> for the provided <i>gameObject</i>.
	 * 
	 * @param gameObject the the game object that this handler is for
	 * @param oggFile the ogg file to play when the handler fires
	 */
	public PlaySoundHandler(SlageObject gameObject, OggFile oggFile) {
		super(gameObject);
		this.soundFilePath = oggFile.getFileName();
		this.bIndirectMustMatch = true;

	}

	/**
	 * When fired, this handler will play the sound registered in the constructor.
	 */
	protected void fire() {

		if (getTarget() instanceof SlageGame == false) {
			setTarget(Slage.getCurrentGame());
		}
		if (soundFilePath == null)
			return;

		((SlageGame) getTarget()).playSound(soundFilePath);
	}

	/**
	 * Returns the file system location of the sound file registered for this
	 * handler.
	 * 
	 * @return the location of the sound file registered for this handler
	 */
	public String getSoundFileLocation() {
		return soundFilePath;
	}

	/**
	 * Sets the location of the sound file that this handler will play to
	 * <i>soundLocation</i>.
	 * 
	 * @param soundLocation the location of the sound file this handler plays
	 */
	public void setSoundFileLocation(String soundLocation) {
		this.soundFilePath = soundLocation;
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
