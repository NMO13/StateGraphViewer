package at.jku.ssw.zesttree.scaling;

import at.jku.ssw.zesttree.listeners.ScaleValueChangeEvent;
import at.jku.ssw.zesttree.listeners.ScaleValueChangedAdapter;
import at.jku.ssw.zesttree.widgets.AdaptedGraph;

public abstract class AbstractNodeScaler implements INodeScaler {

	protected ScaleShape scale;
	protected AdaptedGraph graph;

	public AbstractNodeScaler(AdaptedGraph graph) {

		this.graph = graph;
		this.scale = this.graph.getScaleShape();
		scale.addScaleValueChangedListener(new ScaleValueChangedAdapter() {

			@Override
			public void scaleValueChanged(ScaleValueChangeEvent e) {

				int val = e.getValue();
				resizeNodes(val);
			}
		});
	}

	@Override
	public void defaultResizeNodes() {

		resizeNodes(scale.getDefaultValue());
	}

	@Override
	public abstract void resizeNodes(int length);

}
