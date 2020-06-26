package io.github.redstoneparadox.marionette.animation.sampling;

import io.github.redstoneparadox.marionette.animation.KeyFrame;
import java.util.List;

public class LinearSampler extends Sampler {
	public LinearSampler(List<KeyFrame> keyFrames) {
		super(keyFrames);
	}

	@Override
	float sample(float totalTime, float deltaTime, float first, float second) {
		float slope = (second - first)/totalTime;
		return slope * deltaTime + first;
	}
}
