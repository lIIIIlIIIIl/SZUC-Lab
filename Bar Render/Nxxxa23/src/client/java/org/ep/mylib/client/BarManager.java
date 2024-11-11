package org.ep.mylib.client;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.util.Identifier;
import org.ep.mylib.client.BarInfo.*;

public class BarManager
{
    //各个位置的进度条List
    private List<BarInfo> barListTopLeft = new ArrayList<BarInfo>();
    private List<BarInfo> barListTopRight = new ArrayList<BarInfo>();
    private List<BarInfo> barListBottomLeft = new ArrayList<BarInfo>();
    private List<BarInfo> barListBottomRight = new ArrayList<BarInfo>();
    private List<BarInfo> barListMiddleLeft = new ArrayList<BarInfo>();
    private List<BarInfo> barListMiddleRight = new ArrayList<BarInfo>();

    /**
     * 遍历渲染各个进度条
     */
    public void render(DrawContext ctx)
    {
        float[] basePos = new float[2];

        basePos[0] = 2f + 14f;
        basePos[1] = 2f;
        barListTopLeft.forEach((BarInfo barInfo) ->
        {
            BarRenderer.drawBar(ctx, basePos[0], basePos[1], 1f, barInfo);
            basePos[1] += 10f;
        });

        basePos[0] = ctx.getScaledWindowWidth() - 2f - 14f;
        basePos[1] = 2f;
        barListTopRight.forEach((BarInfo barInfo) ->
        {
            BarRenderer.drawBarContrarily(ctx, basePos[0], basePos[1], 1f, barInfo);
            basePos[1] += 10f;
        });

        basePos[0] = 2f + 14f;
        basePos[1] = ctx.getScaledWindowHeight() - 2f;
        barListBottomLeft.forEach((BarInfo barInfo) ->
        {
            BarRenderer.drawBar(ctx, basePos[0], basePos[1] - BarRenderer.getBarBackgroundHeight(barInfo.backgroundType), 1f, barInfo);
            basePos[1] -= 10f;
        });

        basePos[0] = ctx.getScaledWindowWidth() - 2f - 14f;
        basePos[1] = ctx.getScaledWindowHeight() - 2f;
        barListBottomRight.forEach((BarInfo barInfo) ->
        {
            BarRenderer.drawBarContrarily(ctx, basePos[0],
                    basePos[1] - BarRenderer.getBarBackgroundHeight(barInfo.backgroundType), 1f, barInfo);
            basePos[1] -= 10f;
        });

        basePos[0] = ctx.getScaledWindowWidth() / 2f - 96f;
        basePos[1] = ctx.getScaledWindowHeight() - 50f;
        barListMiddleLeft.forEach((BarInfo barInfo) ->
        {
            BarRenderer.drawBar(ctx, basePos[0], basePos[1], 1f, barInfo);
            basePos[1] -= 10f;
        });

        basePos[0] = ctx.getScaledWindowWidth() / 2f + 92f;
        basePos[1] = ctx.getScaledWindowHeight() - 50f;
        barListMiddleRight.forEach((BarInfo barInfo) ->
        {
            BarRenderer.drawBarContrarily(ctx, basePos[0], basePos[1], 1f, barInfo);
            basePos[1] -= 10f;
        });
    }

    /**
     * 增加进度条信息，适用于无裁剪图标。
     * 需要注意的是，由于修改进度条顺序过于难设计，故一经添加顺序便不再修改（上方进度条从上方往下一个个添加，下方则反过来）。
     * 注意，当且仅当传入的图标贴图长宽为9x9时才使用该方法！
     * @param color 进度条颜色
     * @param maxProgress 进度条进度
     * @param place 进度条所在位置
     * @param backgroundType 进度条背景类型
     * @param contentType 进度条内容类型
     * @param icon 进度条图标
     * @return 新加入进来的BarInfo实例
     * @exception RuntimeException 不可重名
     */
    public BarInfo addBarInfo(String name, int color, int maxProgress, BarPlace place, BarBackgroundType backgroundType, BarContentType contentType, Identifier icon)
    {
        if (findBar(name) != null)
        {
            throw new RuntimeException("Duplicate bar name: " + name);
        }

        BarInfo barInfo = new BarInfo(name, color, maxProgress, backgroundType, contentType, icon);

        switch (place)
        {
            case TopLeft:
                barListTopLeft.add(barInfo);
                break;
            case TopRight:
                barListTopRight.add(barInfo);
                break;
            case BottomLeft:
                barListBottomLeft.add(barInfo);
                break;
            case BottomRight:
                barListBottomRight.add(barInfo);
                break;
            case MiddleLeft:
                barListMiddleLeft.add(barInfo);
                break;
            case MiddleRight:
                barListMiddleRight.add(barInfo);
                break;
        }

        return barInfo;
    }

    /**
     * 增加进度条信息，适用于有裁剪图标。
     * 需要注意的是，由于修改进度条顺序过于难设计，故一经添加顺序便不再修改（上方进度条从上方往下一个个添加，下方则反过来）。
     * 一般来说，建议裁剪后的图标呈正方形，因为渲染时会直接将其压缩成9x9。
     * @param color 进度条颜色
     * @param maxProgress 进度条进度
     * @param place 进度条所在位置
     * @param backgroundType 进度条背景类型
     * @param contentType 进度条内容类型
     * @param icon 进度条图标
     * @param iconU 进度条图标在贴图的横向起始位置
     * @param iconV 进度条图标在贴图的纵向起始位置
     * @param iconWidth 进度条图标在贴图中的长度
     * @param iconHeight 进度条图标在贴图中的宽度
     * @param fromWidth 进度条图标所在贴图的宽度
     * @param fromHeight 进度条图标所在贴图的高度
     * @return 新加入进来的BarInfo实例
     * @exception RuntimeException 不可重名
     */
    public BarInfo addBarInfo(String name, int color, int maxProgress, BarPlace place, BarBackgroundType backgroundType, BarContentType contentType, Identifier icon, int iconU, int iconV, int iconWidth, int iconHeight, int fromWidth, int fromHeight)
    {
        if (findBar(name) != null)
        {
            throw new RuntimeException("Duplicate bar name: " + name);
        }

        BarInfo barInfo = new BarInfo(name, color, maxProgress, backgroundType, contentType, icon, iconU, iconV, iconWidth, iconHeight, fromWidth, fromHeight);

        switch (place)
        {
            case TopLeft:
                barListTopLeft.add(barInfo);
                break;
            case TopRight:
                barListTopRight.add(barInfo);
                break;
            case BottomLeft:
                barListBottomLeft.add(barInfo);
                break;
            case BottomRight:
                barListBottomRight.add(barInfo);
                break;
            case MiddleLeft:
                barListMiddleLeft.add(barInfo);
                break;
            case MiddleRight:
                barListMiddleRight.add(barInfo);
                break;
        }

        return barInfo;
    }

    /**
     * 增加进度条信息，直接以现成BarInfo实例为参数
     * 需要注意的是，由于修改进度条顺序过于难设计，故一经添加顺序便不再修改（上方进度条从上方往下一个个添加，下方则反过来）。
     * @param barInfo 现成BarInfo实例
     * @param place 进度条所在位置
     */
    public void addBarInfo(BarInfo barInfo, BarPlace place)
    {
        if (findBar(barInfo.name) != null)
        {
            throw new RuntimeException("Duplicate bar name: " + barInfo.name);
        }

        switch (place)
        {
            case TopLeft:
                barListTopLeft.add(barInfo);
                break;
            case TopRight:
                barListTopRight.add(barInfo);
                break;
            case BottomLeft:
                barListBottomLeft.add(barInfo);
                break;
            case BottomRight:
                barListBottomRight.add(barInfo);
                break;
            case MiddleLeft:
                barListMiddleLeft.add(barInfo);
                break;
            case MiddleRight:
                barListMiddleRight.add(barInfo);
                break;
        }
    }

    /**
     * 通过名字寻找特定Bar
     * @param name 用于查询的名字
     * @return 查找结果，如果有就返回该BarInfo实例，否则返回null
     */
    public BarInfo findBar(String name)
    {
        BarInfo result;

        result = barListTopLeft.stream().filter((barInfo) -> (barInfo.name.equals(name)))
                .findFirst().orElse(null);
        if (result != null)
        {
            return result;
        }

        result = barListTopRight.stream().filter((barInfo) -> (barInfo.name.equals(name)))
                .findFirst().orElse(null);
        if (result != null)
        {
            return result;
        }

        result = barListBottomLeft.stream().filter((barInfo) -> (barInfo.name.equals(name)))
                .findFirst().orElse(null);
        if (result != null)
        {
            return result;
        }

        result = barListBottomRight.stream().filter((barInfo) -> (barInfo.name.equals(name)))
                .findFirst().orElse(null);
        if (result != null)
        {
            return result;
        }

        result = barListMiddleLeft.stream().filter((barInfo) -> (barInfo.name.equals(name)))
                .findFirst().orElse(null);
        if (result != null)
        {
            return result;
        }

        result = barListMiddleRight.stream().filter((barInfo) -> (barInfo.name.equals(name)))
                .findFirst().orElse(null);
        if (result != null)
        {
            return result;
        }

        return null;
    }

    public void remove(String name)
    {
        if (barListTopLeft.removeIf((barInfo) -> (barInfo.name.equals(name))))
        {
            return;
        }

        if (barListTopRight.removeIf((barInfo) -> (barInfo.name.equals(name))))
        {
            return;
        }

        if (barListBottomLeft.removeIf((barInfo) -> (barInfo.name.equals(name))))
        {
            return;
        }

        if (barListBottomRight.removeIf((barInfo) -> (barInfo.name.equals(name))))
        {
            return;
        }

        if (barListMiddleLeft.removeIf((barInfo) -> (barInfo.name.equals(name))))
        {
            return;
        }
        barListMiddleRight.removeIf((barInfo) -> (barInfo.name.equals(name)));
    }
}
