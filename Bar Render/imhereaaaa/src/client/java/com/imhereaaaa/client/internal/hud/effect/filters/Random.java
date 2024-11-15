package com.imhereaaaa.client.internal.hud.effect.filters;

import com.imhereaaaa.client.internal.hud.effect.Model;

import java.util.function.Predicate;

public class Random implements Predicate<Model> {
    private final java.util.Random random = new java.util.Random();

    @Override
    public boolean test(Model model) {
        return random.nextInt(10) % 2 == 0;
    }
}
