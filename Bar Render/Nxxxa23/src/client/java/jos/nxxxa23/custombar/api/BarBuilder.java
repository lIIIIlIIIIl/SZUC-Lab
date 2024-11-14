package jos.nxxxa23.custombar.api;

import jos.nxxxa23.custombar.*;
import jos.nxxxa23.custombar.info.*;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.util.Identifier;
import org.lwjgl.system.SharedLibrary;
import net.fabricmc.fabric.api.event.Event;

import javax.xml.crypto.Data;

public class BarBuilder<T extends Number>
{
    private final String name;
    private final BarManager manager;
    private int color = 0xFFFFFFFF;
    private final T minProgress;
    private final T maxProgress;
    private TrackedData<T> curProgressTracked;
    private DataTracker dataTracker;
    private Event<ClientTickEvents.EndWorldTick> endTickEvent;
    private BarInfoTicked.UpdateProgress<T> updateProgress;
    private int apartTick;
    private BarInfo.BarPlace place = BarInfo.BarPlace.TopLeft;
    private BarInfo.BarBackgroundType backgroundType = BarInfo.BarBackgroundType.BACKGROUND1;
    private BarInfo.BarContentType contentType = BarInfo.BarContentType.CONTENT1;
    private Identifier icon;
    private int iconU = 0;
    private int iconV = 0;
    private int iconWidth = 16;
    private int iconHeight = 16;
    private int fromWidth = 16;
    private int fromHeight = 16;

    public BarBuilder(String name, T minProgress, T maxProgress, BarManager manager)
    {
        if (maxProgress.doubleValue() < minProgress.doubleValue())
        {
            throw new IllegalArgumentException("Max progress must be greater than min");
        }

        this.name = name;
        this.minProgress = minProgress;
        this.maxProgress = maxProgress;
        this.manager = manager;
    }

    public BarBuilder<T> color(int color)
    {
        this.color = color;
        return this;
    }

    public BarBuilder<T> place(BarInfo.BarPlace place)
    {
        this.place = place;
        return this;
    }

    public BarBuilder<T> background(BarInfo.BarBackgroundType type)
    {
        this.backgroundType = type;
        return this;
    }

    public BarBuilder<T> content(BarInfo.BarContentType type)
    {
        this.contentType = type;
        return this;
    }

    public BarBuilder<T> icon(Identifier icon)
    {
        this.icon = icon;
        return this;
    }

    public BarBuilder<T> iconCrop(int u, int v, int width, int height, int fromWidth, int fromHeight)
    {
        this.iconU = u;
        this.iconV = v;
        this.iconWidth = width;
        this.iconHeight = height;
        this.fromWidth = fromWidth;
        this.fromHeight = fromHeight;
        return this;
    }

    public BarBuilder<T> tracked(TrackedData<T> curTrackedData, DataTracker dataTracker)
    {
        this.curProgressTracked = curTrackedData;
        this.dataTracker = dataTracker;
        return this;
    }

    public BarBuilder<T> ticked(Event<ClientTickEvents.EndWorldTick> endTickEvent, BarInfoTicked.UpdateProgress<T> updateProgress, int apartTick)
    {
        this.endTickEvent = endTickEvent;
        this.updateProgress = updateProgress;
        this.apartTick = apartTick;
        return this;
    }

    public BarInfo<T> build()
    {
        return (BarInfo<T>)manager.addBarInfo(new BarInfo<>(name, color, minProgress, maxProgress,
                backgroundType, contentType, icon,
                iconU, iconV, iconWidth, iconHeight,
                fromWidth, fromHeight), place);
    }

    public BarInfoTracked<T> buildTracked()
    {
        return (BarInfoTracked<T>)manager.addBarInfo(new BarInfoTracked(name, color, minProgress, maxProgress, curProgressTracked, dataTracker,
                backgroundType, contentType, icon,
                iconU, iconV, iconWidth, iconHeight,
                fromWidth, fromHeight), place);
    }

    public BarInfoTicked<T> buildTicked()
    {
        return (BarInfoTicked<T>)manager.addBarInfo(new BarInfoTicked(name, color, minProgress, maxProgress,
                endTickEvent, updateProgress, apartTick,
                backgroundType, contentType, icon,
                iconU, iconV, iconWidth, iconHeight,
                fromWidth, fromHeight), place);
    }
}