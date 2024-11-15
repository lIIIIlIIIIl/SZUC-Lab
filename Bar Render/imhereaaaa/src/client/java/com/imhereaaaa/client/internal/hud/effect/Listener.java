package com.imhereaaaa.client.internal.hud.effect;

import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.effect.StatusEffectInstance;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;

public class Listener implements HudRenderCallback {
    private static final List<Predicate<Model>> filters = new ArrayList<>();
    private static final Config config = Config.GetInstance();

    public static void addFilter(Predicate<Model> filter) {
        filters.add(filter);
    }

    private final List<Model> models = new ArrayList<>();

    @Override
    public void onHudRender(DrawContext context, float tickDelta) {
        MinecraftClient client = MinecraftClient.getInstance();
        ClientPlayerEntity player = client.player;
        if (player != null) {
            if (player.getStatusEffects().isEmpty()) return;
            int y = client.getWindow().getScaledHeight() / 5;

            prepareList(player.getStatusEffects());

            for (Model effect : models) {
                boolean apply = true;
                for (Predicate<Model> filter : filters) {
                    if (!filter.test(effect)) {
                        apply = false;
                        break;
                    }
                }
                if (!apply) continue;

                Config configTemp = config.getConfigById(effect.getId());
                Config subConfig = configTemp == null ? config : configTemp;

                boolean print = switch (subConfig.getTypeNotNull()) {
                    case 1 -> true;
                    case 2 -> effect.getDuration() != -1;
                    default -> false;
                };

                if (print) {
                    Printer.Builder builder = Printer.getBuilder(context)
                            .y(y)
                            .remainingTime(effect.getDuration())
                            .totalTime(effect.getTotalDuration())
                            .backgroundColor(0x80000000)
                            .progressColor(subConfig.getColorIntNotNull())
                            .appendText(subConfig.getName() == null ? effect.getName() : subConfig.getName())
                            .icon(subConfig.getIconNotNull());

                    if (effect.getDuration() != -1) {
                        builder.appendText(" ")
                                .appendText(effect.getDuration() / 20 + "")
                                .appendText("/")
                                .appendText(effect.getTotalDuration() / 20 + "");
                    }

                    builder.build()
                            .Print();
                    y += 10;
                }
            }
        }
    }

    private void prepareList(Collection<StatusEffectInstance> statusEffectInstances) {
        if (statusEffectInstances.isEmpty()) return;

        List<Model> tempList = new ArrayList<>();
        for (StatusEffectInstance statusEffect : statusEffectInstances) {
            Model effectModel = new Model(statusEffect);
            int i = models.indexOf(effectModel);
            if (i != -1) {
                Model find = models.get(i);
                effectModel.setTotalDuration(find.getTotalDuration());
                tempList.add(effectModel);
            } else {
                tempList.add(effectModel);
            }
        }
        models.clear();
        models.addAll(tempList);
    }
}
