package io.github.redstoneparadox.marionette.test.render;

import io.github.redstoneparadox.marionette.animation.Animation;
import io.github.redstoneparadox.marionette.model.ExtendedEntityModel;
import io.github.redstoneparadox.marionette.model.ExtendedModelPart;
import io.github.redstoneparadox.marionette.test.entity.TestEntity;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.util.math.MatrixStack;

public class TestEntityModel extends ExtendedEntityModel<TestEntity> {
	public final ExtendedModelPart part;
	public final Animation spinAnimation;

	public TestEntityModel() {
		textureWidth = 16;
		textureHeight = 16;

		part = new ExtendedModelPart(this);
		part.addCuboid(-8, 0, -8, 16, 16, 16);


		spinAnimation = Animation.builder()
				.startTrack(0.0)
				.addKeyFrame(1.0, 200)
				.completeTrack((value -> part.yaw = (float) value))
				.build();
	}

	@Override
	public void render(MatrixStack matrices, VertexConsumer vertices, int light, int overlay, float red, float green, float blue, float alpha) {
		part.render(matrices, vertices, light, overlay, red, green, blue, alpha);
		spinAnimation.step();
	}
}
