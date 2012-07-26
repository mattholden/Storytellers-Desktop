/*
 * DxxFeat.java
 *
 * Created on November 11, 2005, 9:12 PM
 */

package org.gemini.rules.dXX;
import org.gemini.Feature;
import org.gemini.FeatureNode;
import org.slage.framework.Attribute;
import org.gemini.sources.SourceReference;

/**
 * Define a Feat in dXX. 

  <b>Licensing Notice</b><br>
   The rules defined in this file are provided under the terms of the Open Gaming 
   License v1.0a. The text of this license can be viewed 
   <a href="http://www.wizards.com/d20/files/OGLv1.0a.rtf">here</a>. 
 

 * @author  Jaeden
 */
public class DxxFeat extends Feature<DxxFeatNode<DxxFeat>>
{
     
    /** Creates a new instance of DxxFeat 
        @param _name Name of the Feat
        @param _src SourceReference for the source of this Feat */
    public DxxFeat(String _name, SourceReference<?> _src)
    {
        super(_name, _src);
        
        // Considered making feat type an object, but then we need to track new 
        // types (such as UA's "Spelltouched"). What a pain! Using a string, we can still
        // break them up into chunks for the GUI and whatnot.
        setAttribute(new Attribute<String>("Type", "General"));
        
        
        
        
        // TODO: Take Once / Multiple / Unique Specialization      
        // TODO: Prerequisites
        
    }

    /** Generate a DxxFeatNode for this Feat 
     *  @return a new DxxFeatNode */
    public DxxFeatNode<DxxFeat> getNode() { 
        return new DxxFeatNode<DxxFeat>(this); 
    }
    
    
    /** Accept a Visitor
        @param aVisitor visitor to accept */        
    public void accept(org.gemini.GeminiObjectVisitor aVisitor){
        aVisitor.accept(this); 
    }      
}
    