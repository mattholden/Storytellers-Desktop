/*
 * GrantedObject.java
 *
 * Created on December 12, 2005, 9:35 AM
 */

package org.gemini;

/**
 * Defines an interface for an object which has a Grantor.
 * @author  Jaeden
 */
public interface GrantedObject <GrantType extends Grantor>
{
        /** Accessor for grantor
    @return grantor */
    public GrantType getGrantor();

    /** Mutator for grantor
    @param _grantor new grantor */
    public void setGrantor(GrantType _grantor);
    
    
}
