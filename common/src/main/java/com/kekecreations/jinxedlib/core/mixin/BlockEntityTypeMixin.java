package com.kekecreations.jinxedlib.core.mixin;

import com.kekecreations.jinxedlib.core.util.JinxedSignUtils;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BlockEntityType.class)
public class BlockEntityTypeMixin {

    @Inject(method = "isValid", at = @At("HEAD"), cancellable = true)
    private void jinxedlib_isValid(BlockState state, CallbackInfoReturnable<Boolean> cir) {
        var $this = (BlockEntityType) (Object) this;
        if ($this == BlockEntityType.SIGN) {
            for (Block block : JinxedSignUtils.SIGN_IS_VALID) {
                if (state.is(block)) {
                    cir.setReturnValue(true);
                }
            }
        }
        if ($this == BlockEntityType.HANGING_SIGN) {
            for (Block block : JinxedSignUtils.HANGING_SIGN_IS_VALID) {
                if (state.is(block)) {
                    cir.setReturnValue(true);
                }
            }
        }
    }
}
