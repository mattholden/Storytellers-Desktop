package org.slage.framework;

/**
 * Interface for resolving the type of a SlageFrameworkObject to something more specific than just "SlageFrameworkObject".
 * Add new subclasses to this and implement accept(SlageFrameworkObjectVisitor) as necessary.
 * 
 * This should be extended in Slage and any Slage framework clients to include an accept() method for every known subclass of 
 * SlageFrameworkObject. 

 * @author Travis Savo <Travis@sq7.org>
 */
public interface SlageFrameworkObjectVisitor {
	public void accept(SlageFrameworkObject anObject);
	
}
