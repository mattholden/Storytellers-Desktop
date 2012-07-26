/*
 * DxxCreature.java
 *
 * Created on November 11, 2005, 11:45 PM
 */

package org.gemini.rules.dXX;
import org.slage.framework.Attribute;
import org.gemini.GeminiCreature;
import org.gemini.sources.SourceReference;
import java.util.ArrayList;
import org.gemini.StatisticSet;

/**
 * Defines a dXX implementation of a Creature, which is a monster
 * entry and/or a race. 

  <b>Licensing Notice</b><br>
   The rules defined in this file are provided under the terms of the Open Gaming 
   License v1.0a. The text of this license can be viewed 
   <a href="http://www.wizards.com/d20/files/OGLv1.0a.rtf">here</a>. 
 

 * @author  Jaeden
 *
 * TODO see if removing the templating on DxxCreature
 * and merely re-implementing the interface with a new type
 * is enough to get type clarity in DxxF classes 
 */
public class DxxCreature extends GeminiCreature implements DxxLivingThing
{
    /** Creature size */ private DxxSize size = DxxSize.MEDIUM;

    /** Store ability scores as a statistic set */
    private StatisticSet<DxxAbilityScore, DxxAbilityScoreNode<? extends org.gemini.Grantor>> abilityScores =
        new StatisticSet<DxxAbilityScore, DxxAbilityScoreNode<? extends org.gemini.Grantor>>();
    
    /** Store saving throws as a statistic set */
    private StatisticSet<DxxSavingThrow, DxxSavingThrowNode<? extends org.gemini.Grantor>> savingThrows = 
        new StatisticSet<DxxSavingThrow, DxxSavingThrowNode<? extends org.gemini.Grantor>>();
        
    /** Store movement rates as a statistic set */
    private StatisticSet<DxxMovementRate, DxxMovementRateNode<? extends org.gemini.Grantor>> movementRates = 
        new StatisticSet<DxxMovementRate, DxxMovementRateNode<? extends org.gemini.Grantor>>();
        
   /** Store combat statistics as a statistic set */
    private StatisticSet<DxxCombatStatistic, DxxCombatNode<? extends org.gemini.Grantor>> combatStatistics = 
        new StatisticSet<DxxCombatStatistic, DxxCombatNode<? extends org.gemini.Grantor>>();

      /** Creates a new instance of DxxCreature 
      @param _name character's name 
      @param _src Source for the character */
    public DxxCreature(String _name, SourceReference<?> _src)
    {
        super(_name, _src);
        
        // add common dXX system attributes 
        DxxSystem.addSystemAttributes(this);
    }
    
    
    
    /** Accesor for saving throw set
     @return saving throw set */
    public StatisticSet<DxxSavingThrow, DxxSavingThrowNode<? extends org.gemini.Grantor>> getSavingThrows() { return savingThrows; }
    
    /** Accesor for ability score set
     @return ability score set */
    public StatisticSet<DxxAbilityScore, DxxAbilityScoreNode<? extends org.gemini.Grantor>> getAbilityScores() { return abilityScores; }
        
    /** Accesor for movement rate set
     @return movement rate set */
    public StatisticSet<DxxMovementRate, DxxMovementRateNode<? extends org.gemini.Grantor>> getMovementRates() { return movementRates; }
    
    
     /** Accesor for combat statistics set
     @return combat statistics set */
    public StatisticSet<DxxCombatStatistic, DxxCombatNode<? extends org.gemini.Grantor>> getCombatStatistics() { return combatStatistics; }
    
    
    /** Store the creature type (first) and subtypes of this character */
    private ArrayList<DxxAbstractCreatureType> creatureTypes = new ArrayList<DxxAbstractCreatureType>();        
    
    /** Add a Creature Type
     *  @param type new DxxCreatureType to add */
    public void addCreatureType(DxxAbstractCreatureType type) { creatureTypes.add(type); }
    
    /** Remove a Creature Type
     * @param type DxxCreatureType to remove */
    public void removeCreatureType(DxxAbstractCreatureType type) { creatureTypes.remove(type); }
    
    /** Count creature subtypes
     *  @return number of creature subtypes (doesn't count the base Creature Type */
    public int getCreatureSubtypeCount() { return creatureTypes.size() - 1; }
       
    /** Get the creature's main type
      @return creature's main type or 'null' if it hasn't got one */
    public DxxAbstractCreatureType getCreatureType() 
    {
        return (creatureTypes.isEmpty() ? null : creatureTypes.get(0));
    }
    
    /** Accessor for creature size 
      @return size */
    public DxxSize getSize() { return size; }
    
    /** Muutator for creature size.
     * @param _size new size */
    public void setSize(DxxSize _size) { size = _size; }
    
    
    
    /** Accept a Visitor
        @param aVisitor visitor to accept */        
    public void accept(org.gemini.GeminiObjectVisitor aVisitor) { aVisitor.accept(this);  }          

}
