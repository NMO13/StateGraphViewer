package at.jku.ssw.zesttree.widgets;

import org.eclipse.draw2d.ChopboxAnchor;
import org.eclipse.zest.core.widgets.Graph;
import org.eclipse.zest.core.widgets.GraphNode;

/**
 * Horizontal version of {@link ManhattanGraphConnection} using
 * {@link ManhattanHorizontalAnchor}s
 * 
 * @author Kölbleitner Florian
 */
public class ManhattanHorizontalGraphConnection extends
		ManhattanGraphConnection {

	public ManhattanHorizontalGraphConnection(Graph graphModel, int style,
			GraphNode source, GraphNode destination) {

		super(graphModel, style, source, destination);
	}

	protected ChopboxAnchor getSourceAnchor() {

		return new ManhattanHorizontalAnchor(getSource().getNodeFigure(),
				getDestination().getNodeFigure());
	}

	protected ChopboxAnchor getTargetAnchor() {

		return new ManhattanHorizontalAnchor(getDestination().getNodeFigure(),
				getSource().getNodeFigure());
	}
}
