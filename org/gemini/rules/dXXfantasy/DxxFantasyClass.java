/*
 * DxxFantasyClass.java
 *
 * Created on November 13, 2005, 2:34 AM
 */

package org.gemini.rules.dXXfantasy;
import org.gemini.rules.dXX.DxxClass;
import org.gemini.rules.dXX.DxxClassLevel;
import org.slage.framework.Attribute;
import org.gemini.sources.SourceReference;

/**
 * Defines the extensions to DxxClass to support the dXX Fantasy game.
 
  <b>Licensing Notice</b><br>
   The rules defined in this file are provided under the terms of the Open Gaming 
   License v1.0a. The text of this license can be viewed 
   <a href="http://www.wizards.com/d20/files/OGLv1.0a.rtf">here</a>. 
 

 * @author  Jaeden
 */
public class DxxFantasyClass extends DxxClass
{
    
    /** Creates a new instance of DxxFantasyClass 
        @param _name name of the class
        @param _src SourceReference for the source of this class 
      */
    public DxxFantasyClass(String _name, SourceReference<?> _src) 
    {
        super(_name, _src);
        
        setAttribute(new Attribute<Boolean>("Literate", new Boolean(true)));
        
        // valid values are 1-3
        setAttribute(new Attribute<Integer>("Starting Age Category", 1));
        
        // TODO: Bonus Languages (not automatic ones; those are Features!)
    }
 
    
      /** Get a DxxClassLevel representing the benefits of a given level of this class.
     *
     @param _level the level to gain
     @return a DxxClassLevel object for the appropriate level of this DxxClass */
    public DxxClassLevel<? extends DxxFantasyClass> getLevel(int _level)
    {        
        return new DxxClassLevel<DxxFantasyClass>(this, _level);        
    }
    
    /** Accept a Visitor
        @param aVisitor visitor to accept */        
    public void accept(org.gemini.GeminiObjectVisitor aVisitor){
        aVisitor.accept(this); 
    }      
}
