package biocraft.core;

import org.joml.Matrix4f;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

import biocraft.Launcher;
import biocraft.core.entity.Model;
import biocraft.core.utils.Utils;

public class RenderManager {
	private final WindowManager window;
	private Shader shader;
	private Camera camera;
	
	public RenderManager() {
		window = Launcher.getWindow();
		camera = new Camera();
	}
	
	public void init() throws Exception {
		shader = new Shader();
		shader.createVertexShader(Utils.loadResource("/shaders/default.vs"));
		shader.createFragmentShader(Utils.loadResource("/shaders/default.fs"));
		shader.link();
		
	}
	
	float t = 0;
	
	public void render(Model model) {
		clear();
		shader.bind();
		
		shader.setUniformMat4f("projection", window.updateProjectionMatrix());
		shader.setUniformMat4f("model", new Matrix4f().translate(0,0,-3).rotate(t, 0, 1, 0));
		shader.setUniformMat4f("view", camera.getViewMatrix());
		
		GL30.glBindVertexArray(model.getId());
		GL20.glEnableVertexAttribArray(0);
		
		GL11.glDrawElements(GL11.GL_TRIANGLES, model.getVertexCount(), GL11.GL_DEPTH_BUFFER_BIT,0);
		
		GL20.glDisableVertexAttribArray(0);
		GL30.glBindVertexArray(0);
		
		shader.unbind();
		
		t+=0.1f;
	}
	
	public void clear() {
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
	}
	
	public void cleanup() {
		shader.cleanup();
	}
}
