package links.ui.main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import com.google.inject.Inject;

import links.ui.link.LinkOpener;

public class LinkListKeyListener implements KeyListener {

	@Inject
	private LinkOpener selected;

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (isControlAlt(e, KeyEvent.VK_C)) {
			selected.chrome();
		}
		if (isControlAlt(e, KeyEvent.VK_F)) {
			selected.firefox();
		}
		if (isControlAlt(e, KeyEvent.VK_I)) {
			selected.iexplore();
		}
		if (isControlAlt(e, KeyEvent.VK_P)) {
			selected.password();
		}
	}

	private boolean isControlAlt(KeyEvent e, int key) {
		return e.isAltDown() && e.isControlDown() && e.getKeyCode() == key;
	}
}
