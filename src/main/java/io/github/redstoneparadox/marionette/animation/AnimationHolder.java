package io.github.redstoneparadox.marionette.animation;

import java.util.List;

/**
 * Used to hold animations; called internally
 * to retreive those animations.
 *
 * @param <T> The type that is being animated.
 */
public interface AnimationHolder<T> {
	List<AbstractAnimation<T>> getAnimations();
}
