package org.gemini;

import java.util.Random;
import java.util.SortedSet;
import java.util.Arrays;


/**
 *Dice, as an instanced class, is used as a simple data structure to store dice rolls. It has three public integer members, 
 * iDice, iSides, and iModifier. These form a dice roll representation in the form iDice d(iSides) + iModifier. Methods
 *are provided to perform various special types of rolls using this data.
 *<p>
 *Additionally, Dice is where all interaction with Java's random number generator is done. Thus, there are several static functions
 * provided for rolling dice and generating a random number within a certain range.
 */
public class Dice  implements java.io.Serializable
{
    /** A Random object to get random numbers from. 
    * We only need one.
    */ private static Random random = new Random();    
         
    /** Number of die to roll */
    private int iDice;
    
    /** Number of sides per die */
    private int iSides;
    
    /** Modifier added to die rolls */
    private int iModifier;
    
    /** Dice to drop (as in, 4d6, drop the lowest 1) */
    // private int iDrop; // TODO
    
    /** set the values of this die roll
    * @param iDie Number of dice to roll
    * @param iSide Number of sides of each die
    * @param iMod Amount to add or subtract to our die roll
    */ 
    public Dice(int iDie, int iSide, int iMod)
    {
        set(iDie, iSide, iMod);
    }

    /** set the values of this die roll
    * @param iDie Number of dice to roll
    * @param iSide Number of sides of each die
    */ 
    public Dice(int iDie, int iSide)
    {
        set(iDie, iSide, 0);
    }

    /** Default constructor for Dice
     * Leave everything set to 0 
     */ public Dice() {}

/** set the values of this die roll
* @param iDie Number of dice to roll
* @param iSide Number of sides of each die
* @param iMod Amount to add or subtract to our die roll
*/ public void set(int iDie, int iSide, int iMod)
    {
        // TODO: exception? Ignore? Unsigned value?
        assert (iDie > 0) : "Attempting to set a negative number of dice in Dice.set";
        assert (iSide > 0) : "Attempting to set a negative number of sides in Dice.set";
        iDice = iDie;
        iSides = iSide;
        iModifier = iMod;
    }

/** set the values of this die roll
* @param iDie Number of dice to roll
* @param iSide Number of sides of each die
*/ public void set(int iDie, int iSide)
    {
        set(iDie, iSide, 0);
    }
    
/** roll die with the values contained in this class 
* @return a roll of iDice d iSides + iModifier. Returns a straight roll, no NATURAL1 and NATURAL20
*/ public int roll()
    {
        return roll(iDice, iSides, iModifier);
    }
    
/** roll the die's current values, automatically giving a max for the first die 
* @return iDie d iSide + iMod */
public int rollFirstMax()
{
    return roll(iDice - 1, iSides, iModifier) + iSides;
}

/** roll the die's current values, automatically giving a max for the first die.
 ** @param iDice Number of dice to roll
* @param iSides Number of sides of each die
* @param iModifier Number to add to the roll 
* @return iDie d iSide + iMod */
public static int rollFirstMax(int iDice, int iSides, int iModifier)
{
    return roll(iDice - 1, iSides, iModifier) + iSides;
}

/** roll the die's current values, re-rolling ones. 
 * @return iDie d iSide + iMod */
public int roll_Reroll1() { return roll_Reroll1(iDice, iSides, iModifier); }

/** roll the die's current values, re-rolling ones. 
* @param iDice Number of dice to roll
* @param iSides Number of sides of each die
* @param iModifier Number to add to the roll 
* @return iDie d iSide + iMod */
public static int roll_Reroll1(int iDice, int iSides, int iModifier)
{
    int iTotal = 0, iroll = 0;
    for (int i = 0; i < iDice; i++)
    {
        while (iroll <= 1) iroll = roll(1, iSides);
        iTotal += iroll;
        iroll = 0;
    }
    return iTotal + iModifier;         
}

/** rolls the given die value
* @param iDie Number of dice to roll
* @param iSide Number of sides of each die
* @param iMod Number to add to the roll
* @return the rolled result of iDie d iSide + iMod 
*/ public static int roll(int iDie, int iSide, int iMod)
    {
        int iTotal = iMod;
        int iroll;
        for (int i =0; i < iDie; i++)
        {
           iroll = (random.nextInt() % iSide);
           // Java's random number generator can give negatives...
           iTotal += 1 + ((iroll < 0) ? (iroll * -1) : (iroll));
        }
        return iTotal;
    }

/** rolls the given die value.
* @param iDie Number of dice to roll
* @param iSide Number of sides of each die
* @return The rolled result of iDie d iSide 
*/ public static int roll(int iDie, int iSide)
    {
        return roll(iDie, iSide, 0);
    }
    
/** Get a string representation of this die roll
* @return A string in XdY + Z format
*/ public String toString()
    {
        // Branch based on the sign of iModifier
        if (iModifier == 0)        
            return Integer.toString(iDice) + "d" + Integer.toString(iSides);
        else
            return Integer.toString(iDice) + "d" + Integer.toString(iSides) + 
            ((iModifier < 0) 
                ? (" - " + Integer.toString(iModifier * -1)) // Don't have - -
                : (" + ") + Integer.toString(iModifier)); 
    }

/** Pick a number between iLow and iHigh.
* @param iLow Minimum number to allow
* @param iHigh Maximum number to allow
* @return a random value between iLow and iHigh, inclusive.    
*/ public static int numberBetween(int iLow, int iHigh)
    {
         int iRandom = random.nextInt() % (iHigh - iLow + 1);
         if (iRandom < 0) iRandom *= -1;
         return iLow + iRandom;
    }

/** roll iDice d(iSides), dropping the lowest iDrop dice.
 * @param iDice number of dice to roll
 * @param iSides number of sides per die
 * @param iDrop number of dice to DROP.
*/  public static int rollAndDrop(int iDice, int iSides, int iDrop)
    { 
        return rollAndDrop(iDice, iSides, iDrop, 0);
    }

/** roll iDice d(iSides) + iModifier, dropping the lowest iDrop dice. 
 * @param iDice number of dice to roll
 * @param iSides number of sides per die
 * @param iDrop number of dice to DROP.
 * @param iModifier number to add to the total.
 */ public static int rollAndDrop(int iDice, int iSides, int iDrop, int iModifier)
    {
        int iroll[] = new int[iDice];

        for (int i = 0; i < iDice; i++)
        {
            iroll[i] = (random.nextInt() % iSides);
            iroll[i] = 1 + ((iroll[i] < 0) ? (iroll[i] * -1) : (iroll[i]));
        }

        Arrays.sort(iroll);
        int iTotal = iModifier;

        // Loop through the highest ones and add them
        for (int i = iDrop; i < iDice; i++)
        {
            iTotal += iroll[i];
        }
        return iTotal;
    }
     
    /** Get the maximum potential roll this die could generate. 
  @return the highest possible roll */
 public int getMaximumRoll()
 {
     return (iSides * iDice) + iModifier;
 }
 
 /** Get the maximum potential roll this die could generate. 
 @return lowest possible roll */
 public int getMinimumRoll()
 {
     return (iDice) + iModifier;
 }

 /** Get number of sides per die
  *  @return sides */
 public int getSides() { return iSides; }
 
 /** Get number of die
  *  @return die */
 public int getDie() { return iDice; }
 
 /** Get modifier to this roll
  *  @return modifier */
 public int getModifier() { return iModifier; }
 
 /** Set sides per die
  *  @param i Sides */
 public void setSides(int i) { iSides = i; }
 
 /** Set number of die
  *  @param i Dice */
 public void setDie(int i) { iDice = i; }
 
/** Set modifier for the roll
  *  @param i Modifier */
 public void setModifier(int i) { iModifier = i; }
 
 /** Accessor for static number generator
  * @return number generator */
 public static final Random getRandomGenerator() { return random; }
 
/** Serve as a unit test for Dice.
 * TODO move to junit
* @param args Arguments from OS. 
*/ public static void main(String[] args) 
    {

        System.out.println("");
        System.out.println("Testing Dice.roll() (static). rolling 1d20 1,000,000 times.");
        System.out.println("============================================");

        int[] iValues = new int[22];
        for (int i = 0; i < 1000000; i++) 
        {
           int iX = Dice.roll(1,20, 0);
           iValues[iX]++;
        }
        for (int i = 0; i < 11; i++)
             System.out.println(i + ":\t" + iValues[i] + "\t\t" + (11 + i) + ":\t" + iValues[11 + i]);

        System.out.println("The statistical average should be " + 1000000 / 20 + " rolls of any given value for 1,000,000 rolls.");

        System.out.println("");
        System.out.println("Testing Dice.roll() (non-static). rolling 2d20 1,000,000 times.");
        System.out.println("============================================");

        Dice diceTest = new Dice(2,20,0);
        int [] iVals2 = new int[42];

        for (int i = 0; i < 1000000; i++) 
        {
           int iX = diceTest.roll();
           iVals2[iX]++;
        }

        for (int i = 0; i < 21; i++)
             System.out.println(i + ":\t" + iVals2[i] + "\t\t" + (21 + i) + ":\t" + iVals2[21 + i]);

        System.out.println("The statistical average should be " + 1000000 / 40 + " rolls of any given value for 1,000,000 rolls.");



        int i100 = 0, i500 = 0, i250 = 0;

        System.out.println("");
        System.out.println("Testing Dice.NumberBetween(). rolling 100,000 times.");
        System.out.println("============================================");

        for (int i = 0; i < 100000; i++)
        {
            int iX = Dice.numberBetween(100, 500);
            if (iX == 500) i500++;
            if (iX == 100) i100++;
            if (iX == 250) i250++;
        }
        System.out.println("rolled " + i100 + " minimums, " + i250 + " medians, and " + i500 + " maximums in 100,000 rolls.");
        System.out.println("Statistically, the average should be " + (100000 / 400) + " rolls of any given value for 100,000 rolls.");

        System.out.println("");
        System.out.println("Testing Dice.toString() and set(). rolling 100 times.");
        System.out.println("============================================");

        for (int i = 0; i < 100; i++)
        {
            int iMod = Dice.numberBetween(0, 5) * ((i % 2 == 0) ? 1 : -1);
            diceTest.set(Dice.roll(1,5), Dice.roll(1, 5), iMod);
            if ((i % 5 == 0 && i > 0))
                System.out.println();System.out.print(diceTest + "\t\t");

        }
        System.out.println();

        System.out.println("");
        System.out.println("Testing Dice.rollAndDrop(). rolling 6d6, dropping 2.");
        System.out.println("============================================");
        System.out.println("If you are not seeing individual rolls, comment the println() back in in Dice.rollAndDrop().");
        System.out.println("");
        
        for (int i = 0; i < 5; i++)
        {
            System.out.println ("My total was: " + rollAndDrop(6, 6, 2) + ".");
            System.out.println("");
   
        }
    // End main()
    }
   


} // End class Dice