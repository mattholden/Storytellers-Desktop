/*
 * SourceReference.java
 *
 * Created on November 9, 2005, 11:43 AM
 */

package org.gemini.sources;

/**
 * Base class for objects which reference SourceMaterial subclasses and add a section type
 * (page number, page name (URLs), etc. )
 *
 * @author  Jaeden
 */
public class SourceReference<Type extends SourceMaterial> implements java.io.Serializable
{
    /** Logger instance */
	protected static final org.apache.commons.logging.Log LOG = org.apache.commons.logging.LogFactory.getLog(SourceReference.class);

    /** The source referenced */ private Type source;
    
    /** Construct a new SourceReference 
      @param _source source to reference */
    public SourceReference(Type _source) { setSource(_source); }
    
    /** Get the source to reference 
      @return source referenced */
    public Type getSource() { return source; }
    
    /** Set the source to reference
      @param _source source to reference */
    public void setSource(Type _source) { source = _source; }
    
    /** String representation of the source 
      @return string implementation of the source and its section */
      public String toString() { return source.toString(); }
    
      
    /** Check equality of this source reference and another 
      @param other another reference to check 
      @return 'true' if they are equal */
    public boolean equals(Object other)
    {
        if (other instanceof SourceReference == false) return false;
        
        SourceReference SR = (SourceReference)other;
        return getSource().equals(SR.getSource());
    }
}
