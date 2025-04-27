package dev.youko.ekrimania;

import dev.youko.ekrimania.block.ModBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.item.Items;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class EkrimaniaLootTableProvider extends FabricBlockLootTableProvider {
    protected EkrimaniaLootTableProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generate() {
        addDrop(ModBlocks.COMPRESSED_TNT, drops(Items.TNT)
                .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(4))));

        addDropWithSilkTouch(ModBlocks.COMPRESSED_TNT);
    }
}
