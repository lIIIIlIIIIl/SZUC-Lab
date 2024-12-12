package org.ep.myimproperui;

import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockLocating;
import net.minecraft.world.TeleportTarget;
import net.minecraft.world.border.WorldBorder;
import net.minecraft.world.dimension.DimensionType;

import java.util.Optional;

public class MyUtils
{
    public static Vec3d findOpenPosition(Vec3d fallback, ServerWorld world, Entity entity, EntityDimensions dimensions)
    {

        WorldBorder worldBorder = world.getWorldBorder();

        if (!(dimensions.width > 4.0F) && !(dimensions.height > 4.0F))
        {
            double d = (double)dimensions.height / 2.0;
            Vec3d vec3d = fallback.add(0.0, d, 0.0);
            VoxelShape voxelShape = VoxelShapes.cuboid(Box.of(vec3d, (double)dimensions.width, 0.0, (double)dimensions.width).stretch(0.0, 1.0, 0.0).expand(1.0E-6));
            Optional<Vec3d> optional = world.findClosestCollision(entity, voxelShape, vec3d, (double)dimensions.width, (double)dimensions.height, (double)dimensions.width);
            Optional<Vec3d> optional2 = optional.map((pos) -> {
                return pos.subtract(0.0, d, 0.0);
            });
            return (Vec3d)optional2.orElse(fallback);
        }
        else
        {
            return fallback;
        }
    }
}
