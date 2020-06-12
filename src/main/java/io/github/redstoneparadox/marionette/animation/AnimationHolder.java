package io.github.redstoneparadox.marionette.animation;

import java.util.List;

public interface AnimationHolder<T> {
	List<AbstractAnimation<T>> getAnimations();
}
