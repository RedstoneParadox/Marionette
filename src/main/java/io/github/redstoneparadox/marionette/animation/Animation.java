package io.github.redstoneparadox.marionette.animation;

import io.github.redstoneparadox.marionette.Marionette;
import io.github.redstoneparadox.marionette.animation.sampling.LinearSampler;
import io.github.redstoneparadox.marionette.animation.sampling.Sampler;
import io.github.redstoneparadox.marionette.animation.sampling.SamplerFactory;
import it.unimi.dsi.fastutil.floats.FloatConsumer;

import java.util.ArrayList;
import java.util.List;

public final class Animation {
	private final List<Track> tracks;
	private int tick = 0;
	private final int length;
	private final boolean repeat;

	public Animation(List<Track> tracks, boolean repeat) {
		this.tracks = tracks;
		this.repeat = repeat;

		int length = 0;

		for (Track track: tracks) {
			length = Math.max(length, track.length);
		}

		this.length = length;
	}

	public static Builder builder() {
		return new Builder();
	}

	public void step() {
		for (Track track: tracks) {
			track.seek(tick);
		}

		tick += 1;

		if (repeat && tick >= length) {
			reset();
		}
	}

	public void reset() {
		tick = 0;
	}

	private static class Track {
		private final FloatConsumer setter;
		private final Sampler sampler;
		private final int length;

		private Track(FloatConsumer setter, Sampler sampler, int length) {
			this.setter = setter;
			this.sampler = sampler;
			this.length = length;
		}

		void seek(float time) {
			float value = time < length ? sampler.sample(time) : sampler.sample(length);
			setter.accept(value);
		}
	}

	public static final class Builder {
		private List<KeyFrame> keyFrames = new ArrayList<>();
		private final List<Track> tracks = new ArrayList<>();
		private SamplerFactory factory = LinearSampler::new;
		private int length = 0;
		boolean creatingTrack = false;

		public Builder linearSampler() {
			factory = LinearSampler::new;
			return this;
		}

		public Builder startTrack(float initialValue) {
			if (!creatingTrack) {
				keyFrames = new ArrayList<>();
				keyFrames.add(new KeyFrame(0, initialValue));
				creatingTrack = true;
			} else {
				Marionette.LOGGER.error("Attempted to create new track before starting previous one.");
			}

			return this;
		}

		public Builder keyFrame(float value, int ticks) {
			if (!creatingTrack) {
				Marionette.LOGGER.error("Attempted to add key frame before starting track.");
				return this;
			}

			length += ticks;
			keyFrames.add(new KeyFrame(length, value));
			return this;
		}

		public Builder completeTrack(FloatConsumer setter) {
			if (!creatingTrack) {
				Marionette.LOGGER.error("Attempted to complete track before starting one.");
				return this;
			}

			tracks.add(new Track(setter, factory.create(keyFrames), length));
			creatingTrack = false;
			length = 0;
			return this;
		}

		public Animation build(boolean repeat) {
			return new Animation(tracks, repeat);
		}
	}

}
