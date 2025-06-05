package com.kekecreations.jinxedlib.common.block;

import net.minecraft.world.level.block.SlabBlock;

/**
 * Any blocks using this class will be automatically flammable on the Forge side.
 * It is recommended you make it flammable on the fabric side as well!
 */
public class FlammableSlabBlock extends SlabBlock {

    public FlammableSlabBlock(Properties properties) {
        super(properties);
    }
}
