package at.jku.ssw.stategraph.widgets;

import org.eclipse.draw2d.ChopboxAnchor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;

/**
 * ManhattanAnchor provides reference Points either at the top or the bottom of
 * a node
 * 
 * @author Kölbleitner Florian
 */
public class ManhattanAnchor extends ChopboxAnchor {

	private IFigure othersFigure;

	public ManhattanAnchor(IFigure othersFigure) {

		super();
		this.othersFigure = othersFigure;
	}

	public ManhattanAnchor(IFigure owner, IFigure othersFigure) {

		super(owner);
		this.othersFigure = othersFigure;
	}

	/**
	 * Sets the point to the according location: top or bottom.<BR>
	 * In the special case when two nodes overlap each other, the center is used
	 * to avoid incorrect display
	 * 
	 * @param reference The other anchors reference Point (here: the other nodes
	 *        center)
	 * 
	 * @return the Anchors location
	 */
	public Point getLocation(Point reference) {

		Rectangle bounds = getBox();

		Point top = getSourcePoint(bounds);
		Point bottom = getTargetPoint(bounds);

		boolean isSource = reference.getDistance(bottom) < reference
				.getDistance(top);

		Point shift = new Point(0, 0);
		if(!bounds.intersects(othersFigure.getBounds())) {
			shift = getTranslationPoint(bounds, isSource);
		}
		Point p = getReferencePoint().translate(shift);
		System.out.println(p);
		return getReferencePoint().translate(shift);
	}

	protected Point getSourcePoint(Rectangle bounds) {

		return bounds.getTop();
	}

	protected Point getTargetPoint(Rectangle bounds) {

		return bounds.getBottom();
	}

	protected Point getTranslationPoint(Rectangle bounds, boolean isSource) {

		double shift = isSource ? bounds.height / 2 : -bounds.height / 2;
		return new Point(0, shift);
	}
}
