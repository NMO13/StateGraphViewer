package at.jku.ssw.zesttree.scrolling;

import org.eclipse.zest.core.widgets.GraphNode;

/**
 * A ScrollStrategy scrolls the Graphs Viewport to the appropriate location
 * considering action type and node location
 * 
 * @author Kölbleitner Florian
 */
public interface IScrollStrategy {

	public enum ScrollAction {

		EXPANSION,
		SHIFT,
		CLICK,
		MODE_CHANGE
	}

	/**
	 * notifies the ScrollStrategy to perform a scroll
	 * 
	 * @param cur The involved GraphNode
	 * @param action The type of action
	 */
	void performScroll(GraphNode cur, ScrollAction action);
}
