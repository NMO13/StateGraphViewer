package at.jku.ssw.stategraph.providers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.zest.core.viewers.IGraphContentProvider;
import org.eclipse.zest.core.viewers.internal.AbstractStructuredGraphViewer;
import org.eclipse.zest.core.viewers.internal.AbstractStylingModelFactory;
import org.eclipse.zest.core.widgets.Graph;
import org.eclipse.zest.core.widgets.GraphConnection;
import org.eclipse.zest.core.widgets.GraphNode;

import at.jku.ssw.stategraph.testmodel.StateModelNode;

public class StateGraphModelFactory extends AbstractStylingModelFactory {
	AbstractStructuredGraphViewer viewer = null;

	public StateGraphModelFactory(AbstractStructuredGraphViewer viewer) {
		super(viewer);
		this.viewer = viewer;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ca.uvic.cs.zest.internal.graphmodel.IGraphModelFactory#createModel()
	 */
	public Graph createGraphModel(Graph model) {
		doBuildGraph(model);
		return model;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.zest.core.internal.graphmodel.AbstractStylingModelFactory#doBuildGraph(org.eclipse.zest.core.internal.graphmodel.GraphModel)
	 */
	protected void doBuildGraph(Graph model) {
		super.doBuildGraph(model);
		// make the model have the same styles as the viewer
		Object rels[] = getContentProvider().getElements(getViewer().getInput());
		if (rels != null) {
			// If rels returns null then just continue
			// @tag zest(bug(134928(fix))) : An empty graph causes an NPE
			List<StateModelNode> nodeList = new ArrayList<StateModelNode>();
			for (int i = 0; i < rels.length; i++) {
				// Check the filter on the source
				Object source = getCastedContent().getSource(rels[i]);
				source = filterElement(getViewer().getInput(), source) ? null : source;

				// Check hte filter on the dest
				Object dest = getCastedContent().getDestination(rels[i]);
				dest = filterElement(getViewer().getInput(), dest) ? null : dest;
				
				// save source and destination to list if it does not already contains the nodes
				if(!nodeList.contains(source) && source instanceof StateModelNode)
					nodeList.add((StateModelNode)source);
				if(!nodeList.contains(dest) && dest instanceof StateModelNode)
					nodeList.add((StateModelNode)dest);
				
				if (source == null) {
					// just create the node for the destination
					if (dest != null) {
						createNode(model, dest);
					}
					continue;
				} else if (dest == null) {
					// just create the node for the source
					if (source != null) {
						createNode(model, source);
					}
					continue;
				}
			
				// If any of the source, dest is null or the edge is filtered,
				// don't create the graph.
				if (source != null && dest != null && !filterElement(getViewer().getInput(), rels[i])) {
					GraphNode sn = viewer.getGraphModelNode(source);
					GraphNode dn = viewer.getGraphModelNode(dest);
					if (sn == null) {
						sn = createNode(model, source);
					}
					if (dn == null) {
						dn = createNode(model, dest);
					}
				}
			}
			
			// build connections
			for (StateModelNode n : nodeList) {
				for(int j = 0; j < n.getTargets().length; j++) {
					Relation rel = new Relation(n, n.getTargets()[j]);
					// set name of transition for tooltip
					rel.setTransitionName(n.getTransitionName(j));
					createConnection(model, rel, n, n.getTargets()[j]);
			}
			}
		}

	}
	
	@Override
	public GraphConnection createConnection(Graph graph, Object element, Object source, Object dest) {
		if (source == null || dest == null) {
			return null;
		}
		GraphConnection oldConnection = viewer.getGraphModelConnection(element);
		GraphNode sn = viewer.getGraphModelNode(source);
		GraphNode dn = viewer.getGraphModelNode(dest);
		if (oldConnection != null) {
			if (sn != oldConnection.getSource() || dn != oldConnection.getDestination()) {
				viewer.removeGraphModelConnection(oldConnection);
			} else {
				styleItem(oldConnection);
				return oldConnection;
			}
		}
		GraphConnection c = viewer.addGraphModelConnection(element, sn, dn);
		styleItem(c);
		return null;
	}

	private IGraphContentProvider getCastedContent() {
		return (IGraphContentProvider) getContentProvider();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.zest.core.internal.graphmodel.IStylingGraphModelFactory#refresh(org.eclipse.zest.core.internal.graphmodel.GraphModel,
	 *      java.lang.Object)
	 */
	public void refresh(Graph graph, Object element) {
		refresh(graph, element, false);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.zest.core.internal.graphmodel.IStylingGraphModelFactory#refresh(org.eclipse.zest.core.internal.graphmodel.GraphModel,
	 *      java.lang.Object, boolean)
	 */
	public void refresh(Graph graph, Object element, boolean updateLabels) {
		GraphConnection conn = viewer.getGraphModelConnection(element);
		if (conn == null) {
			// did the user send us a node? Check all of the connections on the
			// node.
			GraphNode node = viewer.getGraphModelNode(element);
			if (node != null) {
				List connections = node.getSourceConnections();
				for (Iterator it = connections.iterator(); it.hasNext();) {
					GraphConnection c = (GraphConnection) it.next();
					refresh(graph, c.getExternalConnection(), updateLabels);
				}
				connections = node.getTargetConnections();
				for (Iterator it = connections.iterator(); it.hasNext();) {
					GraphConnection c = (GraphConnection) it.next();
					refresh(graph, c.getExternalConnection(), updateLabels);
				}
			}
			return;
		}
		Object oldSource = conn.getSource().getData();
		Object oldDest = conn.getDestination().getData();
		Object newSource = getCastedContent().getSource(element);
		Object newDest = getCastedContent().getDestination(element);
		if (!(oldSource.equals(newSource) && oldDest.equals(newDest))) {
			GraphNode internalSource = viewer.getGraphModelNode(newSource);
			GraphNode internalDest = viewer.getGraphModelNode(newDest);
			if (internalSource == null) {
				internalSource = createNode(graph, newSource);
			} else if (updateLabels) {
				styleItem(internalSource);
			}
			if (internalDest == null) {
				internalDest = createNode(graph, newDest);
			} else if (updateLabels) {
				styleItem(internalDest);
			}

			// @tag TODO: Remove these lines
			// conn.disconnect();
			// conn.reconnect(internalSource, internalDest);
			if (updateLabels) {
				styleItem(conn);
			}
		}

	}
}
