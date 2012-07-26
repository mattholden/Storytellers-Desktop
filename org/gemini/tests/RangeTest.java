/*
 * RangeTest.java
 *
 * Created on November 29, 2005, 8:27 AM
 */

package org.gemini.tests;
import org.gemini.Range;

/**
 * Test simple ranges.
 *
 * @author  Jaeden
 */
public class RangeTest extends junit.framework.TestCase
{
    
    /** Creates a new instance of RangeTest 
        @param string string */
    public RangeTest(String string) { super(string); }
    
    /** Test getting and setting of range value */
    public void testGetSet() throws Exception
    {
        Range R = new Range(50);
        assertEquals(50, R.getRange());
        R.setRange(75);
        assertEquals(75, R.getRange());        
    }
    
    /** test equals and compare */
    public void testCompares() throws Exception
    {
        Range A = new Range(100);
        Range B = new Range(200);
        Range b = new Range(200);
        Range C = new Range(300);
        
        assertTrue(B.equals(b));
        assertFalse(B.equals(A));
        
        assertTrue(A.compareTo(B) < 0);
        assertTrue(C.compareTo(B) > 0);
        assertTrue(b.compareTo(B) == 0);
        
    }
}
