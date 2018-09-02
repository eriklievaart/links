package links.ui.main.action;

import javax.swing.JOptionPane;

import links.model.Link;
import links.ui.link.LinkEditController;
import links.ui.main.MainComponents;

import com.eriklievaart.toolkit.swing.api.menu.Command;
import com.google.inject.Inject;

public class EditLinkAction {

	@Inject
	private MainComponents components;
	@Inject
	private LinkEditController editController;

	@Command(name = "Edit")
	public void addLink() {
		Link selected = components.getLinkList().getSelectedValue();
		if (selected == null) {
			JOptionPane.showMessageDialog(null, "Select a link first!");
			return;
		}
		editController.edit(selected);
	}
}
