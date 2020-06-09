package io.github.redstoneparadox.marionette.animation.sampling;

import io.github.redstoneparadox.marionette.animation.KeyFrame;
import java.util.List;

@FunctionalInterface
public interface SamplerFactory {
	Sampler create(List<KeyFrame> keyFrames);
}
