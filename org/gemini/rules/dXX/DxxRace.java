/*
 * DxxRace.java
 *
 * Created on November 17, 2005, 11:40 PM
 */

package org.gemini.rules.dXX;
import org.gemini.sources.SourceReference;
import org.slage.framework.Attribute;
import org.gemini.Dice;

/**
 * A race that a player character can be.
 
  <b>Licensing Notice</b><br>
   The rules defined in this file are provided under the terms of the Open Gaming 
   License v1.0a. The text of this license can be viewed 
   <a href="http://www.wizards.com/d20/files/OGLv1.0a.rtf">here</a>. 
 

 * @author  Jaeden
 */
public class DxxRace extends DxxCreature
{
    
    /** Creates a new instance of DxxRace 
      @param _name name of the race
      @param _src source reference for the race */
    public DxxRace(String _name, SourceReference _src)
    {
        super(_name, _src);
        
        // Height and weight dice for males and females
        setAttribute(new Attribute<Dice>("Height (Male)", null));
        setAttribute(new Attribute<Dice>("Height (Female)", null));
        setAttribute(new Attribute<Dice>("Weight (Male)", null));
        setAttribute(new Attribute<Dice>("Weight (Female)", null));
        
    }
    
    
    /** Accept a Visitor
        @param aVisitor visitor to accept */        
    public void accept(org.gemini.GeminiObjectVisitor aVisitor){
        aVisitor.accept(this); 
    }      
}
