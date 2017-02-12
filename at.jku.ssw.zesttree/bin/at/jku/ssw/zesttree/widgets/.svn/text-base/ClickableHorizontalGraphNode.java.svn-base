package at.jku.ssw.zesttree.widgets;

import org.eclipse.draw2d.IFigure;
import org.eclipse.zest.core.widgets.IContainer;
import org.eclipse.zest.core.widgets.ZestStyles;

/**
 * Horizontal version of {@link ClickableGraphNode}
 * 
 * @author Kölbleitner Florian
 */
public class ClickableHorizontalGraphNode extends ClickableGraphNode {

	public ClickableHorizontalGraphNode(IContainer graphModel, int style) {

		super(graphModel, style);
	}

	protected IFigure createFigureForModel() {

		ClickableGraphLabel label = new ClickableGraphLabel(this.getText(),
				this.getImage(), this.cacheLabel(), this, true);
		label.setFont(this.getFont());
		if(checkStyle(ZestStyles.NODES_HIDE_TEXT)) {
			label.setText("");
		}
		updateFigureForModel(label);
		return label;
	}
}
