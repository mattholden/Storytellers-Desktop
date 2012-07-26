
package org.gemini.tests;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Collects all Gemini System unit tests.
 * 
 * @author <a href="mailto:matt@geminisystem.org">Matt Holden</a>
 */
public class GeminiTests
		extends TestCase {
                    
        /** Constructs the test suite. 
            @returns test suite to run. */
	public static Test suite() 
        {
		TestSuite gemini = new TestSuite("Gemini System API");
		//gemini.addTest(AllParserTests.suite());
                
                
                
		gemini.addTestSuite(HandednessTest.class);
		gemini.addTestSuite(RangeTest.class);
		
                return gemini;
	}
}