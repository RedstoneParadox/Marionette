package io.github.redstoneparadox.marionette.animation.setter;

import net.minecraft.client.util.math.Vector3f;

public interface AnimatedVectorSetter<T> {
	void accept(T t, Vector3f vector);
}
