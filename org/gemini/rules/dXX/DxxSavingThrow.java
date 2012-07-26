/*
 * DxxSavingThrow.java
 *
 * Created on November 17, 2005, 1:46 AM
 */

package org.gemini.rules.dXX;
import org.gemini.Statistic;
import org.gemini.sources.SourceSectionReference;
import org.gemini.sources.SourceReference;
import org.gemini.sources.SourceURL;


/**
 * Defines one of the core saving throws in dXX.

  <b>Licensing Notice</b><br>
   The rules defined in this file are provided under the terms of the Open Gaming 
   License v1.0a. The text of this license can be viewed 
   <a href="http://www.wizards.com/d20/files/OGLv1.0a.rtf">here</a>. 
 

 * @author  Jaeden
 */
public class DxxSavingThrow extends Statistic    
{        
    
    /** Source for all the saving throws */
    protected static final SourceSectionReference<SourceURL> SRD = 
            new SourceSectionReference<SourceURL>(DxxSystem.SRD35, "Combat I (Basics)");
    
    /** Fortitude */
    public static final DxxSavingThrow FORTITUDE = 
        new DxxSavingThrow("Fortitude", DxxAbilityScore.CONSTITUTION, SRD, 
        "Fortitude saves measure your ability to stand up to physical punishment or " +
        "attacks against your vitality and health. Apply your Constitution modifier" +
        " to your Fortitude saving throws.");
    
    /** Reflex */
    public static final DxxSavingThrow REFLEX = 
        new DxxSavingThrow("Reflex", DxxAbilityScore.DEXTERITY, SRD, 
        "Reflex saves test your ability to dodge area attacks. " +
        "Apply your Dexterity modifier to your Reflex saving throws.");
    
    /** Will */
     public static final DxxSavingThrow WILL = 
        new DxxSavingThrow("Will", DxxAbilityScore.WISDOM, SRD, 
        "Will saves reflect your resistance to mental influence as well as many " +
        "magical effects. Apply your Wisdom modifier to your Will saving throws.");
     
    /** Creates a new instance of DxxSavingThrow 
        @param _name Name of the saving throw 
        @param _mod The DxxAbilityScore which modifies this save 
        @param _src Source of the saving throw
        @param _desc Description of the saving throw */
    public DxxSavingThrow(String _name, DxxAbilityScore _mod, SourceReference<?> _src, String _desc) 
    {
        super(_name, _src, _desc);
        modifierScore = _mod;
    }
    

    /** The Ability Score that modifies this save */
    private DxxAbilityScore modifierScore;     
            
    /** Accessor for ability score modifier 
     *  @return ability score which modifies this save */
    public DxxAbilityScore getAbilityScore() { return modifierScore; }
    
    /** Mutator for ability score modifier
     *  @param _mod ability score which modifies this save */
    public void setAbilityScore(DxxAbilityScore _mod) { modifierScore = _mod; }
        
   
}
