/*
 * SavingThrowHalf.java
 *
 * Created on November 17, 2005, 3:29 AM
 */

package org.gemini.rules.dXX.savingthrows;
import org.gemini.rules.dXX.DxxSavingThrow;

/**
 * Defines the effect that a saving throw halves the effects of a condition or effect.
 
  <b>Licensing Notice</b><br>
   The rules defined in this file are provided under the terms of the Open Gaming 
   License v1.0a. The text of this license can be viewed 
   <a href="http://www.wizards.com/d20/files/OGLv1.0a.rtf">here</a>. 
 

 * @author  Jaeden
 */
public class SavingThrowHalf extends AbstractSavingThrowEffect
{
     /** Creates a new instance of SavingThrowHalf 
         @param _save Saving throw to make
         @param _desc description */
    public SavingThrowHalf(DxxSavingThrow _save, String _desc) 
    {
       super(_save, SRD, _desc);
    }
    
     /** Creates a new instance of SavingThrowHalf 
         @param _save Saving throw to make         */
    public SavingThrowHalf(DxxSavingThrow _save) 
    {
       super(_save, SRD, "Deals damage, and a successful saving throw halves the damage taken (round down).");
    }
    
}