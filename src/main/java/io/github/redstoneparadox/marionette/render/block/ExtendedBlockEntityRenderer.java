package io.github.redstoneparadox.marionette.render.block;

import io.github.redstoneparadox.marionette.animation.AbstractAnimation;
import io.github.redstoneparadox.marionette.animation.AnimationHolder;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderDispatcher;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;

public abstract class ExtendedBlockEntityRenderer<U extends BlockEntityRenderer<T>, T extends BlockEntity & AnimationHolder<U>> extends BlockEntityRenderer<T> {
	public ExtendedBlockEntityRenderer(BlockEntityRenderDispatcher dispatcher) {
		super(dispatcher);
	}

	@Override
	public void render(T entity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
		for (AbstractAnimation<U> animation: entity.getAnimations()) {
			animation.step((U) this);
		}
	}
}
