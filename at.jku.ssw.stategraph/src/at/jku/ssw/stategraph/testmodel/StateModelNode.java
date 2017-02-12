package at.jku.ssw.stategraph.testmodel;

import java.util.List;

public interface StateModelNode {

	StateModelNode getParent();
	StateModelNode[] getChildren();
	StateModelNode[] getSiblings();
	void addChild(Object child);
	int getDepth();
	boolean isRoot();
	Object getValue();
	public void setSource(StateModelNode s);
	public StateModelNode[] getTargets();
	public int setTarget(StateModelNode t);
	public String getTransitionName(int index);
	void setTransitionName(String val, int pos);
}
