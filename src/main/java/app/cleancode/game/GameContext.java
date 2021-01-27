package app.cleancode.game;

import java.util.function.BiConsumer;
import java.util.function.Function;

import app.cleancode.shape.Shape3D;

public class GameContext {
private final Function<String, Shape3D> getShape;
private final BiConsumer<String, Shape3D> addShape;

public Shape3D getShape(String name) {
	return getShape.apply(name);
}

public void addShape(String name, Shape3D shape) {
	addShape.accept(name, shape);
}

public GameContext(Function<String, Shape3D> getShape, BiConsumer<String, Shape3D> addShape) {
	this.getShape = getShape;
	this.addShape = addShape;
}
}
