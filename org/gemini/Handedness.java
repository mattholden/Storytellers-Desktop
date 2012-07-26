/*
 * Handedness.java
 *
 * Created on November 11, 2005, 1:57 AM
 */

package org.gemini;
import org.slage.framework.NamedObject;

/**
 * Defines the dominant hand of a character as the right or left hand, or ambidextrous.
 *
 * @author  Jaeden
 */
public class Handedness implements NamedObject, java.io.Serializable, Grantor
{
    /** The name of the hand */ private String handedness;
    
    /** Creates a new instance of Handedness 
        @param hand name of the dominant hand */
    public Handedness(String hand) { setName(hand); }       
    
    /** Get the name of the dominant hand 
     *  @return "left", "right", or "ambidextrous" */
    public String getName() { return handedness; }
        
    /** Set the name of the dominant hand 
     *  @param name Name to use */
    public void setName(String name) { handedness = name; }
    
   /** Refer to the right-handed condition */
    public static final Handedness RIGHT = new Handedness("right");
    
    /** Refer to the left-handed condition */
    public static final Handedness LEFT = new Handedness("left");
   
    /** Refer to the ambidextrous condition */
    public static final Handedness AMBIDEXTROUS = new Handedness("ambidextrous");
   
    /** Test the equality of the handedness object
        @param that an object to test against
        @return true if this == that */
    public boolean equals(Object that)
    {
        if (that instanceof Handedness == false) return false;        
        return ( handedness.equalsIgnoreCase( ((Handedness) that).handedness) );
    }
    
    /** Get the handedness as a String. 
        @return handedness as a string */
    public String toString() { return handedness; }
    
    /** Get the handedness as a string with -handed appended to non-ambidextrous values. 
        @return a formatted string */
    public String toPostfixedString()
    {
        return (handedness.equals("ambidextrous") ? handedness : handedness + "-handed");
    }
     
}
