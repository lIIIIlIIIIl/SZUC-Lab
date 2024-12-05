package org.ep.myimproperui.client.ItemsClass;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.WritableBookItem;
import net.minecraft.stat.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.ep.myimproperui.client.MyimproperuiClient;

public class BookOfDimensionsItem extends Item
{

    public BookOfDimensionsItem(Settings settings)
    {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand)
    {
        if (hand == Hand.MAIN_HAND)
        {
            MyimproperuiClient.lastOperation = null;
            MyimproperuiClient.nextOperation = MyimproperuiClient.ChangePageOperation.ToOverWorldPage;
        }

        return TypedActionResult.fail(user.getStackInHand(hand));
    }
}
