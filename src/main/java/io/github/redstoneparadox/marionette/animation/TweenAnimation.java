package io.github.redstoneparadox.marionette.animation;

import io.github.redstoneparadox.marionette.animation.sampling.*;
import io.github.redstoneparadox.marionette.animation.setter.AnimatedFloatSetter;

import java.util.Arrays;

/**
 * <p>Used for dynamically setting up animations between
 * two values. While {@link Animation} can be used in
 * this matter, it is designed for more complex animations
 * that are set up ahead of time.</p>
 *
 * @param <T> The type that is being animated.
 */
public class TweenAnimation<T> extends AbstractAnimation<T> {
	private final SamplerFactory factory;
	private final AnimatedFloatSetter<T> setter;
	private Sampler sampler = null;
	private int time = 0;
	private int length = 0;
	private boolean playing = false;
	private boolean finished = true;

	private TweenAnimation(SamplerFactory factory, AnimatedFloatSetter<T> setter) {
		this.factory = factory;
		this.setter = setter;
	}

	public static <T> TweenAnimation<T> linear(AnimatedFloatSetter<T> setter) {
		return new TweenAnimation<>(LinearSampler::new, setter);
	}

	public static <T> TweenAnimation<T> cubic(AnimatedFloatSetter<T> setter) {
		return new TweenAnimation<>(CubicSampler::new, setter);
	}

	public static <T> TweenAnimation<T> sine(AnimatedFloatSetter<T> setter) {
		return new TweenAnimation<>(SineSampler::new, setter);
	}

	public void tween(float from, float to, int ticks) {
		length = ticks;

		KeyFrame.FloatKeyFrame first = new KeyFrame.FloatKeyFrame(0, from);
		KeyFrame.FloatKeyFrame second = new KeyFrame.FloatKeyFrame(ticks, to);

		sampler = factory.create(Arrays.asList(first, second));
		finished = false;
		playing = true;
	}

	@Override
	public void step(T t) {
		if (playing) {
			float value = sampler.sampleFloat(time);
			setter.set(t, value);
			time += 1;

			if (time >= length) {
				stop();
			}
		}
	}

	@Override
	public void play() {
		playing = true;
	}

	@Override
	public void pause() {
		playing = false;
	}

	@Override
	public void stop() {
		finished = true;
		playing = false;
		time = 0;
		length = 0;
	}

	@Override
	public boolean isFinished() {
		return finished;
	}
}
