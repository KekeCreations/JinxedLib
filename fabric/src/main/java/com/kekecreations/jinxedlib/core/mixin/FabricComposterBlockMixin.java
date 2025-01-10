package com.kekecreations.jinxedlib.core.mixin;

import com.kekecreations.jinxedlib.common.data.JinxedDataMapsUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.ComposterBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ComposterBlock.class)
public class FabricComposterBlockMixin {

    @Shadow
    static BlockState addItem(@Nullable Entity entity, BlockState state, LevelAccessor level, BlockPos pos, ItemStack stack) {
        return null;
    }

    @Inject(method = "useItemOn", at = @At(value = "TAIL"), cancellable = true)
    private void jinxedlib_useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult, CallbackInfoReturnable<ItemInteractionResult> cir) {
        int i = state.getValue(ComposterBlock.LEVEL);
        if (i < 8 && JinxedDataMapsUtils.compostablesContainsKey(stack.getItem())) {
            if (i < 7 && !level.isClientSide) {
                BlockState blockState = addItem(player, state, level, pos, stack);
                level.levelEvent(1500, pos, state != blockState ? 1 : 0);
                player.awardStat(Stats.ITEM_USED.get(stack.getItem()));
                stack.consume(1, player);
            }

            cir.setReturnValue(ItemInteractionResult.sidedSuccess(level.isClientSide));
        }
    }

    @Inject(method = "addItem", at = @At(value = "TAIL"), cancellable = true)
    private static void jinxedlib_addItem(Entity entity, BlockState state, LevelAccessor level, BlockPos pos, ItemStack stack, CallbackInfoReturnable<BlockState> cir) {
        if (JinxedDataMapsUtils.compostablesContainsKey(stack.getItem())) {
            int i = state.getValue(ComposterBlock.LEVEL);
            float f = JinxedDataMapsUtils.getCompostableValue(stack.getItem());
            if ((i != 0 || !(f > 0.0F)) && !(level.getRandom().nextDouble() < (double) f)) {
                cir.setReturnValue(state);
            } else {
                int j = i + 1;
                BlockState blockState = (BlockState) state.setValue(ComposterBlock.LEVEL, j);
                level.setBlock(pos, blockState, 3);
                level.gameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Context.of(entity, blockState));
                if (j == 7) {
                    level.scheduleTick(pos, state.getBlock(), 20);
                }

                cir.setReturnValue(blockState);
            }
        }
    }
}
