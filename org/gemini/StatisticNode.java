/*
 * StatisticNode.java
 *
 * Created on November 17, 2005, 2:09 AM
 */

package org.gemini;

/**
 * Defines a small data structure to pair a Statistic with its value for a
 * particular Character. This object should be extended to provide type safety
 * for statistics, as well as checks and such.
 *
 * TODO: Conditional bonuses
 * TODO: Expiration on temporary effects
 *
 * @author  Jaeden
 */
public class StatisticNode<StatType extends Statistic, GrantType extends Grantor> 
    extends AbstractStatisticNode<StatType, GrantType>    
{
    
    /** statistic value  */  private int value;
    
    /** Creates a new instance of StatisticNode 
        @param _stat Statistic
        @param _value value of the statistic 
        @param _grantor grantor of the statistic node */
    public StatisticNode(StatType _stat, int _value, GrantType _grantor) 
    {
        super(_stat, _grantor);
        value = _value;                
    }       
    
    /** Getter for statistic value.
     * @return Value of statistic.     */
    public int getValue() { return value;  }    
    
    /** Setter for statistic value.
     * @param _value New value of statistic.   */
    public void setValue(int _value) { this.value = _value; }
    
    /** String representation of score and value
     * @return string representation */
    public String toString() { 
        return getStatistic().getName() + ": " + Integer.toString(getValue()); 
    }
    
    /** Check equality of another object
     * @param other object to check
     * @return true if this == other */
    public boolean equals(Object other)
    {
        if (other instanceof StatisticNode == false)
            return false;
        
        StatisticNode node = (StatisticNode)other;
        
        return (super.equals(other) && node.getValue() == getValue());
    }
    
     /** Compare this object to another object
     *  @param obj object to compare to
     *  @return negative if this is less, 0 if this == obj, positive if this is more */
    public int compareTo(Object obj) 
    {
        if (obj instanceof StatisticNode == false)
            return 0;
        
        StatisticNode node = (StatisticNode)obj;
        return getValue() - node.getValue();        
    }
    
}