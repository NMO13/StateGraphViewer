package at.jku.ssw.zesttree.providers;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.swt.graphics.Color;
import org.eclipse.zest.core.viewers.IConnectionStyleProvider;
import org.eclipse.zest.core.viewers.IEntityStyleProvider;

/**
 * This class acts as a bridge between standard providers and the Zest-specific
 * ones. Additionally two Zest-specific Providers are in use which make detailed
 * statements about node and connection style. The model is required to build
 * the relations.<br>
 * <br>
 * - ITreeContentProvider is translated to IGraphContentProvider<br>
 * - ILabelProvider gains the ability to differ between Nodes and Relations,
 * which is quite useful as the IGraphContentProvider holds both.<br>
 * - IEntityStyleProvider makes specific statements about node style<br>
 * - IConnectionStyleProvider makes specific statements about connection style
 * 
 * @author Kölbleitner Florian
 */
public class Tree2GraphProvider extends SimpleTree2GraphProvider implements
		IEntityStyleProvider, IConnectionStyleProvider {

	private IEntityStyleProvider styleProvider;
	private IConnectionStyleProvider connectionStyleProvider;
	private boolean showTooltipIfNoText;

	/**
	 * @param treeContentProvider The TreeContentProvider(ITreeContentProvider)
	 * @param treeLabelProvider The LabelProvider(ILabelProvider)
	 * @param styleProvider
	 * @param connectionStyleProvider
	 */
	public Tree2GraphProvider(ITreeContentProvider treeContentProvider,
			ILabelProvider treeLabelProvider,
			IEntityStyleProvider styleProvider,
			IConnectionStyleProvider connectionStyleProvider) {

		this(treeContentProvider, treeLabelProvider, styleProvider,
				connectionStyleProvider, false);
	}

	public Tree2GraphProvider(ITreeContentProvider treeContentProvider,
			ILabelProvider treeLabelProvider,
			IEntityStyleProvider styleProvider,
			IConnectionStyleProvider connectionStyleProvider,
			boolean showTooltipIfNoText) {

		super(treeContentProvider, treeLabelProvider);
		this.styleProvider = styleProvider;
		this.connectionStyleProvider = connectionStyleProvider;
		this.showTooltipIfNoText = showTooltipIfNoText;
	}

//	================= IENTITYSTYLE PROVIDER ====================================

	@Override
	public boolean fisheyeNode(Object entity) {

		return styleProvider.fisheyeNode(entity);
	}

	@Override
	public Color getBackgroundColour(Object entity) {

		return styleProvider.getBackgroundColour(entity);
	}

	@Override
	public Color getBorderColor(Object entity) {

		return styleProvider.getBorderColor(entity);
	}

	@Override
	public Color getBorderHighlightColor(Object entity) {

		return styleProvider.getBorderHighlightColor(entity);
	}

	@Override
	public int getBorderWidth(Object entity) {

		return styleProvider.getBorderWidth(entity);
	}

	@Override
	public Color getForegroundColour(Object entity) {

		return styleProvider.getForegroundColour(entity);
	}

	@Override
	public Color getNodeHighlightColor(Object entity) {

		return styleProvider.getNodeHighlightColor(entity);
	}

//	================ IENTITYSTYLE AND ICONNECTIONSTYLE PROVIDER ================

	@Override
	public IFigure getTooltip(Object entity) {

		IFigure tooltip;
		if(entity instanceof Rel) {
			tooltip = connectionStyleProvider.getTooltip(entity);
		} else {
			tooltip = styleProvider.getTooltip(entity);
			if(tooltip == null && showTooltipIfNoText) {
				return new Label(treeLabelProvider.getText(entity));
			}
		}
		return tooltip;
	}

//	================= ICONNECTIONSTYLE PROVIDER ================================

	@Override
	public Color getColor(Object rel) {

		return connectionStyleProvider.getColor(rel);
	}

	@Override
	public int getConnectionStyle(Object rel) {

		return connectionStyleProvider.getConnectionStyle(rel);
	}

	@Override
	public Color getHighlightColor(Object rel) {

		return connectionStyleProvider.getHighlightColor(rel);
	}

	@Override
	public int getLineWidth(Object rel) {

		return connectionStyleProvider.getLineWidth(rel);
	}
}
