/*
 * SomaticComponent.java
 *
 * Created on November 14, 2005, 10:17 AM
 */

package org.gemini.rules.dXX.spellcomponents;

/**
 * Defines a somatic spell component. 
 
  <b>Licensing Notice</b><br>
   The rules defined in this file are provided under the terms of the Open Gaming 
   License v1.0a. The text of this license can be viewed 
   <a href="http://www.wizards.com/d20/files/OGLv1.0a.rtf">here</a>. 
 

 * @author  Jaeden
 */
public class SomaticComponent extends AbstractSpellComponent
{
    /** Creates a new instance of SomaticComponent 
        @param _desc Description - in this case, any specific gestures that need to be performed. 
        <code>null</code> is valid. */
    public SomaticComponent(String _desc) { super(_desc);  }
    
    /** Creates a new instance of SomaticComponent without specific gestures. */        
    public SomaticComponent() { super(null);  }
    
    
    /** String representation
     * @return string representation */
    public String toString()     {
        return "Somatic Component" + 
                       ((getDescription() == null) ? "" : (": " + getDescription()));
    }
}
