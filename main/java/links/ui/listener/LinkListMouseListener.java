package links.ui.listener;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import com.google.inject.Inject;

import links.ui.link.LinkOpener;

public class LinkListMouseListener extends MouseAdapter {

	@Inject
	private LinkOpener opener;

	@Override
	public void mouseClicked(MouseEvent me) {
		if (me.getClickCount() == 2 && me.getButton() == 1) {
			opener.browser();
		}
	}

}
