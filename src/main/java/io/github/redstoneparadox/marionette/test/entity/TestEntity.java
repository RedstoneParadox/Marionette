package io.github.redstoneparadox.marionette.test.entity;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Arm;
import net.minecraft.world.World;

import java.util.ArrayList;

public class TestEntity extends MobEntity {
	public static final EntityType<TestEntity> TYPE = FabricEntityTypeBuilder.<TestEntity>create(SpawnGroup.CREATURE, TestEntity::new).dimensions(EntityDimensions.fixed(1.0f, 1.0f)).build();
	public boolean animated = true;

	protected TestEntity(World world) {
		super(TYPE, world);
		this.world = world;
	}

	protected TestEntity(EntityType<? extends TestEntity> entityType, World world) {
		super(entityType, world);
	}

	@Override
	protected void initDataTracker() {
		super.initDataTracker();
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
	public void tick() {
		super.tick();
		animated = false;
		for (PlayerEntity player: world.getPlayers()) {
			if (player.getPos().distanceTo(getPos()) < 10) {
				animated = true;
				break;
			}
		}
	}

	@Override
	public Arm getMainArm() {
		return Arm.RIGHT;
	}

	public static DefaultAttributeContainer.Builder createTestEntityAttributes() {
		return MobEntity.createMobAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH, 1.0);
	}
}
