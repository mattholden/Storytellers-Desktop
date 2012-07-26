/*
 * DxxAbilityScore.java
 *
 * Created on November 17, 2005, 1:46 AM
 */

package org.gemini.rules.dXX;
import org.gemini.Statistic;
import org.gemini.sources.SourceSectionReference;
import org.gemini.sources.SourceReference;
import org.gemini.sources.SourceURL;


/**
 * Defines one of the core ability scores in dXX.

  <b>Licensing Notice</b><br>
   The rules defined in this file are provided under the terms of the Open Gaming 
   License v1.0a. The text of this license can be viewed 
   <a href="http://www.wizards.com/d20/files/OGLv1.0a.rtf">here</a>. 
 
 * @author  Jaeden
 */
public class DxxAbilityScore extends Statistic    
{        

	/** Source for all the ability scores */
	protected static final SourceSectionReference<SourceURL> SRD = 
            new SourceSectionReference<SourceURL>(DxxSystem.SRD35, "Basics and Ability Scores");
                    
        /** The six core ability scores of dXX */
        
        /** Strength */
        public static final DxxAbilityScore STRENGTH = 
            new DxxAbilityScore("Strength", SRD,
            "Strength measures your character’s muscle and physical power. " +
            "This ability is especially important for fighters, barbarians, " +
            "paladins, rangers, and monks because it helps them prevail in combat. " +
            "Strength also limits the amount of equipment your character can carry.");

        /** Dexterity */
        public static final DxxAbilityScore DEXTERITY = 
            new DxxAbilityScore("Dexterity", SRD,
            "Dexterity measures hand-eye coordination, agility, reflexes, and " +
            "balance. This ability is the most important one for rogues, but it’s " +
            "also high on the list for characters who typically wear light or " +
            "medium armor (rangers and barbarians) or no armor at all (monks, " +
            "wizards, and sorcerers), and for anyone who wants to be a skilled archer.");
      
       /** Constitution */
        public static final DxxAbilityScore CONSTITUTION = 
            new DxxAbilityScore("Constitution", SRD,
            "Constitution represents your character’s health and stamina. " +
            "A Constitution bonus increases a character’s hit points, so the ability " +
            "is important for all classes.");
        
        /** Intelligence */
        public static final DxxAbilityScore INTELLIGENCE = 
            new DxxAbilityScore("Intelligence", SRD,
            "Intelligence determines how well your character learns and reasons. " +
            "This ability is important for wizards because it affects how many " +
            "spells they can cast, how hard their spells are to resist, and how " +
            "powerful their spells can be. It’s also important for any character " +
            "who wants to have a wide assortment of skills.");
        
      /** Wisdom */
        public static final DxxAbilityScore WISDOM = 
            new DxxAbilityScore("Wisdom", SRD,
            "Wisdom describes a character’s willpower, common sense, perception, " +
            "and intuition. While Intelligence represents one’s ability to analyze " +
            "information, Wisdom represents being in tune with and aware of one’s " +
            "surroundings. Wisdom is the most important ability for clerics and " +
            "druids, and it is also important for paladins and rangers. If you want " +
            "your character to have acute senses, put a high score in Wisdom. " +
            "Every creature has a Wisdom score.");
        
     /** Charisma */
        public static final DxxAbilityScore CHARISMA = 
            new DxxAbilityScore("Charisma", SRD,
            "Charisma measures a character’s force of personality, persuasiveness, " +
            "personal magnetism, ability to lead, and physical attractiveness. " +
            "This ability represents actual strength of personality, not merely " +
            "how one is perceived by others in a social setting. Charisma is most " +
            "important for paladins, sorcerers, and bards. It is also important " +
            "for clerics, since it affects their ability to turn undead. Every " +
            "creature has a Charisma score.");
        
        
     
    /** Creates a new instance of DxxAbilityScore 
        @param _name Name of the ability score 
        @param _src Source of the ability score
        @param _desc Description of the ability score */
    public DxxAbilityScore(String _name, SourceReference<?> _src, String _desc) 
    {
        super(_name, _src, _desc);
    }
    
    
    /** Get the Ability Score Modifier for the given value     
     @param _value the value of the ability score
     @return the  Ability Modifier for a particular value */
    public static int getModifier(int _value)
    {
        /* Subtract 10 to get to the "Zero bonus".
           >>, then <<, to ensure an even number.
           To get the final bonus, >> 1 (divide by 2, so we increment by odd numbers)
           Sorry for the readability thing, but >> 1 is faster and we use ability
           bonus a LOT!

           This is equivalent, more readable (but much slower) code to do the same job:
           return (_value - (_value % 2) - 10) / 2;
         */

        return ((((_value) >> 1) << 1)  - 10)  >> 1;
    }

        
      
}
