/*
 * Created on Nov 13, 2005
 *
 */
package org.slage.resource;

import java.io.File;
import java.io.InputStream;

public interface ResourceManager {

	public abstract byte[] getResource(String aName) throws ResourceException;

	public abstract byte[] getResource(File aFile) throws ResourceException;

	public abstract InputStream getResourceAsStream(String aName) throws ResourceException;

	public abstract InputStream getResourceAsStream(File aFile) throws ResourceException;

}
