package com.nashdevsoft.ld32jam.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.nashdevsoft.ld32jam.MainGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Prototype";
		config.width = 900;
		config.height = 700;
		new LwjglApplication(new MainGame(), config);
	}
}
