package io.github.redstoneparadox.marionette.render.block;

import io.github.redstoneparadox.marionette.animation.AbstractAnimation;
import io.github.redstoneparadox.marionette.api.AnimationPlayer;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderDispatcher;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;

import java.util.ArrayList;
import java.util.List;

public class ExtendedBlockEntityRenderer<T extends BlockEntity> extends BlockEntityRenderer<T> implements AnimationPlayer {
	private final List<AbstractAnimation> animations = new ArrayList<>();

	public ExtendedBlockEntityRenderer(BlockEntityRenderDispatcher dispatcher) {
		super(dispatcher);
	}

	@Override
	public void render(T entity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
		for (AbstractAnimation animation: animations) {
			animation.step();
		}
	}

	@Override
	public void addAnimation(AbstractAnimation animation) {
		animations.add(animation);
	}
}
