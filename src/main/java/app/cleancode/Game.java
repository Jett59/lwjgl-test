package app.cleancode;

import org.lwjgl.*;
import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;
import org.lwjgl.system.*;

import java.nio.*;

import static org.lwjgl.glfw.Callbacks.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryStack.*;
import static org.lwjgl.system.MemoryUtil.*;

@SuppressWarnings("unused")
public class Game implements Runnable {
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
	glfwSetKeyCallback(window_handle, new GameKeyCallback());
	glfwMakeContextCurrent(window_handle);
	glfwSwapInterval(1);
	glfwShowWindow(window_handle);
}
public void beginLoop() {
	GL.createCapabilities();
	glMatrixMode(GL_PROJECTION);
	glLoadIdentity();
	glOrtho(0, 640, 480, 0, 1, -1);
	glMatrixMode(GL_MODELVIEW);
	glClearColor(0f, 0f, 0f, 0f);
	while(!glfwWindowShouldClose(window_handle)) {
		loop();
	}
}
public void loop() {
	glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
	glColor3f(0f, 0f, 1f);
	glBegin(GL_QUADS);
	glVertex2f(0, 0);
	glVertex2f(0, 240);
	glVertex2f(320, 240);
	glVertex2f(320, 0);
	glEnd();
	glfwSwapBuffers(window_handle);

	glfwPollEvents();
}
}
