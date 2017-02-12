package at.jku.ssw.zestexample.view;


import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.IViewSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.zest.core.widgets.ZestStyles;
import org.eclipse.zest.layouts.LayoutStyles;

import at.jku.ssw.stategraph.StateGraphViewer;
import at.jku.ssw.stategraph.layoutalgorithms.VerticalStateGraphLayoutAlgorithm;
import at.jku.ssw.stategraph.layoutalgorithms.WaterfallLayoutAlgorithm;
import at.jku.ssw.stategraph.providers.StateModel2GraphContentAndLabelProvider;
import at.jku.ssw.stategraph.testmodel.StateModelNode;
import at.jku.ssw.stategraph.testmodel.TestElemEntityStyleProvider;



public class ExampleView extends ViewPart{

	@Override
	public void createPartControl(Composite parent) {
		parent.setLayout(null);
		// set buttons
		Button b1 = new Button(parent, 0);
		b1.setBounds(0, 0, 100, 30);
		b1.setText("StateGraph");
		Button b2 = new Button(parent, 0);
		b2.setBounds(120, 0, 100, 30);
		b2.setText("Waterfall");
		
		StateGraphViewer t = new StateGraphViewer(parent, ZestStyles.NONE, b1, b2);
	    StateModelNode model = null;
	    StateModel2GraphContentAndLabelProvider m = new StateModel2GraphContentAndLabelProvider(new TestElemEntityStyleProvider());
	    VerticalStateGraphLayoutAlgorithm l = new VerticalStateGraphLayoutAlgorithm(LayoutStyles.NONE);
	    t.setLayoutAlgorithm(l);
	    t.setContentProvider(m);
		t.setLabelProvider(m);
		t.setInput(model);
		Control c = t.getControl();
		c.setBounds(40, 40, 800, 800);
	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void init(IViewSite site) throws PartInitException {
		// TODO Auto-generated method stub
		super.init(site);
	}


}
