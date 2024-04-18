package com.mygdx.imageeditor;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3WindowAdapter;
import com.mygdx.imageeditor.ImageEditor;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setForegroundFPS(60);
		config.setTitle("ImageEditor");
		config.setWindowedMode(584, 480);
		System.out.println("Project made by: Benjamin Palmer");
		ImageEditor editor = new ImageEditor();
		config.setWindowListener(new Lwjgl3WindowAdapter() {
			public void filesDropped (String[] files) {
				editor.filesImported(files);
			}
		});
		new Lwjgl3Application(editor, config);
	}
}
