package com.biogenis;

import com.biogenis.core.*;

public class Launcher {
	
	private static WindowManager window;
	private static EngineManager engine;
	private static ILogic logic;
	
	public static void main(String[] args) {
		try {
			logic = new Demo();
			
			window = new WindowManager("game", 900, 600);
			engine = new EngineManager(logic);
			
			engine.init();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public static WindowManager getWindow() {
		return window;
	}
}
