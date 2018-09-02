package links.ui.observer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JList;

import links.model.Link;
import links.model.LinkManager;
import links.ui.main.MainComponents;
import links.ui.main.MainController;

import com.google.inject.Inject;

public class ListObserver implements Observer {

	@Inject
	private LinkManager manager;
	@Inject
	private MainComponents components;
	@Inject
	private MainController controller;

	@Override
	public void update(Observable observable, Object obj) {
		updateCategories();
		updateLinks();
	}

	private void updateLinks() {
		controller.fillList(components.getCategoryList().getSelectedValue());
	}

	private void updateCategories() {
		JList<String> list = components.getCategoryList();
		String selected = list.getSelectedValue();
		List<String> categories = new ArrayList<>();

		for (Link link : manager.getLinks()) {
			if (link.getCategory() != null && !categories.contains(link.getCategory())) {
				categories.add(link.getCategory());
			}
		}
		Collections.sort(categories, new NullSafeStringComparator());

		String[] array = categories.toArray(new String[] {});
		list.setListData(array);
		list.setSelectedIndex(getIndex(selected, array));
		list.setVisible(true);
	}

	private int getIndex(String selected, String[] array) {
		if (selected != null) {
			for (int i = 0; i < array.length; i++) {
				if (selected.equals(array[i])) {
					return i;
				}
			}
		}
		return 0;
	}

}
