package io.github.redstoneparadox.marionette.render.entity;

import io.github.redstoneparadox.marionette.animation.AbstractAnimation;
import io.github.redstoneparadox.marionette.animation.AnimationHolder;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.entity.Entity;

public abstract class ExtendedEntityModel<U extends ExtendedEntityModel<U, T>, T extends Entity & AnimationHolder<U>> extends EntityModel<T> {
	@Override
	public void setAngles(T entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {

	}

	@Override
	public void animateModel(T entity, float limbAngle, float limbDistance, float tickDelta) {
		for (AbstractAnimation<U> animation: entity.getAnimations()) {
			animation.step((U) this);
		}
	}
}
