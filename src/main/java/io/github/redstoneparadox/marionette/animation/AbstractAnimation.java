package io.github.redstoneparadox.marionette.animation;

public abstract class AbstractAnimation<T> {
	public abstract void step(T t);

	public abstract void play();

	public abstract void pause();

	public abstract void stop();

	public abstract boolean isFinished();
}
