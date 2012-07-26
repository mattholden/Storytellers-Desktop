/*
 * SourceSectionReference.java
 *
 * Created on November 9, 2005, 12:23 PM
 */

package org.gemini.sources;

/**
 * Stores source references for sources divided into sections rather than pages (Usually SourceURLs).
 *
 * @author  Jaeden
 */
public class SourceSectionReference<Type extends SourceMaterial> extends SourceReference<Type>
{

    /** Name of the section */     private String section;
    
    /** Creates a new instance of SourceSectionReference 
      @param _source source reference
      @param _section section name */
    public SourceSectionReference(Type _source, String _section)
    {
        super(_source);
        section = _section;
    }
    
    /** Accessor for section name 
      @return section */
    public String getSection() { return section; }
    
    /** Mutator for section name
     @param _section new section */
    public void setSection(String _section) { section = _section; }
    
    /** String representation of this source
      @return string reference */
    public String toString() { return super.toString() + " [" + section + "]"; }
    
    /** Check equality of this source reference and another 
      @param other another reference to check 
      @return 'true' if they are equal */
    public boolean equals(Object other)
    {
        if (other instanceof SourceSectionReference == false) return false;
        
        SourceSectionReference SSR = (SourceSectionReference)other;
        return (getSource().equals(SSR.getSource()) && section.equalsIgnoreCase(SSR.getSection()));
    }
    
}
