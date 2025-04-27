package dev.youko.ekrimania;

import dev.youko.ekrimania.block.ModBlocks;
import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Ekrimania implements ModInitializer {
	public static final String MOD_ID = "ekrimania";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		LOGGER.info("Initializing Ekrimania");

		ModBlocks.initialize();
	}
}