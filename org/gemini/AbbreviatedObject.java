/*
 * AbbreviatedObject.java
 *
 * Created on November 16, 2005, 12:35 AM
 */

package org.gemini;

/**
 * Defines an object whose name has an abbreviation.
 *
 * @author  Jaeden
 */
public interface AbbreviatedObject 
{
    
    /** Get abbreviation
     * @return abbreviation - if null, the first 3 letters */
    public String getAbbreviation();
        
    
    /** Subinterface for abbreviated objects which can change the abbreviation */
    public static interface Mutable extends AbbreviatedObject
    {        
        /** Set abbreviation
         * @param _abbrev abbreviation. Usually 3 letters. */
        public void setAbbreviation(String _abbrev);
    }
}
