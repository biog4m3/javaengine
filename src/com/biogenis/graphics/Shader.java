package com.biogenis.graphics;

import org.lwjgl.opengl.GL20;

public class Shader {
	
	private final int programID;
	private int vertexShaderID, fragmentShaderID;
	
	public Shader() throws Exception {
		programID = GL20.glCreateProgram();
		if(programID == 0)
			throw new Exception("Could not create Shader");
	}
	
	public void createVertexShader(String shaderCode) throws Exception {
		vertexShaderID = createShader(shaderCode, GL20.GL_VERTEX_SHADER);
	}
	
	public void createFragmentShader(String shaderCode) throws Exception {
		fragmentShaderID = createShader(shaderCode, GL20.GL_FRAGMENT_SHADER);
	}
	
	private int createShader(String shaderCode, int shaderType) throws Exception{ 
		int shaderID = GL20.glCreateShader(shaderType);
		
		String shaderTypeName = shaderType == GL20.GL_FRAGMENT_SHADER ? "Fragment" : "Vertex";
		
		if (shaderID == 0)
			throw new Exception("Error creating shader. Type:" + shaderTypeName);
		
		GL20.glShaderSource(shaderID, shaderCode);
		GL20.glCompileShader(shaderID);
		
		if(GL20.glGetShaderi(shaderID, GL20.GL_COMPILE_STATUS) == 0)
			throw new Exception("Error compiling shader code: Type: " + shaderTypeName +
					"Info: " + GL20.glGetShaderInfoLog(shaderID, 1024));
		
		return shaderID;
	}

	public void link() throws Exception {
		GL20.glLinkProgram(programID);
		
		if(GL20.glGetProgrami(programID, GL20.GL_LINK_STATUS) == 0)
			throw new Exception("Error linking shader Info:" + GL20.glGetProgramInfoLog(programID, 1024));
		
		if(vertexShaderID != 0)
			GL20.glDetachShader(programID, vertexShaderID);
		
		if(fragmentShaderID != 0)
			GL20.glDetachShader(programID, fragmentShaderID);
		
		GL20.glValidateProgram(programID);
		
		if(GL20.glGetProgrami(programID, GL20.GL_VALIDATE_STATUS) == 0)
			throw new Exception("Error validating shader Info:" + GL20.glGetProgramInfoLog(programID, 1024));
	}
	
	public void bind() {
		GL20.glUseProgram(programID);
	}
	public void unbind() {
		GL20.glUseProgram(0);
	}
	
	public void dispose() {
		unbind();
		if(programID != 0) {
			GL20.glDeleteProgram(programID);
		}
	}
}
