package io.github.redstoneparadox.marionette.animation.statemachine;

import java.util.function.BiPredicate;

public final class StateTransition<T> {
	private final AnimationState state;
	private final BiPredicate<T, AnimationStateMachine> predicate;

	public StateTransition(AnimationState state, BiPredicate<T, AnimationStateMachine> predicate) {
		this.state = state;
		this.predicate = predicate;
	}

	public AnimationState getState() {
		return state;
	}

	public boolean shouldTransition(T t, AnimationStateMachine stateMachine) {
		return predicate.test(t, stateMachine);
	}
}
