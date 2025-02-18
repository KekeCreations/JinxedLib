package com.kekecreations.jinxedlib.common.block;

import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.state.BlockState;

/**
 * Any blocks using this class will be automatically flammable on the NeoForge side.
 * It is recommended you make it flammable on the fabric side as well!
 */
public class FlammableStairBlock extends StairBlock {

    public FlammableStairBlock(BlockState state, Properties properties) {
        super(state, properties);
    }
}
