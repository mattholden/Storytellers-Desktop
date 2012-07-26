/*
 * DxxStandardRange.java
 *
 * Created on November 15, 2005, 4:48 PM
 */

package org.gemini.rules.dXX;
import org.slage.framework.NamedObject;
import org.gemini.Range;
import org.gemini.sources.SourceSectionReference;
import org.gemini.sources.SourceURL;
import org.gemini.sources.ObjectFromSource;

/**
 * Class for defining the standard ranges for spells and
 * ranged weapons in dXX games. Also contains static
 * constant instances for the common ranges. 
 *
 * For a "range in feet" use the base class, org.gemini.Range.
 * @see org.gemini.Range
 
  <b>Licensing Notice</b><br>
   The rules defined in this file are provided under the terms of the Open Gaming 
   License v1.0a. The text of this license can be viewed 
   <a href="http://www.wizards.com/d20/files/OGLv1.0a.rtf">here</a>. 
 

 * @author  Jaeden
 */
public abstract class DxxStandardRange extends Range 
    implements NamedObject, ObjectFromSource
{
        /** Name of the range increment */ 
        private String name;
        
        /** Accessor for name
        * @return name */
        public String getName() { return name; }

        /** Mutator for name
         * @param _name new name */
        public void setName(String _name) { name = _name; }

        /** Creates a new instance of DxxStandardRange 
          @param _name name of the range 
          @param _range base range in feet */
        public DxxStandardRange(String _name, int _range) 
        {
            super( _range);
            name = _name; 
        }

        /** Test equality of two Ranges
          @param that other Range to test
          @return true if this == that */
        public boolean equals(Object that)
        {
            if (super.equals(that) == false)
                return false;
            return name.equalsIgnoreCase( ((DxxStandardRange)that).name);
        }
    
        /** Calculate the maximum range for this spell/effect      
        @param i Caster/maniferster level or other user level
        @return maximum range of the spell/effect */
        public abstract int getRange(int i);     
        
        
       /** Source ref for all ranges */
        private static final SourceSectionReference<SourceURL> SRD = 
          new SourceSectionReference<SourceURL>(DxxSystem.SRD35, "Magic Overview");
        
        /** Getter for source reference 
         * @return reference to the source */
        public SourceSectionReference<SourceURL> getSource() { return SRD; }
            
       
    /** Personal (can only affect the user/caster) */
    public static final DxxStandardRange PERSONAL = 
        new DxxStandardRange("Personal", 0)
        {
                public int getRange(int i) { return 0; }
                public String toString() { return "Personal: Only you are affected."; }
        };
    
        /** Touch (the user must be able to physically touch the target) */
        public static final DxxStandardRange TOUCH = 
        new DxxStandardRange("Touch", 0)
        {
                public int getRange(int i) { return 0; }
                public String toString() { return "Touch:  You must touch a creature or object to affect it. "; }
        };
    
        /** Close (25 feet + 5 feet for every 2 user levels) */
        public static final DxxStandardRange CLOSE = 
        new DxxStandardRange("Close", 25)
        {
                public int getRange(int i) {  return 25 + ((i >> 1) * 5);  }
                public String toString() { return "Close: 25 feet + 5 feet for every 2 full caster levels"; }
        };
        
        /** Medium (100 feet + 10 feet per user level) */
        public static final DxxStandardRange MEDIUM = 
        new DxxStandardRange("Medium", 100)
        {
                public int getRange(int i) {  return 100 + (10 * i); }
                public String toString() { return "Medium: 100 feet + 10 feet per caster level"; }
        };
        
        /** Long (400 feet + 40 feet per user level) */
        public static final DxxStandardRange LONG = 
        new DxxStandardRange("Long", 400)
        {
                public int getRange(int i) {  return 400 + (40 * i); }
                public String toString() { return "Long: 400 feet + 40 feet per caster level"; }
        };

       /** Unlimited (anywhere on the same plane) */
        public static final DxxStandardRange UNLIMITED = 
        new DxxStandardRange("Unlimited", Integer.MAX_VALUE)
        {
                public int getRange(int i) {  return Integer.MAX_VALUE; }
                public String toString() { return "Unlimited: Anywhere on the same plane of existence."; }
        };

        
        
    
}
