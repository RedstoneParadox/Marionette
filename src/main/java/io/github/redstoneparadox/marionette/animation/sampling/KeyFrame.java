package io.github.redstoneparadox.marionette.animation.sampling;

import net.minecraft.client.util.math.Vector3f;
import org.jetbrains.annotations.ApiStatus;

public abstract class KeyFrame {
	private KeyFrame() {}

	public abstract int getTime();

	@ApiStatus.Internal
	public static final class FloatKeyFrame extends KeyFrame {
		final int time;
		final float value;

		public FloatKeyFrame(int time, float value) {
			this.time = time;
			this.value = value;
		}

		@Override
		public int getTime() {
			return time;
		}

		public float getValue() {
			return value;
		}
	}

	@ApiStatus.Internal
	public static final class VectorKeyFrame extends KeyFrame {
		private final int time;
		private final Vector3f value;

		public VectorKeyFrame(int time, Vector3f value) {
			this.time = time;
			this.value = value;
		}

		@Override
		public int getTime() {
			return time;
		}

		public Vector3f getValue() {
			return value;
		}
	}
}
