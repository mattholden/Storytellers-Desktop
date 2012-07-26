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
 * Defines a small data structure to pair movement rates with their values. 

  <b>Licensing Notice</b><br>
   The rules defined in this file are provided under the terms of the Open Gaming 
   License v1.0a. The text of this license can be viewed 
   <a href="http://www.wizards.com/d20/files/OGLv1.0a.rtf">here</a>. 
 

 * @author  Jaeden
 */
public class DxxMovementRateNode<GrantType extends org.gemini.Grantor> extends StatisticNode<DxxMovementRate, GrantType>    
{
    /** Creates a new instance of DxxMovementRateNode 
        @param _score movement rate
        @param _value value of the movement rate
        @param _grantor Grantor of this node */
    public DxxMovementRateNode( DxxMovementRate _score, int _value, GrantType _grantor) 
    {
        super(_score, _value, _grantor);
    }       
    
}
