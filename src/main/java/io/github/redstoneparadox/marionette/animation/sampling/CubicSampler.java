package io.github.redstoneparadox.marionette.animation.sampling;

import java.util.List;

/**
 * Sampling using a cubic-piecewise function.
 */
public class CubicSampler extends Sampler {
	public CubicSampler(List<KeyFrame.FloatKeyFrame> keyFrames) {
		super(keyFrames);
	}

	@Override
	float sample(float totalTime, float deltaTime, float first, float second) {
		float x = deltaTime/totalTime;

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

		return y * (second - first) + first;
	}
}
