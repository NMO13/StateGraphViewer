package at.jku.ssw.stategraph.widgets;

import org.eclipse.draw2d.ChopboxAnchor;
import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.Locator;
import org.eclipse.draw2d.ManhattanConnectionRouter;
import org.eclipse.draw2d.MidpointLocator;
import org.eclipse.draw2d.PolylineConnection;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.zest.core.widgets.Graph;
import org.eclipse.zest.core.widgets.GraphConnection;
import org.eclipse.zest.core.widgets.GraphNode;
import org.eclipse.zest.core.widgets.internal.LoopAnchor;
import org.eclipse.zest.core.widgets.internal.PolylineArcConnection;


public class WaterfallGraphConnection extends GraphConnection {

	public WaterfallGraphConnection(Graph graphModel, int style,
			GraphNode source, GraphNode destination) {
		super(graphModel, style, source, destination);
	}

	@Override
	protected Connection createFigure() {

		Connection connectionFigure = null;
		ChopboxAnchor sourceAnchor = null;
		ChopboxAnchor targetAnchor = null;
		this.connectionLabel = new Label();
		Locator labelLocator = null;

		if(getSource() == getDestination()) {
			// If this is a self loop, create a looped arc and put the locator at the top
			// of the connection
			connectionFigure = new PolylineArcConnection();
			sourceAnchor = new LoopAnchor(getSource().getNodeFigure());
			targetAnchor = new LoopAnchor(getDestination().getNodeFigure());
			labelLocator = new MidpointLocator(connectionFigure, 0) {

				protected Point getReferencePoint() {

					Point p = Point.SINGLETON;
					p.x = getConnection().getPoints().getPoint(getIndex()).x;
					p.y = (int) (getConnection().getPoints().getPoint(
							getIndex()).y - (getCurveDepth() * 1.5));
					return p;
				}
			};
		} else {
			connectionFigure = new PolylineConnection();
			sourceAnchor = getSourceAnchorManhattan();//new ChopboxAnchor(getSource().getNodeFigure());
			targetAnchor = getTargetAnchorManhattan();// new ChopboxAnchor(getDestination().getNodeFigure());
			labelLocator = new MidpointLocator(connectionFigure, 0);
		}

		connectionFigure.setSourceAnchor(sourceAnchor);
		connectionFigure.setTargetAnchor(targetAnchor);
		connectionFigure.add(this.connectionLabel, labelLocator);
		connectionFigure.setConnectionRouter(new ManhattanConnectionRouter());
		updateFigure(connectionFigure);
		return connectionFigure;
	}

	protected ChopboxAnchor getSourceAnchor() {

		return new ManhattanLeftSideAnchor(getSource().getNodeFigure());
	}

	protected ChopboxAnchor getTargetAnchor() {

		return new ManhattanLeftSideAnchor(getDestination().getNodeFigure());
	}
	
	protected ChopboxAnchor getSourceAnchorManhattan() {

		return new ManhattanLeftSideAnchor(getSource().getNodeFigure(),
				getDestination().getNodeFigure());
	}

	protected ChopboxAnchor getTargetAnchorManhattan() {

		return new ManhattanLeftSideAnchor(getDestination().getNodeFigure(),
				getSource().getNodeFigure());
	}

}
