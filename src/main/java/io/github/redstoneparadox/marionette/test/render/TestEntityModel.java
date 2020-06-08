package io.github.redstoneparadox.marionette.test.render;

import io.github.redstoneparadox.marionette.model.ExtendedEntityModel;
import io.github.redstoneparadox.marionette.test.entity.TestEntity;
import net.minecraft.client.model.ModelPart;

public class TestEntityModel extends ExtendedEntityModel<TestEntity> {
	public final ModelPart part;

	public TestEntityModel() {
		part = new ModelPart(this);
		part.addCuboid(0, 0, 0, 2, 2, 2);
	}
}
