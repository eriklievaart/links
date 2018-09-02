package links.ui.main;

import java.awt.BorderLayout;
import java.awt.Container;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import links.model.Link;
import links.model.LinkManager;
import links.ui.listener.CategoryListListener;
import links.ui.listener.LinkListMouseListener;
import links.ui.main.action.AddLinkAction;
import links.ui.main.action.EditLinkAction;
import links.ui.main.action.PasswordLinkAction;
import links.ui.main.action.RemoveLinkAction;
import links.ui.observer.ListObserver;

import com.eriklievaart.toolkit.swing.menu.ReflectionActionBuilder;
import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class MainController {

	private MainComponents components;

	@Inject
	private LinkManager manager;

	@Inject
	public MainController(MainComponents components) {
		this.components = components;

		JFrame frame = components.getFrame();
		frame.setName("main");
		frame.setTitle("Links");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		initContentPane(frame, initListPanel(components));
	}

	private void initContentPane(JFrame frame, JPanel listPanel) {
		Container container = frame.getContentPane();
		container.add(listPanel, BorderLayout.CENTER);
		container.add(initButtonPanel(), BorderLayout.SOUTH);
	}

	private JPanel initListPanel(MainComponents components) {
		JPanel listPanel = components.getListPanel();
		listPanel.add(new JScrollPane(components.getCategoryList()));
		listPanel.add(new JScrollPane(components.getLinkList()));
		return listPanel;
	}

	private JPanel initButtonPanel() {
		JPanel buttonPanel = components.getButtonPanel();
		buttonPanel.add(components.getAddButton());
		buttonPanel.add(components.getEditButton());
		buttonPanel.add(components.getRemoveButton());
		buttonPanel.add(components.getPasswordButton());
		return buttonPanel;
	}

	@Inject
	public void initAddButton(AddLinkAction action) {
		setAction(action, components.getAddButton());
	}

	@Inject
	public void initAddButton(EditLinkAction action) {
		setAction(action, components.getEditButton());
	}

	@Inject
	public void initRemoveButton(RemoveLinkAction action) {
		setAction(action, components.getRemoveButton());
	}

	@Inject
	public void initPasswordButton(PasswordLinkAction action) {
		setAction(action, components.getPasswordButton());
	}

	private void setAction(Object action, JButton button) {
		button.setAction(ReflectionActionBuilder.createSingleAction(action));
	}

	@Inject
	public void initObserver(ListObserver clo) {
		manager.addObserver(clo);
		manager.changeAndnotifyObservers();
	}

	@Inject
	public void initListListener(LinkListMouseListener listener) {
		components.getLinkList().addMouseListener(listener);
	}

	@Inject
	public void initListListener(CategoryListListener listener) {
		components.getCategoryList().addListSelectionListener(listener);
	}

	@Inject
	public void initLinkListKeyListener(LinkListKeyListener listener) {
		components.getLinkList().addKeyListener(listener);
	}

	public void show() {
		components.getFrame().setVisible(true);
	}

	public void fillList(String category) {
		List<Link> links = listLinksForCategory(category);
		JList<Link> linkList = components.getLinkList();
		Link value = linkList.getSelectedValue();
		String selected = value == null ? null : value.getName();
		linkList.setListData(links.toArray(new Link[] {}));
		linkList.setSelectedIndex(getIndex(selected, links));
		linkList.setVisible(true);
	}

	private List<Link> listLinksForCategory(String category) {
		List<Link> links = new ArrayList<>(manager.getLinks());
		Iterator<Link> iterator = links.iterator();

		if (category != null) {
			while (iterator.hasNext()) {
				Link link = iterator.next();
				if (!category.equals(link.getCategory())) {
					iterator.remove();
				}
			}
		}
		return links;
	}

	private int getIndex(String name, List<Link> links) {
		for (int i = 0; i < links.size(); i++) {
			if (links.get(i).getName().equals(name)) {
				return i;
			}
		}
		return -1;
	}

}
