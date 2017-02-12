package at.jku.ssw.zesttree.scaling;

import org.eclipse.core.runtime.ListenerList;
import org.eclipse.draw2d.Clickable;
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.FreeformLayout;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.ImageFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.MouseEvent;
import org.eclipse.draw2d.MouseListener;
import org.eclipse.draw2d.MouseMotionListener;
import org.eclipse.draw2d.RoundedRectangle;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.swt.graphics.Image;

import at.jku.ssw.zesttree.activator.ZestTreePlugin;
import at.jku.ssw.zesttree.listeners.ScaleValueChangeEvent;
import at.jku.ssw.zesttree.listeners.ScaleValueChangedListener;

/**
 * A ScaleShape contains the functionalities of an interactive slider
 * 
 * @author Kölbleitner Florian
 * 
 */
public class ScaleShape extends Clickable {

	private final static Dimension DEFAULT_SIZE = new Dimension(200, 15);
	private final static int DEFAULT_MINIMUM = 0;
	private final static int DEFAULT_MAXIMUM = 20;
	private final static int DEFAULT_VALUE = 10;

	private int minimum, maximum, value;
	private ScaleButton v;
	private ListenerList listeners;
	private static Image bgImage;

	static {

		bgImage = ZestTreePlugin.getImageDescriptor("icons/scale.png")
				.createImage();
	}

	public ScaleShape() {

		minimum = DEFAULT_MINIMUM;
		maximum = DEFAULT_MAXIMUM;
		value = DEFAULT_VALUE;
		listeners = new ListenerList();

		setStyle(STYLE_TOGGLE);
		setSize(DEFAULT_SIZE);
		setLayoutManager(new FreeformLayout());

		ImageFigure bg = new ImageFigure();
		bg.setSize(DEFAULT_SIZE);
		bg.addMouseListener(locationDetector);
		bg.addMouseMotionListener(locationDragDetector);
		bg.setImage(bgImage);
		add(bg);

		v = new ScaleButton();
		add(v);
		setValue(value);
	}

	@Override
	public IFigure getToolTip() {

		return new Label(
				" Changes the number of visible Text characters. Actual: "
						+ value + " ");
	}

	public int getMinimum() {

		return minimum;
	}

	public void setMinimum(int minimum) {

		this.minimum = minimum;
	}

	public int getMaximum() {

		return maximum;
	}

	public void setMaximum(int maximum) {

		this.maximum = maximum;
	}

	public int getValue() {

		return value;
	}

	private void setValue(int x) {

		if(x >= 0 && x < maximum) {
			value = x;
			v.setLocation(new Point(getBounds().x + DEFAULT_SIZE.width
					/ maximum * x, v.getLocation().y));
			fireScaleValueChanged();
		}
	}

	public static int getDefaultValue() {

		return DEFAULT_VALUE;
	}

	private MouseListener locationDetector = new MouseListener() {

		@Override
		public void mouseDoubleClicked(MouseEvent me) {

		}

		@Override
		public void mousePressed(MouseEvent me) {

			int newValue = (me.x - getBounds().x)
					/ (DEFAULT_SIZE.width / maximum);
			if(newValue != value) {
				setValue(newValue);
			}
		}

		@Override
		public void mouseReleased(MouseEvent me) {

		}
	};

	private MouseMotionListener locationDragDetector = new MouseMotionListener() {

		@Override
		public void mouseDragged(MouseEvent me) {

			int newValue = (me.x - getBounds().x)
					/ (DEFAULT_SIZE.width / maximum);
			if(newValue != value) {
				setValue(newValue);
			}
		}

		@Override
		public void mouseEntered(MouseEvent me) {

		}

		@Override
		public void mouseExited(MouseEvent me) {

		}

		@Override
		public void mouseHover(MouseEvent me) {

		}

		@Override
		public void mouseMoved(MouseEvent me) {

		}
	};

	public void addScaleValueChangedListener(ScaleValueChangedListener l) {

		listeners.add(l);
	}

	public void removeScaleValueChangedListener(ScaleValueChangedListener l) {

		listeners.remove(l);
	}

	private void fireScaleValueChanged() {

		for(Object o : listeners.getListeners()) {
			ScaleValueChangedListener l = (ScaleValueChangedListener) o;
			l.scaleValueChanged(new ScaleValueChangeEvent(this, value));
		}
	}

	private class ScaleButton extends Clickable {

		public ScaleButton() {

			this.setStyle(STYLE_BUTTON);
			setBackgroundColor(ColorConstants.buttonDarker);
			this.addMouseMotionListener(locationDragDetector);
			this.setSize(DEFAULT_SIZE.width / maximum, DEFAULT_SIZE.height);
//			this.setToolTip(TOOLTIP);

			RoundedRectangle r = new RoundedRectangle();
			r.setBackgroundColor(ColorConstants.buttonDarker);
			r.setForegroundColor(ColorConstants.buttonDarkest);
			r.setSize(this.getSize().expand(-4, -4));
			r.setLocation(new Point(2, 2));
			this.add(r);
		}
	}
}
