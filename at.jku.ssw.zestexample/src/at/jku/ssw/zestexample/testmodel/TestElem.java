package at.jku.ssw.zestexample.testmodel;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.graphics.Image;

public class TestElem implements Comparable<TestElem> {

	protected String name;
	protected Image image;
	protected TestElem parent;
	private List<TestElem> children;

	public TestElem(String name, Image image) {

		this(name, image, new TestElem[0]);
	}

	public TestElem(String name, Image image, TestElem... children) {

		this.name = name;
		this.image = image;
		parent = null;
		this.children = new ArrayList<TestElem>();
		for(TestElem t : children) {
			this.children.add(t);
		}
	}

	public void add(TestElem z) {

		z.parent = this;
		children.add(z);
	}

	public String getName() {

		return name;
	}

	public Image getImage() {

		return image;
	}

	public TestElem getParent() {

		return parent;
	}

	public List<TestElem> getChildren() {

		return children;
	}

	public boolean isLeaf() {

		return children.size() == 0;
	}

	@Override
	public int compareTo(TestElem o) {

		return this.name.compareTo(o.name);
	}

	public String toString() {

		return getName();
	}
}
