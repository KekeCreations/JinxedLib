package com.kekecreations.jinxedlib.core.mixin;

import com.kekecreations.jinxedlib.common.data.FurnaceFuelUtils;
import net.minecraft.world.inventory.AbstractFurnaceMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AbstractFurnaceMenu.class)
public class AbstractFurnaceMenuMixin {

    @Final
    @Shadow
    protected Level level;

    @Inject(method = "isFuel", at = @At(value = "HEAD"), cancellable = true)
    private void jinxedlib_isFuel(ItemStack pStack, CallbackInfoReturnable<Boolean> cir) {
        if (!level.isClientSide()) {
            cir.setReturnValue(FurnaceFuelUtils.isItemFurnaceFuel(level.registryAccess(), pStack));
        }
    }
}
