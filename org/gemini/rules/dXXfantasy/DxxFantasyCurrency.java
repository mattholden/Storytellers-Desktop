/*
 * DxxFantasyCurrency.java
 *
 * Created on November 16, 2005, 9:46 AM
 */

package org.gemini.rules.dXXfantasy;
import org.gemini.Units;

/**
 * Defines a money changer for Dxx Fantasy games.
 
  <b>Licensing Notice</b><br>
   The rules defined in this file are provided under the terms of the Open Gaming 
   License v1.0a. The text of this license can be viewed 
   <a href="http://www.wizards.com/d20/files/OGLv1.0a.rtf">here</a>. 
 

 * @author  Jaeden
 */
public class DxxFantasyCurrency extends Units.Measurement
{
    
    /** Creates a new instance of DxxFantasyCurrency */
    public DxxFantasyCurrency() 
    {
        super(new Units.Unit("gold", "GP", 1.00));
        this.add(new Units.Unit("silver", "SP", 0.1));
        this.add(new Units.Unit("copper", "CP", 0.01));
        this.add(new Units.Unit("platinum", "PP", 10.0));        
    }
    
}
