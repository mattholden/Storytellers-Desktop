/*
 * MentalDisplay.java
 *
 * Created on November 17, 2005, 12:14 AM
 */

package org.gemini.rules.dXX.psionicdisplays;

/**
 * Defines Mental psionic displays.
 
  <b>Licensing Notice</b><br>
   The rules defined in this file are provided under the terms of the Open Gaming 
   License v1.0a. The text of this license can be viewed 
   <a href="http://www.wizards.com/d20/files/OGLv1.0a.rtf">here</a>. 
 

 * @author  Jaeden
 */
public class MentalDisplay extends AbstractPsionicDisplay
{    
    /** Creates a new psionic display
         @param _desc description */
    public MentalDisplay(String _desc) 
    {
        super(_desc);
    }    
    
    /** Creates a new psionic display  */
    public MentalDisplay() 
    {
        super(
        "A subtle chime rings once in the minds of creatures within " +
        "15 feet of either the manifester or the subject (at the manifester’s " +
        "option). At the manifester’s option, the chime can ring continuously " +
        "for the power’s duration. Some powers describe unique mental displays."
        );
    }    
    
    
}
