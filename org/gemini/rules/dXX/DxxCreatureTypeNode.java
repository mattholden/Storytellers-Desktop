/*
 * DxxCreatureTypeNode.java
 *
 * Created on November 18, 2005, 12:16 PM
 */

package org.gemini.rules.dXX;
import java.util.ArrayList;
import java.io.Serializable;

/**
 * Defines a node to store a creature's Creature Type and Subtypes in. 
 *
  <b>Licensing Notice</b><br>
   The rules defined in this file are provided under the terms of the Open Gaming 
   License v1.0a. The text of this license can be viewed 
   <a href="http://www.wizards.com/d20/files/OGLv1.0a.rtf">here</a>. 
 

 * @author  Jaeden
 */
public class DxxCreatureTypeNode implements Serializable
{
   
    
    /** The creature type */
    private DxxCreatureType type;
    
    /** The creature subtypes */
    private ArrayList<DxxCreatureSubType> subtypes = 
        new ArrayList<DxxCreatureSubType>();   
   
    /** The traits */
    private ArrayList<DxxTrait> traits = 
        new ArrayList<DxxTrait>();   
  
    /** Creates a new instance of DxxCreatureTypeNode 
        @param _type the Creature type */
    public DxxCreatureTypeNode(DxxCreatureType _type)
    {
        type = _type;        
        traits.addAll(_type.getTraits());
    }
        
    /** Add a SubType
     *  @param _SubType SubType to add */
    public void addSubType(DxxCreatureSubType _SubType) 
    { 
        subtypes.add(_SubType); 
        traits.addAll(_SubType.getTraits());
    }
    
    /** Remove a SubType
     *  @param _SubType SubType to remove */
    public void removeSubType(DxxCreatureSubType _SubType) 
    {
        subtypes.remove(_SubType); 
        traits.removeAll(_SubType.getTraits());
    }
    
    /** Get count of SubTypes
     *  @return SubType count */
    public int getSubTypeCount() { return subtypes.size(); }
    
    /** Get all the traits for this creature type/subtypes
     *  @return list of traits
     * TODO: Do this without breaking encapsulation */
    public ArrayList<DxxTrait> getTraits() { return traits; }
    
}
