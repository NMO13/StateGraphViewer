package at.jku.ssw.stategraph;

import listeners.StateNodeSelectionListener;

import org.eclipse.draw2d.ScalableFreeformLayeredPane;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.zest.core.viewers.GraphViewer;
import org.eclipse.zest.core.viewers.IGraphContentProvider;
import org.eclipse.zest.core.viewers.IGraphEntityContentProvider;
import org.eclipse.zest.core.viewers.IGraphEntityRelationshipContentProvider;
import org.eclipse.zest.core.viewers.internal.GraphModelEntityFactory;
import org.eclipse.zest.core.viewers.internal.GraphModelEntityRelationshipFactory;
import org.eclipse.zest.core.viewers.internal.GraphModelFactory;
import org.eclipse.zest.core.viewers.internal.IStylingGraphModelFactory;
import org.eclipse.zest.core.widgets.Graph;
import org.eclipse.zest.core.widgets.GraphConnection;
import org.eclipse.zest.core.widgets.GraphNode;
import org.eclipse.zest.core.widgets.internal.ZestRootLayer;
import org.eclipse.zest.layouts.LayoutStyles;

import at.jku.ssw.stategraph.layoutalgorithms.VerticalStateGraphLayoutAlgorithm;
import at.jku.ssw.stategraph.layoutalgorithms.WaterfallLayoutAlgorithm;
import at.jku.ssw.stategraph.providers.StateGraphModelFactory;
import at.jku.ssw.stategraph.testmodel.StateModelNode;
import at.jku.ssw.stategraph.widgets.StateGraphConnection;
import at.jku.ssw.stategraph.widgets.StateGraphNode;

public class StateGraphViewer extends GraphViewer{

	public StateGraphViewer(Composite composite, int style) {
		super(composite, style);
	}
	
	public StateGraphViewer(Composite composite, int style, Button b1, Button b2) {
		super(composite, style);
		b1.addListener(SWT.Selection, e1);
		b2.addListener(SWT.Selection, e2);
		// TODO Auto-generated constructor stub
	}
	
	Listener e1 = new Listener() {

		@Override
		public void handleEvent(Event arg0) {
			/*System.out.println("button pressed");
			Graph c = (Graph) getControl();
			List n =  c.getNodes();
			GraphNode s = (GraphNode) n.get(0);
			s.setLocation(50, 50);*/
			StateGraphViewer.this.setLayoutAlgorithm(new VerticalStateGraphLayoutAlgorithm(LayoutStyles.NONE));
			applyLayout();
		}
	};
	
	Listener e2 = new Listener() {

		@Override
		public void handleEvent(Event arg0) {
			/*System.out.println("button pressed");
			Graph c = (Graph) getControl();
			List n =  c.getNodes();
			GraphNode s = (GraphNode) n.get(0);
			s.setLocation(50, 50);*/
			StateGraphViewer.this.setLayoutAlgorithm(new WaterfallLayoutAlgorithm(LayoutStyles.NONE));
			applyLayout();	
		}
	};
	
	@Override
	public GraphConnection addGraphModelConnection(Object element,
			GraphNode source, GraphNode target) {
		// every node is exactly one time a source node
		// draw all connections from this source node to all other target nodes
		Object s = ((StateGraphNode)source.getLayoutEntity().getGraphData()).getData();
		Object t = ((StateGraphNode)target.getLayoutEntity().getGraphData()).getData();
		 GraphConnection connection = this.getGraphModelConnection(element);
		 if(connection == null) {
		 // connection = new WaterfallGraphConnection(
		 // (Graph) getControl(), SWT.NONE, source, target);
		 // to delete wrong connection lines at the beginning of the animation
		 connection = new StateGraphConnection((Graph) getControl(), SWT.NONE, source, target);
		 int po[] = {0, 0, 0, 0};
		 PointList p = new PointList(po);
		 connection.getConnectionFigure().setPoints(p);
		 
//		 ScalableFreeformLayeredPane o = (ScalableFreeformLayeredPane) ((Graph) getControl()).getContents();
//		 ZestRootLayer z = (ZestRootLayer) o.getChildren().get(0);
//		 z.EDGES_ON_TOP = true;
		 
		 this.connectionsMap.put(element, connection);
		 connection.setData(element);
		 }
		//return null;
		 return connection;
	}
	
	@Override
	public GraphNode addGraphModelNode(Object element) {
		GraphNode node = this.getGraphModelNode(element);
		if(node == null) {
			boolean l = ((StateModelNode)element).getChildren().length == 0 ? true : false;
			StateGraphNode.isLeave(l);
			node = new StateGraphNode((Graph) getControl(), SWT.NONE);
			node.getLayoutEntity().setSizeInLayout(0,  0);
			this.nodesMap.put(element, node);
			node.setData(element);
		}
		return node;
	}
	
	@Override
	protected IStylingGraphModelFactory getFactory() {
		return new StateGraphModelFactory(this);
	}
	
	
}
