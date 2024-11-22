package org.bike.bar_render.config;

public class Position {
    public static manaBarEnum renderManaBar = manaBarEnum.Always;
    public enum manaBarEnum {
        Always, When_Needed, Never
    }
    public static int manaBarPositionX = 420;
    public static int manaBarPositionY = 3;
    public static int manaBarTextOffsetX = 16;
    public static int manaBarTextOffsetY = 35;
}

