package io.github.redstoneparadox.marionette.animation;

import io.github.redstoneparadox.marionette.Marionette;
import io.github.redstoneparadox.marionette.animation.sampling.*;

import java.util.ArrayList;
import java.util.List;

/**
 * An {@link Animation} is a collection of tracks that
 * interpolate over float values. It is created using
 * {@link Animation.Builder}.
 *
 * @param <T> The type that is being animated.
 */
public final class Animation<T> extends AbstractAnimation<T> {
	private final List<Track<T>> tracks;
	private final int length;
	private final boolean repeat;
	private float time = 0.0f;
	private float speed = 1.0f;
	private boolean playing = false;
	private boolean finished = true;

	public Animation(List<Track<T>> tracks, boolean repeat) {
		this.tracks = tracks;
		this.repeat = repeat;

		int length = 0;

		for (Track<T> track: tracks) {
			length = Math.max(length, (track.length + track.startTime));
		}

		this.length = length;
	}

	@Override
	public void step(T t) {
		if (playing) {
			for (Track<T> track: tracks) {
				track.interpolate(t, time);
			}

			time += speed;

			if (time >= length) {
				if (repeat) {
					time = 0;
				}
				else {
					stop();
				}
			}
		}
	}

	@Override
	public void play() {
		playing = true;
	}

	@Override
	public void pause() {
		playing = false;
	}

	@Override
	public void stop() {
		playing = false;
		finished = true;
		time = 0.0f;
	}

	@Override
	public boolean isFinished() {
		return finished;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}

	public float getSpeed() {
		return speed;
	}

	public void setTime(float time) {
		this.time = time;
	}

	public float getTime() {
		return time;
	}

	private static final class Track<T> {
		private final AnimationSetter<T> setter;
		private final Sampler sampler;
		private final int length;
		private final int startTime;

		public Track(AnimationSetter<T> setter, Sampler sampler, int length, int startTime) {
			this.setter = setter;
			this.sampler = sampler;
			this.length = length;
			this.startTime = startTime;
		}

		void interpolate(T t, float time) {
			if (time >= startTime) {
				float value = time < length ? sampler.sample(time - startTime) : sampler.sample(length - startTime);
				setter.set(t, value);
			}
		}
	}

	public static final class Builder<T> {
		private final List<Track<T>> tracks = new ArrayList<>();
		private List<KeyFrame> keyFrames;
		private SamplerFactory factory = LinearSampler::new;
		private int length = 0;
		private int startTime = 0;
		boolean creatingTrack = false;

		/**
		 * <p>Sets the sampler to {@link LinearSampler}. Best for
		 * when the track consists of constant motion such as when
		 * continuously spinning a
		 * {@link net.minecraft.client.model.ModelPart}. This is
		 * the default sampler.</p>
		 *
		 * @return The {@link Animation.Builder} for further modification.
		 */
		public Builder<T> linearSampler() {
			factory = LinearSampler::new;
			return this;
		}

		/**
		 * <p>Sets the sampler to {@link CubicSampler}. Best for
		 * when you need smoother transitions between
		 * keyframes. </p>
		 *
		 * @return The {@link Animation.Builder} for further modification.
		 */
		public Builder<T> cubicSampler() {
			factory = CubicSampler::new;
			return this;
		}

		/**
		 * <p>Sets the sampler to {@link SineSampler}. Best for
		 * when you need smoother transitions between keyframes
		 * with less smoothing than the {@link CubicSampler}.
		 * </p>
		 *
		 * @return The {@link Animation.Builder} for further modification.
		 */
		public Builder<T> sineSampler() {
			factory = SineSampler::new;
			return this;
		}

		/**
		 * <p>Sets the track to use a custom implementation
		 * of {@link Sampler}.</p>
		 *
		 * @param factory A factory function which supplies the
		 *                sampler.
		 * @return The {@link Animation.Builder} for further modification.
		 * @see SamplerFactory
		 */
		public Builder<T> customSampler(SamplerFactory factory) {
			this.factory = factory;
			return this;
		}

		/**
		 * <p>Starts a new track. A track interpolates over
		 * a series of keyframes using sampling to produce
		 * a value for a given tick. Must be called before
		 * calling {@link Animation.Builder#keyFrame(float, int)} or
		 * {@link Animation.Builder#completeTrack(AnimationSetter)}</p>
		 *
		 * @param initialValue The initial value for this track.
		 * @return The {@link Animation.Builder} for further modification.
		 */
		public Builder<T> startTrack(float initialValue) {
			return startTrack(initialValue, 0);
		}

		/**
		 * <p>Starts a new track. A track interpolates over
		 * a series of keyframes using sampling to produce
		 * a value for a given tick. Must be called before
		 * calling {@link Animation.Builder#keyFrame(float, int)} or
		 * {@link Animation.Builder#completeTrack(AnimationSetter)}</p>
		 *
		 * @param initialValue The initial value for this track.
		 * @param startTime The time this track should start at.
		 * @return The {@link Animation.Builder} for further modification.
		 */
		public Builder<T> startTrack(float initialValue, int startTime) {
			if (!creatingTrack) {
				this.keyFrames = new ArrayList<>();
				this.keyFrames.add(new KeyFrame(0, initialValue));
				this.creatingTrack = true;
				this.startTime = startTime;
			} else {
				Marionette.LOGGER.error("Attempted to create new track before completing the previous one.");
			}

			return this;
		}

		/**
		 * <p>Adds a new keyframe to the current track. May be called
		 * any number of times after calling
		 * {@link Animation.Builder#startTrack(float)} and before calling
		 * {@link Animation.Builder#completeTrack(AnimationSetter)}.</p>
		 *
		 * @param value The value to interpolate to for this keyframe
		 * @param ticks The time in ticks between this keyframe and the
		 *                    previous one.
		 * @return The {@link Animation.Builder} for further modification.
		 */
		public Builder<T> keyFrame(float value, int ticks) {
			if (!creatingTrack) {
				Marionette.LOGGER.error("Attempted to add key frame before starting track.");
				return this;
			}

			this.length += ticks;
			this.keyFrames.add(new KeyFrame(length, value));
			return this;
		}

		public Builder<T> completeTrack(AnimationSetter<T> setter) {
			if (!creatingTrack) {
				Marionette.LOGGER.error("Attempted to complete track before starting one.");
				return this;
			}

			tracks.add(new Track<>(setter, factory.create(keyFrames), length, startTime));
			this.creatingTrack = false;
			this.length = 0;
			return this;
		}

		public Animation<T> build(boolean repeat) {
			return new Animation<>(tracks, repeat);
		}
	}
}
