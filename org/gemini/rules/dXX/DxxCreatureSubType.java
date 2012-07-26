/*
 * DxxCreatureSubType.java
 *
 * Created on November 18, 2005, 11:53 AM
 */

package org.gemini.rules.dXX;
import org.gemini.GeminiObject;
import org.gemini.sources.SourceReference;

/**
 * Stores a creature subtype.

  <b>Licensing Notice</b><br>
   The rules defined in this file are provided under the terms of the Open Gaming 
   License v1.0a. The text of this license can be viewed 
   <a href="http://www.wizards.com/d20/files/OGLv1.0a.rtf">here</a>. 
 

 * @author  Jaeden
 */
public abstract class DxxCreatureSubType extends DxxAbstractCreatureType
{
    
    /** Creates a new instance of DxxCreatureSubType 
        @param _name Name of the type
        @param _src SourceReference for the type */
    protected DxxCreatureSubType(String _name, SourceReference _src)
    {
        super(_name, _src);
    }
    
    
    
    /** Accept a Visitor
        @param aVisitor visitor to accept */        
    public void accept(org.gemini.GeminiObjectVisitor aVisitor){
        aVisitor.accept(this); 
    }      
}
