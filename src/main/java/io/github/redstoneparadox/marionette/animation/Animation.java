package io.github.redstoneparadox.marionette.animation;

import io.github.redstoneparadox.marionette.Marionette;
import io.github.redstoneparadox.marionette.animation.sampling.*;
import it.unimi.dsi.fastutil.floats.FloatConsumer;

import java.util.ArrayList;
import java.util.List;

/**
 * An {@link Animation} is a collection of tracks that
 * interpolate over float values. It is created using
 * {@link Animation.Builder}.
 */
public final class Animation extends AbstractAnimation {
	private final List<Track> tracks;
	private float time = 0.0f;
	private final int length;
	private final boolean repeat;
	private boolean playing = false;
	private boolean finished = true;
	private float speed = 1.0f;

	public Animation(List<Track> tracks, boolean repeat) {
		this.tracks = tracks;
		this.repeat = repeat;

		int length = 0;

		for (Track track: tracks) {
			length = Math.max(length, (track.length + track.startTime));
		}

		this.length = length;
	}

	/**
	 * Starts the animation, resuming where it
	 * was if it was paused.
	 */
	@Override
	public void play() {
		finished = false;
		playing = true;
	}

	/**
	 * Paused the animation.
	 */
	@Override
	public void pause() {
		playing = false;
	}

	/**
	 * Sends the animation back to the start.
	 */
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

	@Override
	public void step() {
		if (playing) {
			for (Track track: tracks) {
				track.seek(time);
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

	private static class Track {
		private final FloatConsumer setter;
		private final Sampler sampler;
		private final int length;
		private final int startTime;

		private Track(FloatConsumer setter, Sampler sampler, int length, int startTime) {
			this.setter = setter;
			this.sampler = sampler;
			this.length = length;
			this.startTime = startTime;
		}

		void seek(float time) {
			if (time >= startTime) {
				float value = time < length ? sampler.sample(time - startTime) : sampler.sample(length - startTime);
				setter.accept(value);
			}
		}
	}

	public static final class Builder {
		private List<KeyFrame> keyFrames = new ArrayList<>();
		private final List<Track> tracks = new ArrayList<>();
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
		 * @return The {@link Builder} for further modification.
		 */
		public Builder linearSampler() {
			factory = LinearSampler::new;
			return this;
		}

		/**
		 * <p>Sets the sampler to {@link CubicSampler}. Best for
		 * when you need smoother transitions between
		 * keyframes. </p>
		 *
		 * @return The {@link Builder} for further modification.
		 */
		public Builder cubicSampler() {
			factory = CubicSampler::new;
			return this;
		}

		/**
		 * <p>Sets the sampler to {@link SineSampler}. Best for
		 * when you need smoother transitions between keyframes
		 * with less smoothing than the {@link CubicSampler}.
		 * </p>
		 *
		 * @return The {@link Builder} for further modification.
		 */
		public Builder sineSampler() {
			factory = SineSampler::new;
			return this;
		}

		/**
		 * <p>Starts a new track. A track interpolates over
		 * a series of keyframes using sampling to produce
		 * a value for a given tick. Must be called before
		 * calling {@link Builder#keyFrame(float, int)} or
		 * {@link Builder#completeTrack(FloatConsumer)}</p>
		 *
		 * @param initialValue The initial value for this track.
		 * @return The {@link Builder} for further modification.
		 */
		public Builder startTrack(float initialValue) {
			return startTrack(initialValue, 0);
		}

		/**
		 * <p>Starts a new track. A track interpolates over
		 * a series of keyframes using sampling to produce
		 * a value for a given tick. Must be called before
		 * calling {@link Builder#keyFrame(float, int)} or
		 * {@link Builder#completeTrack(FloatConsumer)}</p>
		 *
		 * @param initialValue The initial value for this track.
		 * @param startTime The time this track should start at.
		 * @return The {@link Builder} for further modification.
		 */
		public Builder startTrack(float initialValue, int startTime) {
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
		 * {@link Builder#startTrack(float)} and before calling
		 * {@link Builder#completeTrack(FloatConsumer)}.</p>
		 *
		 * @param value The value to interpolate to for this keyframe
		 * @param ticks The time in ticks between this keyframe and the
		 *                    previous one.
		 * @return The {@link Builder} for further modification.
		 */
		public Builder keyFrame(float value, int ticks) {
			if (!creatingTrack) {
				Marionette.LOGGER.error("Attempted to add key frame before starting track.");
				return this;
			}

			this.length += ticks;
			this.keyFrames.add(new KeyFrame(length, value));
			return this;
		}

		/**
		 * <p>Completes the current track. Once calling this, it may
		 * not be called again until starting a new track by calling
		 * {@link Builder#startTrack(float)}. </p>
		 *
		 * @param setter A {@link FloatConsumer} which takes the
		 *               interpolated values from this track.
		 * @return The {@link Builder} for further modification.
		 */
		public Builder completeTrack(FloatConsumer setter) {
			if (!creatingTrack) {
				Marionette.LOGGER.error("Attempted to complete track before starting one.");
				return this;
			}

			this.tracks.add(new Track(setter, factory.create(keyFrames), length + startTime, startTime));
			this.creatingTrack = false;
			this.length = 0;
			return this;
		}

		/**
		 * Takes all the created tracks and turns them into an animation.
		 *
		 * @param animationPlayer The {@link AnimationPlayer} to play
		 *                        the {@link Animation}.
		 * @param repeat Whether or not this animation should repeat.
		 * @return The {@link Animation}
		 */
		public Animation build(AnimationPlayer animationPlayer, boolean repeat) {
			Animation animation = new Animation(this.tracks, repeat);
			animationPlayer.addAnimation(animation);
			return animation;
		}
	}
}
