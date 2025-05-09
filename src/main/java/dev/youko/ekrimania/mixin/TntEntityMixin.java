package dev.youko.ekrimania.mixin;

import dev.youko.ekrimania.block.ModBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.TntEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;
import net.minecraft.world.explosion.ExplosionBehavior;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;

@Mixin(TntEntity.class)
public abstract class TntEntityMixin extends Entity {
    @Unique
    private static final ExplosionBehavior SHOCKWAVE_TNT_BEHAVIOR = new ExplosionBehavior() {
        @Override
        public boolean canDestroyBlock(Explosion explosion, BlockView world, BlockPos pos, BlockState state, float power) { return false; }

        @Override
        public float getKnockbackModifier(Entity entity) {
            return 2.0F;
        }
    }, FIREBOMB_TNT_BEHAVIOR = new ExplosionBehavior() {
        @Override
        public boolean canDestroyBlock(Explosion explosion, BlockView world, BlockPos pos, BlockState state, float power) { return false; }

        @Override
        public float getKnockbackModifier(Entity entity) {
            return 0F;
        }
    };

    public TntEntityMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @Shadow
    public BlockState getBlockState() { return null; }

    @ModifyArgs(method = "explode", at = @At(value = "INVOKE", target="Lnet/minecraft/world/World;createExplosion(Lnet/minecraft/entity/Entity;Lnet/minecraft/entity/damage/DamageSource;Lnet/minecraft/world/explosion/ExplosionBehavior;DDDFZLnet/minecraft/world/World$ExplosionSourceType;)V"))
    private void explodeWrap(org.spongepowered.asm.mixin.injection.invoke.arg.Args args) {
        float power = 4.0f;
        ExplosionBehavior behavior = null;
        boolean fire = false;

        if(getBlockState().isOf(ModBlocks.COMPRESSED_TNT)) {
            power = 8.0f;
        } else if(getBlockState().isOf(ModBlocks.SHOCKWAVE_TNT)) {
            behavior = SHOCKWAVE_TNT_BEHAVIOR;
        } else if(getBlockState().isOf(ModBlocks.FIREBOMB_TNT)) {
            fire = true;
        }

        args.set(2, behavior);
        args.set(6, power);
        args.set(7, fire);
    }
}
