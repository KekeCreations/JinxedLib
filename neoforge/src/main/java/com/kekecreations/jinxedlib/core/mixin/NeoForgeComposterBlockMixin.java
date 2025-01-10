package com.kekecreations.jinxedlib.core.mixin;

import com.kekecreations.jinxedlib.common.data.JinxedDataMapsUtils;
import it.unimi.dsi.fastutil.objects.Object2FloatMap;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.ComposterBlock;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ComposterBlock.class)
public class NeoForgeComposterBlockMixin {

    @Shadow @Final @Deprecated public static Object2FloatMap<ItemLike> COMPOSTABLES;

    @Inject(method = "getValue", at = @At(value = "TAIL"), cancellable = true)
    private static void getValue(ItemStack item, CallbackInfoReturnable<Float> cir) {
        if (JinxedDataMapsUtils.compostablesContainsKey(item.getItem())) {
            System.out.println("WAS IN JIXED DATA MAP");
            cir.setReturnValue(JinxedDataMapsUtils.getCompostableValue(item.getItem()));
        }
    }
}
