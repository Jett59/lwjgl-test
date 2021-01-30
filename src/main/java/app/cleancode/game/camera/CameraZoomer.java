package app.cleancode.game.camera;

import app.cleancode.game.GameContext;
import app.cleancode.game.GameListener;

import static org.lwjgl.glfw.GLFW.*;

public class CameraZoomer implements GameListener {

	@Override
	public void loop(GameContext ctx) {
		
	}

	@Override
	public void handleKey(int key, int action, GameContext ctx) {
		if(key == GLFW_KEY_SPACE) {
			if(action == GLFW_RELEASE) {
				ctx.getCamera().setZoom(ctx.getCamera().getZoom()-1);
			}
		}else if(key == GLFW_KEY_X) {
			if(action == GLFW_RELEASE) {
				ctx.getCamera().setZoom(ctx.getCamera().getZoom()+1);
			}
		}
	}

}
