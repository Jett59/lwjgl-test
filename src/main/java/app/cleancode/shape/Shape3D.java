package app.cleancode.shape;

import static org.lwjgl.opengl.GL11.*;

import app.cleancode.graphics.Node;

public abstract class Shape3D extends Node {
protected final void glDraw(float[] color, float[][] mat, int mode) {
	if(color == null || color.length != 3) {
		throw new IllegalArgumentException("color must be an array of three elements (rgb)");
	}
	if(mat == null) {
		throw new IllegalArgumentException("shape matrix may not be null");
	}
	glColor3f(color[0], color[1], color[2]);
	glBegin(mode);
	for(float[] pos : mat) {
		if(pos == null || pos.length != 3) {
			throw new IllegalArgumentException("matrix elements must be arrays of 3 elements (xyz)");
		}
		glVertex3f(pos[0], pos[1], pos[2]);
	}
	glEnd();
}
protected abstract void glDraw();
}
