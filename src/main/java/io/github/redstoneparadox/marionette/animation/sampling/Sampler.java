package io.github.redstoneparadox.marionette.animation.sampling;

import io.github.redstoneparadox.marionette.Marionette;
import net.minecraft.client.util.math.Vector3f;
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
	public final float sampleFloat(float time) {
		if (!(keyFrames.get(0) instanceof KeyFrame.FloatKeyFrame)) {
			Marionette.LOGGER.error("Attempted to sample float on sampler with vector keyframes!");
			return 0;
		}

		if (time <= 0.0f) {
			return ((KeyFrame.FloatKeyFrame)keyFrames.get(0)).getValue();
		}

		Pair<KeyFrame, KeyFrame> pair = getFrames(time);
		KeyFrame.FloatKeyFrame first = (KeyFrame.FloatKeyFrame) pair.getLeft();
		KeyFrame.FloatKeyFrame second = (KeyFrame.FloatKeyFrame) pair.getRight();
		float totalTime = second.getTime() - first.getTime();
		float deltaTime = time - first.getTime();

		return sample(totalTime, deltaTime, first.getValue(), second.getValue());
	}

	@ApiStatus.Internal
	public final Vector3f sampleVector(float time) {
		if (!(keyFrames.get(0) instanceof KeyFrame.VectorKeyFrame)) {
			Marionette.LOGGER.error("Attempted to sample float on sampler with vector keyframes!");
			return new Vector3f(0, 0, 0);
		}

		if (time <= 0.0f) {
			return ((KeyFrame.VectorKeyFrame)keyFrames.get(0)).getValue();
		}

		Pair<KeyFrame, KeyFrame> pair = getFrames(time);
		KeyFrame.VectorKeyFrame first = (KeyFrame.VectorKeyFrame) pair.getLeft();
		KeyFrame.VectorKeyFrame second = (KeyFrame.VectorKeyFrame) pair.getRight();
		float totalTime = second.getTime() - first.getTime();
		float deltaTime = time - first.getTime();

		float x = sample(totalTime, deltaTime, first.getValue().getX(), second.getValue().getX());
		float y = sample(totalTime, deltaTime, first.getValue().getY(), second.getValue().getY());
		float z = sample(totalTime, deltaTime, first.getValue().getZ(), second.getValue().getZ());

		return new Vector3f(0, 0, 0);
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
