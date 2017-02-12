package at.jku.ssw.zestexample.view;


import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.zest.core.widgets.ZestStyles;
import org.eclipse.zest.layouts.LayoutStyles;

import at.jku.ssw.stategraph.StateGraphViewer;
import at.jku.ssw.stategraph.layoutalgorithms.VerticalStateGraphLayoutAlgorithm;
import at.jku.ssw.stategraph.layoutalgorithms.WaterfallLayoutAlgorithm;
import at.jku.ssw.stategraph.providers.StateModel2GraphContentAndLabelProvider;
import at.jku.ssw.stategraph.testmodel.ModelBuilder;
import at.jku.ssw.stategraph.testmodel.StateModelNode;
import at.jku.ssw.stategraph.testmodel.TestElemEntityStyleProvider;
import at.jku.ssw.zestexample.ZestTreeExamplePlugin;

public class StateModelView extends ViewPart {

	public StateModelView() {

		super();
	}

	@Override
	public void createPartControl(Composite parent) {
		//parent.setLayout(null);
		// set buttons
//		Button b1 = new Button(parent, 0);
//		b1.setBounds(0, 0, 100, 30);
//		b1.setText("StateGraph");
//		Button b2 = new Button(parent, 0);
//		b2.setBounds(120, 0, 100, 30);
//		b2.setText("Waterfall");
				StateGraphViewer t = new StateGraphViewer(parent, ZestStyles.NONE);
	    StateModelNode root = null;
			root = ModelBuilder.buildModel(getClass().getResourceAsStream(/*"TestStateModel3.xml"*/"Abb3_2.xml"));
	    StateModel2GraphContentAndLabelProvider m 
	    			= new StateModel2GraphContentAndLabelProvider(new TestElemEntityStyleProvider());
	    VerticalStateGraphLayoutAlgorithm l = new VerticalStateGraphLayoutAlgorithm(LayoutStyles.NONE);
	    t.setLayoutAlgorithm(l);
	    t.setContentProvider(m);
		t.setLabelProvider(m);
		t.setInput(root);
		t.addSelectionChangedListener(new ISelectionChangedListener() {
			
			@Override
			public void selectionChanged(SelectionChangedEvent arg0) {
				System.out.println(arg0);
				System.out.println(arg0.getSelection());
				StateGraphViewer v = (StateGraphViewer) arg0.getSource();
				
			}
		});
		//Control c = t.getControl();
		//c.setBounds(40, 40, 800, 800);
	}

	@Override
	public void setFocus() {

	}

	private static Image getImage(final String path) {

		try {
			ImageDescriptor i = ZestTreeExamplePlugin.getImageDescriptor(path);

			return ImageDescriptor.createFromImageData(
					i.getImageData().scaledTo(16, 16)).createImage();
			// return i.createImage();
		} catch (Exception e) {
			return null;
		}
	}
}
