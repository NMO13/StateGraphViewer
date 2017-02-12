package at.jku.ssw.zestexample.view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;

import at.jku.ssw.zestexample.testmodel.TestElem;
import at.jku.ssw.zestexample.testmodel.TestElemContentProvider;
import at.jku.ssw.zestexample.testmodel.TestElemLabelProvider;
import at.jku.ssw.zesttree.VisualTreeViewer;
import at.jku.ssw.zesttree.providers.SimpleTree2GraphProvider;

/**
 * Zest Example Horizontal Text View
 */
public class ZestViewTwo extends ViewPart {

	@Override
	public void createPartControl(Composite parent) {

		VisualTreeViewer t = new VisualTreeViewer(parent, true, 2);
		TestElem model = createTextModel();

		SimpleTree2GraphProvider adapter = new SimpleTree2GraphProvider(
				new TestElemContentProvider(), new TestElemLabelProvider());
		t.setContentProvider(adapter);
		t.setLabelProvider(adapter);
		t.setInput(model);
	}

	private TestElem createTextModel() {

		return make("Aurea", make("prima", make("satast", make("aetas"),
				make("que"), make("vindice")), make("nullo", make("sponte"),
				make("sua", make(","), make("sine")), make("lege",
						make("fidem"), make("rectumque", make("colebat"),
								make(".")), make("Poena", make("metusquab")),
						make("erant", make(","), make("nec"), make("verba"),
								make("minantia", make("fixo", make("aere"))),
								make("legebantur", make(","), make("nec"),
										make("supplex")), make("turba",
										make("timebat"), make("iudicis"))),
						make("ora", make("sui")), make(","), make("sed",
								make("erant")), make("sine")), make("vindice",
						make("tuti")), make(".", make("Nondum", make("caesa"),
						make("suis")), make(",", make("peregrinum", make("ut"),
						make("viseret"), make("orbem")), make("montibus",
						make("in"))), make("liquidas", make("pinus"),
						make("descenderat")), make("undas", make(",")), make(
						"nullaque", make("mortales"), make("praeter"), make(
								"sua", make("litoria"), make("norant")), make(
								".", make("nondum"))), make("praecipites",
						make("cingebant"), make("oppida", make("fossae"))),
						make(".", make("Non")), make("tuba", make("directi",
								make(","), make("non"))), make("aeris",
								make("cornua")), make("flexi", make(",")),
						make("non")), make("galeae", make(","), make("non",
						make("ensis"), make("erant"))))));
	}

	private TestElem make(String text, TestElem... children) {

		return new TestElem(text, null, children);
	}

	@Override
	public void setFocus() {

	}

}
