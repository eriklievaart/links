package links.runtime;

import java.awt.Desktop;
import java.net.URI;

import com.eriklievaart.toolkit.runtime.api.CliCommand;
import com.eriklievaart.toolkit.runtime.api.CliInvoker;

public class Browser {

	private static final String CHROME_WINDOWS = "C:/Program\\wFiles\\w(x86)/Google/Chrome/Application/chrome.exe";
	private static final String CHROME_LINUX = "/usr/bin/google-chrome";

	public static void desktopBrowse(String url) {
		try {
			Desktop.getDesktop().browse(new URI(url));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void chromeOpen(final String url) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				CliInvoker.invoke(CliCommand.from(CHROME_LINUX + " " + url));
			}
		}).start();
	}
}