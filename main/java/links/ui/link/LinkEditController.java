package links.ui.link;

import java.awt.Container;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import links.model.Link;
import links.ui.link.action.PreviewLinkAction;
import links.ui.link.action.SaveLinkAction;

import com.eriklievaart.toolkit.swing.menu.ReflectionActionBuilder;
import com.google.inject.Inject;

public class LinkEditController {

	private LinkEditComponents components;

	@Inject
	public LinkEditController(LinkEditComponents components) {
		this.components = components;
		init();
	}

	private void init() {
		JFrame frame = components.getJFrame();
		frame.setName("linkedit");
		frame.setTitle("Specify Link");

		initContentPane(frame, initButtonPanel());

		frame.getRootPane().setDefaultButton(components.getSaveButton());
	}

	private void initContentPane(JFrame frame, JPanel buttonPanel) {
		Container container = frame.getContentPane();
		container.setLayout(new GridLayout(0, 1));
		addLinkEditFields(container);
		container.add(buttonPanel);
	}

	private void addLinkEditFields(Container container) {
		container.add(components.getNameLabel());
		container.add(components.getNameField());
		container.add(components.getUrlLabel());
		container.add(components.getUrlField());
		container.add(components.getCategoryLabel());
		container.add(components.getCategoryField());
		container.add(components.getUserLabel());
		container.add(components.getUserField());
		container.add(components.getPasswordLabel());
		container.add(components.getPasswordField());
	}

	private JPanel initButtonPanel() {
		JPanel buttonPanel = components.getButtonPanel();
		buttonPanel.setLayout(new GridLayout(1, 0));
		buttonPanel.add(components.getSaveButton());
		buttonPanel.add(components.getBrowserButton());
		return buttonPanel;
	}

	public void edit(Link link) {
		components.getNameField().setText(link == null ? "" : link.getName());
		components.getUrlField().setText(link == null ? "" : link.getUrl());
		components.getCategoryField().setText(link == null ? "unknown" : link.getCategory());
		components.getPasswordField().setText(link == null ? "" : link.getPassword());
		components.getUserField().setText(link == null ? "" : link.getUser());

		components.getJFrame().setVisible(true);
	}

	public void add(String category) {
		components.getNameField().setText("");
		components.getUrlField().setText("");
		components.getCategoryField().setText(category == null ? "unknown" : category);
		components.getPasswordField().setText("");
		components.getUserField().setText("");

		components.getJFrame().setVisible(true);
	}

	@Inject
	public void preview(PreviewLinkAction action) {
		components.getBrowserButton().setAction(ReflectionActionBuilder.createSingleAction(action));
	}

	@Inject
	public void save(SaveLinkAction action) {
		components.getSaveButton().setAction(ReflectionActionBuilder.createSingleAction(action));
	}
}
