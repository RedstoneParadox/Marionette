package io.github.redstoneparadox.marionette.test.entity;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.*;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Arm;
import net.minecraft.world.World;

public class TestEntity extends LivingEntity {
	public static final EntityType<TestEntity> TYPE = FabricEntityTypeBuilder.<TestEntity>create(SpawnGroup.CREATURE, TestEntity::new).dimensions(EntityDimensions.fixed(1.0f, 1.0f)).build();

	protected TestEntity(World world) {
		super(TYPE, world);
		this.world = world;
	}

	protected TestEntity(EntityType<? extends TestEntity> entityType, World world) {
		super(entityType, world);
	}

	@Override
	public Iterable<ItemStack> getArmorItems() {
		return null;
	}

	@Override
	public ItemStack getEquippedStack(EquipmentSlot slot) {
		return null;
	}

	@Override
	public void equipStack(EquipmentSlot slot, ItemStack stack) {

	}

	@Override
	public Arm getMainArm() {
		return null;
	}
}
