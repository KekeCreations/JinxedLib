package com.kekecreations.jinxedlib.common.block;

import net.minecraft.world.level.block.FenceGateBlock;
import net.minecraft.world.level.block.state.properties.WoodType;

/**
 * Any blocks using this class will be automatically flammable on the NeoForge side.
 * It is recommended you make it flammable on the fabric side as well!
 */
public class FlammableFenceGateBlock extends FenceGateBlock {

    public FlammableFenceGateBlock(WoodType woodType, Properties properties) {
        super(woodType, properties);
    }
}
