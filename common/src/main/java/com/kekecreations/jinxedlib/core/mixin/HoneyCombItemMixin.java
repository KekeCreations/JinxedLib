package com.kekecreations.jinxedlib.core.mixin;

import com.kekecreations.jinxedlib.common.data.WaxableUtils;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.ParticleUtils;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.HoneycombItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(HoneycombItem.class)
public class HoneyCombItemMixin {


    @Inject(method = "useOn", at = @At(value = "HEAD"), cancellable = true)
    public void jinxedlib_useOn(UseOnContext pContext, CallbackInfoReturnable<InteractionResult> cir) {
        Level level = pContext.getLevel();
        BlockPos blockpos = pContext.getClickedPos();
        BlockState blockstate = level.getBlockState(blockpos);
        Player player = pContext.getPlayer();
        ItemStack itemstack = pContext.getItemInHand();
        //Only on server
        boolean returnValue = false;
        if (!level.isClientSide()) {
            if (WaxableUtils.canWaxBlock(level.registryAccess(), blockstate)) {
                Block block = WaxableUtils.getWaxedBlock(level.registryAccess(), blockstate);
                if (block != null) {
                    if (player instanceof ServerPlayer) {
                        CriteriaTriggers.ITEM_USED_ON_BLOCK.trigger((ServerPlayer) player, blockpos, itemstack);
                    }

                    itemstack.shrink(1);
                    level.setBlock(blockpos, block.defaultBlockState(), 11);
                    level.gameEvent(GameEvent.BLOCK_CHANGE, blockpos, GameEvent.Context.of(player, block.defaultBlockState()));

                    returnValue = true;
                }
            }
        }
        //Figure out particle not spawning cuz return value is server only and false on client still
        if (returnValue) {
            if (level.isClientSide) {
                System.out.println(returnValue);
                ParticleUtils.spawnParticlesOnBlockFaces(level, blockpos, ParticleTypes.WAX_ON, UniformInt.of(3, 5));
                level.playSound(null, blockpos, SoundEvents.HONEYCOMB_WAX_ON, SoundSource.BLOCKS);
                //level.playLocalSound(blockpos, SoundEvents.HONEYCOMB_WAX_ON, SoundSource.BLOCKS, 1.0F, 1.0F, false);
            }
            cir.setReturnValue(InteractionResult.SUCCESS);
        }
    }
}
