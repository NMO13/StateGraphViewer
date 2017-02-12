package at.jku.ssw.zesttree.scaling;

import at.jku.ssw.zesttree.widgets.AdaptedGraph;
import at.jku.ssw.zesttree.widgets.ClickableGraphLabel;
import at.jku.ssw.zesttree.widgets.ClickableGraphNode;

public class DefaultNodeScaler extends AbstractNodeScaler {

	private int lastMaxWidth;

	public DefaultNodeScaler(AdaptedGraph graph) {

		super(graph);
		lastMaxWidth = 0;
	}

	@Override
	public void resizeNodes(int length) {

		int maxWidth = 0;
		for(Object o : graph.getNodes()) {
			ClickableGraphNode n = (ClickableGraphNode) o;
			ClickableGraphLabel l = (ClickableGraphLabel) n
					.getClickableFigure();
			l.setTextLength(length);
			int width = l.getSize().width;
			if(width > maxWidth) {
				maxWidth = width;
			}
		}
		if(maxWidth != lastMaxWidth) {
			for(Object o : graph.getNodes()) {
				ClickableGraphNode n = (ClickableGraphNode) o;
				n.setSize(maxWidth, n.getSize().height);
			}
			graph.applyLayout();
		}
		lastMaxWidth = maxWidth;
	}

}
