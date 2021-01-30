package app.cleancode.memory;

import java.nio.FloatBuffer;

public class VBO {
private final int elementSize;
private FloatBuffer buffer;

public VBO(int elementSize, FloatBuffer buffer) {
	this.elementSize = elementSize;
	this.buffer = buffer;
}

public int getElementSize() {
	return elementSize;
}

public FloatBuffer getBuffer() {
	return buffer;
}
}
