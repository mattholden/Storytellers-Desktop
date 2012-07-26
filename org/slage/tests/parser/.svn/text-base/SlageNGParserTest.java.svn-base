// $Id: SlageNGParserTest.java,v 1.1 2005/11/04 07:51:52 kevlar2 Exp $

package org.slage.tests.parser;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.slage.parser.AdjectiveNounDescription;
import org.slage.parser.DefaultBunchOfItems;
import org.slage.parser.Description;
import org.slage.parser.Parser;
import org.slage.parser.Synonym;
import org.slage.parser.WordSet;

public class SlageNGParserTest
		extends ParserTestCase {
	public void testSimpleInputWithoutKnowingVerbs() throws Exception {
		String xml = "<?xml version='1.0'?>\n" + "<p:vocabulary\n" + "	xmlns:xsi='http://www.w3.org/2001/XMLSchema-instance'\n" + "	xmlns:p='http://org.slage.parser'\n" + "	xsi:schemaLocation='http://org.slage.parser xml/vocabulary.xsd'\n" + "	parser='org.slage.parser.Parser'>\n"
				+ "	<Separator word='and'/>\n" + "   <Preposition word='at' code='AT' provideNoun='never'/>" + "</p:vocabulary>";
		InputStream in = new ByteArrayInputStream(xml.getBytes());
		Parser parser = Parser.createFromXML(in);
		DefaultBunchOfItems room = new DefaultBunchOfItems();
		Synonym rockDesc = new Synonym(Description.NEUTRAL, Description.SINGULAR, new WordSet("the"), new WordSet("big"), new WordSet(new String[] { "rock", "piece of stone" }));
		Description rock = new AdjectiveNounDescription("rock", rockDesc);
		room.placeItem(rock);
		parser.setGame(new DummyGame(room, new DefaultBunchOfItems()));
		assertParsed("Wrong text parsed", new Object[] { "LOOK", rock }, parser.parse("look rock"));
	}
}
