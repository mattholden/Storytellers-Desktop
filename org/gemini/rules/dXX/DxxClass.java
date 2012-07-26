/*
 * DxxClass.java
 *
 * Created on November 11, 2005, 11:38 PM
 */

package org.gemini.rules.dXX;
import org.slage.framework.Attribute;
import org.gemini.sources.SourceReference;
import org.gemini.GeminiObject;
import org.gemini.FeatureList;
import org.gemini.Dice;
import org.gemini.GenderNamedObject;
import java.util.ArrayList;
import org.gemini.Progression;
import org.gemini.AbbreviatedObject;

/**
 * Defines a dXX character class. 

  <b>Licensing Notice</b><br>
   The rules defined in this file are provided under the terms of the Open Gaming 
   License v1.0a. The text of this license can be viewed 
   <a href="http://www.wizards.com/d20/files/OGLv1.0a.rtf">here</a>. 
 

 * @author  Jaeden
 */
public class DxxClass extends GeminiObject 
    implements GenderNamedObject, AbbreviatedObject.Mutable
{
    
    /** Feature lists of Features that will be added to the character at each level  */
    private FeatureList[] features = new FeatureList[DxxSystem.getMaxLevel()];
    
    /** Feminine name of the class - if class is Sorcerer, feminine name is Sorceress */
    private String feminineName = null;
    
    /** Abbreviation for the class - Brb, Sorc, Brd, Clr, etc. */
    private String abbreviation = null;
    
    /** List of DxxSkills that are class skills to this Class */
    private ArrayList<DxxSkill> classSkills = new ArrayList<DxxSkill>();
    
    /** List of DxxSkills that are off limits to this Class (Unavailable skills are a nod to dXX 3.0) */
    private ArrayList<DxxSkill> unavailableSkills = new ArrayList<DxxSkill>();
    
    /** Creates a new instance of DxxClass  
         @param _name Name of the Class
         @param _src SourceReference for the source of this Class */
    public DxxClass(String _name, SourceReference<?> _src)
    {
        super(_name, _src);
        
        // give us real feature lists to work with
        for (int i = 0; i < DxxSystem.getMaxLevel(); i++)
            features[i] = new FeatureList();
        
        setAttribute(new Attribute<String>("Specialization Type", null));    
        
        setAttribute(new Attribute<Boolean>("NPC Use Only", new Boolean(false)));
        
        setAttribute(new Attribute<Progression<Integer>>("Fortitude Progression", DxxSystem.SAVINGTHROW_POOR));
        setAttribute(new Attribute<Progression<Integer>>("Reflex Progression", DxxSystem.SAVINGTHROW_POOR));
        setAttribute(new Attribute<Progression<Integer>>("Will Progression", DxxSystem.SAVINGTHROW_POOR));
        setAttribute(new Attribute<Progression<Integer>>("Base Attack Bonus Progression", DxxSystem.BASEATTACK_AVERAGE));
        
        setAttribute(new Attribute<Integer>("Skill Points per Level", new Integer(4))); 
        setAttribute(new Attribute<Dice>("Hit Dice", null));
        
        
        // TODO: Spell/PsiPower sets (are these Features?)
    }
      
    /** Get abbreviation
     * @return abbreviation - if null, the first 3 letters */
    public String getAbbreviation()
    {
        if (abbreviation == null)
            abbreviation = getName().substring(0, 3);
        return abbreviation;
    }
    
    /** Set abbreviation
     * @param _abbrev abbreviation. Usually 3 letters. */
    public void setAbbreviation(String _abbrev)
    {
        abbreviation = _abbrev;
    }
  
    
    /** Get masculine name
     *  @return masculine name */
    public String getMasculineName() { return getName(); }
    
    /** Get feminine name (returns masculine name if feminine is null)
     *  @return feminine name */
    public String getFeminineName() 
    {
        return (feminineName == null) ? getName() : feminineName; 
    }
    
    /** Set masculine name
     *  @param _name new name */
    public void setMasculineName(String _name) { setName(_name); }
    
    /** Set feminine name
     *  @param _name new feminine name */
    public void setFeminineName(String _name) { feminineName = _name; }
    
    /** Add a class skill to this Class
     *  @param skill Skill to add */
    public void addClassSkill(DxxSkill skill) { classSkills.add(skill); }
    
    /** Remove a class skill from this Class
     *  @param skill Skill to remove */
    public void removeClassSkill(DxxSkill skill) { classSkills.remove(skill); }
    
    /** Check if a skill is a class skill
     *  @param skill skill to check
     *  @return true if skill is a class skill */
    public boolean isClassSkill(DxxSkill skill) { return classSkills.contains(skill); }
    
     /** Add an unavailable skill to this Class
     *  @param skill Skill to add */
    public void addUnavailableSkill(DxxSkill skill) { unavailableSkills.add(skill); }
    
    /** Remove an unavailable skill from this Class (Unavailable skills are a nod to dXX 3.0)
     *  @param skill Skill to remove */
    public void removeUnavailableSkill(DxxSkill skill) { unavailableSkills.remove(skill); }
    
    /** Check if a skill is an unavailable skill (Unavailable skills are a nod to dXX 3.0)
     *  @param skill skill to check
     *  @return true if skill is an unavailable skill */
    public boolean isUnavailableSkill(DxxSkill skill) { return unavailableSkills.contains(skill); }
    
    
    
    /** Get the Features for a given level 
     *  @param level Level to get Features for 
        @return a FeatureList containing all the Features for this level 
        @throws IllegalArgumentException if 'level' is outside the bounds of legal levels */
    public FeatureList getFeaturesForLevel(int level)
    {        
        // 0th level exists but you get nothing for it...
        if (level == 0) 
            return new FeatureList();
        
        if (level < 0 || level > DxxSystem.getMaxLevel())
            throw new IllegalArgumentException(
                "The 'level' argument to getFeaturesForLevel() must be between 0 and " + 
                Integer.toString(DxxSystem.getMaxLevel()) + ".");
     
        // --level makes it zero-based for the array        
        return features[--level];
    }
    
    /** Get a DxxClassLevel representing the benefits of a given level of this class.
     *
     @param _level the level to gain
     @return a DxxClassLevel object for the appropriate level of thie DxxClass */
    public DxxClassLevel<? extends DxxClass> getLevel(int _level)
    {        
        return new DxxClassLevel<DxxClass>(this, _level);        
    }
    
    
    
    
    
    
    /** Accept a Visitor
        @param aVisitor visitor to accept */        
    public void accept(org.gemini.GeminiObjectVisitor aVisitor){
        aVisitor.accept(this); 
    }      
    
    
     
}
