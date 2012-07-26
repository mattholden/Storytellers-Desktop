/*
 * Rang.java
 *
 * Created on November 16, 2005, 9:16 AM
 */

package org.gemini;

/**
 * Implementation for a Range in feet. 
 * This may seem like overkill to wrap an integer, but several game systems
 * will extend this object.
 *
 * @author  Jaeden
 */
public class Range implements java.io.Serializable, Comparable
{
   
     /** Range in feet */ private int range = 0;
    
    /** Creates a new instance of Range       
      @param _range base range in feet */
    public Range(int _range) 
    {        
        range = _range;
    }
          
    /** Calculate the maximum range for this spell/effect      
     *@return maximum range of the spell/effect */
    public int getRange() { return range; }
    
    /** Mutator for range
     * @param _range new range */
    public void setRange(int _range) { range = _range; }
    
    /** Test equality of two Ranges
      @param that other Range to test
      @return true if this == that */
    public boolean equals(Object that)
    {
        if (getClass().equals(that.getClass()) == false)
            return false;
        
        return (range == ((Range)that).range);
    }
    
    /** Get range as a string 
     * @return string representation of the range */
    public String toString() { return Integer.toString(range) + " feet"; }
    
    /** Compare this Range to another range
     *  @param that A range to compare to
     *  @return negative if this is shorter than that, 0 if they are equal, 
        positive if that is shorter than this */
    public int compareTo(Object that) 
    {
        if (that instanceof Range == false) 
            return 0;
        
        return this.range - ((Range)that).range;
    }
        
    
    
    }
    
