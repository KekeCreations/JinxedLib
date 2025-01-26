package com.kekecreations.jinxedlib.core.mixin;

import com.kekecreations.jinxedlib.common.data.CompostableUtils;
import it.unimi.dsi.fastutil.objects.Reference2IntMap;
import it.unimi.dsi.fastutil.objects.Reference2IntOpenHashMap;
import net.minecraft.core.BlockPos;
import net.minecraft.core.GlobalPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.ai.behavior.WorkAtComposter;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.ComposterBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(WorkAtComposter.class)
public class WorkAtComposterMixin {
    
    
    @Shadow
    private static final List<Item> COMPOSTABLE_ITEMS = List.of();

    @Shadow
    private void spawnComposterFillEffects(ServerLevel pLevel, BlockState pPreState, BlockPos pPos, BlockState pPostState) {
        pLevel.levelEvent(1500, pPos, pPostState != pPreState ? 1 : 0);
    }

    @Inject(method = "compostItems", at = @At(value = "INVOKE", target = "net/minecraft/world/entity/npc/Villager.getInventory ()Lnet/minecraft/world/SimpleContainer;"))
    public void jinxedlib_compostItems(ServerLevel pLevel, Villager pVillager, GlobalPos pGlobal, BlockState pState, CallbackInfo ci) {
        BlockPos blockPos = pGlobal.pos();
        int i = 20;
        SimpleContainer simplecontainer = pVillager.getInventory();
        int k = simplecontainer.getContainerSize();
        BlockState blockstate = pState;
        Reference2IntMap<Item> amounts = new Reference2IntOpenHashMap(COMPOSTABLE_ITEMS.size() * 2);

        for (int l = k - 1; l >= 0 && i > 0; --l) {
            ItemStack itemstack = simplecontainer.getItem(l);
            if (CompostableUtils.canVillagerCompost(pLevel.registryAccess(), itemstack)) {
                int j1 = itemstack.getCount();
                int k1 = amounts.getInt(itemstack.getItem()) + j1;
                amounts.put(itemstack.getItem(), k1);
                int l1 = Math.min(Math.min(k1 - 10, i), j1);
                if (l1 > 0) {
                    i -= l1;

                    for (int i2 = 0; i2 < l1; ++i2) {
                        blockstate = ComposterBlock.insertItem(pVillager, blockstate, pLevel, itemstack, blockPos);
                        if (blockstate.getValue(ComposterBlock.LEVEL) == 7) {
                            spawnComposterFillEffects(pLevel, pState, blockPos, blockstate);
                            return;
                        }
                    }
                }
            }
        }
    }
}
