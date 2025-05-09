package dev.youko.ekrimania.block;
import dev.youko.ekrimania.Ekrimania;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.TntBlock;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

import java.util.function.Function;

public class ModBlocks {
    public static final Block COMPRESSED_TNT = register(
            "compressed_tnt",
            TntBlock::new,
            AbstractBlock.Settings.create().sounds(BlockSoundGroup.GRASS),
            true
    ), SHOCKWAVE_TNT = register(
            "shockwave_tnt",
            TntBlock::new,
            AbstractBlock.Settings.create().sounds(BlockSoundGroup.GRASS),
            true
    ), FIREBOMB_TNT = register(
            "firebomb_tnt",
            TntBlock::new,
            AbstractBlock.Settings.create().sounds(BlockSoundGroup.GRASS),
            true
    );

    private static Block register(String name, Function<AbstractBlock.Settings, Block> blockFactory, AbstractBlock.Settings settings, boolean shouldRegisterItem) {
        RegistryKey<Block> blockKey = keyOfBlock(name);
        Block block = blockFactory.apply(settings.registryKey(blockKey));

        if (shouldRegisterItem) {
            RegistryKey<Item> itemKey = keyOfItem(name);

            BlockItem blockItem = new BlockItem(block, new Item.Settings().registryKey(itemKey));
            Registry.register(Registries.ITEM, itemKey, blockItem);
        }

        return Registry.register(Registries.BLOCK, blockKey, block);
    }

    private static RegistryKey<Block> keyOfBlock(String name) {
        return RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(Ekrimania.MOD_ID, name));
    }

    private static RegistryKey<Item> keyOfItem(String name) {
        return RegistryKey.of(RegistryKeys.ITEM, Identifier.of(Ekrimania.MOD_ID, name));
    }

    public static void initialize() {
        Ekrimania.LOGGER.info("Initializing Ekrimania blocks");

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register((itemGroup) -> {
            itemGroup.add(ModBlocks.COMPRESSED_TNT.asItem());
            itemGroup.add(ModBlocks.SHOCKWAVE_TNT.asItem());
            itemGroup.add(ModBlocks.FIREBOMB_TNT.asItem());
        });

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.REDSTONE).register((itemGroup) -> {
            itemGroup.add(ModBlocks.COMPRESSED_TNT.asItem());
            itemGroup.add(ModBlocks.SHOCKWAVE_TNT.asItem());
            itemGroup.add(ModBlocks.FIREBOMB_TNT.asItem());
        });
    }
}