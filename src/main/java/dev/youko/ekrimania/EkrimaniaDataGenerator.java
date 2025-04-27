package dev.youko.ekrimania;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class EkrimaniaDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		Ekrimania.LOGGER.info("Initializing Ekrimania data generator");

		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
		pack.addProvider(EkrimaniaRecipeProvider::new);
		pack.addProvider(EkrimaniaLootTableProvider::new);
	}
}
