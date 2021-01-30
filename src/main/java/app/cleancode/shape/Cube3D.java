package app.cleancode.shape;

import org.lwjgl.opengl.GL30;

public class Cube3D extends Shape3D {
public float x, y, z, width, height, depth, r, g, b;
private float[][] mat = new float[24][3];
private float[] color = new float[3];

	@Override
	protected void glDraw() {
		createSquare(0, x, y, z, x+width, y, z, x+width, y+height, z, x, y+height, z);
		createSquare(4, x, y, z+depth, x+width, y, z+depth, x+width, y+height, z+depth, x, y+height, z+depth);
		createSquare(8, x, y, z, x, y, z+depth, x+width, y, z+depth, x+width, y, z);
		createSquare(12, x, y+height, z, x, y+height, z+depth, x+width, y+height, z+depth, x+width, y+height, z);
		createSquare(16, x, y, z, x, y, z+depth, x, y+height, z+depth, x, y+height, z);
		createSquare(20, x+width, y, z, x+width, y, z+depth, x+width, y+height, z+depth, x+width, y+height, z);
		color[0] = r;
		color[1] = g;
		color[2] = b;
		super.glDraw(color, mat, GL30.GL_QUADS);
	}

	public void createSquare(int startMatIndex, float firstX, float firstY, float firstZ, float secondX, float secondY, float secondZ, float thirdX, float thirdY, float thirdZ, float forthX, float forthY, float forthZ) {
		float[] first = mat[startMatIndex];
		float[] second = mat[startMatIndex+1];
		float[] third = mat[startMatIndex+2];
		float[] forth = mat[startMatIndex+3];
		first[0] = firstX;
		first[1] = firstY;
		first[2] = firstZ;
		second[0] = secondX;
		second[1] = secondY;
		second[2] = secondZ;
		third[0] = thirdX;
		third[1] = thirdY;
		third[2] = thirdZ;
				forth[0] = forthX;
				forth[1] = forthY;
				forth[2] = forthZ;
	}

}
