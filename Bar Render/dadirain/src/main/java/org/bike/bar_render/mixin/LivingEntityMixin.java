package org.bike.bar_render.mixin;

import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {
    @Unique
    private int manaLevelZelda;
    @Unique
    private int manaMaxLevelZelda;
    @Unique
    private boolean regen;
    @Unique
    private int tickPause;
    @Unique
    private int amountToRegen;
    @Unique
    private int whenNeededRenderTime;
    @Inject(method = "<init>", at = @At("TAIL"))
    private void onInit(CallbackInfo ci) {
        this.manaMaxLevelZelda = 100;
        this.manaLevelZelda = 0;
        this.regen = false;
        this.tickPause = 40;
        this.amountToRegen = 1;
        this.whenNeededRenderTime = 0;
    }
}
