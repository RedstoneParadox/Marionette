package io.github.redstoneparadox.marionette.animation.statemachine;

import org.jetbrains.annotations.ApiStatus;

import java.util.function.BiPredicate;

@ApiStatus.Experimental
public final class StateTransition<T> {
	private final AnimationState<T> state;
	private final BiPredicate<T, AnimationStateMachine<T>> predicate;

	public StateTransition(AnimationState<T> state, BiPredicate<T, AnimationStateMachine<T>> predicate) {
		this.state = state;
		this.predicate = predicate;
	}

	public AnimationState<T> getState() {
		return state;
	}

	public boolean shouldTransition(T t, AnimationStateMachine<T> stateMachine) {
		return predicate.test(t, stateMachine);
	}
}
