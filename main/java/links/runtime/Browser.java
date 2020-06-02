package links.runtime;

import java.awt.Desktop;
import java.net.URI;

import com.eriklievaart.toolkit.io.api.SystemProperties;
import com.eriklievaart.toolkit.runtime.api.CliCommand;
import com.eriklievaart.toolkit.runtime.api.CliInvoker;

public class Browser {

	private static final String CHROME_WINDOWS = "C:/Program\\wFiles\\w(x86)/Google/Chrome/Application/chrome.exe";
	private static final String CHROME_LINUX = "/usr/bin/google-chrome";
	private static final String CHROME = osSwitch(CHROME_LINUX, CHROME_WINDOWS);

	private static final String FIREFOX_WINDOWS = "C:/Program\\wFiles/Mozilla\\wFirefox/firefox.exe";
	private static final String FIREFOX_LINUX = "/usr/bin/firefox";
	private static final String FIREFOX = osSwitch(FIREFOX_LINUX, FIREFOX_WINDOWS);

	private static final String IEXPLORE = "C:/Program\\wFiles/internet\\wexplorer/iexplore.exe";

	public static void desktopBrowse(String url) {
		try {
			Desktop.getDesktop().browse(new URI(url));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void chromeOpen(final String url) {
		openWithExecutable(url, CHROME);
	}

	public static void firefoxOpen(final String url) {
		openWithExecutable(url, FIREFOX);
	}

	public static void iexploreOpen(final String url) {
		openWithExecutable(url, IEXPLORE);
	}

	private static void openWithExecutable(final String url, String executable) {
		new Thread(() -> CliInvoker.invoke(CliCommand.from(executable + " " + url))).start();
	}

	private static String osSwitch(String linux, String windows) {
		return SystemProperties.isUnix() ? linux : windows;
	}
}
