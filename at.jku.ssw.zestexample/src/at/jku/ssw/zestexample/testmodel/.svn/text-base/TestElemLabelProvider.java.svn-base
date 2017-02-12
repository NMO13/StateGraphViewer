package at.jku.ssw.zestexample.testmodel;

import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

public class TestElemLabelProvider extends LabelProvider {

	private boolean imagesOnly;

	public TestElemLabelProvider() {

		this(false);
	}

	public TestElemLabelProvider(boolean imagesOnly) {

		this.imagesOnly = imagesOnly;
	}

	public Image getImage(Object element) {

		return ((TestElem) element).getImage();
	}

	public String getText(Object element) {

		return element == null ? "" : imagesOnly ? "" : ((TestElem) element)
				.getName();
	}
}
