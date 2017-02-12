package at.jku.ssw.zesttree.providers;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.LabelProviderChangedEvent;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.graphics.Image;
import org.eclipse.zest.core.viewers.IGraphContentProvider;

/**
 * This class acts as a bridge between standard providers and the zest-specific
 * ones. The model is required to build the relations.<br>
 * <br>
 * - ITreeContentProvider is translated to IGraphContentProvider<br>
 * - ILabelProvider gains the ability to differ between Nodes and Relations,
 * which is quite useful as the IGraphContentProvider holds both.
 * 
 * @author Kölbleitner Florian
 */
public class SimpleTree2GraphProvider extends LabelProvider implements
		IGraphContentProvider, ILabelProvider {

	protected ITreeContentProvider treeContentProvider;
	protected ILabelProvider treeLabelProvider;

	private List<Rel> relList = new ArrayList<Rel>();
	private Rel[] relations;

	public SimpleTree2GraphProvider(ITreeContentProvider treeContentProvider,
			ILabelProvider treeLabelProvider) {

		this.treeContentProvider = treeContentProvider;
		this.treeLabelProvider = treeLabelProvider;

		// for forwarding LabelProviderChangedEvent from treeLabelProvider
		treeLabelProvider.addListener(labelProviderListener);
	}

	private void createRelations(Object parent) {

		if(parent == null)
			return;
		for(Object child : treeContentProvider.getChildren(parent)) {
			relList.add(new Rel(parent, child));
		}
		for(Object child : treeContentProvider.getChildren(parent)) {
			createRelations(child);
		}
	}

	@Override
	public void dispose() {

	}

//	=================== CONTENT PROVIDER =======================================

	@Override
	public Object[] getElements(Object input) {

		if(relations == null) {
			createRelations(input);
			relations = relList.toArray(new Rel[relList.size()]);
		}
		return relations;
	}

	@Override
	public Object getDestination(Object rel) {

		if(rel instanceof Rel) {
			Rel r = (Rel) rel;
			return r.getDest();
		} else {
			return null;
		}
	}

	@Override
	public Object getSource(Object rel) {

		if(rel instanceof Rel) {
			Rel r = (Rel) rel;
			return r.getSource();
		} else {
			return null;
		}
	}

	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {

//		model = newInput;
		relList.clear();
		createRelations(newInput);
		relations = relList.toArray(new Rel[relList.size()]);
//		System.out.println(viewer.toString() + " " + oldInput + " " + newInput); 
	}

//	=================== LABEL PROVIDER =========================================

	@Override
	public Image getImage(Object element) {

		if(element instanceof Rel) {
			return null;
		} else {
			return treeLabelProvider.getImage(element);
		}
	}

	@Override
	public String getText(Object element) {

		if(element instanceof Rel) {
			return "";
		} else {
			return treeLabelProvider.getText(element);
		}
	}

	/**
	 * ILabelProviderListener to forward event from
	 * <code>treeLabelProvider</code> to own listeners
	 */
	private ILabelProviderListener labelProviderListener = new ILabelProviderListener() {

		@Override
		public void labelProviderChanged(LabelProviderChangedEvent event) {

			fireLabelProviderChanged(event);
		}

	};

//	================= PRIVATE CLASS REL ========================================

	class Rel {

		Object source, dest;

		public Rel(Object source, Object dest) {

			this.source = source;
			this.dest = dest;
		}

		public boolean equals(Object o) {

			if(!(o instanceof Rel)) {
				return false;
			}
			Rel rel = (Rel) o;
			return source.equals(rel.source) && dest.equals(rel.dest);
		}

		public Object getSource() {

			return source;
		}

		public Object getDest() {

			return dest;
		}
	}
}
