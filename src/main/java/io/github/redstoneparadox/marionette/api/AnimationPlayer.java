package io.github.redstoneparadox.marionette.api;

import io.github.redstoneparadox.marionette.animation.Animation;

/**
 * Implementations of this interface are used to automatically
 * call {@link Animation#step()} instead of the end user
 * calling it manually.
 */
public interface AnimationPlayer {

	void addAnimation(Animation animation);
}
