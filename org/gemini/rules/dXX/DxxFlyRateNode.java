/*
 * DxxMovementRateNode.java
 *
 * Created on November 17, 2005, 2:09 AM
 */

package org.gemini.rules.dXX;
import org.gemini.Dice;
import org.gemini.StatisticNode;
import org.gemini.GeminiObject;

/**
 * Extends DxxMovementRateNode to add maneuverability for flying speeds.

  <b>Licensing Notice</b><br>
   The rules defined in this file are provided under the terms of the Open Gaming 
   License v1.0a. The text of this license can be viewed 
   <a href="http://www.wizards.com/d20/files/OGLv1.0a.rtf">here</a>. 
 

 * @author  Jaeden
 * @see org.gemini.rules.dXX.DxxMovementRateNode
 * @see org.gemini.rules.dXX.DxxFlyingManeuverability
 *
 */
public class DxxFlyRateNode<GrantType extends org.gemini.Grantor> extends DxxMovementRateNode<GrantType>
{    
    /** flight maneuverability */
    private DxxFlyingManeuverability maneuverability;
    
    /** Creates a new instance of DxxFlyRateNode 
        @param _value value of the movement rate
        @param _man Maneuverability
        @param _grantor Grantor of this node */
    public DxxFlyRateNode( int _value, DxxFlyingManeuverability _man, GrantType _grantor) 
    {
        super(DxxMovementRate.FLY, _value, _grantor);
        maneuverability = _man;
    }       
    
    /**
     * Getter for flight maneuverability.
     * @return Value of flight maneuverability.
     */
    public org.gemini.rules.dXX.DxxFlyingManeuverability getManeuverability() {
        return maneuverability;
    }
    
    /**
     * Setter for flight maneuverability.
     * @param _maneuverability New value of flight maneuverability.
     */
    public void setManeuverability(org.gemini.rules.dXX.DxxFlyingManeuverability _maneuverability) {
        this.maneuverability = _maneuverability;
    }
    
}