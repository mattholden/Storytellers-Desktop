/*
 * GenderNamedObject.java
 *
 * Created on November 11, 2005, 3:01 AM
 */

package org.gemini;
import org.slage.framework.NamedObject;

/**
 * Provides an interface for objects which can have a feminine name as well as a 
 * masculine name. Used for things like classes, where there can be a Sorcerer or
 * a Sorceress, for example. Note that masculine name is also provided - this is 
 * for things like the Enchantment domain, whose getName() would yield "Enchantment",
 * feminine name "Enchantress", and masculine name "Enchanter". For situations
 * like classes where only two names are appropriate, simply override the masculine
 * name to point to the base name. 
 *
 * @author  Jaeden
 */
public interface GenderNamedObject extends NamedObject 
{
    
    	/** Get the object's feminine name
	 * @return the name	 */
	public abstract String getFeminineName();

	/** Set the object's feminine name
	 * @param _name the object's new name 	 */
	public abstract void setFeminineName(String _name);

        /** Get the object's masculine name
	 * @return the name	 */
	public abstract String getMasculineName();

	/** Set the object's masculine name
	 * @param _name the object's new name 	 */
	public abstract void setMasculineName(String _name);
    
}
