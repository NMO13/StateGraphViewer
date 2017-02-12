package at.jku.ssw.zesttree.widgets;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;

/**
 * ManhattanHorizontalAnchor provides reference Points either at the left or the
 * right of a node
 * 
 * @author Kölbleitner Florian
 */
public class ManhattanHorizontalAnchor extends ManhattanAnchor {

	public ManhattanHorizontalAnchor(IFigure othersFigure) {

		super(othersFigure);
	}

	public ManhattanHorizontalAnchor(IFigure owner, IFigure othersFigure) {

		super(owner, othersFigure);
	}

	protected Point getSourcePoint(Rectangle bounds) {

		return bounds.getLeft();
	}

	protected Point getTargetPoint(Rectangle bounds) {

		return bounds.getRight();
	}

	protected Point getTranslationPoint(Rectangle bounds, boolean isSource) {

		double shift = isSource ? bounds.width / 2 : -bounds.width / 2;
		return new Point(shift, 0);
	}
}
