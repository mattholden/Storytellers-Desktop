/*
 * VerbalComponent.java
 *
 * Created on November 14, 2005, 10:17 AM
 */

package org.gemini.rules.dXX.spellcomponents;

/**
 * Defines a verbal spell component. 
 
  <b>Licensing Notice</b><br>
   The rules defined in this file are provided under the terms of the Open Gaming 
   License v1.0a. The text of this license can be viewed 
   <a href="http://www.wizards.com/d20/files/OGLv1.0a.rtf">here</a>. 
 

 * @author  Jaeden
 */
public class VerbalComponent extends AbstractSpellComponent
{
    /** Creates a new instance of VerbalComponent 
        @param _desc Description - in this case, any specific words that need to be said. 
        <code>null</code> is valid. */
    public VerbalComponent(String _desc) { super(_desc);  }
    
    /** Creates a new instance of VerbalComponent without specific vocabulary. */        
    public VerbalComponent() { super(null);  }
    
    
    /** String representation
     * @return string representation */
    public String toString()     {
        return "Verbal Component" + 
                       ((getDescription() == null) ? "" : (": " + getDescription()));
    }
}
