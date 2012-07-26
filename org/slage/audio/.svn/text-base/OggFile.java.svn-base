/*
 * SoundFile.java
 *
 * Created on August 29, 2005, 2:23 PM
 */

package org.slage.audio;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;

/**
 * Encapsulates an Ogg Vorbis audio file.
 * 
 * @author <a href="mailto:Matt@SQ7.org">Matt Holden</a>
 */
public class OggFile
		extends SoundFile {
	/**
	 * Creates a new instance of OggFile
	 * 
	 * @param strFile filename to decode
	 */
	public OggFile(String strFile) {
		super(strFile);
	}

	

	/**
	 * Stop playing.
	 * 
	 * @throws IOException if the buffer cannot be read properly.
	 */
	public void stop() throws Exception {
		if (isPlaying() == false)
			return;

		bIsPlaying = false;
	}

	/**
	 * Retrieves a line to play
	 * 
	 * @param audioFormat format to retrieve in
	 * @throws LineUnavailableException when the line is unavailable
	 * @return the line to play
	 */
	private static SourceDataLine getLine(AudioFormat audioFormat) throws LineUnavailableException {
		SourceDataLine res = null;
		DataLine.Info info = new DataLine.Info(SourceDataLine.class, audioFormat);
		res = (SourceDataLine) AudioSystem.getLine(info);
		res.open(audioFormat);
		return res;
	}
        
        /** AudioInputStream to play */
        private transient AudioInputStream in;

	/** Play a sound straight through (not through update loop) */
	public void play() {
		if (getFileName() == null)
			return;
		try {
			bIsPlaying = true;

			// Get AudioInputStream from given file.
			loadResources();
                        
			AudioInputStream din = null;
			if (in != null) {
				AudioFormat baseFormat = in.getFormat();
				AudioFormat decodedFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, baseFormat.getSampleRate(), 16, baseFormat.getChannels(), baseFormat.getChannels() * 2, baseFormat.getSampleRate(), false);
				// Get AudioInputStream that will be decoded by underlying VorbisSPI
				din = AudioSystem.getAudioInputStream(decodedFormat, in);

				// Play now !
				byte[] data = new byte[4096];
				SourceDataLine line = getLine(decodedFormat);

				// we loaded from save at a position
				if (getBytesRead() > 0)
					din.skip(getBytesRead());

				if (line != null) {
					// Start
					line.start();
					int nBytesRead = 0;

					while (nBytesRead != -1) {
						if (isPlaying() == false) {
							nBytesRead = 0; // not saved, just so loop continues
							continue;
						}

						nBytesRead = din.read(data, 0, data.length);
						if (nBytesRead != -1) {
							line.write(data, 0, nBytesRead);
							incBytesRead(nBytesRead);
						}
					}
					// Stop
					line.drain();
					line.stop();
					line.close();
					din.close();
					setBytesRead(0);

				}
				in.close();

				if (isLooping())
					play();
			}
		} catch (Exception e) {
			LOG.error(e);
		}
	}

        /** load resources from a file */
        public void loadResources() throws org.slage.resource.ResourceException 
        {

            try
            {
                /* This code will cache a file into the resource manager and play it.
                 * However, for streaming sounds like .ogg files, this is bad! */
                
                /*
                String aFile = getFileName();
                aFile = Tools.BuildQMarkDelimitedPath(aFile);
                aFile = Tools.GetRelativeQMarkDelimitedPath(aFile);
                File file = new File(aFile);
                in = AudioSystem.getAudioInputStream(Slage.getResourceManager().getResourceAsStream(file));
                */
                
                /** Use streaming until we decide what else to do */           
                File file = new File(getFileName());
                in = AudioSystem.getAudioInputStream(file);
            }
            catch (Exception e)
            {
                throw new org.slage.resource.ResourceException(e);
            }
        }
        
        /** drop refs to the resources */
        public void unloadResources() throws org.slage.resource.ResourceException 
        {
            /** Todo: We don't have a reference to kill, since we're streaming... */
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
