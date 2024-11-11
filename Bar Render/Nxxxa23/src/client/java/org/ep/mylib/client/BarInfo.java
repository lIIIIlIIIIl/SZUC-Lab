package org.ep.mylib.client;

import net.minecraft.util.Identifier;

public class BarInfo
{
    /**
     * 专用于表示进度条位置的枚举类型。
     */
    public enum BarPlace
    {
        /**
         * 左上角
         */
        TopLeft,

        /**
         * 右上角
         */
        TopRight,

        /**
         * 左下角
         */
        BottomLeft,

        /**
         * 右下角
         */
        BottomRight,

        /**
         * 物品框上方左侧
         */
        MiddleLeft,

        /**
         * 物品框上方右侧
         */
        MiddleRight
    }

    /**
     * 专用于表示进度条背景类型，具体请见资源文件里的bar.png。
     */
    public enum BarBackgroundType { BACKGROUND1, BACKGROUND2 }

    /**
     * 专用于表示进度条内容类型，具体请见资源文件里的bar.png。
     */
    public enum BarContentType { CONTENT1, CONTENT2, CONTENT3 }

    /**
     * 进度条名称。
     * 用于和其他进度条区分，请勿在同一BarManager内重名
     */
    public String name;

    /**
     * 进度条内容颜色。
     */
    public int color;

    /**
     * 进度条最大值
     */
    public int maxProgress;

    /**
     * 进度条当前值
     */
    public int curProgress;

    /**
     * 进度条背景类型。
     */
    public BarBackgroundType backgroundType;

    /**
     * 进度条内容类型。
     */
    public BarContentType contentType;

    /**
     * 进度条图标。
     */
    public Identifier icon;

    /**
     * 实际图标在贴图中的横向起始位置(仅shouldCrop为true时才有意义)。
     */
    public int iconU;

    /**
     * 实际图标在贴图中的纵向起始位置(仅shouldCrop为true时才有意义)。
     */
    public int iconV;

    /**
     * 实际图标在贴图中的宽度。
     */
    public int iconWidth;

    /**
     * 实际图标在贴图中的高度。
     */
    public int iconHeight;

    /**
     * 实际图标所在贴图宽度。
     */
    public int fromWidth;

    /**
     * 实际图标所在贴图高度。
     */
    public int fromHeight;

    /**
     * 适用于无裁剪图标的构造方法，同时自动将当前进度设为最大进度
     * 注意，当且仅当传入的图标贴图长宽为9x9时才使用该方法！
     * @param name 进度条名称
     * @param color 进度条颜色
     * @param maxProgress 进度条最大值
     * @param backgroundType 进度条背景类型
     * @param contentType 进度条内容类型
     * @param icon 进度条图标
     */
    public BarInfo(String name, int color, int maxProgress, BarBackgroundType backgroundType, BarContentType contentType, Identifier icon)
    {
        this.name = name;
        this.color = color;
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
    public BarInfo(String name, int color, int maxProgress, BarBackgroundType backgroundType, BarContentType contentType, Identifier icon, int iconU, int iconV, int iconWidth, int iconHeight, int fromWidth, int fromHeight)
    {
        this.name = name;
        this.color = color;
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

    /**
     * 适用于无裁剪地设定图标的方法。
     * 注意，当且仅当传入的图标贴图长宽为9x9时才使用该方法！
     * @param icon 进度条图标
     */
    public void setIconWithoutCrop(Identifier icon)
    {
        this.icon = icon;
        this.iconU = 0;
        this.iconV = 0;
        this.iconWidth = 9;
        this.iconHeight = 9;
        this.fromWidth = 9;
        this.fromHeight = 9;
    }

    /**
     * 适用于有裁剪地设定图标的方法。
     * 一般来说，建议裁剪后的图标呈正方形，因为渲染时会直接将其压缩成9x9。
     * @param icon 进度条图标
     * @param iconU 进度条图标在贴图的横向起始位置
     * @param iconV 进度条图标在贴图的纵向起始位置
     * @param iconWidth 进度条图标在贴图中的长度
     * @param iconHeight 进度条图标在贴图中的宽度
     * @param fromWidth 进度条图标所在贴图的宽度
     * @param fromHeight 进度条图标所在贴图的高度
     */
    public void setIconWithCrop(Identifier icon, int iconU, int iconV, int iconWidth, int iconHeight, int fromWidth, int fromHeight)
    {
        this.icon = icon;
        this.iconU = iconU;
        this.iconV = iconV;
        this.iconWidth = iconWidth;
        this.iconHeight = iconHeight;
        this.fromWidth = fromWidth;
        this.fromHeight = fromHeight;
    }
}
