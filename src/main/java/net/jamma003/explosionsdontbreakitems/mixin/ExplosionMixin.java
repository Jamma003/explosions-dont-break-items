package net.jamma003.explosionsdontbreakitems.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.world.explosion.Explosion;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(Explosion.class)
public abstract class ExplosionMixin {

    @Redirect(method = "collectBlocksAndDamageEntities",
            at = @At(value = "INVOKE",
                    target = "Lnet/minecraft/entity/Entity;damage(Lnet/minecraft/entity/damage/DamageSource;F)Z"))
    private boolean noItemDamage(Entity instance, DamageSource source, float amount) {
        if (instance instanceof ItemEntity) {
            return false; // Skip damage
        }
        return instance.damage(source, amount); // Proceed normally
    }
}