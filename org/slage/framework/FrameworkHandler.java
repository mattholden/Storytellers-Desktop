/*
 * Fireable.java
 *
 * Created on October 24, 2005, 10:00 AM
 */

package org.slage.framework;

import java.awt.event.ActionEvent;
import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.AbstractAction;

import org.slage.conditionals.Condition;
import org.slage.conditionals.ConditionGroup;
import org.slage.conditionals.ConditionalList;

/**
 * Base class for Handlers and other such fire-able things.
 * 
 * @author Jaeden
 */
public abstract class FrameworkHandler implements Serializable, ConditionalList, Runnable {
		
    /**
	 * The whole point of this class - the method to fire
	 */
	protected abstract void fire();

	/**
	 * Wrapper around the fire() method. Override this method to provide
	 * additional functionality that should occur every time fire() is called. As
	 * fire() itself is protected, this method should be the gateway to call
	 * fire().
	 */
	public void execute() {
		fire();
	}

	/** A list of verbs that trigger this event */
	protected ArrayList<String> listVerbs = new ArrayList<String>();

	/**
	 * Adds the supplied <i>verb</i> to the list of verbs that will trigger this
	 * handler to fire.
	 * 
	 * @param verb verb to add
	 */
	public void addVerb(String verb) {
		listVerbs.add(verb.toUpperCase());
	}

	/**
	 * Removes the supplied <i>verb</i> from the list of verbs that will trigger
	 * this handler to fire.
	 * 
	 * @param verb verb to remove
	 */
	public void removeVerb(String verb) {
		listVerbs.remove(verb.toUpperCase());
	}

	/**
	 * Checks if this handler will fire as a result of the supplied <i>verb</i>.
	 * 
	 * @param verb the verb to check
	 * @return true if the verb will fire this handler, false otherwise
	 */
	public boolean hasVerb(String verb) {
		if (listVerbs.size() == 0)
			return false;
		for (String str : listVerbs) {
			if (str.equalsIgnoreCase(verb)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Accessor for verb list (TODO: Try to remove)
	 * 
	 * @return listVerbs
	 */
	public ArrayList<String> getVerbs() {
		return listVerbs;
	}


	/**
	 * Allows this handler class to be used as a Runnable object in a new thread.
	 * This run method will call the <i>execute()</i> method.
	 */
	public void run() {
		execute();
	}

	/** Creates a new instance of FrameworkHandler */
	public FrameworkHandler() {
		//
	}

	/** Store the Conditions on which this handler should fire. */
	private ConditionGroup conditions = new ConditionGroup();

	/**
	 * Add a Condition
	 * 
	 * @param cond Condition to add
	 */
	public void addCondition(Condition cond) {
		conditions.addCondition(cond);

	}

	/**
	 * Remove a Condition
	 * 
	 * @param cond Condition to remove
	 */
	public void removeCondition(Condition cond) {
		conditions.removeCondition(cond);
	}

	/** Clear the Condition list */
	public void clearConditions() {
		conditions.clearConditions();
	}

	/**
	 * Get the count of conditions
	 * 
	 * @return listConditions.size()
	 */
	public int getConditionCount() {
		return conditions.getConditionCount();
	}

	/**
	 * Set the acceptance pattern.
	 * 
	 * There are three acceptance patterns, defined with integer constants in this
	 * class:
	 * 
	 * <li> ALL: All conditions must return 'true'.
	 * <li> ANY: Any one condition must return 'true'.
	 * <li> NONE: All conditions must return 'false'.
	 * 
	 * @param iAccept the constant corresponding to the new acceptance pattern.
	 * @throws IllegalArgumentException if iAccept is not one of the three
	 *         constants above.
	 */
	public void setAcceptance(int iAccept) throws IllegalArgumentException {
		// TODO comment back in when conditions cleaned up
		// conditions.setAcceptance(iAccept);
	}

	/**
	 * Get the acceptance pattern.
	 * 
	 * There are three acceptance patterns, defined with integer constants in this
	 * class:
	 * 
	 * <li> ALL: All conditions must return 'true'.
	 * <li> ANY: Any one condition must return 'true'.
	 * <li> NONE: All conditions must return 'false'.
	 * 
	 * @return the constant corresponding to the new acceptance pattern.
	 */
	public int getAcceptance() {
		return conditions.getAcceptance();
	}

	/**
	 * Check if the contained Conditions meet the acceptance criteria.
	 * 
	 * @return 'true' if the conditions are met.
	 */
	public boolean check() {
		return conditions.check();
	}
}
