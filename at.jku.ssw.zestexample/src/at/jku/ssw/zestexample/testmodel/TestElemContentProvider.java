package at.jku.ssw.zestexample.testmodel;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;


public class TestElemContentProvider implements ITreeContentProvider {

	
	
	@Override
	public Object[] getChildren(Object parentElement) {
		
		return ((TestElem)parentElement).getChildren().toArray();
	}

	@Override
	public Object getParent(Object element) {
		
		return ((TestElem)element).getParent();
	}

	@Override
	public boolean hasChildren(Object element) {
		return 	!((TestElem)element).isLeaf();
	}

	@Override
	public Object[] getElements(Object inputElement) {
		return getChildren(inputElement);
	}

	@Override
	public void dispose() {
	}

	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
	}
}
