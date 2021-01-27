package app.cleancode.game.snake;

import app.cleancode.game.GameContext;
import app.cleancode.game.GameListener;
import static org.lwjgl.glfw.GLFW.*;

public class SnakeController implements GameListener {
	public static float SPEED = 10;
private Snake snake;
private float xVelocity, yVelocity;

	@Override
	public void loop(GameContext ctx) {
		if(snake == null) {
			snake = new Snake();
			snake.addNew();
			snake.addNew();
			snake.addNew();
			ctx.addShape("snake", snake);
		}
		snake.move(xVelocity, yVelocity);
	}

	@Override
	public void handleKey(int key, int action, GameContext ctx) {
		if(action == GLFW_PRESS) {
			System.out.println("key pressed");
		if(key == GLFW_KEY_UP) {
			yVelocity = SPEED*-1;
			xVelocity = 0;
		}else if(key == GLFW_KEY_DOWN) {
			yVelocity = SPEED;
			xVelocity = 0;
		}else if(key == GLFW_KEY_LEFT) {
			xVelocity = SPEED*-1;
			yVelocity = 0;
		}else if(key == GLFW_KEY_RIGHT) {
			xVelocity = SPEED;
			yVelocity = 0;
		}
	}
		System.out.printf("%f,  %f\n", xVelocity, yVelocity);
	}
}
