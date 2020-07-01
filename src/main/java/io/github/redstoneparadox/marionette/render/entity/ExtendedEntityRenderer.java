package io.github.redstoneparadox.marionette.render.entity;

import io.github.redstoneparadox.marionette.animation.AbstractAnimation;
import io.github.redstoneparadox.marionette.animation.AnimationHolder;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;

public abstract class ExtendedEntityRenderer<U extends EntityModel<T>, T extends Entity & AnimationHolder<U>> extends EntityRenderer<T> {
	protected final U model;

	protected ExtendedEntityRenderer(U model, EntityRenderDispatcher dispatcher, float shadowRadius) {
		super(dispatcher);
		this.model = model;
		this.shadowRadius = shadowRadius;
	}

	@Override
	public void render(T entity, float yaw, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light) {
		super.render(entity, yaw, tickDelta, matrices, vertexConsumers, light);
		for (AbstractAnimation<U> animation: entity.getAnimations()) {
			animation.step(model);
		}

		RenderLayer layer = model.getLayer(getTexture(entity));
		model.render(matrices, vertexConsumers.getBuffer(layer), light, 1, 1.0f, 1.0f, 1.0f, 1.0f);
	}
}
