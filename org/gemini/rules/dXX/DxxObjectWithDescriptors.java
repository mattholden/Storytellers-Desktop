/*
 * DxxObjectWithDescriptors.java
 *
 * Created on December 5, 2005, 12:57 PM
 */

package org.gemini.rules.dXX;

/**
 * Interface to define objects which have DxxDescriptors.
 * 
 * @see org.gemini.rules.dXX.DxxDescriptor
 * @author  Jaeden
 */
public interface DxxObjectWithDescriptors {
    
        
    /** Add a Descriptor
     *  @param _descriptor descriptor to add */
    public void addDescriptor(DxxDescriptor _descriptor);
    /** Remove a Descriptor
     *  @param _descriptor descriptor to remove */
    public void removeDescriptor(DxxDescriptor _descriptor);
    
    /** Clear descriptor list */
    public void clearDescriptors();
    
    /** Get descriptor count 
     *  @return descriptor count */
    public int getDescriptorCount();
    
    
}
