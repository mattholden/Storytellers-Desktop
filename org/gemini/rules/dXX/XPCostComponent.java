/*
 * XPCostComponent.java
 *
 * Created on November 14, 2005, 10:17 AM
 */

package org.gemini.rules.dXX;
import org.gemini.ObjectWithCost;
import org.gemini.rules.dXX.spellcomponents.SpellComponent;
/**
 * Defines a experience cost component. Used for spells, psionics, craft skills, etc.
 
  <b>Licensing Notice</b><br>
   The rules defined in this file are provided under the terms of the Open Gaming 
   License v1.0a. The text of this license can be viewed 
   <a href="http://www.wizards.com/d20/files/OGLv1.0a.rtf">here</a>. 
 

 * @author  Jaeden
 */
public class XPCostComponent 
    implements ObjectWithCost.Mutable, SpellComponent
{
    
    /** Cost of the spell in experience.  */
    private int cost = 0;    
     
    /** Creates a new instance of XPCostComponent         
        @param _cost Cost of the spell in experience.     */
    public XPCostComponent(int _cost) 
    {
        setDescription("Character must spend " + Integer.toString(cost) + " in XP."); 
        cost = _cost;  
    }
    
    /** Getter for cost.
     * @return Value of property cost.     */
    public int getCost() { return cost;  }
    
    /** Setter for cost.
     * @param _cost New value of property cost.      */
    public void setCost(int _cost) { this.cost = cost;  }
          
    /** String description of the object */
    private String description; 
    
    /** Accessor for description
     @return description */
    public String getDescription() {  return description;      } 
    
    /** Mutator for description
     @param _desc new description */
    public void setDescription(String _desc) { description = _desc; }    
     
    /** Test equality
     * @param other object to test
     * @return true if this == other */
    public boolean equals(Object other)
    {
        if (other.getClass().equals(getClass()) == false)
            return false;
        
        return (super.equals(other) &&
                       ((XPCostComponent)other).cost == cost);
    }
    
    /** Get string representation
     * @return string */
    public String toString() 
    {
        return "Character must spend " + Integer.toString(cost) + " in experience points."; 
    }
    
}
