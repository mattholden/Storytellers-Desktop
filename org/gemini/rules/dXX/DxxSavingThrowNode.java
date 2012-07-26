/*
 * DxxSavingThrowNode.java
 *
 * Created on November 17, 2005, 2:09 AM
 */

package org.gemini.rules.dXX;
import org.gemini.Dice;
import org.gemini.StatisticNode;
import org.gemini.GeminiObject;

/**
 * Defines a small data structure to pair Saving Throws with their values
 * and provide a few useful methods.
 
  <b>Licensing Notice</b><br>
   The rules defined in this file are provided under the terms of the Open Gaming 
   License v1.0a. The text of this license can be viewed 
   <a href="http://www.wizards.com/d20/files/OGLv1.0a.rtf">here</a>. 
 

 * @author  Jaeden
 */
public class DxxSavingThrowNode<GrantType extends org.gemini.Grantor> extends StatisticNode<DxxSavingThrow, GrantType>    
{
           
/** Creates a new instance of DxxSavingThrowNode 
        @param _score saving throw type 
        @param _value value of the saving throw 
        @param _grantor Grantor of this node */
    public DxxSavingThrowNode(DxxSavingThrow _score, int _value, GrantType _grantor) 
    {
        super(_score, _value, _grantor);
    }       

    
    /** Subclass for the dynamic bonus for ability scores */
    public static class AbilityScoreModNode extends DxxSavingThrowNode<DxxCharacter>    
    {
        /** The ability to give bonus for */
        private DxxAbilityScore ability;
        
            /** Creates a new instance of AbilityScoreModNode 
                @param _score saving throw type
                @param _grantor grantor (the character whose score is being checked)
                @param _ability Ability score to use as a bonus */                
            public AbilityScoreModNode
                (DxxSavingThrow _score, DxxCharacter _grantor, DxxAbilityScore _ability) 
            {
                super(_score, 0, _grantor); 
                ability = _ability;
            }       
        
            /** Accessor for the ability 
             *  @return ability */
            public DxxAbilityScore getAbilityScore() { return ability; }
            
            /** Accessor for the value
             * @return ability score modifier */
            public int getValue()
            {                 
                return DxxAbilityScore.getModifier(getGrantor().getAbilityScores().get(ability));                 
            }
    }
    
}
