/*
 * GamingSystemMod.java
 *
 * Created on November 9, 2005, 3:07 PM
 */

package org.gemini;
import org.gemini.sources.SourceReference;


/**
 * Used to distinguish gaming system partial conversions such as 
 * Grim and Gritty, Skills and Powers, Combat and Tactics, Epic Level Handbook, etc.
 *  
 * @author  Jaeden
 */
public class GamingSystemMod extends GeminiObject
{   
    /** Creates a new instance of GamingSystemMod 
      @param _name Name of the gaming system 
      @param _source reference to the core source for the system (SRD, etc.) */
    public GamingSystemMod(String _name, SourceReference<?> _source) 
    {
        super(_name, _source);
    }
    
     
    
    /** Accept a Visitor
      @param aVisitor visitor to accept */        
    public void accept(GeminiObjectVisitor aVisitor)    {   aVisitor.accept(this);     }
}
