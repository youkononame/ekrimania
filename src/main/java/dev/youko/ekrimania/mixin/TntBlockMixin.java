package dev.youko.ekrimania.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import dev.youko.ekrimania.block.ModBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.TntBlock;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.TntEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(TntBlock.class)
public class TntBlockMixin {
    @Unique
    private static void replaceTnt(TntEntity tntEntity, BlockState state) {
        tntEntity.setBlockState(state);
    }

    @Inject(method = "onDestroyedByExplosion", at = @At(value = "INVOKE", target="Lnet/minecraft/entity/TntEntity;getFuse()I"))
    private void onDestroyedByExplosionWrap(CallbackInfo ci, @Local TntEntity tntEntity) {
        TntBlock thisObject = (TntBlock)(Object)this;
        replaceTnt(tntEntity, thisObject.getDefaultState());
    }

    @Inject(method = "primeTnt(Lnet/minecraft/world/World;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/entity/LivingEntity;)Z", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;spawnEntity(Lnet/minecraft/entity/Entity;)Z"))
    private static void primeTntWrap(World world, BlockPos pos, LivingEntity igniter, CallbackInfoReturnable<Boolean> cir, @Local TntEntity tntEntity) {
        replaceTnt(tntEntity, world.getBlockState(pos));
    }
}