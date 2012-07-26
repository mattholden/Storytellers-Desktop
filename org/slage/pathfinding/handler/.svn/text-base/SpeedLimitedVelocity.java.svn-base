/*
 * Created on Jul 23, 2003 by Kevlar
 *
 */
package org.slage.pathfinding.handler;

/**
 * @author Kevlar
 * 
 * A velocity who's maximum speed is limited to so many pixles per second. It'll
 * move in a straight line to the destination and stop, never exceeding the
 * maximum speed in pixles per second on any of it's axis'. This is the basis
 * for doing charcater movement with; one will do for paths where there's no
 * obstacles in the way, and a series of these will be needed to 'path around'
 * objects
 * 
 * @version $Revision: 1.1 $
 */
public class SpeedLimitedVelocity
		extends TimedVelocity implements Velocity {

	/**
	 * Field ticksPerSecond
	 */
	private static final double ticksPerSecond = new Double((1000 / TimedVelocity.MILLISECONDS_PER_TICK)).doubleValue();
	private static final long millisPerTick = TimedVelocity.MILLISECONDS_PER_TICK;

	/**
	 * A velocity to move in a straight line to a specific destination without
	 * exceeing a speed limit in Pixles per second.
	 * 
	 * @param aStartX The starting X position
	 * @param aStartY The starting Y position
	 * @param aStartZ The starting Z position
	 * @param anEndX The destination X position
	 * @param anEndY The destination Y position
	 * @param anEndZ The destination Z position
	 * @param aXLimit The speed limit on the X axis in pixles per second (1.0 = 1
	 *        pixle per second)
	 * @param aYLimit The speed limit on the Y axis in pixles per second (1.0 = 1
	 *        pixle per second)
	 * @param aZLimit The speed limit on the Z axis in pixles per second (1.0 = 1
	 *        pixle per second)
	 */
	public SpeedLimitedVelocity(int aStartX, int aStartY, int aStartZ, int anEndX, int anEndY, int anEndZ, double aXLimit, double aYLimit, double aZLimit) {
		super(aStartX, aStartY, aStartZ, anEndX, anEndY, anEndZ, new Double(Math.max(Math.max(aXLimit == 0.0 ? 0.0 : Math.abs(((anEndX - aStartX) / (aXLimit / ticksPerSecond) * millisPerTick)), aYLimit == 0.0 ? 0.0 : Math
				.abs(((anEndY - aStartY) / (aYLimit / ticksPerSecond) * millisPerTick))), aZLimit == 0.0 ? 0.0 : Math.abs(((anEndZ - aStartZ) / (aZLimit / ticksPerSecond) * millisPerTick)))).longValue());

	}
}
