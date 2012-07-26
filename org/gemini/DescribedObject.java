/*
 * DescribedObject.java
 *
 * Created on November 12, 2005, 9:01 PM
 */

package org.gemini;

/**
 * Provides an interface for a getDescription() 
 * to glean information about the implementing object. 
 *
 * @author  Jaeden
 */
public interface DescribedObject 
{    
    /** Get a description for the object
     * @return a description */
    public String getDescription();         
    
    /** Subinterface for described objects which can change the description */
    public static interface Mutable extends DescribedObject
    {        
        /** Set description
         * @param _desc description.  */
        public void setDescription(String _desc);
    }
}
