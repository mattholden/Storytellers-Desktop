/*
 * DxxFlyingManeuverability.java
 *
 * Created on November 12, 2005, 9:31 PM
 */

package org.gemini.rules.dXX;
import org.slage.framework.NamedObject;
import org.gemini.sources.ObjectFromSource;
import org.gemini.sources.SourceSectionReference;
import org.gemini.sources.SourceURL;   
import java.io.Serializable;

/**
 * Defines the maneuverability of an object in flight and the 
 * rules governing its motion. 

  <b>Licensing Notice</b><br>
   The rules defined in this file are provided under the terms of the Open Gaming 
   License v1.0a. The text of this license can be viewed 
   <a href="http://www.wizards.com/d20/files/OGLv1.0a.rtf">here</a>. 
 

 * @author  Jaeden
 */
public class DxxFlyingManeuverability 
    implements NamedObject, ObjectFromSource, Serializable, Comparable
{

     /** Perfect maneuverability */
     public static final DxxFlyingManeuverability PERFECT = 
        new DxxFlyingManeuverability(5, "Perfect", 1, true, true, 0, 360, 0, 360, 0, 360, 360, 1.0f, 360, 2.0f, 0);

     /** Good maneuverability */
     public static final DxxFlyingManeuverability GOOD = 
        new DxxFlyingManeuverability(4, "Good", 1, true, true, 5, 90, 5, 90, 5, 360, 360, 0.5f, 360, 2.0f, 0);
     
     /** Average maneuverability */
     public static final DxxFlyingManeuverability AVERAGE = 
        new DxxFlyingManeuverability(3, "Average", 0.5f, false, false, Integer.MAX_VALUE, 45, 5, 45, 5, 90, 60, 0.5f, 360, 2.0f, 5);
     
     /** Poor maneuverability */
     public static final DxxFlyingManeuverability POOR = 
        new DxxFlyingManeuverability(2, "Poor", 0.5f, false, false, Integer.MAX_VALUE, 45, 5, 0, Integer.MAX_VALUE, 45, 45, 0.5f, 45, 2.0f, 10);
     
     /** Clumsy maneuverability */
     public static final DxxFlyingManeuverability CLUMSY = 
        new DxxFlyingManeuverability(1, "Clumsy", 0.5f, false, false, Integer.MAX_VALUE, 45, 10, 0, Integer.MAX_VALUE, 45, 45, 0.5f, 45, 2.0f, 20);
     

    /** Name of the maneuverability type */
    private String name;

   /** The quality of the maneuverability
    *  Used only for comparisons */
    protected int quality;
    
    /** Minimum forward speed, expressed as a 
      percentage of the maximum speed of the creature */
    protected float minForwardSpeed = 0.5f;
    
    /** True if backward movement is allowed */
    protected boolean canMoveBackward = false;
    
    /** True if hovering is allowed */
    protected boolean canHover = false;
    
    /** Amount of speed sacrificed to reverse direction.
      Integer.MAX_VALUE is in place for objects which cannot fly backward. */
    protected int reverseCost = Integer.MAX_VALUE;
    
    /** How much the creature can turn after 
      covering the stated distance. (degrees) */
    protected float turnRadius = 45.0f;
    
    /** Amount of speed sacrificed to turn (in feet) */
    protected int turnCost = 5;
    
    /** Maximum turn radius in place (degrees) */
    protected float turnInPlaceRadius = 0.0f;
    
    /** Amount of speed sacrificed to turn in place (in feet) 
     Integer.MAX_VALUE is in place for objects which cannot turn in place. */
    protected int turnInPlaceCost = 5;
    
    /** Maximum turn in a space (degrees) */
    protected float maxTurnInSpace = 45.0f;
    
    /** Maximum up angle (degrees) */
    protected float maxUpAngle = 45.0f;
    
    /** Maximum down angle (degrees) */
    protected float maxDownAngle = 45.0f;
    
    /** Maximum up speed (expressed as a percentage) */
    protected float maxUpSpeed = 0.5f;
    
    /** Maximum down speed (expressed as a percentage) */
    protected float maxDownSpeed = 2.0f;
    
    /** Distance the object must fly level between 
      flying down and flying up (feet) */
    protected int betweenDownAndUp = 0;
    
   /** Accessor for name
     @return name */
    public String getName() { return name; }
    
    /** Mutator for name
     @param _name new name */
     public void setName(String _name) { name = _name; }
         
     /** Accessor for minimum forward speed
      (as a multiplier of the maximum speed overall)
      @return minimum forward speed */
    public float getMinSpeed() { return minForwardSpeed; }
        
    /** Accessor for the ability to hover
      @return true if the creature can hover */
    public boolean canHover() { return canHover; }
    
    /** Accessor for the ability to fly backward
      @return true if the creature can fly backward */
    public boolean canMoveBackward() { return canMoveBackward; }
    
    /** Accessor for the amount of distance lost from the maximum
      distance moved in a round in order to reverse directions (feet).
     
      @return the cost in feet, or Integer.MAX_VALUE if the 
      creature can't reverse direction in flight. */
    public int getReverseSpeedCost() { return reverseCost; }
    
    /** Accessor for how much the creature can turn (degrees).
      @return how much the creature can turn after covering the stated distance. */
    public float getTurnRadius() { return turnRadius; }
    
    /** Accessor for the distance it takes the creature to turn (feet)
      @return the distance it takes the creature to turn */
    public int getTurnSpeedCost() { return turnCost; }
    
    /** Accessor for the distance it takes the creature to turn in place (feet)
      @return the distance it takes the creature to turn in place */
    public int getTurnInPlaceSpeedCost() { return turnInPlaceCost; }
    
    /** Accessor for the maximum turn possible in one space
      @return maximum turn possible in a space (degrees) */
     public float getMaxTurnRadius() { return maxTurnInSpace; }
     
     /** Accessor for the maximum angle a creature can turn to in place (degrees).
      @return the maximum angle (in degrees) the creature can turn in place (0.0f if it can't) */
     public float getMaxTurnInPlace() { return turnInPlaceRadius; }
     
     /** Shortcut method to check if a creature can turn in place
      @return true if the creature can turn in place */
     public boolean canTurnInPlace() { return (turnInPlaceRadius > 0); }
     
     /** Accessor for the maximum upward angle of the creature (degrees) 
      *@return  the maximum upward angle of the creature (degrees) */
     public float getMaxUpAngle() { return maxUpAngle; }
     
     /** Accessor for the maximum downward angle of the creature (degrees) 
      *@return  the maximum downward angle of the creature (degrees) */
     public float getMaxDownAngle() { return maxDownAngle; }
     
     /** Accessor for the maximum downward speed of the creature (feet/round) 
      *@return  the maximum downward speed of the creature (feet/round) */
     public float getMaxDownSpeed() { return maxDownSpeed; }
     
     /** Accessor for the maximum upward speed of the creature (feet/round) 
      *@return  the maximum upward speed of the creature (feet/round) */
     public float getMaxUpSpeed() { return maxUpSpeed; }
     
     /** Accessor for the distance a creature must travel after flying down
      *before it can fly upward (feet).
      *@return the distance a creature must travel after flying down
      *before it can fly upward */
     public int getBetweenDownAndUp() { return betweenDownAndUp; }
    
    /** Creates a new instance of DxxFlyingManeuverability 
     * (Protected - use static constant values)
      @param _quality Quality (for sorting only)
      @param _name name of the maneuverability class
      @param _minForward minimum forward speed (%)
      @param _hover 'true' if the creature can hover
      @param _backward 'true' if the creature can fly backward
      @param _reverseCost speed cost to reverse direction (ft/rd)
      @param _turn maximum turn angle (deg.)
      @param _turnCost speed cost to turn (ft/rd)
      @param _turnIP maximum turn in place (deg.)
      @param _turnIPCost speed cost to turn in place (ft/rd)
      @param _maxTurn max turn in a space (deg.)
      @param _maxUpAngle maximum up angle (deg.)
      @param _maxUpSpeed maximum up speed (%)
      @param _maxDownAngle maximum down angle (deg.)
      @param _maxDownSpeed maximum down speed (%)
      @param _downUp minimum distance to fly level between flying down and flying up (ft)
     */
    protected DxxFlyingManeuverability(int _quality, 
                                        String _name,
                                        float _minForward,
                                        boolean _hover, 
                                        boolean _backward, int _reverseCost, 
                                        float _turn, int _turnCost,
                                        float _turnIP, int _turnIPCost,
                                        float _maxTurn,
                                        float _maxUpAngle, float _maxUpSpeed,
                                        float _maxDownAngle, float _maxDownSpeed,
                                        int _downUp)

    {
        quality = _quality;
        name = _name;
        minForwardSpeed = _minForward;
        canHover = _hover;
        canMoveBackward = _backward;
        reverseCost = _reverseCost;
        turnRadius = _turn;
        turnCost = _turnCost;
        turnInPlaceRadius = _turnIP;
        turnInPlaceCost = _turnIPCost;
        maxTurnInSpace = _maxTurn;
        maxUpAngle = _maxUpAngle;
        maxDownAngle = _maxDownAngle;
        maxUpSpeed = _maxUpSpeed;
        maxDownSpeed = _maxDownSpeed;
        betweenDownAndUp = _downUp;        
        
    }
    
       /** Compare this maneuverability class to another maneuverability class
     *  @param that A size to maneuverability class to
     *  @return negative if this is more agile than that, 0 if they are equal, 
        positive if that is more clumsy than this */
    public int compareTo(Object that)
    {
        if (that instanceof DxxFlyingManeuverability)
            return 0;
        
        DxxFlyingManeuverability other = (DxxFlyingManeuverability)that;
        return this.quality - other.quality;
    }
            
     /** Test equality of two sizes
      @param that other size to test
      @return true if this == that */
    public boolean equals(Object that)
    {
        return (compareTo(that) == 0);
    }
    
    /** Get size as a string 
     * @return string representation of the size (its name) */
    public String toString() { return getName(); }
         
    /** Source for all the maneuverabilities (Save memory) */
      /** Source ref for the overview */
    protected static final SourceSectionReference<SourceURL> SRD = 
        new SourceSectionReference<SourceURL>(DxxSystem.SRD35, 
        "Carrying, Movement, and Exploration");
    
     /** Accessor for source reference
      @return source */
     public SourceSectionReference<SourceURL> getSource() { return SRD; }
      

}
