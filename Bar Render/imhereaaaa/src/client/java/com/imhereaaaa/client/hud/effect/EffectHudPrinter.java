package com.imhereaaaa.client.hud.effect;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.util.math.MatrixStack;

public class EffectHudPrinter {
    public static void printHud(DrawContext context,
                                int printX,
                                int printY,
                                int backgroundColor,
                                int remainingTime,
                                int totalTime,
                                int progressColor,
                                EffectHudModel model) {
        MatrixStack matrixStack = context.getMatrices();
        TextRenderer textRenderer = MinecraftClient.getInstance().textRenderer;

        float progress = (float) remainingTime / totalTime;  // 进度从 0 到 1

        // 进度条位置和大小
        int barWidth = 100;
        int barHeight = 10;
        // 绘制进度条背景（灰色）
        context.fill(printX, printY, printX + barWidth, printY + barHeight, backgroundColor);
        // 绘制剩余时间进度条
        context.fill(printX, printY, printX + (int) (barWidth * progress), printY + barHeight, progressColor);

        // 绘制文本：显示剩余时间
        matrixStack.push();

        String timeText = model.getName();
        if (remainingTime != -1) {
            timeText = timeText + " " + remainingTime / 20 + "/" + totalTime / 20;
        }
        context.drawText(textRenderer, timeText, printX, printY, 0xFFFFFFFF, true);
        matrixStack.pop();
    }
}
