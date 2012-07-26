/*
 * OlfactoryDisplay.java
 *
 * Created on November 17, 2005, 12:14 AM
 */

package org.gemini.rules.dXX.psionicdisplays;

/**
 * Defines Olfactory psionic displays.
 
  <b>Licensing Notice</b><br>
   The rules defined in this file are provided under the terms of the Open Gaming 
   License v1.0a. The text of this license can be viewed 
   <a href="http://www.wizards.com/d20/files/OGLv1.0a.rtf">here</a>. 
 

 * @author  Jaeden
 */
public class OlfactoryDisplay extends AbstractPsionicDisplay
{    
    /** Creates a new psionic display
         @param _desc description */
    public OlfactoryDisplay(String _desc) 
    {
        super(_desc);
    }    
    
    /** Creates a new psionic display  */
    public OlfactoryDisplay() 
    {
        super(
        "An odd but familiar odor brings to mind a brief mental flash of a " +
        "long-buried memory. The scent is difficult to pin down, and no two " +
        "individuals ever describe it the same way. The odor originates from " +
        "the manifester and spreads to a distance of 20 feet, then fades in " +
        "less than a second (or lasts for the duration, at the manifester’s option)."
        );
    }    
    
    
}
