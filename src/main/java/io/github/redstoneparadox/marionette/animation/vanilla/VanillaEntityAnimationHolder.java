package io.github.redstoneparadox.marionette.animation.vanilla;

import io.github.redstoneparadox.marionette.animation.AnimationHolder;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;

public abstract class VanillaEntityAnimationHolder<E extends Entity, T extends EntityModel<E>> implements AnimationHolder<T> {
	public void tick(E entity) {

	}

	public abstract EntityType<E> getEntityType();
}
