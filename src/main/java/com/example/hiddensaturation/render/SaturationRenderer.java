package com.example.hiddensaturation.render;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.world.entity.player.Player;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.resources.ResourceLocation;

public class SaturationRenderer {

	private static final ResourceLocation ICONS = ResourceLocation.withDefaultNamespace("textures/gui/icons.png");

	public static void renderHiddenSaturation(GuiGraphics guiGraphics, Player player) {
		Minecraft minecraft = Minecraft.getInstance();
		int screenWidth = minecraft.getWindow().getGuiScaledWidth();
		int screenHeight = minecraft.getWindow().getGuiScaledHeight();

		// Position: above the hunger bar
		// Hunger bar is typically at: screenWidth/2 - 91, screenHeight - 39
		int x = screenWidth / 2 - 91;
		int y = screenHeight - 49; // 10 pixels above hunger bar

		float saturation = player.getFoodData().getSaturationLevel();
		int saturationBars = Math.round(saturation / 2.0f);

		// Draw saturation bars (green color)
		drawSaturationBars(guiGraphics, x, y, saturationBars, saturation);

		// Draw text showing exact saturation value
		drawSaturationText(guiGraphics, x, y - 10, saturation);
	}

	private static void drawSaturationBars(GuiGraphics guiGraphics, int x, int y, int bars, float saturation) {
		// Draw background (10 bars like hunger)
		for (int i = 0; i < 10; i++) {
			int barX = x + i * 8;
			// Draw empty bar background
			guiGraphics.blit(ICONS, barX, y, 0, 27, 9, 9);
		}

		// Draw filled saturation bars (golden/green color)
		for (int i = 0; i < bars; i++) {
			int barX = x + i * 8;
			// Draw filled bar (using the hunger bar texture but with different UV)
			guiGraphics.blit(ICONS, barX, y, 0, 36, 9, 9);
		}

		// Draw half bar if needed
		if (saturation % 2.0f >= 1.0f) {
			int halfBarX = x + bars * 8;
			guiGraphics.blit(ICONS, halfBarX, y, 0, 45, 9, 9);
		}
	}

	private static void drawSaturationText(GuiGraphics guiGraphics, int x, int y, float saturation) {
		Minecraft minecraft = Minecraft.getInstance();
		String text = String.format("Saturation: %.1f", saturation);
		int textWidth = minecraft.font.width(text);
		
		// Draw text centered above the bar
		guiGraphics.drawString(minecraft.font, text, x + 91 - textWidth / 2, y, 0xFFFFFF, true);
	}
}
