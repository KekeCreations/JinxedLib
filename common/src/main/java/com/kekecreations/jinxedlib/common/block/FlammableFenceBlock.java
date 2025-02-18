package com.kekecreations.jinxedlib.common.block;

import net.minecraft.world.level.block.FenceBlock;

/**
 * Any blocks using this class will be automatically flammable on the NeoForge side.
 * It is recommended you make it flammable on the fabric side as well!
 */
public class FlammableFenceBlock extends FenceBlock {

    public FlammableFenceBlock(Properties properties) {
        super(properties);
    }
}
