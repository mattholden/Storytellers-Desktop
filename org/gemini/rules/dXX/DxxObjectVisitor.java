/*
 * DxxObjectVisitor.java
 *
 * Created on November 12, 2005, 9:50 AM
 */

package org.gemini.rules.dXX;
import org.gemini.GeminiObjectVisitor;


/**
 * Used in double dispatch to identify the type of GeminiObject-based objects
 *
 * @author Jaeden
 */
public interface DxxObjectVisitor extends GeminiObjectVisitor
{
    /** Accept a DxxAbility
      @param anObject object to accept */        
    public void accept(DxxAbility anObject);
    
    /** Accept a DxxCharacter
      @param anObject object to accept */        
    public void accept(DxxCharacter anObject);
    
     /** Accept a DxxClass
      @param anObject object to accept */        
    public void accept(DxxClass anObject);
    
    /** Accept a DxxCondition
      @param anObject object to accept */        
    public void accept(DxxCondition anObject);
        
    /** Accept a DxxCreature
      @param anObject object to accept */        
    public void accept(DxxCreature anObject);
            
    /** Accept a DxxCreatureType
      @param anObject object to accept */        
    public void accept(DxxCreatureType anObject);
                
    /** Accept a DxxFeat
      @param anObject object to accept */        
    public void accept(DxxFeat anObject);    
               
    /** Accept a DxxSkill
      @param anObject object to accept */        
    public void accept(DxxSkill anObject);
         
      /** Accept a DxxSystem
      @param anObject object to accept */        
    public void accept(DxxSystem anObject);
    
      /** Accept a DxxTemplate
      @param anObject object to accept */        
    public void accept(DxxTemplate anObject);
         
      /** Accept a DxxMetaphysicalEffect
      @param anObject object to accept */        
    public void accept(DxxMetaphysicalEffect anObject);    
     
       /** Accept a DxxMetaphysicalGroup
      @param anObject object to accept */        
    public void accept(DxxMetaphysicalGroup anObject);    
     
      /** Accept a DxxSpell
      @param anObject object to accept */        
    public void accept(DxxSpell anObject);
            
       /** Accept a DxxPsionicPower
      @param anObject object to accept */        
    public void accept(DxxPsionicPower anObject);
    
      /** Accept a trait
      @param anObject object to accept */
    public void accept(DxxTrait anObject);
    
     /** Accept a monster entry
      @param anObject object to accept */
    public void accept(DxxMonsterEntry anObject);
    
    /** Accept a race entry
      @param anObject object to accept */
    public void accept(DxxRace anObject);
    
     /** Accept an abstract creature type
      @param anObject object to accept */
    public void accept(DxxAbstractCreatureType anObject);

    /** Accept a creature subtype
      @param anObject object to accept */
    public void accept(DxxCreatureSubType anObject);
}
