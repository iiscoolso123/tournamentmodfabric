package croncler.tournamentmod.item;

import croncler.tournamentmod.TournamentMod;
import croncler.tournamentmod.item.custom.lillababy;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {
    public static final Item WAND = registerItem("wand", new Item(new FabricItemSettings()));
    public static final Item FLESH = registerItem("flesh", new Item(new FabricItemSettings().food(ModFoodComponents.FLESH)));
    public static final Item LILLA_BABY = registerItem("lilla_baby", new lillababy(new FabricItemSettings().food(ModFoodComponents.FLESH)));

    public static final Item LIME_FEET = registerItem("lime_feet", new Item(new FabricItemSettings().food(ModFoodComponents.LIME_FEET)));

    private static void addItemstoCombatTabItemGroup(FabricItemGroupEntries entries) {
        entries.add(WAND);
    }

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(TournamentMod.MOD_ID,name), item);
    }

    public static void registerModItems() {
        TournamentMod.LOGGER.info("Registering Mod Items for " + TournamentMod.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(ModItems::addItemstoCombatTabItemGroup);
    }
}
