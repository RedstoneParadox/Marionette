package io.github.redstoneparadox.marionette.test;

import io.github.redstoneparadox.marionette.test.entity.TestEntity;
import io.github.redstoneparadox.marionette.test.render.TestEntityModel;
import io.github.redstoneparadox.marionette.test.render.TestEntityRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry;

public class MarionetteTestClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		EntityRendererRegistry.INSTANCE.register(TestEntity.TYPE, ((dispatcher, context) -> new TestEntityRenderer(dispatcher, new TestEntityModel(), 1.0f)));
	}
}
