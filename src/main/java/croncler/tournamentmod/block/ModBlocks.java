package croncler.tournamentmod.block;

import croncler.tournamentmod.TournamentMod;
import croncler.tournamentmod.block.custom.ToggleBlock;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.CropBlock;
import net.minecraft.block.LeavesBlock;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlocks {

    public static final Block KILLER_BLOCK = registerBlock("killer_blocki",
            new Block(FabricBlockSettings.copyOf(Blocks.WHITE_WOOL)));

    public static final Block TOGGLE_BLOCK = registerBlock("toggle_block",
            new ToggleBlock(FabricBlockSettings.copyOf(Blocks.WHITE_WOOL).nonOpaque()));

    public static final Block SPEED_BLOCK = registerBlock("speed_boost_block",
            new Block(FabricBlockSettings.copyOf(Blocks.WHITE_WOOL).velocityMultiplier(2f)));

    public static final Block JUMP_BLOCK = registerBlock("jump_boost_block",
            new Block(FabricBlockSettings.copyOf(Blocks.WHITE_WOOL).jumpVelocityMultiplier(3f)));

    public static final Block ANTI_JUMP_BLOCK = registerBlock("anti_jump_boost_block",
            new Block(FabricBlockSettings.copyOf(Blocks.WHITE_WOOL).jumpVelocityMultiplier(0.4f)));

    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name,block);
        return Registry.register(Registries.BLOCK, new Identifier(TournamentMod.MOD_ID, name), block);
    }

    private static Item registerBlockItem(String name, Block block) {
        return Registry.register(Registries.ITEM, new Identifier(TournamentMod.MOD_ID, name),
               new BlockItem(block, new FabricItemSettings()));
    }

    public static void registerModBlocks() {
        TournamentMod.LOGGER.info("Registering ModBlocks for " + TournamentMod.MOD_ID);
    }
}
