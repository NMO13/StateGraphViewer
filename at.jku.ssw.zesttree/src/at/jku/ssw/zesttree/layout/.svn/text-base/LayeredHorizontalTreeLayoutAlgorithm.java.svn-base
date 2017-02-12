package at.jku.ssw.zesttree.layout;

import org.eclipse.zest.layouts.LayoutStyles;
import org.eclipse.zest.layouts.dataStructures.InternalNode;
import org.eclipse.zest.layouts.dataStructures.InternalRelationship;

/**
 * The horizontal version of {@link LayeredTreeLayoutAlgorithm}
 * 
 * @author Kölbleitner Florian
 * 
 */
public class LayeredHorizontalTreeLayoutAlgorithm extends
		LayeredTreeLayoutAlgorithm {

	public LayeredHorizontalTreeLayoutAlgorithm() {

		this(LayoutStyles.NONE);
	}

	public LayeredHorizontalTreeLayoutAlgorithm(int styles) {

		super(styles);
	}

	public LayeredHorizontalTreeLayoutAlgorithm(int styles, boolean leafingStyle) {

		super(styles, leafingStyle);
	}

	protected void preLayoutAlgorithm(InternalNode[] entitiesToLayout,
			InternalRelationship[] relationshipsToConsider, double x, double y,
			double width, double height) {

		// NOTE: width and height are switched here when calling super method
		super.preLayoutAlgorithm(entitiesToLayout, relationshipsToConsider, x,
				y, height, width);
	}

	protected void postLayoutAlgorithm(InternalNode[] entitiesToLayout,
			InternalRelationship[] relationshipsToConsider) {

		// swap x->y and width->height
		for(int i = 0; i < entitiesToLayout.length; i++) {
			InternalNode entity = entitiesToLayout[i];
			entity.setInternalLocation(entity.getInternalY(), entity
					.getInternalX());
			entity.setInternalSize(entity.getInternalWidth(), entity
					.getInternalHeight());
		}
		super.postLayoutAlgorithm(entitiesToLayout, relationshipsToConsider);
	}

	protected double getLayoutWidth(InternalNode n) {

		return n.getHeightInLayout();
	}

	protected double getLayoutHeight(InternalNode n) {

		return n.getWidthInLayout();
	}

	protected boolean isValidConfiguration(boolean asynchronous,
			boolean continueous) {

		return !continueous;
	}
}
