package io.github.redstoneparadox.marionette.animation.sampling;

import io.github.redstoneparadox.marionette.animation.KeyFrame;

import java.util.List;

/**
 * Sampling using a cubic-piecewise function.
 */
public class CubicSampler extends Sampler {
	public CubicSampler(List<KeyFrame> keyFrames) {
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

		float y;
		if (x >= 0.0f && x < 0.5f) {
			y = (float) (4 * Math.pow(x, 3));
		}
		else if (x >= 0.5f && x <= 1.0f) {
			y = (float) (4 * Math.pow(x - 1, 3) + 1);
		}
		else {
			throw new IllegalStateException("Expected x value between 0 and 1, instead got " + x + ".");
		}

		float delta = second.getValue() - first.getValue();
		return (y * delta) + first.getValue();
	}
}
