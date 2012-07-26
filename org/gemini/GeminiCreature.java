/*
 * Creature.java
 *
 * Created on November 9, 2005, 9:47 PM
 */

package org.gemini;
import org.gemini.sources.SourceReference;
import org.slage.framework.Attribute;
import java.util.LinkedHashMap;

/**
 * A Creature entry detines a type of creature - NOT a creature that walks around at runtime.
 * Runtime creatures should be generated as GeminiCharacters.
 *
 * @see GeminiCharacter
 *
 * @author  Jaeden
 */
public class GeminiCreature extends GeminiObject
{
    
    
    
    /** Creates a new instance of Creature 
      @param _name name of the creature type 
      @param _src SourceReference to the source */
    public GeminiCreature(String _name, SourceReference<?> _src) 
    {
        super (_name, _src);
        GamingSystem.addSystemAttributes(this);
         
        
    }
    
    
    
    /** Accept a Visitor
      @param aVisitor visitor to accept */        
    public void accept(GeminiObjectVisitor aVisitor){
        aVisitor.accept(this); 
    }  
}
