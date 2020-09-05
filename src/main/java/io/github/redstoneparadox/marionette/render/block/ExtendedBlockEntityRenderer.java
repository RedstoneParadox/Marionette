package io.github.redstoneparadox.marionette.render.block;

import io.github.redstoneparadox.marionette.animation.AbstractAnimation;
import io.github.redstoneparadox.marionette.animation.AnimationHolder;
import io.github.redstoneparadox.marionette.model.BlockEntityModel;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderDispatcher;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public abstract class ExtendedBlockEntityRenderer<U extends BlockEntityModel, T extends BlockEntity & AnimationHolder<U>> extends BlockEntityRenderer<T> {
	final U model;

	public ExtendedBlockEntityRenderer(U model, BlockEntityRenderDispatcher dispatcher) {
		super(dispatcher);
		this.model = model;
	}

	@Override
	public void render(T entity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
		for (AbstractAnimation<U> animation: entity.getAnimations()) {
			animation.step(model);
		}

		RenderLayer layer = model.getLayer(getTexture(entity));
		model.render(matrices, vertexConsumers.getBuffer(layer), light, overlay, 1.0f, 1.0f, 1.0f, 1.0f);
	}

	public abstract Identifier getTexture(T entity);
}
