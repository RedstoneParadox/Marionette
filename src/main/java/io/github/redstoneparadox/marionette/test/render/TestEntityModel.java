package io.github.redstoneparadox.marionette.test.render;

import io.github.redstoneparadox.marionette.animation.TweenAnimation;
import io.github.redstoneparadox.marionette.render.entity.ExtendedEntityModel;
import io.github.redstoneparadox.marionette.model.ExtendedModelPart;
import io.github.redstoneparadox.marionette.test.entity.TestEntity;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.util.math.MatrixStack;

import java.util.Random;

public class TestEntityModel extends ExtendedEntityModel<TestEntity> {
	public final ExtendedModelPart part;
	public final TweenAnimation scalingTween;
	private final Random random = new Random();

	public TestEntityModel() {
		textureWidth = 16;
		textureHeight = 16;

		part = new ExtendedModelPart(this);
		part.addCuboid(-8, -8, -8, 16, 16, 16);
		part.setPivot(0, 16, 0);

		scalingTween = TweenAnimation.cubic(t -> {
			part.scaleX = t;
			part.scaleY = t;
			part.scaleZ = t;
		});

		addAnimation(scalingTween);
	}

	@Override
	public void render(MatrixStack matrices, VertexConsumer vertices, int light, int overlay, float red, float green, float blue, float alpha) {
		part.render(matrices, vertices, light, overlay, red, green, blue, alpha);
	}

	@Override
	protected void update(TestEntity entity) {
		if (scalingTween.isFinished()) {
			int nextScale = random.nextInt(4) + 1;
			while (nextScale == part.scaleX) nextScale = random.nextInt(4) + 1;
			scalingTween.tween(part.scaleX, nextScale, 100);
		}
	}
}
