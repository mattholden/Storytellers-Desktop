/*
 * DxxCombatNode.java
 *
 * Created on November 17, 2005, 2:09 AM
 */

package org.gemini.rules.dXX;
import org.gemini.Dice;
import org.gemini.StatisticNode;
import org.gemini.GeminiObject;

/**
 * Defines a small data structure to pair Combat Statistics with their values
 * and provide a few useful methods.
 
  <b>Licensing Notice</b><br>
   The rules defined in this file are provided under the terms of the Open Gaming 
   License v1.0a. The text of this license can be viewed 
   <a href="http://www.wizards.com/d20/files/OGLv1.0a.rtf">here</a>. 
 

 * @author  Jaeden
 */
public class DxxCombatNode<GrantType extends org.gemini.Grantor> extends StatisticNode<DxxCombatStatistic, GrantType>    
{
           
/** Creates a new instance of DxxCombatNode 
        @param _score Combat Statistic type 
        @param _value value of the Combat Statistic 
        @param _grantor Grantor of this node */
    public DxxCombatNode(DxxCombatStatistic _score, int _value, GrantType _grantor) 
    {
        super(_score, _value, _grantor);
    }       

    
    /** Subclass for the dynamic bonus for ability scores */
    public static class AbilityScoreModNode extends DxxCombatNode<DxxCharacter>
    {
        /** The ability to give bonus for */
        private DxxAbilityScore ability;
        
            /** Creates a new instance of AbilityScoreModNode 
                @param _score Combat Statistic type
                @param _grantor grantor (the character whose score is being checked)
                @param _ability Ability score to use as a bonus */                
            public AbilityScoreModNode
                (DxxCombatStatistic _score, DxxCharacter _grantor, DxxAbilityScore _ability) 
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
                DxxCharacter chr = (DxxCharacter)getGrantor(); 
                return DxxAbilityScore.getModifier(chr.getAbilityScores().get(ability)); 
                
            }
    }
    
     
    /** Subclass for the dynamic attack bonus for BAB  */
    public static class BaseAttackNode extends DxxCombatNode<DxxCharacter>
    {
            /** Creates a new instance of BaseAttackNode 
                @param _score Combat Statistic type
                @param _grantor grantor (the character whose score is being checked) */                
            public BaseAttackNode(DxxCombatStatistic _score, DxxCharacter _grantor)  {
                super(_score, 0, _grantor); 
            }       
                    
            /** Accessor for the value
             * @return attack modifier */
            public int getValue()  {
                if (getGrantor() instanceof DxxCharacter == false) return 0;            
                return ((DxxCharacter)getGrantor()).getCombatStatistics().get(DxxCombatStatistic.BASEATTACKBONUS);
            }                        
    }
    
    /** Subclass for the dynamic attack bonus for size  */
    public static class SizeAttackModNode extends DxxCombatNode<DxxCharacter>
    {
            /** Creates a new instance of SizeAttackModNode 
                @param _score Combat Statistic type
                @param _grantor grantor (the character whose score is being checked) */                
            public SizeAttackModNode(DxxCombatStatistic _score, DxxCharacter _grantor)  {
                super(_score, 0, _grantor); 
            }       
                    
            /** Accessor for the value
             * @return attack modifier */
            public int getValue()  {
                if (getGrantor() instanceof DxxCharacter == false) return 0;            
                return ((DxxCharacter)getGrantor()).getSize().getAttackModifier();
            }                        
    }
    
    /** Subclass for the dynamic AC bonus for size  */
    public static class SizeACModNode extends DxxCombatNode<DxxCharacter>
    {
            /** Creates a new instance of SizeAttackModNode 
                @param _score Combat Statistic type
                @param _grantor grantor (the character whose score is being checked) */                
            public SizeACModNode(DxxCombatStatistic _score, DxxCharacter _grantor)  {
                super(_score, 0, _grantor); 
            }       
                    
            /** Accessor for the value
             * @return attack modifier */
            public int getValue()  {
                if (getGrantor() instanceof DxxCharacter == false) return 0;            
                return ((DxxCharacter)getGrantor()).getSize().getACModifier();
            }                        
    }
    
    /** Subclass for the dynamic grapple bonus for size  */
    public static class SizeGrappleModNode  extends DxxCombatNode<DxxCharacter>
    {
            /** Creates a new instance of SizeGrappleModNode 
                @param _score Combat Statistic type
                @param _grantor grantor (the character whose score is being checked) */                
            public SizeGrappleModNode(DxxCombatStatistic _score, DxxCharacter _grantor)  {
                super(_score, 0, _grantor); 
            }       
                    
            /** Accessor for the value
             * @return attack modifier */
            public int getValue()  {
                if (getGrantor() instanceof DxxCharacter == false) return 0;            
                return ((DxxCharacter)getGrantor()).getSize().getGrappleModifier();
            }                        
    }
    
    
}