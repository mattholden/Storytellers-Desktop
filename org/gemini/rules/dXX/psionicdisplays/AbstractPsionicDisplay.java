/*
 * PsionicDisplay.java
 *
 * Created on November 16, 2005, 11:39 PM
 */

package org.gemini.rules.dXX.psionicdisplays;
import org.gemini.DescribedObject;
import org.gemini.sources.ObjectFromSource;
import java.io.Serializable;
import org.slage.framework.NamedObject;
import org.gemini.sources.SourceReference;
import org.gemini.sources.SourceSectionReference;
import org.gemini.sources.SourceURL;


/**
 * Defines a Display for how affected creatures perceive
 * psionic effects.
 
  <b>Licensing Notice</b><br>
   The rules defined in this file are provided under the terms of the Open Gaming 
   License v1.0a. The text of this license can be viewed 
   <a href="http://www.wizards.com/d20/files/OGLv1.0a.rtf">here</a>. 
 

 * @author  Jaeden
 */
public abstract class AbstractPsionicDisplay implements PsionicDisplay
{
     
    /** String description of the object */
    private String description;        
    
    /** Creates a new instance of PsionicDisplay 
         @param _desc description */
    public AbstractPsionicDisplay(String _desc) 
    {
        setDescription(_desc);
    }
    
    /** Accessor for description
     @return description */
    public String getDescription() { return description; }
    
    /** Mutator for description
     @param _desc new description */
    public void setDescription(String _desc) { description = _desc; }
        
    /** Test equality
     * @param other object to test
     * @return true if this == other */
    public boolean equals(Object other)
    {
        if (other.getClass().equals(getClass()) == false)
            return false;
        
        return ((PsionicDisplay)other).getDescription().equals(getDescription());
    }
    
    
    /** Get component as a string
     * @return string */
    public String toString() { return getDescription(); }
          
}
