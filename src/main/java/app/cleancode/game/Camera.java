package app.cleancode.game;

public class Camera {
private float zoom;
private boolean changed;

public float getZoom() {
	return zoom;
}

public void setZoom(float zoom) {
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
