package at.jku.ssw.stategraph.widgets;

import org.eclipse.draw2d.ChopboxAnchor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;

public class ManhattanLeftSideAnchor extends ManhattanAnchor {

	ManhattanLeftSideAnchor(IFigure owner) {
		super(owner);
	}
	
	public ManhattanLeftSideAnchor(IFigure owner, IFigure othersFigure) {
		super(owner, othersFigure);
	}

	protected Point getTranslationPoint(Rectangle bounds, boolean isSource) {
		double shiftX = 0.0;
		double shiftY = 0.0;
		if(isSource)
			shiftY = bounds.height / 2;
		else
			shiftX = -bounds.width / 2;
		
		return new Point(shiftX, shiftY);
	}
}
