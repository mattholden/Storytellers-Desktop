/*
 * AbstractStatisticNode.java
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
public abstract class AbstractStatisticNode<StatType extends Statistic, GrantType extends Grantor> 
     implements java.io.Serializable, Comparable, GrantedObject<GrantType>
{
    
    /** Statistic object */  private StatType statistic;
    /** Statistic grantor */ private GrantType grantor;
    
    /** Creates a new instance of AbstractStatisticNode 
        @param _stat Statistic
        @param _grantor grantor of the statistic node */
    public AbstractStatisticNode(StatType _stat, GrantType _grantor) 
    {
        statistic = _stat;
        setGrantor(_grantor);
    }       
    
    /** Getter for linked statistic.
     * @return Value of linked statistic.      */
    public StatType getStatistic() { return statistic;  }
     
    /** Check equality of another object
     * @param other object to check
     * @return true if this == other */
    public boolean equals(Object other)
    {
        if (other instanceof AbstractStatisticNode == false)
            return false;
        
        AbstractStatisticNode node = (AbstractStatisticNode)other;        
        return (getStatistic().equals(node.getStatistic()));
        
    }
         
    /** Accessor for grantor
    @return grantor */
    public GrantType getGrantor() { return grantor; }

    /** Mutator for grantor
    @param _grantor new grantor */
    public void setGrantor(GrantType _grantor) { grantor = _grantor; }
    
}