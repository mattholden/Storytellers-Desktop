/*
 * MethodListPrinter.java
 *
 * Created on October 8, 2005, 6:11 PM
 */

package org.slage.util;

import java.lang.reflect.Method;

/**
 * Prints (to the console) a list of all methods in the class
 * 
 * @author Jaeden
 */
public class MethodListPrinter {

	/**
	 * Print the methods
	 * 
	 * @param args Arguments.. Args[0] should be the FQN of the class
	 */
	public static void main(String[] args) {
		if (args.length == 0) {
			System.out.println("\nEnter a fully-qualified class name on the command line!\n\n");
			System.exit(0);
		}

		try {
			Class c = Class.forName(args[0]);
			Method[] meths = c.getMethods();
			java.util.Arrays.sort(meths, comparator);

			for (int i = 0; i < meths.length; i++) {
				StringBuffer sb = new StringBuffer();
				sb.append(meths[i].getReturnType().getName());
				sb.append(" ");
				sb.append(meths[i].getName());
				sb.append("(");

				Class[] params = meths[i].getParameterTypes();
				for (int x = 0; x < params.length; x++) {
					sb.append(params[x].getName());
					if (params.length - 1 != x)
						sb.append(", ");
				}
				sb.append(")");

				System.out.println(sb.toString());
			}

		} catch (Exception e) {
			System.out.println("No class named " + args[0] + " found!");
		}

	}

	/** Comparator for sorting Drawables by Z order */
	public static final java.util.Comparator comparator = new java.util.Comparator() {
		public int compare(Object obj, Object obj1) {
			if (obj instanceof Method == false || obj1 instanceof Method == false)
				return 0;

			Method m1 = (Method) obj;
			Method m2 = (Method) obj1;

			return m1.getName().compareToIgnoreCase(m2.getName());
		}
	};

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
