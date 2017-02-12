package at.jku.ssw.stategraph.widgets;

import org.eclipse.draw2d.ChopboxAnchor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;

public class RightSideAnchor extends ChopboxAnchor {
	private double anchorPosY = 0;
	public RightSideAnchor(IFigure owner, double anchorPosY) {
		super(owner);
		this.anchorPosY = anchorPosY;
	}

	public Point getLocation(Point reference) {
		Rectangle r = this.getBox();
		
		return (new Point(r.x+r.width, r.y + anchorPosY/*+ r.height/2*/));		
	}
}