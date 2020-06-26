package io.github.redstoneparadox.marionette.animation;

import io.github.redstoneparadox.marionette.animation.sampling.Sampler;
import io.github.redstoneparadox.marionette.animation.setter.AnimatedFloatSetter;
import io.github.redstoneparadox.marionette.animation.setter.AnimatedVectorSetter;
import net.minecraft.client.util.math.Vector3f;

public abstract class AnimationTrack<T, S> {
	protected final S setter;
	protected final Sampler sampler;
	protected final int length;
	protected final int startTime;

	private AnimationTrack(S setter, Sampler sampler, int length, int startTime) {
		this.setter = setter;
		this.sampler = sampler;
		this.length = length;
		this.startTime = startTime;
	}

	void seek(T t, float time) {
		if (time < startTime) interpolate(t, time);
	}

	abstract void interpolate(T t, float time);

	public static class FloatTrack<T> extends AnimationTrack<T, AnimatedFloatSetter<T>> {
		private FloatTrack(AnimatedFloatSetter<T> setter, Sampler sampler, int length, int startTime) {
			super(setter, sampler, length, startTime);
		}

		@Override
		void interpolate(T t, float time) {
			float value = time < length ? sampler.sampleFloat(time - startTime) : sampler.sampleFloat(length - startTime);
			setter.set(t, value);
		}
	}

	public static class VectorTrack<T> extends AnimationTrack<T, AnimatedVectorSetter<T>> {
		private VectorTrack(AnimatedVectorSetter<T> setter, Sampler sampler, int length, int startTime) {
			super(setter, sampler, length, startTime);
		}

		@Override
		void interpolate(T t, float time) {
			Vector3f value = time < length ? sampler.sampleVector(time - startTime) : sampler.sampleVector(length - startTime);
			setter.set(t, value);
		}
	}
}
