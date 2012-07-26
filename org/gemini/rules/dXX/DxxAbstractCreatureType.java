/*
 * DxxAbstractCreatureType.java
 *
 * Created on November 18, 2005, 11:53 AM
 */

package org.gemini.rules.dXX;
import org.gemini.GeminiObject;
import org.gemini.sources.SourceReference;
import java.util.ArrayList;


/**
 * Base class for creature types and subtypes.

  <b>Licensing Notice</b><br>
   The rules defined in this file are provided under the terms of the Open Gaming 
   License v1.0a. The text of this license can be viewed 
   <a href="http://www.wizards.com/d20/files/OGLv1.0a.rtf">here</a>. 
 

 * @author  Jaeden
 */
public abstract class DxxAbstractCreatureType extends GeminiObject
{
    
    /** Creates a new instance of DxxAbstractCreatureType 
        @param _name Name of the type
        @param _src SourceReference for the type */
    protected DxxAbstractCreatureType(String _name, SourceReference _src)
    {
        super(_name, _src);
    }
    
    /** List of Traits this type has */
    protected ArrayList<DxxTrait> traits = new ArrayList<DxxTrait>();

    /** Add a Trait
     *  @param _trait trait to add */
    public void addTrait(DxxTrait _trait) { traits.add(_trait); }
    
    /** Remove a Trait
     *  @param _trait trait to remove */
    public void removeTrait(DxxTrait _trait) { traits.remove(_trait); }
    
    /** Get count of traits
     *  @return trait count */
    public int getTraitCount() { return traits.size(); }
        
    /** Get list of traits (TODO: do this without breaking encapsulation? 
     * @return trait list */
    public final ArrayList<DxxTrait> getTraits() { return traits; }
    
    /** Accept a Visitor
        @param aVisitor visitor to accept */        
    public void accept(org.gemini.GeminiObjectVisitor aVisitor){
        aVisitor.accept(this); 
    }      
}
