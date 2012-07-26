/*
 * Created on Nov 12, 2005
 *
 */
package org.slage.resource;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class AlwaysLoadingResourceManager extends AbstractResourceManager implements ResourceManager {

	public AlwaysLoadingResourceManager() {
		//
	}

	public byte[] getResource(String aName) throws ResourceException {
		return loadResourceIntoMemory(aName);
	}

	public byte[] getResource(File aFile) throws ResourceException {
		return loadFileIntoMemory(aFile);
	}

	public InputStream getResourceAsStream(String aName) throws ResourceException {
		return ClassLoader.getSystemResourceAsStream(aName);
	}

	public InputStream getResourceAsStream(File aFile) throws ResourceException {
		try {
			return new FileInputStream(aFile);
		} catch (FileNotFoundException e) {
			throw new ResourceException(e);
		}
	}
	

}
