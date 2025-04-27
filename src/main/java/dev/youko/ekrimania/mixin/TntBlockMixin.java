package dev.youko.ekrimania.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import com.llamalad7.mixinextras.sugar.ref.LocalRef;
import dev.youko.ekrimania.block.ModBlocks;
import net.minecraft.block.TntBlock;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.TntEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(TntBlock.class)
public class TntBlockMixin {
    private static final float COMPRESSED_TNT_POWER = 8.0f;

    @Unique
    private static void replaceTnt(TntEntity tntEntity) {
        ((TntEntityAccessor)tntEntity).setExplosionPower(COMPRESSED_TNT_POWER);
        tntEntity.setBlockState(ModBlocks.COMPRESSED_TNT.getDefaultState());
    }

    @Inject(method = "onDestroyedByExplosion", at = @At(value = "INVOKE", target="Lnet/minecraft/entity/TntEntity;getFuse()I"))
    private void onDestroyedByExplosionWrap(CallbackInfo ci, @Local LocalRef<TntEntity> tntEntity) {
        if((Object)this == ModBlocks.COMPRESSED_TNT) {
            replaceTnt(tntEntity.get());
        }
    }

    @Inject(method = "primeTnt(Lnet/minecraft/world/World;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/entity/LivingEntity;)Z", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;spawnEntity(Lnet/minecraft/entity/Entity;)Z"))
    private static void primeTntWrap(World world, BlockPos pos, LivingEntity igniter, CallbackInfoReturnable<Boolean> cir, @Local LocalRef<TntEntity> tntEntity) {
        if(world.getBlockState(pos) == ModBlocks.COMPRESSED_TNT.getDefaultState()) {
            replaceTnt(tntEntity.get());
        }
    }
}
