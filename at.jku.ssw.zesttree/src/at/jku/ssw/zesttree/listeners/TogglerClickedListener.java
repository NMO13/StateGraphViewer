package at.jku.ssw.zesttree.listeners;

import org.eclipse.draw2d.ActionListener;

public interface TogglerClickedListener extends ActionListener {

	void togglerClicked(TogglerClickedEvent e);
}
