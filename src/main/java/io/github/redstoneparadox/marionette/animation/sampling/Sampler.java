package io.github.redstoneparadox.marionette.animation.sampling;

import io.github.redstoneparadox.marionette.animation.KeyFrame;
import net.minecraft.util.Pair;
import org.jetbrains.annotations.ApiStatus;

import java.util.List;

/**
 * A sampler implements a function used to interpolate
 * between two given keyframes in a series of keyframes.
 *
 * See {@link LinearSampler}, {@link CubicSampler}, and
 * {@link SineSampler} for examples.
 */
public abstract class Sampler {
	private final List<KeyFrame> keyFrames;

	protected Sampler(List<KeyFrame> keyFrames) {
		this.keyFrames = keyFrames;
	}

	/**
	 * Called to sample a value between two keyframes.
	 *
	 * @param totalTime The time between the two keyframes.
	 * @param deltaTime The time after the first keyframe to sample at.
	 * @param first The first keyframe value.
	 * @param second The second keyframe value.
	 * @return The interpolated value between the two keyframes.
	 */
	abstract float sample(float totalTime, float deltaTime, float first, float second);

	@ApiStatus.Internal
	public final float sample(float time) {
		if (time <= 0.0f) {
			return keyFrames.get(0).getValue();
		}

		Pair<KeyFrame, KeyFrame> pair = getFrames(time);
		KeyFrame first = pair.getLeft();
		KeyFrame second = pair.getRight();

		float totalTime = second.getTime() - first.getTime();
		float deltaTime = time - first.getTime();

		return sample(totalTime, deltaTime, first.getValue(), second.getValue());
	}

	private Pair<KeyFrame, KeyFrame> getFrames(float time) {
		int frameIndex = 0;


		for (int index = 0; index < keyFrames.size(); index += 1) {
			if (keyFrames.get(index).getTime() >= time) {
				frameIndex = index;
				break;
			}
		}

		return new Pair<>(keyFrames.get(frameIndex - 1), keyFrames.get(frameIndex));
	}
}
