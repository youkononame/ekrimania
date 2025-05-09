package dev.youko.ekrimania;

import dev.youko.ekrimania.block.ModBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class EkrimaniaLootTableProvider extends FabricBlockLootTableProvider {
    protected EkrimaniaLootTableProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generate() {
        // TODO: make drop 4 regular TNT when not mined with silk touch
        addDrop(ModBlocks.COMPRESSED_TNT);

        addDrop(ModBlocks.SHOCKWAVE_TNT);
        addDrop(ModBlocks.FIREBOMB_TNT);
    }

    @Override
    public String getName() {
        return "EkrimaniaLootTablesProvider";
    }
}
