package at.jku.ssw.stategraph.widgets;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.FigureUtilities;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Display;
import org.eclipse.zest.core.widgets.internal.CachedLabel;
import org.eclipse.zest.core.widgets.internal.GraphLabel;

public class StateGraphLabel extends CachedLabel {

	private int arcWidth;
	private int borderWidth;
	private boolean highlight = false;
	private boolean isLeave;
	private static int UPPER_OFFSET_TEXT = 2;
	private static int LOWER_OFFSET_TEXT = 2;
	
	public StateGraphLabel(String text, Image image, boolean cacheLabel, boolean isLeave) {
		super(cacheLabel);
		this.arcWidth = 8;
		this.isLeave = isLeave;
		this.borderWidth = 1;
	}
	
	
	@Override
	public void paint(Graphics graphics) {
		if(highlight)
			graphics.setLineWidth(3);
		graphics.setBackgroundColor(getBackgroundColor());
		Rectangle r = bounds.getCopy();
		graphics.fillRoundRectangle(r, arcWidth, arcWidth);
		r.setSize(bounds.width-1, bounds.height-1);
		graphics.drawRoundRectangle(r, arcWidth, arcWidth);
		super.paint(graphics);
		
		if(!isLeave) {
			Dimension fontSize = new Dimension(0, 0);
			fontSize = this.calculateTextSize();
			graphics.setLineWidth(0);
			graphics.drawLine(bounds.x, bounds.y + fontSize.height + LOWER_OFFSET_TEXT, bounds.x + bounds.width, bounds.y + fontSize.height + LOWER_OFFSET_TEXT);
		}
	}

	@Override
	protected Point getTextLocation() {
		Point loc = super.getTextLocation();
		if(!isLeave) {
			loc.y = UPPER_OFFSET_TEXT;
		}
		return loc;
	}
	
	public int getStateNameWidth() {
		return calculateTextSize().width;
	}
	
	public int getStateNameHeight() {
		return UPPER_OFFSET_TEXT + LOWER_OFFSET_TEXT + calculateTextSize().height;
	}	
	
	@Override
	protected void paintFigure(Graphics graphics) {
		if (isOpaque()) {
			super.paintFigure(graphics);
		}
		Rectangle bounds = getBounds();
		graphics.translate(bounds.x, bounds.y);
		if (getIcon() != null) {
			graphics.drawImage(getIcon(), getIconLocation());
		}
		if (!isEnabled()) {
			graphics.translate(1, 1);
			graphics.setForegroundColor(ColorConstants.buttonLightest);
			graphics.drawText(getSubStringText(), getTextLocation());
			graphics.translate(-1, -1);
			graphics.setForegroundColor(ColorConstants.buttonDarker);
		}
		graphics.drawText(getSubStringText(), getTextLocation());
		graphics.translate(-bounds.x, -bounds.y);
		return;
	}

	@Override
	protected Color getBackgroundTextColor() {
		return getBackgroundColor();
	}
	
	public void setText(String s) {

		if(!s.equals("")) {
			super.setText(s);
		} else {
			super.setText("");
		}
		adjustBoundsToFit();
	}
	
	protected void adjustBoundsToFit() {

		String text = getText();
		if((text != null)) {
			Font font = getFont();
			if(font != null) {
				Dimension minSize = FigureUtilities.getTextExtents(text, font);
				if(getIcon() != null) {
					org.eclipse.swt.graphics.Rectangle imageRect = getIcon()
							.getBounds();
					int expandHeight = Math.max(imageRect.height
							- minSize.height, 0);
					minSize.expand(imageRect.width + 4, expandHeight);
				}
				minSize.expand(10 + (2 * borderWidth), 4 + (2 * borderWidth));
				setBounds(new Rectangle(getLocation(), minSize));
			}
		}
	}

	public void unhighlight() {
		highlight = false;
	}
	
	public void highlight() {
		highlight = true;
	}
	
	public boolean isHighlighted() {
		return highlight;
	}
}
