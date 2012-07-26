/*
 * Created on Jul 22, 2003
 */
package org.slage.pathfinding.handler;

/**
 * @author Kevlar
 * 
 * An Interface for Velocities so an object dosn't have to care what kind of
 * velocity it's using for it's movement.
 * 
 * The idea behind a Velocity is it encapsulates the logic needed to move
 * something smoothly. For example: You could give something intertia (with
 * ConstantVelocity) or ensure that something never excees a certian speed (with
 * SpeedLimitedVelocity) or move something a set distance over a set period of
 * time (TimedVelocity)
 * 
 * All of these Velocities do it in a 'milliseconds per tick' agnostic way,
 * ensuring that given the same numbers it always takes the same amount of time
 * regardless of the pause between ticks.
 * 
 * It's also permissible to have a tick dependant velocity instead of a time
 * dependant one, but no examples are given here, as it's trival to do.
 * 
 * It's expected you'll create the Velocity and either: - Call advance(), set
 * the new position, and repeat as necessary - Construct it with a sprite and
 * call move() each tick. It's expected that move() calls advance()
 * @version $Revision: 1.1 $
 */
public interface Velocity {

	/**
	 * Method advance
	 */
	public abstract void advance();

	/**
	 * Gets the current X for the state of this Velocity
	 * 
	 * @return the current X for this Velocity
	 */
	public abstract int getCurrentX();

	/**
	 * Gets the current Y for the state of this Velocity
	 * 
	 * @return the current Y for this Velocity
	 */
	public abstract int getCurrentY();

	/**
	 * Gets the current Z for the state of this Velocity
	 * 
	 * @return the current Z for this Velocity
	 */
	public abstract int getCurrentZ();

	/**
	 * Method isDone
	 * 
	 * @return boolean
	 */
	public abstract boolean isDone();
}
