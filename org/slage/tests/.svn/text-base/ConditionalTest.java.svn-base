/*
 * ConditionalTest.java
 *
 * Created on September 18, 2005, 11:08 AM
 */

package org.slage.tests;

import java.math.BigDecimal;
import java.math.BigInteger;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.slage.SlageObject;
import org.slage.conditionals.ConditionGroup;
import org.slage.conditionals.Equal;
import org.slage.conditionals.Greater;
import org.slage.conditionals.GreaterEqual;
import org.slage.conditionals.Less;
import org.slage.conditionals.LessEqual;
import org.slage.conditionals.NotEqual;
import org.slage.framework.Tools;
import org.slage.xml.XMLProcessor;

/**
 * Test Conditionals in Handlers
 * 
 * @author Jaeden
 */
public class ConditionalTest
		extends junit.framework.TestCase {

	/**
	 * Creates a new instance of ConditionalTest
	 * 
	 * @param strFunc function
	 */
	public ConditionalTest(String strFunc) {
		super(strFunc);
	}

	/** Test conditionals in Handlers */
	public void testIntegers() throws Exception {
		Integer l5 = new Integer(5);
		Integer i0 = new Integer(0);

		Equal e = new Equal(i0, l5);
		Greater g = new Greater(i0, l5);
		GreaterEqual ge = new GreaterEqual(i0, l5);
		NotEqual ne = new NotEqual(i0, l5);
		Less l = new Less(i0, l5);
		LessEqual le = new LessEqual(i0, l5);

		IncHandler He = new IncHandler();
		IncHandler Hne = new IncHandler();
		IncHandler Hg = new IncHandler();
		IncHandler Hge = new IncHandler();
		IncHandler Hle = new IncHandler();
		IncHandler Hl = new IncHandler();

		He.addCondition(e);
		Hne.addCondition(ne);
		Hg.addCondition(g);
		Hge.addCondition(ge);
		Hl.addCondition(l);
		Hle.addCondition(le);

		IncHandler[] hArray = { He, Hne, Hg, Hge, Hl, Hle };

		for (int i = 0; i < 10; i++) {
			Integer I = new Integer(i);
			e.setObjCompare(I);
			ne.setObjCompare(I);
			g.setObjCompare(I);
			ge.setObjCompare(I);
			l.setObjCompare(I);
			le.setObjCompare(I);

			for (int j = 0; j < 6; j++) {
				if (hArray[j].check())
					hArray[j].fire();
			}
		}

		assertEquals(1, He.i);
		assertEquals(9, Hne.i);
		assertEquals(5, Hl.i);
		assertEquals(6, Hle.i);
		assertEquals(5, Hge.i);
		assertEquals(4, Hg.i);

	}

	/** Test conditionals for strings */
	public void testStrings() throws Exception {
		String str1 = "I am Henry the 8th, I am";
		String str2 = "Henry the 8th, I am, I am...";

		IncHandler hEquals = new IncHandler();
		IncHandler hNotEquals = new IncHandler();
		IncHandler hEqualsCase = new IncHandler();
		IncHandler hFailEquals = new IncHandler();

		hEquals.addCondition(new Equal(str1, str1));
		hNotEquals.addCondition(new NotEqual(str1, str2));
		hFailEquals.addCondition(new Equal(str1, str2));
		hEqualsCase.addCondition(new Equal(str1, str1.toUpperCase()));

		if (hEquals.check())
			hEquals.fire();
		if (hNotEquals.check())
			hNotEquals.fire();
		if (hEqualsCase.check())
			hEqualsCase.fire();
		if (hFailEquals.check())
			hFailEquals.fire();

		assertEquals(1, hEquals.i);
		assertEquals(1, hNotEquals.i);
		assertEquals(1, hEqualsCase.i);
		assertEquals(0, hFailEquals.i);
	}

	/** Test conditionals in Handlers with mixed decimals */
	public void testMixedFloats() throws Exception {
		Integer i0 = new Integer(0);
		Float f55 = new Float(5.5f);

		Equal e = new Equal(i0, f55);
		Greater g = new Greater(i0, f55);
		GreaterEqual ge = new GreaterEqual(i0, f55);
		NotEqual ne = new NotEqual(i0, f55);
		Less l = new Less(i0, f55);
		LessEqual le = new LessEqual(i0, f55);

		IncHandler He = new IncHandler();
		IncHandler Hne = new IncHandler();
		IncHandler Hg = new IncHandler();
		IncHandler Hge = new IncHandler();
		IncHandler Hle = new IncHandler();
		IncHandler Hl = new IncHandler();

		He.addCondition(e);
		Hne.addCondition(ne);
		Hg.addCondition(g);
		Hge.addCondition(ge);
		Hl.addCondition(l);
		Hle.addCondition(le);

		IncHandler[] hArray = { He, Hne, Hg, Hge, Hl, Hle };

		for (int i = 0; i < 10; i++) {
			Integer I = new Integer(i);
			e.setObjCompare(I);
			ne.setObjCompare(I);
			g.setObjCompare(I);
			ge.setObjCompare(I);
			l.setObjCompare(I);
			le.setObjCompare(I);

			for (int j = 0; j < 6; j++) {
				if (hArray[j].check())
					hArray[j].fire();
			}
		}

		assertEquals(0, He.i);
		assertEquals(10, Hne.i);
		assertEquals(6, Hl.i);
		assertEquals(6, Hle.i);
		assertEquals(4, Hge.i);
		assertEquals(4, Hg.i);

	}

	/** Test conditionals in Handlers with all decimals */
	public void testFloatPairs() throws Exception {
		Float i0 = new Float(0);
		Float f55 = new Float(5.5f);

		Equal e = new Equal(i0, f55);
		Greater g = new Greater(i0, f55);
		GreaterEqual ge = new GreaterEqual(i0, f55);
		NotEqual ne = new NotEqual(i0, f55);
		Less l = new Less(i0, f55);
		LessEqual le = new LessEqual(i0, f55);

		IncHandler He = new IncHandler();
		IncHandler Hne = new IncHandler();
		IncHandler Hg = new IncHandler();
		IncHandler Hge = new IncHandler();
		IncHandler Hle = new IncHandler();
		IncHandler Hl = new IncHandler();

		He.addCondition(e);
		Hne.addCondition(ne);
		Hg.addCondition(g);
		Hge.addCondition(ge);
		Hl.addCondition(l);
		Hle.addCondition(le);

		IncHandler[] hArray = { He, Hne, Hg, Hge, Hl, Hle };

		for (int i = 0; i < 10; i++) {
			Float I = new Float(i + 0.5f);
			e.setObjCompare(I);
			ne.setObjCompare(I);
			g.setObjCompare(I);
			ge.setObjCompare(I);
			l.setObjCompare(I);
			le.setObjCompare(I);

			for (int j = 0; j < 6; j++) {
				if (hArray[j].check())
					hArray[j].fire();
			}
		}

		assertEquals(1, He.i);
		assertEquals(9, Hne.i);
		assertEquals(5, Hl.i);
		assertEquals(6, Hle.i);
		assertEquals(5, Hge.i);
		assertEquals(4, Hg.i);

	}

	/** Test conditionals in Handlers with short and long */
	public void testShortLong() throws Exception {
		Short s0 = new Short((short) 0);
		Long l5 = new Long(5l);

		Equal e = new Equal(s0, l5);
		Greater g = new Greater(s0, l5);
		GreaterEqual ge = new GreaterEqual(s0, l5);
		NotEqual ne = new NotEqual(s0, l5);
		Less l = new Less(s0, l5);
		LessEqual le = new LessEqual(s0, l5);

		IncHandler He = new IncHandler();
		IncHandler Hne = new IncHandler();
		IncHandler Hg = new IncHandler();
		IncHandler Hge = new IncHandler();
		IncHandler Hle = new IncHandler();
		IncHandler Hl = new IncHandler();

		He.addCondition(e);
		Hne.addCondition(ne);
		Hg.addCondition(g);
		Hge.addCondition(ge);
		Hl.addCondition(l);
		Hle.addCondition(le);

		IncHandler[] hArray = { He, Hne, Hg, Hge, Hl, Hle };

		for (int i = 0; i < 10; i++) {
			Short s = new Short((short) i);
			e.setObjCompare(s);
			ne.setObjCompare(s);
			g.setObjCompare(s);
			ge.setObjCompare(s);
			l.setObjCompare(s);
			le.setObjCompare(s);

			for (int j = 0; j < 6; j++) {
				if (hArray[j].check())
					hArray[j].fire();
			}
		}

		assertEquals(1, He.i);
		assertEquals(9, Hne.i);
		assertEquals(5, Hl.i);
		assertEquals(6, Hle.i);
		assertEquals(5, Hge.i);
		assertEquals(4, Hg.i);

	}

	/** Test BigInteger functions */
	public void testBigInteger() throws Exception {
		// First, test an easy BigInteger
		BigInteger BI = new BigInteger("12345");
		BigInteger BI2 = new BigInteger("12345");

		// A common pivot point
		BigInteger BImillion = new BigInteger("1000000");

		Equal eq = new Equal(BI, BI2);

		Equal eqMill = new Equal(BI, BImillion);
		NotEqual neqMill = new NotEqual(BI, BImillion);
		Greater gMill = new Greater(BI, BImillion);
		GreaterEqual geMill = new GreaterEqual(BI, BImillion);
		Less lMill = new Less(BI, BImillion);
		LessEqual leMill = new LessEqual(BI, BImillion);

		IncHandler hEQ = new IncHandler();
		hEQ.addCondition(eq);
		IncHandler hEQm = new IncHandler();
		hEQm.addCondition(eqMill);
		IncHandler hNEQm = new IncHandler();
		hNEQm.addCondition(neqMill);
		IncHandler hGm = new IncHandler();
		hGm.addCondition(gMill);
		IncHandler hGEm = new IncHandler();
		hGEm.addCondition(geMill);
		IncHandler hLm = new IncHandler();
		hLm.addCondition(lMill);
		IncHandler hLEm = new IncHandler();
		hLEm.addCondition(leMill);

		hEQ.execute();
		hEQm.execute();
		hNEQm.execute();
		hGm.execute();
		hGEm.execute();
		hLm.execute();
		hLEm.execute();

		assertEquals(1, hEQ.i);
		assertEquals(0, hEQm.i);
		assertEquals(1, hNEQm.i);
		assertEquals(0, hGm.i);
		assertEquals(0, hGEm.i);
		assertEquals(1, hLm.i);
		assertEquals(1, hLEm.i);

		// Now test one REAL BigInteger
		BigInteger Big = new BigInteger("123456789009876543211234567890");
		BigInteger Big2 = new BigInteger("123456789009876543211234567890");

		hEQm.clearConditions();
		hNEQm.clearConditions();
		hGm.clearConditions();
		hGEm.clearConditions();
		hLEm.clearConditions();
		hLm.clearConditions();

		eqMill = new Equal(Big, Big2);
		neqMill = new NotEqual(Big, Big2);
		gMill = new Greater(Big, Big2);
		geMill = new GreaterEqual(Big, Big2);
		lMill = new Less(Big, Big2);
		leMill = new LessEqual(Big, Big2);

		hEQ.addCondition(eq);
		hEQm.addCondition(eqMill);
		hNEQm.addCondition(neqMill);
		hGm.addCondition(gMill);
		hGEm.addCondition(geMill);
		hLm.addCondition(lMill);
		hLEm.addCondition(leMill);

		hEQm.i = 0;
		hNEQm.i = 0;
		hGm.i = 0;
		hGEm.i = 0;
		hLm.i = 0;
		hLEm.i = 0;

		hEQ.execute();
		hEQm.execute();
		hNEQm.execute();
		hGm.execute();
		hGEm.execute();
		hLm.execute();
		hLEm.execute();

		assertEquals(1, hEQm.i);
		assertEquals(0, hNEQm.i);
		assertEquals(0, hGm.i);
		assertEquals(1, hGEm.i);
		assertEquals(0, hLm.i);
		assertEquals(1, hLEm.i);

		Big2 = Big2.subtract(new BigInteger("500"));

		hEQm.clearConditions();
		hNEQm.clearConditions();
		hGm.clearConditions();
		hGEm.clearConditions();
		hLEm.clearConditions();
		hLm.clearConditions();

		eqMill = new Equal(Big, Big2);
		neqMill = new NotEqual(Big, Big2);
		gMill = new Greater(Big, Big2);
		geMill = new GreaterEqual(Big, Big2);
		lMill = new Less(Big, Big2);
		leMill = new LessEqual(Big, Big2);

		hEQm.addCondition(eqMill);
		hNEQm.addCondition(neqMill);
		hGm.addCondition(gMill);
		hGEm.addCondition(geMill);
		hLm.addCondition(lMill);
		hLEm.addCondition(leMill);

		hEQm.i = 0;
		hNEQm.i = 0;
		hGm.i = 0;
		hGEm.i = 0;
		hLm.i = 0;
		hLEm.i = 0;

		assertEquals(hEQm.getConditionCount(), 1);
		hEQm.execute();
		hNEQm.execute();
		hGm.execute();
		hGEm.execute();
		hLm.execute();
		hLEm.execute();

		assertEquals(0, hEQm.i);
		assertEquals(1, hNEQm.i);
		assertEquals(1, hGm.i);
		assertEquals(1, hGEm.i);
		assertEquals(0, hLm.i);
		assertEquals(0, hLEm.i);
	}

	/** Test BigDecimal functions */
	public void testBigDecimal() throws Exception {
		// First, test an easy BigInteger
		BigDecimal BI = new BigDecimal("12345.5");
		BigDecimal BI2 = new BigDecimal("12345.5");

		// A common pivot point
		BigDecimal BImillion = new BigDecimal("1000000");

		Equal eq = new Equal(BI, BI2);

		Equal eqMill = new Equal(BI, BImillion);
		NotEqual neqMill = new NotEqual(BI, BImillion);
		Greater gMill = new Greater(BI, BImillion);
		GreaterEqual geMill = new GreaterEqual(BI, BImillion);
		Less lMill = new Less(BI, BImillion);
		LessEqual leMill = new LessEqual(BI, BImillion);

		IncHandler hEQ = new IncHandler();
		hEQ.addCondition(eq);
		IncHandler hEQm = new IncHandler();
		hEQm.addCondition(eqMill);
		IncHandler hNEQm = new IncHandler();
		hNEQm.addCondition(neqMill);
		IncHandler hGm = new IncHandler();
		hGm.addCondition(gMill);
		IncHandler hGEm = new IncHandler();
		hGEm.addCondition(geMill);
		IncHandler hLm = new IncHandler();
		hLm.addCondition(lMill);
		IncHandler hLEm = new IncHandler();
		hLEm.addCondition(leMill);

		hEQ.execute();
		hEQm.execute();
		hNEQm.execute();
		hGm.execute();
		hGEm.execute();
		hLm.execute();
		hLEm.execute();

		assertEquals(1, hEQ.i);
		assertEquals(0, hEQm.i);
		assertEquals(1, hNEQm.i);
		assertEquals(0, hGm.i);
		assertEquals(0, hGEm.i);
		assertEquals(1, hLm.i);
		assertEquals(1, hLEm.i);

		// Now test one REAL BigInteger
		BigDecimal Big = new BigDecimal("12345678900987654321.1234567890");
		BigDecimal Big2 = new BigDecimal("12345678900987654321.1234567890");

		hEQm.clearConditions();
		hNEQm.clearConditions();
		hGm.clearConditions();
		hGEm.clearConditions();
		hLEm.clearConditions();
		hLm.clearConditions();

		eqMill = new Equal(Big, Big2);
		neqMill = new NotEqual(Big, Big2);
		gMill = new Greater(Big, Big2);
		geMill = new GreaterEqual(Big, Big2);
		lMill = new Less(Big, Big2);
		leMill = new LessEqual(Big, Big2);

		hEQ.addCondition(eq);
		hEQm.addCondition(eqMill);
		hNEQm.addCondition(neqMill);
		hGm.addCondition(gMill);
		hGEm.addCondition(geMill);
		hLm.addCondition(lMill);
		hLEm.addCondition(leMill);

		hEQm.i = 0;
		hNEQm.i = 0;
		hGm.i = 0;
		hGEm.i = 0;
		hLm.i = 0;
		hLEm.i = 0;

		hEQ.execute();
		hEQm.execute();
		hNEQm.execute();
		hGm.execute();
		hGEm.execute();
		hLm.execute();
		hLEm.execute();

		assertEquals(1, hEQm.i);
		assertEquals(0, hNEQm.i);
		assertEquals(0, hGm.i);
		assertEquals(1, hGEm.i);
		assertEquals(0, hLm.i);
		assertEquals(1, hLEm.i);

		Big2 = Big2.subtract(new BigDecimal("500.53"));

		hEQm.clearConditions();
		hNEQm.clearConditions();
		hGm.clearConditions();
		hGEm.clearConditions();
		hLEm.clearConditions();
		hLm.clearConditions();

		eqMill = new Equal(Big, Big2);
		neqMill = new NotEqual(Big, Big2);
		gMill = new Greater(Big, Big2);
		geMill = new GreaterEqual(Big, Big2);
		lMill = new Less(Big, Big2);
		leMill = new LessEqual(Big, Big2);

		hEQm.addCondition(eqMill);
		hNEQm.addCondition(neqMill);
		hGm.addCondition(gMill);
		hGEm.addCondition(geMill);
		hLm.addCondition(lMill);
		hLEm.addCondition(leMill);

		hEQm.i = 0;
		hNEQm.i = 0;
		hGm.i = 0;
		hGEm.i = 0;
		hLm.i = 0;
		hLEm.i = 0;

		assertEquals(hEQm.getConditionCount(), 1);
		hEQm.execute();
		hNEQm.execute();
		hGm.execute();
		hGEm.execute();
		hLm.execute();
		hLEm.execute();

		assertEquals(0, hEQm.i);
		assertEquals(1, hNEQm.i);
		assertEquals(1, hGm.i);
		assertEquals(1, hGEm.i);
		assertEquals(0, hLm.i);
		assertEquals(0, hLEm.i);
	}

	/** Test mixed Big and Not Big functions */
	public void testSomeBigSomeSmall() throws Exception {
		// First, test an easy BigInteger
		BigDecimal BI = new BigDecimal("12345.5");
		BigInteger BInt = new BigInteger("9876");
		Double D = new Double("12345.5");
		Long L = new Long("9876");

		Equal eq = new Equal(BI, D);
		Equal eq2 = new Equal(BInt, L);
		Greater g = new Greater(D, BInt);
		Less l = new Less(L, BI);

		IncHandler h1 = new IncHandler();
		h1.addCondition(eq);
		IncHandler h2 = new IncHandler();
		h2.addCondition(eq2);
		IncHandler h3 = new IncHandler();
		h3.addCondition(g);
		IncHandler h4 = new IncHandler();
		h4.addCondition(l);

		h1.execute();
		h2.execute();
		h3.execute();
		h4.execute();

		assertEquals(1, h1.i);
		assertEquals(1, h2.i);
		assertEquals(1, h3.i);
		assertEquals(1, h4.i);

	}

	/** test multiple conditionals */
	public void testMultiConditions() throws Exception {
		Integer i = new Integer(500);
		Short s = new Short((short) 500);
		Long l = new Long(1000);
		String foo = new String("Fooo");
		String bar = new String("Bar");

		Equal trueInts = new Equal(i, s);
		Equal trueString = new Equal(foo, foo);
		Greater trueGreat = new Greater(l, s);
		Less trueLess = new Less(i, l);

		Equal falseInts = new Equal(i, l);
		Equal falseString = new Equal(foo, bar);

		IncHandler allTrue = new IncHandler();
		allTrue.addCondition(trueInts);
		allTrue.addCondition(trueString);
		allTrue.addCondition(trueGreat);
		allTrue.addCondition(trueLess);

		assertEquals(4, allTrue.getConditionCount());

		allTrue.execute();
		assertEquals(1, allTrue.i);
		allTrue.i = 0;

		allTrue.setAcceptance(ConditionGroup.ANY);
		allTrue.execute();
		assertEquals(1, allTrue.i);
		allTrue.i = 0;

		allTrue.setAcceptance(ConditionGroup.NONE);
		allTrue.execute();
		assertEquals(0, allTrue.i);

		allTrue.addCondition(falseInts);

		assertEquals(5, allTrue.getConditionCount());

		allTrue.setAcceptance(ConditionGroup.ALL);
		allTrue.execute();
		assertEquals(0, allTrue.i);
		allTrue.i = 0;

		allTrue.setAcceptance(ConditionGroup.ANY);
		allTrue.execute();
		assertEquals(1, allTrue.i);
		allTrue.i = 0;

		allTrue.setAcceptance(ConditionGroup.NONE);
		allTrue.execute();
		assertEquals(0, allTrue.i);

		allTrue.clearConditions();
		allTrue.i = 0;
		allTrue.addCondition(falseInts);
		allTrue.addCondition(falseString);

		assertEquals(2, allTrue.getConditionCount());

		allTrue.setAcceptance(ConditionGroup.ALL);
		allTrue.execute();
		assertEquals(0, allTrue.i);
		allTrue.i = 0;

		allTrue.setAcceptance(ConditionGroup.ANY);
		allTrue.execute();
		assertEquals(0, allTrue.i);
		allTrue.i = 0;

		allTrue.setAcceptance(ConditionGroup.NONE);
		allTrue.execute();
		assertEquals(1, allTrue.i);
		allTrue.i = 0;

		// test a nested group
		ConditionGroup group = new ConditionGroup(ConditionGroup.ALL);
		group.addCondition(trueInts);
		group.addCondition(trueString);

		allTrue.clearConditions();
		allTrue.i = 0;

		allTrue.setAcceptance(ConditionGroup.ALL);
		allTrue.addCondition(group);
		allTrue.addCondition(falseInts);

		assertEquals(2, allTrue.getConditionCount());

		allTrue.setAcceptance(ConditionGroup.ALL);
		allTrue.execute();
		assertEquals(0, allTrue.i);
		allTrue.i = 0;

		allTrue.setAcceptance(ConditionGroup.ANY);
		allTrue.execute();
		assertEquals(1, allTrue.i);
		allTrue.i = 0;

		allTrue.setAcceptance(ConditionGroup.NONE);
		allTrue.execute();
		assertEquals(0, allTrue.i);
		allTrue.i = 0;

		group.addCondition(falseInts);

		assertEquals(2, allTrue.getConditionCount());

		allTrue.setAcceptance(ConditionGroup.ALL);
		allTrue.execute();
		assertEquals(0, allTrue.i);
		allTrue.i = 0;

		allTrue.setAcceptance(ConditionGroup.ANY);
		allTrue.execute();
		assertEquals(0, allTrue.i);
		allTrue.i = 0;

		allTrue.setAcceptance(ConditionGroup.NONE);
		allTrue.execute();
		assertEquals(1, allTrue.i);
		allTrue.i = 0;

		allTrue.removeCondition(falseInts);

		assertEquals(1, allTrue.getConditionCount());

		allTrue.setAcceptance(ConditionGroup.ALL);
		allTrue.execute();
		assertEquals(0, allTrue.i);
		allTrue.i = 0;

		allTrue.setAcceptance(ConditionGroup.ANY);
		allTrue.execute();
		assertEquals(0, allTrue.i);
		allTrue.i = 0;

		allTrue.setAcceptance(ConditionGroup.NONE);
		allTrue.execute();
		assertEquals(1, allTrue.i);
		allTrue.i = 0;

		group.removeCondition(falseInts);

		assertEquals(1, allTrue.getConditionCount());

		allTrue.setAcceptance(ConditionGroup.ALL);
		allTrue.execute();
		assertEquals(1, allTrue.i);
		allTrue.i = 0;

		allTrue.setAcceptance(ConditionGroup.ANY);
		allTrue.execute();
		assertEquals(1, allTrue.i);
		allTrue.i = 0;

		allTrue.setAcceptance(ConditionGroup.NONE);
		allTrue.execute();
		assertEquals(0, allTrue.i);
		allTrue.i = 0;

	}

	/** Test that XML for conditions is properly written */
	public void testXMLWrite() throws Exception {
		Equal e = new Equal(new Integer(5), new String("foo"));

		// todo

		org.slage.handlers.ResponseHandler H = new org.slage.handlers.ResponseHandler(null, null, null);
		H.addCondition(e);
		H.addCondition(new NotEqual(new Integer(8), new Short((short) 5)));

		// System.out.println(H.getXMLString());

	}

	/** test that the conditional handler is read */
	public void testXMLReadHandler() throws Exception {
		// XMLProcessor.loadElements();
		SAXBuilder builder = new SAXBuilder(true);
		builder.setFeature("http://apache.org/xml/features/validation/schema", true);
		doc = builder.build(Tools.GetFile("org.slage.tests.xml.testfiles", "objectWithConditionalHandlersXML.xml"));

		objectElement = doc.getRootElement();

		SlageObject ob = (SlageObject) XMLProcessor.dispatch("objectWithConditionalHandlersXML.xml");

		assertNotNull(ob);
		assertEquals(2, ob.getHandlerCount());
		IncHandler H = (IncHandler) ob.getHandlers(null).get(0);
		IncHandler H2 = (IncHandler) ob.getHandlers(null).get(1);

		assertEquals(2, H.getConditionCount());
		assertEquals(1, H2.getConditionCount());

		H.execute();
		H2.execute();
		assertEquals(1, H.i);
		assertEquals(0, H2.i);

	}

	protected Document doc;

	protected Element objectElement;

}
/*******************************************************************************
 * BEGIN LICENSE BLOCK **** Version: MPL 1.1/GPL 2.0/LGPL 2.1
 * 
 * The contents of this file are subject to the Mozilla Public License Version
 * 1.1 (the "License"); you may not use this file except in compliance with the
 * License. You may obtain a copy of the License at http://www.mozilla.org/MPL/
 * 
 * Software distributed under the License is distributed on an "AS IS" basis,
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License for
 * the specific language governing rights and limitations under the License.
 * 
 * The Original Code is Slage.
 * 
 * The Initial Developer of the Original Code is The SQ7.org project. Portions
 * created by the Initial Developer are Copyright (C) 2005 the Initial
 * Developer. All Rights Reserved.
 * 
 * Contributor(s): Matt Holden (Matt@sq7.org) Travis Savo (Travis@sq7.org)
 * Robert Wenner (Robert@sq7.org) Jared Cope (Jared@sq7.org) Colin Davis
 * (Colin@sq7.org)
 * 
 * Alternatively, the contents of this file may be used under the terms of
 * either the GNU General Public License Version 2 or later (the "GPL"), or the
 * GNU Lesser General Public License Version 2.1 or later (the "LGPL"), in which
 * case the provisions of the GPL or the LGPL are applicable instead of those
 * above. If you wish to allow use of your version of this file only under the
 * terms of either the GPL or the LGPL, and not to allow others to use your
 * version of this file under the terms of the MPL, indicate your decision by
 * deleting the provisions above and replace them with the notice and other
 * provisions required by the GPL or the LGPL. If you do not delete the
 * provisions above, a recipient may use your version of this file under the
 * terms of any one of the MPL, the GPL or the LGPL.
 * 
 ******************************************************************************/
