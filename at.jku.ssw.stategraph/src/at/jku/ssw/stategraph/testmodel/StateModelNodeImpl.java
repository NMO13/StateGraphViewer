package at.jku.ssw.stategraph.testmodel;

import java.util.ArrayList;
import java.util.List;

public class StateModelNodeImpl implements StateModelNode{

	private StateModelNode parent;
	private String value;
	private List<StateModelNode> children = new ArrayList<StateModelNode>();
	private List<StateModelNode> siblings = new ArrayList<StateModelNode>();
	private List<StateModelNode> source = new ArrayList<StateModelNode>();
	private List<StateModelNode> target = new ArrayList<StateModelNode>();
	//private List<String> transitionNames = new ArrayList<String>();
	String[] transitionNames = new String[20];
	private int depth;
	
	public StateModelNodeImpl(String value, StateModelNode parent) {
		this.value = value;
		this.parent = parent;
		depth = (isRoot() == true) ? 0 : -1;
	}
	
	@Override
	public void addChild(Object child) {
		if(!(child instanceof StateModelNode))
			throw new IllegalArgumentException();
		setSibling((StateModelNodeImpl)child, children);
		children.add((StateModelNodeImpl)child);
		((StateModelNodeImpl)child).depth = depth + 1;
	}
	
	public void addSibling(StateModelNode n) {
		siblings.add(n);
	}
	
	public void setDepth(int depth) {
		this.depth = depth;
	}
	
	@Override 
	public StateModelNode getParent() {
		return parent;
	}

	@Override
	public StateModelNode[] getChildren() {
		return children.toArray(new StateModelNode[children.size()]);
	}

	@Override
	public StateModelNode[] getSiblings() {
		return siblings.toArray(new StateModelNode[siblings.size()]);
	}

	@Override
	public int getDepth() {
		return depth;
	}

	@Override
	public boolean isRoot() {
		return parent == null ? true : false;
	}

	@Override
	public Object getValue() {
		return value;
	}
	
	private void setSibling(StateModelNode sibling,
			List<StateModelNode> nodes) {
		for(int i = 0; i < nodes.size(); i++) {
			((StateModelNodeImpl)nodes.get(i)).addSibling(sibling);
		}
	}
	
	// not needed
	public void setSource(StateModelNode s) {
		source.add(s);
	}
	
	public int setTarget(StateModelNode t) {
		target.add(t);
		return target.size() - 1;
	}
	
	public static StateModelNode findNode(StateModelNode parent, String value) {
		if(parent.getValue().equals(value))
			return parent;
		for(int i = 0; i < parent.getChildren().length; i++) {
			StateModelNode val = findNode(parent.getChildren()[i], value);
			if(val != null)
				return val;
		}
		return null;
	}

	@Override
	public StateModelNode[] getTargets() {
		return target.toArray(new StateModelNode[target.size()]);
	}

	@Override
	public String toString() {
		return value;
	}

	@Override
	public String getTransitionName(int index) {
		if(index < transitionNames.length)
			return transitionNames[index];
		return null;
	}

	@Override
	public void setTransitionName(String val, int pos) {
		// the index of the array has to match the position of the target so
		// that a transition name can be unambiguously assigned to its respective transition
		if(transitionNames.length <= pos) {
			String[] arr = new String[pos+1];
			System.arraycopy(transitionNames, 0, arr, 0, transitionNames.length);
			transitionNames = arr;
		}
		transitionNames[pos] = val;
	}
	
}
