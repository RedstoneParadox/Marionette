package io.github.redstoneparadox.marionette.test.render;

import io.github.redstoneparadox.marionette.animation.Animation;
import io.github.redstoneparadox.marionette.render.entity.ExtendedEntityModel;
import io.github.redstoneparadox.marionette.model.ExtendedModelPart;
import io.github.redstoneparadox.marionette.test.entity.TestEntity;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.util.math.MatrixStack;

public class TestEntityModel extends ExtendedEntityModel<TestEntity> {
	public final ExtendedModelPart part;
	public final Animation spinAnimation;
	public final Animation scalingAnimation;

	public TestEntityModel() {
		textureWidth = 16;
		textureHeight = 16;

		part = new ExtendedModelPart(this);
		part.addCuboid(-8, -8, -8, 16, 16, 16);
		part.setPivot(0, 16, 0);

		spinAnimation = new Animation.Builder()
				.startTrack((float) ((Math.PI)/4))
				.keyFrame((float) (-(Math.PI)/4), 100)
				.keyFrame((float) ((Math.PI)/4), 100)
				.cubicSampler()
				.completeTrack(value -> part.pitch = value)
				.build(this, true);

		scalingAnimation = new Animation.Builder()
				.startTrack(1.0f)
				.keyFrame(4.0f, 200)
				.keyFrame(1.0f, 200)
				.cubicSampler()
				.completeTrack((t -> {
					part.scaleX = t;
					part.scaleY = t;
					part.scaleZ = t;
				}))
				.build(this, true);

		spinAnimation.play();
	}

	@Override
	public void render(MatrixStack matrices, VertexConsumer vertices, int light, int overlay, float red, float green, float blue, float alpha) {
		part.render(matrices, vertices, light, overlay, red, green, blue, alpha);
	}

	@Override
	protected void update(TestEntity entity) {
		if (entity.spinning) {
			spinAnimation.play();
			scalingAnimation.pause();
		}
		else {
			spinAnimation.pause();
			scalingAnimation.play();
		}
	}
}
