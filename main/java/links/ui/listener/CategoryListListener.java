package links.ui.listener;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import links.ui.main.MainComponents;
import links.ui.main.MainController;

import com.google.inject.Inject;

public class CategoryListListener implements ListSelectionListener {

	@Inject
	private MainComponents components;
	@Inject
	private MainController controller;

	@Override
	public void valueChanged(ListSelectionEvent e) {
		controller.fillList(components.getCategoryList().getSelectedValue());
	}
}
