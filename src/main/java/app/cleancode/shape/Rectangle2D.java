package app.cleancode.shape;

import static org.lwjgl.opengl.GL11.*;

public class Rectangle2D extends Shape2D {
public float[] color;
public float x, y, width, height;
private float[][] mat = new float[4][2];
public Rectangle2D(float[] color, float x, float y, float width, float height) {
	this.color = color;
	this.x = x;
	this.y = y;
	this.width = width;
	this.height = height;
}
	@Override
	public void draw() {
		mat[0][0] = x;
		mat[0][1] = y;
		mat[1][0] = x;
		mat[1][1] = y+height;
		mat[2][0] = x+width;
		mat[2][1] = y+height;
		mat[3][0] = x+width;
		mat[3][1] = y;
		super.draw(color, mat, GL_QUADS);
	}

}
