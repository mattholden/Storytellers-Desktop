/*
 * DxxFantasyCreatureOrganization.java
 *
 * Created on November 13, 2005, 10:38 PM
 */

package org.gemini.rules.dXXfantasy;
import org.slage.framework.NamedObject;
import org.slage.framework.LinkedObject;
import java.io.Serializable;
import org.gemini.rules.dXX.DxxCreature;
import org.gemini.rules.dXX.DxxCreatureType;
import org.gemini.rules.dXX.DxxCharacter;

import java.util.ArrayList;

/**
 * Defines a potential Creature organization. 
 * A DxxFantasyCreature will store a list of these.
 
  <b>Licensing Notice</b><br>
   The rules defined in this file are provided under the terms of the Open Gaming 
   License v1.0a. The text of this license can be viewed 
   <a href="http://www.wizards.com/d20/files/OGLv1.0a.rtf">here</a>. 
 
 
 * TODO generate bands
 *
 * @author  Jaeden
 */
public class DxxFantasyCreatureOrganization
    <CreatureType extends DxxCreature,  // TODO DxxFantasyCreature
      CharType extends DxxCharacter>    // TODO DxxFantasyCharacter
    implements NamedObject, Serializable, LinkedObject<CreatureType>
{
    
 /** Name of the organization */
    private String name;
        
    /** Creature we belong to */
    private CreatureType creature;
        
    /** Minimum appearing */
    private int minimum;
    
    /** Maximum appearing */
    private int maximum;
    
    /** Percentage of noncombatants */
    private float noncombatants = 0.0f;
    
    /** List of leaders */
    private ArrayList<OrganizationSpecialCharacter<CharType>> special =
        new ArrayList<OrganizationSpecialCharacter<CharType>>();
    
    /** Accessor for minimum 
      @return minimum */
   public int getMinimum() { return minimum; }
   
   /** Accessor for maximum  
      @return maximum */
   public int getMaximum() { return maximum; }
   
   /** Accessor for noncombatant percentage 
       @return noncombatants */
    public float getNonCombatantPercentage() { return noncombatants; }
    
    /** Set minimum value
     * @param _min minimum appearing */
    public void setMinimum(int _min) { minimum = _min; }
    
    /** Set maximum value
     * @param _max maximum appearing */
    public void setMaximum(int _max) { maximum = _max; }
    
    /** Set noncombatant percentage
      @param _non nomcombatant % */
    public void setNonCombatantPercentage(float _non) { noncombatants = _non; }
    
    /** Add a special character accompanying the band
      @param _char accompanying charagcter
      @param _needed How many combatants are needed to generate this creature(s)
      @param _genMin minimum to generate if _needed is reached 
      @param _genMax maximum to generate if _needed is reached */
    public void addSpecialCharacter(CharType _char, int _needed, int _genMin, int _genMax)
    {
        special.add(new OrganizationSpecialCharacter<CharType>(_char, _needed, _genMin, _genMax)); 
    }
    
    /** Set the name of the organization
      @param _name new name */
    public void setName(String _name) { this.name = _name; }
    
    /** Get the name of the organization
      @return name */
    public String getName() { return name; }
    
    /** Get the owner of the organization (the monster entry)
      @return owner */
    public CreatureType getOwner() { return creature; }
    
    /** Set the owner of the organization (monster entry) 
      @param _creature creature entry */
    public void setOwner(CreatureType _creature) { creature = _creature; }
        
    /** Creates a new instance of DxxFantasyCreatureOrganization 
      @param _name name of the org
      @param _creature monster entry that owns the org 
      @param _min minimum appearing
      @param _max maximum appearing
      @param _noncomb Noncombatants (as a % of the combatants)  */      
    public DxxFantasyCreatureOrganization(
        String _name, CreatureType _creature, int _min, int _max, float _noncomb) 
    {
        name = _name;
        creature = _creature;
        noncombatants = _noncomb;
        minimum = _min;
        maximum = _max;        
    }

    /** Small class to store special characters that accompany the organization.
      Bear in mind that these characters do not have to be of the same race as 
      the base creature; for example, gnolls travel with hyenas and trolls. 
     
      TODO: Use Creature vs. Character here? Is it safe to do so as we make 
      more powerful creatures than those in the race entry? Do we need a 
      DxxCreature.improve() to raise their HD as per the rules on improving
      monsters in the SRD? */
    private static class OrganizationSpecialCharacter<CharType>
    {
        /** The character sheet */
        public CharType character;
        
        /** The number of combatants needed in order to generate this character
       Use Integer.MAX_VALUE for leaders to be unique across the organization */
        public int numberNeeded;
        
        /** The minimum number of these characters to generate */
        public int minToGenerate;
        /** The maximum number of these characters to generate */
        public int maxToGenerate;
        
       /** Construct an OrganizationSpecialCharacter 
       @param _char character
       @param _needed Number needed to generate
       @param _minGenerate minimum number to generate 
       @param _maxGenerate maximum number to generate */
        public OrganizationSpecialCharacter(CharType _char, int _needed, int _minGenerate, int _maxGenerate)
        {
            character = _char;
            numberNeeded = _needed;
            minToGenerate = _minGenerate;
            maxToGenerate = _maxGenerate;
        }        
    }
    
}
