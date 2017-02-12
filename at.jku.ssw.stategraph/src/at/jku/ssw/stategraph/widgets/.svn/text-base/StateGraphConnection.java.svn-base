package at.jku.ssw.stategraph.widgets;

import java.awt.Color;

import org.eclipse.draw2d.ChopboxAnchor;
import org.eclipse.draw2d.ColorConstants;
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


public class StateGraphConnection extends GraphConnection{

	public StateGraphConnection(Graph graphModel, int style, GraphNode source,
			GraphNode destination) {
		super(graphModel, style, source, destination);
	}
	
	@Override
	protected Connection createFigure() {

		Connection connectionFigure = null;
		ChopboxAnchor sourceAnchor = null;
		ChopboxAnchor targetAnchor = null;
		this.connectionLabel = new Label();
		Locator labelLocator = null;

		
		connectionFigure = new PolylineConnection();
		labelLocator = new MidpointLocator(connectionFigure, 0);
		

//		connectionFigure.setSourceAnchor(sourceAnchor);
//		connectionFigure.setTargetAnchor(targetAnchor);
		connectionFigure.add(this.connectionLabel, labelLocator);
		connectionFigure.setConnectionRouter(new StateGraphConnectionRouter());
		updateFigure(connectionFigure);
		return connectionFigure;
		
//		connectionFigure.setSourceAnchor(sourceAnchor);
//		connectionFigure.setTargetAnchor(targetAnchor);
//		connectionFigure.add(this.connectionLabel, labelLocator);
//		ConnectionRouter r = new BendpointConnectionRouter();
//		connectionFigure.setConnectionRouter(r);
//		ArrayList list = new ArrayList();
//		list.add(new AbsoluteBendpoint(20, 20));
//		r.setConstraint(connectionFigure, list);
//		updateFigure(connectionFigure);
//		return connectionFigure;
	}
	

	
	protected ChopboxAnchor getSourceAnchorManhattan() {

		return new ManhattanAnchor(getSource().getNodeFigure(),
				getDestination().getNodeFigure());
	}

	protected ChopboxAnchor getTargetAnchorManhattan() {

		return new ManhattanAnchor(getDestination().getNodeFigure(),
				getSource().getNodeFigure());
	}

}
