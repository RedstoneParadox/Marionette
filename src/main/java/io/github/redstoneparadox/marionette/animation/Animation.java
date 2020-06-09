package io.github.redstoneparadox.marionette.animation;

import io.github.redstoneparadox.marionette.Marionette;
import it.unimi.dsi.fastutil.doubles.DoubleArrayList;
import it.unimi.dsi.fastutil.doubles.DoubleList;
import it.unimi.dsi.fastutil.doubles.DoubleLists;

import java.util.ArrayList;
import java.util.List;
import java.util.function.DoubleConsumer;
import java.util.logging.LogManager;

public final class Animation {
	private final List<Track> tracks;
	private int tick = 0;

	public Animation(List<Track> tracks) {
		this.tracks = tracks;
	}

	public static Builder builder() {
		return new Builder();
	}

	public void step() {
		for (Track track: tracks) {
			track.to(tick);
		}
		tick += 1;
	}

	public void reset() {
		tick = 0;
	}

	private static class Track {
		private final DoubleConsumer setter;
		private final DoubleList steps;

		Track(DoubleConsumer setter, DoubleList steps) {
			this.setter = setter;
			this.steps = steps;
		}

		void to(int step) {
			if (step < steps.size()) setter.accept(steps.getDouble(step));
		}
	}

	public static final class Builder {
		private DoubleList steps = new DoubleArrayList();
		private final List<Track> tracks = new ArrayList<>();
		private boolean creatingTrack = false;

		private Builder() { }

		public Builder startTrack(double initialValue) {
			if (!creatingTrack) {
				steps = new DoubleArrayList();
				steps.add(initialValue);
				creatingTrack = true;
			}
			else {
				Marionette.LOGGER.error("Attempted to create new track before starting previous one.");
			}

			return this;
		}

		public Builder addKeyFrame(double to, int ticks) {
			if (!creatingTrack) {
				Marionette.LOGGER.error("Attempted to add key frame before starting track.");
				return this;
			}
			double slope = to/ticks;
			double previous = steps.getDouble(steps.size() - 1);

			for (int tick = 0; tick < ticks; tick += 1) {
				steps.add((double)tick * slope + previous);
			}

			return this;
		}

		public Builder completeTrack(DoubleConsumer setter) {
			if (!creatingTrack) {
				Marionette.LOGGER.error("Attempted to complete track before starting one.");
				return this;
			}
			tracks.add(new Track(setter, steps));
			creatingTrack = false;
			return this;
		}

		public Animation build() {
			return new Animation(tracks);
		}
	}
}
