package org.bike.bar_render.client.api;


import net.minecraft.client.gui.DrawContext;
import net.minecraft.util.Identifier;
import org.bike.bar_render.Bar_render;
import org.bike.bar_render.client.renderer.StatusBarLayoutManager;
import org.bike.bar_render.client.renderer.StatusBarLib;

import java.util.ArrayList;

import java.util.List;

public class StatusBarAPI {
    private static final List<StatusBarLib.StatusBar> statusBars = new ArrayList<>();
    private static StatusBarLayoutManager layoutManager;

    public static void setLayoutManager(StatusBarLayoutManager.StatusBarLayoutType layoutType) {
        layoutManager = new StatusBarLayoutManager(layoutType);
    }

    public static void registerStatusBar(String id, String iconPath, int color, float maxValue, float currentValue) {
        Identifier icon = new Identifier(Bar_render.MOD_ID, iconPath);
        StatusBarLib.StatusBar statusBar = StatusBarLib.createStatusBar(id, icon, color, maxValue, currentValue);
        statusBars.add(statusBar);
    }



    public static void renderStatusBars(DrawContext drawContext) {
        if (layoutManager != null) {
            layoutManager.renderStatusBars(drawContext, statusBars);
        }
    }
}
