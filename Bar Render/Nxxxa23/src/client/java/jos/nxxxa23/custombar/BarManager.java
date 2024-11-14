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
     * 增加进度条信息，直接以现成BarInfo实例为参数
     * 需要注意的是，由于修改进度条顺序过于难设计，故一经添加顺序便不再修改（上方进度条从上方往下一个个添加，下方则反过来）。
     * @param barInfo 现成BarInfoAbstract实例
     * @param place 进度条所在位置
     */
    public BarInfoAbstract addBarInfo(BarInfoAbstract barInfo, BarPlace place)
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
