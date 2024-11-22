package org.bike.bar_render.client.renderer;

import com.mojang.blaze3d.systems.RenderSystem;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import org.bike.bar_render.Bar_render;
import org.bike.bar_render.config.Position;

public abstract class HudOverlay implements HudRenderCallback {
    private static final Identifier New_Hud = new Identifier(Bar_render.MOD_ID, "textures/gui/mana_empty.png");
    private static final Identifier Filled_Mana = new Identifier(Bar_render.MOD_ID, "textures/gui/mana_full.png");
    private static float displayedMana = 0.0f;
    private static boolean transitionMinMax = false;
    private static float currentAlpha = 1.0f;
    private static float globalAlpha = 1.0f;
    /*@Override
    public void onHudRender(DrawContext drawContext, float tickDelta) {
        MinecraftClient client = MinecraftClient.getInstance();
        MatrixStack matrices = drawContext.getMatrices();
        TextRenderer textRenderer = client.textRenderer;

        int width = client.getWindow().getScaledWidth();
        int height = client.getWindow().getScaledHeight();

        int mana_x = (width / 2) - 320 + Position.manaBarPositionX;
        int mana_y = height - 42 - Position.manaBarPositionY;

        RenderSystem.setShader(GameRenderer::getPositionTexProgram);
        RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, globalAlpha);
        RenderSystem.setShaderTexture(0, New_Hud);
        drawContext.drawTexture(New_Hud, mana_x, mana_y, 0, 0, 16, 42, 16, 42);

        int currentMana = (int) client.player.getHealth();
        int maxMana = (int) client.player.getMaxHealth();
        int filledHeight = (int) ((currentMana / (float) maxMana) * 33);

        RenderSystem.setShaderTexture(0, Filled_Mana);
        RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, globalAlpha * currentAlpha);
        drawContext.drawTexture(Filled_Mana, mana_x + 4, mana_y + 4 + (33 - filledHeight), 0, 33 - filledHeight, 8, filledHeight, 8, 33);

        RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
        RenderSystem.disableBlend();*/


}
