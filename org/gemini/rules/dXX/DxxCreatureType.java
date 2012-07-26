/*
 * DxxCreatureType.java
 *
 * Created on November 11, 2005, 11:47 PM
 */

package org.gemini.rules.dXX;
import org.gemini.GeminiObject;
import org.slage.framework.Attribute;
import org.gemini.sources.SourceReference;
import org.gemini.Dice;
import org.gemini.Progression;

/**
  DxxCreatureType is the dXX core definition of creature types and subtypes.
  It provides Base Attack Bonus and saving throw progressions, as well as 
  hit dice and skills per level. Creature Type traits are not covered here;
  they are handled in monster descriptions because the traits are not universal
  to creatures of a type and removing the traits in monsters without them is 
  more difficult than adding them where they apply.  
 
  <b>Licensing Notice</b><br>
   The rules defined in this file are provided under the terms of the Open Gaming 
   License v1.0a. The text of this license can be viewed 
   <a href="http://www.wizards.com/d20/files/OGLv1.0a.rtf">here</a>. 
 

  @author  Jaeden
 */ 
public class DxxCreatureType extends DxxAbstractCreatureType
{
    
    /** Creates a new instance of DxxCreatureType 
        @param _name name of the creature type
        @param _src reference to the source of the creature type */
    public DxxCreatureType(String _name, SourceReference<?> _src) 
    {
        super(_name, _src);
                
        setAttribute(new Attribute<Progression<Integer>>("Fortitude Progression", DxxSystem.SAVINGTHROW_POOR));
        setAttribute(new Attribute<Progression<Integer>>("Reflex Progression", DxxSystem.SAVINGTHROW_POOR));
        setAttribute(new Attribute<Progression<Integer>>("Will Progression", DxxSystem.SAVINGTHROW_POOR));
        setAttribute(new Attribute<Progression<Integer>>("Base Attack Bonus Progression", DxxSystem.BASEATTACK_AVERAGE));
         
        setAttribute(new Attribute<Integer>("Skill Points per Level", new Integer(4))); 
        setAttribute(new Attribute<Integer>("Hit Dice", new Integer(6)));
    }
    
    
    
    
    
    
    /** Accept a Visitor
        @param aVisitor visitor to accept */        
    public void accept(org.gemini.GeminiObjectVisitor aVisitor){
        aVisitor.accept(this); 
    }      
    
    
}
