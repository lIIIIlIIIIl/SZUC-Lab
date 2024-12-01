package org.bike.bar_render.client.renderer;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.util.Identifier;
import org.bike.bar_render.client.api.StatusBarAPI;

public class StatusBarLib {
    private static final MinecraftClient client = MinecraftClient.getInstance();

    // 状态栏类，包含基本的属性
    public static class StatusBar {
        private final String id;
        private final Identifier background;
        private final Identifier icon;  // 图标资源
        private final Identifier texture;
        private final int color;


        private StatusBarAPI.ValueManager valueManager = new StatusBarAPI.ValueManager();

        public StatusBar(String id, Identifier background, Identifier icon, Identifier texture, int color, StatusBarAPI.ValueManager valueManager) {
            this.id = id;
            this.background = background;
            this.icon = icon;
            this.texture = texture;
            this.color = color;
            this.valueManager = valueManager;
        }

        public void render(DrawContext context, int x, int y) {
            // 渲染状态栏的方法
            // 1. 绘制背景
            drawBackground(context, x, y);

            // 2. 绘制图标
            drawIcon(context, x+4, y + 40);

            // 3. 绘制进度条
            drawProgressBar(context,  x + 4, (int) (y+4.5));
        }

        private void drawBackground(DrawContext context, int x, int y) {
            context.drawTexture(background, x, y, 0, 0, 16, 42,16,42);
        }

        private void drawIcon(DrawContext context, int x, int y) {
            context.drawTexture(icon, x, y, 0, 0, 8, 8, 8, 8);  // 绘制图标
        }

        private void drawProgressBar(DrawContext context, int x, int y) {
            // 计算进度条长度
            int progressHeight = (int) ((valueManager.getCurValue() / valueManager.getMaxValue()) * 33);
            context.drawTexture(texture, x, (int) (y + (33 - progressHeight)), 0, (float) (33 - progressHeight) /33, 8, progressHeight, 8, 33);  // 绘制进度条
        }
    }




}