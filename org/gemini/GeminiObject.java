/*
 * GeminiObject.java
 *
 * Created on November 9, 2005, 12:45 PM
 */

package org.gemini;
import org.slage.framework.SlageFrameworkObject;
import org.gemini.sources.SourceReference;
import org.gemini.sources.ObjectFromSource;
import org.slage.framework.FrameworkHandler;
import org.gemini.message.Message;

/**
 * Defines a core Gemini object. 
 *
 * @author  Jaeden
 */
public class GeminiObject 
    extends SlageFrameworkObject<Message>
    implements ObjectFromSource.Mutable, DescribedObject.Mutable, Grantor
{
   
    /** String description of the object */
    private String description;
    
    /** Reference to the source for this object */
    private SourceReference<?> source;
    
    /** Creates a new instance of GeminiObject 
      @param name Name for this object 
      @param _srcRef source reference */
    public GeminiObject(String name, SourceReference<?> _srcRef) 
    {
        super(name);
        source = _srcRef;
    }
    
    /** Getter for source reference 
     * @return reference to the source */
    public SourceReference<?> getSource() {         return source;     }    
    
    /** Setter for source reference 
     * @param _source New value of property source.      */
    public void setSource(SourceReference<?> _source) { source = _source;    }
    
    
    /** Check the equality of two GeminiObjects
      @param other object to check against
      @return true if this and other are the same object */
    public boolean equals(Object other)
    {
        // Ensure different GO subclasses can't match
        if (getClass().equals(other.getClass()) == false) return false;
        
        GeminiObject GO = (GeminiObject)other;        
        return (super.equals(GO) && (source == GO.getSource()) );
    }
 
    /** Accessor for description
     @return description */
    public String getDescription() { return description; }
    
    /** Mutator for description
     @param _desc new description */
    public void setDescription(String _desc) { description = _desc; }
    
    /** Accept a Visitor
      @param aVisitor visitor to accept */        
    public void accept(GeminiObjectVisitor aVisitor) { aVisitor.accept(this);  }
    
}
