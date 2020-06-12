package io.github.redstoneparadox.marionette.animation;

import io.github.redstoneparadox.marionette.animation.sampling.Sampler;
import io.github.redstoneparadox.marionette.animation.sampling.SamplerFactory;

import java.util.Arrays;

public class TweenAnimation<T> extends AbstractAnimation<T> {
	private final SamplerFactory factory;
	private final SetterFunction<T> setterFunction;
	private Sampler sampler = null;
	private int time = 0;
	private int length = 0;
	private boolean playing = false;
	private boolean finished = true;

	public TweenAnimation(SamplerFactory factory, SetterFunction<T> setterFunction) {
		this.factory = factory;
		this.setterFunction = setterFunction;
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
