package jos.nxxxa23.custombar.info;

import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.util.Identifier;

public class BarInfo<T extends Number> extends BarInfoAbstract<T>
{
    /**
     * 进度条最大值
     */
    protected T maxProgress;

    /**
     * 进度条最小值
     */
    protected T minProgress;

    /**
     * 进度条当前值
     */
    protected T curProgress;

    /**
     * 适用于无裁剪图标的构造方法，同时自动将当前进度设为最大进度
     * 注意，当且仅当传入的图标贴图长宽为9x9时才使用该方法！
     * @param name 进度条名称
     * @param color 进度条颜色
     * @param minProgress 进度条最小进度
     * @param maxProgress 进度条最大值
     * @param backgroundType 进度条背景类型
     * @param contentType 进度条内容类型
     * @param icon 进度条图标
     */
    public BarInfo(String name, int color, T minProgress, T maxProgress, BarBackgroundType backgroundType, BarContentType contentType, Identifier icon)
    {
        this.name = name;
        this.color = color;
        this.minProgress = minProgress;
        this.maxProgress = maxProgress;
        this.curProgress = maxProgress;
        this.backgroundType = backgroundType;
        this.contentType = contentType;
        this.icon = icon;
        this.iconU = 0;
        this.iconV = 0;
        this.iconWidth = 9;
        this.iconHeight = 9;
        this.fromWidth = 9;
        this.fromHeight = 9;
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
    public BarInfo(String name, int color, T minProgress, T maxProgress, BarBackgroundType backgroundType, BarContentType contentType, Identifier icon, int iconU, int iconV, int iconWidth, int iconHeight, int fromWidth, int fromHeight)
    {
        this.name = name;
        this.color = color;
        this.minProgress = minProgress;
        this.maxProgress = maxProgress;
        this.curProgress = maxProgress;
        this.backgroundType = backgroundType;
        this.contentType = contentType;
        this.icon = icon;
        this.iconU = iconU;
        this.iconV = iconV;
        this.iconWidth = iconWidth;
        this.iconHeight = iconHeight;
        this.fromWidth = fromWidth;
        this.fromHeight = fromHeight;
    }

    public float getProgressRatio()
    {
        return (curProgress.floatValue() - minProgress.floatValue()) / (maxProgress.floatValue() - minProgress.floatValue());
    }

    public T getCurProgress()
    {
        return curProgress;
    }

    public void setCurProgress(T curProgress)
    {
        this.curProgress = curProgress;
        if (checkProgressInvalid())
        {
            throw new RuntimeException("Progress invalid");
        }
    }

    public T getMaxProgress()
    {
        return maxProgress;
    }

    public void setMaxProgress(T maxProgress)
    {
        this.maxProgress = maxProgress;
        if (checkProgressInvalid())
        {
            throw new RuntimeException("Progress invalid");
        }
    }

    public T getMinProgress()
    {
        return minProgress;
    }

    public void setMinProgress(T minProgress)
    {
        this.minProgress = minProgress;
        if (checkProgressInvalid())
        {
            throw new RuntimeException("Progress invalid");
        }
    }

    protected boolean checkProgressInvalid()
    {
        return (curProgress.floatValue() < minProgress.floatValue()
                || curProgress.floatValue() > maxProgress.floatValue());
    }
}
