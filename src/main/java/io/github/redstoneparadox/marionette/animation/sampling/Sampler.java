package io.github.redstoneparadox.marionette.animation.sampling;

import io.github.redstoneparadox.marionette.animation.KeyFrame;
import java.util.List;

/**
 * Used to interpolate over a series of key frames.
 */
public abstract class Sampler {
	protected final List<KeyFrame> keyFrames;

	protected Sampler(List<KeyFrame> keyFrames) {
		this.keyFrames = keyFrames;
	}

	public abstract float sample(float time);

	protected KeyFramePair getFrames(float time) {
		int frameIndex = 0;


		for (int index = 0; index < keyFrames.size(); index += 1) {
			if (keyFrames.get(index).getTime() >= time) {
				frameIndex = index;
				break;
			}
		}

		return new KeyFramePair(keyFrames.get(frameIndex - 1), keyFrames.get(frameIndex));
	}

	protected static class KeyFramePair {
		final KeyFrame first;
		final KeyFrame second;

		public KeyFramePair(KeyFrame first, KeyFrame second) {
			this.first = first;
			this.second = second;
		}
	}
}
