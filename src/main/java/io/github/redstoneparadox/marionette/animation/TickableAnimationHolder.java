package io.github.redstoneparadox.marionette.animation;

/**
 * Used for adding animations to an existing entity.
 *
 * @param <T> The type.
 */
public interface TickableAnimationHolder<T, U> extends AnimationHolder<T> {

	/**
	 * Called every tick on the client.
	 *
	 * @param u The object being ticked.
	 */
	void tick(U u);
}
