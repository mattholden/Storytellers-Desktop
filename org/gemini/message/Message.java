/*
 * Message.java
 *
 * Created on November 19, 2005, 9:25 AM
 */

package org.gemini.message;
import org.slage.framework.FrameworkHandler;
import org.slage.framework.LinkedObject;
import org.gemini.DescribedObject;
import org.gemini.GeminiObject;

/**
 * Defines a Message, the core handler class for Gemini events. 
 *
 * @author  Jaeden
 */
public abstract class Message<OwnerType extends GeminiObject> 
    extends FrameworkHandler
    implements LinkedObject<OwnerType>,  DescribedObject.Mutable
    
{
    
    /** Owner object */ private OwnerType owner;
    /** Description */  private String description;
        
    /** Creates a new instance of Message 
        @param _owner Owner object 
       @param _priority Priority layer 
        @param _desc Description*/
    public Message(OwnerType _owner, MessageLayer _priority, String _desc) 
    {
        setOwner(_owner);
        setPriority(_priority);
        setDescription(_desc);
    }
   
    /** Accessor for specialized object 
    @return specialized object instance */
    public OwnerType getOwner() { return owner; }

    /** Mutator for specialized object 
    @param _owner specialized object instance */
    public void setOwner(OwnerType _owner) { owner = _owner; }

    /** Accessor for description
     @return description */
    public String getDescription() { return description; }
    
    /** Mutator for description
     @param _desc new description */
    public void setDescription(String _desc) { description = _desc; }
    
    /** Priority layer for this message */
    private MessageLayer priority;
    
    /** Accessor for priority layer     
     *  @return priority */
    public MessageLayer getPriority() { return priority; }
    
    /** Mutator for priority layer
     *  @param _layer new priority layer */
    protected void setPriority(MessageLayer _layer) { priority = _layer; }
}
