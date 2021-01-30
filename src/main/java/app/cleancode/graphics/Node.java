package app.cleancode.graphics;

import app.cleancode.convertion.PrimitiveConverter;
import app.cleancode.graphics.shaders.ShaderLoader;

public abstract class Node {
private float translateX, translateY, translateZ;

public Node() {
	translateX = translateY = translateZ = 0;
}

protected abstract void glDraw();

public final void draw() {
	ShaderLoader.setShaderUniform("translateX", Float.valueOf(translateX).intValue());
	System.out.println("translate x: "+Float.valueOf(translateX).intValue());
	ShaderLoader.setShaderUniform("translateY", Float.valueOf(translateY).intValue());
	ShaderLoader.setShaderUniform("translateZ", Float.valueOf(translateZ).intValue());
	glDraw();
}

public float getTranslateX() {
	return translateX;
}

public void setTranslateX(float translateX) {
	this.translateX = translateX;
}

public float getTranslateY() {
	return translateY;
}

public void setTranslateY(float translateY) {
	this.translateY = translateY;
}

public float getTranslateZ() {
	return translateZ;
}

public void setTranslateZ(float translateZ) {
	this.translateZ = translateZ;
}

}
