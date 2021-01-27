package app.cleancode.shape;

import java.util.ArrayList;
import java.util.List;

public class Group2D extends Shape2D {
public final List<Shape2D> children;

public Group2D(List<Shape2D> children) {
	this.children = children;
}
public Group2D() {
	this(new ArrayList<>());
}

	@Override
	public void draw() {
		for(Shape2D shape : children) {
			shape.draw();
		}
	}

}
