package io.github.redstoneparadox.marionette.animation;

import io.github.redstoneparadox.marionette.animation.sampling.*;
import it.unimi.dsi.fastutil.floats.FloatConsumer;

import java.util.Arrays;

public final class TweenAnimation extends AbstractAnimation {
	private final SamplerFactory factory;
	private final FloatConsumer setter;
	private Sampler sampler = null;
	private int time = 0;
	private int length = 0;
	private boolean playing = false;
	private boolean finished = true;

	public TweenAnimation(SamplerFactory factory, FloatConsumer setter) {
		this.factory = factory;
		this.setter = setter;
	}

	public static TweenAnimation linear(FloatConsumer setter) {
		return new TweenAnimation(LinearSampler::new, setter);
	}

	public static TweenAnimation cubic(FloatConsumer setter) {
		return new TweenAnimation(CubicSampler::new, setter);
	}

	public static TweenAnimation sine(FloatConsumer consumer) {
		return new TweenAnimation(SineSampler::new, consumer);
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
	public void step() {
		if (playing) {
			float value = sampler.sample(time);
			setter.accept(value);

			time += 1;

			if (time >= length) {
				stop();
			}
		}
	}

	@Override
	public void play() {
		finished = false;
		playing = true;
	}

	@Override
	public void pause() {
		playing = false;
	}

	@Override
	public void stop() {
		playing = false;
		finished = true;
		time = 0;
	}

	@Override
	public boolean isFinished() {
		return finished;
	}
}
