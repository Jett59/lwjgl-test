package app.cleancode.game.penguin;

import app.cleancode.game.GameContext;
import app.cleancode.game.GameListener;
import app.cleancode.graphics.Obj3D;
import app.cleancode.graphics.zobj.ZobjLoader;

public class PenguinLoader implements GameListener {
private Obj3D penguin;
private boolean addedPenguin = false;

	@Override
	public void loop(GameContext ctx) {
		if(penguin == null) {
			penguin = ZobjLoader.load("penguin", 0);
		}else if(!addedPenguin) {
			ctx.addDrawable("penguin", penguin);
		}
	}

	@Override
	public void handleKey(int key, int action, GameContext ctx) {
		
	}

}
