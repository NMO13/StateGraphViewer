package at.jku.ssw.zesttree.scrolling;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.zest.core.widgets.Graph;
import org.eclipse.zest.core.widgets.GraphNode;

public class DefaultScrollStrategy extends AbstractScrollStrategy {

	private final static int SIDE_GAP = 30;

	public DefaultScrollStrategy(Graph graph) {

		super(graph);
	}

	@Override
	protected Point scrollAtClick(GraphNode cur) {

		return AdjustToSideGap(cur, 1);
	}

	@Override
	protected Point scrollAtExpansion(GraphNode cur) {

		return AdjustToSideGap(cur, cur.getSourceConnections().size());
	}

	@Override
	protected Point scrollAtShift(GraphNode cur) {

		return AdjustToSideGap(cur, 1);
	}

	private Point getCenterOfNode(GraphNode node) {

		Point loc = node.getLocation();
		Dimension rec = node.getSize();
		return new Point(loc.x + rec.width / 2, loc.y + rec.height / 2);
	}

	private Point AdjustToSideGap(GraphNode node, int sensibility) {

		Point adjusted = actPos.getCopy();
		Point ctr = getCenterOfNode(node);
		if(ctr.x - hM.getValue() < SIDE_GAP * sensibility
				|| ctr.x - hM.getValue() > hM.getExtent() - SIDE_GAP
						* sensibility) {
			adjusted.x = ctr.x - hM.getExtent() / 2;
		}
		if(ctr.y - vM.getValue() < SIDE_GAP * sensibility
				|| ctr.y - vM.getValue() > vM.getExtent() - SIDE_GAP
						* sensibility) {
			adjusted.y = ctr.y - vM.getExtent() / 2;
		}
		return adjusted;
	}
}
