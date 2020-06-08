package io.github.redstoneparadox.marionette;

import net.minecraft.client.model.Model;
import net.minecraft.client.model.ModelPart;

public class MarionetteModelPart extends ModelPart {
	public MarionetteModelPart(Model model) {
		super(model);
	}

	public MarionetteModelPart(Model model, int textureOffsetU, int textureOffsetV) {
		super(model, textureOffsetU, textureOffsetV);
	}

	public MarionetteModelPart(int textureWidth, int textureHeight, int textureOffsetU, int textureOffsetV) {
		super(textureWidth, textureHeight, textureOffsetU, textureOffsetV);
	}
}
