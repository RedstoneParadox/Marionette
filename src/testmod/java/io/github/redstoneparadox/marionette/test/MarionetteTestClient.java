package io.github.redstoneparadox.marionette.test;

import io.github.redstoneparadox.marionette.animation.AbstractAnimation;
import io.github.redstoneparadox.marionette.animation.Animation;
import io.github.redstoneparadox.marionette.animation.vanilla.VanillaAnimationHolderRegistry;
import io.github.redstoneparadox.marionette.animation.vanilla.VanillaEntityAnimationHolder;
import io.github.redstoneparadox.marionette.test.entity.TestEntity;
import io.github.redstoneparadox.marionette.test.render.TestEntityModel;
import io.github.redstoneparadox.marionette.test.render.TestEntityRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;

import java.util.Arrays;
import java.util.List;

public class MarionetteTestClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		EntityRendererRegistry.INSTANCE.register(TestEntity.TYPE, ((dispatcher, context) -> new TestEntityRenderer(dispatcher, new TestEntityModel(), 1.0f)));
		VanillaAnimationHolderRegistry.addEntityAnimationHolder(new PlayerEntityAnimationHolder());
	}

	static class PlayerEntityAnimationHolder extends VanillaEntityAnimationHolder<PlayerEntity, PlayerEntityModel<PlayerEntity>> {
		Animation<PlayerEntityModel<PlayerEntity>> leftArmSpin;

		{{
			leftArmSpin = new Animation.Builder<PlayerEntityModel<PlayerEntity>>()
					.startTrack(0.0f)
					.keyFrame((float) (2 * Math.PI), 100)
					.completeTrack((model, value) -> model.leftArm.pitch = value)
					.build(true);

			leftArmSpin.play();
		}}

		@Override
		public EntityType<PlayerEntity> getEntityType() {
			return EntityType.PLAYER;
		}

		@Override
		public List<AbstractAnimation<PlayerEntityModel<PlayerEntity>>> getAnimations() {
			return Arrays.asList(leftArmSpin);
		}
	}
}
