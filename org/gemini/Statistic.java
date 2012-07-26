/*
 * Statistic.java
 *
 * Created on November 17, 2005, 1:46 AM
 */
package org.gemini;
import org.slage.framework.NamedObject;
import java.io.Serializable;
import org.gemini.sources.ObjectFromSource;
import org.gemini.sources.SourceReference;

/**
 * Defines a statistic for a game, with a name, source and description.
 * Your rule set should extend this object into categories of statistics
 * to promote type safety in your stat checks.
 *
 * @author  Jaeden
 */
public class Statistic 
    implements NamedObject, DescribedObject.Mutable, Serializable, ObjectFromSource.Mutable, Grantor
{
    /** Description of the score */ private String description;
    /** Name of the score */ private String name;
        
     /** Source reference for the object */
        private SourceReference<?> source;

    /** Creates a new instance of Statistic 
        @param _name Name of the Statistic
        @param _src Source of the Statistic
        @param _desc Description of the Statistic */
    public Statistic(String _name, SourceReference<?> _src, String _desc) 
    {
        setName(_name);
        setSource(_src);
        setDescription(_desc);
    }
               
        /** Get action as a string 
         * @return string representation of the size (its name) */
        public String toString() { return getName(); }
          
        /** Accessor for description
         * @return description */
        public String getDescription() { return description; }

        /** Set the ability's description 
         * @param _description new description */
        public void setDescription(String _description) { description = _description; }

        /** Accessor for name
         * @return name */
        public String getName() { return name; }    

        /** Set the action's name 
         * @param _name new name */
        public void setName(String _name) { name = _name; }
        
        /** Accessor for source reference
         *  @return source */
        public SourceReference<?> getSource() { return source; }
        
          /** Set the reference to the object's source
         * @param _src new source reference */
        public void setSource(SourceReference<?> _src) { source = _src; }

        /** Check equality
         *  @param other score to check
         *  @return true if this == other */
        public boolean equals(Object other)
        {
            if (other instanceof Statistic == false) return false;
            
            Statistic that = (Statistic)other;
            return (this.name.equals(that.name) && this.description.equals(that.description));            
        }
     
      
}
