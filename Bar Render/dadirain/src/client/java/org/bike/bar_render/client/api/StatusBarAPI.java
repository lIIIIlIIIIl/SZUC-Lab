package org.bike.bar_render.client.api;


import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
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

    public static void registerStatusBar(String id, String backgroundPath, String iconPath, String texturePath, int color, ValueManager valueManager) {
        Identifier icon = new Identifier(Bar_render.MOD_ID, iconPath);
        Identifier background = new Identifier(Bar_render.MOD_ID, backgroundPath);
        Identifier texture = new Identifier(Bar_render.MOD_ID, texturePath );
        StatusBarLib.StatusBar statusBar = new StatusBarLib.StatusBar(id, background, icon, texture, color, valueManager);
        statusBars.add(statusBar);
    }



    public static void renderStatusBars(DrawContext drawContext) {
        if (layoutManager != null) {
            layoutManager.renderStatusBars(drawContext, statusBars);
        }
    }

    public static void renderBars(){
        ClientTickEvents.END_CLIENT_TICK.register(mcclient -> {
            MinecraftClient client = MinecraftClient.getInstance();
            if (client.getBufferBuilders() != null) {
                HudRenderCallback.EVENT.register((drawContext, tickDelta) -> StatusBarAPI.renderStatusBars(drawContext));
                // 调用 StatusBar 渲染
            }
        });
    }

    public static class ValueManager {
        private float maxValue;
        private float curValue;

        public void setMaxValue(float value) {
            this.maxValue = value;
        }
        public void setCurValue(float value){
            this.curValue = value;
        }

        public float getMaxValue() {
            return this.maxValue;
        }
        public float getCurValue(){
            return this.curValue;
        }

        public void displayMaxValue() {
            System.out.println("当前最大值是: " + getMaxValue());
        }
        public void displayCurValue() {
            System.out.println("当前最大值是: " + getCurValue());
        }
    }
}
