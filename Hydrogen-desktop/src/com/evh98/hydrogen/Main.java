package com.evh98.hydrogen;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class Main {
	public static void main(String[] args) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "Hydrogen";
		cfg.useGL20 = true;
		cfg.width = 540;
		cfg.height = 960;
		
		new LwjglApplication(new Hydrogen(), cfg);
	}
}