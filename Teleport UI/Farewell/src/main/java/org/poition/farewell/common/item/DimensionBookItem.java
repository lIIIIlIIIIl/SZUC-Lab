package org.poition.farewell.common.item;

import io.github.itzispyder.improperui.ImproperUIAPI;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.poition.farewell.gui.CrazyCallbacks;

public class DimensionBookItem extends Item {
    public DimensionBookItem() {
        super(new FabricItemSettings().maxCount(1));
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (!world.isClient) {
            openScreen(user);
        }
        return TypedActionResult.pass(user.getStackInHand(hand));
    }

    public void openScreen(Entity entity) {
        ImproperUIAPI.parseAndRunFile("farewell", "overworld.ui", new CrazyCallbacks(entity));
    }

}
