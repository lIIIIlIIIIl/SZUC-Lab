package com.imhereaaaa.client.internal.hud.effect;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;

public class Model extends StatusEffectInstance {
    private int totalDuration;

    public Model(StatusEffectInstance instance) {
        super(instance);
        totalDuration = instance.getDuration();
    }

    public Model(StatusEffectInstance instance, int totalDuration) {
        super(instance);
        this.totalDuration = totalDuration;
    }

    public int getTotalDuration() {
        return totalDuration;
    }

    public void setTotalDuration(int totalDuration) {
        this.totalDuration = totalDuration;
    }

    public String getId() {
        Identifier id = Registries.STATUS_EFFECT.getId(this.getEffectType());
        assert id != null;
        return id.toString();
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
        Model that = (Model) o;
        return this.getEffectType().getName().equals(that.getEffectType().getName());
    }

    @Override
    public int hashCode() {
        return this.getEffectType().getName().hashCode();
    }
}
