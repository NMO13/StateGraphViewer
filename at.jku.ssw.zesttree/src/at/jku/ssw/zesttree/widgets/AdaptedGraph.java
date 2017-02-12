package at.jku.ssw.zesttree.widgets;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import org.eclipse.draw2d.Animation;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.zest.core.widgets.Graph;
import org.eclipse.zest.core.widgets.ZestStyles;
import org.eclipse.zest.layouts.InvalidLayoutConfiguration;
import org.eclipse.zest.layouts.LayoutAlgorithm;
import org.eclipse.zest.layouts.LayoutEntity;
import org.eclipse.zest.layouts.LayoutRelationship;

import at.jku.ssw.zesttree.scaling.DefaultNodeScaler;
import at.jku.ssw.zesttree.scaling.INodeScaler;
import at.jku.ssw.zesttree.scaling.ScaleShape;

public class AdaptedGraph extends Graph {

	private boolean startup;
	private ScaleShape scale;
	private INodeScaler nodeScaler;

	public AdaptedGraph(Composite parent, int style) {

		super(parent, style);
		this.style = ZestStyles.IGNORE_INVISIBLE_LAYOUT;
		startup = true;

		scale = createLabelScale();
		nodeScaler = new DefaultNodeScaler(this);
	}

	private ScaleShape createLabelScale() {

		ScaleShape scale = new ScaleShape();
		scale.setLocation(new Point(5, 5));
		getRootLayer().add(scale);
		getViewport().getHorizontalRangeModel().addPropertyChangeListener(
				scrollDetector);
		getViewport().getVerticalRangeModel().addPropertyChangeListener(
				scrollDetector2);
		return scale;
	}

	protected void runLayout(Dimension d,
			LayoutRelationship[] connectionsToLayout,
			LayoutEntity[] nodesToLayout, LayoutAlgorithm algorithm)
			throws InvalidLayoutConfiguration {

		Animation.markBegin();
		algorithm.applyLayout(nodesToLayout, connectionsToLayout, 0, 0,
				d.width, d.height, false, false);
		if(startup) {
			startup = false;
			Animation.run(ANIMATION_TIME * 3);
		} else {
			Animation.run(ANIMATION_TIME);
		}
	}

	/**
	 * returns the Graphs scale
	 * 
	 * @return the ScaleShape
	 */
	public ScaleShape getScaleShape() {

		return scale;
	}

	public void setDefaultNodeSize() {

		nodeScaler.defaultResizeNodes();
	}

// ==== LISTENERS ==============================================================

	private PropertyChangeListener scrollDetector = new PropertyChangeListener() {

		@Override
		public void propertyChange(PropertyChangeEvent evt) {

//			if(evt.getPropertyName().equalsIgnoreCase("value")) {
//				scale.setLocation(new Point(5 + (Integer) evt.getNewValue(),
//						scale.getLocation().y));
//			}
		}
	};

	private PropertyChangeListener scrollDetector2 = new PropertyChangeListener() {

		@Override
		public void propertyChange(PropertyChangeEvent evt) {

//			if(evt.getPropertyName().equalsIgnoreCase("value")) {
//				scale.setLocation(new Point(scale.getLocation().x,
//						(5 + (Integer) evt.getNewValue())));
//			}
		}
	};
}
