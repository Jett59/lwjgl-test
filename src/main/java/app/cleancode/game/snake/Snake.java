package app.cleancode.game.snake;

import app.cleancode.shape.Group2D;
import app.cleancode.shape.Rectangle2D;

public class Snake extends Group2D {
private Segment first, last;

public void addNew() {
	if(last != null) {
		last = last.addNew();
		children.add(last);
	}else {
		last = new Segment(0, 0);
		children.add(last);
		if(first == null) {
			first = last;
		}
	}
}

public void move(float xAmount, float yAmount) {
	if(last != null) {
	Segment newLast = last.previous;
	if(last.previous != null) {
	last.previous.next = null;
	}
	last.previous = null;
	last.next = first;
	first.previous = last;
	first = last;
	last = newLast;
	first.x = first.next.x+xAmount;
	first.y = first.next.y+yAmount;
	}
}

	public static class Segment extends Rectangle2D {
		public static float WIDTH = 5, HEIGHT = 5;
		public static float[] color = new float[] {0.1f, 0.9f, 0.2f};
public Segment previous, next;

		public Segment(float x, float y) {
			super(color, x, y, WIDTH, HEIGHT);
		}
		public Segment addNew() {
			next = new Segment(x, y);
			next.previous = this;
			return next;
		}
	}
}
