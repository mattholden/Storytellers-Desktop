package org.slage.audio;
import org.slage.resource.Resource;
import org.slage.resource.ResourceManager;
import org.slage.resource.AlwaysLoadingResourceManager;

/**
 * Stores data about a generic sound file for later playing.
 * 
 * @author <a href="mailto:Matt@SQ7.org">Matt Holden</a>
 */
public abstract class SoundFile implements Runnable, Resource {

     /** Resource manager for streaming sound files */
        protected static ResourceManager streamingResources = new AlwaysLoadingResourceManager();
    
	/** Logger instance */
	protected static final org.apache.commons.logging.Log LOG = org.apache.commons.logging.LogFactory.getLog(SoundFile.class);

	/** The filename we loaded */
	private String strFilename;

	/** If playing right now, this is 'true' */
	protected boolean bIsPlaying;

	/** If looping right now, this is 'true' */
	private boolean bIsLooping;

	/** The number of bytes read so far */
	private long lBytesRead = 0;

	/**
	 * Creates a new instance of SoundFile
	 * 
	 * @param strFile filename to decode
	 */
	public SoundFile(String strFile) {
		strFilename = strFile;
	}

	/**
	 * Accessor for filename
	 * 
	 * @return strFilename
	 */
	public String getFileName() {
		return strFilename;
	}

	/**
	 * String representation
	 * 
	 * @return strFilename
	 */
	public String toString() {
		return strFilename;
	}

	/** Plays the sound */
	public abstract void play() throws Exception;

	/** Run as a thread */
	public void run() {
		try {
			play();
		} catch (Exception e) {
			LOG.error(e);
		}
	}

	/** Stop playing. */
	public abstract void stop() throws Exception;

	/**
	 * Getter for property bIsPlaying.
	 * 
	 * @return Value of property bIsPlaying.
	 */
	public boolean isPlaying() {
		return bIsPlaying;
	}

	/**
	 * Getter for property bIsLooping.
	 * 
	 * @return Value of property bIsLooping.
	 */
	public boolean isLooping() {
		return bIsLooping;
	}

	/**
	 * Setter for property bIsLooping.
	 * 
	 * @param aLooping New value of property bIsLooping.
	 */
	public void setLooping(boolean aLooping) {
		this.bIsLooping = aLooping;
	}

	/**
	 * Getter for property lBytesRead.
	 * 
	 * @return Value of property lBytesRead.
	 */
	public long getBytesRead() {
		return lBytesRead;
	}

	/**
	 * Setter for property lBytesRead.
	 * 
	 * @param aBytesRead New value of property lBytesRead.
	 */
	public void setBytesRead(long aBytesRead) {
		this.lBytesRead = aBytesRead;
	}

	/**
	 * Incrementor for property lBytesRead.
	 * 
	 * @param aBytesRead New value of property lBytesRead.
	 */
	public void incBytesRead(long aBytesRead) {
		this.lBytesRead += aBytesRead;
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
