/*
 * HandednessTest.java
 *
 * Created on November 29, 2005, 8:15 AM
 */

package org.gemini.tests;
import org.gemini.Handedness;

/**
 * Silly little unit test for org.gemini.Handedness.
 *
 * @author  Jaeden
 */
public class HandednessTest extends junit.framework.TestCase
{    
    /** Creates a new instance of HandednessTest 
        @param string the method to call */
    public HandednessTest(String string ) { super(string); }
    
    /** Test the "right" hand */
    public void testRight() throws Exception
    {
        assertEquals("right", Handedness.RIGHT.getName());
        assertEquals("right", Handedness.RIGHT.toString());
        assertEquals("right-handed", Handedness.RIGHT.toPostfixedString());    
        assertTrue(Handedness.RIGHT.equals(new Handedness("right")));
        assertFalse(Handedness.RIGHT.equals(new Handedness("left")));
    }
    
    /** Test the "left" hand */
    public void testLeft() throws Exception
    {
        assertEquals("left", Handedness.LEFT.getName());
        assertEquals("left", Handedness.LEFT.toString());
        assertEquals("left-handed", Handedness.LEFT.toPostfixedString());        
        assertTrue(Handedness.LEFT.equals(new Handedness("left")));
        assertFalse(Handedness.LEFT.equals(new Handedness("right")));
    }
    
     /** Test the ambidexterity value */
    public void testAmbidexterity() throws Exception
    {
        assertEquals("ambidextrous", Handedness.AMBIDEXTROUS.getName());
        assertEquals("ambidextrous", Handedness.AMBIDEXTROUS.toString());
        assertEquals("ambidextrous", Handedness.AMBIDEXTROUS.toPostfixedString());        
        assertTrue(Handedness.AMBIDEXTROUS.equals(new Handedness("ambidextrous")));
        assertFalse(Handedness.AMBIDEXTROUS.equals(new Handedness("right")));
    }
    
    /** Test a user-created instance */
    public void testInstance() throws Exception
    {
        Handedness H = new Handedness("chicken");
        assertEquals("chicken", H.getName());
        assertEquals("chicken", H.toString());
        assertEquals("chicken-handed", H.toPostfixedString());        
        assertTrue(H.equals(new Handedness("chicken")));
        assertFalse(H.equals(new Handedness("pork")));
        
        H.setName("beef");
        assertEquals("beef", H.getName());
        assertEquals("beef", H.toString());
        assertEquals("beef-handed", H.toPostfixedString());        
        assertTrue(H.equals(new Handedness("beef")));
        assertFalse(H.equals(new Handedness("chicken")));        
    }
}
