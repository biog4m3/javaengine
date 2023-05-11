package com.biogenis.core;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;
import org.lwjgl.system.MemoryUtil;

public class WindowManager {
	
	private long window;
	private int width, height;
	private final String title;
	
	
	public WindowManager(String title, int width, int height) {
		this.title = title;
		this.width = width;
		this.height = height;
	}
	
	public void init() throws Exception {
		GLFWErrorCallback.createPrint(System.err).set();
		
		if(!GLFW.glfwInit())
			throw new Exception("Falha ao iniciar glfw");
		
		GLFW.glfwDefaultWindowHints();
		GLFW.glfwWindowHint(GLFW.GLFW_VISIBLE, GL11.GL_FALSE);
		GLFW.glfwWindowHint(GLFW.GLFW_RESIZABLE, GL11.GL_TRUE);
		
		GLFW.glfwWindowHint(GLFW.GLFW_OPENGL_PROFILE, GLFW.GLFW_OPENGL_CORE_PROFILE);
		GLFW.glfwWindowHint(GLFW.GLFW_CONTEXT_VERSION_MAJOR, 4);
		GLFW.glfwWindowHint(GLFW.GLFW_CONTEXT_VERSION_MINOR, 1);
		GLFW.glfwWindowHint(GLFW.GLFW_OPENGL_FORWARD_COMPAT, GL11.GL_TRUE);
		
		boolean maximised = false;
		if(width == 0 || height == 0) {
			width = 100;
			height = 100;
			GLFW.glfwWindowHint(GLFW.GLFW_MAXIMIZED, GL11.GL_TRUE);
			maximised = true;
		}
		
		window = GLFW.glfwCreateWindow(width, height, title, MemoryUtil.NULL, MemoryUtil.NULL);
		if(window == MemoryUtil.NULL)
			throw new RuntimeException("Failed to create GLFW window");
		
		GLFW.glfwSetFramebufferSizeCallback(window, (window, width, height) -> {
			this.width = width;
			this.height = height;
		});
		
		GLFW.glfwSetKeyCallback(window, (window, key, scancode, action, mods) -> {
			if(key == GLFW.GLFW_KEY_ESCAPE && action == GLFW.GLFW_RELEASE) {
				GLFW.glfwSetWindowShouldClose(window, true);
			}
		});
		
		if(maximised) {
			GLFW.glfwMaximizeWindow(window);
		}else {
			GLFWVidMode vidMode = GLFW.glfwGetVideoMode(GLFW.glfwGetPrimaryMonitor());
			GLFW.glfwSetWindowPos(window, (vidMode.width() - width) / 2, (vidMode.height() - height) / 2);
		}
		
		GLFW.glfwMakeContextCurrent(window);
		
		GLFW.glfwShowWindow(window);
		
		GL.createCapabilities();
		
		GL11.glClearColor(0, 0, 0, 1.0f);
		GL11.glEnable(GL11.GL_STENCIL_TEST);
		GL11.glEnable(GL11.GL_DEPTH_TEST);
	}
	
	public void update() {
		GLFW.glfwSwapBuffers(window);
		GLFW.glfwPollEvents();
	}

	public boolean windowShouldClose() {
		return GLFW.glfwWindowShouldClose(window);
	}

	public void dispose() {
		GLFW.glfwDestroyWindow(window);
		GLFW.glfwTerminate();
	}
}
