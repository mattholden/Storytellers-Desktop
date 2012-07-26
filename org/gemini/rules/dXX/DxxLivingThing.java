/*
 * DxxLivingThing.java
 *
 * Created on December 4, 2005, 9:55 AM
 */

package org.gemini.rules.dXX;
import org.gemini.StatisticSet;

/**
 * Defines common interface methods shared by creatures and characters implementing
 * dXX rules.
 *
 * @author  Jaeden
 */
public interface DxxLivingThing 
{


/** STATISTIC SETS **/

  /** Accesor for saving throw set
     @return saving throw set */
    public StatisticSet<DxxSavingThrow, DxxSavingThrowNode<? extends org.gemini.Grantor>> getSavingThrows();
    
    /** Accesor for ability score set
     @return ability score set */
    public StatisticSet<DxxAbilityScore, DxxAbilityScoreNode<? extends org.gemini.Grantor>> getAbilityScores();
        
    /** Accesor for movement rate set
     @return movement rate set */
    public StatisticSet<DxxMovementRate, DxxMovementRateNode<? extends org.gemini.Grantor>> getMovementRates();
    
     /** Accesor for combat statistics set
     @return combat statistics set */
    public StatisticSet<DxxCombatStatistic, DxxCombatNode<? extends org.gemini.Grantor>> getCombatStatistics();
    

/** SIZE **/
    
    /** Accessor for creature size 
      @return size */
    public DxxSize getSize();
    
    /** Muutator for creature size.
     * @param _size new size */
    public void setSize(DxxSize _size);
    

/** CREATURE TYPE/SUBTYPES **/
    
    /** Add a Creature Type
     *  @param type new DxxCreatureType to add */
    public void addCreatureType(DxxAbstractCreatureType type);
    
    /** Remove a Creature Type
     * @param type DxxCreatureType to remove */
    public void removeCreatureType(DxxAbstractCreatureType type);
    
    /** Count creature subtypes
     *  @return number of creature subtypes (doesn't count the base Creature Type */
    public int getCreatureSubtypeCount();
       
    /** Get the creature's main type
      @return creature's main type or 'null' if it hasn't got one */
    public DxxAbstractCreatureType getCreatureType();
    


/** DOUBLE DISPATCH **/
    
    /** Accept a Visitor
        @param aVisitor visitor to accept */        
    public void accept(org.gemini.GeminiObjectVisitor aVisitor);
    
     
}
