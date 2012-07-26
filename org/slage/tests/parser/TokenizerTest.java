/*
 * $Id: TokenizerTest.java,v 1.1 2005/11/04 07:51:52 kevlar2 Exp $
 */
package org.slage.tests.parser;

import junit.framework.TestCase;

import org.slage.parser.Tokenizer;
import org.slage.parser.WordRepeatedException;

/**
 * @author <a href="mailto:robert.wenner@gmx.de">Robert Wenner</a>
 */
public class TokenizerTest
		extends TestCase {
	Tokenizer tokenizer;

	protected void setUp() {
		tokenizer = new Tokenizer("and", "one two three");
	}

	public void testToStringAll() {
		assertEquals("one two three", tokenizer.toString());
	}

	public void testToString1() {
		assertEquals("one", tokenizer.toString(1));
	}

	public void testToString2() {
		assertEquals("one two", tokenizer.toString(2));
	}

	public void testToStringTooMuch() {
		try {
			tokenizer.toString(4);
			fail("Accepted to much on toString");
		} catch (IndexOutOfBoundsException expected) { /* Ok. */
		}
	}

	public void testAsCollectionAll() {
		Object[] items = tokenizer.toCollection().toArray();
		assertEquals("one", items[0]);
		assertEquals("two", items[1]);
		assertEquals("three", items[2]);
	}

	public void testAsCollection1() {
		assertEquals("one", tokenizer.toCollection().toArray()[0]);
	}

	public void testHasNextEnd() {
		tokenizer.goAhead(3);
		assertFalse("Should have no more input", tokenizer.hasNext());
	}

	public void testNext() throws WordRepeatedException {
		tokenizer.next();
		assertEquals("two", tokenizer.next());
		assertEquals("three", tokenizer.next());
	}

	public void testStoreReset() throws WordRepeatedException {
		tokenizer.next();
		tokenizer.mark();
		tokenizer.next();
		tokenizer.reset();
		assertEquals("two", tokenizer.next());
	}

	public void testStoreAndResetTwice() throws WordRepeatedException {
		tokenizer.mark();
		tokenizer.next();
		tokenizer.mark();
		tokenizer.next();
		tokenizer.reset();
		tokenizer.reset();
		assertEquals("Wrong tokenizer stack", "one", tokenizer.next());
	}

	public void testSubstitutesSeparatorChar() throws WordRepeatedException {
		tokenizer = new Tokenizer("and", "one.only");
		assertEquals("one", tokenizer.next());
		assertEquals("and", tokenizer.next());
		assertEquals("only", tokenizer.next());
	}
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
