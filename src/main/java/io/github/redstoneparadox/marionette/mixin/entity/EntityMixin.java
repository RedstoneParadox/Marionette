package io.github.redstoneparadox.marionette.mixin.entity;

import io.github.redstoneparadox.marionette.animation.AbstractAnimation;
import io.github.redstoneparadox.marionette.animation.AnimationHolder;
import io.github.redstoneparadox.marionette.animation.CompositeAnimationHolder;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;

import java.util.List;

@Mixin(Entity.class)
public class EntityMixin implements CompositeAnimationHolder<? extends Entity> {

	@Override
	public void addAnimationHolder(AnimationHolder<? extends Entity> animationHolder) {

	}

	@Override
	public List<AbstractAnimation<? extends Entity>> getAnimations() {
		return null;
	}
}
