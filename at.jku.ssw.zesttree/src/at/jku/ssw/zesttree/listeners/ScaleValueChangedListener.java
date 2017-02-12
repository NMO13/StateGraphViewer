package at.jku.ssw.zesttree.listeners;

import org.eclipse.draw2d.ActionListener;

public interface ScaleValueChangedListener extends ActionListener {

	void scaleValueChanged(ScaleValueChangeEvent e);
}
