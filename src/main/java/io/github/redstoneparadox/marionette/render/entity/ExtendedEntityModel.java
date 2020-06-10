package io.github.redstoneparadox.marionette.render.entity;

import io.github.redstoneparadox.marionette.animation.AbstractAnimation;
import io.github.redstoneparadox.marionette.animation.Animation;
import io.github.redstoneparadox.marionette.api.AnimationPlayer;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;

import java.util.ArrayList;
import java.util.List;

/**
 * An {@link EntityModel} that implements {@link AnimationPlayer}.
 *
 * @param <T> The entity type.
 */
public abstract class ExtendedEntityModel<T extends Entity> extends EntityModel<T> implements AnimationPlayer {
	private final List<AbstractAnimation> animations = new ArrayList<>();

	@Override
	public void setAngles(Entity entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {

	}

	@Override
	public void render(MatrixStack matrices, VertexConsumer vertices, int light, int overlay, float red, float green, float blue, float alpha) {

	}

	@Override
	public void animateModel(T entity, float limbAngle, float limbDistance, float tickDelta) {
		update(entity);
		for (AbstractAnimation animation: animations) {
			animation.step();
		}
	}

	protected void update(T entity) {

	}

	@Override
	public void addAnimation(AbstractAnimation animation) {
		animations.add(animation);
	}
}
