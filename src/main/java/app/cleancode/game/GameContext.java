package app.cleancode.game;

import java.util.function.BiConsumer;
import java.util.function.Function;

import app.cleancode.shape.Shape2D;

public class GameContext {
private final Function<String, Shape2D> getShape;
private final BiConsumer<String, Shape2D> addShape;

public Shape2D getShape(String name) {
	return getShape.apply(name);
}

public void addShape(String name, Shape2D shape) {
	addShape.accept(name, shape);
}

public GameContext(Function<String, Shape2D> getShape, BiConsumer<String, Shape2D> addShape) {
	this.getShape = getShape;
	this.addShape = addShape;
}
}
