package io.github.redstoneparadox.marionette.animation.sampling;

public final class KeyFrame {
	final int time;
	final float value;

	KeyFrame(int time, float value) {
		this.time = time;
		this.value = value;
	}

	public int getTime() {
		return time;
	}

	public float getValue() {
		return value;
	}
}
