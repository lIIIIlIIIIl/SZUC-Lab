package org.ep.myimproperui.client;

import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.ep.myimproperui.ModConstants;
import org.ep.myimproperui.client.ItemsClass.BookOfDimensionsItem;

public class ModItems
{
    public static final Item BOOK_OF_DIMENSIONS = new BookOfDimensionsItem(new Item.Settings().maxCount(1));
    public static <T extends Item> T register(String path, T item)
    {
        return Registry.register(Registries.ITEM, Identifier.of(ModConstants.MOD_ID, path), item);
    }

    public static void initialize()
    {
        register("book_of_dimensions", BOOK_OF_DIMENSIONS);
    }
}
