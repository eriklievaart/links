package links.model;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.eriklievaart.toolkit.io.api.JvmPaths;
import com.eriklievaart.toolkit.io.api.ini.IniNode;
import com.eriklievaart.toolkit.io.api.ini.IniNodeIO;
import com.eriklievaart.toolkit.lang.api.collection.NewCollection;
import com.eriklievaart.toolkit.lang.api.str.Str;
import com.eriklievaart.toolkit.logging.api.LogTemplate;

public class LinkIO {
	private static final LogTemplate log = new LogTemplate(LinkIO.class);

	private static final File LINKS_FILE = new File(JvmPaths.getJarDirOrRunDir(LinkIO.class), "links.ini");
	private static final File BACKUP_DIR = new File(LINKS_FILE.getParentFile(), "bak");

	static {
		log.info("Config file: " + LINKS_FILE);
	}

	public static void store(List<Link> links) throws IOException {
		createBackup();

		List<IniNode> nodes = new ArrayList<IniNode>();

		for (Link link : links) {
			IniNode node = new IniNode("link");
			node.setProperty("name", link.getName());
			node.setProperty("category", link.getCategory());
			node.setProperty("url", link.getUrl());
			if (!Str.isBlank(link.getUser())) {
				node.setProperty("user", link.getUser());
			}
			if (!Str.isBlank(link.getPassword())) {
				node.setProperty("password", link.getPassword());
			}
			nodes.add(node);
		}
		IniNodeIO.write(nodes, LINKS_FILE);
	}

	private static void createBackup() throws IOException {
		if (LINKS_FILE.exists()) {
			BACKUP_DIR.mkdirs();
			File stamped = new File(BACKUP_DIR, "links-" + System.currentTimeMillis() + ".ini");
			log.info("creating backup %", stamped);
			FileUtils.copyFile(LINKS_FILE, stamped);
		}
	}

	public static List<Link> load() throws IOException {
		List<Link> links = NewCollection.list();

		for (IniNode node : IniNodeIO.read(LINKS_FILE)) {
			String name = node.getProperty("name");
			String category = node.getProperty("category");
			String url = node.getProperty("url");
			String password = node.getProperty("password");
			String user = node.getProperty("user");

			Link link = new Link(name, url, category);
			link.setPassword(password == null ? "" : password);
			link.setUser(user == null ? "" : user);
			links.add(link);
		}
		return links;
	}

}
