package biocraft.core;

import org.joml.Matrix4f;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.system.MemoryStack;

public class Shader {
	
	private final int programId;
	private int vertexShaderId, fragmentShaderId;
	
	public Shader() throws Exception {
		programId = GL20.glCreateProgram();
		if(programId == 0)
			throw new Exception("Could not create Shader");
		
		bindAttributes();
	}
	
	public void createVertexShader(String shaderCode) throws Exception {
		vertexShaderId = createShader(shaderCode, GL20.GL_VERTEX_SHADER);
	}
	
	public void createFragmentShader(String shaderCode) throws Exception {
		fragmentShaderId = createShader(shaderCode, GL20.GL_FRAGMENT_SHADER);
	}
	
	public int createShader(String shaderCode, int shaderType) throws Exception {
		int shaderId = GL20.glCreateShader(shaderType);
		if(shaderId == 0)
			throw new Exception("Error creating shader. Type: " + shaderType);
		
		GL20.glShaderSource(shaderId, shaderCode);
		GL20.glCompileShader(shaderId);
		
		if(GL20.glGetShaderi(shaderId, GL20.GL_COMPILE_STATUS) == 0)
			throw new Exception("Error compiling shader code: TYPE:" + shaderType + "INFO:" + GL20.glGetShaderInfoLog(shaderId, 1024));
		
		GL20.glAttachShader(programId, shaderId);
		
		return shaderId;
	}
	
	public void link() throws Exception {
		GL20.glLinkProgram(programId);
		if(GL20.glGetProgrami(programId, GL20.GL_LINK_STATUS) == 0)
			throw new Exception("Error linking shader code " + "INFO:" + GL20.glGetProgramInfoLog(programId, 1024));
		
		if(vertexShaderId != 0)
			GL20.glDetachShader(programId, vertexShaderId);
		
		if(fragmentShaderId != 0)
			GL20.glDetachShader(programId, fragmentShaderId);
		
		GL20.glValidateProgram(programId);
		if(GL20.glGetProgrami(programId, GL20.GL_VALIDATE_STATUS) == 0)
			throw new Exception("Unable to validate shader code: " + GL20.glGetProgramInfoLog(programId, 1024));
	}
	
	public void setUniform1f (String attribName, float value) {
		int uniformId = GL20.glGetUniformLocation(programId, attribName);
		GL20.glUniform1f(uniformId, value);;
	}
	
	public void setUniformMat4f (String attribName, Matrix4f value) {
		try(MemoryStack stack = MemoryStack.stackPush()){
			int uniformId = GL20.glGetUniformLocation(programId, attribName);
			GL20.glUniformMatrix4fv(uniformId, false, value.get(stack.mallocFloat(16)));;
		}
	}
	
	private void bindAttributes() {
		bindAttribute(0, "time");
		bindAttribute(1, "projection");
		bindAttribute(2, "model");
		bindAttribute(3, "view");
	}
	
	private void bindAttribute (int id, String attribName) {
		GL20.glBindAttribLocation(programId, id, attribName);
	}
	
	public void bind() {
		GL20.glUseProgram(programId);
	}
	
	public void unbind() {
		GL20.glUseProgram(0);
	}
	
	public void cleanup() {
		unbind();
		if(programId != 0)
			GL20.glDeleteProgram(programId);
	}
}
