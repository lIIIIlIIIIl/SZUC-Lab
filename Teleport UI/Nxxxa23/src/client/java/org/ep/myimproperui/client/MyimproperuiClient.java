package org.ep.myimproperui.client;

import io.github.itzispyder.improperui.ImproperUIAPI;
import io.github.itzispyder.improperui.render.ImproperUIPanel;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.dimension.DimensionTypes;
import org.ep.myimproperui.ModConstants;
import org.ep.myimproperui.Myimproperui;
import org.lwjgl.glfw.GLFW;

public class MyimproperuiClient implements ClientModInitializer
{
    public KeyBinding keyBinding;
    public ImproperUIPanel panel;

    public enum ChangePageOperation
    {
        ToOverWorldPage,
        ToNetherPage,
        ToTheEndPage,
    }

    public static ChangePageOperation lastOperation;
    public static ChangePageOperation nextOperation;

    @Override
    public void onInitializeClient()
    {
        ModItems.initialize();

        ImproperUIAPI.init(ModConstants.MOD_ID, Myimproperui.class,
                 "assets/myimproperui/improperui/overworld_page.ui",
                            "assets/myimproperui/improperui/nether_page.ui",
                            "assets/myimproperui/improperui/theend_page.ui");


        ClientTickEvents.END_CLIENT_TICK.register(client ->
        {
            if (lastOperation != nextOperation)
            {
                switch (nextOperation)
                {
                    case ToOverWorldPage:
                        ImproperUIAPI.parseAndRunFile(ModConstants.MOD_ID, "overworld_page.ui", new TestCallBacks());
                        break;
                    case ToNetherPage:
                        ImproperUIAPI.parseAndRunFile(ModConstants.MOD_ID, "nether_page.ui", new TestCallBacks());
                        break;
                    case ToTheEndPage:
                        ImproperUIAPI.parseAndRunFile(ModConstants.MOD_ID, "theend_page.ui", new TestCallBacks());
                        break;
                }
                lastOperation = nextOperation;
            }

            if (client.world != null)
            {

            }
        });
    }
}
