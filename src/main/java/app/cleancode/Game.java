package app.cleancode;

import org.lwjgl.*;
import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;
import org.lwjgl.system.*;

import app.cleancode.game.GameContext;
import app.cleancode.game.GameListener;
import app.cleancode.game.penguin.PenguinLoader;
import app.cleancode.graphics.Drawable;
import app.cleancode.graphics.shaders.ShaderLoader;
import app.cleancode.input.keyboard.GameKeyCallback;
import app.cleancode.shape.Shape3D;

import java.nio.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.lwjgl.glfw.Callbacks.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryStack.*;
import static org.lwjgl.system.MemoryUtil.*;

@SuppressWarnings("unused")
public class Game implements Runnable {
	private Map<String, Drawable> drawables = new HashMap<>();
	private List<GameListener> listeners = Arrays.asList(new GameListener[] {
			new PenguinLoader()
	});
	private GameContext ctx = new GameContext(drawables::get, drawables::put);
public long window_handle;

public static void main(String[] args) {
	Game game = new Game();
	game.run();
}

@Override
public void run() {
	init();
	beginLoop();
	glfwFreeCallbacks(window_handle);
	glfwDestroyWindow(window_handle);
	glfwTerminate();
	glfwSetErrorCallback(null).free();
}

public void init() {
	GLFWErrorCallback.createPrint(System.err).set();
	if(!glfwInit()) {
		throw new RuntimeException("could not initialise glfw");
	}
	glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
	GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());

	window_handle = glfwCreateWindow(vidmode.width(), vidmode.height(), "hello", glfwGetPrimaryMonitor(), NULL);
	if(window_handle == NULL) {
		throw new RuntimeException("failed to create the window!");
	}
	glfwSetKeyCallback(window_handle, new GameKeyCallback(this::handleKey));
	glfwMakeContextCurrent(window_handle);
	glfwSwapInterval(1);
	glfwShowWindow(window_handle);
}
public void beginLoop() {
	GL.createCapabilities();
	glMatrixMode(GL_PROJECTION);
	glLoadIdentity();
	glOrtho(-5, 5, 0, 6.5, 1, -1);
	glMatrixMode(GL_MODELVIEW);
	glClearColor(1f, 1f, 1f, 0f);
	ARBShaderObjects.glUseProgramObjectARB(ShaderLoader.getShaders("default"));
	ShaderLoader.createShaderUniform("default", "texture_sampler");
	while(!glfwWindowShouldClose(window_handle)) {
		loop();
	}
}

public void loop() {
	glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
	for(GameListener listener : listeners) {
		listener.loop(ctx);
	}
	for(Drawable shape : drawables.values()) {
		shape.draw();
	}
	glfwSwapBuffers(window_handle);

	glfwPollEvents();
}
public void handleKey(int key, int action) {
	for(GameListener listener : listeners) {
		listener.handleKey(key, action, ctx);
	}
}
}
