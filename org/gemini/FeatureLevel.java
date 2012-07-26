/*
 * FeatureLevel.java
 *
 * Created on November 15, 2005, 10:39 PM
 */

package org.gemini;
import java.io.Serializable;
import org.slage.framework.LinkedObject;
import org.gemini.Feature;

/**
 * Defines the level/class combination in a progression.
 * For example, if a spell is Clr 4 or Wiz 3.
 *
 * @author  Jaeden
 */
public class FeatureLevel
    <FeatureType extends Feature, ClassType extends GeminiObject>
    implements Serializable, LinkedObject<FeatureType>
{
    /** The feature that this level is for */
    private FeatureType feature;

    /** The Class that this level is for */
    private ClassType classLevel;

    /** The level number */
    private int level = 0;

    /** Creates a new instance of FeatureLevel
      @param _feature the Feature
      @param _class the character class
      @param _level the level number */
    public FeatureLevel(FeatureType _feature, ClassType _class, int _level)
    {
        feature = _feature;
        classLevel = _class;
        level = _level;
    }

    /** Get the class the level is for
      @return classLevel */
    public ClassType getClassForLevel() { return classLevel; }

    /** Get the level number
     * @return level number */
    public int getLevel() { return level; }

    /** Get the owner Feature
     * @return feature */
    public FeatureType getOwner() { return feature; }

    /** Set the owner feature
     * @param f owner feature */
    public void setOwner(FeatureType f) { feature = f; }

    /** Set the level number
     * @param _level level number */
    public void setLevel(int _level) { level = _level; }

    /** Set the class the level is for
     * @param _class class for the level */
    public void setClassForLevel(ClassType _class) { classLevel = _class; }

    /** Get the string representation of the level (eg. Wiz 2)
     * @return string representation */
    public String toString()
    {
        if (classLevel instanceof AbbreviatedObject)
        	return ((AbbreviatedObject)classLevel).getAbbreviation() + " " + Integer.toString(level);
        else
        	return classLevel.getName() + " " + Integer.toString(level);
    }

    /** Test equality of two FeatureLevels
     * @param that an object to test
     * @return true if this == that */
    public boolean equals(Object that)
    {
        if (that.getClass().equals(this.getClass()) == false)
            return false;

        FeatureLevel FL = (FeatureLevel)that;
        return (FL.feature.equals(feature) &&
                        FL.classLevel.equals(classLevel) &&
                        (FL.level == level));
    }

}
