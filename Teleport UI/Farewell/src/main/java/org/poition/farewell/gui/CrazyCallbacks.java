package org.poition.farewell.gui;

import io.github.itzispyder.improperui.ImproperUIAPI;
import io.github.itzispyder.improperui.script.CallbackHandler;
import io.github.itzispyder.improperui.script.CallbackListener;
import io.github.itzispyder.improperui.script.events.MouseEvent;
import net.minecraft.entity.Entity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.block.Blocks;
import net.minecraft.block.BlockState;
import net.minecraft.world.World;

public class CrazyCallbacks implements CallbackListener {
    Entity entity;

    public CrazyCallbacks(Entity entity) {
        this.entity = entity;
    }

    @CallbackHandler
    public void teleportToOverworld(MouseEvent e) {
        if (e.input.isDown() && !entity.getWorld().isClient) {
            entity.detach();
            entity.setVelocity(Vec3d.ZERO);
            MinecraftServer server = entity.getWorld().getServer();

            if (server == null)
                return;

            ServerWorld serverWorld = server.getWorld(World.OVERWORLD);

            if (entity.getWorld().getRegistryKey().equals(World.END)) { entity.moveToWorld(serverWorld); return; }

            if (serverWorld != null && server.isNetherAllowed()) {

                int movementFactor = entity.getWorld().getRegistryKey() == World.NETHER ? 8 : 1;
                BlockPos pos = createDestinationSpawn(new BlockPos((int) (entity.getPos().getX() * movementFactor), (int) entity.getPos().getY(), (int) (entity.getPos().getZ() * movementFactor)), serverWorld);

                if (entity instanceof ServerPlayerEntity) {
                    ((ServerPlayerEntity) entity).teleport(serverWorld, pos.getX() + 0.5D, pos.getY(), pos.getZ() + 0.5D, entity.getYaw(), entity.getPitch());
                } else {
                    entity.remove(Entity.RemovalReason.CHANGED_DIMENSION);
                    entity.refreshPositionAndAngles(pos.getX() + 0.5D, pos.getY(), pos.getZ() + 0.5D, entity.getYaw(), entity.getPitch());
                    entity.moveToWorld(serverWorld);
                }
            }
        }
    }

    @CallbackHandler
    public void teleportToNether(MouseEvent e) {
        if (e.input.isDown() && !entity.getWorld().isClient) {
            entity.detach();
            entity.setVelocity(Vec3d.ZERO);

            MinecraftServer server = entity.getWorld().getServer();

            if (server == null)
                return;

            ServerWorld serverWorld = server.getWorld(World.NETHER);

            if (serverWorld != null && server.isNetherAllowed()) {

                double movementFactor = entity.getWorld().getRegistryKey() == World.OVERWORLD ? 0.125d : 1;
                BlockPos pos = createDestinationSpawn(new BlockPos((int) (entity.getPos().getX() * movementFactor), (int) entity.getPos().getY(), (int) (entity.getPos().getZ() * movementFactor)), serverWorld);

                if (entity instanceof ServerPlayerEntity) {
                    ((ServerPlayerEntity) entity).teleport(serverWorld, pos.getX() + 0.5D, pos.getY(), pos.getZ() + 0.5D, entity.getYaw(), entity.getPitch());
                } else {
                    entity.remove(Entity.RemovalReason.CHANGED_DIMENSION);
                    entity.refreshPositionAndAngles(pos.getX() + 0.5D, pos.getY(), pos.getZ() + 0.5D, entity.getYaw(), entity.getPitch());
                    entity.moveToWorld(serverWorld);
                }
            }
        }
    }

    @CallbackHandler
    public void teleportToEnd(MouseEvent e) {
        if (e.input.isDown() && !entity.getWorld().isClient) {
            entity.detach();
            entity.setVelocity(Vec3d.ZERO);

            MinecraftServer server = entity.getWorld().getServer();

            if (server == null)
                return;

            ServerWorld serverWorld = server.getWorld(World.END);

            entity.moveToWorld(serverWorld);
        }
    }

    @CallbackHandler
    public void turnToNether(MouseEvent e) {
        if (e.input.isDown()) {
            ImproperUIAPI.parseAndRunFile("farewell", "nether.ui",new CrazyCallbacks(entity));
        }
    }

    @CallbackHandler
    public void turnToEnd(MouseEvent e) {
        if (e.input.isDown()) {
            ImproperUIAPI.parseAndRunFile("farewell", "end.ui", new CrazyCallbacks(entity));
        }
    }

    @CallbackHandler
    public void turnToOverworld(MouseEvent e) {
        if (e.input.isDown()) {
            ImproperUIAPI.parseAndRunFile("farewell", "overworld.ui", new CrazyCallbacks(entity));
        }
    }

    private static BlockPos createDestinationSpawn(BlockPos posIn, World serverWorld) {
        double bestDistance = Double.MAX_VALUE;
        int posX = MathHelper.floor(posIn.getX());
        int posY = MathHelper.floor(posIn.getY());
        int posZ = MathHelper.floor(posIn.getZ());

        int bestX = posX;
        int bestY = posY;
        int bestZ = posZ;
        BlockPos.Mutable mutable = new BlockPos.Mutable();


        for (int xIndex = posX - 16; xIndex <= posX + 16; ++xIndex) {
            double xDistance = (double) xIndex + 0.5D - posX;

            for (int zIndex = posZ - 16; zIndex <= posZ + 16; ++zIndex) {
                double zDistance = (double) zIndex + 0.5D - posZ;

                for (int yIndex = 128 - 1; yIndex >= 0; --yIndex) {
                    if (serverWorld.isAir(mutable.set(xIndex, yIndex, zIndex))) {

                        while (yIndex > 0 && serverWorld.isAir(mutable.set(xIndex, yIndex - 1, zIndex))) {
                            --yIndex;
                        }

                        if (!serverWorld.getBlockState(mutable.set(xIndex, yIndex - 1, zIndex)).isOpaque())
                            continue;

                        if (!serverWorld.isAir(mutable.set(xIndex, yIndex + 1, zIndex)))
                            continue;

                        double yDistance = (double) yIndex + 0.5D - posY;
                        double distance = Math.sqrt(Math.pow(xDistance, 2) + Math.pow(yDistance, 2) + Math.pow(zDistance, 2));
                        if (distance < bestDistance) {
                            bestDistance = distance;
                            bestX = xIndex;
                            bestY = yIndex;
                            bestZ = zIndex;
                        }
                    }
                }
            }
        }


        if (bestDistance == Double.MAX_VALUE) {
            for (int xIndex = -1; xIndex <= 1; xIndex++) {
                for (int zIndex = -1; zIndex <= 1; zIndex++) {
                    BlockState bs = serverWorld.getBlockState(mutable.set(posX + xIndex, posY - 1, posZ + zIndex));
                    if (!bs.isOpaque() && bs.getBlock() != Blocks.BEDROCK) {
                        serverWorld.setBlockState(mutable, Blocks.OBSIDIAN.getDefaultState());
                    }
                }
            }
            for (int yIndex = 0; yIndex <= 1; yIndex++) {
                for (int xIndex = -1; xIndex <= 1; xIndex++) {
                    for (int zIndex = -1; zIndex <= 1; zIndex++) {
                        BlockState bs = serverWorld.getBlockState(mutable.set(posX + xIndex, posY + yIndex, posZ + zIndex));
                        if (bs.getBlock() != Blocks.BEDROCK) {
                            serverWorld.setBlockState(mutable, Blocks.AIR.getDefaultState());
                        }
                    }
                }
            }
        }

        return mutable.set(bestX, bestY, bestZ);
    }

}
