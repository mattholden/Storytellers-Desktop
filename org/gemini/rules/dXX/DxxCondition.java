/*
 * DxxCondition.java
 *
 * Created on November 18, 2005, 11:54 AM
 */

package org.gemini.rules.dXX;
import org.gemini.GeminiObject;
import org.gemini.GeminiObjectVisitor;
import org.gemini.sources.SourceReference;

/**
 * Define a Condition such as dead, dying, knocked down. Also, derive 
 * poisons, diseases, etc. from this.
 
  <b>Licensing Notice</b><br>
   The rules defined in this file are provided under the terms of the Open Gaming 
   License v1.0a. The text of this license can be viewed 
   <a href="http://www.wizards.com/d20/files/OGLv1.0a.rtf">here</a>. 
 

 * @author  Jaeden
 */
public class DxxCondition extends GeminiObject
{
    
    /** Creates a new instance of DxxCondition 
        @param _name name of the Condition 
        @param _src source of the Condition */
    public DxxCondition(String _name, SourceReference _src) 
    {
        super(_name, _src);
    }
  
    
    
    /** Accept a Visitor
        @param aVisitor visitor to accept */        
    public void accept(org.gemini.GeminiObjectVisitor aVisitor){
        aVisitor.accept(this); 
    }      
    
}