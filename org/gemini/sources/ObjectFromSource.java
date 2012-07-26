/*
 * ObjectFromSource.java
 *
 * Created on November 12, 2005, 5:51 PM
 */

package org.gemini.sources;

/**
 * Defines an interface for objects which have Source references.
 *
 * @author  Jaeden
 */
public interface ObjectFromSource 
{
    
    /** Getter for source reference 
     * @return reference to the source */
    public SourceReference<?> getSource();
    
     
    /** Subinterface for sourced objects which can change the source */
    public static interface Mutable extends ObjectFromSource
    {        
        /** Setter for source reference.          
         * @param _source New value of property source.      */
        public void setSource(SourceReference<?> _source);
    }
    
}
