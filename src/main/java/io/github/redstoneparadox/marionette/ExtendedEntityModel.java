package io.github.redstoneparadox.marionette;

import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;

public class ExtendedEntityModel<T extends Entity> extends EntityModel<T> {
	@Override
	public void setAngles(Entity entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {

	}

	@Override
	public void render(MatrixStack matrices, VertexConsumer vertices, int light, int overlay, float red, float green, float blue, float alpha) {

	}

	@Override
	public void animateModel(T entity, float limbAngle, float limbDistance, float tickDelta) {

	}
}
