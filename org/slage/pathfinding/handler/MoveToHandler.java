/*
 * Created on Oct 27, 2005
 *
 */
package org.slage.pathfinding.handler;

import java.awt.Point;
import java.awt.Polygon;
import java.util.ArrayList;
import java.util.List;

import org.slage.Room;
import org.slage.Slage;
import org.slage.SlageObject;
import org.slage.framework.scheduler.RecurringEvent;
import org.slage.framework.scheduler.ScheduledEvent;
import org.slage.handlers.Handler;
import org.slage.pathfinding.PathFactory;

public class MoveToHandler
		extends Handler<SlageObject, SlageObject, SlageObject> {
	public int x = 0;
	public int y = 0;

	/**
	 * @param object
	 */
	public MoveToHandler(SlageObject object, int anX, int aY) {
		super(object);
		x = anX;
		y = aY;
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void fire() {
		Room r = Slage.getCurrentGame().getRoom();
		List<Polygon> listBounds = new ArrayList<Polygon>();
		for (SlageObject obj : r.getContainedObjects()) {
			if (obj.isSolid())
				listBounds.add(obj.getCollisionBoundary());
		}
		getTarget().setPath(PathFactory.getPath(new Point(getTarget().getPosition().x, getTarget().getPosition().y), new Point(x, y), listBounds));
		AdvanceAlongPathHandler aaph = new AdvanceAlongPathHandler(getTarget());
		aaph.event = new RecurringEvent(aaph, ScheduledEvent.NANOSEC_IN_SEC / 30, 10);
		Slage.getCurrentGame().addEvent(aaph.event);

	}

}
