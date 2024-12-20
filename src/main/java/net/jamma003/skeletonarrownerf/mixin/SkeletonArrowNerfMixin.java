package net.jamma003.skeletonarrownerf.mixin;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.AbstractSkeletonEntity;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(AbstractSkeletonEntity.class)
abstract class SkeletonArrowNerfMixin extends HostileEntity {
    protected SkeletonArrowNerfMixin(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
    }

    @Redirect(
            method = "attack",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/entity/projectile/PersistentProjectileEntity;setVelocity(DDDFF)V"
            )
    )
    public void redirectSetVelocity(PersistentProjectileEntity persistentProjectileEntity, double x, double y, double z, float velocity, float inaccuracy) {
        persistentProjectileEntity.setVelocity(x, y, z, velocity - 0.5F, inaccuracy * 0.5F);
    }
}