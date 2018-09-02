package links.ui.main.action;

import javax.swing.JOptionPane;

import links.model.Link;
import links.model.LinkManager;
import links.ui.main.MainComponents;

import com.eriklievaart.toolkit.swing.api.menu.Command;
import com.google.inject.Inject;

public class RemoveLinkAction {

	@Inject
	private LinkManager links;
	@Inject
	private MainComponents components;

	@Command(name = "Remove")
	public void addLink() {
		Link selected = components.getLinkList().getSelectedValue();
		if (selected == null) {
			JOptionPane.showMessageDialog(null, "Select a link first!");
			return;
		}
		links.remove(selected);
	}
}
