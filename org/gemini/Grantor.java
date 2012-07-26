/*
 * FeatureGrantor.java
 *
 * Created on December 5, 2005, 9:55 AM
 */

package org.gemini;

/**
 * Defines an interface for classes which can grant features or nodes.
 *
 * @author  Jaeden
 */
public interface Grantor extends java.io.Serializable
{

    /** Must implement a toString()
     *  @return string representation of the grantor */
    public String toString();
    
}
