package io.github.redstoneparadox.marionette;

import io.github.redstoneparadox.marionette.animation.vanilla.VanillaAnimationHolderRegistry;
import io.github.redstoneparadox.marionette.animation.vanilla.VanillaEntityAnimationHolder;
import io.github.redstoneparadox.marionette.hooks.VanillaEntityAnimationHooks;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientEntityEvents;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Marionette implements ClientModInitializer {
	public static final String ID = "marionette";
	public static final Logger LOGGER = LogManager.getFormatterLogger(ID);

	@Override
	public void onInitializeClient() {
		ClientEntityEvents.ENTITY_LOAD.register((entity, world) -> {
			if (entity instanceof VanillaEntityAnimationHooks) {
				EntityType<?> type = entity.getType();
				VanillaAnimationHolderRegistry.getEntityAnimationHolders(type).forEach(holder -> ((VanillaEntityAnimationHooks) entity).addVanillaAnimationHolder((VanillaEntityAnimationHolder<Entity, ? extends EntityModel<? extends Entity>>) holder));
			}
		});
	}
}
