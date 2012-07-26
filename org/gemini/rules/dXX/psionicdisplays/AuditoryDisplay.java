/*
 * AuditoryDisplay.java
 *
 * Created on November 17, 2005, 12:14 AM
 */

package org.gemini.rules.dXX.psionicdisplays;

/**
 * Defines auditory psionic displays.
 
  <b>Licensing Notice</b><br>
   The rules defined in this file are provided under the terms of the Open Gaming 
   License v1.0a. The text of this license can be viewed 
   <a href="http://www.wizards.com/d20/files/OGLv1.0a.rtf">here</a>. 
 

 * @author  Jaeden
 */
public class AuditoryDisplay extends AbstractPsionicDisplay
{    
    /** Creates a new psionic display
         @param _desc description */
    public AuditoryDisplay(String _desc) 
    {
        super(_desc);
    }    
    
    /** Creates a new psionic display  */
    public AuditoryDisplay() 
    {
        super(
        "A bass-pitched hum issues from the manifester’s vicinity or in the " +
        "vicinity of the power’s subject (manifester’s choice), eerily akin to " +
        "many deep-pitched voices. The sound grows in a second from hardly " +
        "noticeable to as loud as a shout strident enough to be heard within " +
        "100 feet. At the manifester’s option, the instantaneous sound can be " +
        "so soft that it can be heard only within 15 feet with a successful DC 10 " +
        "Listen check. Some powers describe unique auditory displays."
        );
    }    
    
    
}
