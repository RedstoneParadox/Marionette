package io.github.redstoneparadox.marionette.animation.sampling;

import java.util.List;

/**
 * Sampling using a sine function.
 */
public class SineSampler extends Sampler {
	public SineSampler(List<KeyFrame> keyFrames) {
		super(keyFrames);
	}

	@Override
	float sample(float totalTime, float deltaTime, float first, float second) {
		float x = deltaTime/totalTime;

		if (x < 0 || x > 1){
			throw new IllegalStateException("Expected x value between 0 and 1, instead got " + x + ".");
		}

		float y = (float) ((Math.sin(Math.PI * x - Math.PI/2) + 1)/2);
		return y * (second - first) + first;
	}
}
