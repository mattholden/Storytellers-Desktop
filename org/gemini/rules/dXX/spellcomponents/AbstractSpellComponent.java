/*
 * AbstractSpellComponent.java
 *
 * Created on November 14, 2005, 10:17 AM
 */

package org.gemini.rules.dXX.spellcomponents;

/**
 * Provides an abstract implmentation for Spell Components. 
 
  <b>Licensing Notice</b><br>
   The rules defined in this file are provided under the terms of the Open Gaming 
   License v1.0a. The text of this license can be viewed 
   <a href="http://www.wizards.com/d20/files/OGLv1.0a.rtf">here</a>. 
 

 * @author Jaeden
 */
public abstract class AbstractSpellComponent implements SpellComponent, java.io.Serializable
{
   
    
    /** Creates a new instance of AbstractSpellComponent 
         @param _desc description */
    public AbstractSpellComponent(String _desc) 
    {
        setDescription(_desc);
    }
    
    /** String description of the object */
    private String description; 
    
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
        
        return ((AbstractSpellComponent)other).description.equals(description);
    }
    
    
    /** Get component as a string
     * @return string */
    public String toString() { return description; }
    
}
