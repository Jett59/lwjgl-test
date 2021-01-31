package app.cleancode.game.penguin;

import app.cleancode.game.GameContext;
import app.cleancode.game.GameListener;
import app.cleancode.shape.Cube3D;

public class PenguinLoader implements GameListener {
private Cube3D penguin;
private boolean addedPenguin = false;

	@Override
	public void loop(GameContext ctx) {
		if(penguin == null) {
			//penguin = ZobjLoader.load("penguin", 0);
			penguin = new Cube3D();
			penguin.x = -50;
			penguin.y = -50;
			penguin.z = -50;
			penguin.width = 100;
			penguin.height = 100;
			penguin.depth = 100;
		}else if(!addedPenguin) {
			ctx.addDrawable("penguin", penguin);
		}
	}

	@Override
	public void handleKey(int key, int action, GameContext ctx) {
		
	}

}
