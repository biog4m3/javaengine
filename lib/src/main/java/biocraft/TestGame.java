package biocraft;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL11;

import biocraft.core.ILogic;
import biocraft.core.ObjectLoader;
import biocraft.core.RenderManager;
import biocraft.core.WindowManager;
import biocraft.core.entity.Model;
import biocraft.core.entity.Texture;

public class TestGame implements ILogic {

	private final RenderManager renderer;
	private final ObjectLoader loader;
	private final WindowManager window;

	private Model cartaz;
	
	public TestGame() {
		renderer = new RenderManager();
		window = Launcher.getWindow();
		loader = new ObjectLoader();
	}
	
	@Override
	public void init() throws Exception {
		renderer.init();

		float[] vertices = {
			-0.5f,0,0,
			0.5f,0,0,
			0.5f,1.3f,0,
			-0.5f,1.3f,0,
		};
		
		float[] texCoords = {
			0, 1,
			1, 1,
			1, 0,
			0, 0
		};
		
		int[] indices = {
			0, 1, 2,
			2, 3, 0
		};
		
		
		cartaz = loader.loadModel(vertices, texCoords, indices);
		Texture texture = loader.loadTexture("textures/cartaz.png");
		cartaz.setTexture(texture);
		
	}

	@Override
	public void input() {
		
	}

	@Override
	public void update() {
		if(d >= -2) {
			d = -2;
		}
		
		if(window.isKeyPressed(GLFW.GLFW_KEY_SPACE)) {
			d += 1;
		}
	}

	float d = -150;
	
	@Override
	public void render() {
		if(window.isResize()) {
			GL11.glViewport(0, 0, window.getWidth(), window.getHeight());
			window.setResize(true);
		}
		renderer.clear();
		renderer.render(cartaz, d);
	}

	@Override
	public void cleanup() {
		renderer.cleanup();
	}

}
