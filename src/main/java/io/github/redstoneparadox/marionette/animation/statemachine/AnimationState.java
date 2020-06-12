package io.github.redstoneparadox.marionette.animation.statemachine;

import io.github.redstoneparadox.marionette.animation.AbstractAnimation;
import org.jetbrains.annotations.ApiStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiPredicate;

@ApiStatus.Experimental
public final class AnimationState<T> {
	private final List<AbstractAnimation> animations = new ArrayList<>();
	private final List<StateTransition<T>> transitions = new ArrayList<>();

	public void addAnimation(AbstractAnimation animation) {
		animations.add(animation);
	}

	public void addTransition(StateTransition<T> transition) {
		transitions.add(transition);
	}

	public void addTransition(AnimationState<T> state, BiPredicate<T, AnimationStateMachine<T>> predicate) {
		transitions.add(new StateTransition<>(state, predicate));
	}

	public AnimationState<T> tick(T t, AnimationStateMachine<T> stateMachine) {
		for (AbstractAnimation animation: animations) {
			animation.step();
		}
		for (StateTransition<T> transition: transitions) {
			if (transition.shouldTransition(t, stateMachine)) {
				return transition.getState();
			}
		}
		return this;
	}
}
