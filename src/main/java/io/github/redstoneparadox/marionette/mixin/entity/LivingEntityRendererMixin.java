package io.github.redstoneparadox.marionette.mixin.entity;

import io.github.redstoneparadox.marionette.animation.AbstractAnimation;
import io.github.redstoneparadox.marionette.animation.CompositeAnimationHolder;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntityRenderer.class)
public abstract class LivingEntityRendererMixin<T extends LivingEntity> {
	@Shadow protected EntityModel<T> model;

	@Inject(method = "render", at = @At(value = "INVOKE_ASSIGN", target = "Lnet/minecraft/client/render/entity/LivingEntityRenderer;getOverlay(Lnet/minecraft/entity/LivingEntity;F)I", shift = At.Shift.AFTER))
	private void render(T livingEntity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i, CallbackInfo ci) {
		if (livingEntity instanceof CompositeAnimationHolder) {
			CompositeAnimationHolder<EntityModel<T>> holder = (CompositeAnimationHolder<EntityModel<T>>) livingEntity;

			for (AbstractAnimation<EntityModel<T>> animation: holder.getAnimations()) {
				animation.step(model);
			}
		}
	}
}
