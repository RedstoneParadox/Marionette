package io.github.redstoneparadox.marionette.test.render;

import io.github.redstoneparadox.marionette.test.entity.TestEntity;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.util.Identifier;

public class TestEntityRenderer extends LivingEntityRenderer<TestEntity, TestEntityModel> {
	public TestEntityRenderer(EntityRenderDispatcher dispatcher, TestEntityModel model, float shadowRadius) {
		super(dispatcher, model, shadowRadius);
	}

	@Override
	public Identifier getTexture(TestEntity entity) {
		return new Identifier("marionette-test","textures/entity/test.png");
	}
}
