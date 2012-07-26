/*
 * DxxAbilityScore.java
 *
 * Created on November 17, 2005, 1:46 AM
 */

package org.gemini.rules.dXX;
import org.gemini.Statistic;
import org.gemini.sources.SourceSectionReference;
import org.gemini.sources.SourceReference;
import org.gemini.sources.SourceURL;


/**
 * Defines a movement type and rate in dXX.

  <b>Licensing Notice</b><br>
   The rules defined in this file are provided under the terms of the Open Gaming 
   License v1.0a. The text of this license can be viewed 
   <a href="http://www.wizards.com/d20/files/OGLv1.0a.rtf">here</a>. 
 

 * @author  Jaeden
 */
public class DxxMovementRate extends Statistic    
{        

	/** Source for all the ability scores */
	protected static final SourceSectionReference<SourceURL> SRD = 
            new SourceSectionReference<SourceURL>(DxxSystem.SRD35, "Types, Subtypes and Special Abilities");
                            
        /** Land */
        public static final DxxMovementRate LAND = 
            new DxxMovementRate("Land", SRD,
            "Speed at which the character moves on land (base speed).");
        
        /** Burrow */
        public static final DxxMovementRate BURROW = 
            new DxxMovementRate("Burrow", SRD, 
            "A creature with a burrow speed can tunnel through dirt, but not through rock unless the descriptive text says " +
            "otherwise. Creatures cannot charge or run while burrowing. Most burrowing creatures do not leave behind " +
            "tunnels other creatures can use (either because the material they tunnel through fills in behind them or because " +
            "they do not actually dislocate any material when burrowing); see the individual creature descriptions for details.");
        
        /** Climb */
        public static final DxxMovementRate CLIMB = 
            new DxxMovementRate("Climb", SRD, 
            "A creature with a climb speed has a +8 " +
            "racial bonus on all Climb checks. The creature must make a Climb check to climb any wall or slope with a DC " +
            "of more than 0, but it always can choose to take 10 even if rushed or threatened while climbing. The creature " +
            "climbs at the given speed while climbing. If it chooses an accelerated climb it moves at double the given climb " +
            "speed (or its base land speed, whichever is lower) and makes a single Climb check at a -5 penalty. " +
            "Creatures cannot run while climbing. A creature retains its Dexterity bonus to Armor Class (if any) while " +
            "climbing, and opponents get no special bonus on their attacks against a climbing creature");

        /** Fly */
        public static final DxxMovementRate FLY = 
            new DxxMovementRate("Fly", SRD, 
            "A creature with a fly speed can move through the air at the indicated speed if carrying no more than a light load. " +
            "(Note that medium armor does not necessarily constitute a medium load.) All fly speeds include a parenthetical " +
            "note indicating maneuverability.");
        
        /** Swim */
        public static final DxxMovementRate SWIM = 
            new DxxMovementRate("Swim", SRD, 
            "A creature with a swim speed can move through water at its swim speed without making Swim checks. " +
            "It has a +8 racial bonus on any Swim check to perform some special action or avoid a hazard. The creature " +
            "can always can choose to take 10 on a Swim check, even if distracted or endangered. The creature can use the " +
            "run action while swimming, provided it swims in a straight line. ");

        
     
    /** Creates a new instance of DxxMovementRate 
        @param _name Name of the movement type
        @param _src Source of the movement type
        @param _desc Description of the movement type */
    public DxxMovementRate(String _name, SourceReference<?> _src, String _desc) 
    {
        super(_name, _src, _desc);
    }
    
        
      
}