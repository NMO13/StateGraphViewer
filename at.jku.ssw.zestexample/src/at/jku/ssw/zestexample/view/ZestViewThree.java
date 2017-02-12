package at.jku.ssw.zestexample.view;

import java.util.Random;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;

import at.jku.ssw.zestexample.testmodel.TestElem;
import at.jku.ssw.zestexample.testmodel.TestElemContentProvider;
import at.jku.ssw.zestexample.testmodel.TestElemLabelProvider;
import at.jku.ssw.zesttree.VisualTreeViewer;
import at.jku.ssw.zesttree.providers.SimpleTree2GraphProvider;

/**
 * Zest Example Huge Model View
 */
public class ZestViewThree extends ViewPart {

	private final static int NGENS = 6;

	@Override
	public void createPartControl(Composite parent) {

		VisualTreeViewer t = new VisualTreeViewer(parent, true, 2);

		TestElem model = createHugeTestModel(0);

		SimpleTree2GraphProvider adapter = new SimpleTree2GraphProvider(
				new TestElemContentProvider(), new TestElemLabelProvider());
		t.setContentProvider(adapter);
		t.setLabelProvider(adapter);
		t.setInput(model);

	}

	private TestElem createHugeTestModel(int count) {

		TestElem root = new TestElem("", null, getEmptyInstance());
		if(count < NGENS) {
			int ch = new Random().nextInt(NGENS - count) + (NGENS - count) / 4;
			for(int i = 0; i < ch; i++) {
				root.add(createHugeTestModel(count + 1));
			}
		}
		return root;
	}

	private TestElem getEmptyInstance() {

		return new TestElem("", null);
	}

	@Override
	public void setFocus() {

	}

}
