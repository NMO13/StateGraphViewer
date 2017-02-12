package at.jku.ssw.zesttree.scrolling;

import org.eclipse.draw2d.RangeModel;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.zest.core.widgets.Graph;
import org.eclipse.zest.core.widgets.GraphNode;

public abstract class AbstractScrollStrategy implements IScrollStrategy {

	protected Graph graph;
	protected RangeModel hM, vM;
	protected Point actPos;

	public AbstractScrollStrategy(Graph graph) {

		this.graph = graph;
		hM = graph.getViewport().getHorizontalRangeModel();
		vM = graph.getViewport().getVerticalRangeModel();
		actPos = new Point(0, 0);
	}

	public void performScroll(GraphNode cur, ScrollAction action) {

		actPos = new Point(hM.getValue(), vM.getValue());
		Point scrollPos = new Point(actPos);
		switch(action) {
		case CLICK:
			scrollPos = scrollAtClick(cur);
			break;
		case EXPANSION:
			scrollPos = scrollAtExpansion(cur);
			break;
		case SHIFT:
			scrollPos = scrollAtShift(cur);
			break;
		}
		scrollGraphTo(scrollPos);
	}

	protected abstract Point scrollAtClick(GraphNode cur);

	protected abstract Point scrollAtExpansion(GraphNode cur);

	protected abstract Point scrollAtShift(GraphNode cur);

	private void scrollGraphTo(Point scrollPos) {

		if(!actPos.equals(scrollPos)) {
			graph.scrollSmoothTo(scrollPos.x, scrollPos.y);
			actPos = scrollPos;
		}
	}
}
