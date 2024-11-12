package jos.nxxxa23.custombar;

import jos.nxxxa23.custombar.info.BarInfo;
import jos.nxxxa23.custombar.info.BarInfoAbstract;
import jos.nxxxa23.custombar.info.BarInfoTracked;
import net.minecraft.client.gui.DrawContext;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.util.Identifier;
import jos.nxxxa23.custombar.info.BarInfoAbstract.*;

public class BarManager
{
    //各个位置的进度条List
    private List<BarInfoAbstract> barListTopLeft = new ArrayList<>();
    private List<BarInfoAbstract> barListTopRight = new ArrayList<>();
    private List<BarInfoAbstract> barListBottomLeft = new ArrayList<>();
    private List<BarInfoAbstract> barListBottomRight = new ArrayList<>();
    private List<BarInfoAbstract> barListMiddleLeft = new ArrayList<>();
    private List<BarInfoAbstract> barListMiddleRight = new ArrayList<>();

    /**
     * 遍历渲染各个进度条
     */
    public void render(DrawContext ctx)
    {
        float[] basePos = new float[2];

        basePos[0] = 2f + 14f;
        basePos[1] = 2f;
        barListTopLeft.forEach((BarInfoAbstract barInfo) ->
        {
            BarRenderer.drawBar(ctx, basePos[0], basePos[1], 1f, barInfo);
            basePos[1] += 10f;
        });

        basePos[0] = ctx.getScaledWindowWidth() - 2f - 14f;
        basePos[1] = 2f;
        barListTopRight.forEach((BarInfoAbstract barInfo) ->
        {
            BarRenderer.drawBarContrarily(ctx, basePos[0], basePos[1], 1f, barInfo);
            basePos[1] += 10f;
        });

        basePos[0] = 2f + 14f;
        basePos[1] = ctx.getScaledWindowHeight() - 2f;
        barListBottomLeft.forEach((BarInfoAbstract barInfo) ->
        {
            BarRenderer.drawBar(ctx, basePos[0], basePos[1] - BarRenderer.getBarBackgroundHeight(barInfo.getBackgroundType()), 1f, barInfo);
            basePos[1] -= 10f;
        });

        basePos[0] = ctx.getScaledWindowWidth() - 2f - 14f;
        basePos[1] = ctx.getScaledWindowHeight() - 2f;
        barListBottomRight.forEach((BarInfoAbstract barInfo) ->
        {
            BarRenderer.drawBarContrarily(ctx, basePos[0],
                    basePos[1] - BarRenderer.getBarBackgroundHeight(barInfo.getBackgroundType()), 1f, barInfo);
            basePos[1] -= 10f;
        });

        basePos[0] = ctx.getScaledWindowWidth() / 2f - 96f;
        basePos[1] = ctx.getScaledWindowHeight() - 50f;
        barListMiddleLeft.forEach((BarInfoAbstract barInfo) ->
        {
            BarRenderer.drawBar(ctx, basePos[0], basePos[1], 1f, barInfo);
            basePos[1] -= 10f;
        });

        basePos[0] = ctx.getScaledWindowWidth() / 2f + 92f;
        basePos[1] = ctx.getScaledWindowHeight() - 50f;
        barListMiddleRight.forEach((BarInfoAbstract barInfo) ->
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
     * @param minProgress 进度条最小进度
     * @param maxProgress 进度条最大进度
     * @param place 进度条所在位置
     * @param backgroundType 进度条背景类型
     * @param contentType 进度条内容类型
     * @param icon 进度条图标
     * @return 新加入进来的BarInfo实例
     * @exception RuntimeException 不可重名
     */
    public <T extends Number> BarInfo addBarInfo(String name, int color, T minProgress, T maxProgress, BarPlace place, BarBackgroundType backgroundType, BarContentType contentType, Identifier icon)
    {
        if (findBar(name) != null)
        {
            throw new RuntimeException("Duplicate bar name: " + name);
        }

        BarInfo barInfo = new BarInfo(name, color, minProgress, maxProgress, backgroundType, contentType, icon);

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
     * @param minProgress 进度条最小进度
     * @param maxProgress 进度条最大进度
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
    public <T extends Number> BarInfo<T> addBarInfo(String name, int color, T minProgress, T maxProgress, BarPlace place, BarBackgroundType backgroundType, BarContentType contentType, Identifier icon, int iconU, int iconV, int iconWidth, int iconHeight, int fromWidth, int fromHeight)
    {
        if (findBar(name) != null)
        {
            throw new RuntimeException("Duplicate bar name: " + name);
        }

        BarInfo<T> barInfo = new BarInfo<>(name, color, minProgress, maxProgress, backgroundType, contentType, icon, iconU, iconV, iconWidth, iconHeight, fromWidth, fromHeight);

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
     * @param barInfo 现成BarInfoAbstract实例
     * @param place 进度条所在位置
     */
    public void addBarInfo(BarInfoAbstract barInfo, BarPlace place)
    {
        if (findBar(barInfo.getName()) != null)
        {
            throw new RuntimeException("Duplicate bar name: " + barInfo.getName());
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
     * 增加追踪进度条信息，适用于无裁剪图标。
     * 需要注意的是，由于修改进度条顺序过于难设计，故一经添加顺序便不再修改（上方进度条从上方往下一个个添加，下方则反过来）。
     * 注意，当且仅当传入的图标贴图长宽为9x9时才使用该方法！
     * @param color 进度条颜色
     * @param maxProgress 进度条最大进度
     * @param curProgressTracked 当前进度追踪
     * @param dataTracker 当前进度所在追踪
     * @param place 进度条所在位置
     * @param backgroundType 进度条背景类型
     * @param contentType 进度条内容类型
     * @param icon 进度条图标
     * @return 新加入进来的BarInfo实例
     * @exception RuntimeException 不可重名
     */
    public <T extends Number> BarInfoTracked addBarInfo(String name, int color, T minProgress, T maxProgress, TrackedData<T> curProgressTracked, DataTracker dataTracker, BarPlace place, BarBackgroundType backgroundType, BarContentType contentType, Identifier icon)
    {
        if (findBar(name) != null)
        {
            throw new RuntimeException("Duplicate bar name: " + name);
        }

        BarInfoTracked barInfo = new BarInfoTracked(name, color, minProgress, maxProgress, curProgressTracked, dataTracker, backgroundType, contentType, icon);

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
     * 增加追踪进度条信息，适用于有裁剪图标。
     * 需要注意的是，由于修改进度条顺序过于难设计，故一经添加顺序便不再修改（上方进度条从上方往下一个个添加，下方则反过来）。
     * 一般来说，建议裁剪后的图标呈正方形，因为渲染时会直接将其压缩成9x9。
     * @param color 进度条颜色
     * @param maxProgress 进度条进度
     * @param curProgressTracked 当前进度追踪
     * @param dataTracker 当前进度所在追踪
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
    public <T extends Number> BarInfoTracked<T> addBarInfo(String name, int color, T minProgress, T maxProgress, TrackedData<T> curProgressTracked, DataTracker dataTracker, BarPlace place, BarBackgroundType backgroundType, BarContentType contentType, Identifier icon, int iconU, int iconV, int iconWidth, int iconHeight, int fromWidth, int fromHeight)
    {
        if (findBar(name) != null)
        {
            throw new RuntimeException("Duplicate bar name: " + name);
        }

        BarInfoTracked<T> barInfo = new BarInfoTracked<>(name, color, minProgress, maxProgress, curProgressTracked, dataTracker, backgroundType, contentType, icon);

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
     * 通过名字寻找特定Bar
     * @param name 用于查询的名字
     * @return 查找结果，如果有就返回该BarInfoAbstract实例，否则返回null
     */
    public BarInfoAbstract findBar(String name)
    {
        BarInfoAbstract result;

        result = barListTopLeft.stream().filter((barInfo) -> (barInfo.getName().equals(name)))
                .findFirst().orElse(null);
        if (result != null)
        {
            return result;
        }

        result = barListTopRight.stream().filter((barInfo) -> (barInfo.getName().equals(name)))
                .findFirst().orElse(null);
        if (result != null)
        {
            return result;
        }

        result = barListBottomLeft.stream().filter((barInfo) -> (barInfo.getName().equals(name)))
                .findFirst().orElse(null);
        if (result != null)
        {
            return result;
        }

        result = barListBottomRight.stream().filter((barInfo) -> (barInfo.getName().equals(name)))
                .findFirst().orElse(null);
        if (result != null)
        {
            return result;
        }

        result = barListMiddleLeft.stream().filter((barInfo) -> (barInfo.getName().equals(name)))
                .findFirst().orElse(null);
        if (result != null)
        {
            return result;
        }

        result = barListMiddleRight.stream().filter((barInfo) -> (barInfo.getName().equals(name)))
                .findFirst().orElse(null);
        if (result != null)
        {
            return result;
        }

        return null;
    }

    public void remove(String name)
    {
        if (barListTopLeft.removeIf((barInfo) -> (barInfo.getName().equals(name))))
        {
            return;
        }

        if (barListTopRight.removeIf((barInfo) -> (barInfo.getName().equals(name))))
        {
            return;
        }

        if (barListBottomLeft.removeIf((barInfo) -> (barInfo.getName().equals(name))))
        {
            return;
        }

        if (barListBottomRight.removeIf((barInfo) -> (barInfo.getName().equals(name))))
        {
            return;
        }

        if (barListMiddleLeft.removeIf((barInfo) -> (barInfo.getName().equals(name))))
        {
            return;
        }
        barListMiddleRight.removeIf((barInfo) -> (barInfo.getName().equals(name)));
    }
}
