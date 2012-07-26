// $Id: Synonym.java,v 1.1 2005/11/04 07:52:19 kevlar2 Exp $

package org.slage.parser;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.jdom.Element;

/**
 * Encapsulates a set of descriptive articles, adjectives and nouns for one
 * Description.
 * 
 * <p>
 * This class isn't really required for an English parser, but for other
 * languages, which do differentiate genders in their nouns and adjectives. For
 * example, in German a box may be &quot;kiste&quot; (female) as well as
 * &quot;kasten&quot; (male), in Spanish a bottle of red wine may be
 * &quot;botella de vino tinto&quot; (female) or shorter just red wine --
 * &quot;vino tinto&quot; (male). If Description had only one gender and a bunch
 * of nouns and adjectives, players could build gramatically ugly input by
 * combining male nouns with female adjectives. While we could live with this,
 * without Synonyms each Description only had one gender. In the German example
 * the game designer would have to decide whether the box is male or female.
 * Assume it is male. Now the player uses the female word. This works, but the
 * parser stores the noun as last used male noun, because the box was chosen to
 * be male. The next input with a male personal pronoun will refer to the box,
 * while a female pronoun will be rejected. This is very confusing, after all
 * the player used a female word and is right to expect the parser to substitute
 * the female pronoun!
 * </p>
 * 
 * @author <a href="mailto:robert.wenner@gmx.de">Robert Wenner</a>
 */
public class Synonym implements Serializable {
	private int theGender;
	private int theNumber;
	private WordSet theArticles;
	private WordSet theAdjectives;
	private WordSet theNouns;

	/**
	 * Creates a new Synonym. Simplified version for English - Assumes NEUTRAL
	 * gender and SINGULAR number.
	 * 
	 * @param articles possible articles, may be <code>null</code>.
	 * @param adjectives possible adjectives, may be <code>null</code>.
	 * @param nouns possible nouns.
	 * @throws IllegalArgumentException if the gender is invalid or no nouns are
	 *         given or the nouns do not include at least one OneWordTerm.
	 */
	public Synonym(WordSet articles, WordSet adjectives, WordSet nouns) {
		this(Description.NEUTRAL, Description.SINGULAR, articles, adjectives, nouns);
	}

	/**
	 * Creates a new Synonym.
	 * 
	 * @param gender one of <code>Description.NEUTRAL</code>,<code>Description.MALE</code>,
	 *        or <code>Description.FEMALE</code>.
	 * @param number one of <code>Description.SINGULAR</code> or
	 *        <code>Description.PLURAL</code>.
	 * @param articles possible articles, may be <code>null</code>.
	 * @param adjectives possible adjectives, may be <code>null</code>.
	 * @param nouns possible nouns.
	 * @throws IllegalArgumentException if the gender is invalid or no nouns are
	 *         given or the nouns do not include at least one OneWordTerm.
	 */
	public Synonym(int gender, int number, WordSet articles, WordSet adjectives, WordSet nouns) {
		if (gender != Description.NEUTRAL && gender != Description.MALE && gender != Description.FEMALE) {
			throw new IllegalArgumentException("Invalid gender");
		}
		if (number != Description.SINGULAR && number != Description.PLURAL) {
			throw new IllegalArgumentException("Invalid number");
		}
		nouns.checkAsNoun();
		theGender = gender;
		theNumber = number;
		theArticles = articles == null ? new WordSet() : articles;
		theAdjectives = adjectives == null ? new WordSet() : adjectives;
		theNouns = nouns;
		theNouns.checkAsNoun();
	}

	public int getGender() {
		return theGender;
	}

	public int getNumber() {
		return theNumber;
	}

	public int countMatchingArticles(Tokenizer input) throws WordRepeatedException {
		return theArticles.scoreFor(input);
	}

	public int countMatchingAdjectives(Tokenizer input) throws WordRepeatedException {
		return theAdjectives.scoreFor(input);
	}

	public int countMatchingNouns(Tokenizer input) throws WordRepeatedException {
		return theNouns.scoreFor(input);
	}

	/**
	 * Queries whether the last match for a noun phrase was a complete noun
	 * description.
	 * 
	 * @return <code>true</code> if the last check found a complete noun phrase,
	 *         <code>false</code> if the last words checked did not complete a
	 *         noun phrase or no noun checking attempt was made.
	 */
	public boolean wasComplete() {
		return theNouns.wasComplete();
	}

	/**
	 * Get XML element
	 * 
	 * @return XML element
	 */
	public Element getXMLElement() {
		return getXMLElement("descriptor");
	}

	/**
	 * Get XML element
	 * 
	 * @param strRootElementTag what to call the tag
	 * @return XML element
	 */
	public Element getXMLElement(String strRootElementTag) {
		Element el = new Element(strRootElementTag);

		el.setAttribute("gender", getGenderString());
		el.setAttribute("quantity", getQuantityString());

		Element[] eArts = theArticles.getXMLElements("article");
		for (int i = 0; i < eArts.length; i++)
			el.addContent(eArts[i]);
		Element[] eAdj = theAdjectives.getXMLElements("adjective");
		for (int i = 0; i < eAdj.length; i++)
			el.addContent(eAdj[i]);
		Element[] eNoun = theNouns.getXMLElements("synonym");
		for (int i = 0; i < eNoun.length; i++)
			el.addContent(eNoun[i]);

		return el;
	}

	/**
	 * Return the quantity as a string.
	 * 
	 * @return &quot;singular&quot; or &quot;plural&quot;
	 */
	public String getQuantityString() {
		return (theNumber == Description.PLURAL) ? "plural" : "singular";
	}

	/**
	 * Return the gender as a string.
	 * 
	 * @return &quot;male&quot;, &quot;female&quot; or &quot;neutral&quot;
	 */
	public String getGenderString() {
		if (getGender() == Description.MALE) {
			return "male";
		} else if (getGender() == Description.FEMALE) {
			return "female";
		} else if (getGender() == Description.NEUTRAL) {
			return "neutral";
		}
		throw new IllegalStateException("Invalid gender");
	}

	/**
	 * Construct a Synonym from XML
	 * 
	 * @param elem XML element to construct from
	 */
	public Synonym(org.jdom.Element elem) {
		ArrayList adjectives = new ArrayList();
		ArrayList synonyms = new ArrayList();
		ArrayList articles = new ArrayList();

		// First, articles...
		List listArt = elem.getChildren("article", elem.getNamespace());
		if (listArt.size() != 0) {
			for (int i = 0; i < listArt.size(); i++) {
				articles.add(((Element) listArt.get(i)).getAttributeValue("value"));
			}
		}
		// default to "a" and "the" if none given
		else {
			articles.add("a");
			articles.add("an");
			// TODO: Don't generate default articles! Synonyms are
			// language-independent!
			// Providing these should go in the editor, not in the game.
			articles.add("the");
		}

		// adjectives
		List listAdj = elem.getChildren("adjective", elem.getNamespace());
		for (int i = 0; i < listAdj.size(); i++) {
			adjectives.add(((Element) listAdj.get(i)).getAttributeValue("value"));
		}

		// synonyms
		List listSyn = elem.getChildren("synonym", elem.getNamespace());
		for (int i = 0; i < listSyn.size(); i++) {
			synonyms.add(((Element) listSyn.get(i)).getAttributeValue("value"));
		}

		// gender
		String strGender = elem.getAttributeValue("gender");
		if (strGender == null) {
			theGender = Description.NEUTRAL;
		} else if (strGender.equals("female")) {
			theGender = Description.FEMALE;
		} else if (strGender.equals("male")) {
			theGender = Description.MALE;
		} else {
			theGender = Description.NEUTRAL;
		}

		// plurality
		String strPlural = elem.getAttributeValue("quantity");
		if (strPlural == null)
			theNumber = Description.SINGULAR;
		else
			theNumber = strPlural.equals("plural") ? Description.PLURAL : Description.SINGULAR;

		// that hideous toArray() turns a List into a String array
		// TODO Why not use adjectives.toArray()?
		// it returns an array of objects, we need array of strings
		// maybe generics fixes this?
		theNouns = new WordSet((String[]) synonyms.toArray(new String[] {}));
		theArticles = new WordSet((String[]) articles.toArray(new String[] {}));
		theAdjectives = new WordSet((String[]) adjectives.toArray(new String[] {}));
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
