package io.github.redstoneparadox.marionette.animation;

/**
 * <p>Used by {@link Animation} and {@link AbstractAnimation}
 * to use the interpolated values to set a field. Note that
 * nothing is stopping you from setting multiple fields in
 * the same {@link Setter}</p>
 *
 * @param <T> The type that is being animated.
 */
public interface Setter<T> {
	/**
	 * @param t The instance that is being animated.
	 * @param value The value to set.
	 */
	void set(T t, float value);
}
