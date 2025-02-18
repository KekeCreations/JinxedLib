package com.kekecreations.jinxedlib.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.StandingSignBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.SignBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.WoodType;

public class CommonStandingSignBlock extends StandingSignBlock {
    public CommonStandingSignBlock(WoodType woodType, Properties properties) {
        super(woodType, properties);
    }

    /**
     * This fixes the block being invisible due to no block entity spawning
     */
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new SignBlockEntity(pos, state);
    }
}
