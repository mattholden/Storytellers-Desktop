/*
 * DxxDescriptor.java
 *
 * Created on November 15, 2005, 8:11 PM
 */

package org.gemini.rules.dXX;
import org.slage.framework.NamedObject;
import org.gemini.DescribedObject;
import java.io.Serializable;
import org.gemini.sources.ObjectFromSource;
import org.gemini.sources.SourceReference;
import org.gemini.sources.SourceSectionReference;
import org.gemini.sources.SourceURL;

/**
 * Defines a basic Descriptor for spells and items.
 
  <b>Licensing Notice</b><br>
   The rules defined in this file are provided under the terms of the Open Gaming 
   License v1.0a. The text of this license can be viewed 
   <a href="http://www.wizards.com/d20/files/OGLv1.0a.rtf">here</a>. 
 

 * @author  Jaeden
 */
public class DxxDescriptor 
    implements NamedObject, DescribedObject.Mutable, ObjectFromSource.Mutable, Serializable
{
    
    /** Description */ private String description;
    /** Name */ private String name;
    /** Source Reference */ private SourceReference<?> source;
    
    /** Creates a new instance of DxxDescriptor 
      @param _name Name of the descriptor 
      @param _src Source reference */
    public DxxDescriptor(String _name, SourceReference<?> _src)
    {
        setName(_name);
        setSource(_src);
    }
    
    /** Creates a new instance of DxxDescriptor 
      @param _name Name of the descriptor 
      @param _desc Description of the descriptor 
      @param _src Source reference */
    public DxxDescriptor(String _name, String _desc, SourceReference<?> _src)
    {
        this(_name, _src);
        setDescription(_desc);
    }
    
    /** Accessor for description (might be null) 
     * @return description */
    public String getDescription() { return description; }
    
    /** Accessor for name
     * @return name */
    public String getName() { return name; }
    
    /** Mutator for name 
     * @param _name name */
    public void setName(String _name) { name = _name; }
    
    /** Mutator for description
     * @param _desc description */
    public void setDescription(String _desc) { description = _desc; }              
    
    /** Check the equality of two descriptors
      @param other object to check against
      @return true if this and other are the same object */
    public boolean equals(Object other)
    {
        // Ensure different GO subclasses can't match
        if (getClass().equals(other.getClass()) == false) return false;
        
        DxxDescriptor D = (DxxDescriptor)other;        
        return (D.name.equalsIgnoreCase(name) && D.source.equals(source));
    }
 
    /** String representation of the descriptor (its name)
     * @return descriptor's name */
    public String toString() { return getName(); }
    
    
    /** Source ref for the overview */
    protected static final SourceSectionReference<SourceURL> SRD = 
        new SourceSectionReference<SourceURL>(DxxSystem.SRD35, "Magic Overview");
        
         /** Acid */     public static final DxxDescriptor ACID = new DxxDescriptor("Acid", SRD);
        /** Air */        public static final DxxDescriptor AIR = new DxxDescriptor("Air", SRD);
        /** Chaotic */     public static final DxxDescriptor CHAOTIC = new DxxDescriptor("Chaotic", SRD);
        /** Cold */     public static final DxxDescriptor COLD = new DxxDescriptor("Cold", SRD);
        /** Darkness */     public static final DxxDescriptor DARKNESS = new DxxDescriptor( "Darkness", SRD);
        /** Death */     public static final DxxDescriptor DEATH = new DxxDescriptor("Death", SRD);
        /** Earth */     public static final DxxDescriptor EARTH = new DxxDescriptor("Earth", SRD);
        /** Electricity */     public static final DxxDescriptor ELECTRICITY = new DxxDescriptor("Electricity", SRD);
        /** Evil */     public static final DxxDescriptor EVIL = new DxxDescriptor("Evil", SRD);
        /** Fear */     public static final DxxDescriptor FEAR = new DxxDescriptor("Fear", SRD);
        /** Fire */     public static final DxxDescriptor FIRE = new DxxDescriptor("Fire", SRD);
        /** Force */     public static final DxxDescriptor FORCE = new DxxDescriptor("Force", SRD);
        /** Good */     public static final DxxDescriptor GOOD = new DxxDescriptor("Good", SRD);
        /** Language-Dependent */     public static final DxxDescriptor LANGUAGE_DEPENDENT =
            new DxxDescriptor( "Language-Dependent", "A language-dependent spell uses intelligible language as a medium for communication. " +
            "If the target cannot understand or cannot hear what the caster of a language-dependant spell says the spell fails.", SRD);
        /** Lawful */     public static final DxxDescriptor LAWFUL = new DxxDescriptor("Lawful", SRD);
        /** Light */     public static final DxxDescriptor LIGHT = new DxxDescriptor("Light", SRD);
        /** Mind-Affecting */     public static final DxxDescriptor MIND_AFFECTING = 
            new DxxDescriptor("Mind-Affecting", " A mind-affecting spell works only against creatures with an Intelligence score of 1 or higher.", SRD);
        /** Sonic */     public static final DxxDescriptor SONIC = new DxxDescriptor("Sonic", SRD);
        /** Water */     public static final DxxDescriptor WATER = new DxxDescriptor("Water", SRD);     

        
    /** Getter for source reference 
     * @return reference to the source */
    public SourceReference<?> getSource() {         return source;     }    
    
    /** Setter for source reference 
     * @param _source New value of property source.      */
    public void setSource(SourceReference<?> _source) { source = _source;    }
    
}
