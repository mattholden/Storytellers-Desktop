/*
 * DxxMetaphysicalEffect.java
 *
 * Created on November 11, 2005, 11:43 PM
 */

package org.gemini.rules.dXX;
import org.gemini.Feature;
import org.gemini.FeatureNode;
import org.gemini.sources.SourceReference;
import org.gemini.Range;
import java.util.ArrayList;
import org.slage.framework.Attribute;
import org.gemini.rules.dXX.savingthrows.SavingThrowEffect;


/**
 ** Defines a metaphysical effect in dXX games. Provided as a common base class
 *  for DxxSpell and DxxPsionicPower.

  <b>Licensing Notice</b><br>
   The rules defined in this file are provided under the terms of the Open Gaming 
   License v1.0a. The text of this license can be viewed 
   <a href="http://www.wizards.com/d20/files/OGLv1.0a.rtf">here</a>. 
 

 * @author  Jaeden
 */  
public abstract class DxxMetaphysicalEffect
    <NodeType extends FeatureNode<? extends DxxMetaphysicalEffect>>
    extends Feature<FeatureNode<?>>        
    implements DxxObjectWithDescriptors
{            
      
    /** TODO better node */
    public FeatureNode<?> getNode() { return new FeatureNode<DxxMetaphysicalEffect>(this); }
    
    /** list of Descriptors (good, evil, chaos, law, etc.) */
    protected ArrayList<DxxDescriptor> descriptors = new ArrayList<DxxDescriptor>();
       
    
    /** Creates a new instance of DxxMetaphysicalEffect 
        @param _name Name of the effect
        @param _src SourceReference for the source of this DxxMetaphysicalEffect */
    public DxxMetaphysicalEffect(String _name, SourceReference<?> _src)
    {
        super(_name, _src);     
        setAttribute(new Attribute<Range>("Range", null));      
        setAttribute(new Attribute<SavingThrowEffect>("Saving Throw", null));
        
       // if true, the effect can be resisted with spell/power resistance
        setAttribute(new Attribute<Boolean>("Resistance", new Boolean(false)));
        
    }    
    
    
    /** Add a Descriptor
     *  @param _descriptor descriptor to add */
    public void addDescriptor(DxxDescriptor _descriptor) { descriptors.add(_descriptor); }

    /** Remove a Descriptor
     *  @param _descriptor descriptor to remove */
    public void removeDescriptor(DxxDescriptor _descriptor) { descriptors.remove(_descriptor); }
    
    /** Clear descriptor list */
    public void clearDescriptors() { descriptors.clear(); }
    
    /** Get descriptor count 
     *  @return descriptor count */
    public int getDescriptorCount() { return descriptors.size(); }
    
    /** Accept a Visitor
      @param aVisitor visitor to accept */        
    public void accept(org.gemini.GeminiObjectVisitor aVisitor) {aVisitor.accept(this); }  
}


