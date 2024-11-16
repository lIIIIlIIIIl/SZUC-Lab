package com.imhereaaaa.client;

import com.imhereaaaa.client.internal.hud.effect.Listener;
import com.imhereaaaa.client.internal.hud.effect.filters.Example;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ModRenderClient implements ClientModInitializer {
    private static final Logger log = LogManager.getLogger(ModRenderClient.class);
    public static final String MOD_ID = "mod_render";

    @Override
    public void onInitializeClient() {
        log.info("start!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        HudRenderCallback.EVENT.register(new Listener());
        Listener.addFilter(new Example());
//        Listener.addFilter(new Random());
    }
}
