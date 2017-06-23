package com.tuwar.calgordi.desktop;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.tuwar.calgordi.TuWarGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		System.setProperty("org.lwjgl.opengl.Window.undecorated", "true");
		config.title = "TuWar Game";
		config.height = 310;
		config.width = 640;
		config.resizable = false;
		config.addIcon("data/imagenes/generales/icono.gif", Files.FileType.Internal);
		new LwjglApplication(new TuWarGame(), config);
	}
}
