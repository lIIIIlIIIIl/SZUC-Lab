package org.bike.bar_render.client.renderer;


import net.minecraft.client.gui.DrawContext;


import java.util.List;


public class StatusBarLayoutManager {
    public enum StatusBarLayoutType {
        TOP,
        BOTTOM,
        LEFT,
        RIGHT
    }
    private final StatusBarLayoutType layoutType;

    public StatusBarLayoutManager(StatusBarLayoutType layoutType) {
        this.layoutType = layoutType;
    }
    public void renderStatusBars(DrawContext drawContext, List<StatusBarLib.StatusBar> statusBars) {
        int xOffset = 10;
        int yOffset = 10;
        System.out.println("renderStatusBars");
        switch (layoutType) {
            case TOP:
                renderTopLayout(drawContext, statusBars, xOffset, yOffset);
                break;
            case BOTTOM:
                renderBottomLayout(drawContext, statusBars, xOffset, yOffset);
                break;
            case LEFT:
                renderLeftLayout(drawContext, statusBars, xOffset, yOffset);
                break;
            case RIGHT:
                renderRightLayout(drawContext, statusBars, xOffset, yOffset);
                break;
        }
    }
    private void renderTopLayout(DrawContext drawContext, List<StatusBarLib.StatusBar> statusBars, int x, int y) {
        for (StatusBarLib.StatusBar bar : statusBars) {
            bar.render(drawContext, x, y);
            y += 20; // 每个状态栏之间的间距
        }
        System.out.println("renderTopLayout");
    }
    private void renderBottomLayout(DrawContext drawContext, List<StatusBarLib.StatusBar> statusBars, int x, int y) {
        y = 200; // 假设底部 Y 位置
        for (StatusBarLib.StatusBar bar : statusBars) {
            bar.render(drawContext, x, y);
            y += 20; // 每个状态栏之间的间距
        }
    }

    private void renderLeftLayout(DrawContext drawContext, List<StatusBarLib.StatusBar> statusBars, int x, int y) {
        for (StatusBarLib.StatusBar bar : statusBars) {
            bar.render(drawContext, x, y);
            x += 100; // 每个状态栏之间的间距
        }
    }
    private void renderRightLayout(DrawContext drawContext, List<StatusBarLib.StatusBar> statusBars, int x, int y) {
        x = 200; // 假设右侧 X 位置
        for (StatusBarLib.StatusBar bar : statusBars) {
            bar.render(drawContext, x, y);
            x += 100; // 每个状态栏之间的间距
        }
    }
}
