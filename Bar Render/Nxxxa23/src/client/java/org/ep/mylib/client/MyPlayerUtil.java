package org.ep.mylib.client;

import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.text.Text;
import net.minecraft.util.Colors;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;

import java.util.HashMap;
import java.util.Map;

public class MyPlayerUtil
{
    private static final float PLAINS_TEMPERATURE = 20.0f;
    private static final float PLAINS_TEMPERATURE_REFER_RATIO = 0.8f;

    public static float calculateTemperature(ClientWorld world, BlockPos pos)
    {
        int range = 10; // 检查周围10个区块的距离
        HashMap<RegistryEntry<Biome>, Float> distances = new HashMap<>();

        for (int xOffset = -range; xOffset <= range; xOffset++) {
            for (int zOffset = -range; zOffset <= range; zOffset++) {
                for (int yOffset = -range; yOffset <= range; yOffset++) { // 检查区块内的每个生物群系
                    BlockPos chunkBlockPos = new BlockPos(pos.getX() + xOffset * 16, pos.getY() + yOffset * 8, pos.getZ() + zOffset * 16);
                    RegistryEntry<Biome> chunkBiome = world.getBiome(chunkBlockPos);
                    float distance = (float)Math.sqrt(pos.getSquaredDistance(chunkBlockPos));

                    if ((distances.getOrDefault(chunkBiome, Float.MAX_VALUE) > distance) && (distance != 0))
                    {
                        distances.put(chunkBiome, distance);
                    }
                }
            }
        }

        float totalWeightedTemperature = 0f;
        float totalWeight = 0f;

        for (Map.Entry<RegistryEntry<Biome>, Float> entry : distances.entrySet())
        {
            float weight = 1f / entry.getValue(); // 距离越近，权重越大
            totalWeight += weight;
            totalWeightedTemperature += weight * (entry.getKey().value().getTemperature() / PLAINS_TEMPERATURE_REFER_RATIO * PLAINS_TEMPERATURE);
        }


        return totalWeightedTemperature / totalWeight;
    }
}
