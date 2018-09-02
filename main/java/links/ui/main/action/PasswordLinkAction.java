package links.ui.main.action;

import javax.swing.JOptionPane;

import com.eriklievaart.toolkit.swing.menu.Command;
import com.google.inject.Inject;

import links.model.Link;
import links.ui.link.LinkOpener;
import links.ui.main.MainComponents;

public class PasswordLinkAction {
	@Inject
	private MainComponents components;
	@Inject
	private LinkOpener copy;

	@Command(name = "Login")
	public void addLink() {
		Link selected = components.getLinkList().getSelectedValue();
		if (selected == null) {
			JOptionPane.showMessageDialog(null, "Select a link first!");
			return;
		}
		copy.password();
	}
}
