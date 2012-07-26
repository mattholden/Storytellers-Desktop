/*
 * Feature.java
 *
 * Created on November 11, 2005, 2:46 AM
 */

package org.gemini;
import org.gemini.sources.SourceReference;
import org.slage.framework.Attribute;


/**
 * Defines a base Feature, from which we will derive almost all character traits
 * which can be taken multiply. These traits include (but are not limited to):
 *
 * <li> Modifiers
 * <li> Abilities (Supernatural, Spell-Like, etc.) 
 * <li> Skills / Proficiencies
 * <li> Feats
 * <li> Talents
 * <li> Spells
 * <li> Psionic Powers
 * <li> Traits, Flaws
 *
 * The templated NodeType refers to the type of the FeatureNode which should
 * store the Features and will be returned by getNode(). 
 *
 * @author Jaeden
 */
public abstract class Feature<NodeType extends FeatureNode<? extends Feature>> extends GeminiObject
{     
    /** Logger instance */
	protected static final org.apache.commons.logging.Log LOG = org.apache.commons.logging.LogFactory.getLog(Feature.class);

    /** Creates a new instance of Feature 
        @param _name name of the feature
        @param _src SourceReference to the source for this feature */
    public Feature(String _name, SourceReference<?> _src)
    {
        super (_name, _src);        
        setAttribute(new Attribute<String>("Specialization Type", null));        
    }
 
    
    
    
    
   /** Get a FeatureNode subclass from this object 
     @return a FeatureNode subclass storing our object */
    public abstract NodeType getNode();           
    
      /** Get a FeatureNode subclass from this object 
      @param grantor Grantor for the node
     @return a FeatureNode subclass storing our object */
    public NodeType getNode(Grantor grantor)           
    {
        NodeType N = getNode();
        N.setGrantor(grantor);
        return N;
    }
    
    /** Accept a Visitor
        @param aVisitor visitor to accept */        
    public void accept(GeminiObjectVisitor aVisitor){
        aVisitor.accept(this); 
    }  
    
    
}

/** TODO : 
 *  - Uses / time frame? 
 *  - Messaging 
 *  - Targeting ( isValidTarget() ? )
 */
