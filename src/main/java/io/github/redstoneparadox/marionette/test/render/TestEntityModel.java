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
		part.addCuboid(-8, -8, -8, 16, 16, 16);
		part.setPivot(0, 16, 0);

		spinAnimation = Animation.builder()
				.startTrack(0.0)
				.addKeyFrame(2*Math.PI, 200)
				.addKeyFrame(4*Math.PI, 200)
				.completeTrack((value -> part.yaw = (float) value))
				.startTrack(0.0)
				.addKeyFrame((Math.PI)/4, 100)
				.addKeyFrame(-(Math.PI)/4, 200)
				.addKeyFrame(0.0, 100)
				.completeTrack((value -> part.pitch = (float) value))
				.build(true);
	}

	@Override
	public void render(MatrixStack matrices, VertexConsumer vertices, int light, int overlay, float red, float green, float blue, float alpha) {
		part.render(matrices, vertices, light, overlay, red, green, blue, alpha);
		spinAnimation.step();
	}
}
