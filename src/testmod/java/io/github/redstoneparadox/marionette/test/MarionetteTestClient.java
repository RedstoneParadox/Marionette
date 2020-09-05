package io.github.redstoneparadox.marionette.test;

import io.github.redstoneparadox.marionette.animation.AbstractAnimation;
import io.github.redstoneparadox.marionette.animation.Animation;
import io.github.redstoneparadox.marionette.animation.TickableAnimationHolder;
import io.github.redstoneparadox.marionette.test.entity.TestEntity;
import io.github.redstoneparadox.marionette.test.render.TestEntityModel;
import io.github.redstoneparadox.marionette.test.render.TestEntityRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry;
import net.minecraft.client.render.entity.model.PigEntityModel;
import net.minecraft.entity.passive.PigEntity;

import java.util.Collections;
import java.util.List;

public class MarionetteTestClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		EntityRendererRegistry.INSTANCE.register(TestEntity.TYPE, ((dispatcher, context) -> new TestEntityRenderer(new TestEntityModel(), dispatcher, 1.0f)));

		/*
		ClientEntityEvents.ENTITY_LOAD.register(((entity, world) -> {
			if (entity instanceof CompositeAnimationHolder && entity instanceof PigEntity) {
				((CompositeAnimationHolder) entity).addAnimationHolder(new PigAnimationHolder());
			}
		}));
		
		 */
	}

	class PigAnimationHolder implements TickableAnimationHolder<PigEntityModel<PigEntity>,PigEntity> {
		Animation<PigEntityModel<PigEntity>> test;

		public PigAnimationHolder() {
			test = new Animation.Builder<PigEntityModel<PigEntity>>()
					.startTrack(0.0f)
					.keyFrame((float) Math.PI, 20)
					.completeTrack(((pigEntityPigEntityModel, value) -> {
						pigEntityPigEntityModel.torso.roll = value;
					}))
					.build(true);

			test.play();
		}

		@Override
		public void tick(PigEntity pigEntity) {
			System.out.println("Being ticked!");
		}

		@Override
		public List<AbstractAnimation<PigEntityModel<PigEntity>>> getAnimations() {
			return Collections.singletonList(test);
		}
	}
}
