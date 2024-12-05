package org.ep.myimproperui.client;

import io.github.itzispyder.improperui.ImproperUIAPI;
import io.github.itzispyder.improperui.script.CallbackHandler;
import io.github.itzispyder.improperui.script.CallbackListener;
import io.github.itzispyder.improperui.script.events.MouseEvent;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.client.MinecraftClient;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.dimension.DimensionTypes;
import org.ep.myimproperui.NetworkingConstants;

public class TestCallBacks implements CallbackListener
{
    @CallbackHandler
    public void changePageToOverWorld(MouseEvent e)
    {
        if (e.input.isDown())
        {
            MyimproperuiClient.nextOperation = MyimproperuiClient.ChangePageOperation.ToOverWorldPage;
            MinecraftClient.getInstance().setScreen(null);
        }
    }

    @CallbackHandler
    public void changePageToNether(MouseEvent e)
    {
        if (e.input.isDown())
        {
            MyimproperuiClient.nextOperation = MyimproperuiClient.ChangePageOperation.ToNetherPage;
            MinecraftClient.getInstance().setScreen(null);
        }
    }

    @CallbackHandler
    public void changePageToTheEnd(MouseEvent e)
    {
        if (e.input.isDown())
        {
            MyimproperuiClient.nextOperation = MyimproperuiClient.ChangePageOperation.ToTheEndPage;
            MinecraftClient.getInstance().setScreen(null);
        }
    }

    @CallbackHandler
    public void transportToOverWorld(MouseEvent e)
    {
        PacketByteBuf buf = PacketByteBufs.create();
        buf.writeString("overworld");

        System.out.println("get1");
        ClientPlayNetworking.send(NetworkingConstants.TRANSPORT_TO_DIMENSION, buf);
    }

    @CallbackHandler
    public void transportToNether(MouseEvent e)
    {
        PacketByteBuf buf = PacketByteBufs.create();
        buf.writeString("nether");

        System.out.println("get2");
        ClientPlayNetworking.send(NetworkingConstants.TRANSPORT_TO_DIMENSION, buf);
    }

    @CallbackHandler
    public void transportToTheEnd(MouseEvent e)
    {
        PacketByteBuf buf = PacketByteBufs.create();
        buf.writeString("theend");

        System.out.println("get3");
        ClientPlayNetworking.send(NetworkingConstants.TRANSPORT_TO_DIMENSION, buf);
    }
}
