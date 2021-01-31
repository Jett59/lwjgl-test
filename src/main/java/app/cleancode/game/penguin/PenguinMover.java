package app.cleancode.game.penguin;

import app.cleancode.game.GameContext;
import app.cleancode.game.GameListener;
import app.cleancode.graphics.Node;
import static org.lwjgl.glfw.GLFW.*;

public class PenguinMover implements GameListener {
	public static float SPEED = 1;

private Node penguin;
private float xVelocity, yVelocity;

	@Override
	public void loop(GameContext ctx) {
		if(penguin == null) {
			penguin = ctx.getDrawable("penguin");
		}else {
			penguin.setTranslateX(penguin.getTranslateX()+xVelocity);
			penguin.setTranslateY(penguin.getTranslateY()+yVelocity);
		}
	}

	@Override
	public void handleKey(int key, int action, GameContext ctx) {
		if(key == GLFW_KEY_LEFT) {
			if(action == GLFW_PRESS) {
				xVelocity = SPEED*-1;
			}else if(action == GLFW_RELEASE) {
				xVelocity = 0;
			}
		}else if(key == GLFW_KEY_RIGHT) {
			if(action == GLFW_PRESS) {
				xVelocity = SPEED*1;
			}else if(action == GLFW_RELEASE) {
				xVelocity = 0;
			}
		}else if(key == GLFW_KEY_UP) {
			if(action == GLFW_PRESS) {
				yVelocity = SPEED*1;
			}else if(action == GLFW_RELEASE) {
				yVelocity = 0;
			}
		}else if(key == GLFW_KEY_DOWN) {
			if(action == GLFW_PRESS) {
				yVelocity = SPEED*-1;
			}else if(action == GLFW_RELEASE) {
				yVelocity = 0;
			}
		}
		System.out.println(penguin.getTranslateX());
		System.out.println(penguin.getTranslateY());
	}

}
