/*
 * StatisticSet.java
 *
 * Created on November 27, 2005, 6:00 PM
 */

package org.gemini;
import java.util.ArrayList;

/**
 * Stores a set of related Statistics, such as the six ability scores or three saving throws
 * of dXX. Stores these values as an ArrayList of StatisticNodes. The set is type safe to the base
 * types of statistic and node desired, and stores each Node in a list such that it can be 
 * popped off later like a stack, to store each modification to a statistic separately with a 
 * grantor and possibly conditions or expirations.
 *
 * @author  Jaeden
 */
public class StatisticSet <StatType extends Statistic, NodeType extends StatisticNode<StatType, ? extends Grantor>> 
    implements java.io.Serializable
{
    /** List of the statistic values */
    private ArrayList<NodeType> nodes = new ArrayList<NodeType>();
    
    /** Creates a new instance of StatisticSet */
    public StatisticSet() { /* empty */ }
    
    /** Add a node 
      @param node Node to add */
    public void add(NodeType node) { nodes.add(node); }
    
    /** Remove a node 
      @param node Node to remove */
    public void remove(NodeType node) { nodes.remove((Object)node); }
    
    /** Get the current value of a Statistic
     * @param stat Statistic to get value for
     * @return value of the statistic */
    public int get(StatType stat)
    {
        int value = 0;
        for (NodeType node : nodes)
        {
            // Null statistic affects "all"
            if (node.getStatistic() == null || node.getStatistic().equals(stat))
                value += node.getValue();
        }
        return value;
    }
    
    /** Get a list of nodes which modify the value 
      @param stat Statistic to get modifiers for 
      @return list of nodes wihch modify it */
    public ArrayList<NodeType> getNodes(StatType stat)
    {        
            ArrayList<NodeType> toReturn  = new ArrayList<NodeType>();
            for (NodeType node: nodes)
            {
                // Null statistic affects "all"
                if (node.getStatistic() == null || node.getStatistic().equals(stat))
                        toReturn.add(node);
            }
            return toReturn;    
    }
    
     
    /** Remove all statistics provided by a given Grantor
     *  @param grantor Grantor to search for. */
    public void removeGrantor(Grantor grantor)
    {
        for (NodeType N : nodes)
        {
            if (N.getGrantor().equals(grantor))
                nodes.remove(N);
        }
    }
    
}
