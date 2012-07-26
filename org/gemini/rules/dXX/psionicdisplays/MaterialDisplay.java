/*
 * MaterialDisplay.java
 *
 * Created on November 17, 2005, 12:14 AM
 */

package org.gemini.rules.dXX.psionicdisplays;

/**
 * Defines Material psionic displays.
 
  <b>Licensing Notice</b><br>
   The rules defined in this file are provided under the terms of the Open Gaming 
   License v1.0a. The text of this license can be viewed 
   <a href="http://www.wizards.com/d20/files/OGLv1.0a.rtf">here</a>. 
 

 * @author  Jaeden
 */
public class MaterialDisplay extends AbstractPsionicDisplay
{    
    /** Creates a new psionic display
         @param _desc description */
    public MaterialDisplay(String _desc) 
    {
        super(_desc);
    }    
    
    /** Creates a new psionic display  */
    public MaterialDisplay() 
    {
        super(
        "The subject or the area is briefly slicked with a translucent, " +
        "shimmering substance. The glistening substance evaporates after 1 " +
        "round regardless of the power’s duration. Sophisticated psions " +
        "recognize the material as ectoplasmic seepage from the Astral Plane; " +
        "this substance is completely inert."
        );
    }    
    
    
}

