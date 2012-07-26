/*
 * ResourceContainer.java
 *
 * Created on December 8, 2005, 2:48 PM
 */

package org.slage.resource;

/**
 * Defines an interface for objects which contain instances of classes which implement <code>Resource</code>. This interface exposes
 * two methods, <code>loadResources()</code> and <code>unloadResources()</code>, identical to the <code>Resource</code> interface itself.
   Because the behavior in resource containers is fundamentally different from that of Resources, and the metaphor is such that a Resource is
 * "a data file loaded from disk", it is prudent to split the interfaces for readability and to preserve the metaphor. 
 * 
 * Like <code>Resource</code>, <code>ResourceContainers</code> are inherently <code>Serializable</code>.
 *
 * @see org.slage.resource.Resource;
 * @see java.io.Serializable
 *
 * @author  Jaeden
 */
public interface ResourceContainer extends java.io.Serializable
{
    
   	/**
	 * Called when this object's Resources should be loaded. This method should call loadResources() on all <code>Resource</code> instances
         * it contains, as well as the <code>loadResources()</code> on every contained object which implements <code>ResourceContainer</code>.
         *Additionally, it should take care of any initialization code which relies on the presence of resource data which won't be available until the 
         *Resource objects themselves are loaded from disk.
         *
	 * @throws ResourceException
	 *           if something goes wrong with loading the Resources contained within the container
	 */
	public void loadResources() throws ResourceException;

	/**
	 * Called when this object's Resources should be made available for garbage collection. This method should call unloadResources() 
            on all <code>Resource</code> instances it contains, as well as the <code>unloadResources()</code> on every contained
            object which implements <code>ResourceContainer</code>. Additionally, it should take care of any shutdown code which relies
            on the prior release of resource data from disk.
	 */
	public void unloadResources() throws ResourceException;
    
}
