/*
 * Created on Nov 13, 2005
 *
 */
package org.slage.resource;

import java.io.Serializable;

/**
 * A resource defines an interface for allowing something to be notified when it's expected to be loaded and at the
 * ready, and when it's no longer needed.
 * 
 * A resource is explicitly serializable!!!
 * 
 * Any and all references to the data the Resource represents
 * should be made transient! Otherwise the resource will get stored
 * in the saved games (Not Good).
 * 
 * @author Kevlar
 *  
 */
public interface Resource extends Serializable{

	/**
	 * Called when this resource should be loaded.
	 * @throws ResourceException
	 *           if something goes wrong with loading the resource
	 */
	public void loadResources() throws ResourceException;

	/**
	 * Called when this resource should be made available for garbage collection
	 * @throws ResourceException if something goes wrong with unloading the resource
	 */
	public void unloadResources() throws ResourceException;
}