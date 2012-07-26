/*
 * VisualDisplay.java
 *
 * Created on November 17, 2005, 12:14 AM
 */

package org.gemini.rules.dXX.psionicdisplays;

/**
 * Defines Visual psionic displays.
 
  <b>Licensing Notice</b><br>
   The rules defined in this file are provided under the terms of the Open Gaming 
   License v1.0a. The text of this license can be viewed 
   <a href="http://www.wizards.com/d20/files/OGLv1.0a.rtf">here</a>. 
 

 * @author  Jaeden
 */
public class VisualDisplay extends AbstractPsionicDisplay
{    
    /** Creates a new psionic display
         @param _desc description */
    public VisualDisplay(String _desc) 
    {
        super(_desc);
    }    
    
    /** Creates a new psionic display  */
    public VisualDisplay() 
    {
        super(
        "The manifester’s eyes burn like points of silver fire while the power" +
        " remains in effect. A rainbow-flash of light sweeps away from the " +
        "manifester to a distance of 5 feet and then dissipates, unless a unique " +
        "visual display is described. This is the case when the Display entry " +
        "includes “see text,” which means that a visual effect is described " +
        "somewhere in the text of the power."
        );
    }    
    
    
}

