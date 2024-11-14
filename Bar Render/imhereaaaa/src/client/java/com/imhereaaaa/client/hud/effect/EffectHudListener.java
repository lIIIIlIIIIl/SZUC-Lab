package com.imhereaaaa.client.hud.effect;

import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.effect.StatusEffectInstance;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;

public class EffectHudListener implements HudRenderCallback {
    private static final List<Predicate<EffectHudModel>> filters = new ArrayList<>();
    private static final EffectHudConfig config = EffectHudConfig.GetInstance();

    public static void addFilter(Predicate<EffectHudModel> filter) {
        filters.add(filter);
    }

    private final List<EffectHudModel> effectModels = new ArrayList<>();

    @Override
    public void onHudRender(DrawContext context, float tickDelta) {
        MinecraftClient client = MinecraftClient.getInstance();
        ClientPlayerEntity player = client.player;
        if (player != null) {
            if (player.getStatusEffects().isEmpty()) return;
            int y = client.getWindow().getScaledHeight() / 5;

            prepareList(player.getStatusEffects());

            for (EffectHudModel effect : effectModels) {
                boolean apply = true;
                for (Predicate<EffectHudModel> filter : filters) {
                    if (!filter.test(effect)) {
                        apply = false;
                        break;
                    }
                }
                if (!apply) continue;

                EffectHudConfig configTemp = config.getConfigByName(effect.getName());
                EffectHudConfig subConfig = configTemp == null ? config : configTemp;

                boolean print = switch (subConfig.getTypeNotNull()) {
                    case 1 -> true;
                    case 2 -> effect.getDuration() != -1;
                    default -> false;
                };

                if (print) {
                    EffectHudPrinter.printHud(context, 0, y, 0x80000000,
                            effect.getDuration(), effect.getTotalDuration(), subConfig.getColorIntNotNull(), effect);
                    y += 10;
                }
            }
        }
    }

    private void prepareList(Collection<StatusEffectInstance> statusEffectInstances) {
        if (statusEffectInstances.isEmpty()) return;

        List<EffectHudModel> tempList = new ArrayList<>();
        for (StatusEffectInstance statusEffect : statusEffectInstances) {
            EffectHudModel effectModel = new EffectHudModel(statusEffect);
            int i = effectModels.indexOf(effectModel);
            if (i != -1) {
                EffectHudModel find = effectModels.get(i);
                effectModel.setTotalDuration(find.getTotalDuration());
                tempList.add(effectModel);
            } else {
                tempList.add(effectModel);
            }
        }
        effectModels.clear();
        effectModels.addAll(tempList);
    }
}
