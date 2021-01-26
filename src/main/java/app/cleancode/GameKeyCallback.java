package app.cleancode;

import org.lwjgl.glfw.GLFWKeyCallbackI;
import static org.lwjgl.glfw.GLFW.*;

public class GameKeyCallback implements GLFWKeyCallbackI {

	@Override
	public void invoke(long window, int key, int scancode, int action, int mods) {
		if(key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE) {
			glfwSetWindowShouldClose(window, true);
		}
	}

}
