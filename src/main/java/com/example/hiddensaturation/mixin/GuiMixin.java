package com.example.hiddensaturation.mixin;

import com.example.hiddensaturation.render.SaturationRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.world.entity.player.Player;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Environment(EnvType.CLIENT)
@Mixin(Gui.class)
public abstract class GuiMixin {

	@Inject(
		method = "renderStatusEffectOverlay",
		at = @At("TAIL")
	)
	private void onGuiRender(GuiGraphics guiGraphics, int i, CallbackInfo ci) {
		renderHiddenSaturation(guiGraphics);
	}

	@Unique
	private void renderHiddenSaturation(GuiGraphics guiGraphics) {
		net.minecraft.client.Minecraft minecraft = net.minecraft.client.Minecraft.getInstance();
		
		if (minecraft.player != null) {
			Player player = minecraft.player;
			SaturationRenderer.renderHiddenSaturation(guiGraphics, player);
		}
	}
}
