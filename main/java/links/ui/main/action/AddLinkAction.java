package links.ui.main.action;

import links.ui.link.LinkEditController;
import links.ui.main.MainComponents;

import com.eriklievaart.toolkit.swing.api.menu.Command;
import com.google.inject.Inject;

public class AddLinkAction {

	@Inject
	private MainComponents components;
	@Inject
	private LinkEditController editController;

	@Command(name = "Add")
	public void addLink() {
		editController.add(components.getCategoryList().getSelectedValue());
	}

}
