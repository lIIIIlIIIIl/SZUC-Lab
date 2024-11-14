package com.imhereaaaa.client.hud.effect;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Objects;
import java.util.Set;

public class EffectHudConfig {
    private static EffectHudConfig instance;

    public static int DEFAULT_TYPE = 1;
    public static int DEFAULT_COLOR = 0xFFFF0000;
    public static String DEFAULT_ICON = null;

    public synchronized static EffectHudConfig GetInstance() {
        if (instance == null) {
            try (InputStream is = EffectHudListener.class.getClassLoader().getResourceAsStream("effect_hud.json")) {
                assert is != null;
                try (Reader reader = new InputStreamReader(is)) {
                    Gson gson = new Gson();
                    instance = gson.fromJson(reader, EffectHudConfig.class);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return instance;
    }

    private EffectHudConfig() {}

    private String name;
    private Integer type;
    private String color;
    private String icon;
    private Set<EffectHudConfig> sub;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getType() {
        return type;
    }

    public Integer getTypeNotNull() {
        return type == null ? DEFAULT_TYPE : type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getColor() {
        return color;
    }

    public Integer getColorIntNotNull() {
        return color == null ? DEFAULT_COLOR : (int) Long.parseLong(color.substring(2), 16);
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getIcon() {
        return icon;
    }

    public String getIconNotNull() {
        return icon == null ? DEFAULT_ICON : icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Set<EffectHudConfig> getSub() {
        return sub;
    }

    public void setSub(Set<EffectHudConfig> sub) {
        this.sub = sub;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EffectHudConfig that = (EffectHudConfig) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }

    public EffectHudConfig getConfigByName(String name) {
        for (EffectHudConfig effectHudConfig : sub) {
            if (effectHudConfig.name.equals(name)) {
                return effectHudConfig;
            }
        }
        return null;
    }
}
