package io.github.redstoneparadox.marionette.mixin;

import io.github.redstoneparadox.marionette.animation.AbstractAnimation;
import io.github.redstoneparadox.marionette.animation.AnimationHolder;
import net.minecraft.client.model.Model;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.entity.Entity;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.Function;

@Mixin(EntityModel.class)
public abstract class MixinEntityModel<T extends Entity> extends Model {
	public MixinEntityModel(Function<Identifier, RenderLayer> layerFactory) {
		super(layerFactory);
	}

	@Inject(method = "animateModel", at = @At("HEAD"))
	private void animateModel(T entity, float limbAngle, float limbDistance, float tickDelta, CallbackInfo ci) {
		if (entity instanceof AnimationHolder) {
			((AnimationHolder) entity).getAnimations().forEach((abstractAnimation -> {
				AbstractAnimation<EntityModel<T>> casted = (AbstractAnimation<EntityModel<T>>) abstractAnimation;
				EntityModel<T> self = (EntityModel<T>)(Object) this;
				casted.step(self);
			}));
		}
	}
}
