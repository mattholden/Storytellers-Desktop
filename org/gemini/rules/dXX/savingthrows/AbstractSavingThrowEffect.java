/*
 * AbstractSavingThrowEffect.java
 *
 * Created on November 14, 2005, 10:17 AM
 */

package org.gemini.rules.dXX.savingthrows;
import org.gemini.rules.dXX.DxxSavingThrow;
import org.gemini.sources.SourceReference;
import org.gemini.sources.SourceSectionReference;
import org.gemini.sources.SourceURL;
import org.gemini.rules.dXX.DxxSystem;

/**
 * Provides an abstract implmentation for saving throw effects 
 
  <b>Licensing Notice</b><br>
   The rules defined in this file are provided under the terms of the Open Gaming 
   License v1.0a. The text of this license can be viewed 
   <a href="http://www.wizards.com/d20/files/OGLv1.0a.rtf">here</a>. 
 

 * @author Jaeden
 */
public abstract class AbstractSavingThrowEffect implements SavingThrowEffect
{       
    
      /** Creates a new instance of AbstractSavingThrowEffect 
         @param _save Saving throw to make
       * @param _src Reference to the source 
         @param _desc description */
    public AbstractSavingThrowEffect(DxxSavingThrow _save, SourceReference<?> _src, String _desc) 
    {
        setDescription(_desc);
        setSource(_src);
        setSavingThrow(_save);
    }
    
    /** String description of the object */
    private String description; 
    
    /** Accessor for description
     @return description */
    public String getDescription() { return description; }
    
    /** Mutator for description
     @param _desc new description */
    public void setDescription(String _desc) { description = _desc; }
        
    /** Test equality
     * @param other object to test
     * @return true if this == other */
    public boolean equals(Object other)
    {
        if (other.getClass().equals(getClass()) == false)
            return false;
        
        AbstractSavingThrowEffect that = (AbstractSavingThrowEffect)other;
        return (that.description.equals(description) &&
                that.save.equals(save));
    }
    
    
    /** Get component as a string
     * @return string */
    public String toString() { 
        return save.getName() + ": " + ((description == null) ? "" : description);
    }
    
    /** Saving throw type to make */
    private DxxSavingThrow save;
    
    /** Get saving throw type to make
     *  @return saving throw type */
    public DxxSavingThrow getSavingThrow() { return save; }
        
    /** Set saving throw type to make
     *  @param _save saving throw type */
    public void setSavingThrow(DxxSavingThrow _save) { save = _save; }
    
      /** Source reference for the object */
        private SourceReference<?> source;

        /** Set the reference to the object's source
         * @param _src new source reference */
        public void setSource(SourceReference<?> _src) { source = _src; }

        /** Accessor for source reference
         *  @return source */
        public SourceReference<?> getSource() { return source; }
  
        
        /** Source for all the common effects */
	protected static final SourceSectionReference<SourceURL> SRD = 
            new SourceSectionReference<SourceURL>(DxxSystem.SRD35, "Magic Overview");
      
}
