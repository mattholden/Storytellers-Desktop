/*
 * Progression.java
 *
 * Created on November 14, 2005, 11:14 PM
 */

package org.gemini;
import java.io.Serializable;

/**
 * A progression is a simple templated type, static-sized
 * array. It's a hair cleaner to work with, however, 
 * allows for a Progression to change types, and provides
 * a 1-based interface vs. a 0-based one. 
 *  
 * @author  Jaeden
 */
public class Progression<Type> implements Serializable
{
    /** The array of objects */
    private Type[] progression;
    
    /** Creates a new instance of Progression 
      @param array array of objects to progress through */
    public Progression(Type[] array) 
    {
        progression = array;
    }
    
    /** Get the nth element in the array (1-based) 
     @param n the 1-based index to get (i.e. Level 1 is index 1)
     @return the nth value. 
     @throws IllegalArgumentExeception if n-1 is outside the bounds of the array. */
    public Type get(int n)
    {
        n--;
        if (n < 0 || n >= progression.length)
            throw new IllegalArgumentException("Index " + Integer.toString(n+1) + " is out of bounds for this progression.");
        
        return progression[n];
    }
    
}
