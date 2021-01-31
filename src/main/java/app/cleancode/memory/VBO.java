package app.cleancode.memory;

public class VBO {
private final int elementSize;
private int id;

public VBO(int elementSize, int id) {
	this.elementSize = elementSize;
	this.id = id;
}

public int getElementSize() {
	return elementSize;
}

public int getId() {
	return id;
}
}
