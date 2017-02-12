package at.jku.ssw.stategraph.testmodel;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Display;
import org.eclipse.zest.core.viewers.IEntityStyleProvider;

import at.jku.ssw.stategraph.providers.Relation;

public class TestElemEntityStyleProvider implements IEntityStyleProvider {

	Color backgroundColor;
	@Override
	public boolean fisheyeNode(Object entity) {
		return false;
	}

	@Override
	public Color getBackgroundColour(Object entity) {

		
		
		if(entity instanceof StateModelNode) {
			StateModelNode smn = (StateModelNode) entity;
			java.awt.Color col = new java.awt.Color(45, 121, 147); // light blue
//			for(int i = 0; i < smn.getDepth(); i++) {
//				float[] hsb = java.awt.Color.RGBtoHSB(col.getRed(), col.getGreen(), col.getBlue(), null);
//				float brightness = hsb[2];
//				brightness += 0.03;
//				col = new java.awt.Color(java.awt.Color.HSBtoRGB(hsb[0], hsb[1], brightness));
//				col = new java.awt.Color(col.getRed()+10, col.getGreen()+10, col.getBlue()+10);
//			}
//			backgroundColor =  new Color(Display.getCurrent(), col.getRed(), col.getGreen(), col.getBlue());
		
			switch(smn.getDepth()) {
				case 0: backgroundColor = new Color(Display.getCurrent(), 150, 205, 223); break;
				case 1: backgroundColor = new Color(Display.getCurrent(), 167, 214, 228); break;
				case 2: backgroundColor = new Color(Display.getCurrent(), 184, 221, 233); break;
				case 3: backgroundColor = new Color(Display.getCurrent(), 200, 228, 238); break;
				case 4: backgroundColor = new Color(Display.getCurrent(), 216, 237, 243); break;
				case 5: backgroundColor = new Color(Display.getCurrent(), 233, 244, 248); break;
				case 6: backgroundColor = new Color(Display.getCurrent(), 249, 252, 253); break;
				default:
					backgroundColor = new Color(Display.getCurrent(), 255, 255, 255);
			}
			if(smn.getChildren().length == 0)
				backgroundColor = new Color(Display.getCurrent(), 255, 255, 255);
		}
		//backgroundColor =  new Color(Display.getCurrent(), 0, 0, 0);
		return backgroundColor;
	}

	@Override
	public Color getBorderColor(Object entity) {

		return ColorConstants.darkGreen;
	}

	@Override
	public Color getBorderHighlightColor(Object entity) {

		return ColorConstants.red;
	}

	@Override
	public int getBorderWidth(Object entity) {

		return 2;
	}

	@Override
	public Color getForegroundColour(Object entity) {

		return ColorConstants.black;
	}

	@Override
	public Color getNodeHighlightColor(Object entity) {
		return ColorConstants.yellow;
	}

	@Override
	public IFigure getTooltip(Object entity) {
		if(entity instanceof Relation) {
			Relation rel = (Relation) entity;
			return new Label(rel.getTransitionName());
		}
		else if(entity instanceof StateModelNode) {
			StateModelNodeImpl i = (StateModelNodeImpl) entity;
			return new Label(i.toString()); 
		}
		
		return null;
	}

	@Override
	public void dispose() {
		backgroundColor.dispose();
	}
}
