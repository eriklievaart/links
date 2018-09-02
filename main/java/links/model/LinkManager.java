package links.model;

import java.io.IOException;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import com.eriklievaart.toolkit.logging.api.LogTemplate;
import com.eriklievaart.toolkit.lang.api.ObservableDelegate;
import com.eriklievaart.toolkit.lang.api.check.Check;
import com.eriklievaart.toolkit.lang.api.collection.NewCollection;
import com.google.inject.Singleton;

@Singleton
public class LinkManager extends ObservableDelegate {
	private LogTemplate log = new LogTemplate(getClass());

	private List<Link> links = NewCollection.list();

	public LinkManager() {
		try {
			load();
		} catch (IOException e) {
			log.warn("Unable to load link configuration");
		}
	}

	public void add(Link link) {
		removeNoUpdate(link);
		links.add(link);
		Collections.sort(links);
		store();
		changeAndnotifyObservers();
	}

	private void load() throws IOException {
		links.addAll(LinkIO.load());
		Collections.sort(links);
		changeAndnotifyObservers();
	}

	public List<Link> getLinks() {
		return Collections.unmodifiableList(links);
	}

	private void store() {
		try {
			LinkIO.store(links);
		} catch (IOException e) {
			log.warn("Unable to store links; $", e, e.getMessage());
		}
	}

	public void remove(Link selected) {
		removeNoUpdate(selected);
		store();
		changeAndnotifyObservers();
	}

	private void removeNoUpdate(Link selected) {
		Iterator<Link> iter = links.iterator();
		while (iter.hasNext()) {
			Link link = iter.next();
			if (link.getName().equals(selected.getName())) {
				iter.remove();
			}
		}
	}

	public void assignCategory(String category, Link link) {
		Check.isTrue(Link.isValidCategory(category), "Invalid category name");
		link.setCategory(category);
		store();
		changeAndnotifyObservers();
	}
}
