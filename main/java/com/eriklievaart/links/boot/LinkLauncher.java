package com.eriklievaart.links.boot;

import java.util.Map;

import javax.swing.JOptionPane;

import com.eriklievaart.toolkit.io.api.SystemClipboard;
import com.eriklievaart.toolkit.lang.api.collection.NewCollection;
import com.eriklievaart.toolkit.lang.api.date.TimestampTool;
import com.eriklievaart.toolkit.lang.api.str.Str;
import com.google.inject.Inject;

import links.model.Link;
import links.model.LinkManager;
import links.runtime.Browser;

public class LinkLauncher {

	@Inject
	private LinkManager manager;

	public void open(String[] names) {
		Map<String, Link> index = NewCollection.mapNotNull();

		for (Link link : manager.getLinks()) {
			index.put(strip(link.getName()), link);
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

	private void open(Link link) {
		if (Str.notBlank(link.getUser())) {
			SystemClipboard.writeSelection(link.getUser());
		}
		if (Str.notBlank(link.getPassword())) {
			SystemClipboard.writeString(link.getPassword());
		}
		new Thread(() -> {
			try {
				Browser.firefoxOpen(link.getUrl());
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
			}
		}).start();

		sleep(5 * TimestampTool.ONE_MINUTE);
	}

	private void sleep(long millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			System.err.println("thread interrupted");
		}
	}

	private String strip(String name) {
		return name.replaceAll("\\W", "");
	}
}
