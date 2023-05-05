package com.biogenis;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL11;

import com.biogenis.core.ILogic;
import com.biogenis.core.ObjectLoader;
import com.biogenis.core.RenderManager;
import com.biogenis.core.WindowManager;
import com.biogenis.core.entity.Model;
import com.biogenis.core.entity.Texture;

public class TestGame implements ILogic {

	private final RenderManager renderer;
	private final ObjectLoader loader;
	private final WindowManager window;
	
	private Model test;
	
	public TestGame() {
		renderer = new RenderManager();
		window = Launcher.getWindow();
		loader = new ObjectLoader();
	}
	
	@Override
	public void init() throws Exception {
		renderer.init();
		
		test = loader.loadOBJModel("/models/bunny.obj");
		Texture texture = loader.loadTexture("textures/cartaz.png");
		test.setTexture(texture);
	}

	@Override
	public void input() {
		
	}

	@Override
	public void update() {
		
	}
	
	@Override
	public void render() {
		if(window.isResize()) {
			GL11.glViewport(0, 0, window.getWidth(), window.getHeight());
			window.setResize(true);
		}
		renderer.clear();
		renderer.render(test);
	}

	@Override
	public void cleanup() {
		renderer.cleanup();
	}

}
