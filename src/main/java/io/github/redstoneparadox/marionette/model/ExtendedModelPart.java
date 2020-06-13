package io.github.redstoneparadox.marionette.model;

import net.minecraft.client.model.Model;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.util.math.MatrixStack;

/**
 * A {@link ModelPart} that can be scaled or have its
 * tint color change. At the moment, changing the
 * color doesn't do anything.
 */
public class ExtendedModelPart extends ModelPart {
	public float scaleX = 1.0f;
	public float scaleY = 1.0f;
	public float scaleZ = 1.0f;
	public float red = 1.0f;
	public float green = 1.0f;
	public float blue = 1.0f;
	public float alpha = 1.0f;

	public ExtendedModelPart(Model model) {
		super(model);
	}

	public ExtendedModelPart(Model model, int textureOffsetU, int textureOffsetV) {
		super(model, textureOffsetU, textureOffsetV);
	}

	public ExtendedModelPart(int textureWidth, int textureHeight, int textureOffsetU, int textureOffsetV) {
		super(textureWidth, textureHeight, textureOffsetU, textureOffsetV);
	}

	@Override
	public void render(MatrixStack matrices, VertexConsumer vertices, int light, int overlay, float red, float green, float blue, float alpha) {
		if (this.visible) {
			if (!cuboids.isEmpty() || !children.isEmpty()) {
				matrices.push();
				rotate(matrices);
				matrices.scale(scaleX, scaleY, scaleZ);
				renderCuboids(matrices.peek(), vertices, light, overlay, this.red, this.green, this.blue, this.alpha);

				for (ModelPart child: children) {
					if (child instanceof ExtendedModelPart) {
						child.render(matrices, vertices, light, overlay);
					}
					else {
						child.render(matrices, vertices, light, overlay, 1.0f, 1.0f, 1.0f, 1.0f);
					}
				}
				matrices.pop();
			}
		}
	}
}
