/*
 * StatisticPool.java
 *
 * Created on November 27, 2005, 6:00 PM
 */

package org.gemini;
import java.util.ArrayList;
import org.gemini.sources.SourceReference;

/**
 * Stores a "pool" for a single statistic. Provides a collection of nodes to track changes in the statistic's <b>maximum</b> value,
 * as well as a "current" value. 
 *
 * @author  Jaeden
 */
public class StatisticPool<StatType extends Statistic> 
    implements java.io.Serializable
{ 
    /** List of the statistic values */
    private ArrayList<StatisticPoolNode> nodes = new ArrayList<StatisticPoolNode>();
    
    /** Allow negative values? */
    private boolean allowNegatives = false;
    
    /** Store the Statistic */
    private StatType statistic;
    
    /** The current value */
    private int current = 0;
    
    /** Cache the maximum value for speed */
    private int maximum = 0;
    
    /** Creates a new instance of StatisticPool
        @param _stat Statistic to track      
        @param _negatives If 'true', allow the current value to go negative. */
    public StatisticPool(StatType _stat, boolean _negatives)
    {
        statistic = _stat;
        allowNegatives = _negatives;
    }
    
    /** Creates a new instance of StatisticPool
        @param _stat Statistic to track               */
    public StatisticPool(StatType _stat)
    {
        this(_stat, false);   
    }

    /** Return 'true' if negative values are allowed 
       @return 'true' if negative values are allowed  */
    public boolean allowsNegatives() { return allowNegatives; }
    
    /** Set whether negative values are allowed.
     *  @param _allow If 'true', current can be incremented below 0. */
    public void setAllowNegatives(boolean _allow) { allowNegatives = _allow; }    
    
    /** Accessor for statistic (mutator deliberately omitted)
     * @return statistic */
    public StatType getStatistic() { return statistic; }
    
    /** Add a node 
      @param _value Value to add to the maximum
      @param _grantor Grantor of this change to the maximum */
    public void add(int _value, Grantor _grantor) { 
        nodes.add(new StatisticPoolNode(_value, _grantor)); 
        calculateMaximum();
    }
    
    /** Remove a node 
      @param node Node to remove */
    public void remove(StatisticPoolNode node) {
        nodes.remove((Object)node); 
        calculateMaximum();
    }
    
    /** Get the current value of a Statistic     
     * @return value of the statistic */
    public int getMaximum() { return maximum; }
    
    /** Calculate the maximum value */
    private void calculateMaximum() 
    {
        maximum = 0;
        for (StatisticPoolNode node : nodes)                    
                maximum += node.getValue();        
    }
       
    /** Get a list of nodes which modify the value      
      @return list of nodes wihch modify it */
    public ArrayList<StatisticPoolNode> getNodes()   {   return nodes;    }
        
    /** Accessor for current value
     *  @return current value */
    public int getCurrent() { return current; }
    
    /** Mutator for current value. Will return 'false' and make no change if:
     *  <li>The new value is above the maximum
     * <li> The new value is below 0 and allowNegatives is 'false' 
     *
     * @param _current new current value  
       @return 'false' if the set value is above the */
    public boolean setCurrent(int _current)
    { 
        if (_current > maximum || (_current < 0 && allowNegatives == false))
            return false;
        current = _current; 
        return true;
    }
    
    /** Incrementor for current value. Will return 'false' and make no change if:
     * <li>The new value is above the maximum
     * <li> The new value is below 0 and allowNegatives is 'false' 
     *
     *  @param _delta amount to change the value by (positive or negative) */
    public boolean incCurrent(int _delta) 
    { 
        if (current + _delta > maximum || (current + _delta < 0 && allowNegatives == false))
            return false;
        
        current += _delta; 
        return true;
    }
    
    /** String representation in the form StatisticName: current/max 
     * @return string representation */
    public String toString() { return statistic.getName() + ": " + Integer.toString(current) + " / " + Integer.toString(getMaximum()); }
    
    /** Check if 'this' equals 'that'. Returns true if the statistic, current and maximum all match.
     *  @param that Object to check against
     * @return 'true' if this == that */
    public boolean equals(Object that)
    {
        if (that instanceof StatisticPool == false) return false;
        
        StatisticPool other = (StatisticPool)that;
        return (statistic.equals(other.statistic) && getMaximum() == other.getMaximum() && getCurrent() == other.getCurrent());
    }
    
    
    
    
    
    
    /** A Node to store values. Since StatisticPools deal only with integers, and we've only got 
     *  one statistic to track (and that in the main Pool vs. the nodes), separate node classes do not need to be constructed
	as they are in StatisticSets. Therefore, we can use one simple node, and make it an internal class, to get the job done 
       with a clean interface for the user. */
public static class StatisticPoolNode implements java.io.Serializable
{
      /** statistic value  */  private int value;
      /** Statistic grantor */ private Grantor grantor;
 
    /** Creates a new instance of StatisticPoolNode 
        @param _value value of the statistic 
        @param _grantor grantor of the statistic node */
    public StatisticPoolNode(int _value, Grantor _grantor) 
    {
        this.grantor = _grantor;
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
        return Integer.toString(getValue()) + ": " + getGrantor().toString();
    }
    
    /** Check equality of another object
     * @param other object to check
     * @return true if this == other */
    public boolean equals(Object other)
    {
        if (other instanceof StatisticPoolNode == false)
            return false;
        
        StatisticPoolNode node = (StatisticPoolNode)other;
        
        return (grantor.equals(node.grantor) && node.getValue() == getValue());
    }
    
     /** Compare this object to another object
     *  @param obj object to compare to
     *  @return negative if this is less, 0 if this == obj, positive if this is more */
    public int compareTo(Object obj) 
    {
        if (obj instanceof StatisticPoolNode == false)
            return 0;
        
        StatisticPoolNode node = (StatisticPoolNode)obj;
        return getValue() - node.getValue();        
    }
    
    /** Accessor for grantor
    @return grantor */
    public Grantor getGrantor() { return grantor; }

    /** Mutator for grantor
    @param _grantor new grantor */
    public void setGrantor(Grantor _grantor) { grantor = _grantor; }

} // end inner class StatisticPoolNode

}
