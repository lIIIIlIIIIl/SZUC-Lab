package com.imhereaaaa.client.hud.effect.filters;

import com.imhereaaaa.client.hud.effect.EffectHudModel;

import java.util.function.Predicate;

public class ExampleFilter implements Predicate<EffectHudModel> {
    @Override
    public boolean test(EffectHudModel effectHudModel) {
        return true;
    }
}
