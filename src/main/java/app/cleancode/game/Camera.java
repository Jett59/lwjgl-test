package app.cleancode.game;

public class Camera {
private int zoom;
private boolean changed;

public int getZoom() {
	return zoom;
}

public void setZoom(int zoom) {
	this.zoom = zoom;
	this.changed = true;
}

public boolean isChanged() {
	return changed;
}

public void setChanged(boolean changed) {
	this.changed = changed;
}
}
