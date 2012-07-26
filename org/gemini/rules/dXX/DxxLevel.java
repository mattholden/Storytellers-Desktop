/*
 * DxxLevel.java
 *
 * Created on November 18, 2005, 12:33 PM
 */

package org.gemini.rules.dXX;
import org.gemini.GeminiObject;
import java.io.Serializable;
import org.slage.framework.LinkedObject;
import org.gemini.FeatureList;

/**
 * Defines an interface for a character improvement level for a dXX Character.
 *
 * @author  Jaeden
 */
public interface DxxLevel<LevelType extends GeminiObject> 
    extends Serializable, LinkedObject<LevelType>, org.gemini.Grantor
{
    
  /** Get the level gained in this class 
      @return level */
    public int getLevel();
        
    /** Get the features for this level
     * @return features */
    public FeatureList getFeatures();
    
}
