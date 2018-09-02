package links.ui.link.action;

import links.model.Link;
import links.model.LinkManager;
import links.ui.link.LinkEditComponents;

import com.eriklievaart.toolkit.swing.api.menu.Command;
import com.google.inject.Inject;

public class SaveLinkAction {

	@Inject
	private LinkEditComponents components;
	@Inject
	private LinkManager manager;

	@Command(name = "Save")
	public void save() {
		String name = components.getNameField().getText();
		String url = components.getUrlField().getText();
		String category = components.getCategoryField().getText();
		String user = components.getUserField().getText();
		String password = components.getPasswordField().getText();

		Link link = new Link(name, url, category);
		link.setUser(user);
		link.setPassword(password);
		manager.add(link);
		
		components.getJFrame().setVisible(false);
	}
}
