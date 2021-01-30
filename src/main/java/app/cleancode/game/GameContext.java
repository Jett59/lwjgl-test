package app.cleancode.game;

import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;

import app.cleancode.graphics.Node;

public class GameContext {
private final Function<String, Node> getDrawable;
private final BiConsumer<String, Node> addDrawable;
private Supplier<Camera> getCamera;

@SuppressWarnings("unchecked")
public <T extends Node> T getDrawable(String name) {
	Node result = getDrawable.apply(name);
		return (T)result;
}

public void addDrawable(String name, Node drawable) {
	addDrawable.accept(name, drawable);
}

public Camera getCamera() {
	return getCamera.get();
}

public GameContext(Function<String, Node> getDrawable, BiConsumer<String, Node> addDrawable, Supplier<Camera> getCamera) {
	this.getDrawable = getDrawable;
	this.addDrawable = addDrawable;
	this.getCamera = getCamera;
}
}
