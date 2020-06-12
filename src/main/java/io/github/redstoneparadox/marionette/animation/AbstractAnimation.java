package io.github.redstoneparadox.marionette.animation;

/**
 * @param <T> The type that is being animated.
 */
public abstract class AbstractAnimation<T> {
	/**
	 * Moved this animation one step forward.
	 *
	 * @param t The instance that is being animated.
	 */
	public abstract void step(T t);

	public abstract void play();

	public abstract void pause();

	public abstract void stop();

	public abstract boolean isFinished();
}
