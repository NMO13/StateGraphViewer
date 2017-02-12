package at.jku.ssw.stategraph.widgets;

import listeners.StateNodeSelectionListener;

import org.eclipse.core.runtime.ListenerList;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.zest.core.widgets.GraphNode;
import org.eclipse.zest.core.widgets.IContainer;
import org.eclipse.zest.core.widgets.ZestStyles;

public class StateGraphNode extends GraphNode {

	private static boolean isLeave = false;
	private ListenerList list = new ListenerList();
	public StateGraphNode(IContainer graphModel, int style) {
		super(graphModel, style);
	}
	
	public static void isLeave(boolean l) {
		isLeave = l;
	}
	
	protected IFigure createFigureForModel() {
		GraphNode node = this;
		boolean cacheLabel = (this).cacheLabel();
		StateGraphLabel label = new StateGraphLabel(node.getText(), node.getImage(), cacheLabel, isLeave);
		label.setFont(this.getFont());
		if (checkStyle(ZestStyles.NODES_HIDE_TEXT)) {
			label.setText("");
		}
		updateFigureForModel(label);
		return label;
	}
	
	protected void updateFigureForModel(IFigure currentFigure) {
		if (currentFigure == null) {
			return;
		}

		if (!(currentFigure instanceof StateGraphLabel)) {
			return;
		}
		StateGraphLabel figure = (StateGraphLabel) currentFigure;
		IFigure toolTip;

		if (!checkStyle(ZestStyles.NODES_HIDE_TEXT)) {
			figure.setText(this.getText());
		}
		figure.setIcon(getImage());


		if (highlighted == HIGHLIGHT_ON) {
			figure.highlight();
		}
		// @tag ADJACENT : Removed highlight adjacent
		/*
		else if (highlighted == HIGHLIGHT_ADJACENT) {
			figure.setForegroundColor(getForegroundColor());
			figure.setBackgroundColor(getHighlightAdjacentColor());
		}
		*/
		else {
			figure.unhighlight();
			figure.setForegroundColor(getForegroundColor());
			figure.setBackgroundColor(getBackgroundColor());
		}

		figure.setFont(getFont());

		/*
		Dimension d = figure.getSize();
		if (d.height > 0 && d.width > 0) {
			this.size = d.getCopy();
			//setSize(d.width, d.height);
		}
		*/

		if (this.getTooltip() == null) {
			toolTip = new Label();
			((Label) toolTip).setText(getText());
		} else {
			toolTip = this.getTooltip();
		}
		figure.setToolTip(toolTip);

		//figure.addLayoutListener(LayoutAnimator.getDefault());
		refreshLocation();
	}
	
	@Override
	public void highlight() {
		super.highlight();
	}
}
