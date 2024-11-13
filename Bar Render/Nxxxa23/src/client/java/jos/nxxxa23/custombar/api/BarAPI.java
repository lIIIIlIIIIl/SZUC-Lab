package jos.nxxxa23.custombar.api;

import jos.nxxxa23.custombar.*;
import jos.nxxxa23.custombar.info.*;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.client.gui.DrawContext;

public class BarAPI
{
    private static BarManager barManager = new BarManager();
    private static boolean hasRender = false;
    private static boolean isRendering = false;

    public static <T extends Number> BarBuilder<T> createBar(String name, T minProgress, T maxProgress)
    {
        return new BarBuilder<>(name, minProgress, maxProgress, barManager);
    }

    public static void removeBar(String name)
    {
        barManager.remove(name);
    }

    public static void clearBar()
    {
        barManager = new BarManager();
    }

    public static BarInfoAbstract findBar(String name)
    {
        return barManager.findBar(name);
    }

    public static void setHUDRenderCallbackEvent(Event<HudRenderCallback> HUDRenderCallbackEvent)
    {
        if (hasRender)
        {
            throw new RuntimeException("HUDRenderCallback already set");
        }

        HUDRenderCallbackEvent.register((ctx, deltaTick) ->
        {
            if (isRendering)
            {
                render(ctx);
            }
        });
    }

    public static void setRendering(boolean givenIsRendering)
    {
        isRendering = givenIsRendering;
    }

    public static void render(DrawContext ctx)
    {
        barManager.render(ctx);
    }
}