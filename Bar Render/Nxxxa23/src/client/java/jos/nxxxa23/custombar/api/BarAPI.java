package jos.nxxxa23.custombar.api;

import jos.nxxxa23.custombar.*;
import jos.nxxxa23.custombar.info.*;
import net.minecraft.client.gui.DrawContext;

public class BarAPI
{
    private static BarManager barManager = new BarManager();

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

    public static void render(DrawContext ctx)
    {
        barManager.render(ctx);
    }
}