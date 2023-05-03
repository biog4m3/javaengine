package biocraft.core;

import org.joml.Matrix4f;
import org.joml.Vector3f;

public class Camera {
	private Vector3f position = new Vector3f(0,0,0);
	private float pitch;
	private float yaw;
	private float roll;
	
	public void setPosition (float dx, float dy, float dz) {
		position.x = dx;
		position.y = dy;
		position.z = dz;
	}
	
	public void setRotation(float dx, float dy, float dz) {
		pitch = dx;
		yaw = dy;
		roll = dz;
	}
	
	public void movePosition(float dx, float dy, float dz) {
		//position.x += Math.cos(Math.toRadians(yaw)) * dz;
		//position.y += dy;
		//position.z += Math.sin(Math.toRadians(yaw)) * dx;
	}
	
	public void moveRotation(float dx, float dy, float dz) {
		pitch += dx;
		yaw += dy;
		roll += dz;
	}
	
	public Matrix4f getViewMatrix() {
		Matrix4f matrix = new Matrix4f();
		
		matrix.rotate((float) Math.toRadians(pitch), new Vector3f(1, 0, 0));
		matrix.rotate((float) Math.toRadians(yaw), new Vector3f(0, 1, 0));
		matrix.rotate((float) Math.toRadians(roll), new Vector3f(0, 0, 1));
		
		matrix.translate(-position.x, -position.y, -position.z);
		return matrix;
	}
	
}
