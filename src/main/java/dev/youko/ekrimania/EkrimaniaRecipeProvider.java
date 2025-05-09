package dev.youko.ekrimania;

import dev.youko.ekrimania.block.ModBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.recipe.RecipeExporter;
import net.minecraft.data.recipe.RecipeGenerator;
import net.minecraft.item.Items;
import net.minecraft.predicate.NumberRange;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class EkrimaniaRecipeProvider extends FabricRecipeProvider {
    public EkrimaniaRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected RecipeGenerator getRecipeGenerator(RegistryWrapper.WrapperLookup registryLookup, RecipeExporter exporter) {
        return new RecipeGenerator(registryLookup, exporter) {
            @Override
            public void generate() {
                createShaped(RecipeCategory.COMBAT, ModBlocks.COMPRESSED_TNT, 1)
                        .pattern("tt")
                        .pattern("tt")
                        .input('t', Items.TNT)
                        .criterion(hasItem(Items.TNT), conditionsFromItem(Items.TNT))
                        .offerTo(exporter);

                createShapeless(RecipeCategory.COMBAT, ModBlocks.SHOCKWAVE_TNT, 1)
                        .input(Items.TNT)
                        .input(Items.GUNPOWDER)
                        .criterion(hasItem(Items.GUNPOWDER), conditionsFromItem(Items.GUNPOWDER))
                        .offerTo(exporter);

                createShapeless(RecipeCategory.COMBAT, ModBlocks.FIREBOMB_TNT, 1)
                        .input(Items.TNT)
                        .input(Items.FIRE_CHARGE)
                        .criterion(hasItem(Items.FIRE_CHARGE), conditionsFromItem(Items.FIRE_CHARGE))
                        .offerTo(exporter);
            }
        };
    }

    @Override
    public String getName() {
        return "EkrimaniaRecipeProvider";
    }
}
