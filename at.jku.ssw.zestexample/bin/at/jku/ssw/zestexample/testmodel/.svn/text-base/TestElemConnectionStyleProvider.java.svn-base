package at.jku.ssw.zestexample.testmodel;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.IFigure;
import org.eclipse.swt.graphics.Color;
import org.eclipse.zest.core.viewers.IConnectionStyleProvider;

public class TestElemConnectionStyleProvider implements
		IConnectionStyleProvider {

	private final Color HIGHLIGHT_COLOR = ColorConstants.red;

	@Override
	public Color getColor(Object rel) {

		return ColorConstants.darkBlue;
	}

	@Override
	public int getConnectionStyle(Object rel) {

		return 0;
	}

	@Override
	public Color getHighlightColor(Object rel) {

		return HIGHLIGHT_COLOR;
	}

	@Override
	public int getLineWidth(Object rel) {

//		return getColor(src, dest) == HIGHLIGHT_COLOR ? 4 : 2;
		return 1;
	}

	@Override
	public IFigure getTooltip(Object rel) {

		return null;
	}

	@Override
	public void dispose() {

	}
}
