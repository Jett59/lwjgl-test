package app.cleancode.game.camera;

import app.cleancode.game.GameContext;
import app.cleancode.game.GameListener;

import static org.lwjgl.glfw.GLFW.*;

public class CameraZoomer implements GameListener {
private boolean zoomingIn;

	@Override
	public void loop(GameContext ctx) {
		if(zoomingIn) {
			ctx.getCamera().setZoom(ctx.getCamera().getZoom()+0.1f);
		}else if(ctx.getCamera().getZoom() > 1) {
			ctx.getCamera().setZoom(ctx.getCamera().getZoom()-0.1f);
		}
	}

	@Override
	public void handleKey(int key, int action, GameContext ctx) {
		if(key == GLFW_KEY_X) {
			if(action == GLFW_PRESS) {
				zoomingIn = true;
			}else if(action == GLFW_RELEASE) {
				zoomingIn = false;
			}
		}
	}

}
