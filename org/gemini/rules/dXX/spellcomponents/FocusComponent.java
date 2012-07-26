/*
 * FocusComponent.java
 *
 * Created on November 14, 2005, 10:17 AM
 */

package org.gemini.rules.dXX.spellcomponents;
import org.gemini.ObjectWithCost;


/**
 * Defines a focus spell component. 
 
  <b>Licensing Notice</b><br>
   The rules defined in this file are provided under the terms of the Open Gaming 
   License v1.0a. The text of this license can be viewed 
   <a href="http://www.wizards.com/d20/files/OGLv1.0a.rtf">here</a>. 
 

 * @author  Jaeden
 */
public class FocusComponent extends AbstractSpellComponent
    implements ObjectWithCost.Mutable
{
    
    /** Cost of the item in COPPER pieces. 0 = Negligible.*/
    private int cost = 0;    
     
    /** Creates a new instance of FocusComponent 
        @param _desc Description of the focus item.
     *  @param _cost Cost of the item in COPPER pieces. 
        <code>null</code> is valid. */
    public FocusComponent(String _desc, int _cost) { super(_desc); cost = _cost;  }
    
    /** Creates a new instance of FocusComponent with a negligible cost.
        @param _desc Description of the focus item. */        
    public FocusComponent(String _desc) { super(_desc);  }
    
    /** Getter for cost.
     * @return Value of property cost.     */
    public int getCost() { return cost;  }
    
    /** Setter for cost.
     * @param _cost New value of property cost.      */
    public void setCost(int _cost) { this.cost = cost;  }
    
      /** Test equality
     * @param other object to test
     * @return true if this == other */
    public boolean equals(Object other)
    {
        if (other.getClass().equals(getClass()) == false)
            return false;
        
        return (super.equals(other) &&
                       ((FocusComponent)other).cost == cost);
    }
    
    /** String representation (TODO: Cost?)
     * @return string representation */
    public String toString()     {
        return "Focus Component" + 
                       ((getDescription() == null) ? "" : (": " + getDescription()));                       
                       
    }
}
