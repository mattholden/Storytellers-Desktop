/*
 * DxxTemplate.java
 *
 * Created on November 18, 2005, 11:54 AM
 */

package org.gemini.rules.dXX;
import org.gemini.GeminiObject;
import org.gemini.GeminiObjectVisitor;
import org.gemini.sources.SourceReference;

/**
 * Define a Template that can be added to a creature, such as Ghost, Skeleton,
 * Fiendish Creature, etc.

  <b>Licensing Notice</b><br>
   The rules defined in this file are provided under the terms of the Open Gaming 
   License v1.0a. The text of this license can be viewed 
   <a href="http://www.wizards.com/d20/files/OGLv1.0a.rtf">here</a>. 
 

 * @author  Jaeden
 */
public class DxxTemplate extends GeminiObject
{
    
    /** Creates a new instance of DxxTemplate 
        @param _name name of the Template 
        @param _src source of the Template */
    public DxxTemplate(String _name, SourceReference _src) 
    {
        super(_name, _src);
    }
  
    
    
    /** Accept a Visitor
        @param aVisitor visitor to accept */        
    public void accept(org.gemini.GeminiObjectVisitor aVisitor){
        aVisitor.accept(this); 
    }      
    
}