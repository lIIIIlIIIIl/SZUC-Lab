package org.ep.myimproperui;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockLocating;
import net.minecraft.world.PersistentState;
import net.minecraft.world.PersistentStateManager;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.dimension.DimensionTypes;

import java.util.Optional;


public class Myimproperui implements ModInitializer
{


    @Override
    public void onInitialize()
    {
        ServerPlayNetworking.registerGlobalReceiver(NetworkingConstants.TRANSPORT_TO_DIMENSION, (server, player, handler, buf, responseSender) ->
        {
            System.out.println("get4");
            String dimension = buf.readString();
            ServerWorld transportWorld = null;
            Vec3d teleportVec = player.getPos();

            double ratio;
            switch (dimension)
            {
                case "overworld":
                    transportWorld = server.getWorld(World.OVERWORLD);
                    ratio = DimensionType.getCoordinateScaleFactor(player.getWorld().getDimension(), transportWorld.getDimension());
                    teleportVec = new Vec3d(Math.round(teleportVec.x) * ratio, (teleportVec.y) < 64 ? 64 : teleportVec.y, Math.round(teleportVec.z * ratio));
                    if (transportWorld == null)
                    {
                        player.sendMessage(Text.translatable("message.myimproperui.overworld_not_found"));
                        return;
                    }
                    break;
                case "nether":
                    transportWorld = server.getWorld(World.NETHER);
                    ratio = DimensionType.getCoordinateScaleFactor(player.getWorld().getDimension(), transportWorld.getDimension());
                    teleportVec = new Vec3d(Math.round(teleportVec.x) * ratio, (teleportVec.y) < 32 ? 32 : teleportVec.y, Math.round(teleportVec.z * ratio));
                    if (transportWorld == null)
                    {
                        player.sendMessage(Text.translatable("message.myimproperui.nether_not_found"));
                        return;
                    }
                    break;
                case "theend":
                    transportWorld = server.getWorld(World.END);
                    teleportVec = new Vec3d(3, 64, 3);
                    if (transportWorld == null)
                    {
                        player.sendMessage(Text.translatable("message.myimproperui.theend_not_found"));
                        return;
                    }
                    break;
            }

            teleportVec = MyUtils.findOpenPosition(teleportVec, transportWorld, player, player.getDimensions(player.getPose()));
            teleportVec = new Vec3d(Math.round(teleportVec.x), Math.round(teleportVec.y), Math.round(teleportVec.z));

            BlockState bottomBlock = transportWorld.getBlockState(new BlockPos((int)teleportVec.x, (int)teleportVec.y, (int)teleportVec.z));

            while (!bottomBlock.isAir())
            {
                teleportVec = teleportVec.add(0.0, 1.0, 0.0);
                bottomBlock = transportWorld.getBlockState(new BlockPos((int)teleportVec.x, (int)teleportVec.y, (int)teleportVec.z));
            }

            bottomBlock = transportWorld.getBlockState(new BlockPos((int)teleportVec.x, (int)teleportVec.y - 1, (int)teleportVec.z));

            while (bottomBlock.isAir())
            {
                teleportVec = teleportVec.add(0.0, -1.0, 0.0);
                bottomBlock = transportWorld.getBlockState(new BlockPos((int)teleportVec.x, (int)teleportVec.y - 1, (int)teleportVec.z));
            }
            player.teleport(transportWorld, teleportVec.x, teleportVec.y, teleportVec.z, player.getYaw(), player.getPitch());
        });
    }
}
