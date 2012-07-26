/*
 * AnimationController.java
 *
 * Created on August 10, 2005, 8:05 PM
 */

package org.slage.animation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.slage.Slage;
import org.slage.SlageObject;
import org.slage.framework.NotFoundException;

/**
 * 
 * Manages the list of available animations for an animated object.
 * 
 * @author <a href="mailto:Matt@SQ7.org">Matt Holden</a>
 */
public class AnimationController  {

	/** Logger instance */
	protected static transient final org.apache.commons.logging.Log LOG = org.apache.commons.logging.LogFactory.getLog(AnimationController.class);

	/**
	 * Creates a new instance of AnimationController
	 * 
	 * @param obj owner Object
	 */
	public AnimationController(SlageObject obj) {
		object = obj;
	}

	/** Animation currently playing */
	private Animation current;

	/** List of animations */
	private Map<String, Animation> mapAnimations  = new HashMap<String, Animation>();

	/** owner SlageObject */
	private transient SlageObject object;

	/**
	 * Add an animation to the list
	 * 
	 * @param A animation to add
	 */
	public void addAnimation(Animation A) {
		mapAnimations.put(A.getName(), A);

		A.setObject(object);
                
		// if this is the first one, set to current
		if (mapAnimations.size() == 1)
			current = A;
	}

	/**
	 * Get the given animation
	 * 
	 * @param strAnim animation name
	 * @return animation
	 * @throws NotFoundException
	 */
	public Animation getAnimation(String strAnim) throws NotFoundException {
		
                Animation A = mapAnimations.get(strAnim);
                if (A != null) return A;
                
		throw new NotFoundException("Animation", strAnim);
	}

	/**
	 * Accessor for current animation
	 * 
	 * @return current animation or 'null' if none is selected
	 */
	public Animation getCurrentAnimation() {
		return current;
	}

	/**
	 * Change current animation
	 * 
	 * @param strAnim animation name
	 * @throws NotFoundException
	 */
	public void setCurrentAnimation(String strAnim) throws NotFoundException {
		current = mapAnimations.get(strAnim);			
		if (current == null)
                    throw new NotFoundException("Animation", strAnim);    
                
                // remove the old current from the scheduler
                if (getCurrentAnimation() != null && Slage.getCurrentGame() != null)
                        Slage.getCurrentGame().removeEvent(getCurrentAnimation().getEvent());          

                // load the new current into the scheduler
                    if (Slage.getCurrentGame() != null)
                            Slage.getCurrentGame().addEvent(getCurrentAnimation().getEvent());

                getCurrentAnimation().start();
                
	}

	/**
	 * Get count of items in list
	 * 
	 * @return count
	 */
	public int getAnimationCount() {
		return mapAnimations.size();
	}

	/**
	 * Absorb the animations of another AnimationController
	 * 
	 * @param ac AnimationController to merge with
	 */
	public void merge(AnimationController ac) {
		this.mapAnimations.putAll(ac.mapAnimations);
	}

	/**
	 * Getter for property object.
	 * 
	 * @return Value of property object.
	 */
	public SlageObject getObject() {
		return object;
	}

	/**
	 * Setter for property object.
	 * 
	 * @param anObject New value of property object.
	 */
	public void setObject(SlageObject anObject) {
		this.object = anObject;
                Set<Map.Entry<String, Animation>> set = mapAnimations.entrySet();
		for (Map.Entry<String, Animation> m1 : set) {
			Animation obj = m1.getValue();
                        obj.setObject(anObject);
                }
	}

}
/*******************************************************************************
 * BEGIN LICENSE BLOCK **** Version: MPL 1.1/GPL 2.0/LGPL 2.1
 * 
 * The contents of this file are subject to the Mozilla Public License Version
 * 1.1 (the "License"); you may not use this file except in compliance with the
 * License. You may obtain a copy of the License at http://www.mozilla.org/MPL/
 * 
 * Software distributed under the License is distributed on an "AS IS" basis,
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License for
 * the specific language governing rights and limitations under the License.
 * 
 * The Original Code is Slage.
 * 
 * The Initial Developer of the Original Code is The SQ7.org project. Portions
 * created by the Initial Developer are Copyright (C) 2005 the Initial
 * Developer. All Rights Reserved.
 * 
 * Contributor(s): Matt Holden (Matt@sq7.org) Travis Savo (Travis@sq7.org)
 * Robert Wenner (Robert@sq7.org) Jared Cope (Jared@sq7.org) Colin Davis
 * (Colin@sq7.org)
 * 
 * Alternatively, the contents of this file may be used under the terms of
 * either the GNU General Public License Version 2 or later (the "GPL"), or the
 * GNU Lesser General Public License Version 2.1 or later (the "LGPL"), in which
 * case the provisions of the GPL or the LGPL are applicable instead of those
 * above. If you wish to allow use of your version of this file only under the
 * terms of either the GPL or the LGPL, and not to allow others to use your
 * version of this file under the terms of the MPL, indicate your decision by
 * deleting the provisions above and replace them with the notice and other
 * provisions required by the GPL or the LGPL. If you do not delete the
 * provisions above, a recipient may use your version of this file under the
 * terms of any one of the MPL, the GPL or the LGPL.
 * 
 ******************************************************************************/
