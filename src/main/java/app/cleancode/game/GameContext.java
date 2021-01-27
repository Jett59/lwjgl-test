package app.cleancode.game;

import java.util.function.BiConsumer;
import java.util.function.Function;

import app.cleancode.graphics.Drawable;

public class GameContext {
private final Function<String, Drawable> getDrawable;
private final BiConsumer<String, Drawable> addDrawable;

@SuppressWarnings("unchecked")
public <T extends Drawable> T getDrawable(String name) {
	Drawable result = getDrawable.apply(name);
		return (T)result;
}

public void addDrawable(String name, Drawable drawable) {
	addDrawable.accept(name, drawable);
}

public GameContext(Function<String, Drawable> getDrawable, BiConsumer<String, Drawable> addDrawable) {
	this.getDrawable = getDrawable;
	this.addDrawable = addDrawable;
}
}
