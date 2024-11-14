package com.imhereaaaa.client.hud.effect;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;

public class EffectHudModel extends StatusEffectInstance {
    private int totalDuration;

    public EffectHudModel(StatusEffectInstance instance) {
        super(instance);
        totalDuration = instance.getDuration();
    }

    public EffectHudModel(StatusEffectInstance instance, int totalDuration) {
        super(instance);
        this.totalDuration = totalDuration;
    }

    public int getTotalDuration() {
        return totalDuration;
    }

    public void setTotalDuration(int totalDuration) {
        this.totalDuration = totalDuration;
    }

    public String getName() {
        Identifier id = Registries.STATUS_EFFECT.getId(this.getEffectType());
        assert id != null;
        return id.getPath();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EffectHudModel that = (EffectHudModel) o;
        return this.getEffectType().getName().equals(that.getEffectType().getName());
    }

    @Override
    public int hashCode() {
        return this.getEffectType().getName().hashCode();
    }
}
