package biocraft;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL11;

import biocraft.core.ILogic;
import biocraft.core.ObjectLoader;
import biocraft.core.RenderManager;
import biocraft.core.WindowManager;
import biocraft.core.entity.Model;
import biocraft.core.utils.Utils;

public class TestGame implements ILogic {

	private int direction = 0;
	private float colour = 0.0f;
	
	private final RenderManager renderer;
	private final ObjectLoader loader;
	private final WindowManager window;
	
	private Model model;
	
	public TestGame() {
		renderer = new RenderManager();
		window = Launcher.getWindow();
		loader = new ObjectLoader();
	}
	
	@Override
	public void init() throws Exception {
		renderer.init();
		
		float[] vertices = {
            -0.5f, 0.5f, 0f,
            -0.5f, -0.5f, 0f,
            0.5f, -0.5f, 0f,
            0.5f, -0.5f, 0f,
            0.5f, 0.5f, 0f,
            -0.5f, 0.5f, 0f
		};
		
		int[] indices = {
			0, 1, 3,
			3, 1, 2
		};
		
		
		model = loader.loadModel(vertices, indices);
	}

	@Override
	public void input() {
		// TODO Auto-generated method stub
		if(window.isKeyPressed(GLFW.GLFW_KEY_UP)) {
			direction = 1;
		}else if(window.isKeyPressed(GLFW.GLFW_KEY_DOWN)) {
			direction = -1;
		}else {
			direction = 0;
		}
	}

	@Override
	public void update() {
		colour += direction * 0.01f;
		if(colour > 1) {
			colour = 1.0f; 
		}else if (colour <= 0) {
			colour = 0;
		}
		
	}

	@Override
	public void render() {
		if(window.isResize()) {
			GL11.glViewport(0, 0, window.getWidth(), window.getHeight());
			window.setResize(true);
		}
		
		window.setClearColor(colour, colour, colour, 1);
		renderer.render(model);
	}

	@Override
	public void cleanup() {
		renderer.cleanup();
	}

}