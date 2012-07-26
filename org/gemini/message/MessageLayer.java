/*
 * MessageLayer.java
 *
 * Created on December 12, 2005, 3:02 PM
 */

package org.gemini.message;
import java.io.Serializable;
import org.slage.framework.NamedObject;

/**
 * Define a MessageLayer as a (very) small object for tracking message priority.
 *
 * @author  Jaeden
 */
public class MessageLayer implements Serializable, NamedObject
{
 
    /** Name of the layer */
    private String name;
    
    
    /** Creates a new instance of MessageLayer 
         @param _name name of the layer */
    public MessageLayer(String _name) { name = _name; }
    
    /** Get name for the layer
     * @return name */
    public String getName() { return name; }
    
    /** Set name for the layer
     * @param _name new name for the layer */
    public void setName(String _name) { name = _name; }
    
    /** Get layer name 
     *  @return string */
    public String toString() { return name; }
    
    /** Test equality of layers
     *  @param other another layer to test against */
    public boolean equals(Object other)
    {
        if (other instanceof MessageLayer == false) return false;
        return ((MessageLayer)other).name.equalsIgnoreCase(name);
    }
    
    
}
