package croncler.tournamentmod.item;

import croncler.tournamentmod.TournamentMod;
import croncler.tournamentmod.block.ModBlocks;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroups {
    public static final ItemGroup COOL_GROUP = Registry.register(Registries.ITEM_GROUP,
            new Identifier(TournamentMod.MOD_ID,"dildo"),
            FabricItemGroup.builder().displayName(Text.translatable("itemgroup.dildo"))

                    .icon(() -> new ItemStack(ModItems.DILDO)).entries(((displayContext, entries) -> {

                        entries.add(ModItems.DILDO);
                        entries.add(ModBlocks.KILLER_BLOCK);
                        entries.add(ModBlocks.TOGGLE_BLOCK);
                    }))

                    .build());



    public static void registerItemGroups() {
        TournamentMod.LOGGER.info("Registering the item group in " + TournamentMod.MOD_ID);
    }
}
