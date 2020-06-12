package io.github.redstoneparadox.marionette.render.block;

import io.github.redstoneparadox.marionette.animation.AbstractAnimation;
import io.github.redstoneparadox.marionette.animation.AnimationPlayer;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderDispatcher;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;

import java.util.ArrayList;
import java.util.List;

public abstract class ExtendedBlockEntityRenderer<T extends BlockEntity> extends BlockEntityRenderer<T> implements AnimationPlayer {
	private final List<AbstractAnimation> animations = new ArrayList<>();

	public ExtendedBlockEntityRenderer(BlockEntityRenderDispatcher dispatcher) {
		super(dispatcher);
	}

	@Override
	public void render(T entity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
		update(entity);
		animations.forEach(AbstractAnimation::step);
	}

	protected void update(T blockEntity) {

	}

	@Override
	public void addAnimation(AbstractAnimation animation) {
		animations.add(animation);
	}

	@Override
	public void playAll() {
		animations.forEach(AbstractAnimation::play);
	}

	@Override
	public void pauseAll() {
		animations.forEach(AbstractAnimation::pause);
	}

	@Override
	public void stopAll() {
		animations.forEach(AbstractAnimation::stop);
	}
}
