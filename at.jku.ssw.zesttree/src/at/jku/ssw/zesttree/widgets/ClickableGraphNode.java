package at.jku.ssw.zesttree.widgets;

import org.eclipse.draw2d.IFigure;
import org.eclipse.zest.core.widgets.GraphNode;
import org.eclipse.zest.core.widgets.IContainer;
import org.eclipse.zest.core.widgets.ZestStyles;

import at.jku.ssw.zesttree.listeners.TogglerClickedListener;

/**
 * Extension of the standard GraphNode providing a {@link ClickableGraphLabel}
 * 
 * @author Kölbleitner Florian
 */
public class ClickableGraphNode extends GraphNode {

	public ClickableGraphNode(IContainer graphModel, int style) {

		super(graphModel, style);
	}

	protected IFigure createFigureForModel() {

		ClickableGraphLabel label = new ClickableGraphLabel(this.getText(),
				this.getImage(), this.cacheLabel(), this, false);
		label.setFont(this.getFont());
		if(checkStyle(ZestStyles.NODES_HIDE_TEXT)) {
			label.setText("");
		}
		updateFigureForModel(label);
		return label;
	}

	protected void refreshLocation() {

		super.refreshLocation();
		if(nodeFigure instanceof ClickableGraphLabel) {
			((ClickableGraphLabel) nodeFigure).updateExpander();
		}
	}

	public IFigure getClickableFigure() {

		return nodeFigure;
	}

	/**
	 * Sets this nodes state to either expanded or collapsed
	 * 
	 * @param state
	 */
	public void setExpandedState(boolean state) {

		((ClickableGraphLabel) nodeFigure).setExpandedState(state);
	}

	public void addTogglerClickedListener(TogglerClickedListener l) {

		((ClickableGraphLabel) nodeFigure).addTogglerClickedListener(l);
	}
}
