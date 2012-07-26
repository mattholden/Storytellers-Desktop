/*
 * DxxClassLevel.java
 *
 * Created on November 13, 2005, 12:02 AM
 */

package org.gemini.rules.dXX;
import org.gemini.FeatureList;

/**
 * Used to store a "creature level" for monster races.

  <b>Licensing Notice</b><br>
   The rules defined in this file are provided under the terms of the Open Gaming 
   License v1.0a. The text of this license can be viewed 
   <a href="http://www.wizards.com/d20/files/OGLv1.0a.rtf">here</a>. 
 

 * @author  Jaeden
 */
public class DxxCreatureLevel<Type extends DxxAbstractCreatureType> 	
	implements DxxLevel<Type>
{   
    /** The level gained in this class 
      (not total character level) */
    private int level;
    
    /** Features to gain at this level */
    private FeatureList features = new FeatureList();
        
    /** The creature type */
    private Type creatureType;
    
    /** Creates a new instance of DxxCreatureLevel 
      @param _type Creature Type we gained a level in
      @param _level Level gained in that Creature Type 
                    (not total character level!) */
    public DxxCreatureLevel(Type _type, int _level) 
    {
       if (_level == 1)
           features.addFeatures(_type.getTraits());
        level = _level;
        creatureType = _type;        
    }
    
    /** Get the level gained in this class 
      @return level */
    public int getLevel() { return level; }
        
    /** Get the features for this level
     * @return features */
    public FeatureList getFeatures() { return features; }  
    
     /** Get the node as a String for character sheet printing
    @return string representation */
    public String toString() 
    {
        return (creatureType.getName() + " " + Integer.toString(level));                                             
    }

    /** Test two DxxCreatureLevels for equality 
    @param that object to test against
    @return true if this == that */
    public boolean equals(Object that)
    {
        if (that.getClass().equals(this.getClass()) == false)
            return false;

        DxxCreatureLevel Node = (DxxCreatureLevel)that;
        return (Node.creatureType.equals(creatureType) && this.level == Node.level);                
    }
    
       
    /** Accessor for creature type
    @return creature type instance */
    public Type getOwner() { return creatureType; }

    /** Mutator for creature type
    @param _type creature type instance */
    public void setOwner(Type _type) { creatureType = _type; }

    /** Logger instance */
    protected static final org.apache.commons.logging.Log LOG = 
        org.apache.commons.logging.LogFactory.getLog(DxxCreatureLevel.class);


}
