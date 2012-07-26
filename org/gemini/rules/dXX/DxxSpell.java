/*
 * DxxSpell.java
 *
 * Created on November 16, 2005, 10:57 AM
 */

package org.gemini.rules.dXX;
import org.gemini.FeatureNode;
import org.gemini.sources.SourceReference;
import java.util.ArrayList;
import org.slage.framework.Attribute;
import org.gemini.rules.dXX.spellcomponents.SpellComponent;

/**
 * Defines a magic spell in dXX.

  <b>Licensing Notice</b><br>
   The rules defined in this file are provided under the terms of the Open Gaming 
   License v1.0a. The text of this license can be viewed 
   <a href="http://www.wizards.com/d20/files/OGLv1.0a.rtf">here</a>. 
 

 * @author Jaeden
 */
public class DxxSpell
    <NodeType extends FeatureNode<? extends DxxSpell>>
    extends DxxMetaphysicalEffect<FeatureNode<DxxSpell>>
{    
    
    /** TODO better node */
    public FeatureNode<DxxSpell> getNode() { return new FeatureNode<DxxSpell>(this); }
    
    /** A list of Components needed to cast the spell */
    private ArrayList<SpellComponent> components = new ArrayList<SpellComponent>();
    
   /** Creates a new instance of DxxSpell 
        @param _name Name of the spell
        @param _src SourceReference for the source of this DxxSpell */
    public DxxSpell(String _name, SourceReference<?> _src)
    {
        super(_name, _src);        
        setAttribute(new Attribute<DxxAction>("Casting Time", DxxAction.STANDARD));
        
    }
      
   /** Accept a Visitor
      @param aVisitor visitor to accept */        
    public void accept(org.gemini.GeminiObjectVisitor aVisitor) {aVisitor.accept(this); }  
}
