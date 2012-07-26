/*
 * DxxPsionicPower.java
 *
 * Created on November 16, 2005, 10:57 AM
 */

package org.gemini.rules.dXX;
import org.gemini.FeatureNode;
import org.gemini.sources.SourceReference;
import org.slage.framework.Attribute;
import java.util.ArrayList;
import org.gemini.rules.dXX.psionicdisplays.PsionicDisplay;


/**
 * Defines a psionic power in dXX.

  <b>Licensing Notice</b><br>
   The rules defined in this file are provided under the terms of the Open Gaming 
   License v1.0a. The text of this license can be viewed 
   <a href="http://www.wizards.com/d20/files/OGLv1.0a.rtf">here</a>. 
 

 * @author Jaeden
 */
public class DxxPsionicPower
    <NodeType extends FeatureNode<? extends DxxPsionicPower>>
    extends DxxMetaphysicalEffect<FeatureNode<DxxPsionicPower>>
{    
    
    /** TODO better node */
    public FeatureNode<DxxPsionicPower> getNode() { return new FeatureNode<DxxPsionicPower>(this); }
    
    
   /** Creates a new instance of DxxPsionicPower 
        @param _name Name of the psionic power
        @param _src SourceReference for the source of this DxxPsionicPower */
    public DxxPsionicPower(String _name, SourceReference<?> _src)
    {
        super(_name, _src);      
        setAttribute(new Attribute<DxxAction>("Manifestation Time", DxxAction.STANDARD));
                        
        // Psi effects don't have any spell components but they can have XP Cost.. 
        setAttribute(new Attribute<XPCostComponent>("XP Cost", NO_XP_COST));
        
    }
    
    
    /** list of Displays (Auditory, Visual, etc.) */
    protected ArrayList<PsionicDisplay> displays = new ArrayList<PsionicDisplay>();
        
    /** Add a Display
     *  @param _display Display to add */
    public void addDisplay(PsionicDisplay _display) { displays.add(_display); }

    /** Remove a Display
     *  @param _display Display to remove */
    public void removeDisplay(PsionicDisplay _display) { displays.remove(_display); }
    
    /** Clear Display list */
    public void clearDisplays() { displays.clear(); }
    
    /** Get Display count 
     *  @return Display count */
    public int getDisplayCount() { return displays.size(); }
     
    /** static XPCostComponent for 0 (save memory) */
    private static final XPCostComponent NO_XP_COST = new XPCostComponent(0);
    
    /** Easy formula to determine the power point cost: (level * 2) - 1 
     *  @param _powerLevel  Level of the power 
     *  @return power point cost to manifest this power */
    public static int getPowerPointCost(int _powerLevel) { return ( (_powerLevel == 0) ? 0 : (_powerLevel * 2) - 1); }
    
    
    
    /** Accept a Visitor
      @param aVisitor visitor to accept */        
    public void accept(org.gemini.GeminiObjectVisitor aVisitor) {aVisitor.accept(this); }  
}
