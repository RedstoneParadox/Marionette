package io.github.redstoneparadox.marionette.animation.sampling;

import io.github.redstoneparadox.marionette.animation.KeyFrame;

import java.util.List;

/**
 * Sampling using a sine function.
 */
public class SineSampler extends Sampler {
	public SineSampler(List<KeyFrame> keyFrames) {
		super(keyFrames);
	}

	@Override
	public float sample(float time) {
		if (time <= 0.0f) {
			return keyFrames.get(0).getValue();
		}

		KeyFramePair pair = getFrames(time);
		KeyFrame first = pair.first;
		KeyFrame second = pair.second;
		float x = (time - first.getTime())/(second.getTime() - first.getTime());

		if (x < 0 || x > 1){
			throw new IllegalStateException("Expected x value between 0 and 1, instead got " + x + ".");
		}

		float y = (float) ((Math.sin(Math.PI * x - Math.PI/2) + 1)/2);
		float delta = second.getValue() - first.getValue();
		return (y * delta) + first.getValue();
	}
}
