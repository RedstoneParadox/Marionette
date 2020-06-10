package io.github.redstoneparadox.marionette.api;

import io.github.redstoneparadox.marionette.animation.AbstractAnimation;

/**
 * Implementations of this interface are used to automatically
 * call {@link AbstractAnimation#step()} instead of the end user
 * calling it manually.
 */
public interface AnimationPlayer {

	void addAnimation(AbstractAnimation animation);
}
