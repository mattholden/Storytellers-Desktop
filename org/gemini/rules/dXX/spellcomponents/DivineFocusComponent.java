/*
 * DivineFocusComponent.java
 *
 * Created on November 14, 2005, 10:17 AM
 */

package org.gemini.rules.dXX.spellcomponents;

/**
 * Defines a divine focus spell component. 
 
  <b>Licensing Notice</b><br>
   The rules defined in this file are provided under the terms of the Open Gaming 
   License v1.0a. The text of this license can be viewed 
   <a href="http://www.wizards.com/d20/files/OGLv1.0a.rtf">here</a>. 
 

 * @author  Jaeden
 */
public class DivineFocusComponent extends AbstractSpellComponent
{
    /** Creates a new instance of DivineFocusComponent 
        @param _desc Description - in this case, a description of the focus. 
        <code>null</code> is valid. */
    public DivineFocusComponent(String _desc) { super(_desc);  }
    
    /** Creates a new instance of DivineFocusComponent without a description. */        
    public DivineFocusComponent() { super(null);  }
 
    
    /** String representation
     * @return string representation */
    public String toString()     {
        return "Divine Focus Component" + 
                       ((getDescription() == null) ? "" : (": " + getDescription()));
    }
}
