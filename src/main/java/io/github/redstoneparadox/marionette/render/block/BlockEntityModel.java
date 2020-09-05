package io.github.redstoneparadox.marionette.render.block;

import net.minecraft.client.model.Model;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.util.Identifier;

import java.util.function.Function;

public abstract class BlockEntityModel extends Model {
	protected final Identifier texture;

	public BlockEntityModel(Identifier texture, Function<Identifier, RenderLayer> layerFactory) {
		super(layerFactory);
		this.texture = texture;
	}

	public BlockEntityModel(Identifier texture) {
		this(texture, RenderLayer::getEntitySolid);
	}
}
