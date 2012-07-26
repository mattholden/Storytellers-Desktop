/*
 * DxxFantasyPrestigeClass.java
 *
 * Created on November 17, 2005, 4:23 AM
 */

package org.gemini.rules.dXXfantasy;
import org.gemini.rules.dXX.PrestigeClass;
import org.gemini.sources.SourceReference;

/**
 * Defines a Prestige Class in dXX Fantasy. Simply extends the DxxFantasyClass
 * implementing the Prestige Class interface.
 
  <b>Licensing Notice</b><br>
   The rules defined in this file are provided under the terms of the Open Gaming 
   License v1.0a. The text of this license can be viewed 
   <a href="http://www.wizards.com/d20/files/OGLv1.0a.rtf">here</a>. 
 
 
 * @author  Jaeden
 */
public class DxxFantasyPrestigeClass 
extends DxxFantasyClass implements PrestigeClass
{
    
   /** Creates a new instance of DxxFantasyClass 
        @param _name name of the class
        @param _src SourceReference for the source of this class 
      */
    public DxxFantasyPrestigeClass(String _name, SourceReference<?> _src) 
    {
        super(_name, _src);      
    }
    
}
