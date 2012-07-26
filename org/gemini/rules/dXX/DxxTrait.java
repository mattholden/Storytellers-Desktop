/*
 * DxxTrait.java
 *
 * Created on November 18, 2005, 11:54 AM
 */

package org.gemini.rules.dXX;
import org.gemini.GeminiObject;
import org.gemini.GeminiObjectVisitor;
import org.gemini.sources.SourceReference;
import org.gemini.Feature;
import org.gemini.FeatureNode;

/**
 * Define a Trait for character types. Also useful in variant rules with traits
 * and flaws.
 
  <b>Licensing Notice</b><br>
   The rules defined in this file are provided under the terms of the Open Gaming 
   License v1.0a. The text of this license can be viewed 
   <a href="http://www.wizards.com/d20/files/OGLv1.0a.rtf">here</a>. 
 

 * @author  Jaeden
 */
public class DxxTrait extends Feature<FeatureNode<DxxTrait>>
{
    
    /** Creates a new instance of DxxTrait 
        @param _name name of the trait 
        @param _src source of the trait */
    public DxxTrait(String _name, SourceReference _src) 
    {
        super(_name, _src);
    }
    
    /** Get a node (TODO: Better node?)
     *  @return feature node */
    public FeatureNode<DxxTrait> getNode() { return new FeatureNode<DxxTrait>(this); }
    
      
    /** Accept a Visitor
        @param aVisitor visitor to accept */        
    public void accept(org.gemini.GeminiObjectVisitor aVisitor){
        aVisitor.accept(this); 
    }      
}
