/*
 * GamingSystem.java
 *
 * Created on November 9, 2005, 3:07 PM
 */

package org.gemini;
import org.gemini.sources.SourceReference;
import org.slage.framework.NotFoundException;

import java.util.HashMap;

/**
 * Used to distinguish high-level gaming systems such as 
 * "dXX Fantasy" or "GURPS Lite 4.0", etc.
 *  
 * References to this object (at least by name) will exist in the sources as well,
 * so that a source has the ability to know what system it belongs to. This allows
 * sources to be easily included or excluded based on user's preferences for available
 * system material. 
 
 * @author  Jaeden
 */
public class GamingSystem extends GeminiObject
{   
    /** Creates a new instance of GamingSystem 
      @param _name Name of the gaming system 
      @param _source reference to the core source for the system (SRD, etc.) */
    public GamingSystem(String _name, SourceReference<?> _source) 
    {
        super(_name, _source);
    }
    
    /** Map of rule modifications (stuff like Grim and Gritty, 
     Skills and Powers, etc */
    protected HashMap<String, GamingSystemMod> mapRuleMods = 
            new HashMap<String, GamingSystemMod>();
    
    /** Add a GamingSystemMod to the map
      @param _mod rule mod to add */
    public void addSystemMod(GamingSystemMod _mod)
    {
        mapRuleMods.put(_mod.getName(), _mod);        
    }
 
    /** Clear the list of system mods */
    public void clearSystemMods() { mapRuleMods.clear(); }
    
    /** Count the system mods present
      @return system mod count */
    public int getSystemModCount() { return mapRuleMods.size(); }
    
    /** Get the selected mod. 
      @param _name Name of the mod to seek
      @return the GamingSystemMod object matching the name
      @throws NotFoundException if no such mod exists */
    public GamingSystemMod getSystemMod(String _name)
        throws NotFoundException        
    {
        GamingSystemMod GSM = mapRuleMods.get(_name);
        if (GSM != null)
            return GSM;
        throw new NotFoundException("Gaming System Modification", _name);
    }
     
    /** Add common attributes to Creatures and Characters of a given
      System. It's important to make sure you call super.addSystemAttributes()
      when you override this method. 
     
      @param obj Object to add attributes to */
    public static void addSystemAttributes(GeminiObject obj)
    {
    }

    
    /** Accept a Visitor
      @param aVisitor visitor to accept */        
    public void accept(GeminiObjectVisitor aVisitor)    {   aVisitor.accept(this);     }
}
