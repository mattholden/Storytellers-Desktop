/*
 * NamedObject.java
 *
 * Created on October 17, 2005, 1:11 PM
 */

package org.slage.framework;

/**
 * Simple interface for stuff that has a name
 * 
 * @author <a href="mailto:Matt@SQ7.org">Matt Holden</a>
 */
public interface NamedObject {

	/** Get the object's name
	 * @return the name	 */
	public abstract String getName();

	/** Set the object's name
	 * @param _name the object's new name 	 */
	public abstract void setName(String _name);

        /** Ststic comparator for alphabetical sorts */
        public static final java.util.Comparator alphabetizer = new java.util.Comparator()
        {
            public int compare(Object o1, Object o2)
            {
                   if (o1 instanceof NamedObject == false || o2 instanceof NamedObject == false)
                       return 0;
                   
                   String NO1 = ((NamedObject)o1).getName().toUpperCase();
                   String NO2 = ((NamedObject)o2).getName().toUpperCase();
                   
                   return NO1.compareTo(NO2);
            }
        };
        
}
