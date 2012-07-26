/*
 * FeatureNode.java
 *
 * Created on November 11, 2005, 10:06 PM
 */

package org.gemini;

    /** Define a node structure to use in lists. We do this because we want one instance
      of each Feature in memory, and the node to store all of the user-specific data 
      (ranks, uses/day, specialization, et. al.) Unfortunately, given that our objects 
      are built at runtime, we don't know what type we have for specialization, so we'll
      need to use a slightly less safe solution there involving Object.
      
      Each Feature class should provide a nested subclass of FeatureNode to store its 
      relevant values. The FeatureNode base class will be templated on the Feature's 
      type, so at least that much can be certain through the node. However, since the 
      character's Feature list is templated on FeatureNode, you will need to cast from a 
      FeatureNode to the appropriate subclass to access the other members of your FeatureNode 
      interface. 
     
      @author  Jaeden

     */
public class FeatureNode<Type extends Feature> extends Specialized<Type> implements GrantedObject<Grantor>
{   
    /** The object which granted this feature to the character who has it. */
    private Grantor grantor;

    /** Accessor for grantor
    @return grantor */
    public Grantor getGrantor() { return grantor; }

    /** Mutator for grantor
    @param _grantor new grantor */
    public void setGrantor(Grantor _grantor) { grantor = _grantor; }
    
    /** Test two FeatureNodes for equality 
    @param that object to test against
    @return true if this == that */
    public boolean equals(Object that)
    {
        if (that.getClass().equals(this.getClass()) == false)
            return false;

        FeatureNode Node = (FeatureNode)that;
        return (super.equals(Node) && grantor.equals(Node.grantor));                
    }

    /** Construct a FeatureNode 
    @param _feature Feature to track */
    public FeatureNode(Type _feature)
    {
        object = _feature;

        try
        {
            specializationType = (String)_feature.getAttributeValue("Specialization Type");                            
        }
        catch (Exception e) { LOG.error(e); }                                               

        try
        {
            specializationClass = Class.forName(specializationType);
        }
        catch (Exception e) { LOG.error(e); }                                               
    }

}
     