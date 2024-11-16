package com.imhereaaaa.client.internal.hud.effect;

import com.google.gson.Gson;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Objects;
import java.util.Set;

public class Config {
    private static Config instance;

    public static int DEFAULT_TYPE;
    public static int DEFAULT_COLOR;
    public static String DEFAULT_ICON;

    public synchronized static Config GetInstance() {
        if (instance == null) {
            try (InputStream is = Listener.class.getClassLoader().getResourceAsStream("effect_hud.json")) {
                assert is != null;
                try (Reader reader = new InputStreamReader(is)) {
                    Gson gson = new Gson();
                    instance = gson.fromJson(reader, Config.class);
                    DEFAULT_TYPE = instance.getType();
                    DEFAULT_COLOR = instance.getColorIntNotNull();
                    DEFAULT_ICON = instance.getIcon();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return instance;
    }

    private Config() {}

    private String id;
    private String name;
    private Integer type;
    private String color;
    private String icon;
    private Set<Config> sub;

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

    public Integer getColorInt() {
        return (int) Long.parseLong(color.substring(2), 16);
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

    public Set<Config> getSub() {
        return sub;
    }

    public void setSub(Set<Config> sub) {
        this.sub = sub;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Config that = (Config) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }

    public Config getConfigById(String id) {
        for (Config config : sub) {
            if (config.id.equals(id)) {
                return config;
            }
        }
        return null;
    }
}
