package com.eriklievaart.links.boot;

import java.io.File;

import com.eriklievaart.toolkit.io.api.JvmPaths;
import com.eriklievaart.toolkit.swing.api.SwingThread;
import com.eriklievaart.toolkit.swing.api.WindowSaver;
import com.eriklievaart.toolkit.swing.api.laf.LookAndFeel;
import com.google.inject.Guice;
import com.google.inject.Injector;

import links.ui.main.MainController;

public class Main {

	public static void main(final String[] args) throws Exception {
		SwingThread.invokeAndWaitUnchecked(new Runnable() {
			@Override
			public void run() {
				LookAndFeel.instance().load();
				String jarDir = JvmPaths.getJarDirOrRunDir(Main.class);
				WindowSaver.initialize(new File(jarDir, "windows.ini"));
				Injector injector = Guice.createInjector();

				if (args.length > 0) {
					injector.getInstance(LinkLauncher.class).open(args);
				} else {
					injector.getInstance(MainController.class).show();
				}
			}
		});
	}
}
