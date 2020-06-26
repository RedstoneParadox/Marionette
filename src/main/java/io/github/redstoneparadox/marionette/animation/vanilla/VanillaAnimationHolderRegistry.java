package io.github.redstoneparadox.marionette.animation.vanilla;

import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import org.jetbrains.annotations.ApiStatus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class VanillaAnimationHolderRegistry {
	private static Map<EntityType<?>, List<VanillaEntityAnimationHolder<?, ?>>> ENTITY_MAP = new HashMap<>();

	public static <E extends Entity, T extends EntityModel<E>> void addEntityAnimationHolder(VanillaEntityAnimationHolder<E, T> holder) {
		EntityType<E> type = holder.getEntityType();
		ENTITY_MAP.computeIfAbsent(type, key -> new ArrayList<>()).add(holder);
	}

	@ApiStatus.Internal
	public static <E extends Entity, T extends EntityModel<E>> List<VanillaEntityAnimationHolder<?, ?>> getEntityAnimationHolders(EntityType<E> type) {
		return ENTITY_MAP.get(type);
	}
}
