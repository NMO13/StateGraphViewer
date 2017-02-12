package at.jku.ssw.zesttree.widgets;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.zest.core.viewers.GraphViewer;
import org.eclipse.zest.core.widgets.Graph;
import org.eclipse.zest.core.widgets.GraphConnection;
import org.eclipse.zest.core.widgets.GraphNode;

import at.jku.ssw.zesttree.VisualTreeViewer;
import at.jku.ssw.zesttree.scaling.ScaleShape;

public class LayeredTreeViewerBase extends GraphViewer {

	private boolean horizontal;

	public LayeredTreeViewerBase(Composite composite, int style) {

		this(composite, style, false);
	}

	public LayeredTreeViewerBase(Composite composite, int style,
			boolean horizontal) {

		super(composite, style);
		this.horizontal = horizontal;
	}

	protected void init(Composite composite, int style) {

		this.graph = new AdaptedGraph(composite, style);
		ScaleShape scale = ((AdaptedGraph) graph).getScaleShape();

		hookControl(graph);
	}

	public GraphNode addGraphModelNode(Object element) {

		GraphNode node = this.getGraphModelNode(element);
		if(node == null) {
			if(horizontal) {
				node = new ClickableHorizontalGraphNode((Graph) getControl(),
						SWT.NONE);
			} else {
				node = new ClickableGraphNode((Graph) getControl(), SWT.NONE);
			}
			this.nodesMap.put(element, node);
			node.setData(element);
		}
		return node;
	}

	public GraphConnection addGraphModelConnection(Object element,
			GraphNode source, GraphNode target) {

		GraphConnection connection = this.getGraphModelConnection(element);
		if(connection == null) {

			if(horizontal) {
				connection = new ManhattanHorizontalGraphConnection(
						(Graph) getControl(), SWT.NONE, source, target);
			} else {
				connection = new ManhattanGraphConnection((Graph) getControl(),
						SWT.NONE, source, target);
			}
			this.connectionsMap.put(element, connection);
			connection.setData(element);
		}
		return connection;
	}

	/**
	 * TODO: only for debugging means!<br>
	 * see {@link VisualTreeViewer} for further detail
	 */
	public void toggleHorizontal() {

		horizontal = !horizontal;
//		refresh();
		internalRefresh(getInput());
	}
}
