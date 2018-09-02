package boot;

import java.awt.Desktop;
import java.net.URI;
import java.util.Map;

import javax.swing.JOptionPane;

import com.eriklievaart.toolkit.lang.api.collection.NewCollection;
import com.eriklievaart.toolkit.lang.api.str.Str;
import com.google.inject.Inject;

import links.model.Link;
import links.model.LinkManager;

public class LinkLauncher {

	@Inject
	private LinkManager manager;

	public void open(String[] names) {
		Map<String, String> index = NewCollection.mapNotNull();

		for (Link link : manager.getLinks()) {
			index.put(strip(link.getName()), link.getUrl());
		}
		for (String name : names) {
			String stripped = strip(name);
			if (index.containsKey(stripped)) {
				open(index.get(stripped));
			} else {
				JOptionPane.showMessageDialog(null, Str.sub("Unknown url %", stripped));
			}
		}
	}

	private void open(String url) {
		try {
			Desktop.getDesktop().browse(new URI(url));
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}

	private String strip(String name) {
		return name.replaceAll("\\W", "");
	}
}
