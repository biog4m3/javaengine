package biocraft;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL11;

import biocraft.core.ILogic;
import biocraft.core.ObjectLoader;
import biocraft.core.RenderManager;
import biocraft.core.Shader;
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
			0, 0, 0,
			1, 0, 0,
			1, 1, 0,
			0, 1, 0,
			
			0, 0, 1,
			1, 0, 1,
			1, 1, 1,
			0, 1, 1,
		};
		
		int[] indices = {
			0, 1, 2,
			2, 3, 0,
		};
		
		
		model = loader.loadModel(vertices, indices);
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
		renderer.render(model);
	}

	@Override
	public void cleanup() {
		renderer.cleanup();
	}

}
