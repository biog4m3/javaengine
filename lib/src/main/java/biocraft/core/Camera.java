package biocraft.core;

import org.joml.Matrix4f;
import org.joml.Vector3f;

public class Camera {
	private Vector3f position = new Vector3f(0,0,0);
	private float pitch;
	private float yaw;
	private float roll;
	
	public void move(float dx, float dy, float dz) {
		position.x += dx;
		position.y += dy;
		position.z += dz;
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
