/*
 * CampaignSetting.java
 *
 * Created on November 9, 2005, 3:23 PM
 */

package org.gemini;
import org.gemini.sources.SourceReference;
import org.slage.framework.Attribute;

/**
 * The Campaign Setting should contain tons of information about the
 * game world, eventually including its calendar, monetary systems, perhaps
 * even maps, etc. It is the central object for defining a campaign world and 
 * should be viewed as Gemini's digital equivalent of a Campaign Sourcebook. 
 *
 * References to this object (at least by name) will exist in the sources as well,
 * so that a source has the ability to know what setting it belongs to. This allows
 * sources to be easily included or excluded based on user's preferences for available
 * setting material. 
 *
 * @author  Jaeden
 */
public class CampaignSetting extends GeminiObject
{
    
    /** Creates a new instance of CampaignSetting 
      @param _name Name of the setting (ex. "Forgotten Realms")
      @param _world Name of the world (ex. "Faerun") 
      @param _source SourceReference to the core source for this setting     
     */
    public CampaignSetting(String _name, String _world, SourceReference<?> _source)
    {
        super(_name, _source);
        setAttribute(new Attribute<String>("Campaign World", _world));
       
    }
    
    
    
    /** Accept a Visitor
      @param aVisitor visitor to accept */        
    public void accept(GeminiObjectVisitor aVisitor)    {   aVisitor.accept(this);     }
}
