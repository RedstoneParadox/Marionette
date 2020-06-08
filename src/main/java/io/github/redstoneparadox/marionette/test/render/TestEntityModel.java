package io.github.redstoneparadox.marionette.test.render;

import io.github.redstoneparadox.marionette.model.ExtendedEntityModel;
import io.github.redstoneparadox.marionette.test.entity.TestEntity;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.util.math.MatrixStack;

public class TestEntityModel extends ExtendedEntityModel<TestEntity> {
	public final ModelPart part;

	public TestEntityModel() {
		textureWidth = 16;
		textureHeight = 16;

		part = new ModelPart(this);
		part.addCuboid(0, 0, 0, 16, 16, 16);
	}

	@Override
	public void render(MatrixStack matrices, VertexConsumer vertices, int light, int overlay, float red, float green, float blue, float alpha) {
		part.render(matrices, vertices, light, overlay, red, green, blue, alpha);
	}
}
