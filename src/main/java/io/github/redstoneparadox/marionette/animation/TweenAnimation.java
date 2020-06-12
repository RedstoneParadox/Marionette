package io.github.redstoneparadox.marionette.animation;

import io.github.redstoneparadox.marionette.animation.sampling.*;

import java.util.Arrays;

public class TweenAnimation<T> extends AbstractAnimation<T> {
	private final SamplerFactory factory;
	private final SetterFunction<T> setterFunction;
	private Sampler sampler = null;
	private int time = 0;
	private int length = 0;
	private boolean playing = false;
	private boolean finished = true;

	private TweenAnimation(SamplerFactory factory, SetterFunction<T> setterFunction) {
		this.factory = factory;
		this.setterFunction = setterFunction;
	}

	public static <T> TweenAnimation<T> linear(SetterFunction<T> setter) {
		return new TweenAnimation<>(LinearSampler::new, setter);
	}

	public static <T> TweenAnimation<T> cubic(SetterFunction<T> setter) {
		return new TweenAnimation<>(CubicSampler::new, setter);
	}

	public static <T> TweenAnimation<T> sine(SetterFunction<T> setter) {
		return new TweenAnimation<>(SineSampler::new, setter);
	}

	public void tween(float from, float to, int ticks) {
		length = ticks;

		KeyFrame first = new KeyFrame(0, from);
		KeyFrame second = new KeyFrame(ticks, to);

		sampler = factory.create(Arrays.asList(first, second));
		finished = false;
		playing = true;
	}

	@Override
	public void step(T t) {
		if (playing) {
			float value = sampler.sample(time);
			setterFunction.set(t, value);
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
