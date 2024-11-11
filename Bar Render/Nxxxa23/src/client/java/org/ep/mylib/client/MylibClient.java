package org.ep.mylib.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.item.Items;
import net.minecraft.util.Colors;
import net.minecraft.util.Identifier;
import org.ep.mylib.client.BarInfo.*;

public class MylibClient implements ClientModInitializer
{
    private BarManager barManager = new BarManager();

    @Override
    public void onInitializeClient()
    {
        barManager.addBarInfo("TestBar1", Colors.RED, 400,
                BarPlace.MiddleLeft, BarBackgroundType.BACKGROUND1, BarContentType.CONTENT1,
                Identifier.of("minecraft", "textures/item/barrier.png"),
                0, 0,
                16, 16,
                16, 16);
        BarInfo bi = barManager.addBarInfo("TestBar2", 0xFF773322, 400,
                BarPlace.MiddleRight, BarBackgroundType.BACKGROUND1, BarContentType.CONTENT1,
                Identifier.of("minecraft", "textures/item/barrier.png"),
                0, 0,
                16, 16,
                16, 16);
        barManager.addBarInfo("TestBar3", 0xFF713322, 400,
                BarPlace.MiddleLeft, BarBackgroundType.BACKGROUND1, BarContentType.CONTENT1,
                Identifier.of("minecraft", "textures/item/barrier.png"),
                0, 0,
                16, 16,
                16, 16);
        barManager.addBarInfo("TestBar4", 0xFF1133F2, 400,
                BarPlace.MiddleRight, BarBackgroundType.BACKGROUND1, BarContentType.CONTENT1,
                Identifier.of("minecraft", "textures/item/barrier.png"),
                0, 0,
                16, 16,
                16, 16);

        barManager.addBarInfo("TestBar5", 0xFF1033A6, 400,
                BarPlace.TopLeft, BarBackgroundType.BACKGROUND1, BarContentType.CONTENT1,
                Identifier.of("minecraft", "textures/item/barrier.png"),
                0, 0,
                16, 16,
                16, 16);
        barManager.addBarInfo("TestBar6", 0xFF1237B1, 400,
                BarPlace.TopLeft, BarBackgroundType.BACKGROUND1, BarContentType.CONTENT1,
                Identifier.of("minecraft", "textures/item/barrier.png"),
                0, 0,
                16, 16,
                16, 16);

        barManager.addBarInfo("TestBar7", 0xFF2381C1, 400,
                BarPlace.TopRight, BarBackgroundType.BACKGROUND1, BarContentType.CONTENT1,
                Identifier.of("minecraft", "textures/item/barrier.png"),
                0, 0,
                16, 16,
                16, 16);
        barManager.addBarInfo("TestBar8", 0xFFABABAB, 400,
                BarPlace.TopRight, BarBackgroundType.BACKGROUND1, BarContentType.CONTENT1,
                Identifier.of("minecraft", "textures/item/barrier.png"),
                0, 0,
                16, 16,
                16, 16);

        barManager.addBarInfo("TestBar9", 0xFF192C12, 400,
                BarPlace.BottomLeft, BarBackgroundType.BACKGROUND1, BarContentType.CONTENT1,
                Identifier.of("minecraft", "textures/item/barrier.png"),
                0, 0,
                16, 16,
                16, 16);
        barManager.addBarInfo("TestBar10", 0xFFAD1010, 400,
                BarPlace.BottomLeft, BarBackgroundType.BACKGROUND1, BarContentType.CONTENT1,
                Identifier.of("minecraft", "textures/item/barrier.png"),
                0, 0,
                16, 16,
                16, 16);

        barManager.addBarInfo("TestBar11", 0xFFAD11CB, 400,
                BarPlace.BottomRight, BarBackgroundType.BACKGROUND1, BarContentType.CONTENT1,
                Identifier.of("minecraft", "textures/item/barrier.png"),
                0, 0,
                16, 16,
                16, 16);
        barManager.addBarInfo("TestBar12", 0xFFDDBBAA, 400,
                BarPlace.BottomRight, BarBackgroundType.BACKGROUND1, BarContentType.CONTENT1,
                Identifier.of("minecraft", "textures/item/barrier.png"),
                0, 0,
                16, 16,
                16, 16);

        bi.curProgress = 100;

        HudRenderCallback.EVENT.register((ctx, deltaTick) ->
        {
            barManager.render(ctx);
        });
    }
}
