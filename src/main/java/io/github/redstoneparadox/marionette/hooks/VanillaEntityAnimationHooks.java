package io.github.redstoneparadox.marionette.hooks;

import io.github.redstoneparadox.marionette.animation.vanilla.VanillaEntityAnimationHolder;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.entity.Entity;

public interface VanillaEntityAnimationHooks {
	void addVanillaAnimationHolder(VanillaEntityAnimationHolder<Entity, ? extends EntityModel<? extends Entity>> holder);
}
