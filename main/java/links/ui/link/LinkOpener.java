package links.ui.link;

import java.io.File;
import java.util.function.Consumer;

import com.eriklievaart.toolkit.io.api.CheckFile;
import com.eriklievaart.toolkit.io.api.SystemClipboard;
import com.eriklievaart.toolkit.lang.api.FormattedException;
import com.eriklievaart.toolkit.lang.api.str.Str;
import com.eriklievaart.toolkit.logging.api.LogTemplate;
import com.google.inject.Inject;

import links.model.Link;
import links.runtime.Browser;
import links.ui.main.MainComponents;

public class LinkOpener {
	private LogTemplate log = new LogTemplate(getClass());

	@Inject
	private MainComponents components;

	public void browser() {
		Link link = prepareLink();
		if (link != null) {
			browser(link);
		}
	}

	public void browser(Link link) {
		Browser.desktopBrowse(link.getUrl());
	}

	public void chrome() {
		execute(link -> Browser.chromeOpen(link.getUrl()));
	}

	public void firefox() {
		execute(link -> Browser.firefoxOpen(link.getUrl()));
	}

	public void iexplore() {
		execute(link -> Browser.iexploreOpen(link.getUrl()));
	}

	private void execute(Consumer<Link> consumer) {
		Link link = prepareLink();
		if (link != null) {
			consumer.accept(link);
		}
	}

	private Link prepareLink() {
		Link link = components.getLinkList().getSelectedValue();
		if (link == null) {
			return null;
		}
		if (Str.notBlank(link.getUser())) {
			SystemClipboard.writeSelection(link.getUser());
		}
		if (Str.notBlank(link.getPassword())) {
			SystemClipboard.writeString(link.getPassword());
		}
		if (link.getUrl().matches("https?://.++")) {
			return link;
		}
		if (link.getUrl().matches("(file:/*)/?.++")) {
			String path = link.getUrl().replaceFirst("file:/++", "/");
			CheckFile.isFile(new File(path));
			log.info("File % exists", path);
			return new Link(link.getName(), "file://" + path);
		}
		throw new FormattedException("invalid protocol for link %", link.getUrl());
	}

	public void password() {
		prepareLink();
	}
}
