package com.imhereaaaa.client;

import com.imhereaaaa.client.hud.effect.EffectHudListener;
import com.imhereaaaa.client.hud.effect.filters.ExampleFilter;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ModRenderClient implements ClientModInitializer {
    private static final Logger log = LogManager.getLogger(ModRenderClient.class);

    @Override
    public void onInitializeClient() {
        log.info("start!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        HudRenderCallback.EVENT.register(new EffectHudListener());
        EffectHudListener.addFilter(new ExampleFilter());
    }
}
