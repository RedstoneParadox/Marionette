package io.github.redstoneparadox.marionette.animation;

/**
 * Used for adding animations to an existing entity.
 *
 * @param <T> The type.
 */
public interface TickableAnimationHolder<T> extends AnimationHolder<T> {

	/**
	 * Called every tick on the client.
	 *
	 * @param t The object being ticked.
	 */
	void tick(T t);
}
