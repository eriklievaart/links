package links.ui.link.action;

import com.eriklievaart.toolkit.swing.api.menu.Command;
import com.google.inject.Inject;

import links.model.Link;
import links.ui.link.LinkEditComponents;
import links.ui.link.LinkOpener;

public class PreviewLinkAction {

	@Inject
	private LinkEditComponents components;
	@Inject
	private LinkOpener selected;

	@Command(name = "Preview")
	public void save() {
		Link link = new Link(components.getNameField().getText(), components.getUrlField().getText());
		link.setUser(components.getUserField().getText());
		link.setPassword(components.getPasswordField().getText());

		selected.browser(link);
	}
}
