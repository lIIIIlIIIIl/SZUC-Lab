package org.bike.bar_render.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.VertexConsumerProvider;
import org.bike.bar_render.client.api.StatusBarAPI;
import org.bike.bar_render.client.renderer.HudOverlay;
import org.bike.bar_render.client.renderer.StatusBarLayoutManager;

public class Bar_renderClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {

        /*HudRenderCallback.EVENT.register(new HudOverlay() {
            @Override
            public void onHudRender(DrawContext drawContext, float tickDelta) {

            }
        });*/

        //VertexConsumerProvider.Immediate vertexConsumers = client.getBufferBuilders().getEntityVertexConsumers();
        //drawContext = new DrawContext(client, vertexConsumers);
        //if(client.player != null){
        //Barinfo<Float> floatBarinfo = BarManager.creatbar("Test",0F,,);
        //}
        MinecraftClient client = MinecraftClient.getInstance();

        StatusBarAPI.setLayoutManager(StatusBarLayoutManager.StatusBarLayoutType.TOP);
        StatusBarAPI.registerStatusBar("health_bar", "textures/gui/mana_empty.png", 0xFF0000, 20f, 15f);
        ClientTickEvents.END_CLIENT_TICK.register(mcclient -> {
            if (client.getBufferBuilders() != null) {
                VertexConsumerProvider.Immediate vertexConsumers = client.getBufferBuilders().getEntityVertexConsumers();
                DrawContext drawContext = new DrawContext(client, vertexConsumers);

                // 调用 StatusBar 渲染
                StatusBarAPI.renderStatusBars(drawContext);
            }
        });
    }

}

