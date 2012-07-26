/*
 * DxxClassLevel.java
 *
 * Created on November 13, 2005, 12:02 AM
 */

package org.gemini.rules.dXX;
import org.gemini.FeatureList;
import org.gemini.Specialized;

/**
 * Used to store a level of a DxxClass.

  <b>Licensing Notice</b><br>
   The rules defined in this file are provided under the terms of the Open Gaming 
   License v1.0a. The text of this license can be viewed 
   <a href="http://www.wizards.com/d20/files/OGLv1.0a.rtf">here</a>. 
 

 * @author  Jaeden
 */
public class DxxClassLevel<Type extends DxxClass> 
	extends Specialized<Type>
	implements DxxLevel<Type>
{   
    /** The level gained in this class 
      (not total character level) */
    private int level;
    
    /** Features to gain at this level */
    private FeatureList features;
        
    /** Creates a new instance of DxxClassLevel 
      @param _class Class we gained a level in
      @param _level Level gained in that class 
                    (not total character level!) */
    public DxxClassLevel(Type _class, int _level) 
    {
        object = _class;
        level = _level;
        features = _class.getFeaturesForLevel(_level);
          
        try
        {
            specializationType = (String)_class.getAttributeValue("Specialization Type");                            
        }
        catch (Exception e) { LOG.error(e); }                                               

        try
        {
            specializationClass = Class.forName(specializationType);
        }
        catch (Exception e) { LOG.error(e); }                    
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
        return (specialization == null) 
                ? (object.getName() + " " + Integer.toString(level)) 
                :  (object.getName() + " [" + specialization.toString() + "]" +
                   " " + Integer.toString(level));                                
    }

    /** Test two DxxClassLevels for equality 
    @param that object to test against
    @return true if this == that */
    public boolean equals(Object that)
    {
        if (that.getClass().equals(this.getClass()) == false)
            return false;

        DxxClassLevel Node = (DxxClassLevel)that;
        return (super.equals(Node) && this.level == Node.level);                
    }
    
    
    /** Logger instance */
    protected static final org.apache.commons.logging.Log LOG = 
        org.apache.commons.logging.LogFactory.getLog(DxxClassLevel.class);


}
