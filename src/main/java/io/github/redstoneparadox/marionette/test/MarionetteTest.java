package io.github.redstoneparadox.marionette.test;

import io.github.redstoneparadox.marionette.test.entity.TestEntity;
import net.fabricmc.api.ModInitializer;
import net.minecraft.util.registry.Registry;

public class MarionetteTest implements ModInitializer {
	@Override
	public void onInitialize() {
		Registry.register(Registry.ENTITY_TYPE, "marionette:test_entity", TestEntity.TYPE);
	}
}
