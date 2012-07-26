/*
 * DxxCombatStatistic.java
 *
 * Created on November 17, 2005, 1:46 AM
 */

package org.gemini.rules.dXX;
import org.gemini.Statistic;
import org.gemini.sources.SourceSectionReference;
import org.gemini.sources.SourceReference;
import org.gemini.sources.SourceURL;


/**
 * Defines one of the combat-related statistics (attack bonus, etc.) in dXX.

  <b>Licensing Notice</b><br>
   The rules defined in this file are provided under the terms of the Open Gaming 
   License v1.0a. The text of this license can be viewed 
   <a href="http://www.wizards.com/d20/files/OGLv1.0a.rtf">here</a>. 
 
 * @author  Jaeden
 */
public class DxxCombatStatistic extends Statistic    
{   
         
	/** Source for all the ability scores */
	protected static final SourceSectionReference<SourceURL> SRD = 
            new SourceSectionReference<SourceURL>(DxxSystem.SRD35, "Combat I");
     
    /** Defines the base attack bonus */
    public static final DxxCombatStatistic BASEATTACKBONUS = new DxxCombatStatistic
        ("Base Attack Bonus", SRD, "A character's Base Attack Bonus is added to all physical attacks he or she attempts.");

    /** Melee attack (Provided separate from BAB to allow for different bonuses) */ 
    public static final DxxCombatStatistic MELEEATTACK = new DxxCombatStatistic
        ("Melee Attack", SRD, "Your attack bonus with a melee weapon is: Base attack bonus + Strength modifier + size modifier.");

    /** Ranged attack (Provided separate from BAB to allow for different bonuses) */ 
    public static final DxxCombatStatistic RANGEDATTACK = new DxxCombatStatistic
        ("Ranged Attack", SRD, " With a ranged weapon, your attack bonus is: Base attack bonus + Dexterity modifier + size modifier + range penalty.");
    
    /** Grapple attack */
    public static final DxxCombatStatistic GRAPPLEATTACK = new DxxCombatStatistic
       ("Grapple",  new SourceSectionReference<SourceURL>(DxxSystem.SRD35, "Combat II"),
       "Repeatedly in a grapple, you need to make opposed grapple checks against an opponent. " +
       "A grapple check is like a melee attack roll. Your attack bonus on a grapple check is: " +
       "Base attack bonus + Strength modifier + special size modifier."); 

    /** Initiative (bonus - rolls will obviously be redone as appropriate) */
    public static final DxxCombatStatistic INITIATIVE = new DxxCombatStatistic
        ("Initiative", SRD, 
        "At the start of a battle, each combatant makes an initiative check. An initiative check is a Dexterity check. " +
        "Each character applies his or her Dexterity modifier to the roll. Characters act in order, counting down from highest result to lowest. " +
        "In every round that follows, the characters act in the same order (unless a character takes an action that results in his or her initiative " +
        "changing; see Special Initiative Actions). If two or more combatants have the same initiative check result, the combatants who are tied " +
        "act in order of total initiative modifier (highest first). If there is still a tie, the tied characters should roll again to determine which one of them " +
        "goes before the other.");
     
   /** Armor class */
    public static final DxxCombatStatistic ARMORCLASS = new DxxCombatStatistic
        ("Armor Class", SRD, 
        " Your Armor Class (AC) represents how hard it is for opponents to land a solid, damaging blow on you. It’s the attack roll result that an opponent needs to achieve to hit you. Your AC is equal to the following: " +
        "10 + armor bonus + shield bonus + Dexterity modifier + size modifier. " +
        "Note that armor limits your Dexterity bonus, so if you’re wearing armor, you might not be able to apply your whole Dexterity bonus to your AC."+
        "Sometimes you can’t use your Dexterity bonus (if you have one). If you can’t react to a blow, you can’t use your Dexterity bonus to AC. (If you don’t have a Dexterity bonus, nothing happens.)");
    

   
    /** Creates a new instance of DxxCombatStatistic 
        @param _name Name of the statistic 
        @param _src Source of the statistic
        @param _desc Description of the statistic */
    public DxxCombatStatistic(String _name, SourceReference<?> _src, String _desc) 
    {
        super(_name, _src, _desc);
    }    
      
   
    
}
