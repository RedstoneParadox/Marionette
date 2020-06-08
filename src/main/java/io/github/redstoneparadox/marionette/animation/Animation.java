package io.github.redstoneparadox.marionette.animation;

import it.unimi.dsi.fastutil.doubles.DoubleList;
import it.unimi.dsi.fastutil.doubles.DoubleLists;

import java.util.ArrayList;
import java.util.List;
import java.util.function.DoubleConsumer;

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
		private DoubleList steps = DoubleLists.singleton(0.0);
		private final List<Track> tracks = new ArrayList<>();
		private boolean creatingTrack = false;

		private Builder() { }

		public Builder startTrack(double initialValue) {
			steps = DoubleLists.singleton(initialValue);
			creatingTrack = true;
			return this;
		}

		public Builder addKeyFrame(double to, int ticks) throws Exception {
			if (!creatingTrack) throw new Exception("Attempted to add keyframe before starting track.");
			double slope = to/ticks;
			double previous = steps.getDouble(steps.size());

			for (int tick = 0; tick < ticks; tick += 1) {
				steps.add((double)tick * slope + previous);
			}

			return this;
		}

		public Builder completeTrack(DoubleConsumer setter) throws Exception {
			if (!creatingTrack) throw new Exception("Attempted to complete track before starting one.");
			tracks.add(new Track(setter, steps));
			creatingTrack = false;
			return this;
		}

		public Animation build() {
			return new Animation(tracks);
		}
	}
}
