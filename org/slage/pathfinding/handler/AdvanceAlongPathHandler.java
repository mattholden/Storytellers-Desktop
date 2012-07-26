/*
 * Created on Oct 27, 2005
 *
 */
package org.slage.pathfinding.handler;

import org.slage.Slage;
import org.slage.SlageObject;
import org.slage.framework.Point3D;
import org.slage.framework.scheduler.RecurringEvent;
import org.slage.handlers.Handler;
import org.slage.pathfinding.Path;

public class AdvanceAlongPathHandler
		extends Handler<SlageObject, SlageObject, SlageObject> {

	Velocity velocity = null;
	RecurringEvent event = null;

	public AdvanceAlongPathHandler(RecurringEvent anEvent) {
		event = anEvent;
	}

	public AdvanceAlongPathHandler(SlageObject aSlageObject) {
		super(aSlageObject);
	}

	@Override
	protected void fire() {
		SlageObject target = getTarget();
		Path path = target.getPath();
		if (getTarget().getPath() == null) {
			return;
		}
		if (velocity != null) {
			velocity = new SpeedLimitedVelocity(getTarget().getPosition().x, getTarget().getPosition().y, getTarget().getPosition().y, getTarget().getPath().current().x, getTarget().getPath().current().y, getTarget().getPath().current().y, 10, 10, 10);
		}
		if (getTarget().getPosition().x == path.current().x && target.getPosition().y == path.current().y) {
			velocity = null;
			if (!path.hasNext()) {
				target.setPath(null);
				Slage.getCurrentGame().removeEvent(event);
			} else {
				path.next();
				fire();
			}
			return;
		}
		velocity.advance();
		target.setPosition(new Point3D(velocity.getCurrentX(), velocity.getCurrentY(), velocity.getCurrentZ()));
	}

}
