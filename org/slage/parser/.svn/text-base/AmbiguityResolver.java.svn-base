/*
 * AmbiguityResolver.java
 *
 * Created on November 7, 2005, 1:22 PM
 */

package org.slage.parser;

import java.util.Collection;

import org.slage.Slage;
import org.slage.SlageObject;
import org.slage.framework.Point3D;
import org.slage.framework.SlageFrameworkObject;
import org.slage.geometry.Geometry;

/**
 * Provides some more closely coupled checks to help resolve ambiguities in
 * parser output.
 * 
 * @author Jaeden
 */
public abstract class AmbiguityResolver {

    /** Resolve ambiguity on an object. 
      @param input ambiguous input exception from the parser
      @return an object if one can be isolated, otherwise null */
    public static SlageObject resolve(AmbiguousInputException input)
    {
        SlageObject object = null;
        
        object = resolveByRegisteredHandlers(input);
        if (object != null) return object;
        
        object = resolveByProximity(input);
        if (object != null) return object;
        
        return null;        
    }
    
	/** defeat instanciation */
	private AmbiguityResolver() { /* no makey makey */ }
		
	/**
	 * The number of pixels considered to be "too close to call" for ambiguity
	 * resolution by distance
	 */
	public static final int PROXIMITY_PIXEL_TOLERANCE = 20;

	/**
	 * Resolve ambiguity by proximity to the object.
	 * 
	 * TODO don't assume the person issuing the command is the Player.. might be a
	 * bot
	 * 
	 * @param input The AmbiguousInputException the parser threw
	 * @return the "final answer" object, if one could be determined - null if it
	 *         couldn't
	 */
	public static SlageObject resolveByProximity(AmbiguousInputException input) {
		Collection<Description> values = input.getPossibles();
		SlageObject closest = null;
		int closestDistance = Integer.MAX_VALUE;
		int tolerance = PROXIMITY_PIXEL_TOLERANCE * PROXIMITY_PIXEL_TOLERANCE;

		if (Slage.getCurrentGame() == null || Slage.getCurrentGame().getPlayer() == null)
			return null;

		Point3D charPos = org.slage.Slage.getCurrentGame().getPlayer().getPosition();

		for (Description D : values) {
			SlageFrameworkObject fObj = D.getOwner();
			if (fObj == null || fObj instanceof SlageObject == false)
				continue;

			SlageObject obj = (SlageObject) fObj;
			Point3D pos = obj.getPosition();

			int distance = (int) Geometry.distanceSquared(charPos, pos);
			if (distance + tolerance < closestDistance) {
				closestDistance = distance;
				closest = obj;
			}
		}
		return closest;
	}

	/**
	 * Resolves ambiguity by checking if only one of the objects has a handler for
	 * the given verb.
	 * 
	 * @param input The AmbiguousInputException the parser threw
	 * @return the "final answer" object, if one could be determined - null if it
	 *         couldn't
	 */
	public static SlageObject resolveByRegisteredHandlers(AmbiguousInputException input) {
		Collection<Description> values = input.getPossibles();
		SlageObject returned = null;

		// can't check without a verb
		String verb = input.getVerb();
		if (verb == null)
			return null;

		for (Description D : values) {
			SlageFrameworkObject fObj = D.getOwner();

			// todo: just use slageframeworkobject once handlers are moved
			if (fObj == null || fObj instanceof SlageObject == false)
				continue;
			SlageObject obj = (SlageObject) fObj;

			if (obj.getHandlers(verb).size() > 0) {
				// more than one object has a handler on this verb; still ambiguous
				if (returned != null)
					return null;

				// this is the first object to handle that verb; it's in the running
				returned = obj;
			}
		}

		// if we get here and returned is null, no object had a handler on that verb
		// if returned != null it is the only possible object with a handler on the
		// verb
		// so it's the only one who can react
		return returned;
	}

}
