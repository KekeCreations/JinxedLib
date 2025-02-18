package com.kekecreations.jinxedlib.core.mixin;

import com.kekecreations.jinxedlib.common.data.FurnaceFuelUtils;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AbstractFurnaceBlockEntity.class)
public abstract class AbstractFurnaceBlockEntityMixin {

    @Inject(method = "getBurnDuration", at = @At(value = "HEAD"), cancellable = true)
    private void jinxedlib_burn(ItemStack pStack, CallbackInfoReturnable<Integer> cir) {
        AbstractFurnaceBlockEntity $this = (AbstractFurnaceBlockEntity) (Object) this;
        if ($this.getLevel() != null) {
            cir.setReturnValue(FurnaceFuelUtils.getBurnTime($this.getLevel().registryAccess(), pStack));
        }
    }

}
