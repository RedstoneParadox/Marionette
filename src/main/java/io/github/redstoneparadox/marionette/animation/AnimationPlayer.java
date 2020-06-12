package io.github.redstoneparadox.marionette.animation;

import io.github.redstoneparadox.marionette.animation.AbstractAnimation;

/**
 * Implementations of this interface should automatically
 * call {@link AbstractAnimation#step()} and be able to
 * play, pause, and stop all animations.
 */
public interface AnimationPlayer {
	void addAnimation(AbstractAnimation animation);

	/**
	 * Plays all animations handled
	 * by this {@link AnimationPlayer}.
	 */
	void playAll();

	/**
	 * Pauses all running animations handled
	 * by this {@link AnimationPlayer}.
	 */
	void pauseAll();

	/**
	 * Stops all animations handled by this
	 * {@link AnimationPlayer}.
	 */
	void stopAll();
}
