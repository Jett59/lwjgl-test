package app.cleancode.game.penguin;

import app.cleancode.game.GameContext;
import app.cleancode.game.GameListener;
import app.cleancode.graphics.Obj3D;
import app.cleancode.graphics.zobj.ZobjLoader;

public class PenguinLoader implements GameListener {
private Obj3D penguin;

	@Override
	public void loop(GameContext ctx) {
		if(penguin == null) {
			ctx.addDrawable("Penguin", (penguin = ZobjLoader.load("penguin")));
			System.out.println("loaded penguin!");
			System.out.println(penguin.getXWidth());
		}
	}

	@Override
	public void handleKey(int key, int action, GameContext ctx) {
		
	}

}
