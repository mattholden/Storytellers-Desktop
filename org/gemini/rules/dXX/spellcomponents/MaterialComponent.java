/*
 * MaterialComponent.java
 *
 * Created on November 14, 2005, 10:17 AM
 */

package org.gemini.rules.dXX.spellcomponents;
import org.gemini.ObjectWithCost;

/**
 * Defines a material spell component. 
 
  <b>Licensing Notice</b><br>
   The rules defined in this file are provided under the terms of the Open Gaming 
   License v1.0a. The text of this license can be viewed 
   <a href="http://www.wizards.com/d20/files/OGLv1.0a.rtf">here</a>. 
 

 * @author  Jaeden
 */
public class MaterialComponent extends AbstractSpellComponent 
    implements ObjectWithCost.Mutable
{
    
    /** Cost of the item in COPPER pieces. 0 = Negligible.*/
    private int cost = 0;    
     
    /** Creates a new instance of MaterialComponent 
        @param _desc Description of the material item.
     *  @param _cost Cost of the item in COPPER pieces. 
        <code>null</code> is valid. */
    public MaterialComponent(String _desc, int _cost) { super(_desc); cost = _cost;  }
    
    /** Creates a new instance of MaterialComponent with a negligible cost.
        @param _desc Description of the material item. */        
    public MaterialComponent(String _desc) { super(_desc);  }
    
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
                       ((MaterialComponent)other).cost == cost);
    }
    
    
    /** String representation  (TODO: Cost?)
     * @return string representation */
    public String toString()     {
        return "Material Component" + 
                       ((getDescription() == null) ? "" : (": " + getDescription()));
    }
}
