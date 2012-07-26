/*
 * MessagePriority.java
 *
 * Created on December 12, 2005, 3:05 PM
 */

package org.gemini.message;
import java.util.ArrayList;

/**
 * Contains the priority order for messages in a simple list of MessageLayers.
 *
 * @see org.gemini.message.MessageLayer
 *
 * @author  Jaeden
 */
public class MessagePriority implements java.io.Serializable
{

    /** List of layers */
    private ArrayList<MessageLayer> layers = new ArrayList<MessageLayer>();
    
    /** Creates a new instance of MessagePriority */
    public MessagePriority() 
    {
        // TODO: Add standard layers
    }
    
    /** Add a layer at the beginning of the list
     *  @param layer new layer to add */
    public void addLayerToBeginning(MessageLayer layer)
    {
        layers.add(0, layer);
    }
    
    /** Add a layer at the end of the list
     *  @param layer new layer to add */
    public void addLayerToEnd(MessageLayer layer)
    {
        layers.add(layer);        
    }
    
    /** Get the index for a given item in the list 
     *  @param layer layer to find
     *  @return layer's index */
    public int getLayerPriority(MessageLayer layer)
    {
        return layers.indexOf(layer);
    }
  
    /** Insert a layer. If 'position' not found, it will add to the end instead.     
     *  @param layer new layer to add
     * @param position layer to insert AFTER*/
    public void insertLayer(MessageLayer layer, MessageLayer position)
    {
	// foreach not used deliberately; do not optimize
        int i = 0;
        for (i = 0; i < layers.size(); i++)
        {
            MessageLayer ML = (MessageLayer)layers.get(i);        
            if (ML.equals(position))
                break;
        }        
        layers.add(i, layer);      
    }
    
    
    /** Sort a list of messages according to this layer scheme
     *  @param messages List of messages to sort */
    public void prioritize(ArrayList<Message> messages)
    {
        // make bins to store all our messages in
	MessageList[] priority = new MessageList[layers.size()];
	for (int i = 0; i < layers.size(); i++)	
            priority[i] = new MessageList();
        
        // sort the messages into bins
        for (Message M : messages)        
            priority[getLayerPriority(M.getPriority())].add(M);
        
        // rebuild the message list
        messages.clear();
        for (int i = 0; i < layers.size(); i++)
            messages.addAll(priority[i]);

    }
}
