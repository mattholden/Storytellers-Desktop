/*
 * DxxHealthStatistic.java
 *
 * Created on December 6, 2005, 11:14 PM
 */

package org.gemini.rules.dXX;
import org.gemini.Statistic;
import org.gemini.sources.SourceSectionReference;
import org.gemini.sources.SourceReference;
import org.gemini.sources.SourceURL;


/**
 * Defines character health-related statistics such as hit points, vitality and wounds which are pooled (having a current/maximum value).
 *
 * @author  Jaeden
 */
public class DxxHealthStatistic extends Statistic
{
    
   
    /** Creates a new instance of DxxHealthStatistic 
        @param _name Name of the statistic 
        @param _src Source of the statistic
        @param _desc Description of the statistic */
    public DxxHealthStatistic(String _name, SourceReference<?> _src, String _desc) 
    {
        super(_name, _src, _desc);
    }    
      
           
    /** Source for all the ability scores */
    protected static final SourceSectionReference<SourceURL> SRD = 
            new SourceSectionReference<SourceURL>(DxxSystem.SRD35, "Combat I");
    
    /** Defines a Health Statistic for hit points */
    public static final DxxHealthStatistic HITPOINTS = new DxxHealthStatistic("Hit Points", SRD, 
            "When your hit point total reaches 0, you’re disabled. When it reaches -1, you’re dying. When it gets to -10, you’re dead.");
    
    
}
