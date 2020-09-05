package io.github.redstoneparadox.marionette.animation;

/**
 * DO NOT IMPLEMENT. For internal implementation only.
 */
public interface CompositeAnimationHolder<T> extends AnimationHolder<T> {

	/**
	 * Used for adding your own animation holder to an existing entity.
	 *
	 * @param animationHolder The animation holder to add.
	 */
	void addAnimationHolder(AnimationHolder<T> animationHolder);
}
