package io.github.redstoneparadox.marionette.animation.statemachine;

import org.jetbrains.annotations.ApiStatus;

@ApiStatus.Experimental
public final class AnimationStateMachine<T> {
	private AnimationState<T> currentState;

	public AnimationStateMachine(AnimationState<T> currentState) {
		this.currentState = currentState;
	}

	public void tick(T t) {
		currentState = currentState.tick(t, this);
	}
}
