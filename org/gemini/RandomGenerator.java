/*
 * RandomGenerator.java
 *
 * Created on November 11, 2005, 2:19 AM
 */

package org.gemini;
import java.util.ArrayList;

/**
 * Allows for values to be randomly generated from probability objects. 
 *
 * @author  Jaeden
 */
public class RandomGenerator<Type> 
{

    /** list of possibilities */
    private ArrayList<Possibility<Type>> possibilities = new ArrayList<Possibility<Type>>();
    
    /** Count of all probabilities combined */
    private int totalChance = 0;
    
    /** Class to store probabilities for this type */
    private static class Possibility<Type>
    {
        /** Chance to get this possibility */
        public int chance;

        /** The actual possibility */
        public Type possible;

        /** Construct a Possibility 
            @param _possible Object to maybe get 
            @param _chance Chance to get this object */             
        public Possibility(Type _possible, int _chance)
        {
            possible = _possible;
            chance = _chance;
        }
    }

    /** Add a possibility to the chances 
     *  @param _possible Object to maybe get 
        @param _chance Chance to get this object */        
    public void addPossibility(Type _possible, int _chance)
    {
        totalChance += _chance;
        possibilities.add(new Possibility<Type>(_possible, _chance));            
    }

    /** Randomly select a possibility 
     *  @return a randomly-selected Possibility */
    public Type generate() 
    {
        int roll = Dice.roll(1, totalChance);

        int countUp = 0;
        int iterator = 0;            
        Possibility<Type> selection = null;

        // count until we get to the roll
        while (countUp < roll)
        {
            selection = possibilities.get(iterator);                
            countUp += selection.chance;
            iterator++;                
        }

        return selection.possible;            
    }
        
   
}
