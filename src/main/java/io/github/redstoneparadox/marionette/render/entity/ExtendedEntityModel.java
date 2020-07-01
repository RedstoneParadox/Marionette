package io.github.redstoneparadox.marionette.render.entity;

import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.entity.Entity;

public abstract class ExtendedEntityModel<T extends Entity> extends EntityModel<T> {
	@Override
	public void setAngles(T entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
	}
}
