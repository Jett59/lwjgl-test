package app.cleancode.game;

public interface GameListener {
public void loop(GameContext ctx);
public void handleKey(int key, int action, GameContext ctx);
}
