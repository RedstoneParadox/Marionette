package io.github.redstoneparadox.marionette.mixin.entity;

import io.github.redstoneparadox.marionette.animation.AbstractAnimation;
import io.github.redstoneparadox.marionette.animation.AnimationHolder;
import io.github.redstoneparadox.marionette.animation.CompositeAnimationHolder;
import io.github.redstoneparadox.marionette.animation.TickableAnimationHolder;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.List;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin<T extends EntityModel<U>, U extends LivingEntity> implements CompositeAnimationHolder<T> {
	@Unique
	private List<AnimationHolder<T>> holders = new ArrayList<>();

	@Override
	public void addAnimationHolder(AnimationHolder<T> animationHolder) {
		holders.add(animationHolder);
	}

	@Override
	public List<AbstractAnimation<T>> getAnimations() {
		List<AbstractAnimation<T>> anims = new ArrayList<>();

		for (AnimationHolder<T> holder: holders) {
			anims.addAll(holder.getAnimations());
		}

		return anims;
	}

	@Inject(method = "tick", at = @At("HEAD"))
	private void tick(CallbackInfo ci) {
		Entity self = (LivingEntity)(Object)this;

		holders.forEach(tAnimationHolder -> {
			if (tAnimationHolder instanceof TickableAnimationHolder) {
				((TickableAnimationHolder<T, U>) tAnimationHolder).tick((U) self);
			}
		});
	}


}
