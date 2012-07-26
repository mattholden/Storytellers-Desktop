/*
 * FeatureContainer.java
 *
 * Created on November 13, 2005, 2:06 AM
 */

package org.gemini;
import java.util.Collection;

/**
 * Defines an interface for an object with a list of Features. 
 *
 * @author  Jaeden
 */
public interface FeatureContainer extends java.io.Serializable
{    
    /** Add a new feature
      @param f Feature to add */
    public void addFeature(Feature f);
    
    /** Remove a feature 
      @param f feature to remove */
    public void removeFeature(Feature f);
           
    /** Get count of features
      @return features.size() */
    public int getFeatureCount();
    
    /** Add an entire set of Features 
        @param list an ArrayList of Features to add */
    public void addFeatures(Collection<? extends Feature> list);
    
    /** Add an entire set of Features 
        @param list a FeatureList of Features to add */
    public void addFeatures(FeatureList list);
    
    /** Remove an entire set of Features 
        @param list an ArrayList of Features to remove */
    public void removeFeatures(Collection<? extends Feature> list);
    
    /** Remove an entire set of Features 
        @param list a FeatureList of Features to remove */
    public void removeFeatures(FeatureList list);
    
    /** Clear the feature list */
    public void clearFeatures();
}
