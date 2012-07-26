/*
 * Created on Nov 13, 2005
 *
 */
package org.slage.resource;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.channels.ReadableByteChannel;

public abstract class AbstractResourceManager implements ResourceManager{

	protected static byte[] loadStreamIntoMemory(InputStream aStream, int aLength) throws IOException {
		byte[] trash = new byte[aLength];
		aStream.read(trash);
		return trash;
	}

	protected static byte[] loadFileIntoMemory(File aFile) throws ResourceException {
		if (aFile.length() > Integer.MAX_VALUE) {
			throw new UnsupportedOperationException("Can't load a file bigger than MAX_INT into memory. Use a FileChannel or FileInputStream instead.");
		}
		if (!aFile.exists()) {
			throw new ResourceException("Could find file: " + aFile.getName());
		}
		try {
			FileInputStream is = new FileInputStream(aFile);
			return loadChannelIntoMemory(is.getChannel(), (int) aFile.length());
		} catch (IOException e) {
			throw new ResourceException("Couldn't load file: " + aFile.getName(), e);
		}
	}

	protected static byte[] loadResourceIntoMemory(String aName) throws ResourceException {
		try {
                        InputStream in = ClassLoader.getSystemResourceAsStream(aName);
			if (in == null)
				throw new ResourceException("The resource " + aName + " could not be found.");
			return loadStreamIntoMemory(in, in.available());
		} catch (IOException e) {
			throw new ResourceException(e);
		}
	}

	protected static byte[] loadChannelIntoMemory(ReadableByteChannel aChannel, int aLength) throws ResourceException {
		ByteBuffer buf = ByteBuffer.allocateDirect(aLength);
		int numRead = 0;
		buf.rewind();
		try {
			numRead = aChannel.read(buf);
		} catch (IOException e) {
			throw new ResourceException(e);
		}
		if (numRead != aLength) {
			throw new ResourceException("Could only load " + numRead + " bytes of the requested " + aLength);
		}
		buf.rewind();
		byte[] bytes = new byte[buf.capacity()];
		buf.get(bytes, 0, bytes.length);
		return bytes;
	}
}
