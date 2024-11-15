package com.imhereaaaa.client.internal.hud.effect.filters;

import com.imhereaaaa.client.internal.hud.effect.Model;

import java.util.function.Predicate;

public class Example implements Predicate<Model> {
    @Override
    public boolean test(Model model) {
        return true;
    }
}
