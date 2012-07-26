/*
 * DxxCharacterTest.java
 *
 * Created on November 29, 2005, 9:54 AM
 */

package org.gemini.tests.rules.dXX;
import org.gemini.rules.dXX.*;
import java.util.ArrayList;

/**
 * Test for Dxx Character functions
 *
 * @author  Jaeden
 */
public class DxxCharacterTest extends junit.framework.TestCase
{
    
    /** Creates a new instance of DxxCharacterTest 
        @param string String */
    public DxxCharacterTest(String string ) { super(string); }
    
    
    /** Make sure saving throw modifiers are added for ability scores */
    public void testAbilityScoresAddedToSavingThrows() throws Exception
    {
        DxxCharacter C = new DxxCharacter("Joe", null);
        
        ArrayList<DxxSavingThrowNode<? extends org.gemini.Grantor>> nodes = 
            C.getSavingThrows().getNodes(DxxSavingThrow.FORTITUDE);
        
        boolean found = false;
        for (DxxSavingThrowNode node : nodes)
        {
            if (node instanceof DxxSavingThrowNode.AbilityScoreModNode == false)
                continue;
            
            if ( ((DxxSavingThrowNode.AbilityScoreModNode)node).getAbilityScore().equals(
                DxxAbilityScore.CONSTITUTION) )
                found = true;                
        }
        if (found == false)
            fail("Fortitude save is not modified by Constitution.");       
        
        nodes =  C.getSavingThrows().getNodes(DxxSavingThrow.REFLEX);        
        found = false;
        
        for (DxxSavingThrowNode node : nodes)
        {
            if (node instanceof DxxSavingThrowNode.AbilityScoreModNode == false)
                continue;
            
            if ( ((DxxSavingThrowNode.AbilityScoreModNode)node).getAbilityScore().equals(
                DxxAbilityScore.DEXTERITY) )
                found = true;                
        }
        if (found == false)
            fail("Reflex save is not modified by Dexterity.");
        
        nodes =  C.getSavingThrows().getNodes(DxxSavingThrow.WILL);                     
        found = false;
        
        for (DxxSavingThrowNode node : nodes)
        {
            if (node instanceof DxxSavingThrowNode.AbilityScoreModNode == false)
                continue;
            
            if ( ((DxxSavingThrowNode.AbilityScoreModNode)node).getAbilityScore().equals(
                DxxAbilityScore.WISDOM) )
                found = true;                
        }
        if (found == false)
            fail("Will save is not modified by Wisdom.");
    }
    
}
