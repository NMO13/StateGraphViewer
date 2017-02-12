package at.jku.ssw.stategraph.layoutalgorithms;

import org.eclipse.zest.layouts.algorithms.AbstractLayoutAlgorithm;
import org.eclipse.zest.layouts.dataStructures.InternalNode;
import org.eclipse.zest.layouts.dataStructures.InternalRelationship;

public class WaterfallLayoutAlgorithm extends AbstractLayoutAlgorithm{

	public WaterfallLayoutAlgorithm(int styles) {
		super(styles);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void setLayoutArea(double x, double y, double width, double height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected boolean isValidConfiguration(boolean asynchronous,
			boolean continuous) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	protected void applyLayoutInternal(InternalNode[] entitiesToLayout,
			InternalRelationship[] relationshipsToConsider, double boundsX,
			double boundsY, double boundsWidth, double boundsHeight) {
		double offsetX = 0;
		double offsetY = 0;
		double totalWidth = 0;
		double totalHeight = 0;
		for(InternalNode inode : entitiesToLayout) {
//			double width = inode.getWidthInLayout();
//			double height = inode.getHeightInLayout();
			inode.setInternalLocation(offsetX, offsetY);
			inode.setInternalSize(40, 40);
			offsetX += 50;
			offsetY += 50;
		}
		updateLayoutLocations(entitiesToLayout);
	}

	@Override
	protected void preLayoutAlgorithm(InternalNode[] entitiesToLayout,
			InternalRelationship[] relationshipsToConsider, double x, double y,
			double width, double height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void postLayoutAlgorithm(InternalNode[] entitiesToLayout,
			InternalRelationship[] relationshipsToConsider) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected int getTotalNumberOfLayoutSteps() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	protected int getCurrentLayoutStep() {
		// TODO Auto-generated method stub
		return 0;
	}

}
