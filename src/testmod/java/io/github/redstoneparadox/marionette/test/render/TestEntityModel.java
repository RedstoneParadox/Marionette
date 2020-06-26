package io.github.redstoneparadox.marionette.test.render;

import io.github.redstoneparadox.marionette.model.ExtendedModelPart;
import io.github.redstoneparadox.marionette.render.entity.ExtendedEntityModel;
import io.github.redstoneparadox.marionette.test.entity.TestEntity;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.util.math.MatrixStack;

// Default pivot point for model parts seems to be 1.5 blocks off the ground, which is where the origin also is.
// Positive z on the model part translates to negative z ingame.
public class TestEntityModel extends ExtendedEntityModel<TestEntityModel, TestEntity> {
	public final ExtendedModelPart part;

	public TestEntityModel() {
		part = new ExtendedModelPart(this);
		part.addCuboid(-8, -8, -8, 16, 8, 16);
		part.setPivot(0, 16, 0);
	}

	@Override
	public void render(MatrixStack matrices, VertexConsumer vertices, int light, int overlay, float red, float green, float blue, float alpha) {
		part.render(matrices, vertices, light, overlay, red, green, blue, alpha);
	}
}
