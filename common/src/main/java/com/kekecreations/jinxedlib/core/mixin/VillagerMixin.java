package com.kekecreations.jinxedlib.core.mixin;

import com.kekecreations.jinxedlib.common.data.CompostableUtils;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.npc.villager.Villager;
import net.minecraft.world.entity.npc.villager.VillagerProfession;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Villager.class)
public class VillagerMixin {

    @Inject(method = "wantsToPickUp", at = @At(value = "RETURN"), cancellable = true)
    public void jinxedlib_wantsToPickUp(ServerLevel level, ItemStack pStack, CallbackInfoReturnable<Boolean> cir) {
        Villager $this = (Villager) (Object) this;
        if ($this.getVillagerData().profession().is(VillagerProfession.FARMER)) {
            if (CompostableUtils.canVillagerCompost($this.level().registryAccess(), pStack)) {
                cir.setReturnValue(true);
            }
        }

    }
}
