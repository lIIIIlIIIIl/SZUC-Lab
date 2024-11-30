package org.bike.bar_render.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;

import org.bike.bar_render.client.api.StatusBarAPI;
import org.bike.bar_render.client.renderer.StatusBarLayoutManager;

public class Bar_renderClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        MinecraftClient client = MinecraftClient.getInstance();


        StatusBarAPI.ValueManager valueManager = new StatusBarAPI.ValueManager();
        StatusBarAPI.ValueManager valueManager2 = new StatusBarAPI.ValueManager();


        StatusBarAPI.setLayoutManager(StatusBarLayoutManager.StatusBarLayoutType.LEFT);


        ClientTickEvents.END_CLIENT_TICK.register(mcclient -> {
            if (client.player != null) {
                // 获取玩家的最大生命值和当前生命值
                valueManager.setCurValue(client.player.getHealth());
                valueManager.setMaxValue(client.player.getMaxHealth());
                valueManager2.setCurValue(client.player.getHealth());
                valueManager2.setMaxValue(client.player.getMaxHealth());
            }
        });

        StatusBarAPI.registerStatusBar("health_bar", "textures/gui/mana_empty.png", "textures/gui/icon.png", "textures/gui/mana_full.png", 0xFF0000, valueManager);
        StatusBarAPI.registerStatusBar("health_bar2", "textures/gui/mana_empty.png", "textures/gui/icon.png", "textures/gui/mana_full.png", 0xFF0000, valueManager2);

        StatusBarAPI.renderBars();

    }

}

