/*
 * GeminiCharacter.java
 *
 * Created on November 10, 2005, 4:37 PM
 */

package org.gemini;
import org.gemini.sources.SourceReference;
import org.slage.framework.Attribute;
import java.util.ArrayList;

/**
 * Defines a living, breathing entity that will walk around in our game world. 
 * This includes all living enemies as well as our player characters and NPCs. Essentially,
 * this class represents the basic "character sheet". 
 *
 * This should NOT be used for creature templates like monster data entries and races. 
 * For this function, use GeminiCreature instead.
 *
 * @see GeminiCreature
 *
 * @author  Jaeden
 */
public class GeminiCharacter extends GeminiObject
{
    /** A list of Features this Character has */
    private ArrayList<FeatureNode<? extends Feature>> features = new ArrayList<FeatureNode<? extends Feature>>();
        
    /** Creates a new instance of GeminiCharacter.
      @param _name character name
      @param _src reference object to the character's source */
    public GeminiCharacter(String _name, SourceReference<?> _src) 
    {
        super( _name, _src); 
        GamingSystem.addSystemAttributes(this);
        
        setAttribute(new Attribute<String>("Player's Name", "NPC"));
        setAttribute(new Attribute<GeminiCreature>("Race", null));
        
        // physical attributes will be filled in
        setAttribute(new Attribute<Integer>("Age", 0));
        setAttribute(new Attribute<Integer>("Height", 0));
        setAttribute(new Attribute<Integer>("Weight", 0));
        
        // Default values with 80% probability
        setAttribute(new Attribute<Handedness>("Handedness", Handedness.RIGHT));
        setAttribute(new Attribute<Gender>("Gender", Gender.MALE));
                         
        // For now, waste the space and give every creature details.
        addDetailAttributes();
        
    }

    /** Accessor for the feature list
     *  @return features */
    public ArrayList<FeatureNode<? extends Feature>> getFeatureList() { return features; }
    
    /** Add a Feature node 
     *  @param node Node to add */
    public void addFeature(FeatureNode<? extends Feature> node) { features.add(node); }
    
    /** Remove a Feature node 
     *  @param node Node to remove */
    public void removeFeature(FeatureNode<? extends Feature> node) { features.remove(node); }
    
    /** Remove all features provided by a given Grantor
     *  @param grantor Grantor to search for. */
    public void removeGrantor(Grantor grantor)
    {
        for (FeatureNode<? extends Feature> F : features)
        {
            if (F.getGrantor().equals(grantor))
                features.remove(F);
        }
    }
    
    /** Get feature count
     *  @return number of FeatureNodes this character has */
    public int getFeatureCount() { return features.size(); }
    
    /** Clear feature list */ 
    public void clearFeatures() { features.clear(); }
    
    
    /** Adds in-depth background information attributes to the Character.      
     In memory-restrictive situations, we may not want to add these types of values to our
     "cannon fodder" creatures. */
    public void addDetailAttributes()
    {
        setAttribute(new Attribute<String>("Family", ""));
        setAttribute(new Attribute<String>("Biography", ""));
        setAttribute(new Attribute<String>("Religious Background", ""));
        
        setAttribute(new Attribute<String>("Hair Color", ""));
        setAttribute(new Attribute<String>("Hair Style", ""));
        setAttribute(new Attribute<String>("Eye Color", ""));
        setAttribute(new Attribute<String>("Skin Tone", ""));
        setAttribute(new Attribute<String>("Facial Hair", ""));
        
        // TODO: Birthdate (how to store dates for in-game calendar?)
    }
    
       /** Accept a Visitor
      @param aVisitor visitor to accept */        
    public void accept(GeminiObjectVisitor aVisitor){
        aVisitor.accept(this); 
    }  

}
