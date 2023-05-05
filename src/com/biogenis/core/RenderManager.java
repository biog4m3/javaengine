package com.biogenis.core;

import org.joml.Matrix4f;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

import com.biogenis.Launcher;
import com.biogenis.core.entity.Model;
import com.biogenis.core.utils.Utils;

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
		t+=0.01f;
		shader.bind();
		
		shader.setUniformMat4f("projection", window.updateProjectionMatrix());
		shader.setUniformMat4f("model", new Matrix4f().translate(0, 0, -.4f).rotate(t, 0, 1, 0));
		shader.setUniformMat4f("view", camera.getViewMatrix());
		shader.setUniform1i("textureSampler", 0);
		
		
		GL30.glBindVertexArray(model.getId());
		
		GL20.glEnableVertexAttribArray(0);
		GL20.glEnableVertexAttribArray(1);
		
		GL13.glActiveTexture(GL13.GL_TEXTURE0);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, model.getTexture().getId());
		
		GL11.glDrawElements(GL11.GL_TRIANGLES, model.getVertexCount(), GL11.GL_UNSIGNED_INT, 0);
		
		
		GL20.glDisableVertexAttribArray(0);
		GL20.glDisableVertexAttribArray(1);
		
		GL30.glBindVertexArray(0);
		
		shader.unbind();
	}
	
	public void clear() {
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
	}
	
	public void cleanup() {
		shader.cleanup();
	}
}
