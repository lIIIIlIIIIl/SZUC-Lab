package org.poition.farewell;

import io.github.itzispyder.improperui.ImproperUIAPI;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.poition.farewell.common.item.DimensionBookItem;

public class Farewell implements ModInitializer {
    String[] screens = {
            "assets/farewell/improperui/overworld.ui",
            "assets/farewell/improperui/nether.ui",
            "assets/farewell/improperui/end.ui"
    };

    public static final DimensionBookItem DIMENSION_BOOK = new DimensionBookItem();

    @Override
    public void onInitialize() {
        ImproperUIAPI.init("farewell", Farewell.class, screens);
        Registry.register(Registries.ITEM, Identifier.of("farewell", "dimension_book"), DIMENSION_BOOK);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS)
                .register((itemGroup) -> itemGroup.add(DIMENSION_BOOK));
    }

}