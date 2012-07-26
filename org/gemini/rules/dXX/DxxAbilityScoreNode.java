/*
 * DxxAbilityScoreNode.java
 *
 * Created on November 17, 2005, 2:09 AM
 */

package org.gemini.rules.dXX;
import org.gemini.Dice;
import org.gemini.StatisticNode;
import org.gemini.GeminiObject;

/**
 * Defines a small data structure to pair Ability Scores with their values
 * and provide a few useful methods.

  <b>Licensing Notice</b><br>
   The rules defined in this file are provided under the terms of the Open Gaming 
   License v1.0a. The text of this license can be viewed 
   <a href="http://www.wizards.com/d20/files/OGLv1.0a.rtf">here</a>. 
 

 * @author  Jaeden
 */
public class DxxAbilityScoreNode<GrantType extends org.gemini.Grantor> extends StatisticNode<DxxAbilityScore, GrantType>    
    implements CheckAgainstDC
{
    /** Creates a new instance of DxxAbilityScoreNode 
        @param _score Ability score 
        @param _value value of the ability score 
        @param _grantor Grantor of this node */
    public DxxAbilityScoreNode(DxxAbilityScore _score, int _value, GrantType _grantor) 
    {
        super(_score, _value, _grantor);
    }       
    
    /** Get the modifier for the ability score
     *  @return ability score modifier for this score */
    public int getModifier() { return DxxAbilityScore.getModifier(getValue()); }
    
    /** Check against a DC
     *  @param dc the DC to check against
     *  @return the result of the check (no bonuses are added here...) */
    public int check(int dc)
    {
        int roll = Dice.roll(1, 20);        
        return roll + getValue() - dc;
    }
    
}
