/*
 * Created on Nov 12, 2005
 *
 */
package org.slage.tests.util;

import junit.framework.Test;
import junit.framework.TestSuite;

public class UtilTests {

	public static Test suite() {
		TestSuite suite = new TestSuite("Test for org.slage.tests.util");
		//$JUnit-BEGIN$
		suite.addTestSuite(SoftHashMapTest.class);
		//$JUnit-END$
		return suite;
	}

}
