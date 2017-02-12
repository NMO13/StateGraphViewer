package at.jku.ssw.stategraph.widgets;

import org.eclipse.draw2d.AbstractRouter;
import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;

public class StateGraphConnectionRouter extends AbstractRouter{

	@Override
	public void route(Connection conn) {
		Point startPoint = getStartPoint(conn);
	//	conn.translateToRelative(startPoint);
		Point endPoint = getEndPoint(conn);
	//	conn.translateToRelative(endPoint);
		int points[] = {startPoint.x, startPoint.y, conn.getPoints().getPoint(1).x, startPoint.y, conn.getPoints().getPoint(2).x, endPoint.y, endPoint.x, endPoint.y};
		conn.setPoints(new PointList(points));
	}

}
