package io.github.redstoneparadox.marionette.test.render;

import io.github.redstoneparadox.marionette.test.entity.TestEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class TestEntityRenderer extends MobEntityRenderer<TestEntity, TestEntityModel> {
	public TestEntityRenderer(EntityRenderDispatcher dispatcher, TestEntityModel model, float shadowRadius) {
		super(dispatcher, model, shadowRadius);
	}

	@Override
	public Identifier getTexture(TestEntity entity) {
		return new Identifier("marionette","textures/entity/test.png");
	}
}
