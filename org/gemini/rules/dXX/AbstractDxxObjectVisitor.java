/*
 * AbstractDxxObjectVisitor.java
 *
 * Created on November 12, 2005, 9:55 AM
 */

package org.gemini.rules.dXX;
import org.gemini.AbstractGeminiObjectVisitor;
import org.gemini.Feature;
import org.gemini.GeminiCharacter;
import org.gemini.GeminiCreature;
import org.gemini.GeminiObject;
import org.gemini.ObjectGroup;
import org.gemini.GamingSystem;


/**
 * Provides an abstract implementation of DxxObjectVisitor,
 * allowing the user to implement only the methods he/she wishes.
 *
 * @author Jaeden
 */
public abstract class AbstractDxxObjectVisitor 
    extends AbstractGeminiObjectVisitor implements DxxObjectVisitor
{
        
    /** Creates a new instance of AbstractDxxObjectVisitor */
    protected AbstractDxxObjectVisitor() { /** empty on purpose */ }
    
    /** Accept an ability
      @param anObject object to accept */
    public void accept(DxxAbility anObject) { accept((Feature)anObject); }
    
    /** Accept a trait
      @param anObject object to accept */
    public void accept(DxxTrait anObject) { accept((Feature)anObject); }
    
   /** Accept a character
      @param anObject object to accept */
    public void accept(DxxCharacter anObject) { accept((GeminiCharacter)anObject); }
        
    /** Accept an feat
      @param anObject object to accept */
    public void accept(DxxFeat anObject) { accept((Feature)anObject); }
    
    /** Accept a creature
      @param anObject object to accept */
    public void accept(DxxCreature anObject) { accept((GeminiCreature)anObject); }
         
    /** Accept a monster entry
      @param anObject object to accept */
    public void accept(DxxMonsterEntry anObject) { accept((DxxCreature)anObject); }
    
    /** Accept a race entry
      @param anObject object to accept */
    public void accept(DxxRace anObject) { accept((DxxCreature)anObject); }
    
    /** Accept a condition
      @param anObject object to accept */
    public void accept(DxxCondition anObject) { accept((GeminiObject)anObject); }
    
    /** Accept a dXX System
      @param anObject object to accept */
    public void accept(DxxSystem anObject) { accept((GamingSystem)anObject); }
    
    /** Accept a template
      @param anObject object to accept */
    public void accept(DxxTemplate anObject) { accept((GeminiObject)anObject); }
        
    /** Accept a skill
      @param anObject object to accept */
    public void accept(DxxSkill anObject) { accept((Feature)anObject); }
    
    /** Accept an abstract creature type
      @param anObject object to accept */
    public void accept(DxxAbstractCreatureType anObject) { accept((GeminiObject)anObject); }
          
    /** Accept a creature type
      @param anObject object to accept */
    public void accept(DxxCreatureType anObject) { accept((DxxAbstractCreatureType)anObject); }
         
    /** Accept a creature subtype
      @param anObject object to accept */
    public void accept(DxxCreatureSubType anObject) { accept((DxxAbstractCreatureType)anObject); }
   
    /** Accept a class
      @param anObject object to accept */
    public void accept(DxxClass anObject) { accept((GeminiObject)anObject); }   
        
    /** Accept a magic effect
      @param anObject object to accept */
    public void accept(DxxMetaphysicalEffect anObject) { accept((Feature)anObject); }
    
    /** Accept a magic spell
      @param anObject object to accept */
     public void accept(DxxSpell anObject) { accept((DxxMetaphysicalEffect)anObject); }
    
    /** Accept a psionic power
      @param anObject object to accept */
     public void accept(DxxPsionicPower anObject) { accept((DxxMetaphysicalEffect)anObject); }
    
     /** Accept a metaphysical effect group
      @param anObject object to accept */
    public void accept(DxxMetaphysicalGroup anObject) { accept((ObjectGroup)anObject); }
    
}
