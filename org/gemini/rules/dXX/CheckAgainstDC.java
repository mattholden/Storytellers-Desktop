/*
 * CheckAgainstDC.java
 *
 * Created on November 17, 2005, 2:28 AM
 */

package org.gemini.rules.dXX;

/**
 * Defines an interface for objects that can make a check against a DC.
 *
 * @author  Jaeden
 */
public interface CheckAgainstDC 
{
    /** Roll a check against a given DC 
     *  @param dc the DC
     *  @return the result of the check - negative values failed, 0 and positive succeeded */
    public int check(int dc);
    
    /** Constant to signify the check was a Natural 1 */ 
    public static final int NATURAL1 = Integer.MIN_VALUE;
    
    /** Constant to signify the check was a Natural 20 */ 
    public static final int NATURAL20 = Integer.MAX_VALUE;
    
}
