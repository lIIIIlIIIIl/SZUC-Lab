package org.ep.mylib.client;

import jos.nxxxa23.custombar.BarManager;
import jos.nxxxa23.custombar.api.BarAPI;
import jos.nxxxa23.custombar.info.BarInfo;
import jos.nxxxa23.custombar.info.BarInfoAbstract;
import jos.nxxxa23.custombar.info.BarInfoTicked;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientLifecycleEvents;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.util.ClientPlayerTickable;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.text.Text;
import net.minecraft.util.Colors;
import net.minecraft.util.Identifier;

import java.util.ArrayList;

public class MylibClient implements ClientModInitializer
{
    private int tickCount = 20;

    @Override
    public void onInitializeClient()
    {
        /*
        BarInfo bi = barManager.addBarInfo("TestBar2", 0xFF773322, 400,
                BarPlace.MiddleRight, BarBackgroundType.BACKGROUND1, BarContentType.CONTENT1,
                Identifier.of("minecraft", "textures/item/barrier.png"),
                0, 0,
                16, 16,
                16, 16);

        bi.curProgress = 100;
        */

        BarInfoTicked<Float> bi = BarAPI.createBar("TestBar2", -60f, 60f)
                .color(0xFFFFD700)
                .place(BarInfoAbstract.BarPlace.MiddleLeft)
                .background(BarInfoAbstract.BarBackgroundType.BACKGROUND1)
                .content(BarInfoAbstract.BarContentType.CONTENT1)
                .icon(Identifier.of("minecraft", "textures/item/leather_chestplate.png"))
                .iconCrop(0, 0, 16, 16, 16, 16)
                .ticked(ClientTickEvents.END_WORLD_TICK, (world) ->
                {
                    return MyPlayerUtil.calculateTemperature(world, MinecraftClient.getInstance().player.getBlockPos());
                }, 20)
                .buildTicked();

        bi.setTicking(true);
        BarAPI.setHUDRenderCallbackEvent(HudRenderCallback.EVENT);
        BarAPI.setRendering(true);
    }
}
