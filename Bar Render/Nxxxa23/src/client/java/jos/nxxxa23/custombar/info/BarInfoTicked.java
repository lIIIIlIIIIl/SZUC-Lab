package jos.nxxxa23.custombar.info;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.util.Identifier;
import net.fabricmc.fabric.api.event.Event;

public class BarInfoTicked<T extends Number> extends BarInfo<T>
{
    private int apartTick;
    private int currentTick;
    private UpdateProgress<T> updateProgress;
    private boolean ticking;

    /**
     * 适用于无裁剪图标的构造方法，同时自动将当前进度设为最大进度
     * 注意，当且仅当传入的图标贴图长宽为9x9时才使用该方法！
     * @param name 进度条名称
     * @param color 进度条颜色
     * @param minProgress 进度条最小进度
     * @param maxProgress 进度条最大值
     * @param endTickEvent 进度条更新tick依据
     * @param backgroundType 进度条背景类型
     * @param contentType 进度条内容类型
     * @param icon 进度条图标
     */
    public BarInfoTicked(String name, int color, T minProgress, T maxProgress, Event<ClientTickEvents.EndWorldTick> endTickEvent, UpdateProgress<T> updateProgress, int apartTick, BarInfoAbstract.BarBackgroundType backgroundType, BarInfoAbstract.BarContentType contentType, Identifier icon)
    {
        super(name, color, minProgress, maxProgress, backgroundType, contentType, icon);
        this.apartTick = apartTick;
        this.currentTick = apartTick;
        this.updateProgress = updateProgress;
        this.ticking = false;
        endTickEvent.register((world) ->
        {
            if (this.ticking)
            {
                this.currentTick++;
                if (this.currentTick >= this.apartTick)
                {
                    setCurProgress(updateProgress.apply(world));
                    this.currentTick = 0;
                }
            }
        });
    }

    /**
     * 适用于无裁剪图标的构造方法，同时自动将当前进度设为最大进度
     * 一般来说，建议裁剪后的图标呈正方形，因为渲染时会直接将其压缩成9x9
     * @param name 进度条名称
     * @param color 进度条颜色
     * @param minProgress 进度条最小进度
     * @param maxProgress 进度条进度
     * @param backgroundType 进度条背景类型
     * @param contentType 进度条内容类型
     * @param icon 进度条图标
     * @param iconU 进度条图标在贴图的横向起始位置
     * @param iconV 进度条图标在贴图的纵向起始位置
     * @param iconWidth 进度条图标在贴图中的长度
     * @param iconHeight 进度条图标在贴图中的宽度
     * @param fromWidth 进度条图标所在贴图的宽度
     * @param fromHeight 进度条图标所在贴图的高度
     */
    public BarInfoTicked(String name, int color, T minProgress, T maxProgress, Event<ClientTickEvents.EndWorldTick> endTickEvent, UpdateProgress<T> updateProgress, int apartTick, BarInfoAbstract.BarBackgroundType backgroundType, BarInfoAbstract.BarContentType contentType, Identifier icon, int iconU, int iconV, int iconWidth, int iconHeight, int fromWidth, int fromHeight)
    {
        super(name, color, minProgress, maxProgress, backgroundType, contentType, icon, iconU, iconV, iconWidth, iconHeight, fromWidth, fromHeight);
        this.apartTick = apartTick;
        this.currentTick = apartTick;
        this.updateProgress = updateProgress;
        this.ticking = false;
        endTickEvent.register((world) ->
        {
            if (this.ticking)
            {
                this.currentTick++;
                if (this.currentTick >= this.apartTick)
                {
                    setCurProgress(updateProgress.apply(world));
                    this.currentTick = 0;
                }
            }
        });
    }

    public void setTicking(boolean ticking)
    {
        this.ticking = ticking;
    }

    @FunctionalInterface
    public interface UpdateProgress<T extends Number>
    {
        T apply(ClientWorld world);
    }
}
