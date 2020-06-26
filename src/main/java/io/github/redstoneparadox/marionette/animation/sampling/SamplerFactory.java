package io.github.redstoneparadox.marionette.animation.sampling;

import java.util.List;

@FunctionalInterface
public interface SamplerFactory {
	Sampler create(List<KeyFrame> keyFrames);
}
