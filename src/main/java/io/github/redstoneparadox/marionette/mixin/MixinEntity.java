package io.github.redstoneparadox.marionette.mixin;

import io.github.redstoneparadox.marionette.animation.AbstractAnimation;
import io.github.redstoneparadox.marionette.animation.AnimationHolder;
import io.github.redstoneparadox.marionette.animation.vanilla.VanillaEntityAnimationHolder;
import io.github.redstoneparadox.marionette.hooks.VanillaEntityAnimationHooks;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

import java.util.ArrayList;
import java.util.List;

@Mixin(Entity.class)
public abstract class MixinEntity implements VanillaEntityAnimationHooks, AnimationHolder {
	@Unique
	private List<VanillaEntityAnimationHolder<? extends Entity, ? extends EntityModel<Entity>>> vanillaHolders = new ArrayList<>();

	@Override
	public void addVanillaAnimationHolder(VanillaEntityAnimationHolder<Entity, ? extends EntityModel<? extends Entity>> holder) {
		vanillaHolders.add(holder);
	}

	@Override
	public List<AbstractAnimation> getAnimations() {
		List<AbstractAnimation> animations = new ArrayList<>();

		vanillaHolders.forEach((holder -> animations.addAll(holder.getAnimations())));

		return animations;
	}
}
