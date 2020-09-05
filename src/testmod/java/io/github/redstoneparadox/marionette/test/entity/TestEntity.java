package io.github.redstoneparadox.marionette.test.entity;

import io.github.redstoneparadox.marionette.animation.AbstractAnimation;
import io.github.redstoneparadox.marionette.animation.Animation;
import io.github.redstoneparadox.marionette.animation.AnimationHolder;
import io.github.redstoneparadox.marionette.test.render.TestEntityModel;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Arm;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestEntity extends LivingEntity implements AnimationHolder<TestEntityModel> {
	public static final EntityType<TestEntity> TYPE = FabricEntityTypeBuilder.<TestEntity>create(SpawnGroup.CREATURE, TestEntity::new).dimensions(EntityDimensions.fixed(1.0f, 1.0f)).build();
	private Animation<TestEntityModel> spinAnimation;

	public TestEntity(World world) {
		super(TYPE, world);
		initAnimations();
	}

	public TestEntity(EntityType<? extends LivingEntity> entityType, World world) {
		super(entityType, world);
		initAnimations();
	}

	private void initAnimations() {
		spinAnimation = new Animation.Builder<TestEntityModel>()
				.startTrack(0.0f)
				.keyFrame((float) (2 * Math.PI), 100)
				.completeTrack((testEntityModel, value) -> testEntityModel.part.roll = value)
				.playWhileGamePaused()
				.build(true);

		spinAnimation.play();
	}

	@Override
	public Iterable<ItemStack> getArmorItems() {
		return new ArrayList<>();
	}

	@Override
	public ItemStack getEquippedStack(EquipmentSlot slot) {
		return ItemStack.EMPTY;
	}

	@Override
	public void equipStack(EquipmentSlot slot, ItemStack stack) {

	}

	@Override
	public Arm getMainArm() {
		return Arm.RIGHT;
	}

	@Override
	public List<AbstractAnimation<TestEntityModel>> getAnimations() {
		return Arrays.asList(spinAnimation);
	}

	public static DefaultAttributeContainer.Builder createTestEntityAttributes() {
		return MobEntity.createMobAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH, 1.0);
	}
}
