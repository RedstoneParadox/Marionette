package io.github.redstoneparadox.marionette.test;

import io.github.redstoneparadox.marionette.test.entity.TestEntity;
import io.github.redstoneparadox.marionette.test.render.TestEntityModel;
import io.github.redstoneparadox.marionette.test.render.TestEntityRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientEntityEvents;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class MarionetteTestClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		EntityRendererRegistry.INSTANCE.register(TestEntity.TYPE, ((dispatcher, context) -> new TestEntityRenderer(new TestEntityModel(), dispatcher, 1.0f)));

		ClientEntityEvents.ENTITY_LOAD.register(((entity, world) -> {
			EntityType<?> type = entity.getType();
			Identifier id = Registry.ENTITY_TYPE.getId(type);

			System.out.println("Entity loaded: " + id.toString());
		}));
	}
}
