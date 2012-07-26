/*
 * SavingThrowEffect.java
 *
 * Created on November 14, 2005, 10:14 AM
 */

package org.gemini.rules.dXX.savingthrows;
import org.gemini.DescribedObject;
import org.gemini.rules.dXX.DxxSavingThrow;
import org.gemini.sources.ObjectFromSource;

/**
 * Provides an interface for Saving Throws. This does not define the
 * actual saves, but rather reactions to them, such that a spell or magical effect
 * can define the effect of a saving throw on it.
 
  <b>Licensing Notice</b><br>
   The rules defined in this file are provided under the terms of the Open Gaming 
   License v1.0a. The text of this license can be viewed 
   <a href="http://www.wizards.com/d20/files/OGLv1.0a.rtf">here</a>. 
 

 * @author  Jaeden
 */
public interface SavingThrowEffect 
    extends DescribedObject.Mutable, ObjectFromSource.Mutable, java.io.Serializable
{
    /** Get the name of the saving throw which will affect the action.
     *  In dXX, this will be Fortitude, Reflex or Will.
     *  @return name of the appropriate saving throw        */
    public DxxSavingThrow getSavingThrow();
    
     /** Set the name of the saving throw which will affect the action.
     *  In dXX, this will be Fortitude, Reflex or Will.
     *  @param _save the appropriate saving throw        */
    public void setSavingThrow(DxxSavingThrow _save);    
    
}
