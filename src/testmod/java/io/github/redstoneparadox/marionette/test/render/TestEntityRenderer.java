package io.github.redstoneparadox.marionette.test.render;

import io.github.redstoneparadox.marionette.render.entity.ExtendedEntityRenderer;
import io.github.redstoneparadox.marionette.test.entity.TestEntity;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.util.Identifier;

public class TestEntityRenderer extends ExtendedEntityRenderer<TestEntityModel, TestEntity> {
	public TestEntityRenderer(TestEntityModel model, EntityRenderDispatcher dispatcher, float shadowRadius) {
		super(model, dispatcher, shadowRadius);
	}

	@Override
	public Identifier getTexture(TestEntity entity) {
		return new Identifier("marionette-test","textures/entity/test.png");
	}
}
