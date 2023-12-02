package croncler.tournamentmod;

import croncler.tournamentmod.block.ModBlocks;
import croncler.tournamentmod.item.ModItemGroups;
import croncler.tournamentmod.item.ModItems;
import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TournamentMod implements ModInitializer {
	public static final String MOD_ID = "tournamentmod";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItemGroups.registerItemGroups();
		ModItems.registerModItems();
		ModBlocks.registerModBlocks();
	}
}