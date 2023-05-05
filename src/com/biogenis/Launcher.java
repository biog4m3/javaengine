package com.biogenis;

import com.biogenis.core.EngineManager;
import com.biogenis.core.ILogic;
import com.biogenis.core.WindowManager;

public class Launcher {
	
	private static WindowManager window;
	private static EngineManager engine;
	private static TestGame game;
	
	public static void main(String[] args) {
		window = new WindowManager("Fabio", 1980, 1080, true);
		game = new TestGame();
		engine = new EngineManager();
		
		try {
			engine.start();
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static WindowManager getWindow() {
		return window;
	}
	
	public static ILogic getGame() {
		return game;
	}
}
