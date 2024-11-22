package org.bike.bar_render.client.renderer;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import org.bike.bar_render.Bar_render;

public class StatusBarLib {
    private static final MinecraftClient client = MinecraftClient.getInstance();

    // 状态栏类，包含基本的属性
    public static class StatusBar {
        private final String id;
        private final Identifier icon;  // 图标资源
        private static final Identifier New_Hud = new Identifier(Bar_render.MOD_ID, "textures/gui/mana_empty.png");
        private static final Identifier Filled_Mana = new Identifier(Bar_render.MOD_ID, "textures/gui/mana_full.png");
        private final int color;        // 状态栏颜色
        private final float maxValue;   // 最大值（例如生命值）
        private final float currentValue; // 当前值（例如玩家当前生命值）

        public StatusBar(String id, Identifier icon, int color, float maxValue, float currentValue) {
            this.id = id;
            this.icon = icon;
            this.color = color;
            this.maxValue = maxValue;
            this.currentValue = currentValue;
        }

        public void render(DrawContext context, int x, int y) {
            // 渲染状态栏的方法
            // 1. 绘制背景
            drawBackground(context, x, y);

            // 2. 绘制图标
            //drawIcon(context, x + 5, y + 5);

            // 3. 绘制进度条
            //drawProgressBar(context,  x + 20, y + 5);
            //System.out.println("render done");
        }

        private void drawBackground(DrawContext context, int x, int y) {

            context.drawTexture(icon, x, y, 0, 0, 16, 42,16,42);
            //System.out.println("drawBackground");
        }

        private void drawIcon(DrawContext context, int x, int y) {

            context.drawTexture(icon, x, y, 0, 0, 16, 16);  // 绘制图标
        }

        private void drawProgressBar(DrawContext context, int x, int y) {
            // 计算进度条长度
            float progressWidth = (currentValue / maxValue) * 33;
            context.drawTexture(icon, x, y, 0, 0, (int) progressWidth, 10);  // 绘制进度条
        }
    }


    public static StatusBar createStatusBar(String id, Identifier icon, int color, float maxValue, float currentValue) {
        return new StatusBar(id, icon, color, maxValue, currentValue);
    }
    public static StatusBarLib.StatusBar creatBar(String id, String iconPath, int color, float maxValue, float currentValue){
        Identifier icon = new Identifier(iconPath);
        return new StatusBar(id, icon, color, maxValue, currentValue);
    }

}