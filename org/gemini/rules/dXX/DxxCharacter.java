/*
 * DxxCharacter.java
 *
 * Created on November 11, 2005, 11:46 PM
 */

package org.gemini.rules.dXX;
import org.slage.framework.Attribute;
import org.gemini.GeminiCharacter;
import org.gemini.sources.SourceReference;
import java.util.ArrayList;
import org.gemini.GeminiObject;
import org.gemini.StatisticSet;
import org.gemini.Feature;
import org.gemini.Grantor;

/**
 * Defines a living, breathing character in our dXX games. 
 * This is a physical entity, and not a template such as a race 
 * entry or monster entry. For those, use dxxCreature.
 * 

  <b>Licensing Notice</b><br>
   The rules defined in this file are provided under the terms of the Open Gaming 
   License v1.0a. The text of this license can be viewed 
   <a href="http://www.wizards.com/d20/files/OGLv1.0a.rtf">here</a>. 
 

 * @author  Jaeden
 */
public class DxxCharacter extends GeminiCharacter implements DxxLivingThing    
{
    /** TODO
     *  - Template list
     *  - Class skills (for permament class skill gains)
     *  - alt. constructors
     *  - Accessors for level, creaturetype, etc. objects?
     */
       
    /** Store the class levels of this character. Don't need to template the DxxCharacter
        class here because subclasses of DxxClass will return the appropriate type for us. */
    private ArrayList<DxxLevel<? extends GeminiObject>> levels = 
        new ArrayList<DxxLevel<? extends GeminiObject>>();
    
    /** Store ability scores as a statistic set */
    private StatisticSet<DxxAbilityScore, DxxAbilityScoreNode<? extends Grantor>> abilityScores =
        new StatisticSet<DxxAbilityScore, DxxAbilityScoreNode<? extends Grantor>>();
    
    /** Store saving throws as a statistic set */
    private StatisticSet<DxxSavingThrow, DxxSavingThrowNode<? extends Grantor>> savingThrows = 
        new StatisticSet<DxxSavingThrow, DxxSavingThrowNode<? extends Grantor>>();
        
    /** Store movement rates as a statistic set */
    private StatisticSet<DxxMovementRate, DxxMovementRateNode<? extends Grantor>> movementRates = 
        new StatisticSet<DxxMovementRate, DxxMovementRateNode<? extends Grantor>>();

   /** Store combat statistics as a statistic set */
    private StatisticSet<DxxCombatStatistic, DxxCombatNode<? extends Grantor>> combatStatistics = 
        new StatisticSet<DxxCombatStatistic, DxxCombatNode<? extends Grantor>>();

    /** Store the creature type (first) and subtypes of this character */
    private ArrayList<DxxAbstractCreatureType> creatureTypes = new ArrayList<DxxAbstractCreatureType>();        

    /** Creature size */ private DxxSize size = DxxSize.MEDIUM;
    
   

    /** Creates a new instance of DxxCharacter 
      @param _name character's name 
      @param _src Source for the character */
    public DxxCharacter(String _name, SourceReference<?> _src)
    {
        super(_name, _src);
        
        // add common dXX system attributes 
        DxxSystem.addSystemAttributes(this);
        
        setAttribute(new Attribute<Integer>("Experience", new Integer(0)));
        setAttribute(new Attribute<Integer>("Experience for Next Level", new Integer(1000)));
        
        // your life force is strong.. or is it?
        setAttribute(new Attribute<Integer>("Hit Points", new Integer(0)));
        setAttribute(new Attribute<Integer>("Max Hit Points", new Integer(0)));
        setAttribute(new Attribute<Integer>("Non-Lethal Damage", new Integer(0)));
        
        // cheat for psionics... easier than modifying later
        setAttribute(new Attribute<Integer>("Psionic Power Pool", null));
        setAttribute(new Attribute<Integer>("Max Psionic Power Pool", null));
                        
        // Favored class (TODO: Store DxxClass reference or String?)
        setAttribute(new Attribute<String>("Favored Class", null));     
        
        // level adjustment always exists for PCs, even if it's 0.
        // already defined in addSystemAttributes() so just change the value
        setAttribute("Level Adjustment", new Integer(0));
        
        
        
        // Add ability modifiers to saving throws
        savingThrows.add(new DxxSavingThrowNode.AbilityScoreModNode(
            DxxSavingThrow.FORTITUDE, this, DxxAbilityScore.CONSTITUTION));
        savingThrows.add(new DxxSavingThrowNode.AbilityScoreModNode(
            DxxSavingThrow.REFLEX, this, DxxAbilityScore.DEXTERITY));
        savingThrows.add(new DxxSavingThrowNode.AbilityScoreModNode(
            DxxSavingThrow.WILL, this, DxxAbilityScore.WISDOM));
        
        // melee attack: BAB + Str + Size 
        combatStatistics.add(new DxxCombatNode.BaseAttackNode(DxxCombatStatistic.MELEEATTACK, this));
        combatStatistics.add(new DxxCombatNode.AbilityScoreModNode(DxxCombatStatistic.MELEEATTACK, this, DxxAbilityScore.STRENGTH));
        combatStatistics.add(new DxxCombatNode.SizeAttackModNode(DxxCombatStatistic.MELEEATTACK, this));
       
        // ranged attack: BAB + Dex + Size + Ranged Penalty
        combatStatistics.add(new DxxCombatNode.BaseAttackNode(DxxCombatStatistic.RANGEDATTACK, this));
        combatStatistics.add(new DxxCombatNode.AbilityScoreModNode(DxxCombatStatistic.RANGEDATTACK, this, DxxAbilityScore.DEXTERITY));
        combatStatistics.add(new DxxCombatNode.SizeAttackModNode(DxxCombatStatistic.RANGEDATTACK, this));
            /* todo: ranged penalty */
        
        // grapple: BAB + Str + Size (special)
        combatStatistics.add(new DxxCombatNode.BaseAttackNode(DxxCombatStatistic.GRAPPLEATTACK, this));
        combatStatistics.add(new DxxCombatNode.AbilityScoreModNode(DxxCombatStatistic.GRAPPLEATTACK, this, DxxAbilityScore.STRENGTH));
        combatStatistics.add(new DxxCombatNode.SizeGrappleModNode(DxxCombatStatistic.GRAPPLEATTACK, this));
       
        // initiative
        combatStatistics.add(new DxxCombatNode.AbilityScoreModNode(DxxCombatStatistic.INITIATIVE, this, DxxAbilityScore.DEXTERITY));
     
        // Armor Class
        combatStatistics.add(new DxxCombatNode<DxxCharacter>(DxxCombatStatistic.ARMORCLASS, 10, this)); // everybody gets 10
        combatStatistics.add(new DxxCombatNode.SizeACModNode(DxxCombatStatistic.ARMORCLASS, this));    
            /* todo : armor/shield bonus, node that determines when we get to use our dex bonus to AC */
        
        
        
    }
    
    /** Add a level to this character
      @param lvl Level to add */
    public void gainLevel(DxxLevel<? extends GeminiObject> lvl)
    {
        levels.add(lvl);
        
        // gain all the niftyness
        lvl.getFeatures().grantFeatures(lvl, this);
    }
    
    /** Lose the last level gained */
    public void loseLastLevel() 
    {
        if (levels.isEmpty())
            return;
        
        DxxLevel<? extends GeminiObject> level = 
            levels.get(levels.size() - 1);
        
        levels.remove(level);
        
        // lose all the niftyness
        removeGrantor(level);
    }
    
    /** Remove all features provided by a given Grantor
     *  @param grantor Grantor to search for. */
    public void removeGrantor(Grantor grantor)
    {
        super.removeGrantor(grantor);
        combatStatistics.removeGrantor(grantor);
        savingThrows.removeGrantor(grantor);
        abilityScores.removeGrantor(grantor);
        movementRates.removeGrantor(grantor);
    }
    
    /** Get total character level 
      @return total character level (not Effective Character Level) */
    public int getCharacterLevel() { return levels.size(); }
    
      /** Get effective character level (for creatures with level adjustments)
      @return effective character level */
    public int getEffectiveLevel() 
    { 
        int LA = 0;
        try
        {
            LA = getAttributeAsInt("Level Adjustment");
        }
        catch (Exception e) { LOG.warn(e); }
        
        return levels.size() + LA;
    }
   
    
    /** Accesor for saving throw set
     @return saving throw set */
    public StatisticSet<DxxSavingThrow, DxxSavingThrowNode<? extends Grantor>> getSavingThrows() { return savingThrows; }
    
    /** Accesor for ability score set
     @return ability score set */
    public StatisticSet<DxxAbilityScore, DxxAbilityScoreNode<? extends Grantor>> getAbilityScores() { return abilityScores; }
        
    /** Accesor for movement rate set
     @return movement rate set */
    public StatisticSet<DxxMovementRate, DxxMovementRateNode<? extends Grantor>> getMovementRates() { return movementRates; }
    
     /** Accesor for combat statistics set
     @return combat statistics set */
    public StatisticSet<DxxCombatStatistic, DxxCombatNode<? extends Grantor>> getCombatStatistics() { return combatStatistics; }
    
    /** Accessor for creature size 
      @return size */
    public DxxSize getSize() { return size; }
    
    /** Muutator for creature size.
     * @param _size new size */
    public void setSize(DxxSize _size) { size = _size; }
    
    
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
    
    
    /** Accept a Visitor
        @param aVisitor visitor to accept */        
    public void accept(org.gemini.GeminiObjectVisitor aVisitor) { aVisitor.accept(this);  }    
    
     
    /** Logger instance */
    protected static transient final org.apache.commons.logging.Log LOG = 
        org.apache.commons.logging.LogFactory.getLog(DxxCharacter.class);


}
