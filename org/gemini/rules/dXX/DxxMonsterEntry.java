/*
 * DxxMonsterEntry.java
 *
 * Created on November 17, 2005, 11:42 PM
 */

package org.gemini.rules.dXX;
import org.gemini.sources.SourceReference;
import org.slage.framework.NotFoundException;
import org.slage.framework.Attribute;

/**
 
  <b>Licensing Notice</b><br>
   The rules defined in this file are provided under the terms of the Open Gaming 
   License v1.0a. The text of this license can be viewed 
   <a href="http://www.wizards.com/d20/files/OGLv1.0a.rtf">here</a>. 
 

 * @author  Jaeden
 */
public class DxxMonsterEntry extends DxxCreature
{
    
      /** Logger instance */
    protected static final org.apache.commons.logging.Log LOG = 
        org.apache.commons.logging.LogFactory.getLog(DxxMonsterEntry.class);
    
    
    /** Creates a new instance of DxxMonsterEntry 
      @param _name name of the race
      @param _src source reference for the race */
    public DxxMonsterEntry(String _name, SourceReference<?> _src)
    {
        super(_name, _src);
    }
    
    /** Convert a Monster Entry into a Race for playing
      @return a DxxRace representing this monster entry */
    public DxxRace asRace() 
    {
        /*
        DxxRace race = new DxxRace(getName(),getSource());
        race.setDescription(getDescription());
        
        // copy attributes...
        
        // copy features...
        
        // ability score modifiers
        // todo: fix for ability score list
        for (int i = 0; i < 6; i++)
        {
            DxxAbilityScoreNode<DxxRace> ability = null;
            String strScore = null;
            try
            {
                
                if (i == 0) strScore = "Strength";
                if (i == 1) strScore = "Dexterity";
                if (i == 2) strScore = "Constitution";
                if (i == 3) strScore = "Intelligence";
                if (i == 4) strScore = "Wisdom";
                if (i == 5) strScore = "Charisma";
                
                ability = (DxxAbilityScoreNode)getAttributeValue(strScore);
                
            }   catch (NotFoundException ex) { LOG.error(ex); }
            
            
            // fail gracefully
           if (ability == null)
               continue;
           
           int value = ability.getValue();

           // if above 10, subtract 10/11
           if (value >= 10)
            {
                
                    // round to even by chopping off the 1 bit, then subtract 10
                    value = ((value >> 1) << 1) - 10;
                    race.setAttribute(new Attribute<DxxAbilityScoreNode<DxxRace>>(strScore, new DxxAbilityScoreNode<DxxRace>(ability.getStatistic(), value, race)));
                    continue;
            }

           // if below 10, use the chart thingy
                       
        }
         *return race;
        */
        return null;
                 
    }    
    
    /** Accept a Visitor
        @param aVisitor visitor to accept */        
    public void accept(org.gemini.GeminiObjectVisitor aVisitor){
        aVisitor.accept(this); 
    }      
    
}