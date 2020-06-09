package io.github.redstoneparadox.marionette.animation.sampling;

import io.github.redstoneparadox.marionette.animation.KeyFrame;
import java.util.List;

public class LinearSampler extends Sampler {
	public LinearSampler(List<KeyFrame> keyFrames) {
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

		float slope = (second.getValue() - first.getValue())/(second.getTime() - first.getTime());

		return (slope * (time - first.getTime())) + first.getValue();
	}
}
