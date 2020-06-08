package io.github.redstoneparadox.marionette.test.render;

import io.github.redstoneparadox.marionette.test.entity.TestEntity;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class TestEntityRenderer extends LivingEntityRenderer<TestEntity, TestEntityModel> {
	public TestEntityRenderer(EntityRenderDispatcher dispatcher, TestEntityModel model, float shadowRadius) {
		super(dispatcher, model, shadowRadius);
	}

	@Override
	public void render(TestEntity livingEntity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
		VertexConsumer consumer = vertexConsumerProvider.getBuffer(model.getLayer(getTexture(livingEntity)));
		model.render(matrixStack, consumer, i, OverlayTexture.DEFAULT_UV, 1.0f, 1.0f, 1.0f, 1.0f);
	}

	@Override
	public Identifier getTexture(TestEntity entity) {
		return new Identifier("creeper");
	}
}
