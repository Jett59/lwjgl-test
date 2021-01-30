package app.cleancode.input.keyboard;

import org.lwjgl.glfw.GLFWKeyCallbackI;
import static org.lwjgl.glfw.GLFW.*;

public class GameKeyCallback implements GLFWKeyCallbackI {
private IntBiConsumer additionalCallback;
public GameKeyCallback(IntBiConsumer additionalCallback) {
	this.additionalCallback = additionalCallback;
}

	@Override
	public void invoke(long window, int key, int scancode, int action, int mods) {
		if(key == GLFW_KEY_ESCAPE && action == GLFW_PRESS) {
			glfwSetWindowShouldClose(window, true);
		}
		additionalCallback.accept(key, action);
	}

}
