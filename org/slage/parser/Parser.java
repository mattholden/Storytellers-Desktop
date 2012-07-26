// $Id: Parser.java,v 1.1 2005/11/04 07:52:19 kevlar2 Exp $

package org.slage.parser;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.Iterator;
import java.util.Vector;

import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.JDOMParseException;
import org.jdom.input.SAXBuilder;
import org.slage.command.Command;
import org.slage.command.Commander;

/**
 * General purpose natural language adventure game parser. Simplifies typed
 * input for greater flexibility.
 * 
 * <p>
 * In the game use the Parser like this:<br>
 * 
 * <pre>
 * Parser parser = Parser.createFromXML(XMLVocabulary);
 * while (gameIsGoing()) {
 * 	try {
 * 		Command parsed = null;
 * 		if (parser.hasInput()) {
 * 			parsed = parser.parseToCommand();
 * 		} else {
 * 			String input = game.keyboard.getSomeUserInput();
 * 			parsed = parser.parseToCommand(input);
 * 		}
 * 		if (!parsed.isEmpty()) {
 * 			// execute command...
 * 			if (justRestoredGame()) {
 * 				parser.discardInput();
 * 			}
 * 		}
 * 	} catch (ParserException pe) {
 * 		// display exception detail message to player
 * 		if (!pe instanceof WordRepeatedException) {
 * 			parser.discardInput();
 * 		}
 * 	}
 * }
 * </pre>
 * 
 * </p>
 * 
 * <p>
 * The Parser is exception-safe; after an Exception is thrown you can always
 * call feed new input. Previous input is likely to be unusable, though, so do
 * not call <code>parseToCommand()</code>, unless the thrown exception is a
 * {@link WordRepeatedException}, after which retrying is explicitly allowed.
 * The parser is <em>not</em> thread-safe and should not be shared between
 * threads.
 * </p>
 * 
 * <p>
 * To create parsers for different languages, provide sensible vocabulary to the
 * <code>createFromXML</code> factory method. If the new language requires
 * additional or changed behavior, see the {@link GermanParser} for such
 * language specific functionality.
 * </p>
 * 
 * @author <a href="mailto:robert.wenner@gmx.de">Robert Wenner</a>
 */
public class Parser implements Commander {
	private Tokenizer tokenizer;
	private Collection prepositions;
	private Collection pronouns;
	private Collection separators;
	private History history;
	private Tokenizer.WordSimplifier theWordSimplifier;
	private Vector leftOver;
	private org.slage.SlageGame game;

	/**
	 * Factory method to create a Parser instance from the given XML vocabulary.
	 * 
	 * <p>
	 * The XML input for creating a Parser serves two purposes: a) determining
	 * what Parser should be created (i.e. for what language), and b) providing
	 * basic vocabulary. The XML vocabulary consists of word descriptions, which
	 * are (personal) Pronouns (&quot;it&quot;), Prepositions (&quot;under&quot;),
	 * and Separators (&quot;and&quot;).
	 * </p>
	 * 
	 * <p>
	 * For example:
	 * 
	 * <pre>
	 *       &lt;?xml version=';1.0';?&gt;
	 *       &lt;vocabulary parser=';org.slage.parser.EnglishParser';&gt;
	 *               &lt;Separator word=';and';/&gt;
	 *               &lt;Preposition word=';at'; code=';AT'; provideNoun=';never';/&gt;
	 *               &lt;Pronoun word=';him'; number=';singular'; gender=';male';/&gt;
	 *               &lt;Pronoun word =';her'; number=';singular'; gender=';female';/&gt;
	 *               &lt;Pronoun word=';it'; number=';singular'; gender=';neutral';/&gt;
	 *       &lt;/vocabulary&gt;
	 * </pre>
	 * 
	 * </p>
	 * 
	 * <p>
	 * The main entity is <code>vocabulary</code>, which has a mandatory
	 * attribute for the Parser to use with this vocabulary. The vocabulary
	 * <em>must</em> have at least one separator. Each word has a code except
	 * pronouns, which instead have a gender (gender) and a number (singular or
	 * plural), and separators, which do not require further information. Words
	 * with the same meaning have the same code. The provideNoun attribute to a
	 * Preposition is mandatory and means that the Parser should always, never, or
	 * unlessGiven provide the last noun to the preposition. This allows commands
	 * like &quot;open box and look inside&quot;, where &quot;inside&quot; would
	 * have provideNoun set to unlessGiven.
	 * </p>
	 * 
	 * <p>
	 * Nouns are not included in the vocabulary, but rather provided by the game
	 * objects. Verbs are not handled implicitly, but are passed on to the game
	 * objects to deal with them.
	 * </p>
	 * 
	 * <p>
	 * See the <a href="vocabulary.xsd">vocabulary schema</a> for details.
	 * </p>
	 * 
	 * @param input providing XML input to create a new Parser from.
	 * @return concrete Parser for the given vocabulary.
	 * @throws IOException on reading from the given input.
	 * @throws IllegalArgumentException if <code>input</code> is
	 *         <code>null</code>.
	 * @throws JDOMParseException on syntactical problems with the provided XML
	 *         input (usually schema problems).
	 * @throws JDOMException on other problems with the provided XML input.
	 * @throws ClassNotFoundException if a bad parser was specified in the XML
	 *         vocabulary (i.e. the class was not found).
	 * @throws ClassCastException if a bad parser was specified in the XML
	 *         vocabulary (i.e. the specified class was found, but it was no
	 *         Parser).
	 * @throws InvocationTargetException on problems with reflective access to
	 *         Parser or vocabulary.
	 * @throws NoSuchMethodException if the specified parser does not support the
	 *         expected constructor; this is most likely a programming error by
	 *         the parser class author.
	 * @throws InstantiationException on problems creating a vocabulary / word
	 *         class, this may be due to malformed or incomplete XML.
	 * @throws IllegalAccessException on errors accessing words; this is most
	 *         likely a programming error in the Parser package.
	 */
	public static Parser createFromXML(InputStream input) throws IOException, JDOMException, JDOMParseException, ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException {
		SAXBuilder xmlParser = new SAXBuilder(true);
		// If you get something like a org.jdom.JDOMException here that says the
		// http://apache.org/xml/features/validation/warn-on-duplicate-attdef
		// feature is not recognized, you probably use an old / poor XML Parser,
		// like Crimson (which is in hibernation since 2000). Download Apache
		// Xerces from http://xml.apache.org/xerces2-j/download.cgi
		xmlParser.setFeature("http://xml.org/sax/features/namespaces", true);
		xmlParser.setFeature("http://xml.org/sax/features/validation", true);
		xmlParser.setFeature("http://apache.org/xml/features/validation/schema", true);
		// The features below are not necessary to make the tests pass, but look
		// nice to have, since they may detect errors in vocabularies early.
		xmlParser.setFeature("http://apache.org/xml/features/validation/schema-full-checking", true);
		xmlParser.setFeature("http://apache.org/xml/features/validation/warn-on-duplicate-attdef", true);
		// The feature below was removed, since it does not seem to be supported
		// in Xerces 2.6 (however, it was supported in earlier versions).
		// xmlParser.setFeature("http://apache.org/xml/features/validation/warn-on-undeclared-elemdef",
		// true);
		xmlParser.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
		xmlParser.setIgnoringElementContentWhitespace(true);
		org.jdom.Document dom = xmlParser.build(input);
		String wantedParser = dom.getRootElement().getAttribute("parser").getValue();
		Class parserClass = Class.forName(wantedParser);
		Constructor ctor = parserClass.getConstructor();
		Parser sq7parser = (Parser) ctor.newInstance();
		sq7parser.initializeVocabulary(dom);
		return sq7parser;
	}

	/**
	 * Factory method to create a Parser instance from the given XML vocabulary.
	 * 
	 * <p>
	 * The XML input for creating a Parser serves two purposes: a) determining
	 * what Parser should be created (i.e. for what language), and b) providing
	 * basic vocabulary. The XML vocabulary consists of word descriptions, which
	 * are (personal) Pronouns (&quot;it&quot;), Prepositions (&quot;under&quot;),
	 * and Separators (&quot;and&quot;).
	 * </p>
	 * 
	 * <p>
	 * For example:
	 * 
	 * <pre>
	 *       &lt;?xml version=';1.0';?&gt;
	 *       &lt;vocabulary parser=';org.slage.parser.EnglishParser';&gt;
	 *               &lt;Separator word=';and';/&gt;
	 *               &lt;Preposition word=';at'; code=';AT'; provideNoun=';never';/&gt;
	 *               &lt;Pronoun word=';him'; number=';singular'; gender=';male';/&gt;
	 *               &lt;Pronoun word =';her'; number=';singular'; gender=';female';/&gt;
	 *               &lt;Pronoun word=';it'; number=';singular'; gender=';neutral';/&gt;
	 *       &lt;/vocabulary&gt;
	 * </pre>
	 * 
	 * </p>
	 * 
	 * <p>
	 * The main entity is <code>vocabulary</code>, which has a mandatory
	 * attribute for the Parser to use with this vocabulary. The vocabulary
	 * <em>must</em> have at least one separator. Each word has a code except
	 * pronouns, which instead have a gender (gender) and a number (singular or
	 * plural), and separators, which do not require further information. Words
	 * with the same meaning have the same code. The provideNoun attribute to a
	 * Preposition is mandatory and means that the Parser should always, never, or
	 * unlessGiven provide the last noun to the preposition. This allows commands
	 * like &quot;open box and look inside&quot;, where &quot;inside&quot; would
	 * have provideNoun set to unlessGiven.
	 * </p>
	 * 
	 * <p>
	 * Nouns are not included in the vocabulary, but rather provided by the game
	 * objects. Verbs are not handled implicitly, but are passed on to the game
	 * objects to deal with them.
	 * </p>
	 * 
	 * <p>
	 * See the <a href="vocabulary.xsd">vocabulary schema</a> for details.
	 * </p>
	 * 
	 * @param elem XML element to build from
	 * @return a parser of the requested type
	 * 
	 * @throws IllegalArgumentException if <code>input</code> is
	 *         <code>null</code>.
	 * @throws ClassNotFoundException if a bad parser was specified in the XML
	 *         vocabulary (i.e. the class was not found).
	 * @throws ClassCastException if a bad parser was specified in the XML
	 *         vocabulary (i.e. the specified class was found, but it was no
	 *         Parser).
	 * @throws InvocationTargetException on problems with reflective access to
	 *         Parser or vocabulary.
	 * @throws NoSuchMethodException if the specified parser does not support the
	 *         expected constructor; this is most likely a programming error by
	 *         the parser class author.
	 * @throws InstantiationException on problems creating a vocabulary / word
	 *         class, this may be due to malformed or incomplete XML.
	 * @throws IllegalAccessException on errors accessing words; this is most
	 *         likely a programming error in the Parser package.
	 */
	public static Parser createFromXML(org.jdom.Element elem) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException {

		String wantedParser = elem.getAttribute("parser").getValue();
		Class parserClass = Class.forName(wantedParser);
		Constructor ctor = parserClass.getConstructor();
		Parser sq7parser = (Parser) ctor.newInstance();
		sq7parser.initializeVocabulary(elem);
		return sq7parser;
	}

	/**
	 * Creates a new parser. Do <em>not</em> use this constructor, but do use
	 * the creation method <code>createFromXML</code> instead.
	 * 
	 * @see #createFromXML(InputStream)
	 */
	public Parser() {
		// Don't remove this constructor, even if Eclipse thinks it's never
		// used. The creation method calls it via reflection. For that reason
		// it also has to be public.
		this(null);
	}

	/**
	 * Creates a new parser with the given WordSimplifier. You probably don't want
	 * to use the Parser until you've <code>enter</code>ed a room.
	 * 
	 * @param ws WordSimplifier to call for each word, may be <code>null</code>
	 *        to do no word based simplifications. Upper/lower case transformation
	 *        is done automatically, so you don't need a WordSimplifier for that.
	 */
	protected Parser(Tokenizer.WordSimplifier ws) {
		tokenizer = null;
		prepositions = new Vector();
		separators = new Vector();
		pronouns = new Vector();
		history = new History();
		theWordSimplifier = ws;
		leftOver = new Vector();
	}

	private void initializeVocabulary(org.jdom.Document vocabulary) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException {
		Iterator i = vocabulary.getRootElement().getChildren().iterator();
		while (i.hasNext()) {
			Element node = (Element) i.next();
			String name = node.getName();
			if ("#text".equals(name)) {
				continue;
			}
			Class wordClass = Class.forName("org.slage.parser." + name);
			Constructor ctor = wordClass.getConstructor(new Class[] { Element.class });
			Object word = ctor.newInstance(new Object[] { node });
			// Unfortunately, we can not use polymorphism here, with methods like
			// addToVocabulary(Pronoun), and so on. Java insists that word is an
			// Object and calls only addToVocabulary(Object), which is useless.
			if (name.equals("Preposition")) {
				prepositions.add(word);
			} else if (name.equals("Pronoun")) {
				pronouns.add(word);
			} else if (name.equals("Separator")) {
				separators.add(word);
			}
		}
	}

	private void initializeVocabulary(org.jdom.Element vocabulary) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException {
		Iterator i = vocabulary.getChildren().iterator();
		while (i.hasNext()) {
			Element node = (Element) i.next();
			String name = node.getName();
			if ("#text".equals(name)) {
				continue;
			}
			Class wordClass = Class.forName("org.slage.parser." + name);
			Constructor ctor = wordClass.getConstructor(new Class[] { Element.class });
			Object word = ctor.newInstance(new Object[] { node });
			// Unfortunately, we can not use polymorphism here, with methods like
			// addToVocabulary(Pronoun), and so on. Java insists that word is an
			// Object and calls only addToVocabulary(Object), which is useless.
			if (name.equals("Preposition")) {
				prepositions.add(word);
			} else if (name.equals("Pronoun")) {
				pronouns.add(word);
			} else if (name.equals("Separator")) {
				separators.add(word);
			}
		}
	}

	/**
	 * Parse given input. If input from previous calls to any <code>parse</code>
	 * call isn't processed yet, it is silently discarded.
	 * 
	 * @param typedInput as entered by the player.
	 * @return parsed (simplified) command.
	 * @throws WordRepeatedException if a word is repeated in the input. The
	 *         duplicate word is removed from the input and one can safely call
	 *         <code>parse()</code> to work on the input as if no word was
	 *         repeated.
	 * @throws NoSuchStuffException if nothing fitting the given (adjective(s)
	 *         and) noun(s) could be found in the current room and the player's
	 *         inventory.
	 * @throws AmbiguousInputException if (at the end of the input) any found
	 *         Description is ambiguous.
	 * @throws ParserException not actually thrown, just subclasses are used.
	 * @throws AmbiguousPronounException if the parser did not know what a pronoun
	 *         referred to.
	 * @throws NullPointerException if called before entering a room and the input
	 *         is more than a single word.
	 * @see #parseToCommand()
	 * @see #hasInput()
	 */
	public Command parseToCommand(String typedInput) throws ParserException {
		return new Command(typedInput, parse(typedInput), this);
	}

	/**
	 * Parse given input. If input from previous calls to
	 * <code>parse(String)</code> isn't processed yet, it is silently discarded.
	 * 
	 * @param input as entered by the player.
	 * @return parsed (simplified) input consisting of either a verb with one or
	 *         more Description(s), or an empty collection. Returning an empty
	 *         collection does not mean there is no further input to parse (call
	 *         <code>hasInput</code> to check that).
	 * @throws WordRepeatedException if a word is repeated in the input. The
	 *         duplicate word is removed from the input and one can safely call
	 *         <code>parse()</code> to work on the input as if no word was
	 *         repeated.
	 * @throws NoSuchStuffException if nothing fitting the given (adjective(s)
	 *         and) noun(s) could be found in the current room.
	 * @throws AmbiguousInputException if (at the end of the input) any found
	 *         Description is ambiguous.
	 * @throws ParserException not actually thrown, just subclasses are used.
	 * @throws AmbiguousPronounException if the parser did not know what a pronoun
	 *         referred to.
	 * @throws NullPointerException if called before entering a room and the input
	 *         is more than a single word.
	 * @see #parse()
	 * @see #hasInput()
	 */
	public Vector parse(String input) throws ParserException {
		Separator defaultSeparator = (Separator) separators.toArray()[0];
		tokenizer = new Tokenizer(defaultSeparator.getWord(), input, theWordSimplifier);
		tokenizer.append(leftOver);
		return parseIt();
	}

	private Vector parseIt() throws ParserException {
		history.ageLastNouns();
		tokenizer.mark();
		Vector parsed = new Vector();
		while (tokenizer.hasNext()) {
			try {
				Description nextNoun = parseNextNoun();
				if (!hasVerb(parsed)) {
					setVerb(parsed, history.recallVerb());
				}
				addNoun(parsed, nextNoun);
			} catch (WordRepeatedException wordRepeated) {
				tokenizer.reset();
				history.unAgeLastNouns();
				throw wordRepeated;
                        } catch (AmbiguousInputException ambig) {
                          if (hasVerb(parsed))
                              ambig.setVerb(parsed.get(0).toString()); 
                              throw ambig;                        
			} catch (NoSuchStuffException currentWordIsNoNoun) {
				String nextWord = tokenizer.next();
				if (isSentenceSeparator(nextWord)) {
					return parsed;
				} else if (isPreposition(nextWord)) {
					if (!hasVerb(parsed)) {
						setVerb(parsed, history.recallVerb());
					}
					Preposition prep = getPreposition(nextWord);
					addPreposition(parsed, prep.getCode());
					if (shouldProvideNoun(parsed, prep)) {
						String lastNoun = history.recallLastNoun();
						if (lastNoun == null) {
							throw new MissingWordException(nextWord);
						}
						tokenizer.insertText(lastNoun);
					}
				} else if (isPronoun(nextWord)) {
					tokenizer.insertText(history.getNounForPronoun(pronouns, nextWord));
				} else if (!hasVerb(parsed)) {
					String theVerb = nextWord.toUpperCase();
					setVerb(parsed, theVerb);
					history.rememberVerb(theVerb);
				} else {
					// No idea what that word could be; report it as "there is no ..."
					// here.
					throw currentWordIsNoNoun;
				}
			}
		}
		history.forgetVerb();
		return parsed;
	}

	private void addPreposition(Vector parsed, String code) {
		parsed.add(code);
	}

	private void addNoun(Vector parsed, Description nextNoun) {
		parsed.add(nextNoun);
	}

	private void setVerb(Vector parsed, String verb) {
		parsed.add(verb);
	}

	private boolean hasVerb(Vector parsed) {
		return !parsed.isEmpty();
	}

	private Object getWord(Collection c, String word) {
		for (Iterator iter = c.iterator(); iter.hasNext();) {
			Object o = iter.next();
			if (o.equals(word)) {
				return o;
			}
		}
		return null;
	}

	private boolean shouldProvideNoun(Vector parsed, Preposition prep) throws WordRepeatedException {
		return prep.alwaysProvideNoun() || (prep.canProvideNoun() && isEndOfSentence() && inputHasNoNounYet(parsed));
	}

	private boolean inputHasNoNounYet(Vector parsed) {
		Iterator iter = parsed.iterator();
		while (iter.hasNext()) {
			if (iter.next() instanceof Description) {
				return false;
			}
		}
		return true;
	}

	private boolean isEndOfSentence() throws WordRepeatedException {
		if (tokenizer.hasNext()) {
			String nextWord = tokenizer.next();
			tokenizer.goBack(1);
			return isSentenceSeparator(nextWord);
		}
		return true;
	}

	private boolean isPreposition(String word) {
		return getWord(prepositions, word) != null;
	}

	private Preposition getPreposition(String nextWord) {
		return (Preposition) getWord(prepositions, nextWord);
	}

	private boolean isPronoun(String word) {
		return getWord(pronouns, word) != null;
	}

	private boolean isSentenceSeparator(String word) {
		return getWord(separators, word) != null;
	}

	private Description parseNextNoun() // TODO this shouldn't throw
			// NoSuchStuffException but return an
			// empty collection?
			throws AmbiguousInputException, WordRepeatedException, NoSuchStuffException {
		BestMatch bestMatch = mergeMatchesFromRoomAndInventory();
		leftOver.clear();
		if (bestMatch.size() == 0 || bestMatch.getScore() < Description.SCORE_NOUN) {
			int grokedWords = bestMatch.size() == 0 ? 1 : bestMatch.getWordCount();
			throw new NoSuchStuffException(tokenizer.toCollection(grokedWords));
		}
		if (bestMatch.size() == 1 && bestMatch.getScore() >= Description.SCORE_NOUN) {
			Description matchingStuff = bestMatch.getStuff();
			tokenizer.goAhead(bestMatch.getWordCount());
			history.rememberLastNoun(bestMatch.getText(), bestMatch.getGender(), bestMatch.getNumber());
			return matchingStuff;
		}
		leftOver.addAll(tokenizer.toCollection());
		for (int i = 0; i < bestMatch.getWordCount(); i++) {
			leftOver.remove(0);
		}
		Collection ambiguous = tokenizer.toCollection(bestMatch.getWordCount());
		throw new AmbiguousInputException(ambiguous, bestMatch.asCollection());
	}

	private BestMatch mergeMatchesFromRoomAndInventory() throws WordRepeatedException {
		BestMatch bestMatch = new BestMatch();
		BunchOfItems currentRoom = game.getRoomForParser();
		bestMatch.addAll(currentRoom.getBestMatch(tokenizer));
		BunchOfItems inventory = game.getInventoryForParser();
		bestMatch.addAll(inventory.getBestMatch(tokenizer));
		return bestMatch;
	}

	/**
	 * Checks whether this parser is not yet done with the last input. Call
	 * <code>parse()</code> to process input leftovers.
	 * 
	 * @return whether this Parser still has input.
	 */
	public boolean hasInput() {
		return tokenizer != null && tokenizer.hasNext();
	}

	/**
	 * Continue parsing input.
	 * 
	 * @return parsed input (command).
	 * @throws ParserException on any problems parsing the input.
	 * @throws IllegalStateException if no remaining input from previous parse
	 *         calls is available.
	 * @see #hasInput()
	 */
	public Vector parse() throws ParserException {
		makeSureWeHaveInput();
		return parseIt();
	}

	public Command parseToCommand() throws ParserException {
		makeSureWeHaveInput();
		return new Command(tokenizer.toString(), parse(), this);
	}

	private void makeSureWeHaveInput() {
		if (!hasInput()) {
			throw new IllegalStateException("Parser does not have input to parse.");
		}
	}

	/**
	 * Discards not handled input the Parser may have. Call this after restoring a
	 * game, to avoid the player typing &quot;open box and look inside&quot;, then
	 * dying on opening the box (maybe trapped?), and restoring, and then having
	 * the Parser chew on the &quot;look inside box&quot; next time.
	 */
	public void discardInput() {
		tokenizer = null;
	}

	public void setGame(org.slage.SlageGame theGame) {
		this.game = theGame;
	}

	public org.slage.SlageGame getGame() {
		return game;
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
