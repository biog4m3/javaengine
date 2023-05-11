package com.biogenis.core;

import com.biogenis.*;

public class EngineManager {
	
	private WindowManager window;
	private static double deltaTime = 0;
	private ILogic gameLogic;
	
	public EngineManager(ILogic logic) {
		window = Launcher.getWindow();
		gameLogic = logic;
	}
	
	public void init() throws Exception {
		window.init();
		long lastTime = System.currentTimeMillis();
		while(!window.windowShouldClose()) {
			long now = System.currentTimeMillis();
			deltaTime = (now - lastTime) / (double) 1000;
			lastTime = now;
			
			
			update();
			render();
		}
	}
	
	public void update() {
		gameLogic.update();
	}
	
	public void render() {
		gameLogic.render();
		window.update();
	}
	
	public static double getDeltaTime() {
		return deltaTime;
	}
	
	public void dispose() {
		window.dispose();
		gameLogic.dispose();
	}
}
