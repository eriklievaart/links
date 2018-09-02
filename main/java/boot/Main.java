package boot;

import java.io.File;

import links.ui.main.MainController;

import com.eriklievaart.toolkit.io.JvmPaths;
import com.eriklievaart.toolkit.swing.SwingThread;
import com.eriklievaart.toolkit.swing.WindowSaver;
import com.google.inject.Guice;
import com.google.inject.Injector;

public class Main {

	public static void main(final String[] args) throws Exception {
		SwingThread.invokeAndWaitUnchecked(new Runnable() {
			@Override
			public void run() {
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