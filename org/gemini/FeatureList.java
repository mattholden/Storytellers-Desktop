/*
 * FeatureList.java
 *
 * Created on November 13, 2005, 12:17 AM
 */

package org.gemini;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Stores a list of Features as a more easily manipulatable object.
 *
 * TODO: Optimize so that more ranks of the same skill, etc. go into the same node?
 *
 * @author  Jaeden
 */
public class FeatureList implements FeatureContainer
{
    /** The list of features */
    private ArrayList<Feature> features = new ArrayList<Feature>();
    
    /** Add a new feature
      @param f Feature to add */
    public void addFeature(Feature f) { features.add(f); }
    
    /** Remove a feature 
      @param f feature to remove */
    public void removeFeature(Feature f) { features.remove(f); }
    
    /** Remove a feature
      @param i index to remove */
    public void removeFeature(int i) { features.remove(i); }
    
    /** Get count of features
      @return features.size() */
    public int getFeatureCount() { return features.size(); }
        
    /** Creates a new instance of FeatureList */
    public FeatureList() {}    
    
    /** Creates a new instance of FeatureList 
        @param list an ArrayList of Features to add */
    public FeatureList(Collection<? extends Feature> list) { features.addAll(list); }
    
    /** Creates a new instance of FeatureList (copy constructor)
        @param list a FeatureList of Features to add */
    public FeatureList(FeatureList list) { features.addAll(list.features); }    
    
    /** Add an entire set of Features 
        @param list an ArrayList of Features to add */
    public void addFeatures(Collection<? extends Feature> list) { features.addAll(list); }
    
    /** Add an entire set of Features 
        @param list a FeatureList of Features to add */
    public void addFeatures(FeatureList list) { features.addAll(list.features); }
    
      /** Remove an entire set of Features 
        @param list an ArrayList of Features to remove */
    public void removeFeatures(Collection<? extends Feature> list) { features.removeAll(list); }
    
    /** Remove an entire set of Features 
        @param list a FeatureList of Features to remove */
    public void removeFeatures(FeatureList list) { features.removeAll(list.features); }
    
    /** Clear the feature list */
    public void clearFeatures() { features.clear(); }
    
       /** This is about the best we can do given our generic constraints.. */
        @SuppressWarnings("unchecked")
        
    /** Grant all of these features to a character 
     *  @param grantor Object granting the features
     *  @param gainer  Object gaining the features */
    public void grantFeatures(Grantor grantor, GeminiCharacter gainer)
    {     
        
        for (Feature F : features)
            gainer.addFeature(F.getNode(grantor));
    }
      
}
