package com.imhereaaaa.client.internal.hud.effect;

import com.imhereaaaa.client.ModRenderClient;
import com.imhereaaaa.client.pkg.util.ModelAttrChecker;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class Printer {
    public static final String ICON_PATH = "textures/hud/icon/";

    private final DrawContext context;
    private final int x;
    private final int y;
    private final int width;
    private final int height;
    private final int remainingTime;
    private final int totalTime;
    private final int backgroundColor;
    private final int progressColor;
    private final String text;
    private final Identifier icon;

    private Printer(Builder builder) {
        this.context = builder.context;
        this.x = builder.x;
        this.y = builder.y;
        this.width = builder.width;
        this.height = builder.height;
        this.remainingTime = builder.remainingTime;
        this.totalTime = builder.totalTime;
        this.backgroundColor = builder.backgroundColor;
        this.progressColor = builder.progressColor;
        this.text = builder.text.toString();

        String path = ICON_PATH + builder.icon;
        this.icon = new Identifier(ModRenderClient.MOD_ID, path);
    }

    public void Print() {
        context.drawTexture(icon, x, y, 0, 0, 10, 10, 10, 10);
        int x = this.x + 10;

        MatrixStack matrixStack = context.getMatrices();
        TextRenderer textRenderer = MinecraftClient.getInstance().textRenderer;

        float progress = (float) remainingTime / totalTime;  // 进度从 0 到 1

        context.fill(x, y, x + width, y + height, backgroundColor);
        context.fill(x, y, x + (int) (width * progress), y + height, progressColor);

        matrixStack.push();
        context.drawText(textRenderer, text, x, y, 0xFFFFFFFF, true);
        matrixStack.pop();
    }

    public static Builder getBuilder(DrawContext context) {
        return new Builder(context);
    }

    public static class Builder {
        private final DrawContext context;
        private Integer x = 0;
        private Integer y;
        private Integer width = 100;
        private Integer height = 10;
        private Integer remainingTime;
        private Integer totalTime;
        private Integer backgroundColor;
        private Integer progressColor;
        private String icon;
        private final StringBuilder text = new StringBuilder();

        public Builder(DrawContext context) {
            this.context = context;
        }

        public Builder x(int x) {
            this.x = x;
            return this;
        }

        public Builder y(int y) {
            this.y = y;
            return this;
        }

        public Builder width(int width) {
            this.width = width;
            return this;
        }

        public Builder height(int height) {
            this.height = height;
            return this;
        }

        public Builder remainingTime(int remainingTime) {
            this.remainingTime = remainingTime;
            return this;
        }

        public Builder totalTime(int totalTime) {
            this.totalTime = totalTime;
            return this;
        }

        public Builder backgroundColor(int backgroundColor) {
            this.backgroundColor = backgroundColor;
            return this;
        }

        public Builder progressColor(int progressColor) {
            this.progressColor = progressColor;
            return this;
        }

        public Builder icon(String icon) {
            this.icon = icon;
            return this;
        }

        public Builder appendText(String text) {
            this.text.append(text);
            return this;
        }

        public Printer build() {
            if (ModelAttrChecker.isAllNonNull(this)) {
                return new Printer(this);
            }

            throw new IllegalArgumentException("attr is null");
        }
    }
}
