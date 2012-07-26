/*
 * ObjectWithCost.java
 *
 * Created on November 14, 2005, 11:32 AM
 */

package org.gemini;

/**
 * Defines an interface for objects which have a cost (mostly physical items.)
 *
 * @author Jaeden
 */
public interface ObjectWithCost 
{
    /** Getter for cost.
     * @return Value of property cost.     */
    public int getCost();
    
    /** Subinterface for objects with a settable cost */
    public static interface Mutable extends ObjectWithCost
    {
        /** Setter for cost.
         * @param _cost New value of property cost.      */
        public void setCost(int _cost);
    }
    
}
