/*
 * SoundManager.java
 *
 * Created on August 25, 2005, 2:01 PM
 */

package org.slage.audio;

import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import org.slage.resource.ResourceContainer;
import org.slage.resource.ResourceException;
import org.slage.framework.Tools;

/**
 * Manages all the sound files we have loaded and playing.
 * 
 * @author <a href="mailto:Matt@SQ7.org">Matt Holden</a>
 */
public class SoundManager implements ResourceContainer
{

	/** Logger instance */
	protected static transient final org.apache.commons.logging.Log LOG = org.apache.commons.logging.LogFactory.getLog(SoundManager.class);

	/** List of Ogg files we know about */
	private Map<String, SoundFile> mapSounds  = new HashMap<String, SoundFile>();

	/**
	 * A pool of threads to run sound files from
	 */
	private transient static final ThreadPoolExecutor pool;

	static {
		/**
		 * A Queue for things which are awaiting execution to queue through. It
		 * should never get used, because we should always have enough threads to
		 * service sounds immideately.
		 */
		BlockingQueue<Runnable> queue = new ArrayBlockingQueue<Runnable>(32);
		/**
		 * The Thread pool construction: 12 threads initially, 12 threads minimum
		 * 256 maximum threads 60 * 2 before idle threads are allowed to die That's
		 * in seconds, so 2 minutes And the Queue for them to move through.
		 */
		pool = new ThreadPoolExecutor(12, 256, (60 * 2), TimeUnit.SECONDS, queue);
		/**
		 * A rejected execution handler that let's the user know we're blowing up,
		 * but still starts and runs a new thread regardless We may wish to throw a
		 * runtime execption at this point and just blow up entirely because
		 * something is wrong.
		 */
		pool.setRejectedExecutionHandler(new RejectedExecutionHandler() {
			public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
				LOG.fatal(new Exception("-=CRITICAL=- Sound Manager Thread Pool ran out of threads! Someone is not cleaning up their threads."));
				new Thread(r).start();
			}
		});
	}

	/** Creates a new instance of SoundManager */
	public SoundManager() {
		// Intentionally left blank
	}

	/**
	 * Play an Ogg file. Will load it if necessary.
	 * 
	 * @param strFile file to load
	 * @return a reference to the loaded Ogg, in case you want to stop it or
	 *         manipulate it somehow.
	 * 
	 * 
	 */
	public SoundFile play(String strFile) {
		// See if it's loaded
		if (strFile == null)
			return null;
		SoundFile file = null;
		file = load(strFile, false);
		if (file != null) {
			pool.execute(file);
		}
		return file;
	}

	/**
	 * Play an Ogg file. Will load it if necessary.
	 * 
	 * @param strFile file to load
	 * @param bLoop 'true' if the sound should loop
	 * @return a reference to the loaded Ogg, in case you want to stop it or
	 *         manipulate it somehow.
	 */
	public SoundFile play(String strFile, boolean bLoop) {
		SoundFile file = play(strFile);
		if (file != null)
			file.setLooping(bLoop);

		return file;
	}

	/**
	 * Load an Ogg file that isn't loaded
	 * 
	 * @param strFile file to load
	 * @param bLoop 'true' if the sound should loop
	 * @return a reference to the loaded Ogg, in case you want to stop it or
	 *         manipulate it somehow. If the Ogg was already loaded, returns the
	 *         reference to its original object.
	 */
	public SoundFile load(String strFile, boolean bLoop) {
		if (strFile == null)
			return null;

		// make sure it's not loaded
                SoundFile file = mapSounds.get(strFile);                
                if (file != null) return file;
                				
		if (strFile.indexOf('?') != -1)
			strFile = new String(Tools.GetQMarkDelimitedPath(strFile));

		if (strFile.toLowerCase().endsWith("ogg"))
			file = new OggFile(strFile);

		// TODO: Support other file formats
		else
			return null;

		file.setLooping(bLoop);
		mapSounds.put(strFile, file);
		return file;
	}

	/** Shut down and destroy all oggs */
	public void shutdown() {
		mapSounds.clear();
	}

	/**
	 * Get count of sounds in the system
	 * 
	 * @return listSounds.size()
	 */
	public int getSoundCount() {
		return mapSounds.size();
	}

	/**
	 * Unload a sound
	 * 
	 * @param sound Sound to remove
	 */
	public void unload(SoundFile sound) {
		mapSounds.remove(sound);
	}

	/**
	 * Unload a sound
	 * 
	 * @param strSound Sound to remove
	 */
	public void unload(String strSound) {
		mapSounds.remove(strSound);
	}

	

	/**
	 * Get the Sound file with the given name
	 * 
	 * @param strFilename filename to find
	 * @return the SoundFile or null if it's not here
	 */
	public SoundFile getSound(String strFilename) {
		return mapSounds.get(strFilename);
	}

        /** Load sound files */
        public void loadResources() throws ResourceException
        {
            	Set<Map.Entry<String, SoundFile>> set = mapSounds.entrySet();
		for (Map.Entry<String, SoundFile> m1 : set) {                
			m1.getValue().loadResources();
                }	
        }
        
        /** Unload sound files */
        public void unloadResources() throws ResourceException
        {
            	Set<Map.Entry<String, SoundFile>> set = mapSounds.entrySet();
		for (Map.Entry<String, SoundFile> m1 : set) {                
			m1.getValue().unloadResources();
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
