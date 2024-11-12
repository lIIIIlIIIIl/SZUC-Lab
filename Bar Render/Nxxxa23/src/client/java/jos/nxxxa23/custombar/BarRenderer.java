package jos.nxxxa23.custombar;

import jos.nxxxa23.custombar.info.BarInfoAbstract;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.util.Identifier;
import jos.nxxxa23.custombar.info.BarInfoAbstract.*;

import java.text.DecimalFormat;

public class BarRenderer
{
    //进度条贴图集
    private static final Identifier BAR_TEXTURES = Identifier.of("mylib", "textures/bar.png");

    //进度条贴图集长宽
    private static final int BAR_TEXTURES_WIDTH = 256;
    private static final int BAR_TEXTURES_HEIGHT = 256;

    //进度条背景1在贴图的起始位置
    private static final int BAR_BACKGROUND1_U = 0;
    private static final int BAR_BACKGROUND1_V = 0;

    //进度条背景1长宽
    private static final int BAR_BACKGROUND1_WIDTH = 81;
    private static final int BAR_BACKGROUND1_HEIGHT = 9;

    //进度条背景2在贴图的起始位置
    private static final int BAR_BACKGROUND2_U = 0;
    private static final int BAR_BACKGROUND2_V = 18;

    //进度条背景2长宽
    private static final int BAR_BACKGROUND2_WIDTH = 81;
    private static final int BAR_BACKGROUND2_HEIGHT = 9;

    //进度条内容1在贴图的起始位置
    private static final int BAR_CONTENT1_U = 2;
    private static final int BAR_CONTENT1_V = 11;

    //进度条内容1长宽
    private static final int BAR_CONTENT1_WIDTH = 77;
    private static final int BAR_CONTENT1_HEIGHT = 5;

    //进度条内容2在贴图的起始位置
    private static final int BAR_CONTENT2_U = 1;
    private static final int BAR_CONTENT2_V = 28;

    //进度条内容2长宽
    private static final int BAR_CONTENT2_WIDTH = 79;
    private static final int BAR_CONTENT2_HEIGHT = 7;

    //进度条内容3在贴图的起始位置
    private static final int BAR_CONTENT3_U = 2;
    private static final int BAR_CONTENT3_V = 37;

    //进度条内容3长宽
    private static final int BAR_CONTENT3_WIDTH = 77;
    private static final int BAR_CONTENT3_HEIGHT = 5;

    public static void drawBar(DrawContext ctx, float x, float y, float barScale, BarInfoAbstract barInfo)
    {
        ctx.getMatrices().push();
        ctx.getMatrices().translate(x, y, 50f);
        ctx.getMatrices().push();
        ctx.getMatrices().scale(barScale, barScale, 1f);

        ctx.getMatrices().translate(0f, 4.5f, 0f);

        myDrawTextRightAligned(ctx, MinecraftClient.getInstance().textRenderer,
                String.valueOf(new DecimalFormat("#.0").format(barInfo.getCurProgress())), barInfo.getColor(), 14, 0.55f);

        ctx.getMatrices().translate(1f, -4.5f, 0f);

        ctx.drawTexture(barInfo.getIcon(),
                0, 0,
                9, 9,
                barInfo.getIconU(), barInfo.getIconV(),
                barInfo.getIconWidth(), barInfo.getIconHeight(),
                barInfo.getFromWidth(), barInfo.getFromHeight());

        ctx.getMatrices().translate(10, 0, 0);

        switch (barInfo.getBackgroundType())
        {
            case BACKGROUND1:
                ctx.drawTexture(BAR_TEXTURES,
                        0, 0,
                        BAR_BACKGROUND1_WIDTH, BAR_BACKGROUND1_HEIGHT,
                        BAR_BACKGROUND1_U, BAR_BACKGROUND1_V,
                        BAR_BACKGROUND1_WIDTH, BAR_BACKGROUND1_HEIGHT,
                        BAR_TEXTURES_WIDTH, BAR_TEXTURES_HEIGHT);
                break;
            case BACKGROUND2:
                ctx.drawTexture(BAR_TEXTURES,
                        0, 0,
                        BAR_BACKGROUND2_WIDTH, BAR_BACKGROUND2_HEIGHT,
                        BAR_BACKGROUND2_U, BAR_BACKGROUND2_V,
                        BAR_BACKGROUND2_WIDTH, BAR_BACKGROUND2_HEIGHT,
                        BAR_TEXTURES_WIDTH, BAR_TEXTURES_HEIGHT);
                break;
        }

        ctx.setShaderColor(((barInfo.getColor() >> 16) & 0x00FF) / 255.0f,
                ((barInfo.getColor() >> 8) & 0x0000FF) / 255.0f,
                (barInfo.getColor() & 0x000000FF) / 255.0f,
                ((barInfo.getColor() >> 24) & 0xFF) / 255.0f);
        switch (barInfo.getContentType())
        {
            case CONTENT1:
                ctx.drawTexture(BAR_TEXTURES,
                        2, 2,
                        Math.round(BAR_CONTENT1_WIDTH * barInfo.getProgressRatio()), BAR_CONTENT1_HEIGHT,
                        BAR_CONTENT1_U, BAR_CONTENT1_V,
                        BAR_CONTENT1_WIDTH, BAR_CONTENT1_HEIGHT,
                        BAR_TEXTURES_WIDTH, BAR_TEXTURES_HEIGHT);
                break;
            case CONTENT2:
                ctx.drawTexture(BAR_TEXTURES,
                        1, 1,
                        Math.round(BAR_CONTENT2_WIDTH * barInfo.getProgressRatio()), BAR_CONTENT2_HEIGHT,
                        BAR_CONTENT2_U, BAR_CONTENT2_V,
                        Math.round(BAR_CONTENT2_WIDTH * barInfo.getProgressRatio()), BAR_CONTENT2_HEIGHT,
                        BAR_TEXTURES_WIDTH, BAR_TEXTURES_HEIGHT);
                break;
            case CONTENT3:
                ctx.drawTexture(BAR_TEXTURES,
                        2, 2,
                        Math.round(BAR_CONTENT3_WIDTH * barInfo.getProgressRatio()), BAR_CONTENT3_HEIGHT,
                        BAR_CONTENT3_U, BAR_CONTENT3_V,
                        Math.round(BAR_CONTENT3_WIDTH * barInfo.getProgressRatio()), BAR_CONTENT3_HEIGHT,
                        BAR_TEXTURES_WIDTH, BAR_TEXTURES_HEIGHT);
                break;
        }
        ctx.setShaderColor(1f, 1f, 1f, 1f);
        ctx.getMatrices().pop();
        ctx.getMatrices().pop();
    }

    public static void drawBarContrarily(DrawContext ctx, float x, float y, float barScale, BarInfoAbstract barInfo)
    {
        ctx.getMatrices().push();
        ctx.getMatrices().translate(x, y, 50f);
        ctx.getMatrices().push();
        ctx.getMatrices().scale(barScale, barScale, 1f);

        ctx.getMatrices().translate(0f, 4.5f, 0f);

        myDrawTextLeftAligned(ctx, MinecraftClient.getInstance().textRenderer,
                String.valueOf(new DecimalFormat("#.0").format(barInfo.getCurProgress())), barInfo.getColor(), 14, 0.55f);

        ctx.getMatrices().translate(-10f, -4.5f, 0f);

        ctx.drawTexture(barInfo.getIcon(),
                0, 0,
                9, 9,
                barInfo.getIconU(), barInfo.getIconV(),
                barInfo.getIconWidth(), barInfo.getIconHeight(),
                barInfo.getFromWidth(), barInfo.getFromHeight());

        switch (barInfo.getBackgroundType())
        {
            case BACKGROUND1:
                ctx.getMatrices().translate(-BAR_BACKGROUND1_WIDTH - 1f, 0f, 0f);
                ctx.drawTexture(BAR_TEXTURES,
                        0, 0,
                        BAR_BACKGROUND1_WIDTH, BAR_BACKGROUND1_HEIGHT,
                        BAR_BACKGROUND1_U, BAR_BACKGROUND1_V,
                        BAR_BACKGROUND1_WIDTH, BAR_BACKGROUND1_HEIGHT,
                        BAR_TEXTURES_WIDTH, BAR_TEXTURES_HEIGHT);
                break;
            case BACKGROUND2:
                ctx.getMatrices().translate(-BAR_BACKGROUND2_WIDTH - 1f, 0f, 0f);
                ctx.drawTexture(BAR_TEXTURES,
                        0, 0,
                        BAR_BACKGROUND2_WIDTH, BAR_BACKGROUND2_HEIGHT,
                        BAR_BACKGROUND2_U, BAR_BACKGROUND2_V,
                        BAR_BACKGROUND2_WIDTH, BAR_BACKGROUND2_HEIGHT,
                        BAR_TEXTURES_WIDTH, BAR_TEXTURES_HEIGHT);
                break;
        }

        ctx.setShaderColor(((barInfo.getColor() >> 16) & 0x00FF) / 255.0f,
                ((barInfo.getColor() >> 8) & 0x0000FF) / 255.0f,
                (barInfo.getColor() & 0x000000FF) / 255.0f,
                ((barInfo.getColor() >> 24) & 0xFF) / 255.0f);

        int progressWidth;
        switch (barInfo.getContentType())
        {
            case CONTENT1:
                progressWidth = Math.round(BAR_CONTENT1_WIDTH * barInfo.getProgressRatio());
                ctx.drawTexture(BAR_TEXTURES,
                        2 + (BAR_CONTENT1_WIDTH - progressWidth),
                        2,
                        progressWidth, BAR_CONTENT1_HEIGHT,
                        BAR_CONTENT1_U, BAR_CONTENT1_V,
                        BAR_CONTENT1_WIDTH, BAR_CONTENT1_HEIGHT,
                        BAR_TEXTURES_WIDTH, BAR_TEXTURES_HEIGHT);
                break;
            case CONTENT2:
                progressWidth = Math.round(BAR_CONTENT2_WIDTH * barInfo.getProgressRatio());
                ctx.drawTexture(BAR_TEXTURES,
                        1 + (BAR_CONTENT2_WIDTH - progressWidth), 1,
                        progressWidth, BAR_CONTENT2_HEIGHT,
                        BAR_CONTENT2_U, BAR_CONTENT2_V,
                        progressWidth, BAR_CONTENT2_HEIGHT,
                        BAR_TEXTURES_WIDTH, BAR_TEXTURES_HEIGHT);
                break;
            case CONTENT3:
                progressWidth = Math.round(BAR_CONTENT3_WIDTH * barInfo.getProgressRatio());
                ctx.drawTexture(BAR_TEXTURES,
                        2 + (BAR_CONTENT3_WIDTH - progressWidth), 2,
                        progressWidth, BAR_CONTENT3_HEIGHT,
                        BAR_CONTENT3_U, BAR_CONTENT3_V,
                        progressWidth, BAR_CONTENT3_HEIGHT,
                        BAR_TEXTURES_WIDTH, BAR_TEXTURES_HEIGHT);
                break;
        }
        ctx.setShaderColor(1f, 1f, 1f, 1f);
        ctx.getMatrices().pop();
        ctx.getMatrices().pop();
    }

    public static int getBarContentWidth(BarContentType contentType)
    {
        return switch (contentType) {
            case CONTENT1 -> BAR_CONTENT1_WIDTH;
            case CONTENT2 -> BAR_CONTENT2_WIDTH;
            case CONTENT3 -> BAR_CONTENT3_WIDTH;
        };
    }

    public static int getBarContentHeight(BarContentType contentType)
    {
        return switch (contentType) {
            case CONTENT1 -> BAR_CONTENT1_HEIGHT;
            case CONTENT2 -> BAR_CONTENT2_HEIGHT;
            case CONTENT3 -> BAR_CONTENT3_HEIGHT;
        };
    }
    public static int getBarBackgroundWidth(BarBackgroundType backgroundType)
    {
        return switch (backgroundType) {
            case BACKGROUND1 -> BAR_BACKGROUND1_WIDTH;
            case BACKGROUND2 -> BAR_BACKGROUND2_WIDTH;
        };
    }

    public static int getBarBackgroundHeight(BarBackgroundType backgroundType)
    {
        return switch (backgroundType) {
            case BACKGROUND1 -> BAR_BACKGROUND1_HEIGHT;
            case BACKGROUND2 -> BAR_BACKGROUND2_HEIGHT;
        };
    }

    public static void myDrawTextLeftAligned(DrawContext ctx, TextRenderer tr, String text, int textColor, int maxWidth, float originalScale)
    {
        if ((tr.getWidth(text) * originalScale) <= maxWidth)
        {
            myDrawTextLeftAlignedHelper(ctx, tr, text, textColor, originalScale);
            return;
        }
        myDrawTextLeftAlignedHelper(ctx, tr, text, textColor, (float)maxWidth / tr.getWidth(text));
    }

    private static void myDrawTextLeftAlignedHelper(DrawContext ctx, TextRenderer tr, String text, int textColor, float scale)
    {
        ctx.getMatrices().push();
        ctx.getMatrices().scale(scale, scale, 1f);
        ctx.getMatrices().translate(0f, -tr.fontHeight / 2f, 0f);
        ctx.drawText(tr, text, 0, 0, textColor, true);
        ctx.getMatrices().pop();
    }

    public static void myDrawTextRightAligned(DrawContext ctx, TextRenderer tr, String text, int textColor, int maxWidth, float originalScale)
    {
        if ((tr.getWidth(text) * originalScale) <= maxWidth)
        {
            myDrawTextRightAlignedHelper(ctx, tr, text, textColor, originalScale);
            return;
        }
        myDrawTextRightAlignedHelper(ctx, tr, text, textColor, (float)maxWidth / tr.getWidth(text));
    }

    private static void myDrawTextRightAlignedHelper(DrawContext ctx, TextRenderer tr, String text, int textColor, float scale)
    {
        ctx.getMatrices().push();
        ctx.getMatrices().scale(scale, scale, 1f);
        ctx.getMatrices().translate(-tr.getWidth(text), -tr.fontHeight / 2f, 0f);
        ctx.drawText(tr, text, 0, 0, textColor, true);
        ctx.getMatrices().pop();
    }
}
