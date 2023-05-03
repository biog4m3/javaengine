package biocraft;

import biocraft.core.*;

public class Launcher {
	
	private static WindowManager window;
	private static EngineManager engine;
	private static TestGame game;
	
	public static void main(String[] args) {
		window = new WindowManager("biocraft", 900, 600, true);
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
