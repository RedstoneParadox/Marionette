package io.github.redstoneparadox.marionette.animation.statemachine;

import io.github.redstoneparadox.marionette.animation.AbstractAnimation;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public final class AnimationState {
	private final List<AbstractAnimation> animations = new ArrayList<>();
	private final Set<AnimationState> states = new HashSet<>();

	public void addAnimation(AbstractAnimation animation) {
		animations.add(animation);
	}

	public void linkState(AnimationState state) {
		states.add(state);
	}
}
