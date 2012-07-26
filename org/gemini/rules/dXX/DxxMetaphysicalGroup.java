/*
 * DxxMetaphysicalGroup.java
 *
 * Created on November 14, 2005, 9:23 AM
 */

package org.gemini.rules.dXX;
import org.gemini.ObjectGroup;
import org.gemini.Feature;
import java.util.ArrayList;
import org.gemini.GenderNamedObject;
import org.gemini.sources.SourceReference;
import org.gemini.AbbreviatedObject;

/**
 * Defines a base Metaphysical Group, which we'll use to construct Domains, Schools and Disciplines.
 
  <b>Licensing Notice</b><br>
   The rules defined in this file are provided under the terms of the Open Gaming 
   License v1.0a. The text of this license can be viewed 
   <a href="http://www.wizards.com/d20/files/OGLv1.0a.rtf">here</a>. 
 
 
 @author  Jaeden
 */
public abstract class DxxMetaphysicalGroup<MetaphysicalType extends DxxMetaphysicalEffect> 
    extends ObjectGroup<MetaphysicalType> implements GenderNamedObject
{
    /** Construct an DxxMetaphysicalGroup
     *  @param _name name of the DxxMetaphysicalGroup.
     *  @param _src Source reference for the object group
     *  @param _parent Parent DxxMetaphysicalGroup (leave null for top-level groups) */     
    public DxxMetaphysicalGroup(String _name, SourceReference<?> _src, DxxMetaphysicalGroup<MetaphysicalType> _parent)
    {
        super(_name, _src, _parent);                
    }

    /** Masculine practitioner name */ private String practitionerMale = null; 
    /** Feminine practitioner name */ private String practitionerFemale = null; 
    
    /** List of opposed groups */
    private ArrayList<DxxMetaphysicalGroup<?>> opposed = new ArrayList<DxxMetaphysicalGroup<?>>();
    
    /** List of equivalent groups (for stuff like psionics-magic transparency) */
    private ArrayList<DxxMetaphysicalGroup<?>> equivalent = new ArrayList<DxxMetaphysicalGroup<?>>();
    
    /** Accessor for the equivalent groups list 
     *  @return equivalent list */
    public ArrayList<DxxMetaphysicalGroup<?>> getEquivalentGroups() { return equivalent; }
    
    /** Accessor for the opposed groups list 
     *  @return opposed list */
    public ArrayList<DxxMetaphysicalGroup<?>> getOpposedGroups() { return opposed; }
    
       /** Get masculine name (returns base group name if masculine is null) 
     *  @return masculine name */
    public String getMasculineName() 
    { 
        return (practitionerMale == null) ? getName() : practitionerMale; 
    }
    
    /** Get feminine name (returns masculine name if feminine is null)
     *  @return feminine name */
    public String getFeminineName() 
    {
        return (practitionerFemale == null) ? getMasculineName() : practitionerFemale; 
    }
    
    /** Set masculine name
     *  @param _name new name */
    public void setMasculineName(String _name) { practitionerMale = _name; }
    
    /** Set feminine name
     *  @param _name new feminine name */
    public void setFeminineName(String _name) { practitionerFemale = _name; }
    
    /** Accept a Visitor
      @param aVisitor visitor to accept */        
    public void accept(org.gemini.GeminiObjectVisitor aVisitor) {aVisitor.accept(this); }  
}
